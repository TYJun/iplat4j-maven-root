$(function() {

	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	
	$("#RESET").on("click", function (e) {
		$("#itemName").val("");
		resultGrid.dataSource.page(1);
    });

	/**
	 * 保养卡片分类树控件
	 * @type {{tree01: {select: IPLATUI.EFTree.tree01.select, ROOT: {label: string, text: string, leaf: boolean}}}}
	 */
	IPLATUI.EFTree = {
		"tree01": {
			ROOT: {label: "root", text: "项目分类", leaf: true},
			select: function(e) {
				var _data = this.dataItem(e.node);
				$("#moduleId").val(_data.label);
				resultGrid.dataSource.page(1);
			},
		}
	}

	
	IPLATUI.EFGrid = {
		"result": {
			loadComplete: function (grid) {
				$("#SUBMIT").on("click", function (e) {
					//选择数据保存到上级页面
					var checkRows = resultGrid.getCheckedRows();
					if(checkRows.length < 1){
						NotificationUtil("请至少选择一条数据", "error");
						return;
					}
					//window.parent.itemGrid.addRows(checkRows);
					window.parent.saveItem(checkRows);
					window.parent['popDataItemWindow'].close();
			    });
			}
		}
	};
	
});