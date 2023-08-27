$(function (){
    $("#EDITPG").click(function (){
        var validator = IPLAT.Validator({
            id:"result"
        });
        if (validator.validate()){
            var eiInfo = new EiInfo();
            var stageId =  IPLAT.EFInput.value($("#stageId"));
            var stageName = IPLAT.EFInput.value($("#stageName"));
            var stageRemark = IPLAT.EFInput.value($("#stageRemark"));
            eiInfo.set("id",stageId);
            eiInfo.set("stageName",stageName);
            eiInfo.set("stageRemark",stageRemark );
            EiCommunicator.send("PMPG01","editPmStageMsg",eiInfo,{
                onSuccess : function(ei) {
                    NotificationUtil(ei.getMsg(), "success");
                    window.parent.resultGrid.dataSource.page(1);
                    window.parent['stageEditWindow'].close();
                }
            });
        }
    })
});