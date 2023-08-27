$(function() {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        add: false, edit: false, del: false,
        detailUrl:"MPDD0102?inqu_status-0-id=#:id#",
        extentMethod:[{
            buttonId: "ENTER_OUT",
            call: function() {
                let checkedRows = resultGrid.getCheckedRows();
                if(checkedRows.length < 1) {
                    NotificationUtil("请先选择需要入库的记录", "error");
                    return;
                }
                popData("MPDD0202?inqu_status-0-id="+ checkedRows[0].id);
            }
        },{
            buttonId: "ENTER",
            call: function() {
                let checkedRows = resultGrid.getCheckedRows();
                if(checkedRows.length < 1) {
                    NotificationUtil("请先选择需要入库的记录", "error");
                    return;
                }
                popData("MPDD0201?inqu_status-0-id="+ checkedRows[0].id);
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
        resetTime("inqu_status-0-beginTime", "inqu_status-0-endTime");
        resultGrid.dataSource.page(1);
    });

});