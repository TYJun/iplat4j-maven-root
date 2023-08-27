var datagrid = null;

$(function() {
	console.log(__ei);
	console.log(__ei.role);
	if (__ei.role == 'DMSPR_XSSS' || __ei.role == 'DMZSR'){
		$("#hiddenDiv").hide();
	}
	//查询
	$("#QUERY").on("click", function(e) {
		// 设置状态初始值。
		IPLAT.EFInput.value( $("#statusCode") , '03');
		resultGrid.dataSource.query(1);
	});

	// 重置按钮.
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

	// 确认入住进行批量化修改
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
				//确认入住按钮
				$("#CONFIRM").on("click", function(e) {
					console.log("确认入住");
					var checkRows = resultGrid.getCheckedRows();
					var idList = "";
					var manIdList = "";
					 if (checkRows.length < 1) {
						 NotificationUtil("请选择一条/多条入住信息进行确认", "warning");
						 return;
					 }
					for (var i = 0; i < checkRows.length; i++) {
						// 当遍历最后一个值时，省略最后的"，"
						if (i == checkRows.length - 1) {
							idList += checkRows[i]["id"];
							manIdList += checkRows[i]["manId"];
						} else {
							idList += checkRows[i]["id"] + ',';
							manIdList += checkRows[i]["manId"] + ',';
						}
					}
					console.log(idList);
					console.log(manIdList);
					var url = IPLATUI.CONTEXT_PATH + "/web/DMQR0101?initLoad&idList="+idList+"&manIdList=" + manIdList;
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

				// 编辑实际费用按钮.
				$("#EDITACTUALFEE").on("click", function(e) {
					console.log("编辑实际费用按钮");
					var eiInfo = new EiInfo();
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 0 && checkRows.length == 1) {
						var id = checkRows[0]["id"];
						var url = IPLATUI.CONTEXT_PATH + "/web/DMQR0102?initLoad&id="+id;
						var popData = $("#popDataEdit");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataEditWindow.open().center();
					} else if(checkRows.length > 1){
						NotificationUtil("该编辑实际费用按钮只能单选!", "warning");
						return;
					} else {
						NotificationUtil("请选择想要编辑实际费用的信息", "warning");
						return;
					}
				});

			}
		}
	}

});





	



	
	
