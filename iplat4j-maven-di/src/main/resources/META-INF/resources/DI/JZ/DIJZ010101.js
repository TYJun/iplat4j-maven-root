$(function() {
	/**
	 * 查询
	 */
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });

	/**
	 * 重置
	 */
	$("#RESET").on("click", function (e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
    });

	/**
	 * 设备分类树
	 * @type {{menu: {select: IPLATUI.EFTree.tree01.select, ROOT: string}}}
	 */
	IPLATUI.EFTree = {
		"menu" : { //tree01对应jsp中EFTree的bindId属性
			ROOT: "root",
			select: function (e) {
				var _data = this.dataItem(e.node);
				$("#machineTypeId").val(_data.id);
				resultGrid.dataSource.page(1);
			}
		}
	}


	/**
	 * 表格加载
	 * @type {{result: {loadComplete: IPLATUI.EFGrid.result.loadComplete}}}
	 */
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
					//window.parent.deviceGrid.addRows(checkRows);
					window.parent.save(checkRows);
					window.parent['popDataDeviceWindow'].close();
			    });
			}
		}
	};
	
});