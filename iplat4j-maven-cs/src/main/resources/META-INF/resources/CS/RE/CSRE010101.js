$(function() {
	// 查询按钮
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	// 重置按钮
	$("#RESET").on("click", function (e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
    });
	
	IPLATUI.EFGrid = {
			"result":{
				exportGrid:false,
				loadComplete: function (grid) {
					// 新增事项按钮.
					$("#ADDITEMDONE").click(function(){
						var list = resultGrid.getCheckedRows();
						// console.log(list);
						window.parent.resultItemGrid.addRows(list,false,false);
						window.parent['popDataItemWindow'].close();
					});
				}
			},
		}
	
});



IPLATUI.EFTree = {
	"tree01" : { // tree01对应jsp中EFTree的bindId属性
		ROOT : "root", // 虚拟节点不渲染，仅作为初始查询条件
		select : function(e) {
			var _data = this.dataItem(e.node);
			$("#typeId").val(_data.id);
			resultGrid.dataSource.page(1);
		}
	}
};

// 接收其父窗口传来的数值，并借由这些数值进行自己页面的查询.
function setData(e){
	// e: 为其父窗口传来的数值.
	console.log(e);
	var itemCodeList = "";
	// 判断是否有值.
	if (e.length > 0){
		// 对其中每一行进行遍历.
		for (var i = 0; i < e.length; i++) {
			// 将事项编码进行拼接并以","隔开。
			itemCodeList += e[i].itemCode + ",";
		}
		// 将拼接的字符串最后一位的","去除.
		itemCodeList = itemCodeList.substr(0, itemCodeList.length - 1);
		console.log(itemCodeList);
		IPLAT.EFInput.value($("#itemCodeList"),itemCodeList);
		// 自动执行一次查询按钮操作.
		$("#QUERY").click();
	}
}


