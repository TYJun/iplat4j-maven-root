IPLATUI.EFGrid = {
	"result": {
		pageable : false,
		exportGrid : false,
		loadComplete: function (grid) {
			//确定
			$("#DETERMINE").on("click", function (e) {
				debugger
				var perGroupNo = $("#perGroupNo").val();
				var perGroupName = $("#perGroupName").val();
				var groupId = $("#groupId").val();
				if(perGroupName == "" || perGroupNo == ""){
					NotificationUtil("红心为必填项", "error");
					return;
				}
//				grid.checkAllRows();
				var row = grid.getDataItems();
				if(row.length < 1){
					NotificationUtil("请请选择人员", "error");
					return;
				}
				var eiInfo = new EiInfo();
				eiInfo.set("data",row);
				eiInfo.set("perGroupNo",perGroupNo);
				eiInfo.set("perGroupName",perGroupName);
				eiInfo.set("groupId",groupId);
				EiCommunicator.send("SQFL0103", "update", eiInfo, {
					onSuccess : function(ei) {
						var status = ei.getStatus();
						if(status == -1){
							IPLAT.alert(ei.getMsg());
						}else{
							setTimeout(function() {
								NotificationUtil(ei.getMsg(), "success");
								window.parent.location.reload()
							}, 300);
						}
					}
				});
			});
			//新增
			$("#ADD").on("click", function (e) {
				var url = IPLATUI.CONTEXT_PATH + "/web/SQFL010101?operType=DETERMINE";
				var popData = $("#popData");
				popData.data("kendoWindow").setOptions({
					open : function() {
						popData.data("kendoWindow").refresh({
							url : url
						});
					}
				});
				popDataWindow.open().center();
			});
			//删除按钮
			$("#DELETEROW").on("click", function (e) {
				var checkRows = resultGrid.getCheckedRows();
				if(checkRows.length<1){
					NotificationUtil("请选择要删除的行", "error");
					return;
				}else{
					var grid = $("#ef_grid_result").data("kendoGrid");
					grid.removeRows(checkRows);
				}
			});
			//查询方法
			$("#QUERY").on("click", function (e) {
				resultGrid.dataSource.page(1);
			});
		}
	}
}

save = function(rows) {
	//关闭子页面
	popDataWindow.close();
	var grid = $("#ef_grid_result").data("kendoGrid");
	if (grid.getDataItems().length > 0) {
		grid.checkAllRows();
	}
	var data = grid.getCheckedRows();// 获取选中行数据
	//去重
	data = mergeArray(data, rows)
	// 回填
	grid.addRows(data)
	grid.unCheckAllRows();

}

//两数组去除重复数值
function mergeArray(data, rows) {
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

// 单个数组去重
function unique(arr) {
	return Array.from(new Set(arr))
}