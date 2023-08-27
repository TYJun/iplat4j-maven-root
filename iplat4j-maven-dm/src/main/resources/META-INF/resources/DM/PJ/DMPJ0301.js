$(function() {
	$("#SUBMIT").unbind('click').on('click',function(event) {
		$("#SUBMIT").attr("disabled", true);
		setTimeout(function() {
			$("#SUBMIT").attr("disabled", false);
		}, 2000);
		var eiInfo = new EiInfo();
		var manId = IPLAT.EFInput.value($("#manId"));	//人员Id
		var manBehavior = $('input[name="manBehavior"]:checked').val(); // 学生行为（评价等级）
		var evalContent = IPLAT.EFInput.value($("#evalContent"));	//评价备注栏
		eiInfo.set("manId", manId);
		eiInfo.set("manBehavior", manBehavior);
		eiInfo.set("evalContent", evalContent);
		EiCommunicator.send("DMPJ0301", "insertResult", eiInfo,
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
