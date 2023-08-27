var uuid = "";
var fileArrayList = [];
var fileList = [];
var workPicList = [];
var rowNo = 0;
$(function () {
    $("#CONFIRM").on("click", function (e) {
        var btnNode = $(this);
        btnNode.attr("disabled", true)
        var eiInfo = new EiInfo();
        var rows = resultGrid.getDataItems();
        var fileRows = resultFileGrid.getDataItems();
        var cardId = $("#cardId").val();
        eiInfo.setByNode("result");
        eiInfo.set("type","PC");
        eiInfo.set("cardId", cardId);
        if (rows.length > 0){
            eiInfo.set("rows",rows);
        } else {
            IPLAT.alert("请添加访客")
            btnNode.attr("disabled", false);
            return
        }
        for (let i = 0; i < rows.length; i++) {
            if (rows[i].guestName == undefined){
                IPLAT.alert("第"+(i+1)+"个访客，"+"请填写访客姓名")
                btnNode.attr("disabled", false);
                return
            }
            if(isEmpty(rows[i].cardId)){
                IPLAT.alert("第"+(i+1)+"个访客，"+"请填写访客身份证号码")
                btnNode.attr("disabled", false);
                return
            }else if(isCardNo(rows[i].cardId)){
                IPLAT.alert("第"+(i+1)+"个访客，"+"填写的访客身份证号码格式不正确")
                btnNode.attr("disabled", false);
                return
            }
        }
        eiInfo.set("fileList",fileList);
        eiInfo.set("workPicList",fileRows);
        EiCommunicator.send("VIDJ0101", "confirm", eiInfo, {
            onSuccess : function(ei) {
                btnNode.attr("disabled", false);
                NotificationUtil("新增成功", "success");
                window.parent['popDataWindow'].close();
            },
            onFail:function (ei){
                btnNode.attr("disabled", false);
                NotificationUtil("新增失败", "warning");
            }
        });
    })

    $("#CANCEL").on("click", function (e) {
        window.parent['popDataWindow'].close();
    })
});

IPLATUI.EFGrid = {
    "result": {
        pageable: false,
        exportGrid : false,
        columns: [
            {
                field: "fileId",
                title: "操作",
                enable: false,
                template: "<center><button class='custom i-btn-lg' id='openPicChooseWindow'>访客照片</button></center>"
            },
            {
                field: "uplaodPic",
                title: "上传照片",
                enable: false,
                template: "<span class='uploadPic'></span>"
            },
        ],
        onCellClick: function (e) {
            rowNo = e.row;
            var visitId = e.model.visitId;
            var span = document.getElementsByClassName("uploadPic")[rowNo]
            var spanId = "reqPic" + visitId;
            span.setAttribute("id", spanId)
            if (e.field === 'fileId' && !(e.event.clientX == 0 && e.event.clientY == 0)) {
                for (let i = 0; i < fileList.length; i++) {
                    if (fileList[i].visitId == visitId) {
                        if (i >= 9){
                            IPLAT.alert("一个访客只能上传九张照片");
                            return
                        }
                    }
                }
                picChooseWindow.open().center();
            }
        },
        toolbarConfig: {
            hidden: false,
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [{
                name: "addRows", text: "新增", layout: "3",
                click: function () {
                    uuid = createUUID();
                    var model = createModel(uuid);
                    model.uploadTime = getCurrentTime()
                    resultGrid.addRows(model);
                    // 通过for循环来重新设置每个span的id
                    var rows = resultGrid.getDataItems();
                    for (let j = 0; j < rows.length; j++) {
                        var span = document.getElementsByClassName("uploadPic")[j]
                        var spanId = "reqPic" + rows[j].visitId;
                        span.setAttribute("id", spanId)
                    }
                    loadPicTemp(fileList)
                }
            }, {
                name: "deleteRows", text: "删除", layout: "3",
                click: function () {
                    var rows = resultGrid.getCheckedRows();
                    var visitId = rows[0].visitId;
                    for (let i = 0; i < fileList.length; i++) {
                        if (fileList[i].visitId == visitId) {
                            var eiInfo = new EiInfo();
                            var docId = fileList[i].docId;
                            eiInfo.set("docId", docId);
                            EiCommunicator.send("VIDJ0101", "removePicByUid", eiInfo, {
                                onSuccess : function(ei) {
                                    // NotificationUtil(ei.getMsg(), "success");
                                }
                            });
                        }
                        if (fileList[i].visitId == visitId) {
                            fileList.splice(i, 1);
                            i--;//i需要自减，否则每次删除都会讲原数组索引发生变化
                        }
                    }
                    resultGrid.removeRows(rows);
                    var rows = resultGrid.getDataItems();
                    for (let j = 0; j < rows.length; j++) {
                        var span = document.getElementsByClassName("uploadPic")[j]
                        var spanId = "reqPic" + rows[j].visitId;
                        span.setAttribute("id", spanId)
                    }
                    loadPicTemp(fileList)
                }
            }]
        },
        loadComplete: function (grid) {

        },
    },
    "resultFile": {
        pageable: false,
        toolbarConfig: {
            hidden: false,
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [{
                name: "upload", text: "上传", layout: "3",
                click: function () {
                    fileChooseWindow.open().center()
                    $("#viPic").removeAttr("multiple")
                }
            },{
                name: "deleteFile", text: "删除", layout: "3",
                click: function () {
                    var rows = resultFileGrid.getCheckedRows();
                    var docId = rows[0].docId;
                    for (let i = 0; i < workPicList.length; i++) {
                        if (workPicList[i].docId == docId){
                            workPicList.splice(i, 1);
                            i--;//i需要自减，否则每次删除都会讲原数组索引发生变化
                        }
                    }
                    resultFileGrid.removeRows(rows);
            }}]
        }
    }
}

/* 附件上传 */
IPLATUI.EFUpload = {
    "viPic": {
        showFileList: false,
        loadComplete: function (e) {
            $("#viPic").removeAttr("multiple")
            let uploader = e.sender.uploader;
            uploader.clearAllFiles();
        },
        validation: {
            allowedExtensions: [".jpg", "jpeg", ".png", ".gif", ".bmp"]
        },
        localization: {
            invalidFileExtension: "文件格式不支持, 上传失败"
        },
        upload:function (e){
            var file = e.files[0].rawFile;
        },
        success: function (e) {
            $("#viPic").removeAttr("multiple")
            // console.log(e)
            var rows = resultGrid.getCheckedRows();
            let file = e.files[0];
            // console.log(file.name)
            if (e.operation == 'upload') {
                fileList.push({
                    "fileName": file.name,
                    "docId": e.response.docId,
                    "fileContent": "",
                    "uid": file.uid,
                    "visitId": rows[0].visitId,
                    "fileSize": file.size,
                    "mimeType": file.rawFile.type,
                });
                // console.log(fileList)
            } else if (e.operation == 'remove') {
                for (let i = 0; i < fileList.length; i++) {
                    if (fileList[i].uid == file.uid) {
                        fileList.splice(i, 1);
                        i--;//i需要自减，否则每次删除都会讲原数组索引发生变化
                    }
                }
            }
            picChooseWindow.close();
            loadPicTemp(fileList)
        },
    },
    "viFile": {
        showFileList: false,
        loadComplete: function (e) {
            let uploader = e.sender.uploader;
            uploader.clearAllFiles();
        },
        validation: {
            allowedExtensions: [".jpg", "jpeg", ".png", ".gif", ".bmp",
                ".txt", ".doc", "docx", ".xls", ".xlsx", ".ppt",
                ".pptx", ".pdf", ".zip", "rar", ".7z"]
        },
        localization: {
            invalidFileExtension: "文件格式不支持, 上传失败"
        },
        success: function (e) {
            // console.log(e)
            var file = e.files[0];
            var model = createModelToWorkPic(createUUID());
            var fileSize = (file.size / (1024 * 1024)).toFixed(2);
            if (fileSize == 0.00){
                fileSize = 0.01
            }
            model["id"] = "";
            model["docId"] = e.response.docId;
            model["fileContent"] = e.response.docUrl;
            model["fileName"] = file.name;
            model["mimeType"] = file.extension;
            model["fileSize"] = fileSize;
            model["uploadTime"] = getCurrentTime();
            resultFileGrid.addRows(model);
            fileChooseWindow.close();
        },
    }
}

/**
 * 加载临时图片
 */
function loadPicTemp(fileList){
    let inInfo = new EiInfo();
    inInfo.set("picList",fileList);
    EiCommunicator.send("VIDJ0101", "showTempPic", inInfo, {
        onSuccess : function(ei) {
            // console.log(ei)
            let list = ei.get("list");
            var rows = resultGrid.getDataItems();
            for (let i = 0; i < rows.length; i++) {
                var spanId = "#reqPic" + rows[i].visitId;
                $(spanId).html("")
            }
            if (list == undefined){
                return;
            } else {
                for (let i = 0; i < list.length; i++) {
                    // 不保存base64
                    // for (let j = 0; j < fileList.length; j++) {
                    //     if (fileList[j].visitId == list[i].visitId){
                    //         fileList[j].fileContent = list[i].base64
                    //     }
                    // }
                    // var spanId = "#reqPic" + list[i].visitId;
                    // $(spanId).html("")
                    drawImage(list[i].base64,list[i].visitId);
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
function drawImage(imageBase64,visitId) {
    if (imageBase64 == '' || imageBase64 == null || imageBase64 == 'null') {
        return;
    }
    let img = $('<img style="width:100px;height:100px;" class="picMaintain">');
    img.attr('src', "data:image/jpeg;base64," + imageBase64);
    // 报修
    var spanId = "#reqPic" + visitId;
    let pic = $(spanId);
    pic.append(img);
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

// 创建kendo.data.Model（工作证）
function createModelToWorkPic(id){
    var gridRow = kendo.data.Model.define({
        id: "docId",
        fields: {
            "id": {
                type: "string"
            },
            "docId": {
                type: "string"
            },
            "fileContent": {
                type: "string"
            },
            "fileName": {
                type: "string"
            },
            "fileDesc":{
                type: "string"
            },
            "fileSize": {
                type: "string"
            },
            "mimeType": {
                type: "string"
            },
            "uploadTime": {
                type: "string"
            },
        }
    });
    var model = new gridRow({docId: id});
    return model;
}

// 创建kendo.data.Model（访客）
function createModel(id) {
    var gridRow = kendo.data.Model.define({
        id: "visitId",
        fields: {
            "id": {
                type: "string"
            },
            "guestName": {
                type: "string"
            },
            "cardId":{
                type:"string"
            },
            "guestPhone": {
                type: "string"
            },
            "vehicleTypeCode": {
                type: "string"
            },
            "vehicleNumber": {
                type: "string"
            },
            "guestOrgName": {
                type: "string"
            },
            "ntervieweeContent": {
                type: "string"
            },
            "fileId": {
                type: "string"
            },
            "uploadTime": {
                type: "string"
            },

        }
    });
    var model = new gridRow({visitId: id});
    return model;
}

// 生成UUID
function createUUID() {
    var d = new Date().getTime();
    if (window.performance && typeof window.performance.now === "function") {
        d += performance.now();
    }
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = (d + Math.random() * 16) % 16 | 0;
        d = Math.floor(d / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return uuid;
}

/**
 * 判断是否为空
 * @param parameter
 * @returns {boolean}
 */
function isEmpty(parameter){
    if(parameter == undefined || parameter == null){
        return true;
    } // 除去参数俩端的空格
    else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
        return true;
    } else {
        return false;
    }
}

/**
 * 检验身份证号码 true不合规范
 * @param parameter
 * @returns {boolean}
 */
function isCardNo(parameter){
    // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    return !reg.test(parameter);
}

function getExtension (name) {
    return name.substring(name.lastIndexOf(".")+1)
}

const currentDate = new Date();

const year = currentDate.getFullYear();
const month = String(currentDate.getMonth() + 1).padStart(2, '0');
const date = String(currentDate.getDate()).padStart(2, '0');
const hours = String(currentDate.getHours()).padStart(2, '0');

const formattedDate = `${year}-${month}-${date} ${hours}:00:00`;

IPLATUI.EFDatePicker = {
    "inqu_status-0-vistingBeginDate":{
        min : formattedDate
    }
}

// 获取当前时间的函数
function getCurrentTime() {
    var date = new Date(); // 创建一个新的日期对象
    date.getFullYear();
    var year = date.getFullYear(); // 获取年份部分
    var month = (date.getMonth() + 1).toString().padStart(2, '0'); // 获取月份部分，并补齐为两位数
    var day = date.getDate().toString().padStart(2, '0'); // 获取日期部分，并补齐为两位数
    var hours = date.getHours(); // 获取小时部分
    var minutes = date.getMinutes(); // 获取分钟部分
    var seconds = date.getSeconds(); // 获取秒部分

    // 格式化时间字符串
    var timeString =  year + '-' + month + '-' + day + ' ' +
        hours.toString().padStart(2, '0') + ':' +
        minutes.toString().padStart(2, '0') + ':' +
        seconds.toString().padStart(2, '0');

    return timeString; // 返回格式化后的时间字符串
}