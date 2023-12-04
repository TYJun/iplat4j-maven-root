$(function () {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        delConfig: {status: ["01"], serviceName: "RMXQ01", methodName: "delete"},
        submit: true, addCopy: true,
        submitConfig: {status: ["01"], backStatus: ["20"], serviceName: "RMXQ01", methodName: "submit"},
        back: true,
        backConfig: {
            status: [isEffect("requireApproval") ? "10" : "30"],
            serviceName: "RMXQ01",
            methodName: "withdraw"
        },
    }).buildGrid();

    $.extend(true, IPLATUI.Config, { //全局配置
        EFGrid: {
            pageable: {
                pageSize: 20, // 每页显示的条数，优先级低于 DataSource 中的 pageSize
                pageSizes: [10, 20, 50, 100, 500]
            }
        }
    })

    IPLATUI.EFDatePicker = {
        "inqu_status-0-planTime": {
            start: "year",
            depth: "year"
        },
    };

    /**回车键查询**/
    keydown("inqu", "QUERY");

    /**查询**/
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resetParam(__eiInfo, false, true);
        resultGrid.dataSource.page(1);
    });

});