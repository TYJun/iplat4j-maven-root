IPLATUI.EFGrid = {
	"result" : {
		loadComplete: function (grid) {
			//取消挂账按钮
			$("#QUERY1").on("click", function(e) {
				var checkRows = resultGrid.getCheckedRows();
				if(checkRows.length < 1){
					NotificationUtil("请选择需要取消挂账的问题", "error");
					return;
				}
				var id = checkRows[0].id;
				var eiInfo = new EiInfo();
				eiInfo.set("id", id);
				//访问方法
				EiCommunicator.send("PRGL04", "upd", eiInfo, {
					onSuccess : function(ei) {
						NotificationUtil(ei.getMsg(), "success");
						setTimeout(function() {
							window.parent.location.reload()
						}, 300);
					}
				});
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
			//详情弹窗页面
			function popData2(id) {
				var url = IPLATUI.CONTEXT_PATH + "/web/PRGL0302?initLoad&id=" + id;
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
			}
		}
	}
}

$(function() {
	//详情弹窗页面
	function popData2(id) {
		var url = IPLATUI.CONTEXT_PATH + "/web/PRGL0302?initLoad&id=" + id;
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
})
$(function () {
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.query(1);
	});
	//重置按钮
	$("#RESET").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});
});