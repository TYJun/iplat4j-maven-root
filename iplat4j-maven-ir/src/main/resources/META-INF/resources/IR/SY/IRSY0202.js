$(function () {

    $("#SAVE").on("click", function (e) {

        var id = IPLAT.EFInput.value($("#id"));
        var noticeTitle = IPLAT.EFInput.value($("#noticeTitle"));
        var noticeContent = IPLAT.EFInput.value($("#noticeContent"));
        var status =  IPLAT.EFSelect.value($("#status"));

        if(!validate(id)){
            NotificationUtil("公告唯一标识不能为空！", "error");
        }

        if(!validate(noticeTitle)){
            NotificationUtil("公告标题不能为空！", "error");
        }

        if(!validate(noticeContent)){
            NotificationUtil("公告内容不能为空！", "error");
        }

        var eiInfo = new EiInfo();
        eiInfo.set("id", id);
        eiInfo.set("noticeTitle", noticeTitle);
        eiInfo.set("noticeContent", noticeContent);
        eiInfo.set("status", status);

        EiCommunicator.send("IRSY0202", "updateNoticeRecord", eiInfo, {
            onSuccess : function(ei) {
                var editNoticePopData = $("#editNoticePopData", parent.document);
                editNoticePopData.kendoWindow().data("kendoWindow").close();
                if(ei.status == '1'){ //修改成功
                    NotificationUtil(ei.getMsg(), "success");
                } else {
                    NotificationUtil(ei.getMsg(), "error");
                }
                setTimeout(function() {
                    window.parent.location.reload()
                }, 100);
            }
        });

    });

});


/**
 * 校验参数非空
 */
function validate(parameter){
    if(parameter == undefined || parameter == null){
        return false;
    } else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
        return false;
    }

    return true;
}