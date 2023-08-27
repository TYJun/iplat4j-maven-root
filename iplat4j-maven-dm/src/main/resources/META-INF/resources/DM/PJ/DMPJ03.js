var datagrid = null;
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

	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			},
			loadComplete: function (grid) {
				// 评价按钮
				$("#DOEVAL").on("click", function(e) {
					console.log("评价");
					// 获取行数据
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 1){
						NotificationUtil("请勿勾选多条人员信息", "warning");
						return;
					}else if (checkRows.length < 1){
						NotificationUtil("请勾选想要给予评价的人员信息", "warning");
						return;
					}
					var manId = checkRows[0].manId;
					var url = IPLATUI.CONTEXT_PATH + "/web/DMPJ0301?initLoad&manId=" + manId;
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

				// 查看评价履历按钮
				$("#SHOWEVALLIST").on("click", function(e) {
					console.log("评价履历");
					// 获取行数据
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 1){
						NotificationUtil("请勿勾选多条人员信息", "warning");
						return;
					}else if (checkRows.length < 1){
						NotificationUtil("请勾选想要查看评价履历的人员", "warning");
						return;
					}
					var manId = checkRows[0].manId;
					var url = IPLATUI.CONTEXT_PATH + "/web/DMPJ0302?initLoad&manId=" + manId;
					var popData = $("#popDataShow");
					popData.data("kendoWindow").setOptions({
						open : function() {
							popData.data("kendoWindow").refresh({
								url : url
							});
						}
					});
					popDataShowWindow.open().center();
				});
			}
		}
	}

});





	



	
	
