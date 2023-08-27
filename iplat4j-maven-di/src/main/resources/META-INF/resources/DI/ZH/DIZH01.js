$(function() {
	var datagrid = null;
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	$("#RESET").on("click", function (e) {
		$("#taskCode").val("");
		$("#taskName").val("");
		$("#spotName").val("");
		IPLAT.EFSelect.value($("#status"),"");
		$("#machineName").val("");
		$("#startTimeS").val("");
		$("#startTimeE").val("");
		resultGrid.dataSource.page(1);
    });

	IPLATUI.EFGrid = {
		"result": {
			loadComplete: function (grid) {
				$("#PRINTTASK").on("click", function (e) {
					var checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择一条记录进行打印","error");
					} else {
						window.location.href = IPLATUI.CONTEXT_PATH + "/web/DIZH0102?initLoad&taskCode=" + checkRows[0].taskCode;
					}
				});
			},
			onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				if (e.field === "taskCode") {
					var img = e.model['taskCode'];
					if (img != " " && img != null) {
						popData(datagrid.taskID,datagrid.taskCode,datagrid.schemeID);
					}
				}
			},
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			}
		}
	}
});

/**
 * 巡检任务详细页面
 * @param taskID ： 任务ID
 * @param taskCode : 任务单号
 * @param schemeID ： 基准ID
 */
function popData(taskID,taskCode,schemeID) {
	var url = IPLATUI.CONTEXT_PATH + "/web/DIZH0101?initLoad&taskID=" + taskID+"&taskCode=" + taskCode+"&schemeID=" + schemeID;
	var popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	// 打开工作流节点人员选择弹窗
	popDataWindow.open().center();
}




