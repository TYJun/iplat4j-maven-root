$(function() {
	IPLATUI.EFGrid = {
		"result" : {
			onCellClick : function(e) {
				if (e.field === "scrapNo") {
					popData(e.model['id'], "see");
					// resultGrid.unCheckAllRows();
				}
			},
			pageable: {
				pageSize: 20,
				pageSizes: [10, 20, 50, 100]
			},
			loadComplete:function (e) {
				//新增
				$("#ADD").on("click", function(e) { popData('', "add"); });

				//编辑
				$("#EDIT").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择需要编辑记录","error");
					} else if ("01" != checkRows[0].statusCode){
						NotificationUtil("记录已提交无法编辑","error");
					}  else{
						popData(checkRows[0].id, "edit");
					}
				});
				
				//删除
				$("#DEL").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择需要删除的记录","error");
					} else if ("01" != checkRows[0].statusCode){
						NotificationUtil("记录已提交，无法删除","error");
					} else{
						IPLAT.confirm({
							message: '<b>确定要删除吗？</b>',
							okFn: function (e) {
								let eiInfo = new EiInfo();
								eiInfo.set("id", checkRows[0].id)
								EiCommunicator.send("SIBF01", "delete", eiInfo, {
									onSuccess : function(ei) {
										NotificationUtil("删除成功");
										window['popDataWindow'].close();
										window["resultGrid"].dataSource.page(1);
									}
								})
							},
							cancelFn: function (e) {
							}
						})
					}
				});

				//提交
				$("#SUBMIT").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择需要提交的记录","error");
					} else if ("01" != checkRows[0].statusCode){
						NotificationUtil("记录已提交，无需再提交","error");
					} else{
						IPLAT.confirm({
							message: '<b>提交后无法编辑,确定要提交吗？</b>',
							okFn: function (e) {
								let eiInfo = new EiInfo();
								eiInfo.set("id", checkRows[0].id)
								EiCommunicator.send("SIBF01", "submit", eiInfo, {
									onSuccess : function(ei) {
										NotificationUtil("提交成功");
										window['popDataWindow'].close();
										window["resultGrid"].dataSource.page(1);
									}
								})
							},
							cancelFn: function (e) {
							}
						})
					}
				});

				//撤回
				$("#BACK").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择需要撤回的记录","error");
					} else if ("10" != checkRows[0].statusCode){
						NotificationUtil("记录为提交或无法撤回","error");
					} else{
						let eiInfo = new EiInfo();
						eiInfo.set("id", checkRows[0].id)
						EiCommunicator.send("SIBF01", "back", eiInfo, {
							onSuccess : function(ei) {
								NotificationUtil("撤回成功");
								window['popDataWindow'].close();
								window["resultGrid"].dataSource.page(1);
							}
						})
					}
				});

				//审核
				$("#CONFIRM").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择需要审核记录","error");
					} else if ("10" != checkRows[0].statusCode){
						NotificationUtil("记录未提交,无法审核","error");
					}  else{
						popData(checkRows[0].id, "confirm");
					}
				});
			}
		}
	}

	/**回车键查询**/
	keydown("inqu", "QUERY");
	
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});

	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resetTime(false);
		resultGrid.dataSource.page(1);
	});
});

//子页面弹窗
function popData(id, type) {
	let url = IPLATUI.CONTEXT_PATH;
	if("confirm" === type) {
		url = url + "/web/SIBF0102?initLoad&inqu_status-0-id="+ id;
	} else {
		url = url + "/web/SIBF0101?initLoad&inqu_status-0-id="+ id + "&type=" + type;
	}
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