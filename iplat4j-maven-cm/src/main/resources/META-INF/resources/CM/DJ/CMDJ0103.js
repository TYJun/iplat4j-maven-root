$(function() {
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});

	$("#REQUERY").on("click", function(e) {
		$("#workNo").val("");
		$("#name").val("");
		resultGrid.dataSource.page(1);
	});
	
	IPLATUI.EFGrid = {
			"result" : {
				loadComplete: function (e) {
					//确定
					$("#CONFIRM").on("click",function() {
						var checkRows = resultGrid.getCheckedRows();
						var eiInfo = new EiInfo();
						if (checkRows.length > 0) {
							window.parent.addRows(checkRows,"person");
							window.parent["personChooseWindow"].close();
						} else {
							IPLAT.NotificationUtil("请选择人员");
						}
					});

					//取消
					$("#CANCEL").on("click", function() {
						window.parent["personChooseWindow"].close();
					});
				}
			}
	}
})