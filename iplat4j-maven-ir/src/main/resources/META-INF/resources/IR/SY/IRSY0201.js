$(function () {

    $("#SAVE").on("click", function (e) {
        var noticeTitle = IPLAT.EFInput.value($("#noticeTitle"));
        var noticeContent = IPLAT.EFInput.value($("#noticeContent"));

        if(!validate(noticeTitle)){
            NotificationUtil("公告标题不能为空！", "error");
        }

        if(!validate(noticeContent)){
            NotificationUtil("公告内容不能为空！", "error");
        }

        var eiInfo = new EiInfo();
        eiInfo.set("noticeTitle", noticeTitle);
        eiInfo.set("noticeContent", noticeContent);

        EiCommunicator.send("IRSY0201", "insertNoticeRecord", eiInfo, {
            onSuccess : function(ei) {
                var insertNoticePopData = $("#insertNoticePopData", parent.document);
                insertNoticePopData.kendoWindow().data("kendoWindow").close();
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