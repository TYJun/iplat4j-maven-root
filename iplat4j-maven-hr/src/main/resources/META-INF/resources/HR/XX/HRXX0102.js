$(function (){
    //确认
    $("#RESAVE").unbind('click').on('click', function(e){
        // 防止连续提交
        $("#RESAVE").attr("disabled",true);
        setTimeout(function(){$("#RESAVE").attr("disabled",false);},5000);
        var inInfo = new EiInfo();
        var inTime=IPLAT.EFInput.value($("#inTime"));
        if(inTime == null && inTime==""){
            NotificationUtil("入职时间不能为空", "error");
            return;
        }
        var id=IPLAT.EFInput.value($("#id"));
        inInfo.set("id", id);
        inInfo.set("type","induction");
        inInfo.set("inTime",inTime);
        EiCommunicator.send("HRXX01", "updateInduction", inInfo, {
            onSuccess: function (ei) {
                if(ei.getStatus() == -1){
                    NotificationUtil(ei.getMsg(), "error");
                    return;
                }
                NotificationUtil("入职成功");
                window.parent['popDataModifyWindow'].close();
                window.parent["resultGrid"].dataSource.page(1);
            }
        });

    });

});
