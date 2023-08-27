$(function() {

	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});

	$("#REQUERY").on("click", function(e) {
		$("#contTypeName").val("");
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
							window.parent.addRows(checkRows,"cont");
							window.parent["clauseChooseWindow"].close();
						} else {
							IPLAT.NotificationUtil("请选择合同条款");
						}
					});

					//取消
					$("#CANCEL").on("click", function() {
						window.parent["clauseChooseWindow"].close();
					});
				}
			}
	}

})