$(function() {

	$("#inqu_status-0-parentId").css("display", "none");
	
	IPLATUI.EFTree = {
		"tree01": {
			ROOT: {
				label: "root",
				roleName: "根节点",
				leaf: true
			},
			
			select: function(e) {
				var _data = this.dataItem(e.node);
				var length = _data.children._data.length;
				var labelValue = _data.label;
				var typeValue = _data.type;

				$("#roleIdHidden").val(_data.label);
				$("#roleNameHidden").val(_data.roleName);
				$("#roleId").val(labelValue);
				resultGrid.dataSource.page(1);
				
			},

		}

	};
});
