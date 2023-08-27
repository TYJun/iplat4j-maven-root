var taskCode = null;


IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender,
					model = e.model, 
					$tr = e.tr,
					row = e.row;
					datagrid = model;
					itemID = datagrid.itemID;
					console.log(taskCode);
				}
			},
           loadComplete: function (grid) {
        	 //查看
       		$("#RELOOK").on("click", function (e) {
       			var checkRows = resultGrid.getCheckedRows();
       			if(checkRows.length<1){
       				NotificationUtil("请选择要处理的行", "error");
       				return;
       			}else{
       				var url = IPLATUI.CONTEXT_PATH + "/web/IMYC0101?taskCode=" + itemID+"&type=look";
       				var popData = $("#popData");
       				popData.data("kendoWindow").setOptions({
       					open : function() {
       						popData.data("kendoWindow").refresh({
       							url : url
       						});
       					}
       				});
       			}
       			popDataWindow.open().center();
       		});
        	   
        	 //异常处理
        		$("#REDEAL").on("click", function (e) {
        			var checkRows = resultGrid.getCheckedRows();
        			if(checkRows.length<1){
        				NotificationUtil("请选择要处理的行", "error");
        				return;
        			}else if(checkRows[0].exceptionStatus=='已处理'){
        				NotificationUtil("该异常已处理,不能再处理", "error");
        				return;
        			}
        			else{
        				var url = IPLATUI.CONTEXT_PATH + "/web/IMYC0101?taskCode=" + itemID+"&type=write";
        				var popData = $("#popData");
        				popData.data("kendoWindow").setOptions({
        					open : function() {
        						popData.data("kendoWindow").refresh({
        							url : url
        						});
        					}
        				});
        			}
        			popDataWindow.open().center();
        		});
           }
		}
};
$(function() {
	//查询按钮
	$("#QUERY").on("click", function (e) {
		//开始时间
		var startTime = $("#inqu_status-0-startTime").data("kendoDatePicker").value();
		//结束时间
		var endTime = $("#inqu_status-0-endTime").data("kendoDatePicker").value();
		if(startTime != null && endTime != null){
			if(startTime > endTime){
				NotificationUtil("开始时间不能大于结束时间", "error");
				return;
			}
		}
		resultGrid.dataSource.query(1);
	});
	
	//重置按钮
	$("#RESET").on("click", function (e) {
		$("#inqu_status-0-taskCode").val("");
		$("#inqu_status-0-jitemName").val("");
		$("#inqu_status-0-startTime").val("");
		$("#inqu_status-0-endTime").val("");
		resultGrid.dataSource.query(1);
	});


})