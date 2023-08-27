var datagrid = null;

$(function() {
	console.log(__ei);
	console.log(__ei.role);
	if (__ei.role == 'DMSPR_XSSS' || __ei.role == 'DMZSR'){
		$("#hiddenDiv").hide();
	}
	//查询
	$("#QUERY").on("click", function(e) {
		// 设置状态初始值。
		IPLAT.EFInput.value( $("#statusCode") , '03');
		resultGrid.dataSource.query(1);
	});

	// 重置按钮.
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

	// 确认入住进行批量化修改
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

				IPLAT.confirm({
					message: '<b>不得私自改用、损坏宿舍固定资产，如有损坏，按原价赔偿。退房时按入住清单清点物品，如有遗失，按原价赔偿！</br><font color="red">是否确定？</font></b>',
					okFn: function (e) {

						//确认入住按钮
						$("#CONFIRM").on("click", function(e) {
							console.log("确认入住");
							var checkRows = resultGrid.getCheckedRows();
							var idList = "";
							var manIdList = "";
							var manNoList = "";
							if (checkRows.length < 1) {
								NotificationUtil("请选择一条/多条入住信息进行确认", "warning");
								return;
							}
							var roomId = checkRows[0]["roomId"];

							for (var i = 0; i < checkRows.length; i++) {
								// 当遍历最后一个值时，省略最后的"，"
								if (i == checkRows.length - 1) {
									idList += checkRows[i]["id"];
									manIdList += checkRows[i]["manId"];
									manNoList += checkRows[i]["manNo"];
								} else {
									idList += checkRows[i]["id"] + ',';
									manIdList += checkRows[i]["manId"] + ',';
									manNoList += checkRows[i]["manNo"] + ',';
								}
							}
							console.log(idList);
							console.log(manIdList);

							IPLAT.confirm({
								message: '<b>入住有效期最多一年，请在到期一个月前及时去物业管理科办理退宿手续！</br><font color="red">是否确定？</font></b>',
								okFn: function (e) {
									popDatashows(roomId);
									setTimeout(
									IPLAT.confirm({
										message: '<b>是否核对完物资信息！</br><font color="red">是否确定？</font></b>',
										okFn: function (e) {
											popDatashowsWindow.close();
											var url = IPLATUI.CONTEXT_PATH + "/web/DMQR0101?initLoad&idList="+idList+"&manIdList=" + manIdList+"&manNoList=" + manNoList;
											var popData = $("#popData");
											popData.data("kendoWindow").setOptions({
												open : function() {
													popData.data("kendoWindow").refresh({
														url : url
													});
												}
											});
											popDataWindow.open().center();
										},
										title: '提醒'
									})
								,5000);
								},
								title: '提醒'
							});

						});

					},
					title: '提醒'
				});



				// 编辑实际费用按钮.
				$("#EDITACTUALFEE").on("click", function(e) {
					console.log("编辑实际费用按钮");
					var eiInfo = new EiInfo();
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 0 && checkRows.length == 1) {
						var id = checkRows[0]["id"];
						var url = IPLATUI.CONTEXT_PATH + "/web/DMQR0102?initLoad&id="+id;
						var popData = $("#popDataEdit");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataEditWindow.open().center();
					} else if(checkRows.length > 1){
						NotificationUtil("该编辑实际费用按钮只能单选!", "warning");
						return;
					} else {
						NotificationUtil("请选择想要编辑实际费用的信息", "warning");
						return;
					}
				});

			}
		}
	}

});

// 查看详情
function popDatashows(roomId) {
	var url = IPLATUI.CONTEXT_PATH + "/web/DMXX0103?initLoad&roomId=" + roomId;
	var popData = $("#popDatashows");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	// 打开工作流节点人员选择弹窗
	popDatashowsWindow.open().center();
}





	



	
	
