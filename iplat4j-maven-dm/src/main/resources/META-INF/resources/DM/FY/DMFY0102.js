$(function() {
	IPLATUI.EFDatePicker = {
		"currentMonth":{
			start:"year",
			depth:"year"
		}
	};
	// 对实际月租和实际管理费进行初始赋值。
	// console.log(__ei);
	IPLAT.EFInput.value($("#currentRent"),__ei.actualRent);
	IPLAT.EFInput.value($("#currentManageFee"),__ei.actualManageFee);

	$("#SUBMIT").unbind('click').on('click',function(event) {
		$("#SUBMIT").attr("disabled", true);
		setTimeout(function() {
			$("#SUBMIT").attr("disabled", false);
		}, 2000);
		var eiInfo = new EiInfo();
		var roomId = IPLAT.EFInput.value($("#roomId")); /* 宿舍信息表主键 */
		var manId = IPLAT.EFInput.value($("#manId")); /* 人员入住信息表主键 */
		var currentMonth = IPLAT.EFInput.value($("#currentMonth")); /* 当前月份 */
		var currentRent = IPLAT.EFInput.value($("#currentRent")); /* 本月实际月租（元） */
		var currentManageFee = IPLAT.EFInput.value($("#currentManageFee")); /* 本月实际管理费（元） */
		var elecDegree = IPLAT.EFInput.value($("#elecDegree")); /* 本月用电量（度） */
		var elecPriece = IPLAT.EFInput.value($("#elecPriece")); /* 本月电费（元） */
		var waterDegree = IPLAT.EFInput.value($("#waterDegree")); /* 本月用水量（吨） */
		var waterPriece = IPLAT.EFInput.value($("#waterPriece")); /* 本月水费（元） */
		var returnRent = IPLAT.EFInput.value($("#returnRent")); /* 退房租（元） */
		var returnManageFee = IPLAT.EFInput.value($("#returnManageFee")); /* 退管理费（元） */
		var returnWaterPriece = IPLAT.EFInput.value($("#returnWaterPriece")); /* 退水费（元） */
		var returnElecPriece = IPLAT.EFInput.value($("#returnElecPriece")); /* 退电费（元） */
		var replenishRent = IPLAT.EFInput.value($("#replenishRent")); /* 补房租（元） */
		var replenishManageFee = IPLAT.EFInput.value($("#replenishManageFee")); /* 补管理费（元） */
		var replenishWaterPriece = IPLAT.EFInput.value($("#replenishWaterPriece")); /* 补水费（元） */
		var replenishElecPriece = IPLAT.EFInput.value($("#replenishElecPriece")); /* 补电费（元） */
		var extraCharges = IPLAT.EFInput.value($("#extraCharges")); /* 额外费用（元） */
		var remark = IPLAT.EFInput.value($("#remark")); /* 备注 */
		// 参数校验
		if (!validate(currentRent,currentManageFee,waterPriece,elecPriece)) {
			return;
		}
		eiInfo.set("roomId", roomId);
		eiInfo.set("manId", manId);
		eiInfo.set("currentMonth", currentMonth);
		eiInfo.set("currentRent", currentRent);
		eiInfo.set("currentManageFee", currentManageFee);
		eiInfo.set("elecDegree", elecDegree);
		eiInfo.set("elecPriece", elecPriece);
		eiInfo.set("waterDegree", waterDegree);
		eiInfo.set("waterPriece", waterPriece);
		eiInfo.set("returnRent", returnRent);
		eiInfo.set("returnManageFee", returnManageFee);
		eiInfo.set("returnWaterPriece", returnWaterPriece);
		eiInfo.set("returnElecPriece", returnElecPriece);
		eiInfo.set("replenishRent", replenishRent);
		eiInfo.set("replenishManageFee", replenishManageFee);
		eiInfo.set("replenishWaterPriece", replenishWaterPriece);
		eiInfo.set("replenishElecPriece", replenishElecPriece);
		eiInfo.set("extraCharges", extraCharges);
		eiInfo.set("remark", remark);
		EiCommunicator.send("DMFY01", "insertDormsRoomFeeInfo", eiInfo,
			{
				onSuccess : function(ei) {
					var popData = $("#popDataADD",
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
	function validate(currentRent,currentManageFee,waterPriece,elecPriece){
		// var reg = /^[0-9]*$/;
		// 非负数正则
		var reg = /^(\+)?\d+(\.\d+)?$/;
		if (isEmpty(currentRent)) {
			NotificationUtil("请输入本月实际月租", "warning");
			return false;
		}else{
			if (!reg.test(currentRent)){
				NotificationUtil("本月实际月租仅需要输入数值(非负数)，不需要额外补充中文", "warning");
				return false;
			}
		}
		if (isEmpty(currentManageFee)) {
			NotificationUtil("请输入本月实际管理费", "warning");
			return false;
		}else{
			if (!reg.test(currentManageFee)){
				NotificationUtil("本月实际管理费仅需要输入数值(非负数)，不需要额外补充中文", "warning");
				return false;
			}
		}
		if (isEmpty(waterPriece)) {
			NotificationUtil("请输入本月水费", "warning");
			return false;
		}else{
			if (!reg.test(waterPriece)){
				NotificationUtil("本月水费仅需要输入数值(非负数)，不需要额外补充中文", "warning");
				return false;
			}
		}
		if (isEmpty(elecPriece)) {
			NotificationUtil("请输入本月电费", "warning");
			return false;
		}else{
			if (!reg.test(elecPriece)){
				NotificationUtil("本月电费仅需要输入数值(非负数)，不需要额外补充中文", "warning");
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
