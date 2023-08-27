$(function () {
    //表格初始化处理
    IPLATUI.EFGrid = {
        "result": {
            onCellClick: function (e) {
                if (e.field === "planNo") {
                    let url = "";
                    if (e.model['planType'] == '01') {
                        url = "RMXQ0101?inqu_status-0-id=" + e.model['id'] + "&type=see"
                    } else if (e.model['planType'] == '01') {
                        url = "RMXQ0201?inqu_status-0-id=" + e.model['id'] + "&type=see"
                    } else {
                        url = "RMXQ0301?inqu_status-0-id=" + e.model['id'] + "&type=see"
                    }
                    popData(url);
                    resultGrid.unCheckAllRows();
                }
            },
            pageable: {
                pageSize: 20, // 每页显示的条数，优先级低于 DataSource 中的 pageSize
                pageSizes: [10, 20, 50, 100, 500]
            }
        }
    }

    /**回车键查询**/
    keydown("inqu", "QUERY");

    /**查询**/
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resetParam(__eiInfo, true, true);
        resultGrid.dataSource.page(1);
    });

});