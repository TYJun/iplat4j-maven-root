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
		$("#itemName").val("");
		resultGrid.dataSource.page(1);
    });

	IPLATUI.EFTree = {
		"menu": {
			ROOT: {id: "root", classifyName: "项目分类信息", hasChildren: true},
			/**
			 * 树节点内容点击触发回调函数（点击节点内容前展开收起符号不会触发）
			 */
			select: function (e) {
				var _data = this.dataItem(e.node);
				$("#typeId").val(_data.id);
				if($("#typeId").val()=="root"){
					$("#typeId").val("");
				}
				resultGrid.dataSource.page(1);
			},
		}
	}

	/**
	 * 加载表格
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
					window.parent.saveItem(checkRows);
					window.parent['popDataItemWindow'].close();
			    });
			}
		}
	};
	
});