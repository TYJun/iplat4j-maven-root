$(function () {
	/**
	 * 查询
	 */
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });

	/**
	 * 重置
	 */
	$("#RESET").on("click", function (e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
    });
});


