$(function() {
	//查询按钮
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	//重置按钮
	$("#RESET").on("click", function (e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
    });
	
	/*//查看按钮
	$("#RELOOK").on("click", function(e) {
		 if (datagrid == null) {
				IPLAT.alert("请先选择一条记录进行操作");
			} else {
				var url = IPLATUI.CONTEXT_PATH + "/web/DMSB0401?initLoad&type=edit&id="+datagrid.id;
				var popData = $("#popData");
				popData.data("kendoWindow").setOptions({
					open : function() {
						popData.data("kendoWindow").refresh({
							url : url
						});
					}
				});
				popDataWindow.open().center();
			}
	});*/
	
	IPLATUI.EFGrid = {
		"result" : {
			onRowDblClick: function (e) {
				debugger;
				var machineId = e.model.machineId;
//				window.location.href = IPLATUI.CONTEXT_PATH + "/web/DFTZ0101";
				var url = IPLATUI.CONTEXT_PATH + "/web/DFSB0401?initLoad&machineId="+ machineId;
            	var popData = $("#popData");
            	popData.data("kendoWindow").setOptions({
            		open : function() {
            			popData.data("kendoWindow").refresh({
            				url : url
            			});
            		}
            	});
            	popDataWindow.open().center();
			}
		}
	}
	
});