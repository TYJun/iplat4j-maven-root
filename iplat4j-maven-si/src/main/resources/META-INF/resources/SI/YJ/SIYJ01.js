let datagrid = null;
$(function() {
	IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					let grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			},
			pageable: {
				pageSize: 20,
				pageSizes: [10, 20, 50, 100]
			},
			loadComplete:function (e) {
				//录入
				$("#ADD").on("click", function(e) {
					popData(" ", " ", " ", "add");
				});

				//编辑
				$("#EDIT").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows();
					if (checkRows.length == 0) {
						NotificationUtil("请先选择一条记录","error");
					}else{
						popData(checkRows[0].id , checkRows[0].wareHouseName , checkRows[0].wareHouseNo, "edit");
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

function popData(ids,wareHouseName,wareHouseNo,type) {
	let url = IPLATUI.CONTEXT_PATH + "/web/SIYJ0101?initLoad&ids=" + ids + "&type=" + type;
			+ "&inqu_status-0-wareHouseNo_textField=" + wareHouseName 
			+ "&inqu_status-0-wareHouseNo="+ wareHouseNo 
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