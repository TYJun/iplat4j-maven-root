$(function() {
	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});
	//查询方法
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	});
});
IPLATUI.EFGrid = {
	"result": {
		loadComplete: function (grid) {
			//编辑按钮
			$("#UPDATE1").on("click", function(e) {
				var checkRows = resultGrid.getCheckedRows();
				if (checkRows.length!=1) {
					NotificationUtil("请先选择一条记录进行编辑", "error");
					return;
				}
				var perGroupNo = checkRows[0].perGroupNo;
				var url = IPLATUI.CONTEXT_PATH + "/web/SQFL0103?initLoad&perGroupNo=" + perGroupNo;
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
			});
			//删除按钮
			$("#DELETE1").on("click", function (e) {
				var checkRows = resultGrid.getCheckedRows();
				if (checkRows.length!=1) {
					NotificationUtil("请先选择一条记录进行删除", "error");
					return;
				}
				var perGroupNo = checkRows[0].perGroupNo;
				var perGroupName = checkRows[0].perGroupName;
				var info=new EiInfo();
				info.set("perGroupNo",perGroupNo);
				info.set("perGroupName",perGroupName);
				EiCommunicator.send("SQFL01", "delete", info, {
					onSuccess : function(ei) {
						if(ei.getMsg()=='删除成功'){
							NotificationUtil(ei.getMsg(), "success");
							resultGrid.dataSource.page(1);
						}else{
							NotificationUtil(ei.getMsg(), "error");
						}
					}
				});
			});
			//新增按钮
			$("#ADD").on("click", function(e) {
				var url = IPLATUI.CONTEXT_PATH + "/web/SQFL0101?initLoad";
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
			});

			},
		//页面点击事件获取选中行id
		onCellClick : function(e) {
			e.preventDefault();
			if (!e.fake) {
				datagrid = null;
				var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
				datagrid = model;
			}
			if (e.field === "perGroupNo") {
				popData(datagrid.perGroupNo);
			}
		}
	}
}

function popData(perGroupNo) {
	var url = IPLATUI.CONTEXT_PATH + "/web/SQFL0102?initLoad&perGroupNo=" + perGroupNo;
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
