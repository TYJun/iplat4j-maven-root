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
			columns: [{field: "enterNum", template: function (item) {
					return ("05" == item['enterType'] ? "-" : "") + item['enterNum']
				}
			},{field: "enterAmount", template: function (item) {
					return ("05" == item['enterType'] ? "-" : "") + item['enterAmount']
				}
			}],
			pageable: {
				pageSize: 50,
				pageSizes: [10, 20, 50, 100, 500, 1000]
			},
			dataBinding: function (e) {
				for (let i = 0, length = e.items.length; i < length; i++) {
					if($.isNumeric(e.items[i].enterNum)){
						e.items[i].enterNum = parseFloat(e.items[i].enterNum).toFixed(2);
					}
					if($.isNumeric(e.items[i].enterPrice)){
						e.items[i].enterPrice = parseFloat(e.items[i].enterPrice).toFixed(2);
					}
					if($.isNumeric(e.items[i].enterAmount)){
						e.items[i].enterAmount = parseFloat(e.items[i].enterAmount).toFixed(2);
					}
				}
			}
		},
	}
});