$(function (){
    //确认
    $("#RESAVE").unbind('click').on('click', function(e){
        // 防止连续提交
        $("#RESAVE").attr("disabled",true);
        setTimeout(function(){$("#RESAVE").attr("disabled",false);},5000);
        var inInfo = new EiInfo();
        var outTime=IPLAT.EFInput.value($("#outTime"));
        if(outTime == null || outTime==""){
            NotificationUtil("离职时间不能为空", "error");
            return;
        }
        var id=IPLAT.EFInput.value($("#id"));
        inInfo.set("id", id);
        inInfo.set("type","remove");
        inInfo.set("outTime",outTime);
        EiCommunicator.send("HRLZ01", "updateCancel", inInfo, {
            onSuccess: function (ei) {
                if(ei.getStatus() == -1){
                    NotificationUtil(ei.getMsg(), "error");
                    return;
                }
                NotificationUtil("离职成功");
                window.parent['popDataModifyWindow'].close();
                window.parent["resultGrid"].dataSource.page(1);
            }
        });

    });

});
