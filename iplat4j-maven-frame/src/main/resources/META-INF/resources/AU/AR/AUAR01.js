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

				$("#parentId").val(labelValue);
				var s = $("#inqu_status-0-parentId").val();
				resultGrid.dataSource.page(1);
				$("#parentId").attr("value", "");
			},

		}

	};
});
