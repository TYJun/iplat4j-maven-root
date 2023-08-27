/**
 * 
 */
$(function() {
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	$("#RESET").on("click", function (e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
    });


	IPLATUI.EFGrid = {
			"result": {
	            loadComplete: function (grid) {	
	            	$("#EDIT").on("click", function (e) {
	            		let id;
	            		let rows = resultGrid.getCheckedRows();
	            		if(rows.length == 0){
	            			NotificationUtil("没有勾选数据", "error");
	            			return;
	            		}
	            		let url = IPLATUI.CONTEXT_PATH + "/web/DFSB0301?machineId="+rows[0].machineId
							+"&machineId_textField="+rows[0].machineCode
							+"&machineName="+rows[0].machineName
							+"&innerMachineCode="+rows[0].innerMachineCode
							+"&thisCheckDate="+rows[0].nextCheckDate
		            	let popData = $("#popData");
		            	popData.data("kendoWindow").setOptions({
		            		open : function() {
		            			popData.data("kendoWindow").refresh({
		            				url : url
		            			});
		            		}
		            	});
		            	popDataWindow.open().center();
	                });
	            }
			}
	}
});