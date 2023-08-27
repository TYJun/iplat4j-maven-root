$(function() {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        add: false, edit: false, del: false,
        detailUrl: "RMTK0101?inqu_status-0-id=#:id#",
        hasRowClick: true, rowClickConfig: {gridId:"detail", paramName:"backOutId", colName: "id"},
        extentMethod: [{
            buttonId: "ADD",
            call: function () { popData("RMTK0203?type=add"); }
        },{
            buttonId: "APPROVAL",
            call: function () {
                let checkRows = window["resultGrid"].getCheckedRows();
                if (checkRows.length < 1) {
                    NotificationUtil("请选择需要审批的记录", "error");
                } else if ("10" != checkRows[0].statusCode) {
                    NotificationUtil("已审批的记录无需审批", "error");
                } else {
                    popData("RMTK0201?inqu_status-0-id=" + checkRows[0].id + "&type=approve");
                }
            }
        },{
            buttonId: "OUT",
            call: function () {
                let checkRows = window["resultGrid"].getCheckedRows();
                if (checkRows.length < 1) {
                    NotificationUtil("请选择需要出库的记录", "error");
                } else if (!["30","40"].includes(checkRows[0].statusCode)) {
                    NotificationUtil("未审批或已完成出库的记录无法出库", "error");
                } else {
                    popData("RMTK0202?inqu_status-0-id=" + checkRows[0].id + "&type=out");
                }
            }
        },{
            buttonId: "OVER",
            call: function () {
                let checkRows = window["resultGrid"].getCheckedRows();
                if (checkRows.length < 1) {
                    NotificationUtil("请选择需要结束的记录", "error");
                } else {
                    IPLAT.confirm({
                        message: '<b>您确定要结束吗？</b>',
                        okFn: function (e) {
                            let eiInfo = new EiInfo();
                            eiInfo.set("id", checkRows[0].id);
                            EiCommunicator.send("RMTK02", "over", eiInfo, {
                                onSuccess: function (ei) {
                                    if (ei.getStatus() == -1) {
                                        NotificationUtil(ei.getMsg(), "error");
                                        return;
                                    }
                                    NotificationUtil("结束成功");
                                    window["resultGrid"].dataSource.page(1);
                                }
                            });
                        },
                        cancelFn: function (e) {
                        }
                    })
                }
            }
        }]
    }).buildGrid();

    $.extend(true, IPLATUI.Config, { //全局配置
        EFGrid: {
            pageable: {
                pageSize: 20, // 每页显示的条数，优先级低于 DataSource 中的 pageSize
                pageSizes: [5, 10, 20, 50]
            }
        }
    })

    /**回车键查询**/
    keydown("inqu", "QUERY");

    /**查询**/
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        resetTime("inqu_status-0-beginTime", "inqu_status-0-endTime")
        resultGrid.dataSource.page(1);
    });

});