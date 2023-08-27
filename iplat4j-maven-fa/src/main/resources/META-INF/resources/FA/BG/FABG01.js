$(function () {
    $("#QUERY").on("click", function (e) {
        resultAGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
        // resultBGrid.dataSource.page(1);
    });

    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultAGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
        // resultBGrid.dataSource.page(1);
    });
    IPLATUI.EFTab = {
        "FaDaTab": {
            select: function (e) {
                var grid = $(e.contentElement).find("div.k-grid").data("kendoGrid")
                if (grid != undefined) {
                    setTimeout(function() {
                        grid.dataSource.page(1);
                    },500)
                }
            }
        }
    }
    IPLATUI.EFGrid = {
        "resultA": {
            onCellClick: function (e) {
                if (e.field === "goodsNum") {
                    popDataWindow.setOptions({"title": "资产卡片详情"});
                    fixedAssetsWindowDetail(e.model.faInfoId)
                }
            },
            pageable: {
                pageSize: 15,
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    // {
                    //     name: "modifyType",
                    //     text: "资产大类变更",
                    //     layout: "3",
                    //     click: function () {
                    //         var checkRows = resultAGrid.getCheckedRows();
                    //         if (checkRows.length > 0){
                    //             if (checkRows.length > 1){
                    //                 NotificationUtil("不支持批量变更，请勿多选", "warning");
                    //             } else {
                    //                 if (checkRows[0].lockFlag == "1") {
                    //                     NotificationUtil("该资产已被锁定，请等待解锁", "warning");
                    //                 }else if (checkRows[0].lockFlag == "2") {
                    //                     NotificationUtil("请先撤回或者审批该变更单", "warning");
                    //                 }else if (checkRows[0].lockFlag == "3") {
                    //                     NotificationUtil("该资产正在被拆分，请等待拆分", "warning");
                    //                 } else {
                    //                     popDataWindow.setOptions({"title": "资产大类变更"});
                    //                     fixedAssetsWindow("editType", checkRows[0].id);
                    //                 }
                    //             }
                    //         } else {
                    //             NotificationUtil("请先选择一条记录", "warning");
                    //         }
                    //     }
                    // },
                    {
                        name: "modifyMsg",
                        text: "资产信息变更",
                        layout: "3",
                        click: function () {
                            var checkRows = resultAGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                if (checkRows.length > 1) {
                                    NotificationUtil("不支持批量变更，请勿多选", "warning");
                                } else {
                                    if (checkRows[0].lockFlag == "1") {
                                        NotificationUtil("该资产已被锁定，请等待解锁", "warning");
                                    } else if (checkRows[0].lockFlag == "2") {
                                        NotificationUtil("请先撤回或者审批该变更单", "warning");
                                    } else if (checkRows[0].lockFlag == "3") {
                                        NotificationUtil("该资产正在被拆分，请等待拆分", "warning");
                                    } else {
                                        popDataWindow.setOptions({"title": "资产信息变更"});
                                        fixedAssetsWindow("edit", checkRows[0].id);
                                    }
                                }
                            } else {
                                NotificationUtil("请先选择一条记录", "warning");
                            }
                        }
                    },
                    // {
                    //     name: "modifyValue",
                    //     text: "资产价值变更",
                    //     layout: "3",
                    //     click: function () {
                    //         var checkRows = resultAGrid.getCheckedRows();
                    //         if (checkRows.length > 0){
                    //             if (checkRows.length > 1){
                    //                 NotificationUtil("不支持批量变更，请勿多选", "warning");
                    //             } else {
                    //                 if (checkRows[0].lockFlag == "1") {
                    //                     NotificationUtil("该资产已被锁定，请等待解锁", "warning");
                    //                 }else if (checkRows[0].lockFlag == "2") {
                    //                     NotificationUtil("请先撤回或者审批该变更单", "warning");
                    //                 }else if (checkRows[0].lockFlag == "3") {
                    //                     NotificationUtil("该资产正在被拆分，请等待拆分", "warning");
                    //                 } else {
                    //                     popDataWindow.setOptions({"title": "资产价值变更"});
                    //                     fixedAssetsWindow("editValue", checkRows[0].id);
                    //                 }
                    //             }
                    //         } else {
                    //             NotificationUtil("请先选择一条记录", "warning");
                    //         }
                    //     }
                    // },
                ]
            },
            loadComplete: function (grid) {

            }
        },
        "resultB": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: []
            },
            onCellClick: function (e) {
                if (e.field === "goodsNum") {
                    var faInfoId = e.model['faInfoId'];
                    var type = "look";
                    if (faInfoId != " " && faInfoId != null) {
                        popData(faInfoId, type);
                    }
                }
            },
            loadComplete: function (grid) {
                // 批量审批
                $("#APPROVAL").on("click", function (e) {
                    var checkRows = resultBGrid.getCheckedRows();
                    var idsList = []
                    // checkRows.map( row => idsList.push(row.id))
                    if (checkRows.length > 0) {
                        for (let i = 0; i < checkRows.length; i++) {
                            idsList.push(checkRows[i].id)
                        }
                        var eiInfo = new EiInfo();
                        eiInfo.set("idsList", idsList)
                        EiCommunicator.send("FABG01", "batchApproval", eiInfo, {
                            onSuccess: function (ei) {
                                if (ei.status == -1) {
                                    NotificationUtil(ei.msg, "warning");
                                } else {
                                    IPLAT.alert(
                                        ei.msg,
                                        function (e) {
                                        },
                                        "提示",
                                        300
                                    );
                                    resultBGrid.dataSource.page(1);
                                }
                            }
                        })
                    } else {
                        NotificationUtil("请先选择一条记录", "warning");
                    }
                });

                // 批量驳回
                $("#REVOCATION").on("click", function (e) {
                    var checkRows = resultBGrid.getCheckedRows();
                    var idsList = []
                    // checkRows.map( row => idsList.push(row.id))
                    if (checkRows.length > 0) {
                        for (let i = 0; i < checkRows.length; i++) {
                            idsList.push(checkRows[i].id)
                        }
                        var eiInfo = new EiInfo();
                        eiInfo.set("idsList", idsList)
                        EiCommunicator.send("FABG01", "batchRevocation", eiInfo, {
                            onSuccess: function (ei) {
                                if (ei.status == -1) {
                                    NotificationUtil(ei.msg, "warning");
                                } else {
                                    IPLAT.alert(
                                        ei.msg,
                                        function (e) {
                                        },
                                        "提示",
                                        300
                                    );
                                    resultBGrid.dataSource.page(1);
                                }
                            }
                        })
                    } else {
                        NotificationUtil("请先选择一条记录", "warning");
                    }
                });
            }
        },
        "resultC": {
            pageable: {
                pageSize: 15,
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: []
            },
            onCellClick: function (e) {
                if (e.field === "goodsNum") {
                    var faInfoId = e.model['faInfoId'];
                    var type = "look";
                    if (faInfoId != " " && faInfoId != null) {
                        popData(faInfoId, type);
                    }
                }
            },
        }
    }
});

// 自定义资产弹窗
function fixedAssetsWindow(type, id) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FABG0101?initLoad&faInfoId=" + id + "&type=" + type;
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

// 变更详情
function popData(faInfoId, type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FABG0103?initLoad&faInfoId=" + faInfoId + "&type=" + type;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url,
            });
        }
    });
    popDataWindow.open().center();
}

// 资产详情展示
function fixedAssetsWindowDetail(faInfoId){
    var url = IPLATUI.CONTEXT_PATH + "/web/FADA0102?initLoad&faInfoId=" + faInfoId;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url
            });
        }
    });
    popDataWindow.open().center();
}