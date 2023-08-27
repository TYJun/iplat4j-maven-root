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
		loadComplete: function (grid) {

		},
		//页面点击事件获取选中行id
		onCellClick : function(e) {
			e.preventDefault();
			if (!e.fake) {
				datagrid = null;
				var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
				datagrid = model;
			}
			if (e.field === "batchNo") {
				popData(datagrid.batchNo);
			}
		}
	}
}

function popData(batchNo) {
	var url = IPLATUI.CONTEXT_PATH + "/web/SQZH02?initLoad&batchNo=" + batchNo;
	var popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	// 打开工作流节点人员选择弹窗
	popDataWindow.open().center();
}


