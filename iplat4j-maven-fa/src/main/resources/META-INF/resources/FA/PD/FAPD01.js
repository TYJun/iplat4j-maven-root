$(function () {
    /**回车键查询**/
    keydown("inqu", "QUERY");

    $("#QUERY").on("click", function (e) {
        resultAGrid.dataSource.page(1);
        resultBGrid.dataSource.page(1);
    });

    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultAGrid.dataSource.page(1);
        resultBGrid.dataSource.page(1);
    });

    IPLATUI.EFTab = {
        "FaDaTab": {
            select: function (e) {
                var grid = $(e.contentElement).find("div.k-grid").data("kendoGrid")
                console.log(grid)
                if (grid != undefined) {
                    setTimeout(function() {
                        grid.dataSource.page(1);
                    },500)
                }
            }
        },
    }

    IPLATUI.EFGrid = {
        "resultA": {
            pageable: {
                pageSize: 50,
                pageSizes: [50, 100, 500, 1000]
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    // {
                    // 	name : "look",
                    // 	text : "详情",
                    // 	layout : "3",
                    // 	click : function() {
                    // 		var checkRows = resultAGrid.getCheckedRows();
                    // 		if (checkRows.length > 0){
                    // 			popDataWindow.setOptions({"title":"固定资产盘点单详情"});
                    // 			fixedAssetsDetailWindow("look",checkRows[0].faInventoryId);
                    // 		} else {
                    // 			NotificationUtil("请先选择一条记录","warning");
                    // 		}
                    // 	}
                    // },
                    {
                        name: "enter",
                        text: "盘点单生成",
                        layout: "3",
                        click: function () {
                            popDataWindow.setOptions({"title": "资产盘点单录入"});
                            fixedAssetsWindow("enter", "");
                        }
                    },
                    // {
                    // 	name : "edit",
                    // 	text : "编辑",
                    // 	layout : "3",
                    // 	click : function() {
                    // 		var checkRows = resultAGrid.getCheckedRows();
                    // 		if (checkRows.length > 0){
                    // 			// 0 - 待盘点
                    // 			if ("0" == checkRows[0].statusCode) {
                    // 				popDataWindow.setOptions({"title":"固定资产盘点单编辑"});
                    // 				fixedAssetsWindow("edit",checkRows[0].faInventoryId,"","");
                    // 			} else {
                    // 				NotificationUtil("已确认的盘点单无法编辑", "warning");
                    // 			}
                    // 		} else {
                    // 			NotificationUtil("请先选择一条记录","warning");
                    // 		}
                    // 	}
                    // },
                    {
                        name: "remove",
                        text: "删除",
                        layout: "3",
                        click: function () {
                            var checkRows = resultAGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                var faInventoryId = [];
                                for (let i = 0; i < checkRows.length; i++) {
                                    // 0 - 待盘点
                                    if (checkRows[i].statusCode == "待盘点") {
                                        faInventoryId.push(checkRows[i].faInventoryId)
                                    } else {
                                        NotificationUtil("已操作的盘点单无法删除", "warning");
                                        return
                                    }
                                }
                                IPLAT.confirm({
                                    message: '<b>将删除所勾选的资产盘点单信息！</br><font color="red">是否确定？</font></b>',
                                    okFn: function (e) {
                                        var eiInfo = new EiInfo();
                                        eiInfo.set("faInventoryId", faInventoryId);
                                        EiCommunicator.send("FAPD0101", "removeFaInventoryInfo", eiInfo, {
                                            onSuccess: function (ei) {
                                                resultAGrid.dataSource.page(1);
                                                resultBGrid.dataSource.page(1);
                                            }
                                        });
                                    },
                                });
                            } else {
                                NotificationUtil("请先选择一条记录", "warning");
                            }
                        }
                    },
                    {
                        name: "confirm",
                        text: "确认完结",
                        layout: "3",
                        click: function () {
                            var checkRows = resultAGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                var faInventoryId = [];
                                var faInventoryNo = [];
                                for (let i = 0; i < checkRows.length; i++) {
                                    if ("已盘点" == checkRows[i].statusCode) {
                                        faInventoryId.push(checkRows[i].faInventoryId)
                                        faInventoryNo.push(checkRows[i].inventoryNo)
                                    } else {
                                        NotificationUtil("请选择已盘点的盘点单完结", "warning");
                                        return
                                    }
                                }
                                IPLAT.confirm({
                                    message: '<b>将确认所勾选的资产盘点单信息！</br><font color="red">是否确定？</font></b>',
                                    okFn: function (e) {
                                        var eiInfo = new EiInfo();
                                        eiInfo.set("faInventoryId", faInventoryId);
                                        eiInfo.set("faInventoryNo", faInventoryNo);
                                        // eiInfo.set("archiveFlag", checkRows().archiveFlag);
                                        EiCommunicator.send("FAPD0101", "confirmFaInventoryInfo", eiInfo, {
                                            onSuccess: function (ei) {
                                                resultAGrid.dataSource.page(1);
                                            }
                                        });
                                    },
                                });
                            }
                        }
                    },
                    // {
                    // 	name : "print",
                    // 	text : "打印",
                    // 	layout : "3",
                    // 	click : function() {
                    // 		alert("帆软报表")
                    // 	}
                    // },
                ]
            },
            onCellClick: function (e) {
                if (e.field === "inventoryNo") {
                    popDataWindow.setOptions({"title": "资产盘点单详情"});
                    fixedAssetsWindow("edit", e.model.faInventoryId);
                }
            },
            loadComplete: function (grid) {
                $("#APPROVAL").on("click", function (e) {
                    var checkRows = resultAGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        // 0 - 待盘点
                        if ("0" == checkRows[0].statusCode) {
                            IPLAT.confirm({
                                message: '<b>将确认所勾选的资产盘点单信息！</br><font color="red">是否确定？</font></b>',
                                okFn: function (e) {
                                    var eiInfo = new EiInfo();
                                    eiInfo.set("faInventoryId", checkRows[0].faInventoryId);
                                    eiInfo.set("archiveFlag", checkRows[0].archiveFlag);
                                    EiCommunicator.send("FAPD0101", "confirmFaInventoryInfo", eiInfo, {
                                        onSuccess: function (ei) {
                                            resultAGrid.dataSource.page(1);
                                            resultBGrid.dataSource.page(1);
                                        }
                                    });
                                },
                            });
                        } else {
                            NotificationUtil("已确认的盘点单无需重复确认", "warning");
                        }
                    } else {
                        NotificationUtil("请先选择一条记录", "warning");
                    }
                });
            }
        },
        "resultB": {
            pageable: {
                pageSize: 50,
                pageSizes: [50, 100, 500, 1000]
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "look",
                        text: "详情",
                        layout: "3",
                        click: function () {
                            var checkRows = resultBGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                popDataWindow.setOptions({"title": "资产盘点单详情"});
                                fixedAssetsDetailWindow("look", checkRows[0].faInventoryId, checkRows[0].inventoryNo);
                            } else {
                                NotificationUtil("请先选择一条记录", "warning");
                            }
                        }
                    },
                    {
                        name: "print",
                        text: "打印盘点单",
                        layout: "3",
                        click: function () {
                            var checkRows = resultBGrid.getCheckedRows();
                            if (checkRows.length == 1) {
                                var eiInfo = new EiInfo();
                                // 调用小代码
                                EiCommunicator.send("FAAP01", "automaticallyURL", eiInfo, {
                                    onSuccess: function (ei) {
                                        // 当前页面地址
                                        var pageUrl = window.location.href;
                                        // 获取报表地址前袋
                                        var baseUrl = pageUrl.split('/')[0]+"//"+pageUrl.split('/')[2]+"/";
                                        var BaseUrl = "fr/ReportServer?reportlet=v5stable/";
                                        if (ei.extAttr.url != undefined) {
                                            BaseUrl = ei.extAttr.url;
                                        }
                                        console.log(baseUrl)
                                        // 适用于直接点击超链接，浏览器会自动补全格式
                                        var url = baseUrl + BaseUrl + "15.梅州市人民医院固定资产盘点单.cpt&inventoryNo=" + checkRows[0].inventoryNo;
                                        console.log(url)
                                        var popData = $("#popData");
                                        popData.data("kendoWindow").setOptions({
                                            open: function () {
                                                popData.data("kendoWindow").refresh({
                                                    url: url
                                                });
                                            }
                                        });
                                        popDataWindow.setOptions({"title": ""});
                                        popDataWindow.open().center();
                                    },
                                });
                            } else {
                                NotificationUtil("请先选择一条记录", "warning");
                            }
                        }
                    },
                ]
            },
            onCellClick: function (e) {
                if (e.field === "inventoryNo") {
                    popDataWindow.setOptions({"title": "资产盘点单详情"});
                    fixedAssetsDetailWindow("look", e.model.faInventoryId, e.model.inventoryNo);
                }
            },
            loadComplete: function (grid) {

            }
        },
    }
});

// 自定义资产弹窗
function fixedAssetsWindow(type, id, typeId, typeName) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FAPD0101?initLoad&faInventoryId=" + id + "&type=" + type + "&typeId=" + typeId + "&typeName=" + typeName;
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

function fixedAssetsDetailWindow(type, id, inventoryNo) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FAPD0102?initLoad&faInventoryId=" + id + "&type=" + type + "&inventoryNo=" + inventoryNo;
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