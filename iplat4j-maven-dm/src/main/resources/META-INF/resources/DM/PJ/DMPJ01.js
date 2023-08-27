$(function() {
	console.log(__ei);
	console.log(__ei.role);
	if (__ei.role == 'DMSPR_XSSS' || __ei.role == 'DMZSR'){
		$("#hiddenDiv").hide();
	}
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
				// 评价按钮.
				$("#ADDEVAL").on("click", function(e) {
					console.log("满意度评价按钮");
					var eiInfo = new EiInfo();
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 0) {
						var evalStatus = checkRows[0]["evalStatus"];
						if (evalStatus == '1'){
							NotificationUtil("该用户已进行评价，请勿反复操作!", "warning");
							return;
						}else {
							var roomManId = checkRows[0]["id"];
							var url = IPLATUI.CONTEXT_PATH + "/web/DMPJ0101?initLoad&id="+roomManId;
							var popData = $("#popData");
							popData.data("kendoWindow").setOptions({
								open : function() {
									popData.data("kendoWindow").refresh({
										url : url
									});
								}
							});
							popDataWindow.open().center();
						}
					} else {
						NotificationUtil("请选择想要评价的信息", "warning");
					}
				});

				// 查看按钮.
				$("#SHOWEVALHISTORY").on("click", function(e) {
					console.log("查看历史满意度评价结果按钮");
					var eiInfo = new EiInfo();
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 0) {
						var manId = checkRows[0]["manId"];
						var url = IPLATUI.CONTEXT_PATH + "/web/DMPJ0102?initLoad&manId="+manId;
						var popData = $("#popDataHistoryShow");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataHistoryShowWindow.open().center();
					} else {
						NotificationUtil("请选择想要查看评价结果历史的用户", "warning");
					}
				});

			}
		}
	}

});