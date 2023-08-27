/***医信签电子签名**/
let signConfig = {};
let timer = null;

/**
 * 配置赋值
 * @param workNo : string 工号
 * @param data : string 待签名数据原文
 * @param authKEY : string 临时授权密码
 * @param transactionId : string 事务id, 需要前台保存用于调用数据签名接口
 * @param prefix : string 文件名前缀
 */
function setConfig(workNo, data, prefix) {
    signConfig = {
        userId: workNo,
        transactionId: null,
        authKey: null,
        data: data,
        prefix: prefix
    }
}

/**
 * 获取签名
 */
function getSign(callback) {
    let eiInfo = new EiInfo();
    eiInfo.set("userId", signConfig.userId);
    EiCommunicator.send("XQMS01", "checkAuthStatusByUserId", eiInfo, {
        onSuccess: function (ei) {
            let status = ei.status;
            if (status == '0') { // 获取临时授权Key成功
                signConfig.authKey = ei.get("auth").authKEY;
                signConfig.transactionId = ei.get("auth").transactionId;
                // 3. 已授权，数据签名
                genSign(callback);
            } else {
                if (status == '-201' || status == '-202') { // 用户未授权，或已过期
                    IPLAT.confirm({
                        message: '<b>' + signConfig.userId + '：' + ei.getMsg() + '</b>',
                        okFn: function (e) {
                            // 2. 未授权，跳转授权页面
                            scanAuth(signConfig.userId, callback)
                        },
                        cancelFn: function () {
                        },
                        title: '授权'
                    });
                } else { // 提示其他调用失败的信息
                    NotificationUtil(ei.getMsg(), "error");
                    return;
                }
            }
        }
    });
}

/**
 * 生成签名
 * @param callback 回调函数
 */
function genSign(callback) {
    let signDataInfo = new EiInfo();
    signDataInfo.set("userId", signConfig.userId);
    signDataInfo.set("transactionId", signConfig.transactionId);
    signDataInfo.set("authKEY", signConfig.authKey);
    let currentDateTime = new Date().format("yyyyMMddhhmmss");
    signDataInfo.set("fileName", signConfig.prefix + currentDateTime);
    signDataInfo.set("data", signConfig.data);
    //签名
    EiCommunicator.send("XQMS02", "signData", signDataInfo, {
        onSuccess: function (ei) {
            if (ei.status == '0') {
                fileCode(ei.get("result").fileCode)
            }
        }
    });
}

/**
 * 扫码授权
 * @param workNo : string 工号
 * @param callback 回调函数
 */
function scanAuth(workNo, callback) {
    let innerInfo = new EiInfo();
    innerInfo.set("userId", workNo);
    EiCommunicator.send("XQMS01", "scanOAuth", innerInfo, {
        onSuccess: function (ei) {
            if (ei.status == '0') {
                let transactionId = ei.get("result").data.transactionId;
                let oauthWindowURL = ei.get("result").data.oauthWindowURL;
                checkWebUrl(oauthWindowURL)
                openWindows(oauthWindowURL, true, transactionId)
            }
        }
    });
}

/**
 * 检查授权
 * @param transactionId : string 事务id, 需要前台保存用于调用数据签名接口
 * @param popData 弹窗对象
 * @param callback 回调函数
 */
function checkAuth(transactionId, popDataWindow, callback) {
    let eiInfo = new EiInfo();
    eiInfo.set('transactionId', transactionId);
    //检查授权
    EiCommunicator.send("XQMS01", "checkScanStatus", eiInfo, {
        onSuccess: function (ei) {
            if (ei.status != '0') { // 调用异常，提示错误信息，同时关闭授权窗口
                NotificationUtil(ei.getMsg(), "error");
                popDataWindow.close();
            } else if (ei.get("result").data.oauthStatus == '1') { // 授权成功
                signConfig.authKey = ei.get("result").data.authKEY;
                signConfig.transactionId = transactionId;
                popDataWindow.close();
                genSign(callback)
            } else {
                console.log('current oauthStatus is' + ei.get("result").data.oauthStatus);
            }
        }
    });
}

/**
 * 检查是内网还是外网
 * @param oauthWindowURL : string 地址
 * @param back 回调函数
 */
function checkWebUrl(oauthWindowURL, back) {
    let promise = reqUrl(oauthWindowURL);
    promise.then((value) => {
        console.log(value)
        back(value)
    }, (err) => {
        console.log(err)
        back(err)
    })
}

/**
 * 校验url是否能在当前页面访问
 * @param oauthWindowURL: string 地址
 * @returns {Promise<unknown>}
 */
function reqUrl(oauthWindowURL) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: oauthWindowURL,
            type: 'GET',
            async: true,
            timeout: 2000,
            dataType: 'json',
            success: function (data) {
                console.log(data)
                resolve(true)
            },
            error:function (data) {
                console.log(data)
                resolve(false)
            },
            complete: function (XMLHttpRequest, status) {
                console.log(XMLHttpRequest)
                console.log(status)
                //请求完成后最终执行参数
                if (status == 'timeout') {
                    reject(false)
                }
            }
        });
    })
}

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function openWindows(oauthWindowURL, flag, transactionId) {
    //打开扫码授权页面
    let url = flag ? oauthWindowURL : ("https://yxq-mz.linksign.cn/h5/authwindow/index.html?t=" + transactionId);
    console.log(url)
    let popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url
            });
        },
        close: function () { // 关闭窗口，直接关闭定时轮询
            clearInterval(timer);
        }
    });
    popDataWindow.setOptions({"title": "身份授权"});
    popDataWindow.open().center();
    timer = setInterval(function () {
        checkAuth(transactionId, popDataWindow)
    }, 2000);
}