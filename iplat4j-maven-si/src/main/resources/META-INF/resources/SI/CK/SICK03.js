$(function() {
	/**回车键查询**/
	keydown("inqu", "QUERY");

	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});

	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resetTime(false);
		resultGrid.dataSource.page(1);
	});

	IPLATUI.EFGrid = {
		"result": {
			columns: [{field: "outNum", template: function (item) {
					return ("05" == item['outType'] ? "-" : "") + item['outNum']
				}
			},{field: "outAmount", template: function (item) {
					return ("05" == item['outType'] ? "-" : "") + item['outAmount']
				}
			}],
			pageable: {
				pageSize: 50,
				pageSizes: [10, 20, 50, 100, 500]
			},
			dataBinding: function (e) {
				for (let i = 0, length = e.items.length; i < length; i++) {
					if($.isNumeric(e.items[i].outNum)){
						e.items[i].outNum = parseFloat(e.items[i].outNum).toFixed(2);
					}
					if($.isNumeric(e.items[i].outPrice)){
						e.items[i].outPrice = parseFloat(e.items[i].outPrice).toFixed(2);
					}
					if($.isNumeric(e.items[i].outAmount)){
						e.items[i].outAmount = parseFloat(e.items[i].outAmount).toFixed(2);
					}
				}
			}
		},
	}
})
	