$(function() {

	$("#inqu_status-0-parentId").css("display", "none");
	
	IPLATUI.EFTree = {
		"tree01": {
			ROOT: {
				groupId: "roleRoot",
				groupCname: "根节点",
				leaf: true
			},
			
			select: function(e) {
				var _data = this.dataItem(e.node);
				var labelValue = _data.groupId;

				$("#roleId").val(labelValue);
				resultGrid.dataSource.page(1);
				
			},

		}

	};
	
	
});
