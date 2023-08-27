$(function () {
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

