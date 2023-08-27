$(function () {
    $("#QUERY").on("click", function (e) {
        resultAGrid.dataSource.page(1);
        resultBGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
    });

    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultAGrid.dataSource.page(1);
        resultBGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
    });

    IPLATUI.EFTab = {
        "FaDaTab": {
            select: function (e) {
                var grid = $(e.contentElement).find("div.k-grid").data("kendoGrid")
                if (grid != undefined) {
                    grid.dataSource.page(1);
                }
            }
        },
    }

    IPLATUI.EFGrid = {
        "resultA": {
            onCellClick: function (e) {
                if (e.field === "goodsNum") {
                    var faInfoId = e.model['faInfoId'];
                    if (faInfoId != " " && faInfoId != null) {
                        fixedAssetsWindowByNumberAPPROVAL(faInfoId);
                    }
                }
            },
            pageable: {
                pageSize: 15
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    // {
                    //     name: "splitByNumber",
                    //     text: "按数量拆分",
                    //     layout: "3",
                    //     click: function () {
                    //         var checkedRows = resultAGrid.getCheckedRows();
                    //         if (checkedRows.length > 0) {
                    //             popDataWindow.setOptions({"title": "资产按数量拆分"});
                    //             fixedAssetsWindowByNumber(checkedRows[0].faInfoId)
                    //         } else {
                    //             NotificationUtil("请选择一条记录", "warning");
                    //         }
                    //     }
                    // },
                    {
                        name: "splitByValue",
                        text: "按价值拆分",
                        layout: "3",
                        click: function () {
                            var checkedRows = resultAGrid.getCheckedRows();
                            if (checkedRows.length > 0) {
                                // console.log(checkedRows[0])
                                popDataWindow.setOptions({"title": "资产按价值拆分"});
                                fixedAssetsWindowByValue(checkedRows[0].faInfoId, "enter", checkedRows[0].goodsTypeCode)
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    },
                ]
            },
            loadComplete: function (grid) {
                // 审批
                $("#APPROVAL").on("click", function (e) {
                    if (checkRows()) {
                        if (checkRows().lockFlag == "3") {
                            fixedAssetsWindowByNumberAPPROVAL(checkRows().faInfoId);
                        } else {
                            NotificationUtil("请先拆分再审批", "warning");
                        }
                    }
                });
            }
        },
        "resultB": {
            toolbarConfig: {
                hidden: false,
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "detail",
                        text: "详情",
                        layout: "3",
                        click: function () {
                            var checkedRows = resultBGrid.getCheckedRows();
                            if (checkedRows.length > 0) {
                                popDataWindow.setOptions({"title": "资产按价值拆分"});
                                fixedAssetsWindowByValue(checkedRows[0].faInfoId, "confirm")
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    }, {
                        name: "delete",
                        text: "删除",
                        layout: "3",
                        click: function () {

                        }
                    },
                ]
            }
        },
        "resultC": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "detail",
                        text: "详情",
                        layout: "3",
                        click: function () {
                            var checkedRows = resultCGrid.getCheckedRows();
                            if (checkedRows.length > 0) {
                                popDataWindow.setOptions({"title": "资产按价值拆分"});
                                fixedAssetsWindowByValue(checkedRows[0].faInfoId, "look")
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    },
                ]
            }
        },
    }
});

// 自定义资产弹窗
function fixedAssetsWindowByNumber(id) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FACF0101?initLoad&faInfoId=" + id;
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

// 自定义资产弹窗
function fixedAssetsWindowByValue(id, type, goodsTypeCode) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FACF0102?initLoad&faInfoId=" + id + "&type=" + type + "&goodsTypeCode=" + goodsTypeCode;
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

// 自定义资产弹窗
function fixedAssetsWindowByNumberAPPROVAL(id) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FACF0103?initLoad&faInfoId=" + id;
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

function checkRows() {
    const checkRows = resultGrid.getCheckedRows();
    if (checkRows.length < 1) {
        NotificationUtil("请先选择一条记录", "warning");
        return false;
    } else {
        return checkRows[0];
    }
}

function isEmptyStr(s) {
    if (s == null || s === '') {
        return true
    }
    return false
}