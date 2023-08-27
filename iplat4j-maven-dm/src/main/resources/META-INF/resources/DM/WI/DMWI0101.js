$(function() {
	$("#roomId").blur(function(e){
		var roomId = e.currentTarget.value;
		var eiInfo = new EiInfo();
		eiInfo.set("roomId",roomId);
		EiCommunicator.send("DMFM0101", "queryBuildingAndFloor", eiInfo, {
			onSuccess : function(ei) {
				//var roomName = ei.get("roomName");
				//var typeCode = ei.get("typeCode");
				//var remainBed = ei.get("remainBed");
				var rent = ei.get("rent");
				var elecPriece = ei.get("elecPriece");
				var waterPriece = ei.get("waterPriece");
				//IPLAT.EFInput.value($("#roomName"),roomName);
				//IPLAT.EFInput.value($("#typeCode"),typeCode);
				//SSIPLAT.EFInput.value($("#remainBed"),remainBed);
				IPLAT.EFInput.value($("#elecPriece"),elecPriece);
				IPLAT.EFInput.value($("#waterPriece"),waterPriece);
				IPLAT.EFInput.value($("#rent"),rent);
			}
		});
		
	});

});