$(function() {
    $("#INS").on("click",function(e) {
        var rejectreason = $("#rejectreason").val();
        var id = $("#id").val();
        if(null == rejectreason){
            NotificationUtil("请填写挂账原因", "error");
            return;
        }
        var eiInfo = new EiInfo();
        eiInfo.set("rejectreason",rejectreason);
        eiInfo.set("id",id);
        EiCommunicator.send("PRGL0202", "ins", eiInfo, {
            onSuccess : function(ei) {
                NotificationUtil(ei.getMsg(), "success");
                setTimeout(function() {
                    window.parent.location.reload()
                }, 500);
            }
        })
    })
})