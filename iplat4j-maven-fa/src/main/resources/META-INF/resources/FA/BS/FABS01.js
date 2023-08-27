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
                        name: "enter",
                        text: "录入",
                        layout: "3",
                        click: function () {
                            popDataWindow.setOptions({"title": "固定资产报损录入"});
                            fixedAssetsWindow("enter", "");
                        }
                    },
                    {
                        name: "edit",
                        text: "编辑",
                        layout: "3",
                        click: function () {
                            if (checkRows()) {
                                if ("录入" == checkRows().confirmFlagMean) {
                                    popDataWindow.setOptions({"title": "固定资产报损编辑"});
                                    fixedAssetsWindow("edit", checkRows().faReimburseId, "", "");
                                } else {
                                    NotificationUtil("已确认的报损单无法编辑", "warning");
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
                                if ("录入" == checkRows().confirmFlagMean) {
                                    IPLAT.confirm({
                                        message: '<b>将删除所勾选的固定资产报损信息！</br><font color="red">是否确定？</font></b>',
                                        okFn: function (e) {
                                            var eiInfo = new EiInfo();
                                            eiInfo.set("faReimburseId", checkRows().faReimburseId);
                                            eiInfo.set("faInfoId", checkRows().goodsId);
                                            eiInfo.set("archiveFlag", checkRows().archiveFlag);
                                            EiCommunicator.send("FABS0101", "removeFaReimburseInfo", eiInfo, {
                                                onSuccess: function (ei) {
                                                    resultGrid.dataSource.page(1);
                                                }
                                            });
                                        },
                                    });
                                } else {
                                    NotificationUtil("已确认的报损单无法删除", "warning");
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
                    //             if ("录入" == checkRows().confirmFlagMean) {
                    //                 IPLAT.confirm({
                    //                     message: '<b>将确认所勾选的固定资产报损信息！</br><font color="red">是否确定？</font></b>',
                    //                     okFn: function (e) {
                    //                         var eiInfo = new EiInfo();
                    //                         eiInfo.set("faReimburseId", checkRows().faReimburseId);
                    //                         eiInfo.set("faInfoId", checkRows().goodsId);
                    //                         eiInfo.set("archiveFlag", checkRows().archiveFlag);
                    //                         EiCommunicator.send("FABS0101", "confirmFaReimburseInfo", eiInfo, {
                    //                             onSuccess: function (ei) {
                    //                                 resultGrid.dataSource.page(1);
                    //                             }
                    //                         });
                    //                     },
                    //                 });
                    //             } else {
                    //                 NotificationUtil("已确认的报损单无需再次确认", "warning");
                    //             }
                    //         }
                    //     }
                    // },
                ]
            },
            loadComplete: function (grid) {
                $("#APPROVAL").on("click", function (e) {
                    if (checkRows()) {
                        if ("录入" == checkRows().confirmFlagMean) {
                            IPLAT.confirm({
                                message: '<b>将确认所勾选的固定资产报损信息！</br><font color="red">是否确定？</font></b>',
                                okFn: function (e) {
                                    var eiInfo = new EiInfo();
                                    eiInfo.set("faReimburseId", checkRows().faReimburseId);
                                    eiInfo.set("faInfoId", checkRows().goodsId);
                                    eiInfo.set("archiveFlag", checkRows().archiveFlag);
                                    EiCommunicator.send("FABS0101", "confirmFaReimburseInfo", eiInfo, {
                                        onSuccess: function (ei) {
                                            resultGrid.dataSource.page(1);
                                        }
                                    });
                                },
                            });
                        } else {
                            NotificationUtil("已确认的报损单无需再次确认", "warning");
                        }
                    }
                });
                // 录入
                $("#REGISTER").on("click", function (e) {
                    var url = IPLATUI.CONTEXT_PATH + "/web/FABS0101";
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
                $("#EDITINFO").on("click", function (e) {
                    console.log("编辑");
                    // 获取选中的行数据.
                    var checkRows = resultGrid.getCheckedRows();
                    // 判断是否有选中行.
                    if (checkRows.length > 0) {
                        var id = checkRows[0]["id"];
                        console.log(id);
                        var url = IPLATUI.CONTEXT_PATH + "/web/FABS0102?initLoad&id=" + id;
                        var popData = $("#popDataEdit");
                        popData.data("kendoWindow").setOptions({
                            open: function () {
                                popData.data("kendoWindow").refresh({
                                    url: url
                                });
                            }
                        });
                        popDataEditWindow.open().center();
                    } else {
                        NotificationUtil("请选择想要编辑的固资报损信息", "warning")
                    }
                });

                // 确认按钮
                $("#CONFIRM").on("click", function (e) {
                    console.log("确认按钮");
                    // 获取选中的行数据.
                    var checkRows = resultGrid.getCheckedRows();
                    // 判断是否有选中行.
                    if (checkRows.length > 0) {
                        var id = checkRows[0]["id"];
                        var goodsId = checkRows[0]["goodsId"];
                        IPLAT.confirm({
                            message: '<b>将确认该固定资产报损信息！</br><font color="red">是否确定？</font></b>',
                            okFn: function (e) {
                                //提交数据
                                var eiInfo = new EiInfo();
                                eiInfo.set("id", id);
                                eiInfo.set("goodsId", goodsId);
                                EiCommunicator.send("FABS0102", "updateBSStatus", eiInfo, {
                                    onSuccess: function (ei) {
                                        IPLAT.NotificationUtil(ei.msg);
                                        setTimeout(function () {
                                            window.parent.location.reload()
                                        }, 600);
                                    }
                                });
                            },
                            title: '提醒'
                        });
                    } else {
                        NotificationUtil("请选择想要确认的固定资产报损信息", "warning");
                    }
                });

            }
        }
    }
});

// 自定义固定资产弹窗
function fixedAssetsWindow(type, id, typeId, typeName) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FABS0101?initLoad&faReimburseId=" + id + "&type=" + type + "&typeId=" + typeId + "&typeName=" + typeName;
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