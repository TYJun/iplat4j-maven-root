$(function () {
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	
	$("#REQUERY").on("click", function (e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
    });

	IPLATUI.EFGrid = {
		"result": {
			toolbarConfig: {
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false, cancel: false, save: false, 'delete': false,
				buttons: [
					{
						name : "enter",
						text : "录入",
						layout : "3",
						click : function() {
							popDataWindow.setOptions({"title":"资产闲置录入"});
							fixedAssetsWindow("enter","");
						}
					},
					{
						name : "edit",
						text : "编辑",
						layout : "3",
						click : function() {
							console.log(checkRows())
							if (checkRows()){
								if ("录入" == checkRows().statusCodeMean) {
									popDataWindow.setOptions({"title":"资产闲置编辑"});
									fixedAssetsWindow("edit",checkRows().faIdleId,"","");
								}else {
									NotificationUtil("已确认的闲置单无法编辑", "warning");
								}
							}
						}
					},
					{
						name : "remove",
						text : "删除",
						layout : "3",
						click : function() {
							if (checkRows()){
								if ("录入" == checkRows().statusCodeMean) {
									IPLAT.confirm({
										message: '<b>将删除所勾选的资产闲置信息！</br><font color="red">是否确定？</font></b>',
										okFn: function (e) {
											var eiInfo = new EiInfo();
											eiInfo.set("faIdleId", checkRows().faIdleId);
											eiInfo.set("faInfoId", checkRows().goodsId);
											eiInfo.set("archiveFlag", checkRows().archiveFlag);
											EiCommunicator.send("FAXZ0101", "removeFaIdleInfo", eiInfo, {
												onSuccess: function (ei) {
													resultGrid.dataSource.page(1);
												}
											});
										},
									});
								} else {
									NotificationUtil("已确认的闲置单无法删除", "warning");
								}
							}
						}
					},
					// {
					// 	name : "confirm",
					// 	text : "确认",
					// 	layout : "3",
					// 	click : function() {
					// 		if (checkRows()){
					// 			if ("录入" == checkRows().statusCodeMean) {
					// 				IPLAT.confirm({
					// 					message: '<b>将删除所勾选的资产闲置信息！</br><font color="red">是否确定？</font></b>',
					// 					okFn: function (e) {
					// 						var eiInfo = new EiInfo();
					// 						eiInfo.set("faIdleId", checkRows().faIdleId);
					// 						eiInfo.set("faInfoId", checkRows().goodsId);
					// 						eiInfo.set("archiveFlag", checkRows().archiveFlag);
					// 						EiCommunicator.send("FAXZ0101", "confirmFaIdleInfo", eiInfo, {
					// 							onSuccess: function (ei) {
					// 								resultGrid.dataSource.page(1);
					// 							}
					// 						});
					// 					},
					// 				});
					// 			} else {
					// 				NotificationUtil("已确认的闲置单无需再次确认", "warning");
					// 			}
					// 		}
					// 	}
					// },
				]
			},
			loadComplete: function (grid) {
				$("#APPROVAL").on("click", function (e) {
					if (checkRows()){
						if ("录入" == checkRows().statusCodeMean) {
							IPLAT.confirm({
								message: '<b>将审批所勾选的资产闲置信息！</br><font color="red">是否确定？</font></b>',
								okFn: function (e) {
									var eiInfo = new EiInfo();
									eiInfo.set("faIdleId", checkRows().faIdleId);
									eiInfo.set("faInfoId", checkRows().goodsId);
									eiInfo.set("archiveFlag", checkRows().archiveFlag);
									EiCommunicator.send("FAXZ0101", "confirmFaIdleInfo", eiInfo, {
										onSuccess: function (ei) {
											resultGrid.dataSource.page(1);
										}
									});
								},
							});
						} else {
							NotificationUtil("已确认的闲置单无需再次确认", "warning");
						}
					}
				});

				// 编辑
				$("#EDITINFO").on("click", function (e) {
					console.log("编辑");
					// 获取选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					// 判断是否有选中行(单行/多行/未选行均不一样).
					if (checkRows.length > 0) {
						if (checkRows.length == 1){
							var statusCode = checkRows[0]["statusCode"];
							if (statusCode == 1){
								NotificationUtil("已确认的闲置单无法编辑", "warning");
								return;
							}
							var id = checkRows[0]["id"];
							console.log(id);
							var url = IPLATUI.CONTEXT_PATH + "/web/FAXZ0102?initLoad&id=" + id;
							var popData = $("#popDataEdit");
							popData.data("kendoWindow").setOptions({
								open: function () {
									popData.data("kendoWindow").refresh({
										url: url
									});
								}
							});
							popDataEditWindow.open().center();
						}else {
							NotificationUtil("请选择一条需要编辑的闲置信息", "warning");
							return;
						}
					} else {
						NotificationUtil("请选择需要编辑的闲置信息", "warning");
						return;
					}
				});

				// 确认按钮
				$("#CONFIRM").on("click", function(e) {
					console.log("确认按钮");
					// 获取选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					// 判断是否有选中行.
					if (checkRows.length > 0) {
						var arr=[];
						var arr1=[];
						for(var i = 0;i < checkRows.length;i++){
							arr[i] = checkRows[i]["id"];
							arr1[i] = checkRows[i]["goodsId"];
						}
						IPLAT.confirm({
							message: '<b>将确认所勾选的资产闲置信息！</br><font color="red">是否确定？</font></b>',
							okFn: function (e) {
								//提交数据
								var eiInfo = new EiInfo();
								eiInfo.set("idList", arr);
								eiInfo.set("goodsIdList", arr1);
								EiCommunicator.send("FAXZ0102", "updateXZStatus", eiInfo, {
									onSuccess : function(ei) {
										IPLAT.NotificationUtil(ei.msg);
										setTimeout(function() {
											window.parent.location.reload()
										}, 600);
									}
								});
							},
							title: '提醒'
						});
					} else {
						NotificationUtil("请选择需要确认的闲置信息", "warning");
					}
				});

			}
		}
	}
});

// 自定义资产弹窗
function fixedAssetsWindow(type,id,typeId,typeName){
	var url = IPLATUI.CONTEXT_PATH + "/web/FAXZ0101?initLoad&faIdleId="+id +"&type="+type+"&typeId="+typeId+"&typeName="+typeName;
	var popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	// 新窗口打开居中
	popDataWindow.open().center();
}

function checkRows(){
	const checkRows = resultGrid.getCheckedRows();
	if (checkRows.length < 1){
		NotificationUtil("请先选择一条记录","warning");
		return false;
	}else {
		return checkRows[0];
	}
}