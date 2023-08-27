var eiInfo = new EiInfo();
var picList = [];
//调用图片加载方法
loadPic();

//文件上传
IPLATUI.EFUpload = {
    "saftyPic":{
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
            afterloadPic();
        },
    }
}


$(function() {
    //上传图片
    $("#uploadPic").click(function(){
        picChooseWindow.open().center()
    });

    //保存点击事件
    $(function() {
        $("#PIC").on("click",function(e) {
            var id = $("#id").val();
            var localtypecode = $("#localtypecode").val();
            var localTypeName = $("#localTypeName").val();
            var writeman = $("#writeman").val();
            var time = $("#finishtime").data("kendoDatePicker").value();
            var contentdesc = $("#contentdesc2").val();


            if(null == picList || null == writeman || null == time || null == contentdesc){
                NotificationUtil("整改问题请填写完整信息", "error");
                return;
            }

            eiInfo.set("id", id);
            eiInfo.set("localtypecode", localtypecode);
            eiInfo.set("localTypeName", localTypeName);
            eiInfo.set("writeman", writeman);
            eiInfo.set("time", time);
            eiInfo.set("contentdesc", contentdesc);
            eiInfo.set("picList", picList);

            EiCommunicator.send("PRGL0201", "insert", eiInfo, {
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
        })
    })
})

//加载整改前图片
function loadPic(){
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    var id = vars[1].split("=")[1];
    var inInfo = new EiInfo();
    inInfo.set("id",id);
    EiCommunicator.send("PRGL0201", "showTempPic", inInfo, {
        onSuccess : function(ei) {
            var list = ei.get("list");
            $("#reqPic").html("")
            for (var i = 0; i < list.length; i++) {
                drawImage(list[i],1);
            }
        }
    });
}


//加载整改后图片
function afterloadPic(){
    var inInfo = new EiInfo();
    inInfo.set("picList",picList);
    EiCommunicator.send("PRGL0201", "showAfterTempPic", inInfo, {
        onSuccess : function(ei) {
            var list = ei.get("list");
            $("#reqPic2").html("")
            for (var i = 0; i < list.length; i++) {
                image(list[i].base64,"#reqPic2")
            }
        }
    });
}
