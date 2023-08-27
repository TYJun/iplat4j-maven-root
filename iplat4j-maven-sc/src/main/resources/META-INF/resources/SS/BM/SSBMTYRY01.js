$(function() {
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});
});