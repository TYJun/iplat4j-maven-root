$(function() {
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	$("#RESET").on("click", function (e) {
		$("#taskCode").val("");
		$("#taskName").val("");
		$("#spotName").val("");
		IPLAT.EFSelect.value($("#status"),"");
		$("#machineName").val("");
		$("#startTimeS").val("");
		$("#startTimeE").val("");
		$("#objectName").val("");
		resultGrid.dataSource.page(1);
    });
/*	IPLATUI.EFGrid = {
	        "result": {
	            columns: [
	                {
	                    field: "taskCode",
	                    title: "任务编码",
	                    template: '<div><a href="javascript:void(0)" onclick="showDetail(\'#:taskCode#\');return false;">#:taskCode#</a></div>',
	                    width: 150
	                }
	            ]
	        }
	}*/
	
});
function showDetail(taskCode) {
	var url = IPLATUI.CONTEXT_PATH + "/web/IMZH0101?"+
	"initLoad&taskCode=" + taskCode;
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




