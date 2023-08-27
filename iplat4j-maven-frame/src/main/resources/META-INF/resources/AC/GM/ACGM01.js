$(function() {

	$("#inqu_status-0-parentId").css("display", "none");
	
	IPLATUI.EFTree = {
		"tree01": {
			ROOT: {
				label: "root",
				matClassName: "根节点",
				leaf: true
			},
			
			select: function(e) {
				var _data = this.dataItem(e.node);
				var length = _data.children._data.length;
				var labelValue = _data.label;
				var matClassName = _data.matClassName;
				var typeValue = _data.type;

				$("#matClassId").val(labelValue);
				var s = $("#inqu_status-0-parentId").val();
				resultGrid.dataSource.page(1);
				if ($("#matClassId").val() == "root"){
					// 如果点击根节点则视为全局搜索，去除 parentId
					$("#matClassId").attr("value", "");
				}
			},

		}

	};
});
