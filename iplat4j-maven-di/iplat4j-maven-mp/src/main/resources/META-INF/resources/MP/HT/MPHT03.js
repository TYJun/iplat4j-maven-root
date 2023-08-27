$(function() {
	IPLATUI.EFGrid = {
		"result": {
			columns:[ {
				field: "payCost",
				readonly: true,
				template: "<span>#=payCost#元</span>",
			}],
			onCellClick: function (e) {
				e.preventDefault();
				if (e.td.context.className == "cell-url") {
					popData(e.model.id, "see", "MPHT0301");
					window["resultGrid"].unCheckAllRows();
				}
			},
			loadComplete: function (e) {
				//新增按钮
				$("#ADD").on("click", function (e) {
					popData("", "add", "MPHT0301");
				});

				/* 编辑 */
				$("#EDIT").on("click", function (e) {
					let checkRows = resultGrid.getCheckedRows();
					if (checkRows.length < 1) {
						NotificationUtil("请选择需要编辑的记录", "error");
					} else if (checkRows[0].statusCode != "01") {
						NotificationUtil("申请付款状态的合同才可编辑", "error");
					} else {
						popDataWindow.setOptions({"title": "付款信息"});
						popData(checkRows[0].id, "edit", "MPHT0301");
					}
				});

				/*删除*/
				$("#DEL").on("click", function (e) {
					let checkRows = resultGrid.getCheckedRows();
					if (checkRows.length < 1) {
						NotificationUtil("请选择需要删除的记录", "error")
					} else if (checkRows[0].statusCode != "01") {
						NotificationUtil("付款完成的工单不可删除", "error")
					} else {
						IPLAT.confirm({
							message: '<b>您确定要删除吗？</b>',
							okFn: function (e) {
								let eiInfo = new EiInfo();
								eiInfo.set("id", checkRows[0].id);
								EiCommunicator.send("MPHT03", "delete", eiInfo, {
									onSuccess: function (ei) {
										if (ei.getStatus() == -1) {
											NotificationUtil(ei.getMsg(), "error");
											return;
										}
										NotificationUtil("删除成功");
										window["resultGrid"].dataSource.page(1);
									}
								});
							},
							cancelFn: function (e) {
							}
						})
					}
				});

				/* 付款按钮 */
				$("#PAYMENT").on("click", function (e) {
					let checkRows = resultGrid.getCheckedRows();
					if (checkRows.length < 1) {
						NotificationUtil("请选择需要付款完成的记录", "error");
					} else if (checkRows[0].statusCode != "01") {
						NotificationUtil("已付款完成的记录无需再次付款", "error");
					} else {
						IPLAT.confirm({
							message: '<b>您确定要完成付款吗？</b>',
							okFn: function (e) {
								let eiInfo = new EiInfo();
								eiInfo.set("id", checkRows[0].id);
								EiCommunicator.send("MPHT03", "payment", eiInfo, {
									onSuccess: function (ei) {
										if (ei.getStatus() == -1) {
											NotificationUtil(ei.getMsg(), "error");
											return;
										}
										NotificationUtil("付款成功");
										window["resultGrid"].dataSource.page(1);
									}
								});
							},
							cancelFn: function (e) {
							}
						})
					}
				});
			}
		}
	}

	//查询
	$("#QUERY").on("click",function(e){
		resultGrid.dataSource.page(1);
	});

	//重置
	$("#REQUERY").on("click",function(e){
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

});

/**
 * 打开子页面
 * @param id
 * @param type
 * @param service
 */
function popData(id, type, service) {
	let url = IPLATUI.CONTEXT_PATH + "/web/"+ service +"?initLoad&inqu_status-0-id=" + id + "&type=" + type;
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

