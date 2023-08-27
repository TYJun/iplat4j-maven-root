$(function() {
	//重置按钮
	// $("#REQUERY").on("click", function(e) {
	// 	document.getElementById("inqu-trash").click();
	// 	resultGrid.dataSource.page(1);
	// });
	//保存方法
	$("#SURE").on("click", function (e) {

		var manageId = IPLAT.EFInput.value($("#manageId"));
		// var type = IPLAT.EFInput.value($("#type"));
		var standardName = IPLAT.EFSelect.text($("#inqu_status-0-standardName"));
		var standardCode = IPLAT.EFSelect.value($("#inqu_status-0-standardName"));
		// var workNo = IPLAT.EFPopupInput.value($("#inqu_status-0-workNameLeader"));
		// var workName = IPLAT.EFPopupInput.text($("#inqu_status-0-workNameLeader"));
		// var beginDate = $("#inqu_status-0-beginDate").val();
		// var endDate = $("#inqu_status-0-endDate").val();
		// // 0 - 全院范围，1 - 指定范围
		// var canteenNum = IPLAT.EFSelect.value($("#inqu_status-0-canteenNum"));
		// var mealNum = IPLAT.EFSelect.value($("#inqu_status-0-mealNum"));
		// var mealName = IPLAT.EFSelect.text($("#inqu_status-0-mealNum"));
		// // 0 - 无周期生成，1 - 按周期生成
		// var isCycle = IPLAT.EFSelect.value($("#inqu_status-0-isCycle"));
		// var cycleTime = $("#inqu_status-0-cycleTime").val();
		// var cycleTimeUnit = $('input[name="inqu_status-0-cycleTimeUnit"]:checked').val();
		var info = new EiInfo();
		if (standardCode == ""){
			IPLAT.NotificationUtil("请选择上级问卷主题", "error")
			return;
		}
		// else if (workName == ""){
		// 	IPLAT.NotificationUtil("请选择问卷负责人", "error")
		// 	return;
		// } else if (beginDate == ""){
		// 	IPLAT.NotificationUtil("请选择开始日期", "error")
		// 	return;
		// } else if (endDate == ""){
		// 	IPLAT.NotificationUtil("请选择截至日期", "error")
		// 	return;
		// }
		info.set("manageId",manageId);
		// info.set("type",type);
		info.set("standardCode",standardCode);
		info.set("standardName",standardName);
		// info.set("workNo",workNo);
		// info.set("workName",workName);
		// info.set("beginDate",beginDate);
		// info.set("endDate",endDate);
		// info.set("canteenNum",canteenNum);
		// info.set("mealNum",mealNum);
		// info.set("mealName",mealName);
		// info.set("isCycle",isCycle);
		// info.set("cycleTime",cycleTime);
		// info.set("cycleTimeUnit",cycleTimeUnit);
		EiCommunicator.send("SQWJ03", "insertSQManage", info, {
			onSuccess : function(ei) {
				console.log(ei.status);
				if(ei.status=="-1"){
					NotificationUtil(ei.getMsg(), "error");
				}else{
					setTimeout(function() {
						NotificationUtil(ei.getMsg(), "success");
						window.parent.location.reload()
					}, 500);
				}
			}
		});
	});

	// IPLATUI.EFSelect = {
	// 	"inqu_status-0-isCycle":{
	// 		change: function (e) {
	// 			var status = IPLAT.EFSelect.value( $("#inqu_status-0-isCycle") );
	// 			if(status=="1"){
	// 				$("#cycleTimeUnit").show();
	// 				IPLAT.EFInput.readonly($("#cycleTime"),false);
	// 			}else{
	// 				$("#cycleTimeUnit").hide();
	// 				IPLAT.EFInput.readonly($("#cycleTime"),true);
	// 			}
	// 		}
	// 	}
	// }

	// IPLATUI.EFCascadeSelect = {
	// 	"inqu_status-0-canteenNum":{
	// 		change: function (e) {
	// 			var status = IPLAT.EFSelect.value( $("#inqu_status-0-canteenNum"));
	// 			if(status=="1"){
	// 				$("#range").show();
	// 			}else{
	// 				$("#range").hide();
	// 			}
	// 		}
	// 	}
	// }
});
