var datagrid = null;
$(function() {
	IPLATUI.EFGrid = {
		"result" : {
			 onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					let grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				if (e.field === "enterBillNo") {
					let enterBillNo = e.model['enterBillNo'];
					if (enterBillNo != " " && enterBillNo != null) {
						popData(datagrid.enterBillNo,datagrid.enterType,datagrid.enterTypeName,datagrid.wareHouseName, datagrid.remark);
					}
				}
			},
			pageable: {
				pageSize: 20,
				pageSizes: [5, 10, 20, 50]
			},
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
		resetTime(true);
		resultGrid.dataSource.page(1);
	});
})

//详情弹窗
function popData(enterBillNo, enterType, enterTypeName,wareHouseName, remark) {
	let url = IPLATUI.CONTEXT_PATH + "/web/SIRK0201?initLoad&enterBillNo=" + enterBillNo + "&enterType=" + enterType
			+ "&enterTypeName=" + enterTypeName+ "&wareHouseName=" + wareHouseName + "&remark="+remark;
	let popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popDataWindow.open().center();
}