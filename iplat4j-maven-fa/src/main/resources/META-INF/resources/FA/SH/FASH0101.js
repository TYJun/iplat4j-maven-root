var pageCount = 0.00, netValueCount = 0.00, numberCount = 0.00;// 资产原值总金额
var datagrid = null;
let addFlag = true;// afterEdit会执行两次，总价会重复计算两次，用该变量控制只计算一次
$(function () {
    $("#SUBMIT").on("click", function (e) {
        var discussName = $("#info-0-discussName").val();
        var checkRows = resultGrid.getDataItems();
        if (discussName == "") {
            NotificationUtil("会议名称不能为空", "warning")
            return;
        }
        if (!checkRows.length > 0) {
            NotificationUtil("上会资产不能为空", "warning")
            return;
        }
        // 获取参数
        var eiInfo = new EiInfo();
        eiInfo.setByNode("info");
        eiInfo.set("list", checkRows)
        // 固资类别录入提交
        EiCommunicator.send("FASH0101", "insertFaDiscuss", eiInfo, {
            onSuccess: function (ei) {
                window.parent['popDataWindow'].close();
                window.parent.location.reload()
            }
        })
    });

    $("#CANCEL").on("click", function (e) {
        window.parent['popDataWindow'].close();
    });

    IPLATUI.EFGrid = {
        "result": {
            pageable: false,
            // exportGrid: false,
            // pageable: {
            //     pageSize: 15
            // },

            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "remove",
                        text: "移除资产",
                        layout: "3",
                        click: function (e) {
                            var checkRows = resultGrid.getCheckedRows()
                            resultGrid.removeRows(checkRows);
                            if (checkRows.length > 0){
                                var newRows = resultGrid.getDataItems();
                                pageCount = 0.00,netValueCount = 0.00;
                                for (let i = 0; i < newRows.length; i++) {
                                    pageCount += $.isNumeric(newRows[i].buyCost) ? + newRows[i].buyCost : 0;
                                    netValueCount += $.isNumeric(newRows[i].netAssetValue) ? + newRows[i].netAssetValue : 0;
                                }
                                numberCount = newRows.length;
                                $("#pageCount").text(pageCount.toFixed(2));
                                $("#netValueCount").text(netValueCount.toFixed(2));
                                $("#numberCount").text(numberCount.toFixed(2));
                            } else {
                                NotificationUtil("请选择需要移除的卡片", "warning")
                                return;
                            }
                        }
                    },
                    {
                        name: "addRows",
                        text: "附加资产",
                        layout: "3",
                        click: function () {
                            fixedAssetsWindow()
                        }
                    },
                ]
            },
            loadComplete: function (grid) {
                $("#ef_grid_toolbar_result").prepend("<div style='float:left;font-size:13px;'>" +
                    "当页资产原值总金额：<span id='pageCount' style='color: red'>0.00</span>元，" +
                    "当页资产净值总金额：<span id='netValueCount' style='color: red'>0.00</span>元，" +
                    "当页资产数量：<span id='numberCount' style='color: red'>0.00</span>条</div>")
                $("#info-0-discussDate").data("kendoDatePicker").value(new Date())
                var checkRows = window.parent.resultAGrid.getCheckedRows()
                if (checkRows.length > 0) {
                    grid.addRows(checkRows);
                    grid.unCheckAllRows();
                    for (let i = 0; i < checkRows.length; i++) {
                        pageCount += $.isNumeric(checkRows[i].buyCost) ? + checkRows[i].buyCost : 0;
                        netValueCount += $.isNumeric(checkRows[i].netAssetValue) ? + checkRows[i].netAssetValue : 0;
                        numberCount = checkRows.length;
                        $("#pageCount").text(pageCount.toFixed(2));
                        $("#netValueCount").text(netValueCount.toFixed(2));
                        $("#numberCount").text(numberCount.toFixed(2));
                    }
                } else {
                    if (grid != undefined){

                    }
                }
            },
            beforeRequest: function(e) {

            },
            onCheckRow: function (e) {

            },
        },
    }
});

// 自定义资产弹窗
function fixedAssetsWindow(discussId) {
    var DataItems = resultGrid.getDataItems();
    var faInfoIdList = null;
    for (let i = 0; i < DataItems.length; i++) {
        if (i!=0){
            faInfoIdList = faInfoIdList+","+DataItems[i].faInfoId;
        }else {
            faInfoIdList = DataItems[i].faInfoId;
        }
    }
    var url = IPLATUI.CONTEXT_PATH + "/web/FASH00?query&discussId=" + discussId+"&faInfoIdList=" +faInfoIdList;
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

window.methods = {
    pageCount01Callback(arguments) {
        $("#pageCount").text(arguments);
    },
    netValueCount01Callback(arguments) {
        $("#netValueCount").text(arguments);
    },
    numberCount01Callback(arguments) {
        $("#numberCount").text(arguments);
    },
}