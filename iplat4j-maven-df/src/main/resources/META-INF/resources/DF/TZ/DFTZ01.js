$(function() {
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	
	$("#RESET").on("click", function (e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
    });
	
	IPLATUI.EFGrid = {
		"result" : {
			onRowDblClick: function (e) {
				let machineId = e.model.machineId;
				let machineCode = e.model.machineCode;
				let machineName = e.model.machineName;
//				window.location.href = IPLATUI.CONTEXT_PATH + "/web/DFTZ0101";
				var url = IPLATUI.CONTEXT_PATH + "/web/DFTZ0101?initLoad&machineId="+ machineId+"&machineCode="+ machineCode+"&machineName"+machineName;
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