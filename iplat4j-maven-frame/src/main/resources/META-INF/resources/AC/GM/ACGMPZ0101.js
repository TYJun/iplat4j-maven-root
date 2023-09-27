$(function() {
    var validator = IPLAT.Validator({
        id: "result"
    });
    //保存
    $("#SAVE").unbind('click').on('click', function(event) {
        // 防止连续提交
        $("#SAVE").attr("disabled", true);
        setTimeout(function () {$("#SAVE").attr("disabled", false);}, 5000);

        var eiInfo = new EiInfo();
        eiInfo.setByNode("result");
        var matClassCode = IPLAT.EFSelect.value($("#materialCategory"));
        var matClassName = IPLAT.EFSelect.text($("#materialCategory"));
        var groupEname = IPLAT.EFSelect.value($("#Permission"));
        var groupCname = IPLAT.EFSelect.text($("#Permission"));

        eiInfo.set("matClassCode",matClassCode);
        eiInfo.set("matClassName",matClassName);
        eiInfo.set("groupEname",groupEname);
        eiInfo.set("groupCname",groupCname);

        //调用后台保存方法
        EiCommunicator.send("ACGMPZ0101", "save", eiInfo, {
            onSuccess : function(ei) {
                if(ei.getStatus() == -1){
                    NotificationUtil(ei.getMsg(), "error");
                    return;
                }

                NotificationUtil("保存成功", "success");
                setTimeout(function() {
                    window.parent.location.reload()
                }, 500);
            },
            onFail : function(errorMsg, status, e) {
                NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
            }
        });

    });

});




