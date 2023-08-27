$(function() {
	
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	
	$("#RESET").on("click", function (e) {
		$("#objName").val("");
		$("#spotName").val("");
		resultGrid.dataSource.page(1);
    });
	
	IPLATUI.EFGrid = {
		"result": {
			loadComplete: function (grid) {
				$("#SUBMIT").on("click", function (e) {
					//选择数据保存到上级页面
					var checkRows = resultGrid.getCheckedRows();
					if(checkRows.length < 1){
						NotificationUtil("请至少选择一条数据", "error");
						return;
					}
					debugger
					//window.parent.deviceGrid.addRows(checkRows);
					window.parent.save(checkRows);
					window.parent['popDataDeviceWindow'].close();
			    });
			}
		}
	};
	
});