$(function() {
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	$("#RESET").on("click", function (e) {
		$("#cardID").val("");
		$("#cardCode").val("");
		$("#cardName").val("");
		IPLAT.EFSelect.value($("#status"),"");
		resultGrid.dataSource.page(1);
    });
	IPLATUI.EFGrid = {
			"result": {
				onCellClick : function(e) {
					e.preventDefault();
					if (!e.fake) {
						datagrid = null;
						var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
						datagrid = model;
					}
					if (e.field === "cardCode") {
						var img = e.model['cardID'];
						var cardCode = e.model['cardCode'];
						var cardName = e.model['cardName'];
						var memo = e.model['memo'];
						if (img != " " && img != null) {
							var url = IPLATUI.CONTEXT_PATH + "/web/DKKP0101?kpType=1&cardID="+img+"&cardCode="+cardCode+"&cardName="+cardName+"&memo="+memo;
							var popData = $("#popDataLook");
							popData.data("kendoWindow").setOptions({
								open : function() {
									popData.data("kendoWindow").refresh({
										url : url
									});
								}
							});
							popDataLookWindow.open().center();
						}
					}
				},
				loadComplete: function (grid) {
					$("#ADD").on("click", function (e) {
						var url = IPLATUI.CONTEXT_PATH + "/web/DKKP0101?kpType=2";
						var popData = $("#popDataAdd");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataAddWindow.open().center();
				    });
					$("#EDIT").on("click", function (e) {
						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length!=1){
							NotificationUtil("只能单选", "error");
							return;
						}
						var id = checkRows[0].cardID;
						var cardCode = checkRows[0].cardCode;
						var cardName = checkRows[0].cardName;
						var memo = checkRows[0].memo;
						var status = checkRows[0].status
						if(status=="启用"){
							NotificationUtil("请先禁用卡片！", "error");
							return;
						}
						var url = IPLATUI.CONTEXT_PATH + "/web/DKKP0101?kpType=3&cardID="+id+"&cardCode="+cardCode+"&cardName="+cardName+"&memo="+memo;
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
							arr[i]=checkRows[i].cardID;
							if(checkRows[i].status == '启用'){
								NotificationUtil("无法删除已启用的卡片", "error");
								return;
							}
						}
						var info=new EiInfo();
						info.set("list",arr);
						EiCommunicator.send("DKKP01", "deleteKP", info, {
							onSuccess : function(ei) {
								NotificationUtil(ei.getMsg(), "success");
								resultGrid.dataSource.page(1);
							}
						});
				    });
					
					$("#ACTION").on("click", function (e) {
						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length<1){
							NotificationUtil("请选择要启用的行", "error");
							return;
						}
						var arr=[];
						for(var i=0;i<checkRows.length;i++){
							arr[i]=checkRows[i].cardID;
						}
						var info=new EiInfo();
						info.set("list",arr);
						EiCommunicator.send("DKKP01", "startKP", info, {
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
							arr[i]=checkRows[i].cardID;
						}
						var info=new EiInfo();
						info.set("list",arr);
						EiCommunicator.send("DKKP01", "stopKP", info, {
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
	

		function popData(taskID,taskCode,schemeID) {
			var url = IPLATUI.CONTEXT_PATH + "/web/DKZH0101?initLoad&taskID=" + taskID+"&taskCode=" + taskCode+"&schemeID=" + schemeID;
			var popData = $("#popData");
			popData.data("kendoWindow").setOptions({
				open : function() {
					popData.data("kendoWindow").refresh({
						url : url,
					});
				}
			});
			// 打开工作流节点人员选择弹窗
			popDataWindow.open().center();
		}