$(function() {
	$("#SUBMIT").unbind('click').on('click',function(event) {
		$("#SUBMIT").attr("disabled", true);
		setTimeout(function() {
			$("#SUBMIT").attr("disabled", false);
		}, 2000);
		var eiInfo = new EiInfo();
		var noticeNo = IPLAT.EFInput
			.value($("#noticeNo")); /* 公告序号 */
		var noticeType = IPLAT.EFSelect.value($("#noticeType")); /* 公告类别 */
		var notice = IPLAT.EFInput
		.value($("#notice")); /* 公告内容 */
		// 参数校验
		if (!validate(noticeNo,noticeType,notice)) {
			return;
		}
		eiInfo.set("noticeNo", noticeNo);
		eiInfo.set("noticeType", noticeType);
		eiInfo.set("notice", notice);
		
		EiCommunicator.send("DMGG01", "insert", eiInfo,
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
	function validate(noticeNo,noticeType,notice) {
		if (isEmpty(noticeNo)) {
			NotificationUtil("请为新增的公告内容标记个序号", "error");
			return false;
		}
		if (isEmpty(noticeType)) {
			NotificationUtil("请为新增的公告内容做个分类", "error");
			return false;
		}
		if (isEmpty(notice)) {
			NotificationUtil("新增的公告内容不能为空", "error");
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
