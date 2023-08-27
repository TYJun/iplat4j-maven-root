var eiInfo = new EiInfo();

//提交类型
var submitType = "insert";
var editValidator = null;
var picList = [];

//文件上传
IPLATUI.EFUpload = {
    "prPic":{
        loadComplete: function(e) {
            var uploader = e.sender.uploader;
            uploader.clearAllFiles();
        },
        validation: {
            allowedExtensions: [".jpg","jpeg",".png",".gif",".bmp"]
        },
        localization: {
            invalidFileExtension: "文件格式不支持, 上传失败"
        },
        showFileList:false,
        success: function(e) {
            var file = e.files[0];
            if(file.size>2097152){
                NotificationUtil("照片不能大于2M,请重选", "error");
                return;
            }
            if(e.operation == 'upload') {
                picList.push({"fileName":file.name,"path":e.response.docUrl,"base64":"","type":"RE","uid":file.uid});
            } else if (e.operation == 'remove') {
                for (var i = 0; i < picList.length; i++) {
                    if (picList[i].uid== file.uid) {
                        picList.splice(i, 1); i--;//i需要自减，否则每次删除都会讲原数组索引发生变化
                    }
                }
            }
            picChooseWindow.close();
            loadPic();
        },
    }
}


$(function() {
    $("#QUERY").on("click",function(e) {

        var problemCategory = IPLAT.EFSelect.value($("#inqu_status-problemCategory"));
        var problemCategoryText = IPLAT.EFSelect.text($("#inqu_status-problemCategory"));
        var problemLocation = $("#inqu_status-problemLocation").val();
        var time = $("#inqu_status-0-date").data("kendoDatePicker").value();
        if (time) {
            var rectificationTime = time.toString(Date,IPLAT.FORMAT.DATE_8_PR);//"yyyy-MM-dd"
        }
        var problemlevel = IPLAT.EFSelect.value($("#inqu_status-problemlevel"));
        var problemDesciption = $("#inqu_status-problemDesciption").val();
        var requrieDesc = $("#inqu_status-requrieDesc").val();

        //参数校验
        if("" == problemCategory || "请选择" == problemCategoryText || "" == problemLocation || null == time || "" == problemlevel || "" == problemDesciption || "" == requrieDesc){
            NotificationUtil("上报问题请填写完整信息", "error");
            return;
        }
        if(picList.length == 0){
            NotificationUtil("请上传问题图片", "error");
            return;
        }
        var myDate = new Date();
        if(time<myDate){
            NotificationUtil("要求整改时间不能小于当前日期", "error");
            return;
        }
        eiInfo.set("problemCategoryText", problemCategoryText);
        eiInfo.set("problemCategory", problemCategory);
        eiInfo.set("problemLocation", problemLocation);
        eiInfo.set("rectificationTime", rectificationTime);
        eiInfo.set("problemlevel", problemlevel);
        eiInfo.set("problemDesciption", problemDesciption);
        eiInfo.set("time", time);
        eiInfo.set("requiredesc", requrieDesc);
        eiInfo.set("picList", picList);


        EiCommunicator.send("PRGL01", "insert", eiInfo, {
            onSuccess : function(ei) {
                var status = ei.getStatus();
                var msg = ei.getMsg();
                if(-1 == status){
                    NotificationUtil(msg, "error");
                }else{
                    NotificationUtil(msg, "success");
                    //刷新当前页面
                    setTimeout(function() {
                        window.parent.location.reload()
                    }, 500);
                }
            }
        })
    });

    //上传图片
    $("#uploadPic").click(function(){
        picChooseWindow.open().center()
    });

})


//加载图片
function loadPic(){
    var inInfo = new EiInfo();
    inInfo.set("picList",picList);
    EiCommunicator.send("PRGL01", "showTempPic", inInfo, {
        onSuccess : function(ei) {
            var list = ei.get("list");
            $("#reqPic").html("")
            for (var i = 0; i < list.length; i++) {
                if (list[i].type == "RE") {
                    drawImage(list[i].base64, 1);
                }
            }
        }
    });
}