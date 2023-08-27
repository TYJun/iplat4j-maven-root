$(function (){
    // 查询
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#RESET").on("click", function (e) {
        $("#inqu_status-0-realName").val("");
        $("#inqu_status-0-jobCode").val("");
        $("#inqu_status-0-company").val("");
        $("#inqu_status-0-workNo").val("");
        IPLAT.EFSelect.value($("#inqu_status-0-statusCode"), "");
        $("#inqu_status-0-deptNum").val("");
        resultGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "result": {
            loadComplete: function (grid) {
                // 新增
                $("#READD").on("click", function (e) {
                    var type ="add";
                    var url = IPLATUI.CONTEXT_PATH + "/web/HRLZ0101?type=" +type;
                    var popData = $("#popData");
                    popData.data("kendoWindow").setOptions({
                        open: function () {
                            popData.data("kendoWindow").refresh({
                                url: url
                            });
                        }
                    });
                    popDataWindow.open().center();
                });
                // 取消预离职
                $("#RECANCEL").on("click", function (e) {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length < 1) {
                        NotificationUtil("请选择行", "error");
                        return;
                    }
                    if (checkRows[0].statusCode != '02' ) {
                        NotificationUtil("只能选择预离职的人员", "error");
                        return;
                    }
                    var id = checkRows[0].id;
                    var info = new EiInfo();
                    info.set("id", id);
                    info.set("type","cancel");
                    IPLAT.confirm({
                        message: '<b>确定取消离职吗？</b></i>',
                        okFn: function (e) {
                            EiCommunicator.send("HRLZ01", "updateCancel", info, {
                                onSuccess: function (ei) {
                                    if (ei.getStatus() == -1) {
                                        NotificationUtil(ei.getMsg(), "error");
                                        return;
                                    }
                                    NotificationUtil(ei.getMsg(), "success");
                                    resultGrid.dataSource.query(1);
                                }
                            })
                        },
                        cancelFn: function (e) {
                        }
                    })
                });
                //确认离职按钮
                $("#QUIT").on("click", function (e) {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length < 1) {
                        NotificationUtil("请选择要确认的行", "error");
                        return;
                    }
                    if (checkRows[0].statusCode != '02' ) {
                        NotificationUtil("只能选择预离职的人员", "error");
                        return;
                    }
                    var id = checkRows[0].id;
                    var preOutTime = checkRows[0].preOutTime;
                    var url = IPLATUI.CONTEXT_PATH + "/web/HRLZ0201?id=" + id + "&preOutTime=" + preOutTime;
                    var popData = $("#popDataModify");
                    popData.data("kendoWindow").setOptions({
                        open: function () {
                            popData.data("kendoWindow").refresh({
                                url: url
                            });
                        }
                    });
                    popDataModifyWindow.open().center();
                });
            }
        }
    }
})
