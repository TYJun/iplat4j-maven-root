$(function() {
	$("#SUBMIT").unbind('click').on('click',function(event) {
		$("#SUBMIT").attr("disabled", true);
		setTimeout(function() {
			$("#SUBMIT").attr("disabled", false);
		}, 2000);
		var eiInfo = new EiInfo();
		var idList = IPLAT.EFInput
			.value($("#idList")); /* 宿舍人员绑定关系表id */
		var manIdList = IPLAT.EFInput
			.value($("#manIdList")); /* 人员id */
		var manNoList = IPLAT.EFInput
			.value($("#manNoList")); /* 人员工号 */
		var actualInDate = IPLAT.EFInput
		.value($("#actualInDate")); /* 实际入住时间 */
		// 参数校验
		if (!validate(actualInDate)) {
			return;
		}
		eiInfo.set("idList", idList);
		eiInfo.set("manIdList", manIdList);
		eiInfo.set("manNoList", manNoList);
		eiInfo.set("actualInDate", actualInDate);
		EiCommunicator.send("DMQR0101", "batchUpdateOperation", eiInfo,
				{
					onSuccess : function(ei) {
						var popData = $("#popData",
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
	function validate(actualInDate) {
		if (isEmpty(actualInDate)) {
			NotificationUtil("请选择该员工的实际入住时间", "warning");
			return false;
		}
		return true;
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
