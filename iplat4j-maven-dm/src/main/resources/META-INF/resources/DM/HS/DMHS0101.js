$(function() {
	$("#SUBMIT").unbind('click').on('click',function(event) {
		$("#SUBMIT").attr("disabled", true);
		setTimeout(function() {
			$("#SUBMIT").attr("disabled", false);
		}, 2000);
		var eiInfo = new EiInfo();
		var id = IPLAT.EFInput
			.value($("#id")); /* 宿舍人员绑定关系表id */
		var manId = IPLAT.EFInput
			.value($("#manId")); /* 人员id */
		var changeRoomNote = IPLAT.EFInput
		.value($("#changeRoomNote")); /* 换宿原因 */
		// 参数校验
		if (!validate(changeRoomNote)) {
			return;
		}
		eiInfo.set("id", id);
		eiInfo.set("manId", manId);
		eiInfo.set("changeRoomNote", changeRoomNote);
		EiCommunicator.send("DMHS0101", "update", eiInfo,
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
	function validate(changeRoomNote) {
		if (isEmpty(changeRoomNote)) {
			NotificationUtil("请输入换宿原因", "warning");
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
