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
				// 查看按钮.
				$("#SHOWZHDETAIL").on("click", function(e) {
					console.log("查看详情按钮");
					var eiInfo = new EiInfo();
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 0) {
						var manId = checkRows[0]["manId"];
						popDatashow(manId);
					} else {
						NotificationUtil("请选择想要查看详情的信息", "warning")
					}
				});

				// 编辑实际费用按钮.
				$("#EDITACTUALFEE").on("click", function(e) {
					console.log("编辑实际费用按钮");
					var eiInfo = new EiInfo();
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 0) {
						if (checkRows[0]["statusCode"] == '00' || checkRows[0]["statusCode"] == '01' ||
							checkRows[0]["statusCode"] == '02' || checkRows[0]["roomNo"] == null){
							NotificationUtil("请确认该人员是否有入住信息或刷新重新！", "warning")
							return;
						}else {
							var id = checkRows[0]["id"];
							var actualRent = checkRows[0]["actualRent"];
							var actualManageFee = checkRows[0]["actualManageFee"];
							var url = IPLATUI.CONTEXT_PATH + "/web/DMZH0102?initLoad&id="+id+"&actualRent=" + actualRent+"&actualManageFee=" + actualManageFee;
							var popData = $("#popDataEdit");
							popData.data("kendoWindow").setOptions({
								open : function() {
									popData.data("kendoWindow").refresh({
										url : url
									});
								}
							});
							popDataEditWindow.open().center();
						}
					} else {
						NotificationUtil("请选择想要编辑实际费用的信息", "warning")
					}
				});
			}
		}
	}

});

// 查看详情信息
function popDatashow(manId) {
	var url = IPLATUI.CONTEXT_PATH + "/web/DMZH0101?initLoad&manId=" + manId;
	var popData = $("#popDatashow");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	// 打开工作流节点人员选择弹窗
	popDatashowWindow.open().center();
}





	



	
	
