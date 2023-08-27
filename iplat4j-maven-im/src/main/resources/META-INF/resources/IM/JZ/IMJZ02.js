$(function() {
	
	//重置按钮
	$("#RESET").on("click", function (e) {
		$("#inqu_status-0-machineCode").val("");
		$("#inqu_status-0-machineName").val("");
		$("#inqu_status-0-status").val("");
		resultGrid.dataSource.page(1);
    });
	
	//查询按钮
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	
	IPLATUI.EFGrid = {
			"result": {
				loadComplete: function (grid) {
					$("#ADD").on("click", function (e) {
						var url = IPLATUI.CONTEXT_PATH + "/web/IMJZ0201?operType=add";
						var popData = $("#popData");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataWindow.open().center();
				    });
					$("#EDIT").on("click", function (e) {
						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length!=1){
							NotificationUtil("只能单选", "error");
							return;
						}
						var id = checkRows[0].id;
						var url = IPLATUI.CONTEXT_PATH + "/web/IMJZ0202?operType=update&id="+id;
						var popData = $("#popDataModify");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataModifyWindow.open().center();
				    });
					$("#DELETEROW").on("click", function (e) {
						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length<1){
							NotificationUtil("请选择要删除的行", "error");
							return;
						}
						var arr=[];
						for(var i=0;i<checkRows.length;i++){
							arr[i]=checkRows[i].id;
							if(checkRows[i].status == "启用"){
								NotificationUtil("无法删除已启用的基准", "error");
								return;
							}
						}
						var info=new EiInfo();
						info.set("idList",arr);
						EiCommunicator.send("IMJZ02", "delete", info, {
							onSuccess : function(ei) {
								NotificationUtil(ei.getMsg(), "success");
								resultGrid.dataSource.page(1);
							}
						});
				    });
					
					$("#START").on("click", function (e) {
						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length<1){
							NotificationUtil("请选择要启用的行", "error");
							return;
						}
						var arr=[];
						for(var i=0;i<checkRows.length;i++){
							arr[i]=checkRows[i].id;
						}
						var info=new EiInfo();
						info.set("list",arr);
						info.set("code",1);
						EiCommunicator.send("IMJZ02", "update", info, {
							onSuccess : function(ei) {
								NotificationUtil(ei.getMsg(), "success");
								resultGrid.dataSource.page(1);
							}
						});
				    });
					
					$("#STOP").on("click", function (e) {
						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length<1){
							NotificationUtil("请选择要禁用的行", "error");
							return;
						}
						var arr=[];
						for(var i=0;i<checkRows.length;i++){
							arr[i]=checkRows[i].id;
						}
						var info=new EiInfo();
						info.set("list",arr);
						info.set("code",-1);
						EiCommunicator.send("IMJZ02", "update", info, {
							onSuccess : function(ei) {
								NotificationUtil(ei.getMsg(), "success");
								resultGrid.dataSource.page(1);
							}
						});
				    });
					
				}
			}
		};
	
});