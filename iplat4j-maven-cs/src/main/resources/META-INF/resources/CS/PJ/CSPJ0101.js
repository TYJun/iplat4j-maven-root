$(function () {
	// 工单评价提交按钮.
	$("#SUBMIT").unbind('click').on('click', function(){
		// 防止连续提交
		$("#SUBMIT").attr("disabled", true);
		setTimeout(function() {
			$("#SUBMIT").attr("disabled", false);
		}, 5000);
		console.log("评价");
		var eiInfo = new EiInfo();
		var changeStatusCode = '99'; /* 评价结束后进入工单结束状态 */
		var changeEvalStatus = '01'; /* 更新评价状态 */
		var taskId = $("#taskId").val(); /* 工单id */
		var evalGrade = $('input[name="evalGrade"]:checked').val(); // 服务评价（评价等级）
		var evalOpinion = IPLAT.EFInput.value($("#evalOpinion"));	//评价意见
		
		eiInfo.set("taskId", taskId);
		eiInfo.set("statusCode", changeStatusCode);
		eiInfo.set("evalGrade", evalGrade);
		eiInfo.set("evalOpinion", evalOpinion);
		eiInfo.set("evalStatus", changeEvalStatus);
		// 执行后台CSPJ0101.evaluateMethod接口方法.
		EiCommunicator.send("CSPJ0101", "evaluateMethod", eiInfo, {
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
