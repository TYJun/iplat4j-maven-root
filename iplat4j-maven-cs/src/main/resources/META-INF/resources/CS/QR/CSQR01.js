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
			onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				// 将选中的行的taskNo 工单号带出判断.
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
				// 工单确认按钮
				$("#COMFIRM").on("click", function(e) {
					// 获取所有选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					// 判断是否有选中行.
					if (checkRows.length > 0) {
						// 当前端数据缓存的状态码为01待确认的时候,可执行确认操作.
						if (checkRows[0]["statusCode"] == "01") {
							// 将行中taskId工单ID传到后台进行处理.
							var taskId = checkRows[0]["taskId"];
							var url = IPLATUI.CONTEXT_PATH
									+ "/web/CSQR0101?"
									+ "initLoad&taskId=" + taskId;
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
							NotificationUtil("请选择待确定状态的工单", "error");
						}
					} else {
						NotificationUtil("请选择想要确认的工单", "error")
					}
				});


				// 查看按钮
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
	var url = IPLATUI.CONTEXT_PATH + "/web/CSRE0201?initLoad&taskId=" + taskId;
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
