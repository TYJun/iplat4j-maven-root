$(function() {
    //保存
    $("#SUBMIT").unbind('click').on('click', function(event) {
        // 防止连续提交
        $("#SUBMIT").attr("disabled", true);
        setTimeout(function () {$("#SUBMIT").attr("disabled", false);}, 5000);

        //获取表单数据
        let eiInfo = new EiInfo();
        eiInfo.setByNode("inqu");

        //调用后台保存方法
        EiCommunicator.send("SIPZ01", "save", eiInfo, {
            onSuccess : function(ei) {
                if(ei.getStatus() == -1){
                    NotificationUtil(ei.getMsg(), "error");
                    return;
                }

                NotificationUtil("保存成功", "success");
                window.location.reload();
            },
            onFail : function(errorMsg, status, e) {
                NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
            }
        });

    });


    //刷新
    $("#REFRESH").click(function() {
        window.location.reload();
    });

});