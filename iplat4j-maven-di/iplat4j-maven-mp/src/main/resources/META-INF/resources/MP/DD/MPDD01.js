$(function() {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        detailUrl:"MPDD0102?inqu_status-0-id=#:id#",
        editUrl:"MPDD0102?inqu_status-0-id=#:id#",
        editConfig: {status: ["01","10"]},
        extentMethod: [{
            buttonId:'DOWNLOAD',
            call: function () {
                let checkedRows = resultGrid.getCheckedRows();
                if(checkedRows.length < 1) {
                    NotificationUtil("请选择需要导出的订单信息", "error");
                }
                window.location.href =  IPLATUI.CONTEXT_PATH+"/mp/dd/orderExport?id="+checkedRows[0].id;
            }
        }]
    }).buildGrid();
    $.extend(true, IPLATUI.Config, { //全局配置
        EFGrid: {

            columns:[ {
                field: "orderCost",
                readonly: true,
                template: "<span>#=orderCost#元</span>",
            }]

        }
    })
    /**查询**/
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        $("#inqu_status-0-deptNum").val(__eiInfo.get("inqu_status-0-deptNum"));
        $("#inqu_status-0-deptName").val(__eiInfo.get("inqu_status-0-deptName"));
        resetTime("inqu_status-0-beginTime", "inqu_status-0-endTime");
        resultGrid.dataSource.page(1);
    });

});