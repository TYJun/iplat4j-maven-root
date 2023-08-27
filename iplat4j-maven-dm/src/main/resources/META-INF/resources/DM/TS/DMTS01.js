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
				// 退宿申请按钮
				$("#QUITROOM").on("click", function(e) {
					console.log("退宿申请");
					// 获取行数据
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 1){
						NotificationUtil("请勿勾选多条信息", "warning");
						return;
					}else if (checkRows.length < 1){
						NotificationUtil("请勾选自己的信息", "warning");
						return;
					}
					var id = checkRows[0].id;
					var manId = checkRows[0].manId;
					var manNo = checkRows[0].manNo;
					var manName = checkRows[0].manName;
					var url = IPLATUI.CONTEXT_PATH + "/web/DMTS0101?initLoad&id="+id+"&manId="+manId+"&manNo=" + manNo+"&manName=" + manName;
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


			}
		}
	}

});





	



	
	
