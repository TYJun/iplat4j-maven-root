
$(function() {
	
	// 新增电话信息的保存按钮点击事件
	$("#SAVE").on("click", function (e) {
		var eiInfo = new EiInfo();

		var spotId = IPLAT.EFInput.value($("#spotId"));
		var deptId = IPLAT.EFInput.value($("#deptId"));
		var remark = IPLAT.EFInput.value($("#remark"));
		var telNum = IPLAT.EFInput.value($("#telNum"));
		
		//参数校验
		if(!validate(telNum)){
			return;
		}
		
		
		eiInfo.set("spotId",spotId);
		eiInfo.set("deptId",deptId);
		eiInfo.set("remark",remark);
		eiInfo.set("telNum",telNum);

		EiCommunicator.send("ACSP01", "insertTele", eiInfo, {
			onSuccess : function(ei) {
				if (ei.getStatus() != 0){
					IPLAT.alert(ei.getMsg());
					return;
				}
				window.parent.resultTeleGrid.dataSource.page(1);
				window.parent['spotName'].value = "";
				window.parent['spotId'].value = "";
				window.parent['popDataWindow'].close();
			}
		});

    });
});



//参数校验
function validate(telNum){
	if(isEmpty(telNum)){
		NotificationUtil("电话称不能为空", "error");
		return false;
	} 
	return true;
}

function isEmpty(parameter){
	if(parameter == undefined || parameter == null){
		return true;
	} else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
		return true;
	} else {
		return false;
	}
}