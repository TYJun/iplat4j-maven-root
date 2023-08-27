$(function () {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        add: false, edit: false, del: false, detailUrl: "RMLY0101?inqu_status-0-id=#:id#"
    }).buildGrid();

    $.extend(true, IPLATUI.Config, { //全局配置
        EFGrid: {
            pageable: {
                pageSize: 50, // 每页显示的条数，优先级低于 DataSource 中的 pageSize
                pageSizes: [10, 20, 50, 100, 500]
            }
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
        resetParam(__eiInfo, true, true)
        resultGrid.dataSource.page(1);
    });

});