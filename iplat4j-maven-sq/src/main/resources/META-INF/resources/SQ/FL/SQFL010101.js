//page参数
var datagrid = null;
var id = null;
var arr = [];

$(function() {
	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});
	//查询方法
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	});
});
IPLATUI.EFGrid = {
	"result": {
		onCheckRow : function(e) {
			if (!e.fake) {
				var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
				datagrid = model;
				id = datagrid.id;
				arr.push(id);
			}
		},
		loadComplete: function (grid) {
			//确定
			$("#ADD").on("click", function (e) {
				var checkRows = resultGrid.getCheckedRows();
				if (checkRows.length==0) {
					IPLAT.alert("请选择需要分组的人员");
					return;
				}
				else{
					var grid = $("#ef_grid_result").data("kendoGrid");
					var data = grid.getCheckedRows();// 获取选中行数据
					window.parent.save(data);
				}
			});
		},
	}
}