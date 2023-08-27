$(function() {
    /**回车键查询**/
    keydown("inqu", "QUERY");

    /**查询**/
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function(e) {
        $("#inqu_status-0-orderNo").val("");
        resultGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "result": {
            onRowClick: function (e) {
                e.preventDefault();
                $("#inqu_status-0-claimId").val( e.model["id"]);
                window["detailGrid"].dataSource.page(1);
            }
        },
        "detail": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false,cancel: false,save: false,'delete': false,
                buttons: [{
                    name: "SURE",text: "确定",layout: "3",
                    click: function (){
                        let checkRows = detailGrid.getCheckedRows();
                        let resultRows = resultGrid.getCheckedRows();
                        if (checkRows && checkRows.length > 0) {
                            window.parent.addRows(checkRows);
                            window.parent.resetCostDept(resultRows[0].costDeptNum, resultRows[0].costDeptName);
                            window.parent['popDataWindow'].close();
                        } else {
                            NotificationUtil("请选择采购领用明细", "warning");
                        }
                    }
                }]
            }
        },
    }
});
