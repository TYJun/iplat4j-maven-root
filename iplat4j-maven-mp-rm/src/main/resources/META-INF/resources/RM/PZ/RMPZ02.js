$(function () {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        detail: false, edit: false, isOpType: false, showImg: true
    }).buildGrid();

    $.extend(true, IPLATUI.Config, { //全局配置
        EFGrid: {
            pageable: {
                pageSize: 20, // 每页显示的条数，优先级低于 DataSource 中的 pageSize
                pageSizes: [10, 20, 50, 100, 500]
            },
            columns: [{
                field: "price",
                readonly: true,
                template: "<span>#=price#元</span>",
            }]

        }
    })

    /**回车键查询**/
    keydown("inqu", "QUERY");

    /**查询**/
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultGrid.dataSource.page(1);
    });

});