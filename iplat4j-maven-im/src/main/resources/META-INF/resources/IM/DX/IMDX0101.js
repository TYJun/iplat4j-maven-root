$(function() {
	//地点
	var fixedPlaceNum = __ei.spotCode;
	var fixedPlaceName = __ei.spotName;
	IPLAT.EFPopupInput.value($("#fixedPlace"),fixedPlaceNum);
	IPLAT.EFPopupInput.text($("#fixedPlace"),fixedPlaceName);
	//id
	var id = __ei.id;
	$("#id").val(id);
	//spotId
	var spotId2 = __ei.spotId;
	$("#spotId2").val(spotId2);
	//对象
	var objName = __ei.objName;
	$("#objectName").val(objName);
	//备注
	var objRemark = __ei.objRemark;
	$("#remark").val(objRemark);
	
	//保存方法
	$("#QUERY").on("click", function (e) {
		debugger
		var objectName = $("#objectName").val();
		var spotNum = IPLAT.EFPopupInput.value($("#fixedPlace"));
		var spotName = IPLAT.EFPopupInput.text($("#fixedPlace"));
		var remark = $("#remark").val();
		var id = $("#id").val();
		if(objectName == "" || spotNum == "" || spotName == "" ){
			NotificationUtil("请填写必填项", "error");
			return;
		}
		var info = new EiInfo();
		info.set("objectName",objectName);
		info.set("spotNum",spotNum);
		info.set("spotName",spotName);
		info.set("remark",remark);
		info.set("id",id);
		//info.set("spotId",spotId);
		if("undefined" != typeof spotId){ 
			info.set("spotId",spotId);
		}else{
			info.set("spotId",spotId2);
		}
		EiCommunicator.send("IMDX0101", "insert", info, {
			onSuccess : function(ei) {
				var status = ei.getStatus();
				if(status == -1){
					IPLAT.alert(ei.getMsg());
				}else{
					setTimeout(function() {
						NotificationUtil(ei.getMsg(), "success");
						window.parent.location.reload()
					}, 300);
				}
			}
		});
	});
})

IPLATUI.EFPopupInput = {
	"fixedPlace":{
		backFill: function (e) {
			spotId = e.model['spotId'];
			spotCode = e.model['spotNum'];
			spotName = e.model['spotName'];
		},
	}
}
