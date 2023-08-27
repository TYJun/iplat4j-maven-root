$(function() {
	$("#SUBMIT").unbind('click').on('click',function(event) {
		$("#SUBMIT").attr("disabled", true);
		setTimeout(function() {
			$("#SUBMIT").attr("disabled", false);
		}, 2000);
		var eiInfo = new EiInfo();
		var id = IPLAT.EFInput
			.value($("#id")); /* 宿舍人员绑定关系表id */
		var actualRent = IPLAT.EFInput
			.value($("#actualRent")); /* 实际房租 */
		var actualManageFee = IPLAT.EFInput
		.value($("#actualManageFee")); /* 实际管理费 */
		// 参数校验
		if (!validate(actualRent,actualManageFee)) {
			return;
		}
		eiInfo.set("id", id);
		eiInfo.set("actualRent", actualRent);
		eiInfo.set("actualManageFee", actualManageFee);
		EiCommunicator.send("DMQR0101", "updateDormRoomMan", eiInfo,
				{
					onSuccess : function(ei) {
						var popData = $("#popDataEdit",
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
	function validate(actualRent,actualManageFee) {
		// var reg = /^[0-9]*$/;
		// 非负数正则
		var reg = /^(\+)?\d+(\.\d+)?$/;
		if (isEmpty(actualRent)) {
			NotificationUtil("请输入实际月租", "warning");
			return false;
		}else{
			if (!reg.test(actualRent)){
				NotificationUtil("实际月租仅需要输入数值(非负数)，不需要额外补充中文", "warning");
				return false;
			}
		}
		if (isEmpty(actualManageFee)) {
			NotificationUtil("请输入实际管理费", "warning");
			return false;
		}else{
			if (!reg.test(actualManageFee)){
				NotificationUtil("实际管理费仅需要输入数值(非负数)，不需要额外补充中文", "warning");
				return false;
			}
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
