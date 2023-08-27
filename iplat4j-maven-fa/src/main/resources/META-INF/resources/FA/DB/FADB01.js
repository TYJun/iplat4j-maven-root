$(function () {
    $("#QUERY").on("click", function (e) {
        resultAGrid.dataSource.page(1);
        // resultBGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
        resultDGrid.dataSource.page(1);
        resultEGrid.dataSource.page(1);
    });

    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultAGrid.dataSource.page(1);
        // resultBGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
        resultDGrid.dataSource.page(1);
        resultEGrid.dataSource.page(1);
    });

    IPLATUI.EFTab = {
        "FaDaTab": {
            select: function (e) {
                var grid = $(e.contentElement).find("div.k-grid").data("kendoGrid")
                if (grid != undefined) {
                    if (grid.options.blockId === "resultA") {
                        $("#one").show();
                        $("#other").hide();
                    } else {
                        $("#one").hide();
                        $("#other").show();
                    }
                    setTimeout(function() {
                        grid.dataSource.page(1);
                    },500)
                }
            }
        }
    }

    IPLATUI.EFGrid = {
        "resultA": {
            pageable: {
                pageSize: 15,
                pageSizes: [15, 20, 50, 100]
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    // {
                    //     name: "transferApply",
                    //     text: "科室调拨申请",
                    //     layout: "3",
                    //     click: function () {
                    //         var checkRows = resultAGrid.getCheckedRows();
                    //         // 是否存在电签--后续补充一下
                    //         if (checkRows.length > 0) {
                    //             var flag = true;
                    //             for (let i = 0; i < checkRows.length; i++) {
                    //                 if (checkRows[i].statusCode == "待用") {
                    //                     NotificationUtil("权限不足，请选择在用状态的资产", "warning");
                    //                     flag = false;
                    //                 }
                    //             }
                    //             if (flag) {
                    //                 // fixedAssetsTransferWindow("enter", "");
                    //                 setConfig(__ei.workNo, __ei.name, "FA");
                    //                 setConfig("testzw", "赵伟", "FA");
                    //                 getSign(fileCode => {
                    //                     if (fileCode) {
                    //                         fixedAssetsTransferWindow("enter", fileCode);
                    //                     } else {
                    //                         NotificationUtil("电子签名校验失败", "warning");
                    //                     }
                    //                 });
                    //             }
                    //         } else {
                    //             NotificationUtil("请选择一条记录", "warning");
                    //         }
                    //         // let eiInfo = new EiInfo();
                    //         // var fileCode = "202303-b8ec5af82a9742d7960985210e2407d92";
                    //         // eiInfo.set("fileCode",fileCode);
                    //         // eiInfo.set("isBackSignatureImg",1);
                    //         // EiCommunicator.send("XQMS03", "verifySignData", eiInfo, {
                    //         //     onSuccess : function(ei) {
                    //         //         console.log(ei)
                    //         //         var signatureImg = ei.extAttr.data.signatureImg;
                    //         //         console.log(signatureImg)
                    //         //         $("#reqPic").html("");
                    //         //         var img = $('<img style="width:100px;height:100px" class="signature">');
                    //         //         img.attr('src', "data:image/jpeg;base64," + signatureImg);
                    //         //         var pic = $("#reqPic");
                    //         //         pic.append(img);
                    //         //     }
                    //         // });
                    //     }
                    // },
                ]
            },
            onCellClick: function (e) {
                if (e.field === "goodsNum") {
                    popDataWindow.setOptions({"title": "资产卡片详情"});
                    fixedAssetsWindowDetail(e.model.faInfoId)
                }
            },
            loadComplete: function (grid) {
                // 仓库调拨申请
                $("#ADMINAPPLY").on("click", function (e) {
                    popDataWindow.setOptions({"title": "仓库调拨申请"});
                    fixedAssetsDetailWindow("admin", "")
                });
                // 科室调拨申请
                $("#TRANSFERAPPLY").on("click",function (e) {
                    popDataWindow.setOptions({"title": "科室调拨申请"});
                    var checkRows = resultAGrid.getCheckedRows();
                    // 是否存在电签--后续补充一下
                    if (checkRows.length > 0) {
                        var flag = true;
                        for (let i = 0; i < checkRows.length; i++) {
                            if (checkRows[i].statusCode == "待用") {
                                NotificationUtil("权限不足，请选择在用状态的资产", "warning");
                                flag = false;
                            }
                        }
                        if (flag) {
                            // fixedAssetsTransferWindow("enter", "");
                            setConfig(__ei.workNo, __ei.name, "FA");
                            // setConfig("testzw", "赵伟", "FA");
                            getSign(fileCode => {
                                if (fileCode) {
                                    fixedAssetsTransferWindow("enter", fileCode);
                                } else {
                                    NotificationUtil("电子签名校验失败", "warning");
                                }
                            });
                        }
                    } else {
                        NotificationUtil("请选择一条记录", "warning");
                    }
                })
            }
        },
        "resultB": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "detail",
                        text: "详情",
                        layout: "3",
                        click: function () {
                            var checkRows = resultBGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                if (checkRows.length > 1) {
                                    NotificationUtil("请勿选择多条记录", "warning");
                                } else {
                                    popDataWindow.setOptions({"title": "资产调拨详情"});
                                    fixedAssetsWindow("confirm", "", checkRows[0].transferNo);
                                }
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    },
                    {
                        name: "batchRevocation",
                        text: "批量撤销",
                        layout: "3",
                        click: function () {
                            var checkRows = resultBGrid.getCheckedRows();
                            var transferNoList = [];
                            if (checkRows.length > 0) {
                                IPLAT.confirm({
                                    message: '<b style="color: red">您确定要撤销吗？该操作不可恢复！</b>',
                                    okFn: function (e) {
                                        var eiInfo = new EiInfo()
                                        for (let i = 0; i < checkRows.length; i++) {
                                            transferNoList.push(checkRows[i].transferNo)
                                        }
                                        eiInfo.set("transferNoList", transferNoList)
                                        // 固资删除
                                        EiCommunicator.send("FADB01", "batchRevocation", eiInfo, {
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
                                    },
                                    cancelFn: function (e) {
                                    }
                                })
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    }
                ]
            },
            loadComplete: function (grid) {

            }
        },
        "resultC": {
            pageable: {
                pageSize: 20,
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "detail",
                        text: "接收科室确认",
                        layout: "3",
                        click: function () {
                            var checkRows = resultCGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                if (checkRows.length > 1) {
                                    NotificationUtil("请勿选择多条记录", "warning");
                                } else {
                                    setConfig(__ei.workNo, __ei.name, "FA");
                                    // setConfig("testzw", "赵伟", "FA");
                                    getSign(fileCode => {
                                        if (fileCode) {
                                            popDataWindow.setOptions({"title": "接收科室确认"});
                                            fixedAssetsWindow("confirm", "", checkRows[0].transferNo, fileCode);
                                        } else {
                                            NotificationUtil("电子签名校验失败", "warning");
                                        }
                                    });

                                }
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    }
                ]
            },
            onCellClick: function (e) {
                if (e.field === "transferNo") {
                    setConfig(__ei.workNo, __ei.name, "FA");
                    // setConfig("testzw", "赵伟", "FA");
                    getSign(fileCode => {
                        if (fileCode) {
                            popDataWindow.setOptions({"title": "接收科室确认"});
                            fixedAssetsWindow("confirm", "", e.model.transferNo, fileCode);
                        } else {
                            NotificationUtil("电子签名校验失败", "warning");
                        }
                    });
                }
            },
            loadComplete: function (grid) {

            }
        },
        "resultD": {
            pageable: {
                pageSize: 20,
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: []
            },
            onCellClick: function (e) {
                if (e.field === "transferNo") {
                    setConfig(__ei.workNo, __ei.name, "FA");
                    // setConfig("testzw", "赵伟", "FA");
                    getSign(fileCode => {
                        if (fileCode) {
                            popDataWindow.setOptions({"title": "资产科审批"});
                            fixedAssetsWindow("audit", "", e.model.transferNo, fileCode);
                        } else {
                            NotificationUtil("电子签名校验失败", "warning");
                        }
                    });

                }
            },
            loadComplete: function (grid) {
                // 批量审批
                $("#BATCHAPPROVAL").on("click", function (e) {
                    var checkRows = resultDGrid.getCheckedRows();
                    var transferNoList = [];
                    var applyDeptList = [];
                    var confirmDeptList = [];
                    if (checkRows.length > 0) {
                        setConfig(__ei.workNo, __ei.name, "FA");
                        // setConfig("testzw", "赵伟", "FA");
                        var auditFileCode;
                        getSign(fileCode => {
                            if (fileCode) {
                                auditFileCode = fileCode;
                                IPLAT.EFInput.value($("#info-0-auditFileCode"),auditFileCode);
                                // getSignatureImg(auditFileCode, "audit")
                                // 批量获取调拨单号
                                acceptWindow.open().center()
                                for (let i = 0; i < checkRows.length; i++) {
                                    transferNoList.push(checkRows[i].transferNo);
                                    applyDeptList.push(
                                        {
                                            applyDeptNum: checkRows[i].applyDeptNum,
                                            applyDeptName: checkRows[i].applyDeptName
                                        }
                                    );
                                    confirmDeptList.push(
                                        {
                                            confirmDeptNum: checkRows[i].confirmDeptNum,
                                            confirmDeptName: checkRows[i].confirmDeptName
                                        }
                                    );
                                }
                                // 通过
                                $("#pass").on("click", function (e) {
                                    var eiInfo = new EiInfo();
                                    var auditReason = IPLAT.EFInput.value($("#info-0-auditReason"));
                                    eiInfo.set("transferNoList", transferNoList);
                                    eiInfo.set("applyDeptList", applyDeptList);
                                    eiInfo.set("confirmDeptList", confirmDeptList);
                                    eiInfo.set("auditReason", auditReason);
                                    eiInfo.set("auditFileCode", auditFileCode);
                                    eiInfo.set("type", "pass");
                                    EiCommunicator.send("FADB01", "batchApproval", eiInfo, {
                                        onSuccess: function (ei) {
                                            if (ei.status == -1) {
                                                NotificationUtil(ei.msg, "warning");
                                            } else {
                                                // IPLAT.alert(
                                                //     ei.msg,
                                                //     function (e) {
                                                //     },
                                                //     "提示",
                                                //     300
                                                // );
                                                acceptWindow.close();
                                                IPLAT.EFInput.value($("#info-0-auditReason"), "");
                                                resultDGrid.dataSource.page(1);
                                            }
                                        }
                                    })
                                });
                                // 驳回
                                $("#reject").on("click", function (e) {
                                    var eiInfo = new EiInfo();
                                    var auditReason = IPLAT.EFInput.value($("#info-0-auditReason"));
                                    eiInfo.set("transferNoList", transferNoList);
                                    eiInfo.set("applyDeptList", applyDeptList);
                                    eiInfo.set("confirmDeptList", confirmDeptList);
                                    eiInfo.set("auditReason", auditReason);
                                    eiInfo.set("auditFileCode", auditFileCode);
                                    eiInfo.set("type", "reject");
                                    EiCommunicator.send("FADB01", "batchApproval", eiInfo, {
                                        onSuccess: function (ei) {
                                            if (ei.status == -1) {
                                                NotificationUtil(ei.msg, "warning");
                                            } else {
                                                // IPLAT.alert(
                                                //     ei.msg,
                                                //     function (e) {
                                                //     },
                                                //     "提示",
                                                //     300
                                                // );
                                                acceptWindow.close();
                                                IPLAT.EFInput.value($("#info-0-auditReason"), "");
                                                resultDGrid.dataSource.page(1);
                                            }
                                        }
                                    })
                                });
                                // 取消
                                $("#cancel").on("click", function (e) {
                                    acceptWindow.close();
                                });
                            } else {
                                NotificationUtil("电子签名校验失败", "warning");
                            }
                        });
                    } else {
                        NotificationUtil("请选择一条记录", "warning");
                    }
                });
            }
        },
        "resultE": {
            pageable: {
                pageSize: 20,
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "detail",
                        text: "详情",
                        layout: "3",
                        click: function () {
                            var checkRows = resultEGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                if (checkRows.length > 1) {
                                    NotificationUtil("请勿选择多条记录", "warning");
                                } else {
                                    popDataWindow.setOptions({"title": "资产调拨详情"});
                                    fixedAssetsWindow("all", "", checkRows[0].transferNo);
                                }
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    },
                    // {
                    //     name: "printInTransfer",
                    //     text: "调拨单打印",
                    //     layout: "3",
                    //     click: function () {
                    //         var checkRows = resultEGrid.getCheckedRows();
                    //         if (checkRows.length > 0) {
                    //             var eiInfo = new EiInfo();
                    //             // 调用小代码
                    //             EiCommunicator.send("FAAP01", "automaticallyURL", eiInfo, {
                    //                 onSuccess: function (ei) {
                    //                     var BaseUrl = "http://121.36.30.129:7225/fr/ReportServer?reportlet=v5stable/";
                    //                     if (ei.extAttr.url != undefined) {
                    //                         BaseUrl = ei.extAttr.url;
                    //                     }
                    //                     // 适用于直接点击超链接，浏览器会自动补全格式
                    //                     var url = BaseUrl + "14.梅州市人民医院固定资产调拨表.cpt&transferNo=" + checkRows[0].transferNo;
                    //                     console.log(url)
                    //                     var popData = $("#popData");
                    //                     popData.data("kendoWindow").setOptions({
                    //                         open: function () {
                    //                             popData.data("kendoWindow").refresh({
                    //                                 url: url
                    //                             });
                    //                         }
                    //                     });
                    //                     popDataWindow.setOptions({"title": ""});
                    //                     popDataWindow.open().center();
                    //                 },
                    //             });
                    //         } elsePRINTINTRANSFER {
                    //             NotificationUtil("请先选择一条记录", "warning");
                    //         }
                    //     }
                    // }
                ]
            },
            onCellClick: function (e) {
                if (e.field === "transferNo") {
                    popDataWindow.setOptions({"title": "资产调拨详情"});
                    fixedAssetsWindow("all", "", e.model.transferNo);
                }
            },
            loadComplete: function (grid) {
                // 打印调拨单PRINTINTRANSFER
                $("#PRINTINTRANSFER").on("click", function (e) {
                    var checkRows = resultEGrid.getCheckedRows();
                    if (checkRows.length > 0) {
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
                                var url = baseUrl + BaseUrl + "14.梅州市人民医院固定资产调拨表.cpt&transferNo=" + checkRows[0].transferNo;
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
                });
            }
        }
    }
});

// 自定义资产弹窗
function fixedAssetsWindow(type, id, transferNo, fileCode) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FADB0101?initLoad&faTransferId=" + id + "&type=" + type + "&transferNo=" + transferNo + "&fileCode=" + fileCode;
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


// 资产详情展示
function fixedAssetsWindowDetail(faInfoId) {
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

function fixedAssetsTransferWindow(type, fileCode) {
    fixedAssetsWindow(type, "", "", fileCode);
}