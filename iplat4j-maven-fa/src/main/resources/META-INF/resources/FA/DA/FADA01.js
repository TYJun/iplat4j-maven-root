var ws = null, pageCount = 0.00, numberCount = 0.00;
$(function () {
    /**回车键查询**/
    keydown("inqu", "QUERY");

    // webSocketInit(0);
    $("#QUERY").on("click", function (e) {
        // resultAGrid.dataSource.page(1);
        resultBGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
        var eiInfo = new EiInfo();
        eiInfo.set("inqu_status-0-buyDateS", kendo.toString($("#inqu_status-0-buyDateS").data("kendoDatePicker").value(), "yyyy-MM-dd"));
        eiInfo.set("inqu_status-0-buyDateE", kendo.toString($("#inqu_status-0-buyDateE").data("kendoDatePicker").value(), "yyyy-MM-dd"));
        eiInfo.set("inqu_status-0-useDateS", kendo.toString($("#inqu_status-0-useDateS").data("kendoDatePicker").value(), "yyyy-MM-dd"));
        eiInfo.set("inqu_status-0-useDateE", kendo.toString($("#inqu_status-0-useDateE").data("kendoDatePicker").value(), "yyyy-MM-dd"));
        eiInfo.set("inqu_status-0-goodsNum", IPLAT.EFInput.value($("#inqu_status-0-goodsNum")));
        eiInfo.set("inqu_status-0-surpName", IPLAT.EFInput.value($("#inqu_status-0-surpName")));
        eiInfo.set("inqu_status-0-remark", IPLAT.EFInput.value($("#inqu_status-0-remark")));
        eiInfo.set("inqu_status-0-buyCostS", IPLAT.EFInput.value($("#inqu_status-0-buyCostS")));
        eiInfo.set("inqu_status-0-buyCostE", IPLAT.EFInput.value($("#inqu_status-0-buyCostE")));
        eiInfo.set("inqu_status-0-netAssetValueS", IPLAT.EFInput.value($("#inqu_status-0-netAssetValueS")));
        eiInfo.set("inqu_status-0-netAssetValueE", IPLAT.EFInput.value($("#inqu_status-0-netAssetValueE")));
        eiInfo.set("inqu_status-0-useYear", IPLAT.EFInput.value($("#inqu_status-0-useYear")));
        eiInfo.set("inqu_status-0-goodsClassifyCode", IPLAT.EFInput.value($("#inqu_status-0-goodsClassifyCode")));
        eiInfo.set("inqu_status-0-goodsTypeCode", IPLAT.EFInput.value($("#inqu_status-0-goodsTypeCode")));
        eiInfo.set("inqu_status-0-rfidCode", IPLAT.EFInput.value($("#inqu_status-0-rfidCode")));
        eiInfo.set("inqu_status-0-fundingSourceNum", IPLAT.EFSelect.value($("#inqu_status-0-fundingSourceNum")));
        eiInfo.set("inqu_status-0-statusCode", IPLAT.EFSelect.value($("#inqu_status-0-statusCode")));
        eiInfo.set("inqu_status-0-deptName", $("#inqu_status-0-deptName").data("kendoMultiSelect").value());
        EiCommunicator.send("FADA01", "confirmedQuery", eiInfo, {
            onSuccess: function (outInfo) {
                //var outInfo = new EiInfo();
                console.log(outInfo);
                $("#inqu_status-0-buyCostCount").val(outInfo.extAttr.obj[0].buyCostCount);
                $("#inqu_status-0-countAll").val(outInfo.extAttr.obj[0].countAll);
                pageCount = $.isNumeric($("#inqu_status-0-buyCostCount").val()) ? $("#inqu_status-0-buyCostCount").val() : 0;
                numberCount = $.isNumeric($("#inqu_status-0-countAll").val()) ? $("#inqu_status-0-countAll").val() : 0
                $("#pageCount").text(parseInt(pageCount).toFixed(2));
                $("#numberCount").text(parseInt(numberCount).toFixed(2));
            }
        });
    });

    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        $("#inqu_status-0-deptName").val("");
        // resultAGrid.dataSource.page(1);
        resultBGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
    });

    /**
     * 数据导入模板下载
     */
    $("#download").click(function () {
        window.location.href = IPLATUI.CONTEXT_PATH + "/fa/FADAImport";
    });


    $("#IMPORTSUBMIT").on("click", function (e) {
        var uploading = false;
        // 防止连续提交
        $("#SUBMIT").attr("disabled", true);
        setTimeout(function () {
            $("#SUBMIT").attr("disabled", false);
        }, 5000);
        //参数处理
        var form = new FormData();
        form.append("fileUpload", $('#excelFile')[0].files[0]);
        //数据校验
        if ($('#excelFile')[0].files[0] == null) {
            NotificationUtil("请上传文件", "warning");
            return;
        }
        if (uploading) {
            NotificationUtil("数据正在提交中，请不要重复点击提交", "warning");
            return;
        }
        //数据提交
        $.ajax({
            url: IPLATUI.CONTEXT_PATH + "/fa/FADAImport",
            type: 'POST',
            cache: false,
            processData: false,
            contentType: false,
            dataType: 'json',
            data: form,
            beforeSend: function () {
                uploading = true;
            },
            success: function (data) {
                uploading = false;
                if (data.msg == "all") {
                    NotificationUtil("数据导入成功", "success");
                    downloadFileByBase64('data:application/xls;base64,' + data.base64, "FADA_success.xls");
                    window['execlImportWindow'].close();
                    setTimeout(function () {
                        window.parent.location.reload()
                    }, 600);
                } else if (data.msg == "part") {
                    NotificationUtil("导入数据存在问题，请查看返回文件", "warning");
                    downloadFileByBase64('data:application/xls;base64,' + data.base64, "FADA_error.xls");
                    window['execlImportWindow'].close();
                    setTimeout(function () {
                        window.parent.location.reload()
                    }, 600);
                } else if (data.msg == "error") {
                    NotificationUtil("数据导入失败", "warning");
                    window['execlImportWindow'].close();
                }
            }
        });
    });

    /**
     * 关闭数据导入窗口
     */
    $("#IMPORTCLOSE").on("click", function (e) {
        execlImportWindow.close();
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
        },
    }

    IPLATUI.EFGrid = {
        "resultA": {
            toolbarConfig: {
                hidden:false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    // {
                    //     name: "upload",
                    //     text: "数据导入",
                    //     layout: "3",
                    //     click: function () {
                    //         execlImportWindow.open().center()
                    //     }
                    // },
                    {
                        name: "enter",
                        text: "手工录入",
                        layout: "3",
                        click: function () {
                            popDataWindow.setOptions({"title": "资产卡片录入"});
                            fixedAssetsWindow("enter", "");
                        }
                    },
                    {
                        name: "edit",
                        text: "编辑",
                        layout: "3",
                        click: function () {
                            const checkedRows = resultAGrid.getCheckedRows();
                            if (checkedRows.length > 0) {
                                if (checkedRows.length > 1) {
                                    NotificationUtil("请勿选择多条记录", "warning");
                                } else {
                                    popDataWindow.setOptions({"title": "资产卡片编辑"});
                                    fixedAssetsWindow("edit", checkedRows[0].id);
                                }
                            } else {
                                NotificationUtil("请先选择一条记录", "warning");
                            }
                        }
                    },
                    {
                        name: "remove",
                        text: "批量删除",
                        layout: "3",
                        click: function () {
                            var checkRows = resultAGrid.getCheckedRows();
                            var idsList = []
                            if (checkRows.length > 0) {
                                for (let i = 0; i < checkRows.length; i++) {
                                    idsList.push(checkRows[i].id)
                                }
                                IPLAT.confirm({
                                    message: '<b style="color: red">您确定要删除吗？该操作不可恢复！</b>',
                                    okFn: function (e) {
                                        var eiInfo = new EiInfo();
                                        eiInfo.set("idsList", idsList)
                                        // 资产删除
                                        EiCommunicator.send("FADA01", "batchRemove", eiInfo, {
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
                                                    resultAGrid.dataSource.page(1);
                                                }
                                            }
                                        })
                                    },
                                    cancelFn: function (e) {
                                    }
                                })
                            } else {
                                NotificationUtil("请先选择一条记录", "warning");
                            }
                        }
                    },
                ]
            },
            loadComplete: function (grid) {
                ("#ef_grid_result").data("kendoGrid").hideColumn("goodsNum");

                $("#APPROVAL").on("click", function (e) {
                    const checkRows = resultAGrid.getCheckedRows();
                    popDataWindow.setOptions({"title": "资产卡片转移"});
                    fixedAssetsToDeptWindow("enter", "");
                    if (checkRows.length > 0) {
                        IPLAT.confirm({
                            message: '<b style="color: red">您确定要审批启用吗？</b>',
                            okFn: function (e) {
                                var eiInfo = new EiInfo();
                                var faInfoList = [];
                                for (let i = 0; i < checkRows.length; i++) {
                                    var faInfoMap = {
                                        "id": checkRows[i].id,
                                        "deptNum": checkRows[i].deptNum,
                                        "deptName": checkRows[i].deptName,
                                        "installLocationNum": checkRows[i].installLocationNum,
                                        "installLocationName": checkRows[i].installLocation
                                    }
                                    faInfoList.push(faInfoMap);
                                }
                                eiInfo.set("faInfo", faInfoList)
                                // 资产入账
                                EiCommunicator.send("FADA01", "batchRecorded", eiInfo, {
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
                                            resultAGrid.dataSource.page(1);
                                        }
                                    },
                                })
                            },
                            cancelFn: function (e) {
                            }
                        })
                    } else {
                        NotificationUtil("请先选择一条记录", "warning");
                    }
                });
            }
        },
        "resultB": {
            pageable: {
                pageSize: 500,
                pageSizes: [50,100,500,1000]
            },
            onCellClick: function (e) {
                if (e.field === "goodsNum") {
                    popDataWindow.setOptions({"title": "资产卡片详情"});
                    fixedAssetsWindowDetail(e.model.faInfoId)
                }
            },
            toolbarConfig: {
                hidden:false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    /*{
                        name: "enter",
                        text: "手工录入",
                        layout: "3",
                        click: function () {
                            popDataWindow.setOptions({"title": "资产卡片录入"});
                            var checkRows = resultBGrid.getCheckedRows();
                            if (checkRows.length != 1){
                                fixedAssetsWindow("enter", "");
                            } else {
                                IPLAT.confirm({
                                    message: '<b>是否需要进行信息复制？</b></i>',
                                    okFn: function (e) {
                                        var faInfoId = checkRows[0].faInfoId;
                                        fixedAssetsWindow("enter", faInfoId);
                                    },
                                    cancelFn: function (e) {
                                        fixedAssetsWindow("enter", "");
                                    }
                                })
                            }
                        }
                    },*/
                    // {
                    //     name: "writeOff",
                    //     text: "销账",
                    //     layout: "3",
                    //     click: function () {
                    //         var checkRows = resultBGrid.getCheckedRows();
                    //         if (checkRows.length > 0) {
                    //             var faInfoId = [];
                    //             for (let i = 0; i < checkRows.length; i++) {
                    //                 if (checkRows[i].statusCode != "预报废") {
                    //                     NotificationUtil("请选择预报废记录", "warning");
                    //                     return;
                    //                 } else {
                    //                     faInfoId.push(checkRows[i].faInfoId);
                    //                 }
                    //             }
                    //             IPLAT.confirm({
                    //                 message: '<b style="color: red">您确定要销账吗？</b>',
                    //                 okFn: function (e) {
                    //                     var eiInfo = new EiInfo();
                    //                     console.log(faInfoId)
                    //                     eiInfo.set("faInfoId", faInfoId);
                    //                     EiCommunicator.send("FADA01", "writeOff", eiInfo, {
                    //                         onSuccess: function (ei) {
                    //                             if (ei.status == -1) {
                    //                                 NotificationUtil(ei.msg, "warning");
                    //                             } else {
                    //                                 IPLAT.alert(
                    //                                     ei.msg,
                    //                                     function (e) {
                    //                                     },
                    //                                     "提示",
                    //                                     300
                    //                                 );
                    //                                 resultBGrid.dataSource.page(1);
                    //                             }
                    //                         },
                    //                     });
                    //                 },
                    //                 cancelFn: function (e) {
                    //                 }
                    //             })
                    //         } else {
                    //             NotificationUtil("请先选择一条记录", "warning");
                    //         }
                    //     }
                    // },
                    {
                        name: "printCardFinancial",
                        text: "资产卡片-入库财务",
                        layout: "3",
                        click: function () {
                            var checkRows = resultBGrid.getCheckedRows();
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
                                        var url = baseUrl + BaseUrl + "梅州市人民医院固定资产卡片-入库财务.cpt&enterBillNo=" + checkRowsItemByEnterBillNo();
                                        console.log(url)
                                        if (checkRowsItemByOutBillNo()) {
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
                                            // window.open(url)
                                        }
                                    },
                                });
                            } else {
                                NotificationUtil("请先选择一条记录", "warning");
                            }
                        }
                    },
                    {
                        name: "printCardOutFinancial",
                        text: "资产卡片-出库财务",
                        layout: "3",
                        click: function () {
                            var checkRows = resultBGrid.getCheckedRows();
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
                                        var url = baseUrl + BaseUrl + "梅州市人民医院固定资产卡片-财务.cpt&outBillNo=" + checkRowsItemByOutBillNo();
                                        console.log(url)
                                        if (checkRowsItemByOutBillNo()) {
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
                                            // window.open(url)
                                        }
                                    },
                                });
                            } else {
                                NotificationUtil("请先选择一条记录", "warning");
                            }
                        }
                    },
                    {
                        name: "printCard",
                        text: "资产卡片-普通",
                        layout: "3",
                        click: function () {
                            var checkRows = resultBGrid.getCheckedRows();
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
                                        var url = baseUrl + BaseUrl + "11.梅州市人民医院固定资产卡片.cpt&faInfoId=" + checkRowsItemById();
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
                    // {
                    //     name: "printScraping",
                    //     text: "预报废汇总表",
                    //     layout: "3",
                    //     click: function () {
                    //         var checkRows = resultBGrid.getCheckedRows();
                    //         var flag = true;
                    //         if (checkRows.length > 0) {
                    //             NotificationUtil("汇总指定预报废状态的资产信息", "success");
                    //             var faInfoId = [];
                    //             for (let i = 0; i < checkRows.length; i++) {
                    //                 if (checkRows[i].statusCode == "预报废") {
                    //                     faInfoId.push(checkRows[i].faInfoId)
                    //                 } else {
                    //                     flag = false;
                    //                     NotificationUtil("请选择预报废状态的资产", "warning");
                    //                 }
                    //             }
                    //         } else {
                    //             NotificationUtil("汇总所有预报废状态的资产信息", "success");
                    //         }
                    //         if (flag) {
                    //             var eiInfo = new EiInfo();
                    //             // 调用小代码
                    //             EiCommunicator.send("FAAP01", "automaticallyURL", eiInfo, {
                    //                 onSuccess: function (ei) {
                    //                     // 当前页面地址
                    //                     var pageUrl = window.location.href;
                    //                     // 获取报表地址前袋
                    //                     var baseUrl = pageUrl.split('/')[0]+"//"+pageUrl.split('/')[2]+"/";
                    //                     var BaseUrl = "fr/ReportServer?reportlet=v5stable/";
                    //                     if (ei.extAttr.url != undefined) {
                    //                         BaseUrl = ei.extAttr.url;
                    //                     }
                    //                     console.log(baseUrl)
                    //                     // 适用于直接点击超链接，浏览器会自动补全格式
                    //                     var url = baseUrl + BaseUrl + "12.梅州市人民医院固定资产预报废汇总表.cpt&faInfoId=" + checkRowsItemById();
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
                    //         }
                    //     }
                    // },
                    {
                        name: "details",
                        text: "详情",
                        layout: "3",
                        click: function () {
                            var checkRows = resultBGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                if (checkRows.length > 1) {
                                    NotificationUtil("请勿选择多条记录", "warning");
                                } else {
                                    popDataWindow.setOptions({"title": "资产卡片详情"});
                                    fixedAssetsWindowDetail(checkRows[0].id)
                                }
                            } else {
                                NotificationUtil("请先选择一条记录", "warning");
                            }
                        }
                    },
                    // {
                    //     name: "batchRevocation",
                    //     text: "批量驳回",
                    //     layout: "3",
                    //     click: function () {
                    //         var checkRows  = resultBGrid.getCheckedRows();
                    //         var idsList = []
                    //         if (checkRows.length > 0){
                    //             IPLAT.confirm({
                    //                 message: '<b style="color: red">您确定要驳回审批吗？</b>',
                    //                 okFn: function (e) {
                    //                     var eiInfo = new EiInfo();
                    //                     for (let i = 0; i < checkRows.length; i++) {
                    //                         idsList.push(checkRows[i].id);
                    //                     }
                    //                     eiInfo.set("idsList", idsList)
                    //                     // 批量驳回
                    //                     EiCommunicator.send("FADA01", "batchRevocation", eiInfo, {
                    //                         onSuccess: function (ei) {
                    //                             if (ei.status == -1) {
                    //                                 NotificationUtil(ei.msg, "warning");
                    //                             } else {
                    //                                 IPLAT.alert(
                    //                                     ei.msg,
                    //                                     function (e) {},
                    //                                     "提示",
                    //                                     300
                    //                                 );
                    //                                 resultBGrid.dataSource.page(1);
                    //                             }
                    //                         },
                    //                     })
                    //                 },
                    //                 cancelFn: function (e) {
                    //                 }
                    //             })
                    //         } else {
                    //             NotificationUtil("请先选择一条记录", "warning");
                    //         }
                    //     }
                    // }
                ]
            },
            loadComplete: function (grid) {
                $("#ef_grid_toolbar_resultB").prepend("<div style='float:left;font-size:13px;'>" +
                    "当页资产原值总金额：<span id='pageCount' style='color: red'>0.00</span>元，" +
                    "当页资产数量：<span id='numberCount' style='color: red'>0.00</span>条</div>")
                var checkRows = grid.getCheckedRows()
                if (checkRows.length > 0) {
                    grid.addRows(checkRows);
                    grid.unCheckAllRows();
                    numberCount = checkRows.length;
                    for (let i = 0; i < checkRows.length; i++) {
                        pageCount += $.isNumeric(checkRows[i].buyCost) ? +checkRows[i].buyCost : 0;
                        $("#pageCount").text(pageCount.toFixed(2));
                        $("#numberCount").text(numberCount.toFixed(2));
                    }
                } else {
                    if (grid != undefined) {

                    }
                    pageCount = $.isNumeric($("#inqu_status-0-buyCostCount").val()) ? $("#inqu_status-0-buyCostCount").val() : 0;
                    numberCount = $.isNumeric($("#inqu_status-0-countAll").val()) ? $("#inqu_status-0-countAll").val() : 0
                    $("#pageCount").text(parseInt(pageCount).toFixed(2));
                    $("#numberCount").text(parseInt(numberCount).toFixed(2));
                }
            },
            onCheckRow: function (e) {
                let model = e.model;
                var rows = resultBGrid.getCheckedRows();
                pageCount = 0;
                let numberCount = $.isNumeric(rows.length) ? rows.length : 0;
                if (e.checked) {
                    for (let i = 0; i < rows.length; i++) {
                        pageCount += rows[i].buyCost;
                    }
                    // 全选时调用n次单选，加上最后一条记录的金额时，pageMoneySum记录下本页总金额
                } else {
                    for (let i = 0; i < rows.length; i++) {
                        pageCount += rows[i].buyCost;
                    }
                }
                // var getCheckedRows = resultGrid.getCheckedRows().length;
                // $("#checkRowsCount").text(getCheckedRows);
                // if (getCheckedRows == 0) {
                //     pageCount = 0
                //     numberCount = 0
                // }
                $("#pageCount").text(parseInt(pageCount).toFixed(2));
                $("#numberCount").text(parseInt(numberCount).toFixed(2));
            },
        },
        "resultC":{
            pageable: {
                pageSize: 500,
                pageSizes: [50,100,500,1000]
            },
            onCellClick: function (e) {
                if (e.field === "goodsNum") {
                    popDataWindow.setOptions({"title": "资产卡片详情"});
                    fixedAssetsWindowDetail(e.model.faInfoId)
                }
            },
            toolbarConfig:{
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons:[
                    {
                        name: "printLabel",
                        text: "资产标签",
                        layout: "3",
                        click: function () {
                            var checkRows = resultCGrid.getCheckedRows();
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
                                        var url = baseUrl + BaseUrl + "10.梅州市人民医院固定资产标签.cpt&faInfoId=" + checkRowsCItemById();
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
                    {
                        name: "RfidScanAndPrint",
                        text: "批量打印",
                        layout: "3",
                        click: function () {
                            //当选择数据时
                            var checkRows = resultCGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                webSocketInit(0, checkRows);
                            } else {
                                NotificationUtil("请先选择一条记录", "warning");
                            }
                        }
                    },
                    {
                        name: "billing",
                        text: "发卡",
                        layout: "3",
                        click: function () {
                            var checkRows = resultCGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                var eiInfo = new EiInfo();
                                // 调用小代码
                                EiCommunicator.send("FAAP01", "automaticallyIp", eiInfo, {
                                    onSuccess: function (ei) {
                                        var ip = "192.168.1.137";
                                        if (ei.extAttr.ip != undefined) {
                                            ip = ei.extAttr.ip;
                                        }
                                        console.log(ip)
                                        creatConnection(ip, function (connectFlag) {
                                            //连接建立成功
                                            if (connectFlag == true) {
                                                NotificationUtil("正在建立连接，准备读卡...", "success");
                                                //开始读卡
                                                RfidRead(ip, "billing", checkRows[0]);
                                            } else {
                                                NotificationUtil("读卡器连接失败...", "warning")
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
                        name: "reading",
                        text: "读卡",
                        layout: "3",
                        click: function () {
                            var eiInfo = new EiInfo();
                            const ip = "192.168.1.137"
                            // 调用小代码
                            EiCommunicator.send("FAAP01", "automaticallyIp", eiInfo, {
                                onSuccess: function (ei) {
                                    var ip = "192.168.1.137";
                                    if (ei.extAttr.ip != undefined) {
                                        ip = ei.extAttr.ip;
                                    }
                                    console.log(ip)
                                    creatConnection(ip, function (connectFlag) {
                                        //连接建立成功
                                        if (connectFlag == true) {
                                            NotificationUtil("正在建立连接，准备读卡...", "success");
                                            //开始读卡
                                            RfidRead(ip, "reading");
                                        } else {
                                            NotificationUtil("读卡器连接失败...", "warning")
                                        }
                                    });
                                },
                            });
                        }
                    },
                ]
            }
        },
    }

    // 自定义资产窗口
    function fixedAssetsWindow(type, id) {
        var url = IPLATUI.CONTEXT_PATH + "/web/FADA0101?initLoad&faInfoId=" + id + "&type=" + type;
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

    // 自定义资产窗口
    function fixedAssetsToDeptWindow(type, id) {
        var url = IPLATUI.CONTEXT_PATH + "/web/FADA0103?initLoad&faInfoId=" + id + "&type=" + type;
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

    function checkRows(needSingle) {
        const checkRows = resultGrid.getCheckedRows();
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
        const checkRows = resultBGrid.getCheckedRows();
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

    function checkRowsCItemById() {
        const checkRows = resultCGrid.getCheckedRows();
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

    function checkRowsItemByOutBillNo() {
        const checkRows = resultBGrid.getCheckedRows();
        if (checkRows.length == 1) {
            var arr = "";
            for (var i = 0; i < checkRows.length; i++) {
                if (checkRows[i]["outBillNo"] == null || checkRows[i]["outBillNo"] == undefined || checkRows[i]["outBillNo"] == "" || checkRows[i]["outBillNo"] == " "){
                    NotificationUtil("只选择存在出库记录的资产卡片", "warning");
                    return false;
                } else {
                    // 帆软转义 ' 为 %27
                    arr += checkRows[i]["outBillNo"];
                    return arr;
                }
            }
        } else {
            NotificationUtil("只能选择一条记录", "warning");
            return false;
        }
    }

    function checkRowsItemByEnterBillNo() {
        const checkRows = resultBGrid.getCheckedRows();
        if (checkRows.length == 1) {
            var arr = "";
            for (var i = 0; i < checkRows.length; i++) {
                if (checkRows[i]["enterBillNo"] == null || checkRows[i]["enterBillNo"] == undefined || checkRows[i]["enterBillNo"] == "" || checkRows[i]["enterBillNo"] == " "){
                    NotificationUtil("只选择存在入库记录的资产卡片", "warning");
                    return false;
                } else {
                    // 帆软转义 ' 为 %27
                    arr += checkRows[i]["enterBillNo"];
                    return arr;
                }
            }
        } else {
            NotificationUtil("只能选择一条记录", "warning");
            return false;
        }
    }
});

function RfidRead(ip, type, model) {
    var connectFlag = false;
    interval = setInterval(function () {
        //"192.168.1.137"
        //读取芯片信息
        doRead(ip, function (list) {
            if (connectFlag == false) {
                NotificationUtil("连接成功", "success");
                connectFlag = true;
            }
            NotificationUtil("读取到芯片数量" + list.length, "success")
            if (list.length > 0) {
                closeConnection(ip, function (flag) {
                    if (flag == true) {
                        // NotificationUtil("连接关闭", "success");
                        connectFlag = false;
                        switch (type) {
                            case "billing":
                                if (list.length == 1) {
                                    var eiInfo = new EiInfo();
                                    eiInfo.set("rfidCode", list);
                                    EiCommunicator.send("FADA01", "existsRFID", eiInfo, {
                                        onSuccess: function (ei) {
                                            if (ei.extAttr.existsRFID) {
                                                IPLAT.confirm({
                                                    message: '<b>资产编码为:' + model['goodsNum'] + '<br/>' +
                                                        '资产名称为:' + model['goodsName'] + '<br/>' +
                                                        '将绑定<font color="red">' + list + '</font><br/>' +
                                                        '确定发卡操作吗？</b>',
                                                    okFn: function (e) {
                                                        var eiInfo = new EiInfo();
                                                        eiInfo.set("faInfoId", model['faInfoId']);
                                                        eiInfo.set("rfidCode", list);
                                                        EiCommunicator.send("FADA01", "billing", eiInfo, {
                                                            onSuccess: function (ei) {
                                                                IPLAT.NotificationUtil(ei.msg)
                                                                setTimeout("changeState()",500);
                                                            }
                                                        })
                                                    },
                                                    cancelFn: function (e) {
                                                    }
                                                })
                                            } else {
                                                IPLAT.confirm({
                                                    message: '<b>资产编码为:' + model['goodsNum'] + '<br/>' +
                                                        '资产名称为:' + model['goodsName'] + '<br/>' +
                                                        '将绑定<font color="red">' + list + '</font><br/>' +
                                                        '确定发卡操作吗？<br/><font color="red">该芯片已经注册，再次注册将会注销之前的芯片！！！</font></b>',
                                                    okFn: function (e) {
                                                        var eiInfo = new EiInfo();
                                                        eiInfo.set("faInfoId", model['faInfoId']);
                                                        eiInfo.set("oldFaInfoId", ei.extAttr.faInfoId);
                                                        eiInfo.set("rfidCode", list);
                                                        EiCommunicator.send("FADA01", "billing", eiInfo, {
                                                            onSuccess: function (ei) {
                                                                IPLAT.NotificationUtil(ei.msg)
                                                                setTimeout("changeState()",500);
                                                            }
                                                        })
                                                    },
                                                    cancelFn: function (e) {
                                                    }
                                                })
                                            }
                                        }
                                    })
                                } else {
                                    NotificationUtil("资产无法绑定多个芯片", "warning");
                                }
                                break;
                            case "reading":
                                NotificationUtil("读取到的芯片为:" + [list], "success");
                                $("#inqu_status-0-rfidCode").val(list);
                                break;
                        }
                        clearInterval(interval);
                    } else {
                        NotificationUtil("连接关闭失败", "warning");
                    }
                });
            }
        });
    }, 1500);
}


//建立连接
function creatConnection(ip, callback) {
    //time读取时间(0~10秒)，若为0，则一直读卡
    axios({
        method: 'post',
        url: 'http://' + ip + ':8011/',
        data: {
            "cmd": "10004",
            "data": {"time": 0}
        },
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        timeout: 10000
    }).then(function (request) {
        let res = request.data;
        console.log(res)
        let flag = false;
        if (res && res.data["status"] == 0) {
            //"status" == 0表示连接可用
            flag = true;
        } else if (res && res.data["status"] == -1) {
            //连接已被占用
            NotificationUtil("设备连接" + ip + "被占用，请检查", "warning");
            closeConnection(ip)
        } else {
            //连接失败
            NotificationUtil("连接失败", "warning");
        }
        //回调
        if (callback) {
            callback(flag);
        }
    }).catch(function (error) {
        //连接失败
        NotificationUtil("连接超时,请检查机器连接", "warning");
    });
}

//关闭连接
function closeConnection(ip, callback) {
    //time读取时间(0~10秒)，若为0，则一直读卡
    axios({
        method: 'post',
        url: 'http://' + ip + ':8011/',
        data: {
            "cmd": "10005"
        },
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    }).then(function (request) {
        let res = request.data;
        // console.log(res);
        let flag = false;
        if (res && res.data["status"] == 0) {
            flag = true;
        }
        if (callback) {
            callback(flag);
        }
    });
}

//读卡器读取卡片
function doRead(ip, callback) {
    // console.log("doRead");
    axios({
        method: 'post',
        url: 'http://' + ip + ':8011/',
        data: {
            "cmd": "10006"
        },
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    }).then(function (request) {
        let res = request.data;
        // console.log(res)
        if (res && res.data["status"] == 0) {
            //回调
            if (callback) {
                callback(res.data["epcs"]);
            }
        } else {
            //读取失败
            NotificationUtil("读取失败", "warning");
        }
    });
}

/**
 * 将文件base64转成文件
 * @param base64
 * @param name
 */
function downloadFileByBase64(base64, name) {
    let myBlob = dataURLtoBlob(base64)
    let myUrl = URL.createObjectURL(myBlob)
    let failFile = document.createElement("a")//创建a标签
    failFile.setAttribute("href", myUrl)
    failFile.setAttribute("download", name)
    failFile.setAttribute("target", "_blank")
    let clickEvent = document.createEvent("MouseEvents");
    clickEvent.initEvent("click", true, true);
    failFile.dispatchEvent(clickEvent);
}

/**
 * 将base64转成数据流
 * @param dataurl
 * @returns {Blob}
 */
function dataURLtoBlob(dataurl) {
    let arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new Blob([u8arr], {type: mime});
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

//显示转圈中等待
function loadingShow() {
    qrCodeWindow.setOptions({"title": "正在加载中"});
    qrCodeWindow.open().center();
    var parent = $("#qrCode");
    var loading = '<i id="loading"  class="fa fa-spinner fa-spin fa-3x fa-fw" style="margin: auto;text-align: center"></i>';
    var div = $(loading);
    div.appendTo(parent);
}

//移除转圈中等待
function loadingRemove() {
    //设置多少毫秒，进行弹框关闭
    setTimeout(function () {
        window.parent['qrCodeWindow'].close();
        $('#loading').remove();
    }, 9000);
}

/*
* 步骤：
* 第一步：初始化WebSocket连接
* 第二步：点击事件进入RfidPrint方法，首先判定WebSocket连接状态
* 若其中出现异常，会重连，
* 若连接成功，readyState状态为1，即进行打印操作
* 第三步：写入芯片和写入需要打印的信息
* 第四步：onmessage返回回来的信息，通过返回信息进行判断
* 返回数据时会先返回rfid信息，后返回print打印信息
* 当返回rfid信息时，将信息保存到数组里，当返回print信息时，将信息保存到数组里。
* 若print打印信息为True时，判断rfid信息是否识别芯片
* 状态为13时，即rfid芯片未识别出信息，则页面提醒芯片未读出的编码是多少，并将编码保存到数组里
* 状态为55时，即rfid芯片识别出信息，则将识别出的信息保存到数组里
* 当进行到最后一次返回时，将数组里面的信息传给后台进行保存。
* */
function RfidPrint(checkRows) {
    var count = 0;
    // 配合C#桌面机建立websocket连接
    var printNum = checkRows.length;
    //连接成功状态readyState状态码为1
    if (ws.readyState === 1) {
        //显示转圈中等待
        loadingShow();
        //移除转圈中等待
        loadingRemove();
        //芯片识别出来的信息
        var readRfid = [];
        // ----------正常循环打印--------------
        for (var i = 0; i < printNum; i++) {
            var id = checkRows[i].faInfoId;
            var sendRfid = "rfid#id" + id;
            var start = Date.now();
            // 正常读取RFID
            ws.send(sendRfid);
            // 正常打印芯片和打印信息相对应
            let gather = checkRows[i];
            id = gather["faInfoId"];
            var qrcode = "http://119.146.152.53:8081/mzsrmyy/FixedAssets/index.html?goodsNum=" + gather["goodsNum"];
            var code = gather["goodsNum"] == null ? "" : gather["goodsNum"];
            var name = gather["goodsName"] == null ? "" : gather["goodsName"];
            var model = gather["model"] == null ? "" : gather["model"];
            var spec = gather["spec"] == null ? "" : gather["spec"];
            var special = model == "" ? spec : model + spec;
            var dept = gather["deptName"] == null ? "" : gather["deptName"];
            var time = gather["useDate"] == null ? "" : gather["useDate"];
            var date = "print#id" + id +
                ":qrcode=" + qrcode +
                ";code=" + code +
                ";name=" + name +
                ";special=" + special +
                ";dept=" + dept +
                ";time=" + time;
            ws.send(date);
        }
        // ----------天线2循环打印--------------
        // for (var i = 0; i < printNum; i++) {
        //     var id = checkRows[i].faInfoId;
        //     var sendRfid = "rfid#id" + id;
        //     var start = Date.now();
        //     // 正常读取RFID
        //     ws.send(sendRfid);
        //     // 使用天线2读取芯片存在芯片偏移的情况打印出第一张芯片
        //     if (i == 0) {
        //         // 打印命令
        //         var test = "print#id" + "test" +
        //             ":qrcode=" + "test" +
        //             ";code=" + "test" +
        //             ";name=" + "测试" +
        //             ";special=" + "" +
        //             ";dept=" + "" +
        //             ";time=" + new Date().format("yyyy-MM-dd");
        //         ws.send(test);
        //     } else {
        //         //写入打印信息
        //         let gather = checkRows[i - 1];
        //         id = gather["faInfoId"];
        //         // console.log(id)
        //         var qrcode = "http://119.146.152.53:8081/mzsrmyy/FixedAssets/index.html?goodsNum=" + gather["goodsNum"];
        //         var code = gather["goodsNum"] == null ? "" : gather["goodsNum"];
        //         var name = gather["goodsName"] == null ? "" : gather["goodsName"];
        //         var model = gather["model"] == null ? "" : gather["model"];
        //         var spec = gather["spec"] == null ? "" : gather["spec"];
        //         var special = model == "" ? spec : model;
        //         var dept = gather["deptName"] == null ? "" : gather["deptName"];
        //         var time = gather["useDate"] == null ? "" : gather["useDate"];
        //         var date = "print#id" + id +
        //             ":qrcode=" + qrcode +
        //             ";code=" + code +
        //             ";name=" + name +
        //             ";special=" + special +
        //             ";dept=" + dept +
        //             ";time=" + time;
        //         ws.send(date);
        //     }
        // }
        // 使用正常打印时注释，补打循环外的芯片
        // let gather = checkRows[printNum - 1];
        // id = gather["faInfoId"];
        // // console.log(id)
        // var qrcode = "http://119.146.152.53:8081/mzsrmyy/FixedAssets/index.html?goodsNum=" + gather["goodsNum"];
        // var code = gather["goodsNum"] == null ? "" : gather["goodsNum"];
        // var name = gather["goodsName"] == null ? "" : gather["goodsName"];
        // var model = gather["model"] == null ? "" : gather["model"];
        // var spec = gather["spec"] == null ? "" : gather["spec"];
        // var special = model == "" ? spec : model;
        // var dept = gather["deptName"] == null ? "" : gather["deptName"];
        // var time = gather["useDate"] == null ? "" : gather["useDate"];
        // var date = "print#id" + id +
        //     ":qrcode=" + qrcode +
        //     ";code=" + code +
        //     ";name=" + name +
        //     ";special=" + special +
        //     ";dept=" + dept +
        //     ";time=" + time;
        // ws.send(date);
        // 打印机回调事件处理
        ws.onmessage = function (evt) {
            console.log('Received Message:' + evt.data);
            var end = Date.now();
            console.log((end - start) / 1000);
            // 打印机回传参数信息
            var printerBackMsg = evt.data;
            // 获取返回标头是否是rfid
            var epcAndTid = printerBackMsg.slice(0, 5);
            if (epcAndTid == "rfid:") {
                // 去重多余信息，分成2组，第一组是faInfoId、第二组是epc&tid
                var backMsg = printerBackMsg.slice(10, printerBackMsg.length)
                var faInfoId = backMsg.split("\n");
                // 判断epc&tid是否返回成功
                if (55 == faInfoId[1].slice(0, 2)) {
                    var result = faInfoId[1].split(",");
                    var epc = result[1].slice(3);
                    var tidResult = result[2].split(";");
                    var tid = tidResult[0].slice(3);
                    var rfid = {
                        id: uuid(),
                        faInfoId: faInfoId[0].slice(2),
                        readInMsg: printerBackMsg,
                        epc: epc,
                        tid: tid,
                        createTime: new Date().format("yyyy-MM-dd hh:mm:ss"),
                        result: "success"
                    }
                    readRfid.push(rfid);
                } else if (13 == backMsg[1].slice(0, 2)) {
                    var rfid = {
                        id: uuid(),
                        faInfoId: faInfoId[0].slice(2),
                        readInMsg: printerBackMsg,
                        epc: "",
                        tid: "",
                        createTime: new Date().format("yyyy-MM-dd hh:mm:ss"),
                        result: "fail"
                    }
                    readRfid.push(rfid);
                    ws.close();
                    NotificationUtil("读取失败", "warning");
                    // 断开连接
                    ws.onclose = function () {
                        NotificationUtil("打印报错，断开连接", "warning");
                        console.log("中途报错，断开连接")
                        readInRfid(readRfid);
                    };
                }
            }
            count++;
            // 天线2读取
            // if (count == (printNum * 2 + 1))
            // 正常
            if (count == (printNum * 2))
            {
                ws.close();
                ws.onclose = function () {
                    console.log("开卡成功，断开连接")
                    IPLAT.alert("开卡成功，断开连接");
                    readInRfid(readRfid);
                };
            }
        };

    }
}

// 注册rfid芯片
function readInRfid(readRfid) {
    var eiInfo = new EiInfo();
    console.log(readRfid)
    eiInfo.set("rfid", readRfid)
    EiCommunicator.send("FADA01", "readInRFID", eiInfo, {
        onSuccess: function (ei) {
            if (ei.status == -1) {
                NotificationUtil(ei.msg, "warning");
            } else {
                resultCGrid.dataSource.page(1);
            }
        },
    })
}

//重连
function reconnect(reconnectNum) {
    //没连接上会一直重连，设置延迟避免请求过多
    console.info("尝试重连..." + reconnectNum + "次");
    webSocketInit(reconnectNum)
}

//建立webSocket连接
function webSocketInit(reconnectNum, checkRows) {
    var host = 'ws://127.0.0.1:4096/printer';
    ws = new WebSocket(host);
    if (reconnectNum > 3) {
        ws.onclose = function () {
            NotificationUtil("连接关闭", "warning");
            console.log("关闭连接");
        };
    } else {
        ws.onopen = function (e) {
            NotificationUtil("打印机连接成功", "success");
            console.log('Connection open ...');
            //打印、注册操作
            RfidPrint(checkRows);
        };
        ws.onerror = function () {
            reconnectNum++;
            NotificationUtil("请检查打印机连接 ...", "warning");
            console.log('webSock连接发生错误')
            reconnect(reconnectNum)
        };
        ws.onmessage = function (msg) {
            console.log(msg);
        };
    }
}

function uuid() {
    var d = new Date().getTime();
    if (window.performance && typeof window.performance.now === "function") {
        d += performance.now(); //use high-precision timer if available
    }
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = (d + Math.random() * 16) % 16 | 0;    // d是随机种子
        d = Math.floor(d / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return uuid;
}

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function changeState() {
    resultBGrid.dataSource.page(1);
    resultCGrid.dataSource.page(1);
}