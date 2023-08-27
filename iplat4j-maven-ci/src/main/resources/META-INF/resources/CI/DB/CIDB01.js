var datagrid = null;
$(function(){
	IPLATUI.EFGrid = {
		"result" : {
			onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				if (e.field === "transBillNo") {
					var transBillNo = e.model['transBillNo'];
					if (transBillNo != " " && transBillNo != null) {
						popData("see", datagrid.transBillNo,datagrid.outWareHouseNo,datagrid.outWareHouseName,
								datagrid.inWareHouseNo,datagrid.inWareHouseName);
						resultGrid.unCheckAllRows();
					}
				}
			},
			loadComplete:function (e) {
				//录入
				$("#ADD").on("click", function(e) {
					popData("add", " ", " ", " "," "," ");
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

//录入页面弹出窗口
function popData(type, transBillNo,outWareHouseNo,outWareHouseName,inWareHouseNo,inWareHouseName) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CIDB0101?initLoad&type=" + type + "&transBillNo=" + transBillNo
		+"&inqu_status-0-outWareHouseNo="+outWareHouseNo
		+"&inqu_status-0-outWareHouseNo_textField="+outWareHouseName
		+"&inqu_status-0-inWareHouseNo"+inWareHouseNo
		+"&inqu_status-0-inWareHouseNo_textField="+inWareHouseName
		
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