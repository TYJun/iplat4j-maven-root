var datagrid = null;
$(function() {
	console.log(__ei);
	console.log(__ei.role);
	if (__ei.role == 'DMSPR_XSSS' || __ei.role == 'DMZSR'){
		$("#hiddenDiv").hide();
	}
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.query(1);
	});

	// 重置按钮.
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			},
			loadComplete: function (grid) {
				// 退宿审核通过按钮
				$("#PASSAPPLY").on("click", function(e) {
					console.log("退宿审核通过");
					// 获取选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					var idList = "";
					var manIdList = "";
					var roomIdList = "";
					// 判断是否有选中行.
					if (checkRows.length > 0) {
						for (var i = 0; i < checkRows.length; i++) {
							// 当遍历最后一个值时，省略最后的"，"
							if (i == checkRows.length - 1) {
								idList += checkRows[i]["id"];
								manIdList += checkRows[i]["manId"];
								roomIdList += checkRows[i]["roomId"];
							} else {
								idList += checkRows[i]["id"] + ',';
								manIdList += checkRows[i]["manId"] + ',';
								roomIdList += checkRows[i]["roomId"] + ',';
							}
						}
						IPLAT.confirm({
							message: '<b>将会通过该员工的退宿申请！</br><font color="red">是否确定？</font></b>',
							okFn: function (e) {
								//提交数据
								var eiInfo = new EiInfo();
								eiInfo.set("idList", idList);
								eiInfo.set("manIdList", manIdList);
								eiInfo.set("roomIdList", roomIdList);
								EiCommunicator.send("DMTS02", "batchUpdate", eiInfo, {
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
						NotificationUtil("请选择想要通过的员工退宿申请", "warning");
					}
				});

				// 拒绝申请按钮
				$("#REFUSEAPPLY").on("click", function(e) {
					console.log("拒绝申请");
					// 获取选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					var manIdList = "";
					// 判断是否有选中行.
					if (checkRows.length > 0) {
						for (var i = 0; i < checkRows.length; i++) {
							// 当遍历最后一个值时，省略最后的"，"
							if (i == checkRows.length - 1) {
								manIdList += checkRows[i]["manId"];
							} else {
								manIdList += checkRows[i]["manId"] + ',';
							}
						}
						var statusCode = "04";
						IPLAT.confirm({
							message: '<b>将会拒绝所勾选的退宿申请！</br><font color="red">是否确定？</font></b>',
							okFn: function (e) {
								//提交数据
								var eiInfo = new EiInfo();
								eiInfo.set("manIdList", manIdList);
								eiInfo.set("statusCode", statusCode);
								EiCommunicator.send("DMSH01", "batchUpdateStatusCode", eiInfo, {
									onSuccess : function(ei) {
										IPLAT.NotificationUtil(ei.getMsg());
										setTimeout(function() {
											window.parent.location.reload()
										}, 600);
									}
								});
							},
							title: '提醒'
						});
					} else {
						NotificationUtil("请选择想要拒绝的退宿申请", "warning");
					}
				});

				// 清单下载按钮
				$("#DOWNLOADLIST").on("click", function(e) {
					console.log("检查清单");
					// 获取选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					// 判断是否有选中行.
					if (checkRows.length > 0) {
						var id = checkRows[0]["id"];
						var url = IPLATUI.CONTEXT_PATH + "/web/DMQD01?initLoad&id="+id;
						var popData = $("#popData");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataWindow.open().center();
					}else {
						NotificationUtil("请选择想要检查清单的退宿申请", "warning");
					}

					// console.log("清单下载");
					// //获取文件路径
					// var url = IPLATUI.CONTEXT_PATH +  "/" + "DM/template/dormslist.xls";
					// const a = document.createElement('a'); // 创建a标签
					// a.setAttribute('download', '');// download属性
					// a.setAttribute('href', url);// href链接
					// a.click();// 自执行点击事件
				});


				// 结算退宿按钮
				$("#SETTLEAPPLY").on("click", function(e) {
					console.log("结算退宿");
					// 获取选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					// 判断是否有选中行.
					if (checkRows.length > 1){
						NotificationUtil("结算退宿请勿勾选多个退宿申请信息", "warning");
						return;
					}else if (checkRows.length < 1){
						NotificationUtil("请选择想要结算退宿的退宿申请", "warning");
						return;
					}
					var id = checkRows[0]["id"];
					var url = IPLATUI.CONTEXT_PATH + "/web/DMTS0201?initLoad&id="+id;
					var popData = $("#popDataSettle");
					popData.data("kendoWindow").setOptions({
						open : function() {
							popData.data("kendoWindow").refresh({
								url : url
							});
						}
					});
					popDataSettleWindow.open().center();
				});

			}
		}
	}

});





	



	
	
