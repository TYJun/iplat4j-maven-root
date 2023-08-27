$(function() {

//确定
	$("#COMFIRM").unbind('click').on('click', function(e){
		// 防止连续提交
		$("#COMFIRM").attr("disabled",true);
		setTimeout(function(){$("#COMFIRM").attr("disabled",false);},5000);
		var id = IPLAT.EFInput.value($("#id"));
		//参数检验
			var inInfo = new EiInfo();
			inInfo.set("id", id);
			inInfo.set("type","agree");
			EiCommunicator.send("HRDP01", "submit", inInfo, {
				onSuccess : function(ei) {
					if(ei.getStatus() == -1){
						NotificationUtil(ei.getMsg(), "error");
						return;
					}
					NotificationUtil("审核成功");
					window.parent['popDataModifyWindow'].close();
					window.parent["resultGrid"].dataSource.page(1);
				}
			})
	});

	//驳回
	$("#REJECT").unbind('click').on('click', function(e){
		// 防止连续提交
		$("#REJECT").attr("disabled",true);
		setTimeout(function(){$("#REJECT").attr("disabled",false);},5000);
		var id = IPLAT.EFInput.value($("#id"));
		var auditOpinion = IPLAT.EFInput.value($("#auditOpinion"));
		//参数检验
		if(validate(auditOpinion)){
			var eiInfo = new EiInfo();
			eiInfo.set("id", id);
			eiInfo.set("auditOpinion", auditOpinion);
			eiInfo.set("type","back");
			EiCommunicator.send("HRDP01", "submit", eiInfo, {
				onSuccess : function(ei) {
					if(ei.getStatus() == -1){
						NotificationUtil(ei.getMsg(), "error");
						return;
					}
					NotificationUtil("驳回成功");
					window.parent['popDataModifyWindow'].close();
					window.parent["resultGrid"].dataSource.page(1);
				}
			})
		}
	});

	//取消
	$("#CLOSE").on("click", function(e) {
		window.parent['popDataModifyWindow'].close();
	});
});

//参数校验
function validate(auditOpinion) {
	if(auditOpinion == null || auditOpinion=="" || auditOpinion==undefined){
		NotificationUtil("驳回理由不能为空","error");
		return false;
	}
	return true;
}

