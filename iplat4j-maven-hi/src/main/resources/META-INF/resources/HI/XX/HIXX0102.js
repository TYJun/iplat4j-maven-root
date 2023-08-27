$(function() {
	/**
	 * @type {{select: IPLATUI.EFTree.tree02.select, ROOT: string}}
	 */

	/**
	 * 表格控件
	 * @type {{result: {loadComplete: IPLATUI.EFGrid.result.loadComplete, toolbarConfig: {add: boolean, cancel: boolean, buttons: [{layout: string, name: string, text: string, click: click}], hidden: boolean, save: boolean, delete: boolean}}}}
	 */
	IPLATUI.EFGrid = {
		"result": {
			toolbarConfig: {
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false, cancel: false, save: false, 'delete': false,
				buttons: [{
					name: "ef_popup_grid_fillback", text: "确定", layout: "3",
					click: function () {
						let checkRows = resultGrid.getCheckedRows();
						let model = checkRows[0];
						if (model) {
							if (parent && parent.Message) {
								parent.Message.send("item","itemChoose",model);
							}
						} else {
							NotificationUtil("请选择医院标识申请", "warning");
						}
					}
				}]
			},
			loadComplete: function (e) {
				$("#queryItem").on("click", function () {
					resultGrid.dataSource.page(1);
				})
			}
		}
	}
})

//父页面方法
/*
Message.on("item","itemChoose",function(param) {
	$("#actualItemName").val(param['itemNum']);
	$("#actualItemName_textField").val(param['itemName']);
	//关闭窗口
	let windowFrame = $("#ef_popup_item_iframe").data("kendoWindow");
	windowFrame.close();
});*/

/*
Message.on("item","itemChoose",function(param) {
	$("#itemName").val(param['itemNum']);
	$("#itemName_textField").val(param['itemName']);
	IPLAT.EFTreeInput.setAllFields( $("#itemTypeNum") , param['itemTypeNum'] ,param['itemTypeName']);

	//关闭窗口
	let windowFrame = $("#ef_popup_item_iframe").data("kendoWindow");
	windowFrame.close();
});*/
