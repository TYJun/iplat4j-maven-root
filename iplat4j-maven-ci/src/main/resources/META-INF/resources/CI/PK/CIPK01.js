var datagrid = null;
$(function() {
	IPLATUI.EFGrid = {
		"result" : {
			onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				if (e.field === "invenBillNo") {
					var invenBillNo = e.model['invenBillNo'];
					if (invenBillNo != " " && invenBillNo != null) {
						popData(datagrid.invenBillNo, datagrid.wareHouseName,"see");
						resultGrid.unCheckAllRows();
					}
				}
			},
			loadComplete:function (e) {
				//审核
				$("#COMFIRM").on("click", function(e) {
					var checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择一条记录进行审核", "error");
					}  else if(checkRows[0].invenStatus == '0') {
						NotificationUtil("请先维护盘点信息", "error");
					} else if(checkRows[0].invenStatus == '2'){
						NotificationUtil("该盘库单已经审核，请勿重复操作", "error");
					}else{
						popData(checkRows[0].invenBillNo, checkRows[0].wareHouseName,"edit");
					}
				});

				//盘点信息维护
				$("#PDXXWH").on("click", function(e) {
					var checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择一条数据", "error");
					} else if(checkRows[0].invenStatus == '2'){
						NotificationUtil("审核后的单子无法修改", "error");
					}else{
						popData(checkRows[0].invenBillNo, checkRows[0].wareHouseName,"pdxxxwh");
					}
				});
			}
		}
	}
	
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});

	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

})

//打开盘库信息页面
function popData(invenBillNo, wareHouseName, type) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CIPK0101?initLoad&invenBillNo="+ invenBillNo 
		+ "&wareHouseName=" + wareHouseName + "&type="+ type;
	var popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popDataWindow.open().center();
}