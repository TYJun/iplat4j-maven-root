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
				if (e.field === "outBillNo") {
					let outBillNo = e.model['outBillNo'];
					if (outBillNo != " " && outBillNo != null) {
						popData(datagrid.outBillNo, datagrid.outType, datagrid.outTypeName,datagrid.wareHouseName, datagrid.userDeptName, datagrid.remark);
					}
				}
			},
			pageable: {
				pageSize: 20,
				pageSizes: [10, 20, 50, 100, 500]
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
function popData(outBillNo, outType, outTypeName, wareHouseName, userDeptName, remark) {
	let url = IPLATUI.CONTEXT_PATH + "/web/SICK0103?initLoad&outBillNo=" + outBillNo + "&outType=" + outType
		+ "&outTypeName=" + outTypeName + "&wareHouseName=" + wareHouseName + "&userDeptName=" + userDeptName
		+ "&remark=" + remark;
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