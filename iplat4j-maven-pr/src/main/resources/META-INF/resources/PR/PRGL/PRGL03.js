IPLATUI.EFGrid = {
	"result" : {
		loadComplete: function (grid) {
			function popData(id) {
				var url = IPLATUI.CONTEXT_PATH + "/web/PRGL0301?initLoad&id=" + id;
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
			};
			//审核按钮
			$("#QUERY1").on("click", function(e) {
				var checkRows = resultGrid.getCheckedRows();
				if(checkRows.length < 1){
					NotificationUtil("请选择需要审核的问题", "error");
					return;
				}
				var id = checkRows[0].id;
				popData(id);
			});
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
			$("#QUERY").on("click", function (e) {
				resultGrid.dataSource.query(1);
			});
			//重置按钮
			$("#RESET").on("click", function(e) {
				document.getElementById("inqu-trash").click();
				resultGrid.dataSource.page(1);
			});
		}
	}
}