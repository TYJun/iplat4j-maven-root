$(function(){
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

	IPLATUI.EFGrid = {
		"result" : {
			loadComplete:function (e) {
				//打开子页面
				$("#GEN").on("click", function(e) {
					let url = IPLATUI.CONTEXT_PATH + "/web/SIYJ0201";
					let popData = $("#popData");
					popData.data("kendoWindow").setOptions({
						open : function() {
							popData.data("kendoWindow").refresh({
								url : url,
							});
						}
					});
					popDataWindow.open().center();
				});
			},
			pageable: {
				pageSize: 20,
				pageSizes: [10, 20, 50, 100]
			},
		}
	}
	
})