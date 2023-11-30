$(function () {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        submit: true,
        back: true,
        backConfig: {
            status: [isEffect("requireApproval") ? "10" : "30"],
            serviceName: "RMXQ01",
            methodName: "withdraw"
        },
        addCopy: true
    }).buildGrid();

    $.extend(true, IPLATUI.Config, { //全局配置
        EFGrid: {
            pageable: {
                pageSize: 20, // 每页显示的条数，优先级低于 DataSource 中的 pageSize
                pageSizes: [10, 20, 50, 100, 500]
            },
        }
    }),


    IPLATUI.EFDatePicker = {
        "inqu_status-0-planTime": {
            start: "decade",
            depth: "decade"
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
    $("#EXPORTTYPE").on("click", function (e) {
        exportFile();
    });
    function exportFile() {
        window.location.href = IPLATUI.CONTEXT_PATH+"/exp/采购模块申请与审批操作说明——需求计划2023.11.01.docx"
    }
});