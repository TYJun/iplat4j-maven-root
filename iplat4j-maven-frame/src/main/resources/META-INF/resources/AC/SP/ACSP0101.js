/**
 * 新增地点信息
 */
$(function() {
	$("#deptId").val(__ei.deptId);
	$("#deptId_textField").val(__ei.deptName);

	// 新增地点信息的保存按钮点击事件
	$("#SAVE").on("click", function (e) {
		var eiInfo = new EiInfo();
		var deptId = IPLAT.EFInput.value($("#deptId"));
		var spotName = IPLAT.EFInput.value($("#spotName"));
		var jpSpotName = IPLAT.EFInput.value($("#jpSpotName"));
		var building = IPLAT.EFInput.value($("#building"));
		var floor = IPLAT.EFInput.value($("#floor"));
		var room = IPLAT.EFInput.value($("#room"));
		var remark = IPLAT.EFInput.value($("#remark"));
		
		//参数校验
		if(!validate(spotName,building,floor,room)){
			return;
		}
		
		eiInfo.set("deptId",deptId);
		eiInfo.set("spotName",spotName);
		eiInfo.set("jpSpotName",jpSpotName);
		eiInfo.set("building",building);
		eiInfo.set("floor",floor);
		eiInfo.set("room",room);
		eiInfo.set("remark",remark);
		EiCommunicator.send("ACSP01", "insertSpot", eiInfo, {
			onSuccess : function(ei) {
				NotificationUtil(ei.getMsg(), "添加地点成功");
				window.parent.resultSpotGrid.dataSource.page(1);
				window.parent['deptName'].value = "";
				window.parent['deptId'].value = "";
				window.parent['popDataWindow'].close();
			}
		});
		
		
		
    });
});


//参数校验
function validate(spotName,building,floor,room){
	if(isEmpty(spotName)){
		NotificationUtil("地点名称不能为空", "error");
		return false;
	} 
	if(isEmpty(building)){
		NotificationUtil("楼不能为空", "error");
		return false;
	} 
	if(isEmpty(floor)){
		NotificationUtil("层不能为空", "error");
		return false;
	} 
	if(isEmpty(room)){
		NotificationUtil("房间不能不能为空", "error");
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