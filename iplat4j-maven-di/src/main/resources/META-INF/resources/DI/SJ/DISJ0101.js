var validator = IPLAT.Validator({id: "EDIT"});//开启校验
$(function() {

	//点击保存保养实绩
	$("#SUBMIT").click(function() {
		var info = new EiInfo();
		//列表数据
		var itemList = itemGrid.getDataItems();

		info.set("itemList", itemList);

		EiCommunicator.send("DISJ0101", "updateItem", info, {
			onSuccess : function(ei) {
				var status = ei.getStatus();
				if(status!=0){
					NotificationUtil(ei.getMsg(),"error");
				}else{
					NotificationUtil(ei.getMsg(), "success");
					window.parent.resultGrid.dataSource.page(1);
					window.parent['editItemWindow'].close();
				}

			}
		});
	});
	
	

	
	//巡检设备去重
	save = function(rows) {
		//关闭子页面
		var grid = $("#ef_grid_device").data("kendoGrid");
		if (grid.getDataItems().length > 0) {
			grid.checkAllRows();
		}
		var data = grid.getCheckedRows();// 获取选中行数据
		if(Array.isArray(rows)){
			//去重
			data = mergeArray(data, rows)
		}else{
			//去重
			data = merge(data, rows)
		}
		/*if(data == 1){
			NotificationUtil("请勿添加重复设备", "error");
			return;
		}*/
		// 回填
		grid.addRows(data)
		grid.unCheckAllRows();
	}
	//一数组去除重复数值
	function merge(data, rows) {
		for (var i = 0; i < data.length; i++) {
			var a = data[i].machineCode;
			var b = rows.machineCode;
				if (a == b) {
					return;
				}
		}
		return rows;
	}
	//两数组去除重复数值
	function mergeArray(data, rows) {
		for (var i = 0; i < data.length; i++) {
			var a = data[i].machineCode;
			for (var j = 0; j < rows.length; j++) {
				var b = rows[j].machineCode;
				if (a == b) {
					//利用splice函数删除元素，从第i个位置，截取长度为1的元素
					rows.splice(j, 1);
				}
			}
		}
		return rows;
	}
	
	
	//巡检项目去重
	saveItem = function(rows) {
		//关闭子页面
		var grid = $("#ef_grid_item").data("kendoGrid");
		if (grid.getDataItems().length > 0) {
			grid.checkAllRows();
		}
		var data = grid.getCheckedRows();// 获取选中行数据
		//去重
		data = mergeArray2(data, rows)
		// 回填
		grid.addRows(data)
		grid.unCheckAllRows();
	}
	
	//两数组去除重复数值
	function mergeArray2(data, rows) {
		for (var i = 0; i < data.length; i++) {
			var a = data[i].content;
			for (var j = 0; j < rows.length; j++) {
				var b = rows[j].content;
				if (a == b) {
					//利用splice函数删除元素，从第i个位置，截取长度为1的元素
					rows.splice(j, 1);
				}
			}
		}
		return rows;
	}
	
	//巡检执行人去重
	saveExman = function(rows) {
		//关闭子页面
		var grid = $("#ef_grid_exman").data("kendoGrid");
		if (grid.getDataItems().length > 0) {
			grid.checkAllRows();
		}
		var data = grid.getCheckedRows();// 获取选中行数据
		//去重
		data = mergeArray3(data, rows)
		// 回填
		grid.addRows(data)
		grid.unCheckAllRows();
	}
	
	//两数组去除重复数值
	function mergeArray3(data, rows) {
		for (var i = 0; i < data.length; i++) {
			var a = data[i].workNo;
			for (var j = 0; j < rows.length; j++) {
				var b = rows[j].workNo;
				if (a == b) {
					//利用splice函数删除元素，从第i个位置，截取长度为1的元素
					rows.splice(j, 1);
				}
			}
		}
		return rows;
	}
});