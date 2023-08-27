$(function() {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        add: true, edit: false, del: false,
        detailUrl: "RMLY0204?inqu_status-0-id=#:id#&inqu-status-0-statusCode=#:statusCode#",
        detailWindow: "detailPopData",
        addUrl: "RMLY0205?inqu_status-0-id=#:id#",
        hasRowClick: true, rowClickConfig: {gridId:"detail", paramName:"claimId", colName: "id"},
        extentMethod: [
            {
                buttonId: "APPROVAL",
                call: function () {
                    let checkRows = window["resultGrid"].getCheckedRows();
                    if (checkRows.length < 1) {
                        NotificationUtil("请选择需要操作的记录", "error");
                    } else if ("30" == checkRows[0].statusCode) {
                        // NotificationUtil("正在操作出库审批");
                        popData("RMLY0202?inqu_status-0-id=" + checkRows[0].id + "&type=approve", "detailPopData");
                    } else if (["50","60"].includes(checkRows[0].statusCode)) {
                        // NotificationUtil("正在执行物资出库操作");
                        popData("RMLY0203?inqu_status-0-id=" + checkRows[0].id + "&type=out", "detailPopData");
                    }
                }
            },
            // {
            //     buttonId: "OUT",
            //     call: function () {
            //         let checkRows = window["resultGrid"].getCheckedRows();
            //         if (checkRows.length < 1) {
            //             NotificationUtil("请选择需要出库的记录", "error");
            //         } else if (!["50","60"].includes(checkRows[0].statusCode)) {
            //             NotificationUtil("未审批或已完成出库的记录无法出库", "error");
            //         } else {
            //             popData("RMLY0203?inqu_status-0-id=" + checkRows[0].id + "&type=out", "detailPopData");
            //         }
            //     }
            // },
            {
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
                                EiCommunicator.send("RMLY01", "over", eiInfo, {
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
                            cancelFn: function (e) {}
                        })
                    }
                }
            }
        ]
    }).buildGrid();

    IPLATUI.EFGrid['detail'] = {
        dataBound: function (e) {
            let grid = e.sender;
            let trs = grid.table.find("tr");
            $.each(trs, function (i, tr) {
                let stockNum = e.sender.dataItems()[i].stockNum;
                let num = e.sender.dataItems()[i].num;
                let outNum = e.sender.dataItems()[i].outNum;
                if ((num>0 && num !=outNum && num-stockNum >0  )) {
                    tr.style.background = "#FF6347"
                }
            });
        }
    }

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

function printOutBill(outBillNo) {
    setTimeout(function () {
        popData("SICK0104?initLoad&outBillNo="+ outBillNo, "detailPopData");
    }, 1000);
}