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
					popData("", "add", "HISQ0101");
				});
				
				/* 宣传科审核按钮 */
				$("#LOOK").on("click",function(e){
					if (datagrid == null) {
						NotificationUtil("请选择需要审核的记录", "warning")
					} else {
						var checkRows = resultGrid.getCheckedRows()
						var status = checkRows[0].status;
						if(status == "01"){
							NotificationUtil("请先将医院标识申请提交", "warning")
						}else if(status == "05"){
							NotificationUtil("请您先审批，再进行设计提交", "warning")
						}else if(status == "07"){
							NotificationUtil("请您先审批，再进行科室确认审批", "warning")
						}else if(status == "08"){
							NotificationUtil("审核拒绝的工单不可提交设计", "warning")
						}else if(status == "09"){
							NotificationUtil("请您先审批，再进行制作提交", "warning")
						}else if(status == "11"){
							NotificationUtil("请您先审批，再进行安装提交", "warning")
						}else if(status == "09"){
							NotificationUtil("请您先联系审批科室审批，再进行验收提交", "warning")
						}else if(status == "10"){
							NotificationUtil("审核拒绝的工单不可提交设计", "warning")
						}else if(status == "11"){
							NotificationUtil("请您先审批，再进行制作提交", "warning")
						}else if(status == "12"){
							NotificationUtil("审核拒绝的工单不可提交设计", "warning")
						}else if(status == "13"){
							NotificationUtil("请您先完成安装确认，再联系科室进行验收", "warning")
						}else if(status == "14"){
							NotificationUtil("审核拒绝的工单不可提交设计", "warning")
						}else {
							popData(datagrid.id, "see","HISQ0103",status);
						}

					}
				});

				/* 宣传科设计提交按钮 */
				$("#LOOK1").on("click",function(e){
					if (datagrid == null) {
						NotificationUtil("请选择需要审核的记录", "warning")
					} else {
						var checkRows = resultGrid.getCheckedRows()
						var status = checkRows[0].status;
						if(status == "01"){
							NotificationUtil("请先将医院标识申请提交", "warning")
						}else if(status == "02"){
							NotificationUtil("请您先审批", "warning")
						}else if(status == "04"){
							NotificationUtil("审核拒绝的工单不可提交设计", "warning")
						}else if(status == "06"){
							NotificationUtil("审核拒绝的工单不可提交设计", "warning")
						}else if(status == "07"){
							NotificationUtil("请您先审批，再进行科室确认审批", "warning")
						}else if(status == "08"){
							NotificationUtil("审核拒绝的工单不可提交设计", "warning")
						}else if(status == "09"){
							NotificationUtil("请您先审批，再进行制作提交", "warning")
						}else if(status == "10"){
							NotificationUtil("审核拒绝的工单不可提交设计", "warning")
						}else if(status == "11"){
							NotificationUtil("请您先审批，再进行制作提交", "warning")
						}else if(status == "12"){
							NotificationUtil("审核拒绝的工单不可提交设计", "warning")
						}else if(status == "13"){
							NotificationUtil("请您先完成安装确认，再联系科室进行验收", "warning")
						}else if(status == "14"){
							NotificationUtil("审核拒绝的工单不可提交设计", "warning")
						}else {
							popData(datagrid.id, "see","HISQ0104",status);
						}

					}
				});

				/* 科室审核确认按钮 */
				$("#LOOK2").on("click",function(e){
					if (datagrid == null) {
						NotificationUtil("请选择需要审核的记录", "warning")
					} else {
						var checkRows = resultGrid.getCheckedRows()
						var status = checkRows[0].status;
						if(status == "01"){
							NotificationUtil("请先将医院标识申请提交", "warning")
						}else if(status == "02"){
							NotificationUtil("请先提交", "warning")
						}else if(status == "03"){
							NotificationUtil("请先审批", "warning")
						}else if(status == "04"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "06"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "08"){
							NotificationUtil("审核拒绝的工单不可提交设计", "warning")
						}else if(status == "09"){
							NotificationUtil("请您先审批，再进行制作提交", "warning")
						}else if(status == "10"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "11"){
							NotificationUtil("请您先审批，再进行安装提交", "warning")
						}else if(status == "12"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "13"){
							NotificationUtil("请您先完成安装确认，再联系科室进行验收", "warning")
						}else if(status == "14"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						} else {
							popData(datagrid.id, "see","HISQ0105",status);
						}

					}
				});

				/* 宣传科制作提交按钮 */
				$("#LOOK3").on("click",function(e){
					if (datagrid == null) {
						NotificationUtil("请选择需要审核的记录", "warning")
					} else {
						var checkRows = resultGrid.getCheckedRows()
						var status = checkRows[0].status;
						if(status == "01"){
							NotificationUtil("请先将医院标识申请提交", "warning")
						}else if(status == "02"){
							NotificationUtil("请先提交", "warning")
						}else if(status == "03"){
							NotificationUtil("请先上传设计后，再进行审批", "warning")
						}else if(status == "04"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "05"){
							NotificationUtil("请您先进行审批", "warning")
						}else if(status == "06"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "08"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "10"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "11"){
							NotificationUtil("请您先审批，再进行安装提交", "warning")
						}else if(status == "12"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "13"){
							NotificationUtil("请您先完成安装确认，再联系科室进行验收", "warning")
						}else if(status == "14"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						} else {
							popData(datagrid.id, "see","HISQ0106",status);
						}

					}
				});

				/* 宣传科安装提交按钮 */
				$("#LOOK4").on("click",function(e){
					if (datagrid == null) {
						NotificationUtil("请选择需要审核的记录", "warning")
					} else {
						var checkRows = resultGrid.getCheckedRows()
						var status = checkRows[0].status;
						if(status == "01"){
							NotificationUtil("请先将医院标识申请提交", "warning")
						}else if(status == "02"){
							NotificationUtil("请先提交", "warning")
						}else if(status == "03"){
							NotificationUtil("请先上传设计后，再进行审批", "warning")
						}else if(status == "04"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "05"){
							NotificationUtil("已完成设计提交不可重复提交", "warning")
						}else if(status == "06"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "07"){
							NotificationUtil("请您先审批，再进行科室确认审批", "warning")
						}else if(status == "08"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "10"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "12"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "13"){
							NotificationUtil("请您先完成安装确认，再联系科室进行验收", "warning")
						}else if(status == "14"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						} else {
							popData(datagrid.id, "see","HISQ0107",status);
						}

					}
				});

				/* 科室验收按钮 */
				$("#LOOK5").on("click",function(e){
					if (datagrid == null) {
						NotificationUtil("请选择需要审核的记录", "warning")
					} else {
						var checkRows = resultGrid.getCheckedRows()
						var status = checkRows[0].status;
						if(status == "01"){
							NotificationUtil("请先将医院标识申请提交", "warning")
						}else if(status == "02"){
							NotificationUtil("请先提交", "warning")
						}else if(status == "03"){
							NotificationUtil("请先上传设计后，再进行审批", "warning")
						}else if(status == "04"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "05"){
							NotificationUtil("已完成设计提交不可重复提交", "warning")
						}else if(status == "06"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "07"){
							NotificationUtil("请您先审批，再进行科室确认审批", "warning")
						}else if(status == "08"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "09"){
							NotificationUtil("请您先审批，再进行制作提交", "warning")
						}else if(status == "10"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "12"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						}else if(status == "14"){
							NotificationUtil("审核拒绝的工单不可提交", "warning")
						} else {
							popData(datagrid.id, "see","HISQ0108",status);
						}

					}
				});


				/* 编辑 */
				$("#EDIT").on("click", function() {
					if (datagrid == null) {
						NotificationUtil("请选择需要编辑的记录", "warning")
					} else {
						var checkRows = resultGrid.getCheckedRows()
						var status = checkRows[0].status;
						if(status > "02" ){
							NotificationUtil("已提交的工单不允许编辑", "warning")
						}
						else {
							popData(datagrid.id, "edit","HISQ0102",status);
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
						if(status == "01"){
                       if(!del()){
                       	return;
					   }
							var eiInfo = new EiInfo();
							var id = checkRows[0].id;
							eiInfo.set("id",id);
							eiInfo.set("status",status);
							EiCommunicator.send("HISQ01", "delete", eiInfo, {
								onSuccess : function(ei) {
									NotificationUtil(ei.getMsg(), "success");
									resultGrid.dataSource.page(1);
								}
							});
						}else {
							NotificationUtil("已提交的工单不允许删除", "warning")
						}
					}
				});

				/*撤回*/
				$("#WITHDRAW").on("click",function (e){
                    if(datagrid == null){
                    	    NotificationUtil("请选择需要提交的记录","warning")
					}else {
						var eiInfo = new EiInfo();
                    	eiInfo.set("id",datagrid.id)
						var checkRows = resultGrid.getCheckedRows()
						var status = checkRows[0].status;
                    	eiInfo.set("status",status);
                    	if( status == "02"){
							EiCommunicator.send("HISQ01","withdraw",eiInfo,{
								onSuccess : function (ei){
									if(ei.getStatus() === -1){
										IPLAT.alert(ei.getMsg());
									}else{
										NotificationUtil("撤回成功", "success");
										window.parent['popDataWindow'].close();
										window.parent["resultGrid"].dataSource.query(1);
									}
								}
							})
						} else {
                    		NotificationUtil("请选择待审核的工单", "warning")
						}

					}

				})
				 
				/* 提交*/
				$("#EXAMINE").on("click",function(e){
					if (datagrid == null) {
						//IPLAT.alert("请先选择一条记录进行审批");
						NotificationUtil("请选择需要提交的记录", "warning")
					} else {
						var checkRows = resultGrid.getCheckedRows()
						var status = checkRows[0].status;
						if(status == '03' || status == '04' || status == '05' || status == '06' || status == '07' || status == '08' || status == '09' || status == '10' || status == '11' || status == '12' || status == '13' || status == '14'){
							NotificationUtil("已审批的工单不可以再次提交", "warning")
						}else {
							var eiInfo = new EiInfo();
							eiInfo.set("id", datagrid.id);
							eiInfo.set("status",status);
							EiCommunicator.send("HISQ01", "examine", eiInfo, {
								onSuccess: function (ei) {
									if (ei.getStatus() === -1) {
										IPLAT.alert(ei.getMsg());
									} else {
										//IPLAT.alert("审批成功");
										NotificationUtil("提交成功", "success");
										window.parent['popDataWindow'].close();
										window.parent["resultGrid"].dataSource.query(1);
									}
								}
							})
						}
					}
				});
			},
		}
	}



	function del (){
		if(confirm("确定要删除吗？"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	$(function(){
	    /* 查询 */
		$("#QUERY").on("click",function(e){
			var recCreateTimeS = $("#recCreateTimeS").val();
			var recCreateTimeE = $("#recCreateTimeE").val();
			if (recCreateTimeS != "" && recCreateTimeE != "") {
				if(recCreateTimeS>recCreateTimeE){
					NotificationUtil("申请日期起不能大于申请日期止", "warning")
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
function popData(id, type, service ,status) {
	let url = IPLATUI.CONTEXT_PATH + "/web/"+ service +"?initLoad&id=" + id + "&type=" + type + "&status=" + status ;
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