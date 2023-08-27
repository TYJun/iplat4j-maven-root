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
				if (e.field === "outBillNo") {
					var outBillNo = e.model['outBillNo'];
					if (outBillNo != " " && outBillNo != null) {
						popData(datagrid.outBillNo, datagrid.outTypeName,datagrid.wareHouseName, datagrid.userDeptName);
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
function popData(outBillNo, outTypeName, wareHouseName, userDeptName) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CICK0103?initLoad&outBillNo=" + outBillNo
		+ "&outTypeName=" + outTypeName + "&wareHouseName=" + wareHouseName + "&userDeptName=" + userDeptName;
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