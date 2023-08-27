$(function() {
	// 获取前端的查询时间起和查询时间止
	var time1 = document.getElementById("recCreateTimeS");
	var time2 = document.getElementById("recCreateTimeE");
	// 查询按钮.
	$("#QUERY").on("click", function(e) {
		// 判断前端的查询时间起和查询时间止是否为空，当都不为空的时候，对两者进行对比
		// 若查询时间起的时间大于查询时间止的时间，则弹窗提示并重新加载.
		if (time2.value != "" && time1.value != "") {
			if (time1.value > time2.value) {
				alert("查询条件中填写的时间有误，时间起不能大于时间止！");
				window.location.reload();
			} else {
				resultGrid.dataSource.page(1);
			}
			// 当查询时间止不为空的时，单独查询.
		} else if (time2.value != "") {
			resultGrid.dataSource.page(1);
		} else {
			resultGrid.dataSource.page(1);
		}
	});

	// 重置按钮.
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

	IPLATUI.EFGrid = {
		"result" : {
			// 产生行的选中事件时执行的操作.
			onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				// 将选中的行的taskNo 工单号带出.
				if (e.field === "taskNo") {
					var img = e.model['taskNo'];
					if (img != " " && img != null) {
						// 将选中的工单id作为参数传入popDatashow 函数中，显示工单详情.
						popDatashow(datagrid.taskId);
					}
				}
			},
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			},
			loadComplete : function(grid) {
				// 保洁完工按钮
				$("#FINISHDONE").on("click", function(e) {
					// 获取所有选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					// 判断是否有选中行.
					if (checkRows.length > 0) {
						// 当前端数据缓存的状态码为02待完工的时候,可执行完工操作.
						if (checkRows[0]["statusCode"] == "02") {
							// 将获取行的工单ID和事项编码itemCode 取出并做拼接.
							var taskId = checkRows[0]["taskId"];
							var itemCode = checkRows[0]["itemCode"];
							var taskIdAnditemCode = taskId + ',' + itemCode;
							// 将拼接的值传到后台进行处理.
							var url = IPLATUI.CONTEXT_PATH
									+ "/web/CSFS0101?"
									+ "initLoad&taskIdAnditemCode=" + taskIdAnditemCode;
							var popData = $("#popData");
							popData.data("kendoWindow").setOptions(
									{
										open : function() {
											popData.data("kendoWindow")
													.refresh({
														url : url
													});
										}
									});
							popDataWindow.open().center();
						} else {
							NotificationUtil("请选择待完工状态的工单", "error");
						}
					} else {
						NotificationUtil("请选择想要完工的工单", "error")
					}
				});


				// 查看按钮.
				$("#SHOW").on("click", function(e) {
					var eiInfo = new EiInfo();
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 0) {
						var taskId = checkRows[0]["taskId"];
						popDatashow(taskId);
					} else {
						NotificationUtil("请选择想要查看详情的记录", "error")
					}
				});
			}
		}
	}
});

// 查看详情工单
function popDatashow(taskId) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CSFS0102?initLoad&taskId=" + taskId;
	var popData = $("#popDatashow");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	// 打开工作流节点人员选择弹窗
	popDatashowWindow.open().center();
}
