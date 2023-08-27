var fileList=[];

$(function() {
    //开启校验
    let validator = IPLAT.Validator({id : "result"});
    if("add" != __ei.type){
        loadImage(__ei.billNo)
    }

    /*附件上传*/
    IPLATUI.EFUpload = {
        "tsPic": {
            loadComplete: function (e) {
                let uploader = e.sender.uploader;
                uploader.clearAllFiles();
            },
            validation: {
                allowedExtensions: [".jpg", "jpeg", ".png", ".gif", ".bmp"]
            },
            localization: {
                invalidFileExtension: "文件格式不支持, 上传失败"
            },
            success: function (e) {
                let file = e.files[0];
                if (e.operation == 'upload') {
                    fileList.push({
                        "billNo":IPLAT.EFInput.value($("#inqu_status-0-billNo")),
                        "fileName": file.name,
                        "docId": e.response.docId,
                        "base64": "",
                        "uid": file.uid
                    });
                } else if (e.operation == 'remove') {
                    for (let i = 0; i < fileList.length; i++) {
                        if (fileList[i].uid == file.uid) {
                            fileList.splice(i, 1);
                            i--;//i需要自减，否则每次删除都会讲原数组索引发生变化
                        }
                    }
                }
                picChooseWindow.close();
                loadPic();
            },
        }
    }

    //弹窗保存
    $("#SAVEPR").on("click", function() {
        if (validator.validate()) {
            //获取参数
            let eiInfo = new EiInfo();
            eiInfo.setByNode("result");

            //获取tab数据
            eiInfo.set("picList", fileList);
            //提交
            EiCommunicator.send("CPDJ0101", "updateCLKS", eiInfo, {
                onSuccess: function (ei) {
                    closeCurrentWindow();
                    IPLAT.NotificationUtil(ei.msg)
                    window.parent["resultGrid"].dataSource.page(1);
                }
            })
        }
    });

    //取消
    $("#CANCEL").on("click", function() {
        closeCurrentWindow();
    });

    /**
     * 打开上传图片弹出框
     */
    $("#uploadPic").on("click",function(){
        picChooseWindow.open().center()
    });


})

    //关闭窗口
function closeCurrentWindow() {
    window.parent['popDataWindow'].close();
}

//创建kendo.data.Model
function createModel(id){
    let gridRow = kendo.data.Model.define({
        id: "uploadId",
        fields: {
            "id": {type:"string"},
            "base64": {type: "string"},
            "docId": {type: "string"},
            "fileName": {type: "string"},
            "uid": {type: "string"},
        }
    });
    return new gridRow({uploadId:id});
}



    //参数校验
function validatePR(eiInfo) {
    if (isEmpty(eiInfo.get("inqu_status-0-complaintDate"))) {
        IPLAT.NotificationUtil("发生日期不能为空");
        return false;
    }
    if (isEmpty(eiInfo.get("inqu_status-0-complaintName"))) {
        IPLAT.NotificationUtil("投诉人不能为空");
        return false;
    }
    if (isEmpty(eiInfo.get("inqu_status-0-complaintPhone"))) {
        IPLAT.NotificationUtil("电话不能为空");
        return false;
    }
    if (isEmpty(eiInfo.get("inqu_status-0-complaintType"))) {
        IPLAT.NotificationUtil("投诉类别不能为空");
        return false;
    }

    if (isEmpty(eiInfo.get("inqu_status-0-complaintWay"))) {
        IPLAT.NotificationUtil("投诉方式不能为空");
        return false;
    }

    if (isEmpty(eiInfo.get("inqu_status-0-complaintContent"))) {
        IPLAT.NotificationUtil("投诉内容不能为空");
        return false;
    }
    return true;
}


/**
 * 加载投诉图片
 */
function loadPic(){
    let inInfo = new EiInfo();
    inInfo.set("picList",fileList);
    EiCommunicator.send("CPDJ0101", "showTempPic", inInfo, {
        onSuccess : function(ei) {
            let list = ei.get("list");
            $("#reqPic").html("")
            for (let i = 0; i < list.length; i++) {
                drawImage(list[i].base64);
            }
        }
    });
}
/**
 * 加载图片（投诉图片）
 * @param billNo
 */
function loadImage(billNo){
    var inInfo = new EiInfo();
    inInfo.set("billNo", billNo);
    EiCommunicator.send("CPDJ01", "showPic", inInfo, {
        onSuccess : function(ei) {
            let list = ei.get("list");
            $("#reqPic").html("");
            for (let i = 0; i < list.length; i++) {
                drawImage(list[i].base64);
            }
        }
    });
}
/**
 * 构建展示(图片回显)
 * @param imageBase64 ： 图片base64码
 * @param index ： 标记
 */
function drawImage(imageBase64) {
    if (imageBase64 == '' || imageBase64 == null || imageBase64 == 'null') {
        return;
    }
    let img = $('<img style="width:100px;height:100px" class="picMaintain">');
    img.attr('src', "data:image/jpeg;base64," + imageBase64);
    // 报修
    let pic = $("#reqPic");
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
