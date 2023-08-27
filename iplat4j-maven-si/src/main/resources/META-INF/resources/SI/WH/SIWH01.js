var datagrid = null;

$(function() {
	//表格初始化处理
	IPLATUI.EFGrid = {
		"result" : {
			onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				if (e.field == "wareHouseNo") {
					var wareHouseNo = e.model['wareHouseNo'];
					if (wareHouseNo != " " && wareHouseNo != null) {
						popData(datagrid.id, "see");
						// resultGrid.unCheckAllRows();
					}
				}
			},
			pageable: {
				pageSize: 20,
				pageSizes: [5, 10, 20, 50]
			},
			loadComplete:function (e) {
				//新增按钮
				$("#ADD").on("click", function(e) {
					popData("", "add");
				});
				
				//冻结
				$("#DJ").on("click", function(e) {
					var checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						IPLAT.alert("请先选择一条记录进行修改");
					} else {
						if(checkRows[0].freezeFlag == "是"){
							NotificationUtil("已冻结的仓库无需再冻结","error");
							return;
						}
						var eiInfo = new EiInfo();
						eiInfo.set("id", checkRows[0].id);
						eiInfo.set("type", "dj");
						EiCommunicator.send("SIWH01", "djOrJd", eiInfo, {
							onSuccess : function(ei) {
								NotificationUtil("冻结成功");
								resultGrid.dataSource.page(1);
							}
						})
					}
				});

				//解冻
				$("#JD").on("click", function(e) {
					var checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						IPLAT.alert("请先选择一条记录进行修改");
					} else {
						if(checkRows[0].freezeFlag == "否"){
							NotificationUtil("已解冻的仓库无需再解冻","error");
							return;
						}
						var eiInfo = new EiInfo();
						eiInfo.set("id", checkRows[0].id);
						eiInfo.set("type", "jd");
						EiCommunicator.send("SIWH01", "djOrJd", eiInfo, {
							onSuccess : function(ei) {
								resultGrid.dataSource.page(1);
								NotificationUtil("解冻成功");
							}
						})
					}

				});
			}
		}
	}

	/**回车键查询**/
	keydown("inqu", "QUERY");
	
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});
	
	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});
	
});

//打开弹窗
function popData(id, type) {
	var url = IPLATUI.CONTEXT_PATH + "/web/SIWH0101?initLoad&id=" + id + "&type=" + type;
	var popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	// 打开工作流节点人员选择弹窗
	popDataWindow.open().center();
}