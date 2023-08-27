/*********************************************************维修流程图（开始）************************************************/

/**
 * 构建维修执行流程
 * @param id ：元素id属性值
 * @param data ： 数据
 */
function loadProcessGraph(id, taskNo) {
    let info = new EiInfo();
    info.set("taskNo", taskNo);
    EiCommunicator.send("MTCQ0101", "showProcessGraph", info, {
        onSuccess: function (ei) {
            let block = ei.getBlock("result");
            let dataJson = block.getMappedRows();
            // 绘制图像
            FnTimeline(id, dataJson);
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
    if (data != null) {
        let datalist = [];
        let count = data.length;
        if (count > 0) {
            for (let i = 0; i < count; i++) {
                datalist.push(' <li class="time-li">');
                datalist.push(' <div class="timeline-point point-stateed">');
                datalist.push(' <span></span>');
                datalist.push(' </div>');
                datalist.push(' <div class="timeline-content">');
                datalist.push(' <div class="timeline-top">');

                datalist.push(' <span>' + data[i].task + '</span>');
                datalist.push('</div> ');
                datalist.push(' <div class="my-line line-stateed">');
                datalist.push(' <span></span>');
                datalist.push('</div> ');
                datalist.push(' <div class="timeline-bottom">');
                datalist.push(' <div class="timeline-content-child">');
                datalist.push('<span>' + data[i].operation_name + '</span> ');
                datalist.push(' <span>' + data[i].operation_time + '</span>');
                datalist.push(' </div>');
                //维修科室
                if (data[i].task == "受理") {
                    datalist.push(' <div class="timeline-content-child">');
                    datalist.push(' <label>维修科室:</label>');
                    datalist.push(' <span>' + data[i].wgroup_name + '</span>');
                    datalist.push(' </div>');
                }
                //转单科室
                if (data[i].task == "转单") {
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
        let dataHtml = datalist.join("");
        $("#" + id).append(dataHtml);
    }
}

/**
 * 处理操作原因
 * @param data
 * @returns {string}
 */
function buildReason(data) {
    let datalist = [];
    datalist.push(' <div class="timeline-content-child">');
    let label, value;
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

/*********************************************************维修流程图（结束）************************************************/

/*********************************************************维修图片（开始）*************************************************/

/**
 * 加载图片（报修图片、完工图片）
 * @param taskNo
 */
function loadImage(taskNo,type){
    let inInfo = new EiInfo();
    inInfo.set("taskNo", taskNo);
    inInfo.set("type", type);
    EiCommunicator.send("MTRE01", "showPic", inInfo, {
        onSuccess : function(ei) {
            let list = ei.get("list");
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
    let img = $('<img style="width:100px;height:100px" class="picMaintain">');
    img.attr('src', "data:image/jpeg;base64," + imageBase64);
    switch (index) {
        // 报修
        case 1: $("#reqPic").append(img); break;
        // 完工
        case 2: $("#fsPic").append(img); break;
        // 签字
        case 3: $("#qmPic").append(img); break;
        // 退单
        case 4: $("#picRefundLab").append(img); break;
    }

    $(".picMaintain").click(function() {
        let _this = $(this);// 将当前的pimg元素作为_this传入函数
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
    let src = _this.attr("src");// 获取当前点击的pimg元素中的src属性
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性

    /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function(){
        let windowW = $(window).width();//获取当前窗口宽度
        let windowH = $(window).height();//获取当前窗口高度
        let realWidth = this.width;//获取图片真实宽度
        let realHeight = this.height;//获取图片真实高度
        let imgWidth, imgHeight;
        let scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

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

        let w = (windowW-imgWidth)/2;//计算图片与窗口左边距
        let h = (windowH-imgHeight)/2;//计算图片与窗口上边距
        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
    });

    $(outerdiv).click(function(){//再次点击淡出消失弹出层
        $(this).fadeOut("fast");
    });
}

/**
 * 返回上传图片对象
 * @param picList : *[] 图片数组
 * @param index : 1=报修图片,2=完工图片, 3=签字图片
 */
function imgUpload(picList, index) {
    return {
        loadComplete: function(e) {
            let uploader = e.sender.uploader;
            uploader.clearAllFiles();
        },
        validation: {
            allowedExtensions: [".jpg","jpeg",".png",".gif",".bmp"]
        },
        localization: {
            invalidFileExtension: "文件格式不支持, 上传失败"
        },
        success: function(e) {
            let file = e.files[0];
            if(e.operation == 'upload') {
                picList.push({"fileName":file.name,"docId":e.response.docId,"base64":"","type":"RE","uid":file.uid});
            } else if (e.operation == 'remove') {
                for (let i = 0; i < picList.length; i++) {
                    if (picList[i].uid== file.uid) {
                        picList.splice(i, 1); i--;//i需要自减，否则每次删除都会讲原数组索引发生变化
                    }
                }
            }
            window["picChooseWindow"].close();
            //回显上传的图片
            loadTempImg(picList, index);
        },
    }
}

/**
 * 回显上传临时文件
 * @param picList
 */
function loadTempImg(picList, index) {
    let inInfo = new EiInfo();
    inInfo.set("picList",picList);
    EiCommunicator.send("MTRE0101", "showTempPic", inInfo, {
        onSuccess : function(ei) {
            let list = ei.get("list");
            //清空元素
            switch (index) {
                // 报修
                case 1: $("#reqPic").html(""); break;
                // 完工
                case 2: $("#fsPic").html(""); break;
            }
            //重新赋值元素
            for (let i = 0; i < list.length; i++) {
                drawImage(list[i].base64, index);
            }
        }
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
    let inInfo = new EiInfo();
    inInfo.set("taskNo", taskNo);
    EiCommunicator.send("MTRE01", "showAudio", inInfo, {
        onSuccess : function(ei) {
            let base64 = ei.get("base64");
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
    let amr = new BenzAMRRecorder();
    let playBtn = E('#'+playBtnId);
    let stopBtn = E('#'+stopBtnId);
    let progressCtrl = E('#'+progressCtrlId);
    let cur = E('#'+curId);
    let duration = E('#'+durationId);
    //初始化
    amr.initWithBlob(base64ToBlob(base64)).then(function () {
        progressCtrl.removeAttribute('disabled');
        progressCtrl.setAttribute('max', amr.getDuration());
        duration.innerHTML = amr.getDuration().toFixed(2) + '\'';
    });

    //播放
    playBtn.onclick = function () {
        amr.playOrPauseOrResume();
        let durationNumber = parseFloat(duration.innerHTML.substring(0,duration.innerHTML.length-1));
        let timer = setInterval(function () {
            cur.innerHTML =  amr.getCurrentPosition().toFixed(2) + '\'';
            progressCtrl.value = amr.getCurrentPosition().toFixed(2);

            //停止定时任务
            let progressCtrlNumber = parseFloat(progressCtrl.value);
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
/*********************************************************维修报修语音（结束）*************************************************/

/***************************************************kendoAutoComplete自动补全（开始）******************************************/

/**
 * kendoAutoComplete的dataSource配置
 * @param url : 请求接口路径
 * @param blockId : 返回EiInfo的EiBlockId
 * @param selector : *[] 选择器ID数组
 * @param params : *[] 参数名称数组
 * @returns {*}
 */
function dataSourceConfig(url,blockId, selector=[], params=[]){
    return new kendo.data.DataSource({
        serverFiltering: true,//每次输入重新获取数据; false:初始获取数据后,就不再重新获取
        transport: {
            read: {
                url:IPLATUI.CONTEXT_PATH + url,
                type: 'POST',
                dataType: "json",
                contentType: "application/json"
            },
            parameterMap: function (options, operation) {
                let info = new EiInfo();
                if(selector.length > 0){
                    for(let index in selector){
                        info.set(params[index] === undefined ? selector[index] : params[index], $("#"+selector[index]).val());
                    }
                }
                return  info.toJSONString();
            }
        },
        schema: {
            data: function (response) {
                ajaxEi = EiInfo.parseJSONObject(response);
                let block = ajaxEi.getBlock(blockId);
                let array = new Array();
                for(let index in  block.getRows()){
                    array.push(block.getMappedObject( block.getRows()[index]));
                }
                return array;
            }
        }
    })
}

/**
 * kendoAutoComplete查询所有数据
 * @param selector
 */
function showAll(selector){
    let autocomplete = $("#"+selector).data("kendoAutoComplete");
    autocomplete.search("");
}

/**
 * 回显指定下拉选项数据
 * @param selector : string  ID选择去
 * @param fieldName : string 属性名称
 */
function echoOne(selector, fieldName){
    let autocomplete = $("#"+selector).data("kendoAutoComplete");
    let options = autocomplete.options;
    let rows = options.dataSource._data;
    let value = autocomplete.value();
    if(rows !=null && rows.length > 0 && value.length > 0){
        let index = -1;
        for (let i=0; i<rows.length; i++) {
            if(rows[i][fieldName] == value){ index = i;}
        }
        if(index > -1){
            autocomplete.select(index);
            let dataItem = autocomplete.dataItem(index);
            echoSelect(selector, dataItem);
        }
    }
}

/**
 * 回显指定数据
 * @param selector
 * @param dataItem
 */
function echoSelect(selector, dataItem) {
    if(selector == "reqStaffId"){
        $("#reqStaffName").val(dataItem.name);		/* 报修人*/
        $("#reqDeptNum").val(dataItem.deptNum);		/* 报修科室*/
        $("#reqDeptName").val(dataItem.deptName);		/* 报修科室*/
    } else if (selector == "reqTelNum") {
        $("#reqDeptNum").val(dataItem.deptNum);		/* 报修科室*/
        $("#reqDeptName").val(dataItem.deptName);		/* 报修科室*/
        $("#building").val(dataItem.building);
        $("#floor").val(dataItem.floor);
    }
}

/***************************************************kendoAutoComplete自动补全（结束）******************************************/

/**********************************************************通用方法*************************************************/



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

/**
 * 电话校验
 * @param mobile
 * @returns {boolean}
 */
function checkPhone(mobile) {
    let tel = /^([0-9]{3,4}-?\s?)?[0-9]{4,8}$/;
    let phone = /^((\+?86)|(\(\+86\)))?(1[3-9][0-9]{9})$/;
    if(tel.test(mobile) || phone.test(mobile) ){//手机号码
        return true;
    }
    return false;
}


