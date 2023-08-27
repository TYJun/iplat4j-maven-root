$(function() {
	IPLATUI.EFGrid = {
		"result": {
			loadComplete:function(){
				//确定
				$("#COMFIRM").on("click", function(e) {
					var checkRows = resultGrid.getCheckedRows();
					var eiInfo = new EiInfo();
					if (checkRows.length > 0) {
						window.parent.addRows(checkRows);
						window.parent["popData1Window"].close();
					} else {
						IPLAT.NotificationUtil("请选择物资");
					}
				});
				
				//关闭
				$("#CLOSE").on("click", function(e) {
					window.parent['popData1Window'].close();
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