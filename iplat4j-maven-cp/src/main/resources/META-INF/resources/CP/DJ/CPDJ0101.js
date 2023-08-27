var fileList=[];

$(function() {
    //开启校验
    let validator = IPLAT.Validator({id : "result"});
    console.log(__ei.type)
    if ("deal" === __ei.type){
        $("#deal").show();
        $("#inqu_status-0-complaintDate").attr("disabled",true)
        $("#inqu_status-0-complaintName").attr("disabled",true)
        $("#inqu_status-0-complaintPhone").attr("disabled",true)
        $("#inqu_status-0-complaintEmail").attr("disabled",true)
        $("#inqu_status-0-complaintDept").attr("disabled",true)
        $("#inqu_status-0-complaintContent").attr("disabled",true)
        $("#complaintContent").attr("disabled",true)
        $("#uploadPicSpan").hide()
    } else if ("see" === __ei.type){
        $("#inqu_status-0-complaintDate").attr("disabled",true)
        $("#inqu_status-0-complaintName").attr("disabled",true)
        $("#inqu_status-0-complaintPhone").attr("disabled",true)
        $("#inqu_status-0-complaintEmail").attr("disabled",true)
        $("#inqu_status-0-complaintDept").attr("disabled",true)
        $("#inqu_status-0-complaintContent").attr("disabled",true)
        $("#complaintContent").attr("disabled",true)
        $("#uploadPicSpan").hide()
        $("#SAVEPRSPAN").hide()
    } else if (undefined == __ei.type){
        IPLAT.EFInput.value($("#inqu_status-0-type"),"OA");
        $("#type").val("OA");
    }
    if("add" != __ei.type){
        loadImage(__ei.billNo)
    }

    /*附件上传*/
    IPLATUI.EFUpload = {
        "tsPic": {
            showFileList:false,
            loadComplete: function (e) {
                var picList = __ei.list1
                let uploader = e.sender.uploader;
                uploader.clearAllFiles();
                if (picList != undefined){
                    for (let i = 0; i < picList.length; i++) {
                        var item = picList[i].fileId;
                        var files = uploader.options.files
                        // var showList=[];
                        // showList.push({
                        //     "billNo": picList[i].billNo,
                        //     "fileName": picList[i].fileName,
                        //     "docId": picList[i].fileId,
                        //     "base64": picList[i].base64,
                        //     "uid": picList[i].id
                        // })
                    }
                }
            },
            validation: {
                allowedExtensions: [".jpg", "jpeg", ".png", ".gif", ".bmp"]
            },
            localization: {
                invalidFileExtension: "文件格式不支持, 上传失败"
            },
            success: function (e) {
                console.log(e)
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

    // 完结
    $("#COMPLETED").on("click",function (){
        var billNo = $("#inqu_status-0-billNo").val();
        var deptName = IPLAT.EFSelect.text($("#inqu_status-0-deptName"));
        var deptNum = IPLAT.EFSelect.value($("#inqu_status-0-deptName"));
        var eiInfo = new EiInfo();
        eiInfo.set("billNo", billNo);
        eiInfo.set("deptNum", deptNum);
        eiInfo.set("deptName", deptName);
        // 完结
        EiCommunicator.send("CPDJ0101", "completed", eiInfo, {
            onSuccess: function (ei) {
                closeCurrentWindow();
                IPLAT.NotificationUtil(ei.msg)
                window.parent["resultGrid"].dataSource.page(1);
            }
        })
    })

    //弹窗保存
    $("#SAVEPR").on("click", function() {
        if (validator.validate()) {
            if ($("#inqu_status-0-complaintDate").val() == ""){
                IPLAT.NotificationUtil("发生日期不能为空", "error")
                return;
            }
            if ($("#inqu_status-0-complaintName").val() == ""){
                IPLAT.NotificationUtil("发起人不能为空", "error")
                return;
            }
            if ($("#inqu_status-0-complaintPhone").val() == ""){
                IPLAT.NotificationUtil("发起人电话不能为空", "error")
                return;
            }
            var complaintContent = IPLAT.EFInput.value($("#complaintContent"));
            IPLAT.EFInput.value($("#inqu_status-0-complaintContent"),complaintContent);
            if ($("#inqu_status-0-complaintContent").val() == ""){
                IPLAT.NotificationUtil("发起内容不能为空", "error")
                return;
            }
            //获取参数
            let eiInfo = new EiInfo();
            var type = IPLAT.EFInput.value($("#inqu_status-0-type"));
            if (type == "deal"){
                var deptName = IPLAT.EFSelect.text($("#inqu_status-0-deptName"));
                var deptNum = IPLAT.EFSelect.value($("#inqu_status-0-deptName"));
                // alert("deptName"+deptName)
                if (deptNum == ""){
                    IPLAT.alert("请选择处理部门/单位","error");
                    return
                }
            }
            var recCreator = IPLAT.EFInput.value($("#inqu_status-0-recCreator"));
            eiInfo.setByNode("result");
            eiInfo.set("deptName", deptName);
            eiInfo.set("deptNum", deptNum);
            eiInfo.set("recCreator", recCreator);
            eiInfo.set("type", type);
            //获取tab数据
            eiInfo.set("picList", fileList);
            //提交
            EiCommunicator.send("CPDJ0101", "insert", eiInfo, {
                onSuccess: function (ei) {
                    console.log(ei)
                    if (ei.extAttr.type == "OA"){
                        IPLAT.NotificationUtil(ei.msg)
                        IPLAT.alert(
                            "如要查询您的处理进度，请去'系统链接'——'系统一体化'——'职工心声模块'查看",
                            function (e) {
                                closeCurrentWindowNew()
                            },
                            "提交成功",
                            300
                        );
                    } else {
                        closeCurrentWindow();
                        IPLAT.NotificationUtil(ei.msg)
                        window.parent["resultGrid"].dataSource.page(1);
                    }
                }
            })
        }
    });

    //取消
    $("#CANCEL").on("click", function() {
        var type = $("#type").val();
        if (type == "OA"){
            closeCurrentWindowNew();
        } else {
            closeCurrentWindow();
        }
    });

    /**
     * 打开上传图片弹出框
     */
    $("#uploadPic").on("click",function(){
        picChooseWindow.open().center()
    });

    $("#cleanPic").on("click",function (){
        let inInfo = new EiInfo();
        var billNo = $("#inqu_status-0-billNo").val();
        inInfo.set("billNo",billNo);
        EiCommunicator.send("CPDJ0101", "cleanPic", inInfo, {
            onSuccess : function(ei) {
                fileList=[]
                $("#reqPic").html("")
                drawImage("")
            }
        });
    })

})

//关闭窗口
function closeCurrentWindow() {
    window.parent['popDataWindow'].close();
}

function closeCurrentWindowNew() {
    window.close();
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
    let img = $('<img style="width:100px;height:100px;" class="picMaintain">');
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

IPLATUI.EFDatePicker = {
    "inqu_status-0-complaintDate": {
        max: new Date() // 设置最小日期
    }
    // "inqu_status-0-m2": {
    //     max: new Date("2018-07-23") // 设置最大日期
    // }
};