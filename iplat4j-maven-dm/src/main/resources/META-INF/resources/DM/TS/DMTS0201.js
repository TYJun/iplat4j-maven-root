$(function() {
	IPLATUI.EFDatePicker = {
		"currentMonth":{
			start:"year",
			depth:"year"
		}
	};
	// 值回显
	IPLAT.EFInput.value( $("#currentRent") , __ei.actualRent);
	IPLAT.EFInput.value( $("#currentManageFee") , __ei.actualManageFee);
	$("#SUBMIT").unbind('click').on('click',function(event) {
		$("#SUBMIT").attr("disabled", true);
		setTimeout(function() {
			$("#SUBMIT").attr("disabled", false);
		}, 2000);
		var eiInfo = new EiInfo();
		var id = IPLAT.EFInput.value($("#id")); /* 宿舍人员绑定关系表主键id */
		var manId = IPLAT.EFInput.value($("#manId")); /* 人员id */
		var roomId = IPLAT.EFInput.value($("#roomId")); /* 房间id */
		var bedNo = IPLAT.EFInput.value($("#bedNo")); /* 床位号 */
		var remainingBedNum = IPLAT.EFInput.value($("#remainingBedNum")); /* 剩余床位 */
		var currentMonth = IPLAT.EFInput.value($("#currentMonth")); /* 当前年月 */
		var currentRent = IPLAT.EFInput.value($("#currentRent")); /* 当月实际房租 */
		var currentManageFee = IPLAT.EFInput.value($("#currentManageFee")); /* 当月实际管理费 */
		var waterPriece = IPLAT.EFInput.value($("#waterPriece")); /* 当月水费 */
		var elecPriece = IPLAT.EFInput.value($("#elecPriece")); /* 当月电费 */
		// 参数校验
		if (!validate(currentMonth,currentRent,currentManageFee,waterPriece,elecPriece)) {
			return;
		}
		eiInfo.set("id", id);
		eiInfo.set("manId", manId);
		eiInfo.set("roomId", roomId);
		eiInfo.set("bedNo", bedNo);
		eiInfo.set("remainingBedNum", remainingBedNum);
		eiInfo.set("currentMonth", currentMonth);
		eiInfo.set("currentRent", currentRent);
		eiInfo.set("currentManageFee", currentManageFee);
		eiInfo.set("waterPriece", waterPriece);
		eiInfo.set("elecPriece", elecPriece);
		EiCommunicator.send("DMTS0201", "update", eiInfo,
				{
					onSuccess : function(ei) {
						var popData = $("#popDataSettle",
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
	function validate(currentMonth,currentRent,currentManageFee,waterPriece,elecPriece) {
		// var reg = /^[0-9]*$/;
		// 非负数正则
		var reg = /^(\+)?\d+(\.\d+)?$/;
		if (isEmpty(currentMonth)) {
			NotificationUtil("请选择当前录入的年月份", "warning");
			return false;
		}
		if (isEmpty(currentRent)) {
			NotificationUtil("请输入当月实际月租", "warning");
			return false;
		}else{
			if (!reg.test(currentRent)){
				NotificationUtil("当月实际月租仅需要输入数值(非负数)，不需要额外补充中文", "warning");
				return false;
			}
		}
		if (isEmpty(currentManageFee)) {
			NotificationUtil("请输入当月实际管理费", "warning");
			return false;
		}else{
			if (!reg.test(currentManageFee)){
				NotificationUtil("当月实际管理费仅需要输入数值(非负数)，不需要额外补充中文", "warning");
				return false;
			}
		}
		if (isEmpty(waterPriece)) {
			NotificationUtil("请输入当月水费", "warning");
			return false;
		}else{
			if (!reg.test(waterPriece)){
				NotificationUtil("当月水费仅需要输入数值(非负数)，不需要额外补充中文", "warning");
				return false;
			}
		}
		if (isEmpty(elecPriece)) {
			NotificationUtil("请输入当月电费", "warning");
			return false;
		}else{
			if (!reg.test(elecPriece)){
				NotificationUtil("当月电费仅需要输入数值(非负数)，不需要额外补充中文", "warning");
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
