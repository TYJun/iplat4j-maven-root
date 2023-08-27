$(function () {
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });

    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "result": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "look",
                        text: "详情",
                        layout: "3",
                        click: function () {
                            if (checkRows()) {
                                popDataWindow.setOptions({"title": "固定资产调拨详情"});
                                fixedAssetsDetailWindow("look", checkRows().faTransferId);
                            }
                        }
                    },
                    {
                        name: "enter",
                        text: "录入",
                        layout: "3",
                        click: function () {
                            popDataWindow.setOptions({"title": "固定资产调拨录入"});
                            fixedAssetsWindow("enter", "");
                        }
                    },
                    {
                        name: "edit",
                        text: "编辑",
                        layout: "3",
                        click: function () {
                            if (checkRows()) {
                                if ("录入" == checkRows().transferStatusCodeMean) {
                                    popDataWindow.setOptions({"title": "固定资产调拨编辑"});
                                    fixedAssetsWindow("edit", checkRows().faTransferId);
                                } else {
                                    NotificationUtil("已确认的调拨单无法编辑", "warning");
                                }
                            }
                        }
                    },
                    {
                        name: "remove",
                        text: "删除",
                        layout: "3",
                        click: function () {
                            if (checkRows()) {
                                if ("录入" == checkRows().transferStatusCodeMean) {
                                    IPLAT.confirm({
                                        message: '<b>将删除所勾选的固定资产调拨信息！</br><font color="red">是否确定？</font></b>',
                                        okFn: function (e) {
                                            var eiInfo = new EiInfo();
                                            eiInfo.set("faTransferId", checkRows().faTransferId);
                                            eiInfo.set("faInfoId", checkRows().goodsId);
                                            eiInfo.set("relateId", checkRows().faTransferId);
                                            eiInfo.set("archiveFlag", checkRows().archiveFlag);
                                            EiCommunicator.send("FADB0101", "removeFaTransferInfo", eiInfo, {
                                                onSuccess: function (ei) {
                                                    resultGrid.dataSource.page(1);
                                                }
                                            });
                                        },
                                    });
                                } else {
                                    NotificationUtil("已确认的调拨单无法删除", "warning");
                                }
                            }
                        }
                    },
                    // {
                    //     name: "confirm",
                    //     text: "确认",
                    //     layout: "3",
                    //     click: function () {
                    //         if (checkRows()) {
                    //             if ("录入" == checkRows().transferStatusCodeMean) {
                    //                 IPLAT.confirm({
                    //                     message: '<b>将确认所勾选的固定资产调拨信息！</br><font color="red">是否确定？</font></b>',
                    //                     okFn: function (e) {
                    //                         var eiInfo = new EiInfo();
                    //                         eiInfo.set("faTransferId", checkRows().faTransferId);
                    //                         eiInfo.set("archiveFlag", checkRows().archiveFlag);
                    //                         eiInfo.set("inventoryDeptNum", checkRows().newUseDeptNum)
                    //                         eiInfo.set("inventorySpotNum", checkRows().newGoodsLocationNum)
                    //                         EiCommunicator.send("FADB0101", "confirmFaTransferInfo", eiInfo, {
                    //                             onSuccess: function (ei) {
                    //                                 if (ei.status == -1){
                    //                                     NotificationUtil(ei.msg, "warning");
                    //                                 }else {
                    //                                     resultGrid.dataSource.page(1);
                    //                                 }
                    //                             },
                    //                         });
                    //                     },
                    //                 });
                    //             } else {
                    //                 NotificationUtil("已确认的调拨单无需再次确认", "warning");
                    //             }
                    //         }
                    //     }
                    // },
                    // {
                    //     name: "print",
                    //     text: "打印",
                    //     layout: "3",
                    //     click: function () {
                    //         alert("帆软报表")
                    //     }
                    // }
                ]
            },
            loadComplete: function (grid) {
                $("#APPROVAL").on("click", function (e) {
                    if (checkRows()) {
                        if ("录入" == checkRows().transferStatusCodeMean) {
                            IPLAT.confirm({
                                message: '<b>将确认所勾选的固定资产调拨信息！</br><font color="red">是否确定？</font></b>',
                                okFn: function (e) {
                                    var eiInfo = new EiInfo();
                                    eiInfo.set("faTransferId", checkRows().faTransferId);
                                    eiInfo.set("archiveFlag", checkRows().archiveFlag);
                                    eiInfo.set("inventoryDeptNum", checkRows().newUseDeptNum)
                                    eiInfo.set("inventorySpotNum", checkRows().newGoodsLocationNum)
                                    EiCommunicator.send("FADB0101", "confirmFaTransferInfo", eiInfo, {
                                        onSuccess: function (ei) {
                                            if (ei.status == -1){
                                                NotificationUtil(ei.msg, "warning");
                                            }else {
                                                resultGrid.dataSource.page(1);
                                            }
                                        },
                                    });
                                },
                            });
                        } else {
                            NotificationUtil("已确认的调拨单无需再次确认", "warning");
                        }
                    }
                });
            }
        }
    }
});

// 自定义固定资产弹窗
function fixedAssetsWindow(type, id) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FADB0101?initLoad&faTransferId=" + id + "&type=" + type;
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

// 自定义固定资产弹窗
function fixedAssetsDetailWindow(type, id) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FADB0102?initLoad&faTransferId=" + id + "&type=" + type;
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