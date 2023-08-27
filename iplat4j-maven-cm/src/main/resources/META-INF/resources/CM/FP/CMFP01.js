//打开合同付款管理新增
function invoiceWindowOpen(type, invoiceAutoNo) {
    var url = IPLATUI.CONTEXT_PATH + "/web/CMFP0101?methodName=initLoad&type=" + type + "&invoiceAutoNo=" + invoiceAutoNo;
    var popData = $("#invoice");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url,
            });
        }
    });
    // 打开弹窗
    invoiceWindow.open().center();
}

$(function () {
    // 查询
    $("#QUERY").on("click", function () {
        resultGrid.dataSource.page(1);
    });

    // 重置
    $("#RESET").on("click", function () {
        $("#invoiceAutoNo").val("");
        $("#invoiceNo").val("");
        IPLAT.EFSelect.value($("#invoiceStatus"),(""));
        resultGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "result": {
            dataBinding: function (e) {
                for (var i = 0, length = e.items.length; i < length; i++){
                    if(isAvailable(e.items[i].invoiceTaxExcludeAmount)){
                        e.items[i].invoiceTaxExcludeAmount = parseFloat(e.items[i].invoiceTaxExcludeAmount);
                    }
                    if(isAvailable(e.items[i].invoiceTaxAmount)){
                        e.items[i].invoiceTaxAmount = parseFloat(e.items[i].invoiceTaxAmount);
                    }
                }
            },
            loadComplete: function (e) {
                // 新增
                $("#APPEND").on("click", function () {
                    invoiceWindowOpen("new", "");
                    invoiceWindow.setOptions({"title": "新增"});
                });

                // 编辑
                $("#EDIT").on("click", function () {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        var invoiceAutoNo = checkRows[0].invoiceAutoNo;
                        invoiceWindowOpen("edit", invoiceAutoNo);
                    } else {
                        NotificationUtil("请选择一条发票记录", "error");
                        return;
                    }
                    invoiceWindow.setOptions({"title": "编辑"});
                });

                // 申请付款
                $("#REQPAYMENT").on("click", function () {
                    var row = resultGrid.getCheckedRows();
                    if (row.length > 0) {
                        var eiInfo = new EiInfo();
                        if (row[0].invoiceStatus=="申请付款"){
                            NotificationUtil("请选择一条发票登记记录", "error");
                            return;
                        }
                        var invoiceAutoNo = row[0].invoiceAutoNo;
                        eiInfo.set("invoiceAutoNo",invoiceAutoNo);
                        IPLAT.confirm({
                            message: '<b>确定申请付款操作吗？</b></i>',
                            okFn: function (e) {
                                EiCommunicator.send("CMFP0101", "reqPayment", eiInfo, {
                                    onSuccess: function (ei) {
                                        IPLAT.NotificationUtil(ei.msg)
                                        resultGrid.dataSource.page(1);
                                    }
                                })
                            },
                            cancelFn: function (e) {}
                        })
                    } else {
                        NotificationUtil("请选择一条发票登记记录", "error");
                        return;
                    }
                });

                // 删除
                $("#DELETER").on("click", function () {
                    var row = resultGrid.getCheckedRows();
                    if (row.length > 0) {
                        if (row[0].invoiceStatus=="申请付款"){
                            NotificationUtil("请选择一条发票登记记录", "error");
                            return;
                        }
                        var eiInfo = new EiInfo();
                        var invoiceAutoNo = row[0].invoiceAutoNo;
                        eiInfo.set("invoiceAutoNo",invoiceAutoNo);
                        IPLAT.confirm({
                            message: '<b>确定执行删除操作吗？</b></i>',
                            okFn: function (e) {
                                EiCommunicator.send("CMFP0101", "deleter", eiInfo, {
                                    onSuccess: function (ei) {
                                        IPLAT.NotificationUtil(ei.msg)
                                        resultGrid.dataSource.page(1);
                                    }
                                })
                            },
                            cancelFn: function (e) {}
                        })
                    } else {
                        NotificationUtil("请选择一条合同记录", "error");
                        return;
                    }
                });

                // 查看
                $("#LOOK").on("click", function () {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        var invoiceAutoNo = checkRows[0].invoiceAutoNo;
                        invoiceWindowOpen("look", invoiceAutoNo);
                    } else {
                        NotificationUtil("请选择一条发票记录", "error");
                        return;
                    }
                    invoiceWindow.setOptions({"title": "查看"});
                });
            }
        }
    }
})
