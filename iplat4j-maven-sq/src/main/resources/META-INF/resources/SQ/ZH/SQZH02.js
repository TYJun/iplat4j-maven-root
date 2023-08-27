$(function() {
    //重置按钮
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        resultGrid.dataSource.page(1);
    });
    //查询方法
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });
});
IPLATUI.EFGrid = {
    "result": {
        // pageable: false,
        // exportGrid: false,
        loadComplete: function (grid) {

        },
    }
}