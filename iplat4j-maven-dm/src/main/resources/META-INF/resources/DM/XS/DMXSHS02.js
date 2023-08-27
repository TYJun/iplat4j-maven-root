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
				// 换宿审核通过按钮
				$("#PASSAPPLY").on("click", function(e) {
					console.log("换宿审核通过");
					// 获取选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					// 判断是否有选中行.
					if (checkRows.length > 0) {
						// var id = checkRows[0]["id"];
						// var manId = checkRows[0]["manId"];
						// var roomId = checkRows[0]["roomId"];
						// var bedNo = checkRows[0]["bedNo"];
						// var changeRoomNote = checkRows[0]["changeRoomNote"];
						// var applyChangeDate = checkRows[0]["applyChangeDate"];
						// var actualInDate = checkRows[0]["actualInDate"];
						// var remainingBedNum = checkRows[0]["remainingBedNum"];
						IPLAT.confirm({
							message: '<b>将会通过所勾选的员工的换宿申请！</br><font color="red">是否确定？</font></b>',
							okFn: function (e) {
								//提交数据
								var eiInfo = new EiInfo();
								// eiInfo.set("id", id);
								// eiInfo.set("manId", manId);
								// eiInfo.set("roomId", roomId);
								// eiInfo.set("bedNo", bedNo);
								// eiInfo.set("changeRoomNote", changeRoomNote);
								// eiInfo.set("applyChangeDate", applyChangeDate);
								// eiInfo.set("actualInDate", actualInDate);
								// eiInfo.set("remainingBedNum", remainingBedNum);
								eiInfo.set("row", checkRows);
								EiCommunicator.send("DMHS02", "batchUpdate", eiInfo, {
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
						NotificationUtil("请选择想要通过的员工换宿申请", "warning")
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
							message: '<b>将会拒绝所勾选的换宿申请！</br><font color="red">是否确定？</font></b>',
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
						NotificationUtil("请选择想要拒绝的换宿申请", "warning")
					}
				});


				// 直接分配按钮
				$("#DIRECTALLOT").on("click", function(e) {
					console.log("直接分配");
					// 获取行数据
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 1){
						NotificationUtil("直接分配请勿勾选多条信息", "warning");
						return;
					}else if (checkRows.length < 1){
						NotificationUtil("请勾选想要进行直接分配的换宿信息", "warning");
						return;
					}
					var manId = checkRows[0].manId;
					var url = IPLATUI.CONTEXT_PATH + "/web/DMHS0201?initLoad&manId=" + manId;
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

			}
		}
	}

});





	



	
	
