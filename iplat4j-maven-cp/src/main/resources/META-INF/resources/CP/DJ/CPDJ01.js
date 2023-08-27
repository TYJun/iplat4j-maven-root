$(function () {
	if (__ei.labor == "labor"){
		$("#labor").show()
	}

	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1)
	});

	$("#REQUERY").on("click", function (e) {
		$("#inqu_status-0-billNo").val("")
		IPLAT.EFSelect.value($("#inqu_status-0-statusCode"),"",true)
		$("#inqu_status-0-complaintName").val("")
		$("#inqu_status-0-complaintDept").val("")
		$("#inqu_status-0-complaintDateS").val("")
		$("#inqu_status-0-complaintDateE").val("")
		$("#inqu_status-0-complaintContent").val("")
		resultGrid.dataSource.page(1)
	});
})

	IPLATUI.EFGrid = {
		"result" : {
			loadComplete: function (e) {
				//新增按钮
				$("#ADD").on("click",function(e){
					popData(" ","add","");
				});
				
				/* 查看按钮 */
				$("#LOOK").on("click",function(e){
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 1){
						NotificationUtil("请先选择一条记录进行查看", "error")
					} else if (checkRows.length === 1){
						popDataWindow.setOptions({"title": "职工心声查看"});
						popData(checkRows[0].id, "see",checkRows[0].billNo);
					} else {
						NotificationUtil("请选择需要查看的记录", "error")
					}
				});

				/* 处理科室 */
				$("#DEAL").on("click",function (){
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 1){
						NotificationUtil("请先选择一条记录进行分配处理科室", "error")
					} else if (checkRows.length === 1){
						if (checkRows[0].statusCode == "待分配"){
							popDataWindow.setOptions({"title": "职工心声分配处理科室"});
							popData(checkRows[0].id, "deal",checkRows[0].billNo);
						} else {
							NotificationUtil("请先进行提交", "error")
						}
					} else {
						NotificationUtil("请选择需要分配处理科室的记录", "error")
					}
				})

				/* 编辑 */
				$("#EDIT").on("click", function() {
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 1){
						NotificationUtil("请先选择一条记录进行修改", "error")
					} else if (checkRows.length === 1){
						if (checkRows[0].statusCode == "新建"){
							popDataWindow.setOptions({"title": "职工心声编辑"});
							popData(checkRows[0].id, "edit",checkRows[0].billNo);
						} else if (checkRows[0].statusCode == "作废"){
							NotificationUtil("已作废工单不能编辑", "error")
						} else {
							NotificationUtil("已提交工单不能编辑", "error")

						}
					} else {
						NotificationUtil("请选择需要编辑的记录", "error")
					}
				});
				
				 /* 删除 */
				$("#DELETE").on("click",function(e){
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 1){
						NotificationUtil("请先选择一条记录进行删除", "error")
					} else if (checkRows.length === 1){
						if (checkRows[0].statusCode != "新建"){
							NotificationUtil("只能删除'新建'状态的工单", "error")
						} else {
							resultGrid.dataSource.delete(1);
						}
					} else {
						NotificationUtil("请选择需要删除的记录", "error")
					}
				});

				/* 撤回*/
				$("#REVOCATION").on("click",function(e){
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 1){
						NotificationUtil("请先选择一条记录进行撤回", "error")
						return
					} else if (checkRows.length === 1){
						if (checkRows[0].statusCode == "待分配"){
							var eiInfo = new EiInfo();
							eiInfo.set("billNo",checkRows[0].billNo);
							EiCommunicator.send("CPDJ01","revocation",eiInfo, {
								onSuccess : function(ei) {
									if(ei.getStatus() === -1){
										IPLAT.alert(ei.getMsg());
									}else{
										NotificationUtil("撤回成功", "success");
										window.parent['popDataWindow'].close();
										window.parent["resultGrid"].dataSource.page(1);
									}
								}
							})
						} else {
							NotificationUtil("请选择'待分配'的记录", "error")
							return
						}
					} else {
						NotificationUtil("请选择需要撤回的记录", "error")
						return
					}
				});

				/* 提交*/
				$("#EXAMINE").on("click",function(e){
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 1){
						NotificationUtil("请先选择一条记录进行提交", "error")
					} else if (checkRows.length === 1){
						if (checkRows[0].statusCode == "待分配"){
							NotificationUtil("请勿重复提交", "error")
						} else {
							var eiInfo = new EiInfo();
							eiInfo.set("id",checkRows[0].id);
							EiCommunicator.send("CPDJ01","examine",eiInfo, {
								onSuccess : function(ei) {
									if(ei.getStatus() === -1){
										IPLAT.alert(ei.getMsg());
									}else{
										NotificationUtil("提交成功", "success");
										window.parent['popDataWindow'].close();
										window.parent["resultGrid"].dataSource.page(1);
									}
								}
							})
						}
					} else {
						NotificationUtil("请选择需要提交的记录", "error")
					}
				});

				/* 作废*/
				$("#INVALID").on("click",function(e){
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 1){
						NotificationUtil("请先选择一条记录进行作废", "error")
					} else if (checkRows.length === 1){
						if (checkRows[0].statusCode == "作废"){
							NotificationUtil("请勿重复作废", "error")
						} else {
							var eiInfo = new EiInfo();
							eiInfo.set("id",checkRows[0].id);
							EiCommunicator.send("CPDJ01","invalid",eiInfo, {
								onSuccess : function(ei) {
									if(ei.getStatus() === -1){
										IPLAT.alert(ei.getMsg());
									}else{
										NotificationUtil("作废成功", "success");
										window.parent['popDataWindow'].close();
										window.parent["resultGrid"].dataSource.page(1);
									}
								}
							})
						}
					} else {
						NotificationUtil("请选择需要作废的记录", "error")
					}
				});
			},
		}
	}

	/*新增,修改,查看窗口 */
 	function popData(id,type,billNo) {
		var url = IPLATUI.CONTEXT_PATH + "/web/CPDJ0101?initLoad&id="+id +"&type="+type +"&billNo=" + billNo;

		var popData = $("#popData");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
				// setTimeout(function(){
				// 	if(type=="see"){
				// 		var frame = popData.find("iframe")[0].contentWindow.$("#SAVEPR").hide();
				// 	}
				// }, 600)
			}
		});
		// 打开工作流节点人员选择弹窗
		popDataWindow.open().center();
	}