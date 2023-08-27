$(function() {
	
	//重置按钮
	$("#RESET").on("click", function (e) {
		$("#schemeCode").val("");
		$("#schemeName").val("");
		$("#schemeDept").val("");
		$("#objId").val("");
		$("#objName").val("");
		$("#status").val("");
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
						var url = IPLATUI.CONTEXT_PATH + "/web/IMJZ0101?operType=add";
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
						debugger
						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length!=1){
							NotificationUtil("只能单选", "error");
							return;
						}
						var id = checkRows[0].schemeID;
						var statusName = checkRows[0].statusName;
						if(statusName=="启用"){
							NotificationUtil("请先禁用基准！", "error");
							return;
						}
						var url = IPLATUI.CONTEXT_PATH + "/web/IMJZ0101?operType=update&id="+id;
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
							arr[i]=checkRows[i].schemeID;
							if(checkRows[i].status == "1"){
								NotificationUtil("无法删除已启用的基准", "error");
								return;
							}
						}
						var info=new EiInfo();
						info.set("list",arr);
						EiCommunicator.send("IMJZ01", "deleteScheme", info, {
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
							arr[i]=checkRows[i].schemeID;
						}
						var info=new EiInfo();
						info.set("list",arr);
						EiCommunicator.send("IMJZ01", "startScheme", info, {
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
							arr[i]=checkRows[i].schemeID;
						}
						var info=new EiInfo();
						info.set("list",arr);
						EiCommunicator.send("IMJZ01", "stopScheme", info, {
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