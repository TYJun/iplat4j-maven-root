$(function() {
	//弹窗页面保存
	$("#SAVEPR").on("click", function(ei) {
		var validator = IPLAT.Validator({
			id: "result"
		});
		if (validator.validate()) {
			var eiInfo = new EiInfo();
			eiInfo.setByNode("result");
			//获取tab数据
			var content = resultAGrid.getDataItems();
			eiInfo.set("content", content)

			EiCommunicator.send("CMTK0101", "saveResult", eiInfo, {
				onSuccess : function(ei) {
					closeCurrentWindow();
					IPLAT.NotificationUtil(ei.msg)
					window.parent["resultGrid"].dataSource.page(1);
					window.parent.datagrid = null;
				}
			})
		}
	});
	$("#CANCEL").on("click", function() {
		closeCurrentWindow();
	});

	/* 渲染分页 */
	IPLATUI.EFTab = {
			"tab-tab_grid" : {
				select : function(e) {
					var grid = $(e.contentElement).find("div[data-role='grid']").data("kendoGrid")
					if (grid.getDataItems().length === 0) {
						grid.dataSource.page(1);
					}
				}
			}
	}

	var validator = IPLAT.Validator({
		id : "result"
	});//开启校验
	IPLATUI.EFGrid = {
			"resultA":{
//				pageable : false,
//				exportGrid : false,
				toolbarConfig:{
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[
						{
							name: "ADDCED",text: "新增",layout:"3",
							click: function () {
								resultAGrid.addRow()
							}
						},
						{
							name: "DELCED",text: "删除",layout: "3",
							click: function () {
								var checkRows = resultAGrid.getCheckedRows();
								if (checkRows.length > 0) {
									resultAGrid.removeRows(checkRows);
								} else {
									IPLAT.NotificationUtil("请选择需要删除的合同条款内容")
								}
							}
						}
						]
				}, 
			},
	}
})
//关闭窗口
function closeCurrentWindow() {
	window.parent['popDataWindow'].close();
}