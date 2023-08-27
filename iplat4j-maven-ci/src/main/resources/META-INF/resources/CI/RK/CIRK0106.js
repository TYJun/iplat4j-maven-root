$(function() {
	IPLATUI.EFGrid = {
		"result": {
			loadComplete:function(grid){
				//确定
				$("#COMFIRM").on("click", function(e) {
					var grid = $("#ef_grid_result").data("kendoGrid");
					var data = grid.getCheckedRows();// 获取选中行数据
					console.log(data)
					if (data.length > 0) {
						// alert("11111")
						window.parent.save(data);
					} else {
						IPLAT.NotificationUtil("请选择物资");
					}
				});
				
				//关闭
				$("#CLOSE").on("click", function(e) {
					window.parent['popData2Window'].close();
				});
			}
		}
	}
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});
	
	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

})