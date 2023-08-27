/* 获取选中的全局变量 */
	var datagrid = null;
	IPLATUI.EFGrid = {
		"result" : {
			page: function (e) {
				datagrid = null;
			},
			onRowClick : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;				
				}				
			},
			onCheckRow : function(e){
				if (!e.fake) {
					var flag = datagrid;
					console.log(flag);
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
					console.log(datagrid);
					if(flag == datagrid){
						datagrid = null;
					}
				}
			},
			dataBinding: function (e) {
				for (var i = 0, length = e.items.length; i < length; i++){
					if(isAvailable(e.items[i].budget)){
						e.items[i].budget = parseFloat(e.items[i].budget);
					}
				}
			},
			onDelete : function(e){
				datagrid = null;
			},
			loadComplete: function (e) {
				//新增按钮
				$("#ADD").on("click", function(e) {
					popData("", "add", "HIXX0101");
				});
				
				/* 详情按钮 */
				$("#LOOK").on("click",function(e){
					if (datagrid == null) {
						NotificationUtil("请选择需要查看的记录", "warning")
					} else {
							popData(datagrid.id, "see","HIXX0104");
						}
				}); 
				
				/* 编辑 */
				$("#EDIT").on("click", function() {
					if (datagrid == null) {
						NotificationUtil("请选择需要编辑的记录", "warning")
					} else {
						var checkRows = resultGrid.getCheckedRows()
						var status = checkRows[0].status;
						if(status == "01"){
							NotificationUtil("已启用的工单不允许编辑", "warning")
						}
						else {
							popData(datagrid.id, "edit","HIXX0103");
						}

					}
				});
				
				 /* 删除 */
				$("#REDELETE").on("click",function(e) {
					var checkRows = resultGrid.getCheckedRows()
					if(checkRows.length <= 0) {
						NotificationUtil("请选择需要删除的记录", "warning")
					}else {
						var checkRows = resultGrid.getCheckedRows()
						var status = checkRows[0].status;
						if(status == "00"){
							var eiInfo = new EiInfo();
							var id = checkRows[0].id;
							eiInfo.set("id",id);
							eiInfo.set("status",status);
							EiCommunicator.send("HIXX01", "delete", eiInfo, {
								onSuccess : function(ei) {
									NotificationUtil(ei.getMsg(), "success");
									resultGrid.dataSource.page(1);
								}
							});
						}else {
							NotificationUtil("新建的工单才可删除", "warning")
						}
					}
				});
				 
				/* 启用*/
				$("#ENABLE").on("click",function(e){
					if (datagrid == null) {
						//IPLAT.alert("请先选择一条记录进行审批");
						NotificationUtil("请选择需要启用的记录", "warning")
					} else {
						var eiInfo = new EiInfo();
						eiInfo.set("id",datagrid.id);
						EiCommunicator.send("HIXX01","enable",eiInfo, {
							onSuccess : function(ei) {
								if(ei.getStatus() === -1){
									IPLAT.alert(ei.getMsg());
								}else{
									NotificationUtil("提交成功", "success");
									window.parent['popDataWindow'].close();
									window.parent["resultGrid"].dataSource.query(1);
								}
							}
						})
					}
				});

				// 停用
				$("#DEACTIVATE").on("click",function(e){
					if (datagrid == null) {
						//IPLAT.alert("请先选择一条记录进行审批");
						NotificationUtil("请选择需要停用的记录", "warning")
					} else {
						var eiInfo = new EiInfo();
						eiInfo.set("id",datagrid.id);
						EiCommunicator.send("HIXX01","deactivate",eiInfo, {
							onSuccess : function(ei) {
								if(ei.getStatus() === -1){
									IPLAT.alert(ei.getMsg());
								}else{
									//IPLAT.alert("审批成功");
									NotificationUtil("提交成功", "success");
									window.parent['popDataWindow'].close();
									window.parent["resultGrid"].dataSource.query(1);
								}
							}
						})
					}
				});



			},
		}
	}

	$(function(){


	    /* 查询 */
		$("#QUERY").on("click",function(e){
			var installDateS = $("#installDateS").val();
			var installDateE = $("#installDateE").val();
			if (installDateS != "" && installDateE != "") {
				if(installDateS>installDateE){
					NotificationUtil("安装日期起不能大于安装日期止", "warning")
					return;
				}
			}
			resultGrid.dataSource.page(1);
		});

		//重置
		$("#RESET").on("click",function(e){
			document.getElementById("inqu-trash").click();
			resultGrid.dataSource.page(1);
		});
	})

/**
 * 打开新增/编辑窗口
 * @param id
 * @param type
 * @param service
 */
function popData(id, type, service) {
	let url = IPLATUI.CONTEXT_PATH + "/web/"+ service +"?initLoad&id=" + id + "&type=" + type;
	let popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popDataWindow.open().center();
}