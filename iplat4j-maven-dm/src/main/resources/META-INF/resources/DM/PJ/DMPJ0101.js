$(function () {
	// 工单评价提交按钮.
	$("#SUBMIT").unbind('click').on('click', function(){
		// 防止连续提交
		$("#SUBMIT").attr("disabled", true);
		setTimeout(function() {
			$("#SUBMIT").attr("disabled", false);
		}, 5000);
		console.log("评价");
		var array = [];
		var eiInfo = new EiInfo();
		var manId = $("#manId").val(); /* 人员入住信息id */
		var roomId = $("#roomId").val(); /* 宿舍信息id */
		var evalContent = IPLAT.EFInput.value($("#evalContent"));	//评价内容
		EiCommunicator.send("DMPJ0101", "queryItemCode", eiInfo, {
			onSuccess : function(ei) {
				NotificationUtil(ei.getMsg(), "success");
				console.log(ei);
				var itemCodeList = ei.getBlock("itemCodelist").getRows();
				console.log(itemCodeList);
				for (var i = 0; i < itemCodeList.length; i++) {
					array.push(getParamter(itemCodeList[i][0]));//参数获取
				}
				eiInfo.set("manId",manId);
				eiInfo.set("roomId",roomId);
				eiInfo.set("evalContent", evalContent);
				eiInfo.set("list",array);
				// 执行后台CSPJ0101.evaluateMethod接口方法.
				EiCommunicator.send("DMPJ0101", "saveEvaluateResult", eiInfo, {
					onSuccess : function(ei) {
						var popData = $("#popData", parent.document);
						popData.kendoWindow().data("kendoWindow").close();
						NotificationUtil(ei.getMsg(), "success");
						setTimeout(function() {
							window.parent.location.reload()
						}, 600);
					}
				});
			}
		});
	});
})

function getParamter (value) {
	var param = {
		"code" : value,		/* 项目编码 */
		"itemName" : $("." + value + "").text(), /* 评价内容 */
		"evalLevel" : $("input[name='" + value + "']:checked").val(), /* 项目选项 */
	}
	return param;
}
