IPLATUI.EFGrid = {
	"result" : {
		loadComplete: function (grid) {
			//问题明细弹窗页面
			function popData(id) {
				var url = IPLATUI.CONTEXT_PATH + "/web/PRGL0501?initLoad&id=" + id;
				var popData = $("#popData");
				popData.data("kendoWindow").setOptions({
					open : function() {
						popData.data("kendoWindow").refresh({
							url : url
						});
					}
				});
				// 打开弹窗
				popDataWindow.open().center();
			};
			//详情弹窗页面
			function popData2(id) {
				var url = IPLATUI.CONTEXT_PATH + "/web/PRGL0302?initLoad&id=" + id;
				var popData = $("#popData2");
				popData.data("kendoWindow").setOptions({
					open : function() {
						popData.data("kendoWindow").refresh({
							url : url
						});
					}
				});
				// 打开弹窗
				popData2Window.open().center();
			}
			//查看详情按钮
			$("#QUERY2").on("click", function(e) {
				var checkRows = resultGrid.getCheckedRows();
				if(checkRows.length < 1){
					NotificationUtil("请选择需要查看的问题", "error");
					return;
				}
				var id = checkRows[0].id;
				popData2(id);
			});
			//问题明细按钮
			$("#QUERY1").on("click", function(e) {
				var checkRows = resultGrid.getCheckedRows();
				if(checkRows.length < 1){
					NotificationUtil("请选择需要查看的问题", "error");
					return;
				}
				var id = checkRows[0].id;
				popData(id);
			});
		}
	}
}
$(function () {
	//查询按钮
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.query(1);
	});
	//重置按钮
	$("#RESET").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});
});