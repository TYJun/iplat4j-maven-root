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
				if (e.field === "enterBillNo") {
					var enterBillNo = e.model['enterBillNo'];
					if (enterBillNo != " " && enterBillNo != null) {
						popData(datagrid.enterBillNo,datagrid.enterTypeName,datagrid.wareHouseName);
					}
				}
			},
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

//详情弹窗
function popData(enterBillNo, enterTypeName,wareHouseName) {
	resultGrid.unCheckAllRows();
	var url = IPLATUI.CONTEXT_PATH + "/web/CIRK0201?initLoad&enterBillNo=" + enterBillNo
			+ "&enterTypeName=" + enterTypeName+ "&wareHouseName=" + wareHouseName;
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