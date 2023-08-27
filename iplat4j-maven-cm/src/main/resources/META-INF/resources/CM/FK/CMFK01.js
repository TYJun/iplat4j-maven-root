//打开合同付款管理新增
function paymentWindowOpen(type, paymentAutoNo) {
    var url = IPLATUI.CONTEXT_PATH + "/web/CMFK0101?methodName=initLoad&type=" + type + "&paymentAutoNo=" + paymentAutoNo;
    var popData = $("#payment");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url,
            });
        }
    });
    // 打开弹窗
    paymentWindow.open().center();
}

$(function () {
    // 查询
    $("#QUERY").on("click", function () {
        resultGrid.dataSource.page(1);
    });

    // 重置
    $("#RESET").on("click", function () {
        $("#paymentNo").val("");
        IPLAT.EFSelect.value($("#paymentStatus"),(""));
        resultGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "result": {
            dataBinding: function (e) {
                for (var i = 0, length = e.items.length; i < length; i++){
                    if(isAvailable(e.items[i].paymentTaxExcludeAmount)){
                        e.items[i].paymentTaxExcludeAmount = parseFloat(e.items[i].paymentTaxExcludeAmount);
                    }
                    if(isAvailable(e.items[i].paymentTaxAmount)){
                        e.items[i].paymentTaxAmount = parseFloat(e.items[i].paymentTaxAmount);
                    }
                }
            },
            loadComplete: function (e) {
                // 新增
                $("#APPEND").on("click", function () {
                    paymentWindowOpen("new", "");
                    paymentWindow.setOptions({"title":"新增"});
                });

                // 付款
                $("#PAYMENT").on("click", function () {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        if (checkRows[0].paymentStatus=="付款完成"){
                            NotificationUtil("请选择一条申请付款记录", "error");
                            return;
                        }
                        var paymentAutoNo = checkRows[0].paymentAutoNo;
                        paymentWindowOpen("edit", paymentAutoNo);
                    } else {
                        NotificationUtil("请选择一条发票记录", "error");
                        return;
                    }
                    paymentWindow.setOptions({"title": "编辑"});
                });

                // 查看
                $("#LOOK").on("click", function () {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        var paymentAutoNo = checkRows[0].paymentAutoNo;
                        paymentWindowOpen("look", paymentAutoNo);
                    } else {
                        NotificationUtil("请选择一条发票记录", "error");
                        return;
                    }
                    paymentWindow.setOptions({"title": "查看"});
                });
            }
        }
    }
})

