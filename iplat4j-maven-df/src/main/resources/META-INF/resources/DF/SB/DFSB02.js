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
	                // 登记
	            	$("#REGISTER").on("click", function (e) {
	            		let url = IPLATUI.CONTEXT_PATH + "/web/DFSB0201";
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
	            	
	            	// 编辑
	            	$("#EDIT").on("click", function (e) {
	            		let id;
	            		let rows = resultGrid.getCheckedRows();
	            		if(rows.length > 0){
	            			id = rows[0].id;
	            		}else{
	            			NotificationUtil("没有勾选数据", "error");
	            			return;
	            		}
						if(rows[0].statusCode == "-1"||rows[0].statusCode == "1"){
							NotificationUtil("不能编辑已经审核或待审核的工单", "error");
							return;
						}
	            		let url = IPLATUI.CONTEXT_PATH + "/web/DFSB0202?id="+id;
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
	            	
	            	
	            	//提交
	                $("#SUBMIT").on("click", function (e) {
	                	/*debugger;*/
	                 let checkRows = resultGrid.getCheckedRows();
	                 if(checkRows.length<1){
	                  NotificationUtil("请选择要确认的行", "error");
	                  return;
	                 }
	                 let idList = [];
	                 for (let i = 0; i < checkRows.length; i++) {
	                  if(checkRows[i].statusCode == "-1"||checkRows[i].statusCode == "1"){            	  
	               	     NotificationUtil("只能提交新建工单", "error");
	                   return;
	                  }
	                  idList[i] = checkRows[i].id;
	                  
	                 }
					IPLAT.confirm({
						message: '<b>您确定要提交吗？</b>',
						okFn: function (e) {
							let inInfo = new EiInfo();
							inInfo.set("idList",idList);//将id放到EiInfo后台
							EiCommunicator.send("DFSB02", "updateTiJiao", inInfo, {
								onSuccess : function(ei) {
									NotificationUtil("提交成功", "success");
									resultGrid.dataSource.page(1);//刷新页面
								}
							});
						},
						cancelFn: function (e) {
						}
					});
	              });
	                
	                //审核
	                $("#EXAMINE").on("click", function (e) {
	            
	                 let checkRows = resultGrid.getCheckedRows();
	                 if(checkRows.length<1){
	                	 NotificationUtil("请选择要确认的行", "error");
		                  return;
	                 }                 
	                
	                 let idList = [];
	                 for (let i = 0; i < checkRows.length; i++) {
	                  if(checkRows[i].statusCode == "1"||checkRows[i].statusCode == "0"){
	                   	NotificationUtil("只能审核待审核工单", "error");
	                   return;
	                   }
	                
	                  idList[i] = checkRows[i].id;
	                 }
	                 let inInfo = new EiInfo();
	                 inInfo.set("idList",idList);
	                 EiCommunicator.send("DFSB02", "updateShenHe", inInfo, {
	                  onSuccess : function(ei) {
	                   NotificationUtil("审核成功", "success");
	                   resultGrid.dataSource.page(1);
	                  }
	                 });
	                });
	                
	            	// 删除
	            	$("#REDELETE").on("click",function(e) {
	            		let checkRows = resultGrid.getCheckedRows();
						if(checkRows.length < 1){
							NotificationUtil("请选择要删除的行", "error");
							return;
						}
						let arr=[];
						for(let i = 0;i < checkRows.length;i++){
							 if(checkRows[i].statusCode == "-1"||checkRows[i].statusCode == "1"){            	  
			               	     NotificationUtil("不能删除已经审核或待审核的工单", "error");
			                   return;
							 }
							arr[i] = checkRows[i].id;
						}

						IPLAT.confirm({
							message: '<b>您确定要删除吗？</b>',
							okFn: function (e) {
								let info = new EiInfo();
								info.set("List",arr);
								EiCommunicator.send("DFSB02", "delete", info, {
									onSuccess : function(ei) {
										NotificationUtil("删除成功", "success");
										resultGrid.dataSource.page(1);
										window.parent['popDataWindow'].close();
										window.parent["resultGrid"].dataSource.query(1);
									}
								});
							},
							cancelFn: function (e) {
							}
						});

	            	});         	          
	               	               	          
	            }
			}		
	}
});