$(function() {
	// 安装地方选择后自动带出后面的楼和层
	$("#roomId").blur(function(e){
		//alert("1111");
		var roomId = e.currentTarget.value;
		console.log(roomId);
		var eiInfo = new EiInfo();
		eiInfo.set("roomId",roomId);
		EiCommunicator.send("DMFM0101", "queryBuildingAndFloor", eiInfo, {
			onSuccess : function(ei) {
				
				var roomName = ei.get("roomName");
				var buildingCode = ei.get("buildingCode");
				var floorCode = ei.get("floorCode");
				var roomNo = ei.get("roomNo");
				var rent = ei.get("rent");
				
				IPLAT.EFInput.value($("#roomName"),roomName);
				IPLAT.EFInput.value($("#buildingCode"),buildingCode);
				IPLAT.EFInput.value($("#floorCode"),floorCode);
				IPLAT.EFInput.value($("#roomNo"),roomNo);
				IPLAT.EFInput.value($("#rent"),rent);
				
			}
		});
		
	});
});



