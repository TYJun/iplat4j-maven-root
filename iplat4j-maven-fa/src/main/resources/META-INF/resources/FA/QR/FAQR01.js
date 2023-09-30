$(function () {
    $("#QUERY").on("click", function (e) {
        // resultAGrid.dataSource.page(1);
        resultBGrid.dataSource.page(1);
    });

    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        // resultAGrid.dataSource.page(1);
        resultBGrid.dataSource.page(1);
    });

    IPLATUI.EFTab = {
        "FaDaTab": {
            select: function (e) {
                var grid = $(e.contentElement).find("div.k-grid").data("kendoGrid")
                if (grid != undefined) {
                    grid.dataSource.page(1);
                }
            }
        }
    }

    IPLATUI.EFGrid = {
        "resultA": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "batchRevocation",
                        text: "批量撤销",
                        layout: "3",
                        click: function () {
                            var id = [];
                            let checkRows = resultAGrid.getCheckedRows()
                            if (checkRows.length > 0) {
                                for (let i = 0; i < checkRows.length; i++) {
                                    console.log(parseInt(checkRows[i].confirmStatus))
                                    if (checkRows[i].confirmStatus != "已确认") {
                                        NotificationUtil("使用状态已发生改变，请选择已确认状态的记录", "warning");
                                        return
                                    } else {
                                        id[i] = checkRows[i].id
                                    }
                                }
                                IPLAT.confirm({
                                    message: '<b style="color: red">您确定要进行资产确认撤销吗？</b>',
                                    okFn: function (e) {
                                        var eiInfo = new EiInfo();
                                        eiInfo.set("id", id)
                                        // 资产批量确认
                                        EiCommunicator.send("FAQR01", "batchRevocation", eiInfo, {
                                            onSuccess: function (ei) {
                                                if (ei.status == -1) {
                                                    NotificationUtil(ei.msg, "warning");
                                                } else {
                                                    resultAGrid.dataSource.page(1);
                                                    NotificationUtil(ei.msg, "warning");
                                                }
                                            },
                                        })
                                    },
                                    cancelFn: function (e) {
                                    }
                                })
                            } else {
                                NotificationUtil("请先选择至少一条记录", "warning");
                            }
                        }
                    },
                ]
            },
            loadComplete: function (grid) {

            }
        },
        "resultB": {
            pageable: {
                pageSize: 15,
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "createCard",
                        text: "生成卡片",
                        layout: "3",
                        click: function () {
                            var checkedRows = resultBGrid.getCheckedRows();
                            if (checkedRows.length > 0) {
                                if (checkedRows.length > 1) {
                                    NotificationUtil("请勿选择多条记录", "warning");
                                } else {
                                    fixedAssetsWindow("enter", checkedRows[0].id, checkedRows[0].enterBillNo, checkedRows[0].receiveType)
                                }
                            } else {
                                NotificationUtil("请先选择至少一条记录", "warning");
                            }

                        }
                    },/*
                    {
                        name: "batchRemove",
                        text: "批量删除",
                        layout: "3",
                        click: function () {
                            var id = [];
                            let checkRows = resultBGrid.getCheckedRows()
                            if (checkRows.length > 0) {
                                for (let i = 0; i < checkRows.length; i++) {
                                    if (checkRows[i].confirmStatus != "待确认") {
                                        NotificationUtil("资产状态已经改变，请刷新界面", "warning");
                                        return
                                    } else {
                                        id[i] = checkRows[i].id
                                    }
                                }
                                IPLAT.confirm({
                                    message: '<b style="color: red">您确定要进行资产删除吗？</b>',
                                    okFn: function (e) {
                                        var eiInfo = new EiInfo();
                                        eiInfo.set("id", id)
                                        // 资产批量确认
                                        EiCommunicator.send("FAQR01", "batchRemove", eiInfo, {
                                            onSuccess: function (ei) {
                                                if (ei.status == -1) {
                                                    NotificationUtil(ei.msg, "warning");
                                                } else {
                                                    resultBGrid.dataSource.page(1);
                                                    NotificationUtil(ei.msg, "warning");
                                                }
                                            },
                                        })
                                    },
                                    cancelFn: function (e) {
                                    }
                                })
                            } else {
                                NotificationUtil("请先选择至少一条记录", "warning");
                            }
                        }
                    },*/
                ]
            },
            onCellClick: function (e) {
                if (e.field === "enterBillNo") {
                    popDataWindow.setOptions({"title": "资产生成卡片"});
                    fixedAssetsWindow("enter", e.model.faConfirmId, e.model.enterBillNo, e.model.receiveType)
                }
            },
            loadComplete: function (grid) {
                $("#BATCHAPPROVAL").on("click", function (e) {
                    var id = [];
                    let checkRows = resultBGrid.getCheckedRows()
                    if (checkRows.length > 0) {
                        for (let i = 0; i < checkRows.length; i++) {
                            if (parseInt(checkRows[i].confirmStatus) != 0) {
                                NotificationUtil("资产状态已经改变，请刷新界面", "warning");
                                return
                            } else {
                                id[i] = checkRows[i].id
                            }
                        }
                        IPLAT.confirm({
                            message: '<b style="color: red">您确定要进行资产确认吗？</b>',
                            okFn: function (e) {
                                var eiInfo = new EiInfo();
                                eiInfo.set("id", id)
                                // 资产批量确认
                                EiCommunicator.send("FAQR01", "batchApproval", eiInfo, {
                                    onSuccess: function (ei) {
                                        if (ei.status == -1) {
                                            NotificationUtil(ei.msg, "warning");
                                        } else {
                                            resultBGrid.dataSource.page(1);
                                            NotificationUtil(ei.msg, "warning");
                                        }
                                    },
                                })
                            },
                            cancelFn: function (e) {
                            }
                        })
                    } else {
                        NotificationUtil("请先选择至少一条记录", "warning");
                    }
                });
            }
        },
        "resultC": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "batchRecover",
                        text: "批量恢复",
                        layout: "3",
                        click: function () {
                            var id = [];
                            let checkRows = resultCGrid.getCheckedRows()
                            if (checkRows.length > 0) {
                                for (let i = 0; i < checkRows.length; i++) {
                                    if (parseInt(checkRows[i].confirmStatus) != -1) {
                                        NotificationUtil("资产状态已经改变，请刷新界面", "warning");
                                        return
                                    } else {
                                        id[i] = checkRows[i].id
                                    }
                                }
                                IPLAT.confirm({
                                    message: '<b style="color: red">您确定要进行资产恢复吗？</b>',
                                    okFn: function (e) {
                                        var eiInfo = new EiInfo();
                                        eiInfo.set("id", id)
                                        // 资产批量确认
                                        EiCommunicator.send("FAQR01", "batchRecover", eiInfo, {
                                            onSuccess: function (ei) {
                                                if (ei.status == -1) {
                                                    NotificationUtil(ei.msg, "warning");
                                                } else {
                                                    resultCGrid.dataSource.page(1);
                                                    NotificationUtil(ei.msg, "warning");
                                                }
                                            },
                                        })
                                    },
                                    cancelFn: function (e) {
                                    }
                                })
                            } else {
                                NotificationUtil("请先选择至少一条记录", "warning");
                            }
                        }
                    },
                ]
            },
            loadComplete: function (grid) {

            }
        },
    }

    // 自定义资产窗口
    function fixedAssetsWindow(type, id, enterBillNo, receiveType) {
        var url = IPLATUI.CONTEXT_PATH + "/web/FAQR0101?initLoad&faConfirmId=" + id + "&type=" + type + "&enterBillNo=" + enterBillNo
            + "&receiveType=" + receiveType;
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

    function checkRows(needSingle, Grid) {
        console.log(Grid)
        const checkRows = window[Grid].getCheckedRows();
        if (parseInt(checkRows.length) < 1) {
            NotificationUtil("请先选择一条记录", "warning");
            return false;
        } else {
            // 是否需要单选
            if (needSingle) {
                if (parseInt(checkRows.length) > 1) {
                    NotificationUtil("请勿选择多条记录", "warning");
                    return;
                } else {
                    return checkRows[0];
                }
            } else {
                return checkRows;
            }
        }
    }

    function checkRowsItemById() {
        const checkRows = resultGrid.getCheckedRows();
        if (checkRows.length < 1) {
            NotificationUtil("请先选择一条记录", "warning");
            return false;
        } else {
            var arr = "";
            for (var i = 0; i < checkRows.length; i++) {
                arr += checkRows[i]["faInfoId"] + "%27,%27";
                // 帆软转义 ' 为 %27
                // arr += checkRows[i]["faInfoId"] +  "','" ;
            }
            if (arr.length > 0) {
                arr = arr.substr(0, arr.length - 7);
            }
            return arr;
        }
    }
});