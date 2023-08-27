IPLATUI.EFGrid = {
		"result": {
			loadComplete: function (grid) {
				$("#SAVE01").on("click", function() {
					var list = resultGrid.getCheckedRows();
					window.parent.save(list);
					window.parent['popDataWindow'].close();
				});
			}
		}
};

function dd () {
	var list = resultGrid.getCheckedRows();
	//window.parent.resultGrid.addRows(list);
	window.parent.save(list);
	//var aa = window.parent['popData'];
	//window.parent['popData'].close(); 
	window.parent['popDataWindow'].close();
}