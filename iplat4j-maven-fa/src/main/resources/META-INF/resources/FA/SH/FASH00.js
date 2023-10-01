$(function () {
    $("#QUERY").on("click", function (e) {
        var DataItems = window.parent.resultGrid.getDataItems();
        var faInfoIdList = null;
        for (let i = 0; i < DataItems.length; i++) {
            if (i != 0) {
                faInfoIdList = faInfoIdList + "," + DataItems[i].faInfoId;
            } else {
                faInfoIdList = DataItems[i].faInfoId;
            }
        }
        IPLAT.EFInput.value($("#inqu_status-0-faInfoIdList"),faInfoIdList);
        resultAGrid.dataSource.page(1);
    });

    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        var DataItems = window.parent.resultGrid.getDataItems();
        var faInfoIdList = null;
        for (let i = 0; i < DataItems.length; i++) {
            if (i != 0) {
                faInfoIdList = faInfoIdList + "," + DataItems[i].faInfoId;
            } else {
                faInfoIdList = DataItems[i].faInfoId;
            }
        }
        IPLAT.EFInput.value($("#inqu_status-0-faInfoIdList"),faInfoIdList);
        resultAGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "resultA": {
            pageable: {
                pageSize: 100,
                pageSizes: [100, 200, 500, 1000]
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "enter",
                        text: "增加上会资产",
                        layout: "3",
                        click: function () {
                            var checkRows = resultAGrid.getCheckedRows()
                            if (checkRows.length > 0) {
                                if (__ei.discussId != undefined && __ei.discussId != "undefined") {
                                    var eiInfo = new EiInfo();
                                    eiInfo.set("discussId", __ei.discussId);
                                    eiInfo.set("faInfoList", checkRows);
                                    eiInfo.set("lockFlag", 1);
                                    EiCommunicator.send("FASH00", "updateFaInfoLock", eiInfo, {
                                        onSuccess: function (ei) {
                                            addRowsCheck(checkRows);
                                            var pageCount = 0.00;
                                            var DataItems = window.parent.resultGrid.getDataItems();
                                            var numberCount = DataItems.length;
                                            for (let j = 0; j < DataItems.length; j++) {
                                                pageCount += $.isNumeric(DataItems[j].buyCost) ? +DataItems[j].buyCost : 0;
                                            }
                                            window.parent.methods.pageCountCallback(pageCount.toFixed(2));
                                            window.parent.methods.numberCount01Callback(numberCount.toFixed(2));
                                            window.parent.resultGrid.dataSource.page(1);
                                            window.parent['popDataWindow'].close();
                                        }
                                    });
                                } else {
                                    addRowsCheck(checkRows);
                                    window.parent['popDataWindow'].close();
                                    var netValueCount = 0.00;
                                    var pageCount = 0.00;
                                    var DataItems = window.parent.resultGrid.getDataItems();
                                    var numberCount = DataItems.length;
                                    for (let j = 0; j < DataItems.length; j++) {
                                        pageCount += $.isNumeric(DataItems[j].buyCost) ? +DataItems[j].buyCost : 0;
                                        netValueCount += $.isNumeric(DataItems[j].netAssetValue) ? +DataItems[j].netAssetValue : 0;
                                    }
                                    window.parent.methods.pageCount01Callback(pageCount.toFixed(2));
                                    window.parent.methods.netValueCount01Callback(netValueCount.toFixed(2));
                                    window.parent.methods.numberCount01Callback(numberCount.toFixed(2));
                                }
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    },
                ]
            },
            loadComplete: function (grid) {
                var DataItems = window.parent.resultGrid.getDataItems();
                var faInfoIdList = null;
                for (let i = 0; i < DataItems.length; i++) {
                    if (i != 0) {
                        faInfoIdList = faInfoIdList + "," + DataItems[i].faInfoId;
                    } else {
                        faInfoIdList = DataItems[i].faInfoId;
                    }
                }
                IPLAT.EFInput.value($("#inqu_status-0-faInfoIdList"),faInfoIdList);
            },
        },
    }
});

function addRowsCheck(rows) {
    var flag = false;
    var checkRows = window.parent.resultGrid.getDataItems();
    if (checkRows.length > 0) {
        for (var j = 0; j < rows.length; j++) {
            for (var i = 0; i < checkRows.length; i++) {
                if (checkRows[i].goodsNum != rows[j].goodsNum) {
                    flag = true
                } else {
                    flag = false
                    break;
                }
            }
            if (flag) {
                window.parent.resultGrid.addRows(rows[j])
            }
        }
    } else {
        window.parent.resultGrid.addRows(rows)
    }
    window.parent.resultGrid.unCheckAllRows();
}
