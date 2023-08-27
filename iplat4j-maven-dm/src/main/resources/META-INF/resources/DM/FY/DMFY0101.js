$(function() {
	IPLAT.EFInput.value($("#extraCharges"),__ei.extraCharges); /* 额外费用 */
	$("#SUBMIT").unbind('click').on('click',function(event) {
		$("#SUBMIT").attr("disabled", true);
		setTimeout(function() {
			$("#SUBMIT").attr("disabled", false);
		}, 2000);
		var eiInfo = new EiInfo();
		var roomId = IPLAT.EFInput
			.value($("#roomId")); /* 宿舍id */
		var manId = IPLAT.EFInput
			.value($("#manId")); /* 人员id */
		var currentMonth = IPLAT.EFInput
			.value($("#currentMonth")); /* 当前年月 */
		var extraCharges = IPLAT.EFInput
		.value($("#extraCharges")); /* 额外费用 */
		// 参数校验
		if (!validate(extraCharges)) {
			return;
		}
		eiInfo.set("roomId", roomId);
		eiInfo.set("manId", manId);
		eiInfo.set("currentMonth", currentMonth);
		eiInfo.set("extraCharges", extraCharges);
		EiCommunicator.send("DMFY01", "updateExtraCharges", eiInfo,
			{
				onSuccess : function(ei) {
					var popData = $("#popDataExtraCharges",
							parent.document);
					popData.kendoWindow().data(
							"kendoWindow").close();
					NotificationUtil(ei.getMsg(),
							"success");
					setTimeout(function() {
						window.parent.location.reload()
					}, 600);
				}
			});
	});

	// 参数校验
	function validate(extraCharges){
		// var reg = /^[1-9]\d*$/;
		// 非负数正则
		var reg = /^(\+)?\d+(\.\d+)?$/;
		if(isEmpty(extraCharges)){
			NotificationUtil("请输入额外费用", "warning");
			return false;
		}else{
			if (reg.test(extraCharges)) {
				return true;
			} else {
				NotificationUtil("请正确填写额外费用,仅需要输入数值(非负数)，不需要额外补充中文", "warning");
				return false;
			}
		}
	}

	// 判空函数
	function isEmpty(parameter) {
		if (parameter == undefined || parameter == null) {
			return true;
		} else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == "") {
			return true;
		} else {
			return false;
		}
	}
	
	
});
