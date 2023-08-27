$(function() {
	//重置按钮
	$("#RESET").on("click", function(e) {
		$("#inqu_status-0-objName").val("");
		$("#inqu_status-0-spotName").val("");
		$("#inqu_status-0-spotCode").val("");
		resultGrid.dataSource.page(1);
	});
	//查看方法
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	})

	IPLATUI.EFGrid = {
		"result": {
			loadComplete: function (grid) {
				// 新增
				$("#ADD").on("click", function (e) {
					var url = IPLATUI.CONTEXT_PATH + "/web/IMDX0101";
					var popData = $("#popData");
					popData.data("kendoWindow").setOptions({
						open : function() {
							popData.data("kendoWindow").refresh({
								url : url
							});
						}
					});
					popDataWindow.open().center();
				});
				
				// 编辑
				$("#EDIT").on("click", function (e) {
					var rows = resultGrid.getCheckedRows();
					if(rows.length > 0){
						id = rows[0].id;
					}else{
						NotificationUtil("请选择需要编辑的行", "error");
						return;
					}
					var url = IPLATUI.CONTEXT_PATH + "/web/IMDX0101?id="+id;
					var popData = $("#popData");
					popData.data("kendoWindow").setOptions({
						open : function() {
							popData.data("kendoWindow").refresh({
								url : url
							});
						}
					});
					popDataWindow.open().center();
				});

				// 删除
				$("#DELETEROW").on("click",function(e) {
					var checkRows = resultGrid.getCheckedRows();
					if(checkRows.length < 1){
						NotificationUtil("请选择要删除的行", "error");
						return;
					}
					var id = checkRows[0].id;
					var info = new EiInfo();
					info.set("id",id);
					EiCommunicator.send("IMDX01", "delete", info, {
						onSuccess : function(ei) {
							NotificationUtil(ei.getMsg(), "success");
							resultGrid.dataSource.page(1);
						}
					});
				});
			}
		}
	};
});