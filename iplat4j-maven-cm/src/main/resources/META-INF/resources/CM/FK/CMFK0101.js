$(function () {
    var validator = IPLAT.Validator({
        id: "result"
    });

    IPLATUI.EFPopupInput = {
        "inqu_status-0-contNo": {
            init: function (e) {
                resultAGrid.dataSource.page(1);
            },
        },
        "inqu_status-0-invoiceAutoNo": {
            init: function (e) {
                resultBGrid.dataSource.page(1);
            },
        }
    }

    IPLATUI.EFGrid = {
        "resultA": {
            dataBinding: function (e) {
                for (var i = 0, length = e.items.length; i < length; i++){
                    if(isAvailable(e.items[i].budget)){
                        e.items[i].budget = parseFloat(e.items[i].budget);
                    }
                    if(isAvailable(e.items[i].contTaxIncludeAmount)){
                        e.items[i].contTaxIncludeAmount = parseFloat(e.items[i].contTaxIncludeAmount);
                    }
                }
            },
            loadComplete: function (e) {
                if (__ei.type=="look"){
                    $("#save").css("display","none");
                }

                $("#typeQueryA").on("click", function () {
                    resultAGrid.dataSource.page(1);
                })

                $("#typeResetA").on("click", function () {
                    $("#inqu_status-0-contNoWindow").val("");
                    $("#inqu_status-0-contNameWindow").val("");
                    resultAGrid.dataSource.page(1);
                })

                $("#typeSaveA").on("click", function () {
                    var row = resultAGrid.getCheckedRows();
                    if (row.length > 0) {
                        var model = row[0];
                        var contNo = model.contNo;
                        var contName = model.contName;
                        $(".k-window").hide();
                        IPLAT.EFInput.value($("#contPk"),model.contPk);
                        IPLAT.EFPopupInput.text($("#inqu_status-0-contNo"), contName + "-" + contNo);
                        IPLAT.EFPopupInput.value($("#inqu_status-0-contNo"), contNo);
                    } else {
                        NotificationUtil("请选择一条合同记录", "error");
                        return;
                    }
                })
            }
        },
        "resultB": {
            loadComplete: function (e) {
                if (__ei.type=="look"){
                    $("#save").css("display","none");
                }

                $("#typeQueryB").on("click", function () {
                    resultBGrid.dataSource.page(1);
                })

                $("#typeResetB").on("click", function () {
                    $("#inqu_status-0-invoiceAutoNoWindow").val("");
                    $("#inqu_status-0-invoiceNoWindow").val("");
                    resultBGrid.dataSource.page(1);
                })

                $("#typeSaveB").on("click", function () {
                    var row = resultBGrid.getCheckedRows();
                    if (row.length > 0) {
                        var model = row[0];
                        var invoiceAutoNo = model.invoiceAutoNo;
                        $(".k-window").hide();
                        IPLAT.EFPopupInput.text($("#inqu_status-0-invoiceAutoNo"), invoiceAutoNo);
                        IPLAT.EFPopupInput.value($("#inqu_status-0-invoiceAutoNo"), invoiceAutoNo);
                    } else {
                        NotificationUtil("请选择一条合同记录", "error");
                        return;
                    }
                })
            }
        },
    }

    // 保存
    $("#save").on("click", function () {
        //获取参数
        var eiInfo = new EiInfo();
        eiInfo.setByNode("result");
        //提交
        EiCommunicator.send("CMFK0101", "save", eiInfo, {
            onSuccess: function (ei) {
                closeCurrentWindow();
                IPLAT.NotificationUtil(ei.msg)
                window.parent["resultGrid"].dataSource.page(1);
            }
        })
    });

    // 取消
    $("#return").on("click", function () {
        closeCurrentWindow();
    });
});

//关闭窗口
function closeCurrentWindow() {
    window.parent['paymentWindow'].close();
}