$(function() {
	
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	
	$("#RESET").on("click", function (e) {
		$("#deptName").val("");
		$("#workNo").val("");
		$("#userName").val("");
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
					//window.parent.exmanGrid.addRows(checkRows);
					window.parent.saveExman(checkRows);
					window.parent['popDataExmanWindow'].close();
			    });
			}
		}
	};
	
});