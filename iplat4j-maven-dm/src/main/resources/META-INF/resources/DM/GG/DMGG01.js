$(function() {
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.query(1);
	});

	IPLATUI.EFGrid = {
		"result" : {
			loadComplete : function(grid) {
				$("#SUBMIT").on("click", function(e) {
					var url = IPLATUI.CONTEXT_PATH + "/web/DMGG0101";
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
			}
		}
	}

});
