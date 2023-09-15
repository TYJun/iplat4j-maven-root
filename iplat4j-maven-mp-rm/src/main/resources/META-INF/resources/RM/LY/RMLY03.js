$(function () {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        add: false, edit: false, del: false,
        hasRowClick: true, rowClickConfig: {gridId: "detail", paramName: "claimId", colName: "id"},
        extentMethod: [{
            buttonId: "APPROVAL",
            call: function () {
                let checkRows = window["resultGrid"].getCheckedRows();
                if (checkRows.length < 1) {
                    NotificationUtil("请选择需要审批的记录", "error");
                } else if (checkRows.length > 1) {
                    NotificationUtil("请选择一条需要审批的记录", "error");
                } else {
                    popData("RMLY0301?inqu_status-0-id=" + checkRows[0].id + "&type=approve");
                }
            }
        }, {
            buttonId: "BATCH_PASS",
            call: function () {
                let checkRows = window["resultGrid"].getCheckedRows();
                if (checkRows.length < 1) {
                    NotificationUtil("请选择需要审批的记录", "error");
                } else {
                    let eiInfo = new EiInfo();
                    let ids = checkRows.map(row => row.id);
                    eiInfo.set("ids", ids)
                    //调用后台保存方法
                    EiCommunicator.send("RMLY03", "batchPass", eiInfo, {
                        onSuccess: function (ei) {
                            if (ei.getStatus() == -1) {
                                NotificationUtil(ei.getMsg(), "error");
                                return;
                            }

                            NotificationUtil("审批成功", "success");
                            window['resultGrid'].dataSource.page(1);
                        },
                        onFail: function (errorMsg, status, e) {
                            NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
                        }
                    });
                }
            }
        }, {
            buttonId: "BATCH_REJECT",
            call: function () {
                let checkRows = window["resultGrid"].getCheckedRows();
                if (checkRows.length < 1) {
                    NotificationUtil("请选择需要审批的记录", "error");
                } else {
                    IPLAT.prompt({
                        message: '驳回原因',
                        okFn: function (e) {
                            reject(e, checkRows)
                        },
                        cancelFn: function (e) {
                        },
                        title: '领用申请审批驳回',
                        minWidth: 450
                    });
                }
            }
        }]
    }).buildGrid();

    /**回车键查询**/
    keydown("inqu", "QUERY");

    /**查询**/
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function (e) {
        //document.getElementById("inqu-trash").click();
        resetTime("inqu_status-0-beginTime", "inqu_status-0-endTime");
        $("#inqu_status-0-planNo").val("");
        resultGrid.dataSource.page(1);
    });

});

function reject(rejectReason, rows) {
    //参数处理
    let eiInfo = new EiInfo();
    let ids = rows.map(row => row.id);
    eiInfo.set("ids", ids);
    eiInfo.set("rejectReason", rejectReason);

    //调用后台保存方法
    EiCommunicator.send("RMLY03", "batchReject", eiInfo, {
        onSuccess: function (ei) {
            if (ei.getStatus() == -1) {
                NotificationUtil(ei.getMsg(), "error");
                return;
            }

            NotificationUtil("保存成功", "success");
            window.parent['resultGrid'].dataSource.page(1);
        },
        onFail: function (errorMsg, status, e) {
            NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
        }
    });
}