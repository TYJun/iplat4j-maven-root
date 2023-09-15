var checkRowsCount = 0, pageCount = 0.00, buyCostCount = 0.00, netValueCount = 0.00;// 资产原值总金额
var pageMoneySum = 0.00;// 全选后记录本页总金额
var datagrid = null;
let addFlag = true;// afterEdit会执行两次，总价会重复计算两次，用该变量控制只计算一次
$(function () {
    if ($("#info-0-type").val() == "wasting") {
        $("#wasted").hide();
        $("#wasting").show();
    } else if ($("#info-0-type").val() == "wasted") {
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
                                                    pageCount = 0.00;
                                                    var DataItems = resultGrid.getDataItems();
                                                    for (let j = 0; j < DataItems.length; j++) {
                                                        pageCount += $.isNumeric(DataItems[j].buyCost) ? +DataItems[j].buyCost : 0;
                                                    }
                                                    $("#pageCount").text(pageCount.toFixed(2));
                                                    $("#checkRowsCount").text(0);
                                                    $("#buyCostCount").text("0.00");
                                                    $("#netValueCount").text("0.00");
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
                                                    pageCount = 0.00;
                                                    checkRowsCount = 0;
                                                    buyCostCount = 0.00;
                                                    netValueCount = 0.00;
                                                    var DataItems = resultGrid.getDataItems();
                                                    for (let j = 0; j < DataItems.length; j++) {
                                                        pageCount += $.isNumeric(DataItems[j].buyCost) ? +DataItems[j].buyCost : 0;
                                                    }
                                                    $("#pageCount").text(pageCount.toFixed(2));
                                                    $("#checkRowsCount").text(checkRowsCount);
                                                    $("#buyCostCount").text(buyCostCount);
                                                    $("#netValueCount").text(netValueCount);
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
                            $("#checkRowsCount").text(0);
                            $("#buyCostCount").text("0.00");
                            $("#netValueCount").text("0.00");
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
            onCellClick: function (e) {
                e.preventDefault();
                if (!e.fake) {
                    let grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
                    datagrid = model;
                }
            },
            onRowClick: function (e) {
                e.preventDefault();
            },
            onCheckAllRows: function (e) {
                buyCostCount = 0.00;
                $("#buyCostCount").text(buyCostCount);
            },
            loadComplete: function (grid) {
                $("#ef_grid_toolbar_result").prepend("<div style='float:left;font-size:13px;'>" +
                    "当页资产原值总金额：<span id='pageCount' style='color: red'>0.00</span>元，" +
                    "选中资产数量：<span id='checkRowsCount' style='color: red'>0</span>条，" +
                    "选中资产原值总金额：<span id='buyCostCount' style='color: red'>0.00</span>元，" +
                    "选中资产净值总金额：<span id='netValueCount' style='color: red'>0.00</span>元</div>")
                var checkRows = window.parent.resultAGrid.getCheckedRows()
                if (checkRows.length > 0) {
                    grid.addRows(checkRows);
                    grid.unCheckAllRows();
                } else {
                    if (grid != undefined) {
                        for (let i = 0; i < grid.eiBlock.rows.length; i++) {
                            pageCount += $.isNumeric(grid.eiBlock.rows[i][13]) ? +grid.eiBlock.rows[i][13] : 0;
                        }
                    }
                    $("#pageCount").text(pageCount.toFixed(2));
                }
                if (__ei.type == "wasted") {
                    $(".k-grid-addRows").hide()
                }
            },
            onSuccess: function (e) {
                // 数据源发生改变（切换条数、切换页）时，入库总金额清零
                pageCount = 0.00;
                for (let i = 0; i < e.eiInfo.blocks.result.rows.length; i++) {
                    pageCount += $.isNumeric(e.eiInfo.blocks.result.rows[i][13]) ? + e.eiInfo.blocks.result.rows[i][13] : 0;
                }
                $("#pageCount").text(pageCount.toFixed(2));
                checkRowsCount = 0;
                buyCostCount = 0.00;
                netValueCount = 0.00;
                $("#checkRowsCount").text(checkRowsCount);
                $("#buyCostCount").text(buyCostCount);
                $("#netValueCount").text(netValueCount);
            },
            onCheckRow: function (e) {
                let model = e.model;
                let buyCost = $.isNumeric(model["buyCost"]) ? +model["buyCost"] : 0;
                let netValue = $.isNumeric(model["netAssetValue"]) ? +model["netAssetValue"] : 0;
                if (e.checked) {
                    buyCostCount += buyCost
                    netValueCount += netValue
                    // 全选时调用n次单选，加上最后一条记录的金额时，pageMoneySum记录下本页总金额
                } else {
                    buyCostCount -= buyCost
                    netValueCount -= netValue
                }
                var getCheckedRows = resultGrid.getCheckedRows().length;
                $("#checkRowsCount").text(getCheckedRows);
                if (getCheckedRows == 0) {
                    buyCostCount = 0
                    netValueCount = 0
                }
                $("#buyCostCount").text(buyCostCount.toFixed(2));
                $("#netValueCount").text(netValueCount.toFixed(2));

            },
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

window.methods = {
    pageCountCallback(arguments) {
        $("#pageCount").text(arguments);
    }
}