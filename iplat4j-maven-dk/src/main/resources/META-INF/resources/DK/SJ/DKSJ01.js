$(function() {
	
	var datagrid = null;

	IPLATUI.EFGrid = {
		"result" : {

		}
	}

	IPLATUI.EFGrid = {
		"result" : {
			onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				if (e.field === "taskCode") {
					var img = e.model['taskCode'];
					if (img != " " && img != null) {
						popData(datagrid.taskID,datagrid.taskCode,datagrid.schemeID);
					}
				}
			},
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			},
			loadComplete: function (grid) {
				//完工提交
				$("#FINISH").click(function() {
					var checkRows = resultGrid.getCheckedRows();
					var eiInfo = new EiInfo();
					if(checkRows.length!=1){
						NotificationUtil("只能单选", "error");
						return;
					}
					var status = checkRows[0].status;
					if(status=="已完工"){
						NotificationUtil("该任务已完工,请重新选择任务！", "error");
						return;
					}
					if (checkRows.length > 0) {
						var block = resultGrid.model2EiBlock(checkRows)
						eiInfo.setByNode("inqu");
						eiInfo.addBlock(block);
					}  else {
						IPLAT.NotificationUtil("请选择需要完工的数据")
						return;
					}
					var taskID = checkRows[0].taskID;
					eiInfo.set("taskID",taskID);
					IPLAT.confirm({
						message: '<b>确定执行完工操作吗？</b></i>',
						okFn: function (e) { 
							EiCommunicator.send("DKSJ0101", "updateTaskStatus", eiInfo, {
								onSuccess : function(ei) {
									IPLAT.NotificationUtil(ei.msg)
									resultGrid.dataSource.query(1);
								}
							})
						},
						cancelFn: function (e) {}
					})
				});
				//实绩维护
				$("#EDIT").on("click", function (e) {
					
					var checkRows = resultGrid.getCheckedRows();
					if(checkRows.length!=1){
						NotificationUtil("只能单选", "error");
						return;
					}
					var taskID = checkRows[0].taskID;
					var schemeID = checkRows[0].schemeID;
					var status = checkRows[0].status;
					if(status!="执行中"){
						NotificationUtil("只有执行中的任务才能维护实绩！", "error");
						return;
					}
					//打开保养窗口
					var url = IPLATUI.CONTEXT_PATH + "/web/DKSJ0101?" + "initLoad&operType=edit&taskID="+taskID+"&schemeID="+schemeID;
					var editItem = $("#editItem");
					editItem.data("kendoWindow").setOptions({
						open : function() {
							editItem.data("kendoWindow").refresh({
								url : url
							});
						}
					});
					editItemWindow.open().center();
			    });
			}
		}
	}
	
	
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	$("#RESET").on("click", function (e) {
		$("#taskCode").val("");
		$("#taskName").val("");
		$("#schemeDept").val("");
		IPLAT.EFSelect.value($("#status"),"");
		$("#startTimeS").val("");
		$("#startTimeE").val("");
		resultGrid.dataSource.page(1);
    });
	
	
	
});




