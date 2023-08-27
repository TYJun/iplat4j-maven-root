$(function() {

	$("#inqu_status-0-parentId").css("display", "none");
	
	IPLATUI.EFTree = {
		"tree01": {
			ROOT: {
				label: "root",
				deptName: "根节点",
				leaf: true
			},
			
			select: function(e) {
				var _data = this.dataItem(e.node);
				var length = _data.children._data.length;
				var labelValue = _data.label;
				var typeValue = _data.type;
				var deptName = _data.deptName;
				$("#parentId").val(labelValue);
				$("#parentName").val(deptName);
				var s = $("#inqu_status-0-parentId").val();
				resultGrid.dataSource.page(1);
				if ($("#parentId").val() == "root"){
					// 如果点击根节点则视为全局搜索，去除 parentId
					$("#parentId").attr("value", "");
				}
			},

		}

	};
});
