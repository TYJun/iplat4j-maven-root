$(function () {
	// 查询按钮
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	// 重置按钮
	$("#REQUERY").on("click", function (e) {
		document.getElementById("inqu-trash").click();
	    resultGrid.dataSource.page(1);
    });
	
	
	IPLATUI.EFGrid = {
	        "result": {
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
					// 查看按钮.
					$("#SHOW").on("click", function(e) {
						var eiInfo = new EiInfo();
						// 获取所有选中的行数据.
						var checkRows = resultGrid.getCheckedRows();
						if (checkRows.length > 0) {
							// 将选中的行数据的工单ID拿出来.
							var taskId = checkRows[0]["taskId"];
							// 将选中的工单id作为参数传入popDatashow 函数中，显示工单详情.
							popDatashow(taskId);
						} else {
							NotificationUtil("请选择想要查看详情的记录", "error")
						}
					});

					// 打印工单按钮.
					$("#PRINT").on("click", function(e) {
						var eiInfo = new EiInfo();
						// 获取所有选中的行数据.
						var checkRows = resultGrid.getCheckedRows();
						var taskNo = "";
						// 判空
						if (checkRows && checkRows.length > 0) {
							// 循环遍历一下选中的行，有可能有多行，并对它们的工单号进行一个以“，”拼接.
							for (var i = 0; i < checkRows.length; i++) {
								// 当遍历最后一个值时，省略最后的"，"
								if (i == checkRows.length - 1) {
									taskNo += checkRows[i]["taskNo"];
								} else {
									taskNo += checkRows[i]["taskNo"] + ',';
								}
							}
							console.log(taskNo);
							// 将工单号传出，并以当前标签打开新的页面且刷新当前页面.
							window.location.href = IPLATUI.CONTEXT_PATH + "/web/CSCX0102?initLoad&taskNo=" + taskNo;
						} else {
							NotificationUtil("请选择想要打印的工单！", "warning")
						}
					});
				}
	        }
	}
});



// 查看工单详情
function popDatashow(taskId) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CSCX0101?initLoad&taskId=" + taskId;
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


