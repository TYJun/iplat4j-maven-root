$(function () {
    $("#QUERY").on("click", function (e) {
        resultAGrid.dataSource.page(1);
        resultBGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
        resultDGrid.dataSource.page(1);
        // resultEGrid.dataSource.page(1);
    });

    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultAGrid.dataSource.page(1);
        resultBGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
        resultDGrid.dataSource.page(1);
        // resultEGrid.dataSource.page(1);
    });

    IPLATUI.EFTab = {
        "FaDaTab": {
            select: function (e) {
                var grid = $(e.contentElement).find("div.k-grid").data("kendoGrid")
                if (grid != undefined) {
                    if (grid.options.blockId == "resultA" || grid.options.blockId == "resultD") {
                        $("#one").show();
                        $("#discuss").hide();
                    } else {
                        $("#one").hide();
                        $("#discuss").show();
                    }
                    setTimeout(function () {
                        grid.dataSource.page(1);
                    }, 500)
                }
            }
        },
    }

    IPLATUI.EFGrid = {
        "resultA": {
            pageable: {
                pageSize: 15
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "enter",
                        text: "新建上会资产单",
                        layout: "3",
                        click: function () {
                            var checkRows = resultAGrid.getCheckedRows()
                            if (checkRows.length > 0) {
                                popDataWindow.setOptions({"title": "新建资产上会单"});
                                fixedAssetsWindow()
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    },
                    // {
                    //     name: "update",
                    //     text: "补充上会资产单",
                    //     layout: "3",
                    //     click: function () {
                    //         var checkRows = resultAGrid.getCheckedRows()
                    //         if (checkRows.length > 0) {
                    //             popDataWindow.setOptions({"title": "补充资产上会单"});
                    //             fixedAssetsWindow()
                    //         } else {
                    //             NotificationUtil("请选择一条记录", "warning");
                    //         }
                    //     }
                    // },
                ]
            },
            loadComplete: function (grid) {

            }
        },
        "resultB": {
            pageable: {
                pageSize: 15
            },
            onCellClick: function (e) {
                if (e.field === "discussNo") {
                    popDataWindow.setOptions({"title": "资产上会单详情"});
                    fixedAssetsWindowDetail(e.model.discussId, e.model.discussNo, e.model.discussName, e.model.discussDate, "wasting")
                }
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "enter",
                        text: "上会单通过",
                        layout: "3",
                        click: function () {
                            if (resultAGrid.getDataItems.length > 0) {
                                resultAGrid.unCheckAllRows()
                            }
                            var checkRows = resultBGrid.getCheckedRows()
                            if (checkRows.length > 0) {
                                popDataWindow.setOptions({"title": "资产上会单详情"});
                                fixedAssetsWindowDetail(checkRows[0].discussId, checkRows[0].discussNo, checkRows[0].discussName, checkRows[0].discussDate, "wasting")
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    },
                ]
            },
            loadComplete: function (grid) {

            }
        },
        "resultC": {
            pageable: {
                pageSize: 15
            },
            onCellClick: function (e) {
                if (e.field === "discussNo") {
                    popDataWindow.setOptions({"title": "销账单详情"});
                    fixedAssetsWindowDetail(e.model.discussId, e.model.discussNo, e.model.discussName, e.model.discussDate, "wasted")
                }
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "enter",
                        text: "上会单销账",
                        layout: "3",
                        click: function () {
                            if (resultAGrid.getDataItems.length > 0) {
                                resultAGrid.unCheckAllRows()
                            }
                            var checkRows = resultCGrid.getCheckedRows()
                            if (checkRows.length > 0) {
                                popDataWindow.setOptions({"title": "销账单详情"});
                                fixedAssetsWindowDetail(checkRows[0].discussId, checkRows[0].discussNo, checkRows[0].discussName, checkRows[0].discussDate, "wasted")
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    },
                ]
            },
            loadComplete: function (grid) {

            }
        },
        "resultD": {
            pageable: {
                pageSize: 15
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: []
            },
            loadComplete: function (grid) {

            }
        },
        // "resultE": {
        //     pageable: {
        //         pageSize: 15
        //     },
        //     toolbarConfig:{
        //         hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
        //         add: false, cancel: false, save: false, 'delete': false,
        //         buttons:[
        //             {
        //                 name: "printScraping",
        //                 text: "预报废汇总表",
        //                 layout: "3",
        //                 click: function () {
        //                     var checkRows = resultBGrid.getCheckedRows();
        //                     var flag = true;
        //                     if (checkRows.length > 0) {
        //                         NotificationUtil("汇总指定预报废状态的资产信息", "success");
        //                         var faInfoId = [];
        //                         for (let i = 0; i < checkRows.length; i++) {
        //                             if (checkRows[i].statusCode == "预报废") {
        //                                 faInfoId.push(checkRows[i].faInfoId)
        //                             } else {
        //                                 flag = false;
        //                                 NotificationUtil("请选择预报废状态的资产", "warning");
        //                             }
        //                         }
        //                     } else {
        //                         NotificationUtil("汇总所有预报废状态的资产信息", "success");
        //                     }
        //                     if (flag) {
        //                         var eiInfo = new EiInfo();
        //                         // 调用小代码
        //                         EiCommunicator.send("FAAP01", "automaticallyURL", eiInfo, {
        //                             onSuccess: function (ei) {
        //                                 // 当前页面地址
        //                                 var pageUrl = window.location.href;
        //                                 // 获取报表地址前袋
        //                                 var baseUrl = pageUrl.split('/')[0]+"//"+pageUrl.split('/')[2]+"/";
        //                                 var BaseUrl = "fr/ReportServer?reportlet=v5stable/";
        //                                 if (ei.extAttr.url != undefined) {
        //                                     BaseUrl = ei.extAttr.url;
        //                                 }
        //                                 console.log(baseUrl)
        //                                 // 适用于直接点击超链接，浏览器会自动补全格式
        //                                 var url = baseUrl + BaseUrl + "12.梅州市人民医院固定资产预报废汇总表.cpt&faInfoId=" + checkRowsItemById();
        //                                 console.log(url)
        //                                 var popData = $("#popData");
        //                                 popData.data("kendoWindow").setOptions({
        //                                     open: function () {
        //                                         popData.data("kendoWindow").refresh({
        //                                             url: url
        //                                         });
        //                                     }
        //                                 });
        //                                 popDataWindow.setOptions({"title": ""});
        //                                 popDataWindow.open().center();
        //                             },
        //                         });
        //                     }
        //                 }
        //             },
        //         ]
        //     }
        // }
    }
});

// 自定义资产弹窗
function fixedAssetsWindow(type, id, scrappedNo, detailId, fileCode) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FASH0101?initLoad&faScrapId=" + id + "&type=" + type + "&scrappedNo=" + scrappedNo + "&detailId=" + detailId + "&fileCode=" + fileCode;
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

// 资产上会单详情展示
function fixedAssetsWindowDetail(discussId, discussNo, discussName, discussDate, type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FASH0102?initLoad&discussId=" + discussId + "&discussNo=" + discussNo + "&discussName=" + discussName + "&discussDate=" + discussDate + "&type=" + type;
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