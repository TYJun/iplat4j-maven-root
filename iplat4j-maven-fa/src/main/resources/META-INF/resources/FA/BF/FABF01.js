$(function () {
    /**回车键查询**/
    keydown("inqu", "QUERY");

    $("#QUERY").on("click", function (e) {
        resultAGrid.dataSource.page(1);
        resultGGrid.dataSource.page(1);
        resultBGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
        resultDGrid.dataSource.page(1);
        resultEGrid.dataSource.page(1);
        resultFGrid.dataSource.page(1);
    });

    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultAGrid.dataSource.page(1);
        resultGGrid.dataSource.page(1);
        resultBGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
        resultDGrid.dataSource.page(1);
        resultEGrid.dataSource.page(1);
        resultFGrid.dataSource.page(1);
    });

    IPLATUI.EFTab = {
        "FaDaTab": {
            select: function (e) {
                var grid = $(e.contentElement).find("div.k-grid").data("kendoGrid")
                // console.log(grid)
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
                pageSize: 500,
                pageSizes: [50, 100, 500, 1000]
            },
            onCellClick: function (e) {
                if (e.field === "goodsNum") {
                    popDataWindow.setOptions({"title": "资产卡片详情"});
                    fixedAssetsWindowDetail(e.model.faInfoId)
                }
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "enter",
                        text: "资产报废",
                        layout: "3",
                        click: function () {
                            var checkRows = resultAGrid.getCheckedRows()
                            if (checkRows.length > 0) {
                                for (let i = 0; i < checkRows.length; i++) {
                                    if (checkRows[i].statusCode == "报废中") {
                                        NotificationUtil("权限不足，请选择在用状态的资产", "warning");
                                        return
                                    }
                                }
                                // for (let i = 0; i < checkRows.length; i++) {
                                //     if (__ei.deptName != checkRows[i].deptName) {
                                //         NotificationUtil("权限不足，非当前科室无法进行报废", "warning");
                                //         return;
                                //     }
                                // }
                                // popDataWindow.setOptions({"title": "资产报废录入"});
                                // fixedAssetsWindow("enter", "", "", "", "fileCode");

                                // setConfig("testzw", "赵伟", "FA");
                                setConfig(__ei.workNo, __ei.name, "FA");
                                getSign(fileCode => {
                                    if (fileCode) {
                                        // getSignatureImg(fileCode, "enter")
                                        popDataWindow.setOptions({"title": "资产报废录入"});
                                        fixedAssetsWindow("enter", "", "", "", fileCode);
                                    } else {
                                        NotificationUtil("电子签名不存在", "warning")
                                        return
                                    }
                                })
                            } else {
                                NotificationUtil("请选择需要报废的资产", "warning")
                                return
                            }
                        }
                    },
                ]
            },
            loadComplete: function (grid) {

            }
        },
        // 科室报废审批
        "resultG": {
            pageable: {
                pageSize: 500,
                pageSizes: [50, 100, 500, 1000]
            },
            onCellClick: function (e) {
                if (e.field === "scrappedNo") {
                    popDataWindow.setOptions({"title": "资产报废详情"});
                    fixedAssetsWindow("apply", "", e.model.scrappedNo);
                }
                if (e.field === "goodsNum") {
                    popDataWindow.setOptions({"title": "资产卡片详情"});
                    fixedAssetsWindowDetail(e.model.faInfoId)
                }
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "batchRevocation", text: "撤销", layout: "3",
                        click: function () {
                            var checkedRows = resultGGrid.getCheckedRows();
                            // 报废单号废弃
                            var scrappedNosList = [];
                            // 报废明细id
                            var detailIdList = [];
                            if (checkedRows.length > 0) {
                                IPLAT.confirm({
                                    message: '<b style="color: red">您确定要撤销吗？该操作不可恢复！</b>',
                                    okFn: function (e) {
                                        for (let i = 0; i < checkedRows.length; i++) {
                                            scrappedNosList.push(checkedRows[i].scrappedNo)
                                            detailIdList.push(checkedRows[i].detailId)
                                        }
                                        var eiInfo = new EiInfo();
                                        // eiInfo.set("scrappedNosList", scrappedNosList);
                                        eiInfo.set("detailIdList", detailIdList);
                                        EiCommunicator.send("FABF01", "batchRevocation", eiInfo, {
                                            onSuccess: function (ei) {
                                                resultGGrid.dataSource.page(1);
                                            }
                                        });
                                    }, cancelFn: function (e) {
                                    }
                                })
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    },
                ]
            },
            loadComplete: function (grid) {
                // 科室审批
                $("#DEPTAPPROVAL").on("click", function (e) {
                    // 增加电子签名
                    // setConfig("testzw", "赵伟", "FA");
                    setConfig(__ei.workNo, __ei.name, "FA");
                    getSign(fileCode => {
                        if (fileCode) {
                            var checkedRows = resultGGrid.getCheckedRows();
                            var detailIdList = [];
                            if (checkedRows.length > 0) {
                                IPLAT.confirm({
                                    message: '<b style="color: red">您确定要审批吗？该操作不可恢复！</b>',
                                    okFn: function (e) {
                                        for (let i = 0; i < checkedRows.length; i++) {
                                            detailIdList.push(checkedRows[i].detailId)
                                        }
                                        var eiInfo = new EiInfo();
                                        eiInfo.set("deptFileCode", fileCode);
                                        eiInfo.set("detailIdList", detailIdList);
                                        EiCommunicator.send("FABF01", "batchDeptApproval", eiInfo, {
                                            onSuccess: function (ei) {
                                                resultGGrid.dataSource.page(1);
                                            }
                                        });
                                    }, cancelFn: function (e) {
                                    }
                                })
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                                return
                            }
                        } else {
                            NotificationUtil("电子签名不存在", "warning")
                            return
                        }
                    })
                });
            }
        },
        "resultB": {
            pageable: {
                pageSize: 500,
                pageSizes: [50, 100, 500, 1000]
            },
            onCellClick: function (e) {
                if (e.field === "scrappedNo") {
                    popDataWindow.setOptions({"title": "资产报废详情"});
                    fixedAssetsWindow("apply", "", e.model.scrappedNo);
                }
                if (e.field === "goodsNum") {
                    popDataWindow.setOptions({"title": "资产卡片详情"});
                    fixedAssetsWindowDetail(e.model.faInfoId)
                }
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    // {
                    //     name: "detail", text: "详情", layout: "3",
                    //     click: function () {
                    //         var checkedRows = resultBGrid.getCheckedRows();
                    //         if (checkedRows.length > 0) {
                    //             if (checkedRows.length > 1) {
                    //                 NotificationUtil("请勿选择多条记录", "warning");
                    //             } else {
                    //                 popDataWindow.setOptions({"title": "资产报废详情"});
                    //                 fixedAssetsWindow("apply", "", checkedRows[0].scrappedNo);
                    //             }
                    //         } else {
                    //             NotificationUtil("请选择一条记录", "warning");
                    //         }
                    //     }
                    // },
                    // {
                    //     name: "identification", text: "鉴定科室选择", layout: "3",
                    //     click: function () {
                    //         var checkedRows = resultBGrid.getCheckedRows();
                    //         var faInfoIdsList = [];
                    //         if (checkedRows.length > 0) {
                    //             popDataWindow.setOptions({"title": "选择鉴定科室"});
                    //             fixedAssetsWindowSelect(checkedRows[0].goodsClassifyCode);
                    //         } else {
                    //             NotificationUtil("请选择一条记录", "warning");
                    //         }
                    //     }
                    // }
                ]
            },
            loadComplete: function (grid) {
                // 鉴定科室选择
                $("#IDENTIFICATION").on("click", function (e) {
                    var checkedRows = resultBGrid.getCheckedRows();
                    var faInfoIdsList = [];
                    if (checkedRows.length > 0) {
                        popDataWindow.setOptions({"title": "选择鉴定科室"});
                        fixedAssetsWindowSelect(checkedRows[0].goodsClassifyCode);
                    } else {
                        NotificationUtil("请选择一条记录", "warning");
                    }
                });

                // 不合理申请驳回
                $("#ASSIGNMENTREVOCATION").on("click",function(e){
                    var checkedRows = resultBGrid.getCheckedRows();
                    if (checkedRows.length > 0) {
                        IPLAT.prompt({
                            message: '驳回原因:',
                            okFn: function (e) {
                                var checkedRows = resultBGrid.getCheckedRows();
                                // 报废明细id
                                var detailIdList = [];
                                for (let i = 0; i < checkedRows.length; i++) {
                                    detailIdList.push(checkedRows[i].detailId)
                                }
                                var eiInfo = new EiInfo();
                                eiInfo.set("detailIdList", detailIdList);
                                eiInfo.set("assignmentReason", e);
                                EiCommunicator.send("FABF01", "batchAssignmentReason", eiInfo, {
                                    onSuccess: function (ei) {
                                        resultBGrid.dataSource.page(1);
                                    }
                                });
                            },
                            cancelFn: function (e) {

                            },
                            title: '不合规申请驳回',
                            minWidth: 450
                        });
                    } else {
                        NotificationUtil("请选择一条记录", "warning");
                    }
                });
            }
        },
        "resultC": {
            pageable: {
                pageSize: 500,
                pageSizes: [50, 100, 500, 1000]
            },
            onCellClick: function (e) {
                if (e.field === "scrappedNo") {
                    popDataWindow.setOptions({"title": "资产报废详情"});
                    fixedAssetsWindow("identify", "", e.model.scrappedNo);
                }
                if (e.field === "goodsNum") {
                    popDataWindow.setOptions({"title": "资产卡片详情"});
                    fixedAssetsWindowDetail(e.model.faInfoId)
                }
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: []
            },
            loadComplete: function (grid) {
                // 批量鉴定
                $("#BATCHIDENTIFY").on("click", function (e) {
                    var checkedRows = resultCGrid.getCheckedRows();
                    if (checkedRows.length > 0) {
                        // IPLAT.EFInput.value($("#info-0-identifyFileCode"), "fileCode");
                        // identifyWindow.open().center()
                        // console.log(checkedRows)
                        // setConfig("testzw", "赵伟", "FA");
                        setConfig(__ei.workNo, __ei.name, "FA");
                        getSign(fileCode => {
                            if (fileCode) {
                                IPLAT.EFInput.value($("#info-0-identifyFileCode"), fileCode);
                                // getSignatureImg(fileCode, "identify")
                                // 批量获取调拨单号
                                identifyWindow.open().center()
                            }
                        })
                        // 通过
                        $("#pass").on("click", function (e) {
                            var detailIdList = new Array();
                            var checkedRows = resultCGrid.getCheckedRows();
                            for (let i = 0; i < checkedRows.length; i++) {
                                detailIdList.push(checkedRows[i].detailId)
                            }
                            var identifyReason = $("#identifyReason").val();
                            var identifyFileCode = $("#info-0-identifyFileCode").val();
                            // console.log(identifyReason)
                            if (identifyReason == "" || identifyReason == null) {
                                NotificationUtil("请填写鉴定意见", "warning");
                                return
                            }
                            var eiInfo = new EiInfo();
                            eiInfo.set("type", "pass");
                            eiInfo.set("identifyReason", identifyReason);
                            eiInfo.set("identifyFileCode", identifyFileCode);
                            eiInfo.set("detailIdList", detailIdList);
                            EiCommunicator.send("FABF01", "batchIdentify", eiInfo, {
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
                                        IPLAT.EFInput.value($("#identifyReason"), "");
                                        identifyWindow.close();
                                        resultCGrid.dataSource.page(1);
                                    }
                                }
                            })
                        });
                        // 驳回
                        $("#reject").on("click", function (e) {
                            var detailIdList = new Array();
                            var checkedRows = resultCGrid.getCheckedRows();
                            for (let i = 0; i < checkedRows.length; i++) {
                                detailIdList.push(checkedRows[i].detailId)
                            }
                            var identifyReason = $("#identifyReason").val();
                            var identifyFileCode = $("#info-0-identifyFileCode").val();
                            if (identifyReason == "" || identifyReason == null) {
                                NotificationUtil("请填写鉴定意见", "warning");
                                return
                            }
                            var eiInfo = new EiInfo();
                            eiInfo.set("type", "reject");
                            eiInfo.set("identifyReason", identifyReason);
                            eiInfo.set("identifyFileCode", identifyFileCode);
                            eiInfo.set("detailIdList", detailIdList);
                            console.log(detailIdList)
                            EiCommunicator.send("FABF01", "batchIdentify", eiInfo, {
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
                                        IPLAT.EFInput.value($("#identifyReason"), "");
                                        identifyWindow.close();
                                        resultCGrid.dataSource.page(1);
                                    }
                                }
                            })
                        });
                        // 取消
                        $("#cancel").on("click", function (e) {
                            IPLAT.EFInput.value($("#identifyReason"), "");
                            identifyWindow.close();
                        });
                    } else {
                        NotificationUtil("请选择一条记录", "warning");
                    }
                })
            }
        },
        "resultD": {
            pageable: {
                pageSize: 500,
                pageSizes: [50, 100, 500, 1000]
            },
            onCellClick: function (e) {
                if (e.field === "scrappedNo") {
                    popDataWindow.setOptions({"title": "资产报废详情"});
                    fixedAssetsWindow("function", "", e.model.scrappedNo);
                }
                if (e.field === "goodsNum") {
                    popDataWindow.setOptions({"title": "资产卡片详情"});
                    fixedAssetsWindowDetail(e.model.faInfoId)
                }
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: []
            },
            loadComplete: function (grid) {
                // 批量确认
                $("#BATCHFUNCTION").on("click", function (e) {
                    var checkedRows = resultDGrid.getCheckedRows();
                    if (checkedRows.length > 0) {
                        // console.log(checkedRows)
                        // IPLAT.EFInput.value($("#info-0-functionFileCode"), "fileCode");
                        // functionWindow.open().center()
                        // setConfig("testzw", "赵伟", "FA");
                        setConfig(__ei.workNo, __ei.name, "FA");
                        getSign(fileCode => {
                            if (fileCode) {
                                IPLAT.EFInput.value($("#info-0-functionFileCode"), fileCode);
                                // getSignatureImg(fileCode, "function")
                                // 批量获取调拨单号
                                functionWindow.open().center()
                            }
                        })
                        // 通过
                        $("#functionPass").on("click", function (e) {
                            var detailIdList = new Array();
                            var checkedRows = resultDGrid.getCheckedRows();
                            for (let i = 0; i < checkedRows.length; i++) {
                                detailIdList.push(checkedRows[i].detailId)
                            }
                            var functionReason = $("#functionReason").val();
                            var functionFileCode = $("#info-0-functionFileCode").val();
                            if (functionReason == "" || functionReason == null) {
                                NotificationUtil("请填写归口意见", "warning");
                                return
                            }
                            var eiInfo = new EiInfo();
                            eiInfo.set("type", "pass");
                            eiInfo.set("functionReason", functionReason);
                            eiInfo.set("functionFileCode", functionFileCode);
                            eiInfo.set("detailIdList", detailIdList);
                            EiCommunicator.send("FABF01", "batchFunction", eiInfo, {
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
                                        IPLAT.EFInput.value($("#functionReason"), "");
                                        functionWindow.close();
                                        resultDGrid.dataSource.page(1);
                                    }
                                }
                            })
                        });
                        // 驳回
                        $("#functionReject").on("click", function (e) {
                            var detailIdList = new Array();
                            var checkedRows = resultDGrid.getCheckedRows();
                            for (let i = 0; i < checkedRows.length; i++) {
                                detailIdList.push(checkedRows[i].detailId)
                            }
                            var functionReason = $("#functionReason").val();
                            var functionFileCode = $("#info-0-functionFileCode").val();
                            if (functionReason == "" || functionReason == null) {
                                NotificationUtil("请填写归口意见", "warning");
                                return
                            }
                            var eiInfo = new EiInfo();
                            eiInfo.set("type", "reject");
                            eiInfo.set("functionReason", functionReason);
                            eiInfo.set("functionFileCode", functionFileCode);
                            eiInfo.set("detailIdList", detailIdList);
                            EiCommunicator.send("FABF01", "batchFunction", eiInfo, {
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
                                        IPLAT.EFInput.value($("#functionReason"), "");
                                        functionWindow.close();
                                        resultDGrid.dataSource.page(1);
                                    }
                                }
                            })
                        });
                        // 取消
                        $("#functionCancel").on("click", function (e) {
                            IPLAT.EFInput.value($("#functionReason"), "");
                            functionWindow.close();
                        });
                    } else {
                        NotificationUtil("请选择一条记录", "warning");
                    }
                })
            }
        },
        "resultE": {
            pageable: {
                pageSize: 500,
                pageSizes: [50, 100, 500, 1000]
            },
            // toolbarConfig: {
            //     hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            //     add: false, cancel: false, save: false, 'delete': false,
            //     buttons: [
            //         {
            //             name: "approval",
            //             text: "资产管理",
            //             layout: "3",
            //             click: function () {
            //                 var checkedRows = resultEGrid.getCheckedRows();
            //                 if (checkedRows.length > 0) {
            //                     if (checkedRows.length > 1) {
            //                         NotificationUtil("请勿选择多条记录", "warning");
            //                     } else {
            //                         popDataWindow.setOptions({"title": "资产报废详情"});
            //                         fixedAssetsWindow("asset", "", checkedRows[0].scrappedNo);
            //                     }
            //                 } else {
            //                     NotificationUtil("请选择一条记录", "warning");
            //                 }
            //             }
            //         },
            //     ]
            // },
            onCellClick: function (e) {
                if (e.field === "scrappedNo") {
                    popDataWindow.setOptions({"title": "资产报废详情"});
                    fixedAssetsWindow("asset", "", e.model.scrappedNo);
                }
                if (e.field === "goodsNum") {
                    popDataWindow.setOptions({"title": "资产卡片详情"});
                    fixedAssetsWindowDetail(e.model.faInfoId)
                }
            },
            loadComplete: function (grid) {
                // 资产管理
                $("#DETAIL").on("click", function (e) {
                    var checkedRows = resultEGrid.getCheckedRows();
                    if (checkedRows.length > 0) {
                        if (checkedRows.length > 1) {
                            NotificationUtil("请勿选择多条记录", "warning");
                        } else {
                            popDataWindow.setOptions({"title": "资产报废详情"});
                            fixedAssetsWindow("asset", "", checkedRows[0].scrappedNo);
                        }
                    } else {
                        NotificationUtil("请选择一条记录", "warning");
                    }
                })
                // 批量审批
                $("#APPROVAL").on("click", function (e) {
                    var checkedRows = resultEGrid.getCheckedRows();
                    if (checkedRows.length > 0) {
                        // console.log(checkedRows)
                        // IPLAT.EFInput.value($("#info-0-assetFileCode"), "fileCode");
                        // acceptWindow.open().center()
                        // setConfig("testzw", "赵伟", "FA");
                        setConfig(__ei.workNo, __ei.name, "FA");
                        getSign(fileCode => {
                            if (fileCode) {
                                IPLAT.EFInput.value($("#info-0-assetFileCode"), fileCode);
                                // getSignatureImg(fileCode, "asset")
                                // 批量获取调拨单号
                                acceptWindow.open().center()
                            }
                        })
                        // 通过
                        $("#acceptPass").on("click", function (e) {
                            var scrappedNoList = new Array();
                            var detailIdList = new Array();
                            var checkedRows = resultEGrid.getCheckedRows();
                            for (let i = 0; i < checkedRows.length; i++) {
                                scrappedNoList.push(checkedRows[i].scrappedNo)
                                detailIdList.push(checkedRows[i].detailId)
                            }
                            var assetReason = $("#assetReason").val();
                            var assetFileCode = $("#info-0-assetFileCode").val();
                            if (assetReason == "" || assetReason == null) {
                                NotificationUtil("请填写审批意见", "warning");
                                return
                            }
                            var eiInfo = new EiInfo();
                            eiInfo.set("type", "pass");
                            eiInfo.set("assetReason", assetReason);
                            eiInfo.set("assetFileCode", assetFileCode);
                            // eiInfo.set("scrappedNoList", scrappedNoList);
                            eiInfo.set("detailIdList", detailIdList);
                            EiCommunicator.send("FABF01", "batchApproval", eiInfo, {
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
                                        IPLAT.EFInput.value($("#assetReason"), "");
                                        acceptWindow.close();
                                        resultEGrid.dataSource.page(1);
                                    }
                                }
                            })
                        });
                        // 驳回
                        $("#acceptReject").on("click", function (e) {
                            var scrappedNoList = new Array();
                            var detailIdList = new Array();
                            var checkedRows = resultEGrid.getCheckedRows();
                            for (let i = 0; i < checkedRows.length; i++) {
                                scrappedNoList.push(checkedRows[i].scrappedNo)
                                detailIdList.push(checkedRows[i].detailId)
                            }
                            var assetReason = $("#assetReason").val();
                            var assetFileCode = $("#info-0-assetFileCode").val();
                            if (assetReason == "" || assetReason == null) {
                                NotificationUtil("请填写审批意见", "warning");
                                return
                            }
                            var eiInfo = new EiInfo();
                            eiInfo.set("type", "reject");
                            eiInfo.set("assetReason", assetReason);
                            eiInfo.set("assetFileCode", assetFileCode);
                            // eiInfo.set("scrappedNoList", scrappedNoList);
                            eiInfo.set("detailIdList", detailIdList);
                            EiCommunicator.send("FABF01", "batchApproval", eiInfo, {
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
                                        IPLAT.EFInput.value($("#assetReason"), "");
                                        acceptWindow.close();
                                        resultEGrid.dataSource.page(1);
                                    }
                                }
                            })
                        });
                        // 取消
                        $("#acceptCancel").on("click", function (e) {
                            IPLAT.EFInput.value($("#assetReason"), "");
                            acceptWindow.close();
                        });
                    } else {
                        NotificationUtil("请选择一条记录", "warning");
                    }
                })
            }
        },
        "resultF": {
            pageable: {
                pageSize: 500,
                pageSizes: [50, 100, 500, 1000]
            },
            onCellClick: function (e) {
                if (e.field === "scrappedNo") {
                    popDataWindow.setOptions({"title": "资产报废详情"});
                    fixedAssetsWindow("all", "", e.model.scrappedNo);
                }
                if (e.field === "goodsNum") {
                    popDataWindow.setOptions({"title": "资产报废详情"});
                    fixedAssetsWindow("all", "", e.model.scrappedNo, e.model.goodsNum);
                    // popDataWindow.setOptions({"title": "资产卡片详情"});
                    // fixedAssetsWindowDetail(e.model.faInfoId)
                }
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    // {
                    //     name: "detail",
                    //     text: "详情",
                    //     layout: "3",
                    //     click: function () {
                    //         var checkedRows = resultFGrid.getCheckedRows();
                    //         if (checkedRows.length > 0) {
                    //             if (checkedRows.length > 1) {
                    //                 NotificationUtil("请勿选择多条记录", "warning");
                    //             } else {
                    //                 popDataWindow.setOptions({"title": "资产报废详情"});
                    //                 fixedAssetsWindow("all", "", checkedRows[0].scrappedNo);
                    //             }
                    //         } else {
                    //             NotificationUtil("请选择一条记录", "warning");
                    //         }
                    //     }
                    // },
                    {
                        name: "printInScrap",
                        text: "报废单打印",
                        layout: "3",
                        click: function () {
                            var checkRows = resultFGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                var arr = "";
                                for (let i = 0; i < checkRows.length; i++) {
                                    arr += checkRows[i].faInfoId + "%27,%27";
                                    // 帆软转义 ' 为 %27
                                    // arr += checkRows[i]["faInfoId"] +  "','" ;
                                }
                                console.log(arr)
                                if (arr.length > 0) {
                                    arr = arr.substr(0, arr.length - 7);
                                }
                                // console.log(arr)
                                // 调用小代码
                                var eiInfo = new EiInfo();
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
                                        // console.log(baseUrl)
                                        // 适用于直接点击超链接，浏览器会自动补全格式
                                        var url = baseUrl + BaseUrl + "13.梅州市人民医院固定资产报废申请表.cpt&faInfoId=" + arr;
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
                    }
                ]
            }
        }
    }
});

// 自定义资产弹窗
function fixedAssetsWindow(type, id, scrappedNo, detailId, fileCode) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FABF0101?initLoad&faScrapId=" + id + "&type=" + type + "&scrappedNo=" + scrappedNo + "&detailId=" + detailId + "&fileCode=" + fileCode;
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

// 资产鉴定科室选择
function fixedAssetsWindowSelect(goodsClassifyCode) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FABF00?initLoad&goodsClassifyCode=" + goodsClassifyCode;
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