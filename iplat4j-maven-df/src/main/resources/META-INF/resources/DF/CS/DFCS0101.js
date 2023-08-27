$(function(){
	IPLAT.EFTreeInput.setAllFields( $("#machineTypeId") , 
	IPLAT.EFInput.value($("#moduleId")) , 
	IPLAT.EFInput.value($("#classifyName")));
	$("#SUBMIT").click(function(){
		//判断
		var validator = IPLAT.Validator({
			id: "register"
		});
		if (validator.validate()) {
			var eiInfo = new EiInfo();
			var moduleId = IPLAT.EFInput.value($("#machineTypeId"));
			var paramName = IPLAT.EFInput.value($("#paramName"));
			var paramValue = IPLAT.EFInput.value($("#paramValue"));
			var paramUnit = IPLAT.EFInput.value($("#paramUnit"));
			var memo = IPLAT.EFInput.value($("#memo"));
			eiInfo.set("moduleId",moduleId);
			eiInfo.set("paramName",paramName);
			eiInfo.set("paramValue",paramValue);
			eiInfo.set("paramUnit",paramUnit);
			eiInfo.set("memo",memo);
			EiCommunicator.send("DFCS0101", "insert", eiInfo, {
				onSuccess : function(ei) {
					NotificationUtil(ei.getMsg(), "success");
					window.parent.resultGrid.dataSource.page(1);
					window.parent['popDataWindow'].close();
				}
			});
		}
		
	});
});