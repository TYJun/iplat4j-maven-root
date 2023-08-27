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
				if (e.field === "matNum") {
					var matNum = e.model['matNum'];
					if (matNum != " " && matNum != null) {
						popData(datagrid.wareHouseNo, datagrid.matNum,datagrid.wareHouseName);
						resultGrid.unCheckAllRows();
					}
				}
			},
			loadComplete:function (e) {
				//生成盘库单
				$("#SCPKD").on("click", function(e) {
					popData1();
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

//打开查看物资详情弹窗
function popData(wareHouseNo, matNum,wareHouseName) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CIKC0101?initLoad&inqu_status-0-wareHouseNo="+wareHouseNo
		+ "&inqu_status-0-wareHouseName="+wareHouseName
		+ "&inqu_status-0-matNum="+matNum;
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

//打开生成盘库单弹窗
function popData1() {
	var url = IPLATUI.CONTEXT_PATH + "/web/CIKC0102?initLoad";
	var popData = $("#popData1");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popData1Window.open().center();
}