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
                    var url = IPLATUI.CONTEXT_PATH + "/web/HRXX0101?type=" +type;
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
                // 编辑
                $("#EDIT").on("click", function (e) {

                    var rows = resultGrid.getCheckedRows();
                    for (var i = 0; i < rows.length; i++) {
                        if (rows[i].statusCode != '01') {
                            NotificationUtil("只能编辑新建的人员", "error");
                            return;
                        }
                    }
                    if (rows.length > 0) {
                        id = rows[0].id;
                    } else {
                        NotificationUtil("没有勾选数据", "error");
                        return;
                    }
                    var id =rows[0].id;
                    var type = "edit";
                    var url = IPLATUI.CONTEXT_PATH + "/web/HRXX0101?id=" + id +"&type=" +type;
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
                // 删除
                $("#REDELETE").on("click", function (e) {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length < 1) {
                        NotificationUtil("请选择要删除的行", "error");
                        return;
                    }
                    if (checkRows[0].statusCode != '01' ) {
                        NotificationUtil("只能选择新建信息", "error");
                        return;
                    }
                    var id = checkRows[0].id;
                    var info = new EiInfo();
                    info.set("id", id);
                    IPLAT.confirm({
                        message: '<b>确定删除此条信息吗？</b></i>',
                        okFn: function (e) {
                            EiCommunicator.send("HRXX01", "delete", info, {
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

                //确认入职按钮
                $("#INDUCTION").on("click", function (e) {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length < 1) {
                        NotificationUtil("请选择要确认的行", "error");
                        return;
                    }
                    if (checkRows[0].statusCode != '01') {
                        NotificationUtil("只能选中新建状态的人员", "error");
                        return;
                    }
                    var id = checkRows[0].id;
                    var url = IPLATUI.CONTEXT_PATH + "/web/HRXX0102?id=" + id;
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

                //取消入职按钮
                $("#UNINDUCTION").on("click", function (e) {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length < 1) {
                        NotificationUtil("请选择要确认的行", "error");
                        return;
                    }
                    if (checkRows[0].statusCode != '01'){
                        NotificationUtil("只能选中新建状态的人员", "error");
                        return;
                    }
                    var id = checkRows[0].id;
                    var inInfo = new EiInfo();
                    inInfo.set("id", id);
                    inInfo.set("type","uninduction");
                    IPLAT.confirm({
                        message: '<b>确定取消入职吗？</b></i>',
                        okFn: function (e) {
                            EiCommunicator.send("HRXX01", "updateInduction", inInfo, {
                                onSuccess: function (ei) {
                                    NotificationUtil("取消成功", "success");
                                    resultGrid.dataSource.page(1);
                                }
                            });
                        },
                        cancelFn: function (e) {
                        }
                    })

                });
            }
        }
    }
})
