$(function (){
   $("#ADDPG").click(function (){
      var validator = IPLAT.Validator({
         id:"result"
      });
      if (validator.validate()){
         var eiInfo = new EiInfo();
         var stageName = IPLAT.EFInput.value($("#stageName"));
         var stageRemark = IPLAT.EFInput.value($("#stageRemark"));
         eiInfo.set("stageName",stageName);
         eiInfo.set("stageRemark",stageRemark );
         EiCommunicator.send("PMPG01","insertPmStageMsg",eiInfo,{
            onSuccess : function(ei) {
               NotificationUtil(ei.getMsg(), "success");
               window.parent.resultGrid.dataSource.page(1);
               window.parent['stageNewWindow'].close();
            }
         });
      }
   })
});