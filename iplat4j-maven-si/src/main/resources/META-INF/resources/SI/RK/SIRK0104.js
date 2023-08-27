$(function() {
	$.extend(true, IPLATUI.Config, { //全局配置
		EFGrid: {
			pageable: {
				pageSize: 50, // 每页显示的条数，优先级低于 DataSource 中的 pageSize
				pageSizes: [10, 20, 50, 100, 500, 1000]
			}
		}
	})
	IPLATUI.EFGrid = {
		"result": {
			dataBinding: function (e) {
				for (let i = 0, length = e.items.length; i < length; i++) {
					if($.isNumeric(e.items[i].price)){
						e.items[i].price = parseFloat(e.items[i].price).toFixed(2);
					}
				}
			},
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

	/**回车键查询**/
	keydown("inqu", "QUERY");

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