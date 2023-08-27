$(function () {
    if ($("#info-0-type").val() == "wasting"){
        $("#wasted").hide();
        $("#wasting").show();
    } else if ($("#info-0-type").val() == "wasted"){
        $("#wasting").hide();
        $("#wasted").show();
    }

    $("#wastingSUBMIT").on("click", function (e) {
        var discussName = $("#info-0-discussName").val();
        var discussId = $("#info-0-discussId").val();
        var checkRows = resultGrid.getDataItems();
        if (discussName == "") {
            NotificationUtil("会议名称不能为空", "warning")
            return;
        }
        if (!checkRows.length > 0) {
            NotificationUtil("上会资产不能为空", "warning")
            return;
        }
        // 获取参数
        var eiInfo = new EiInfo();
        eiInfo.setByNode("info");
        eiInfo.set("discussId", discussId)
        eiInfo.set("faIdList", checkRows)
        // 固资类别录入提交
        EiCommunicator.send("FASH0102", "updateFaInfoWasting", eiInfo, {
            onSuccess: function (ei) {
                window.parent['popDataWindow'].close();
                window.parent["resultBGrid"].dataSource.page(1);
                window.parent["resultCGrid"].dataSource.page(1);
            }
        })
    });

    $("#wastedSUBMIT").on("click", function (e) {
        var discussId = $("#info-0-discussId").val();
        var checkRows = resultGrid.getDataItems();
        if (!checkRows.length > 0) {
            NotificationUtil("上会资产不能为空", "warning")
            return;
        }
        // 获取参数
        var eiInfo = new EiInfo();
        eiInfo.setByNode("info");
        eiInfo.set("discussId", discussId)
        eiInfo.set("faIdList", checkRows)
        // 固资类别录入提交
        EiCommunicator.send("FASH0102", "updateFaInfoWasted", eiInfo, {
            onSuccess: function (ei) {
                window.parent['popDataWindow'].close();
                window.parent["resultBGrid"].dataSource.page(1);
                window.parent["resultCGrid"].dataSource.page(1);
            }
        })
    });

    $("#CANCEL").on("click", function (e) {
        window.parent['popDataWindow'].close();
    });

    IPLATUI.EFGrid = {
        "result": {
            pageable: false,
            exportGrid: false,
            // pageable: {
            //     pageSize: 15
            // },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "remove",
                        text: "移除资产",
                        layout: "3",
                        click: function () {
                            var type = $("#info-0-type").val();
                            if (type == "wasting") {
                                var checkRows = resultGrid.getCheckedRows()
                                if (checkRows.length > 0) {
                                    var eiInfo = new EiInfo();
                                    var discussId = IPLAT.EFInput.value($("#info-0-discussId"))
                                    eiInfo.set("discussId", discussId);
                                    eiInfo.set("faInfoList", checkRows);
                                    eiInfo.set("lockFlag", 0);
                                    IPLAT.confirm({
                                        message: '<b style="color: red">确定执行资产移除操作吗？该操作不可恢复</b></i>',
                                        okFn: function (e) {
                                            EiCommunicator.send("FASH00", "updateFaInfoLock", eiInfo, {
                                                onSuccess: function (ei) {
                                                    resultGrid.removeRows(checkRows);
                                                }
                                            });
                                        },
                                        cancelFn: function (e) {
                                        }
                                    })
                                } else {
                                    NotificationUtil("请选择一条资产进行移除", "warning")
                                    return;
                                }
                            } else if (type == "wasted") {
                                var checkRows = resultGrid.getCheckedRows()
                                if (checkRows.length > 0) {
                                    var eiInfo = new EiInfo();
                                    var discussId = IPLAT.EFInput.value($("#info-0-discussId"))
                                    eiInfo.set("discussId", discussId);
                                    eiInfo.set("faInfoList", checkRows);
                                    eiInfo.set("lockFlag", 0);
                                    eiInfo.set("type", type);
                                    IPLAT.confirm({
                                        message: '<b style="color: red">确定执行资产移除操作吗？该操作不可恢复</b></i>',
                                        okFn: function (e) {
                                            EiCommunicator.send("FASH00", "updateFaInfoLock", eiInfo, {
                                                onSuccess: function (ei) {
                                                    resultGrid.removeRows(checkRows);
                                                }
                                            });
                                        },
                                        cancelFn: function (e) {
                                        }
                                    })
                                } else {
                                    NotificationUtil("请选择一条资产进行移除", "warning")
                                    return;
                                }
                            }
                        }
                    },
                    {
                        name: "addRows",
                        text: "附加资产",
                        layout: "3",
                        click: function () {
                            var discussId = IPLAT.EFInput.value($("#info-0-discussId"))
                            fixedAssetsWindow(discussId)
                        }
                    },
                ]
            },
            columns: [{
                field: "statusCodeMean",
                filterable: false
            }, {
                field: "discussId",
                template: function () {
                    return $("#info-0-discussId").val();
                }
            }],
            loadComplete: function (grid) {
                var checkRows = window.parent.resultAGrid.getCheckedRows()
                if (checkRows.length > 0) {
                    grid.addRows(checkRows);
                    grid.unCheckAllRows();
                }
                if (__ei.type == "wasted") {
                    $(".k-grid-addRows").hide()
                }
            }
        },
    }
});

// 自定义资产弹窗
function fixedAssetsWindow(discussId) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FASH00?initLoad&discussId=" + discussId;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url,
            });
        }
    });
    // 新窗口打开居中
    popDataWindow.open().center();
}