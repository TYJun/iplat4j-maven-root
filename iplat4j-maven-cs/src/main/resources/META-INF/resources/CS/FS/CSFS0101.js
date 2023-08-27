$(function () {
	// 完工提交按钮.
	$("#SUBMIT").unbind('click').on('click', function(){
		// 防止连续提交
		$("#SUBMIT").attr("disabled", true);
		setTimeout(function() {
			$("#SUBMIT").attr("disabled", false);
		}, 5000);
		console.log("完工");
		var eiInfo = new EiInfo();
		var changeStatusCode = '03'; /* 进入待评价状态 */
		var taskId = $("#taskId").val(); /* 工单id */
		var itemCode = $("#itemCode").val();/* 子工单对应的保洁事项 */
		
		eiInfo.set("taskId", taskId);
		eiInfo.set("itemCode", itemCode);
		eiInfo.set("statusCode", changeStatusCode);
		// 执行后台CSFS0101.finishMethod接口方法.
		EiCommunicator.send("CSFS0101", "finishMethod", eiInfo, {
			onSuccess : function(ei) {
				var popData = $("#popData", parent.document);
				popData.kendoWindow().data("kendoWindow").close();
				NotificationUtil(ei.getMsg(), "success");
				setTimeout(function() {
					window.parent.location.reload()
				}, 600);
			}
		});
		
	});

})
