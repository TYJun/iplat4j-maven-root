$(function() {
	let validator = IPLAT.Validator({id: "inqu"}); let submitFlag = true;
	IPLATUI.EFGrid = {
		"result" : {
			pageable : false,
			exportGrid : false,
			toolbarConfig: 'see' === __ei.type ? {} : {
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false,
				buttons:[{
					name: "ADD",text: "新增",layout:"3",
					click: function () { popData(); }
				},{
					name: "DEL",text: "删除",layout:"3",
					click: function () {
						let checkRows = resultGrid.getCheckedRows();
						if (checkRows.length > 0) {
							resultGrid.removeRows(checkRows);
						} else {
							IPLAT.NotificationUtil("请选择需要删除的记录")
						}
					}
				},{
					name: "SAVE",text: "保存",layout:"3",
					click: function () {
						$("#SAVE .k-grid-SAVE").attr("disabled",true);
						setTimeout(function(){$("#SAVE .k-grid-SAVE").attr("disabled",false);},5000);
						if(!submitFlag) { return;}

						if(validator.validate()) {
							let checkRows = resultGrid.getDataItems();
							if (!checkRows && checkRows.length == 0) {
								IPLAT.NotificationUtil("物资批次列表不能为空");
								return;
							}
							//参数
							let eiInfo = new EiInfo();
							eiInfo.setByNode("inqu");
							eiInfo.set("list", checkRows);

							//发送请求
							submitFlag = false; //防止重复提交
							EiCommunicator.send("SIBF0101", "save", eiInfo, {
								onSuccess: function (ei) {
									if (ei.getStatus() == -1) {
										NotificationUtil(ei.getMsg(), "error");
										return;
									}

									NotificationUtil("保存成功", "success");
									window.parent['popDataWindow'].close();
									window.parent['resultGrid'].dataSource.page(1);
								},
								onFail: function (errorMsg, status, e) {
									NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
								}
							});
							submitFlag = true;
						} else {
							IPLAT.NotificationUtil("必填参数不能为空", "error");
						}
					}
				}]
			},
			onAdd: function (e) {
				e.items.forEach(item => item['scrapNum'] = item['enterNum']);
			}
		}
	}
});

//子页面弹窗
function popData() {
	let url = IPLATUI.CONTEXT_PATH + "/web/SIBF0103";
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

/**
 * 添加数据
 * @param checkRows
 * @param args
 */
function addRows(checkRows, args=['batchNo','wareHouseNo','matNum']) {
	let rows = resultGrid.getDataItems();
	if(rows && rows.length > 0 && args && args.length > 0) {
		for (let i = 0; i < checkRows.length; i++) {
			let model = checkRows[i], isExist = false;
			rows.forEach(e => {
				let isEqual = true;
				args.forEach(field => isEqual = isEqual && e[field] == model[field])
				isExist = isExist || isEqual;
			});
			if (!isExist) {
				resultGrid.addRows(model);
			}
		}
	} else {
		resultGrid.addRows(checkRows);
	}
}