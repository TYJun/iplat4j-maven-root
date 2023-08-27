$(function() {
	IPLAT.EFInput.value($("#manId"), __ei.manId);
	IPLATUI.EFDatePicker = {
		"evalMonth":{
			start:"year",
			depth:"year"
		}
	};
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.query(1);
	});

	// 重置按钮.
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

	IPLATUI.EFGrid = {
		"result": {
			onCheckRow: function (e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			},
			loadComplete: function (grid) {
				// 查看按钮.
				$("#SHOWEVALRESULT").on("click", function(e) {
					console.log("查看满意度评价结果按钮");
					var eiInfo = new EiInfo();
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 0) {
						var manId = checkRows[0]["manId"];
						var evalMonth = checkRows[0]["evalMonth"];
						var url = IPLATUI.CONTEXT_PATH + "/web/DMPJ010101?initLoad&manId="+manId+"&evalMonth="+evalMonth;
						var popData = $("#popDataShow");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataShowWindow.open().center();
					} else {
						NotificationUtil("请选择想要查看详情的信息", "warning");
					}
				});

			}
		}
	}

});