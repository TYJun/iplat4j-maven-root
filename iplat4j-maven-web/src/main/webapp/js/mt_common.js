var mtConfigs = {};
var mtConfig = {};
window.οnlοad=function(){
    loadMtConfig();
}
/*********************************************************维修流程图（开始）************************************************/

/**
 * 构建维修执行流程
 * @param id ：元素id属性值
 * @param data ： 数据
 */
function loadProcessGraph(id, taskNo) {
    var info = new EiInfo();
    info.set("taskNo", taskNo);
    EiCommunicator.send("MTCQ0101", "showProcessGraph", info, {
        onSuccess: function (ei) {
            let block = ei.getBlock("result");
            let dataJson = block.getMappedRows();
            // 绘制图像
            FnTimeline(id, dataJson);

           /* for (var j = 0; j < items.length; j++) {
                var remark = "";
                if (items[j][1] != "") {
                    remark = items[j][3] + "<br/>操作原因: " + items[j][1]
                } else {
                    remark = items[j][3];
                }
                var item = {
                    "name": items[j][0],
                    "time": items[j][4],
                    "job": items[j][5],
                    "state": 1,
                    "remark": remark,
                    "timelimit": ""
                };
                datajson.push(item);
            }*/

        }
    });
}

/**
 * 流程时间图绘制方法
 * @param id ： 元素id属性值
 * @param data : 数据
 * @constructor
 */
function FnTimeline(id, data) {
    if(data != null) {
        let datalist = [];
        let count = data.length;
        if(count > 0) {
            for(var i = 0; i < count; i++) {

                let point = ' <div class="timeline-point point-stateed">';
                let line = ' <div class="my-line line-stateed">';
                let content = ' <div class="timeline-bottom">';
                let jobhtml = ' <span>' + data[i].task + '</span>';

               /* if(data[i].state == 0) {
                    point = ' <div class="timeline-point point-stateing">';
                    line = ' <div class="my-line line-stateing">';
                    content = ' <div class="timeline-bottom linebottom-stateing">';
                    jobhtml = ' <span class="linetop-stateing">' + data[i].task + '</span>';
                }*/

                datalist.push(' <li class="time-li">');
                datalist.push(point);
                datalist.push(' <span></span>');
                datalist.push(' </div>');
                datalist.push(' <div class="timeline-content">');
                datalist.push(' <div class="timeline-top">');

                datalist.push(jobhtml);
                datalist.push('</div> ');
                datalist.push(line);
                datalist.push(' <span></span>');
                datalist.push('</div> ');
                datalist.push(content);
                datalist.push(' <div class="timeline-content-child">');
                datalist.push('<span>' + data[i].operation_name + '</span> ');
                datalist.push(' <span>' + data[i].operation_time + '</span>');
                datalist.push(' </div>');
                //维修科室
                if(data[i].task == "受理"){
                    datalist.push(' <div class="timeline-content-child">');
                    datalist.push(' <label>维修科室:</label>');
                    datalist.push(' <span>' + data[i].wgroup_name + '</span>');
                    datalist.push(' </div>');
                }
                //转单科室
                if(data[i].task == "转单"){
                    datalist.push(' <div class="timeline-content-child">');
                    datalist.push(' <label>转入科室:</label>');
                    datalist.push(' <span>' + data[i].wgroup_name + '</span>');
                    datalist.push(' </div>');
                }
                //处理备注或操作原因
                datalist.push(buildReason(data[i]));
                datalist.push('</div> ');
                datalist.push(' </div>');
                datalist.push(' </li>');

            }

        }

        var datahtml = datalist.join("");
        $("#" + id).append(datahtml);
    }

    function buildReason(data) {
        let datalist = [];
        datalist.push(' <div class="timeline-content-child">');
        let label = "备注", value = data.comments;
        switch (data.task){
            case "班组退单":
                label = "退单原因", value = data.return_reason;
                break;
            case "转单":
                label = "转单原因", value = data.return_reason;
                break;
            case "维修人员退单":
                label = "退单原因", value = data.return_reason;
                break;
            case "派工":
                label = "班组备注", value = data.return_reason;
                break;
            case "挂单":
                label = "挂单原因", value = data.return_reason;
                break;
            case "作废":
                label = "作废原因", value = data.return_reason;
                break;
            default:
                label = "备注", value = data.comments;
        }
        datalist.push(' <label>' + label + ':</label>');
        datalist.push(' <span>' + value + '</span>');
        datalist.push(' </div>');
        return datalist.join("");
    }
}

/*********************************************************维修流程图（结束）************************************************/

/*********************************************************维修图片（开始）*************************************************/

/**
 * 加载图片（报修图片、完工图片）
 * @param taskNo
 */
function loadImage(taskNo,type){
    var inInfo = new EiInfo();
    inInfo.set("taskNo", taskNo);
    inInfo.set("type", type);
    EiCommunicator.send("MTRE01", "showPic", inInfo, {
        onSuccess : function(ei) {
            var list = ei.get("list");
            if(E("#reqPic")){
                $("#reqPic").html("");
            }
            if(E("#fsPic")) {
                $("#fsPic").html("");
            }
            if (E("#qmPic")) {
                $("#qmPic").html("");
            }
            for (let i = 0; i < list.length; i++) {
                if (list[i].type == "RE") {
                    drawImage(list[i].base64, 1);
                } else if (list[i].type == "FS") {
                    drawImage(list[i].base64, 2);
                } else if (list[i].type == "QM") {
                    drawImage(list[i].base64, 3);
                }
            }
        }
    });
}

/**
 * 构建展示(图片回显)
 * @param imageBase64 ： 图片base64码
 * @param index ： 标记
 */
function drawImage(imageBase64, index) {
    if (imageBase64 == '' || imageBase64 == null || imageBase64 == 'null') {
        return;
    }
    var img = $('<img style="width:100px;height:100px" class="picMaintain">');
    img.attr('src', "data:image/jpeg;base64," + imageBase64);
    // 报修
    if (index == 1) {
        var pic = $("#reqPic");
        pic.append(img);
    }
    // 完工
    if (index == 2) {
        var pic = $("#fsPic");
        pic.append(img);
    }
    // 签字
    if (index == 3) {
        var pic = $("#qmPic");
        pic.append(img);
    }
    // 退单
    if (index == 4) {
        var pic = $("#picRefundLab");
        pic.append(img);
    }
    $(".picMaintain").click(function() {
        var _this = $(this);// 将当前的pimg元素作为_this传入函数
        imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
    });
}

/**
 * 查看大图
 * @param outerdiv
 * @param innerdiv
 * @param bigimg
 * @param _this
 */
function imgShow(outerdiv, innerdiv, bigimg, _this){
    var src = _this.attr("src");// 获取当前点击的pimg元素中的src属性
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性

    /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function(){
        var windowW = $(window).width();//获取当前窗口宽度
        var windowH = $(window).height();//获取当前窗口高度
        var realWidth = this.width;//获取图片真实宽度
        var realHeight = this.height;//获取图片真实高度
        var imgWidth, imgHeight;
        var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

        if(realHeight>windowH*scale) {//判断图片高度
            imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
            imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
            if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
                imgWidth = windowW*scale;//再对宽度进行缩放
            }
        } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
            imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
            imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
        } else {//如果图片真实高度和宽度都符合要求，高宽不变
            imgWidth = realWidth;
            imgHeight = realHeight;
        }
        $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放

        var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
        var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
    });

    $(outerdiv).click(function(){//再次点击淡出消失弹出层
        $(this).fadeOut("fast");
    });
}
/*********************************************************维修图片（结束）*************************************************/

/*********************************************************维修报修语音（开始）*************************************************/

/**
 * 加载语音
 * @param taskNo : 任务单号
 * @param playBtnId ： 播放按钮元素id
 * @param stopBtnId : 停止按钮元素id
 * @param progressCtrlId : 进度条元素id
 * @param curId : 播放进度元素id
 * @param durationId : 语音时长元素id
 */
function loadAudio (taskNo,playBtnId,stopBtnId,progressCtrlId,curId,durationId){
    var inInfo = new EiInfo();
    inInfo.set("taskNo", taskNo);
    EiCommunicator.send("MTRE01", "showAudio", inInfo, {
        onSuccess : function(ei) {
            var base64 = ei.get("base64");
            if(!isEmpty(base64)){
                createAudio(base64,playBtnId,stopBtnId,progressCtrlId,curId,durationId);
            } else {
                E('#'+playBtnId).setAttribute("disabled", true)
                E('#'+stopBtnId).setAttribute("disabled", true)
            }
        }
    });
}

/**
 * 构建语音播放
 * @param base64 ： 文件base64码
 * @param playBtnId ： 播放按钮元素id
 * @param stopBtnId : 停止按钮元素id
 * @param progressCtrlId : 进度条元素id
 * @param curId : 播放进度元素id
 * @param durationId : 语音时长元素id
 */
function createAudio(base64,playBtnId,stopBtnId,progressCtrlId,curId,durationId){
    var amr = new BenzAMRRecorder();
    var playBtn = E('#'+playBtnId);
    var stopBtn = E('#'+stopBtnId);
    var progressCtrl = E('#'+progressCtrlId);
    var cur = E('#'+curId);
    var duration = E('#'+durationId);
    //初始化
    amr.initWithBlob(base64ToBlob(base64)).then(function () {
        progressCtrl.removeAttribute('disabled');
        progressCtrl.setAttribute('max', amr.getDuration());
        duration.innerHTML = amr.getDuration().toFixed(2) + '\'';
    });

    //播放
    playBtn.onclick = function () {
        amr.playOrPauseOrResume();
        var durationNumber = parseFloat(duration.innerHTML.substring(0,duration.innerHTML.length-1));
        var timer = setInterval(function () {
            cur.innerHTML =  amr.getCurrentPosition().toFixed(2) + '\'';
            progressCtrl.value = amr.getCurrentPosition().toFixed(2);

            //停止定时任务
            var progressCtrlNumber = parseFloat(progressCtrl.value);
            if(durationNumber <= progressCtrlNumber){
                clearInterval(timer);
            }
            //console.log("zhi:", cur.innerHTML + "-----"+progressCtrl.value);
        }, 10);
    };

    //停止
    stopBtn.onclick = function () {
        amr.stop();
    };
}
/*********************************************************维修报修语音（结束）*************************************************/

/*********************************************************维修配配置项（开始）************************************************/

/**
 * 加载维修配置项
 * @param configType ： 指定配置项
 */
function loadMtConfig (configType) {
    var eiInfo = new EiInfo();
    if(isEmpty(configType)){
        queryMtConfig("getConfigAll",eiInfo,initConfig);
        return mtConfigs
    } else {
        eiInfo.set("config", configType);
        queryMtConfig("getConfig", eiInfo,initConfig);
        return mtConfig
    }
}

/**
 * 查询维修配置项
 * @param methodName ： 方法名
 * @param inInfo ： 参数对象
 * @param callback : 回调函数
 */
function queryMtConfig (methodName,inInfo,callback) {
    EiCommunicator.send("MTCF04", methodName, inInfo, {
        onSuccess : function(ei) {
            if(methodName == "getConfig"){
                mtConfig = ei.get("mtConfig");
                callback(mtConfig, inInfo.get("config"));
            } else {
                mtConfigs = ei.get("config");
                callback(mtConfigs, null);
            }
        }
    },{async: false});
}

/**
 * 初始化维修事项
 * @param config ： 配置项
 * @param methodName ： 方法名
 */
function initConfig(config, methodName) {
    if(!isEmpty(methodName)){
        invoke(methodName, config);
    } else {
        for (let key in config) {
            invoke(key, config[key]);
        }
    }
}

/**
 * 执行指定方法
 * @param methodName ： 方法名
 * @param param ： 方法参数
 */
function invoke(methodName, param) {
    //维修事项是否可以手写
    if(methodName == "handLoseItem"){
        handLoseItem(param);
    }
}


/**
 * 维修事项是否可以手写
 * @param config
 */
function handLoseItem(config){
    if(config !=null && config.configValueRedio == "Y"){
        IPLAT.EFPopupInput.readonly($("#itemName"), false);
    }
}

/******************************************************维修配配置项（结束）************************************************/

/**********************************************************通用方法*************************************************/

/**
 * 将base64字符串转换成数据流
 * @param base64 ： 文件base64字符串
 * @returns {Blob} ： 数据流
 */
function base64ToBlob(base64) {
    base64 = 'data:audio/amr;base64,' + base64
    let arr = base64.split(",");
    let mime = arr[0].match(/:(.*?);/)[1];
    let bytes = window.atob(arr[1]);
    let ab = new ArrayBuffer(bytes.length);
    let ia = new Uint8Array(ab);
    for(let i=0; i< bytes.length; i++) {
        ia[i] = bytes.charCodeAt(i);
    }
    return new Blob([ab],{type : mime});
}

/**
 * 获取javaScirpt元素对象
 * @param selector
 * @returns {*}
 * @constructor
 */
function E(selector) {
    return document.querySelector(selector);
}

/**
 * 判断字符串是否为空
 * @param parameter ： 字符串参数
 * @returns {boolean}
 */
function isEmpty(parameter){
    if(parameter == undefined || parameter == null){
        return true;
    } else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
        return true;
    } else {
        return false;
    }
}

/**
 * 判断元素是否存在
 * @param elementId
 * @returns {boolean}
 */
function isExistElement(elementId) {
    return $("#" + elementId).length>0 ? true : false;
}



