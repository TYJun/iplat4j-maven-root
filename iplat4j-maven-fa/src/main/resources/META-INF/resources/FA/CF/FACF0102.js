$(function () {
    var type = $("#type").val();
    switch (type) {
        case "enter":
            $("#apply").show()
            break;
        case "confrim":
            $("#confirm").show()
            break
        case "look":
            $("#look").show()
            break
    }
});

IPLATUI.EFGrid = {
    "resultSplitByValue": {
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [{
                name: "addByValue", text: "新增", layout: "3",
                click: function () {
                    var rows = resultSplitByValueGrid.getDataItems();
                    resultSplitByValueGrid.removeRows(rows);
                    var splitNumber = $("#splitNumber").val();
                    if (splitNumber == "") {
                        NotificationUtil("请填写拆分数量", "warning");
                        return
                    } else if (parseInt(splitNumber) == "1") {
                        NotificationUtil("拆分数量不能为1", "warning");
                        return
                    } else {
                        var i = 0;
                        for (i; i < splitNumber - 1; i++) {
                            var uuid = createUUID()
                            var model = createModel(i);
                            model["faInfoId"] = uuid
                            model["goodsNum"] = goodsNum(i);
                            model["goodsName"] = $("#info-0-goodsName").val();
                            model["amount"] = 1;
                            model["buyCost"] = ($("#info-0-buyCost").val() / splitNumber).toFixed(2);
                            model["totalDepreciation"] = ($("#info-0-totalDepreciation").val() / splitNumber).toFixed(2);
                            model["netAssetValue"] = ($("#info-0-netAssetValue").val() / splitNumber).toFixed(2);
                            resultSplitByValueGrid.addRows(model);
                        }
                        var uuid = createUUID()
                        model["faInfoId"] = uuid
                        model["goodsNum"] = goodsNum(i);
                        model["goodsName"] = $("#info-0-goodsName").val();
                        model["amount"] = 1;
                        model["price"] = $("#price").val();
                        model["buyCost"] = buyCost(splitNumber);
                        model["totalDepreciation"] = totalDepreciation(splitNumber);
                        model["netAssetValue"] = netAssetValue(splitNumber);
                        resultSplitByValueGrid.addRows(model);
                    }
                }
            }, {
                name: "remove", text: "删除", layout: "3",
                click: function () {
                    var checkRows = resultSplitByValueGrid.getCheckedRows();
                    if (checkRows.length < 1) {
                        NotificationUtil("请选择要删除的行", "warning");
                        return;
                    }
                    resultSplitByValueGrid.removeRows(checkRows);
                }
            }]
        },
        loadComplete: function (e) {
            $("#save").on("click", function (e) {
                if ($("#info-0-splitReason").val() == "") {
                    NotificationUtil("请填写拆分理由", "warning")
                    return
                }
                var splitList = resultSplitByValueGrid.getDataItems();
                if (splitList == false) {
                    NotificationUtil("请选择需要拆分资产的数量", "warning")
                    return
                }
                // 遍历拆分后的数据是否等于原属性
                var buyCostSum = 0, totalDepreciationSum = 0, netAssetValueSum = 0;
                for (var i = 0; i < splitList.length; i++) {
                    buyCostSum += Number(splitList[i].buyCost);
                    totalDepreciationSum += Number(splitList[i].totalDepreciation);
                    netAssetValueSum += Number(splitList[i].netAssetValue);
                }
                if (buyCostSum != $("#info-0-buyCost").val()) {
                    NotificationUtil("拆分后的资产原值与拆分前不符", "warning")
                    return
                }
                if (totalDepreciationSum != $("#info-0-totalDepreciation").val()) {
                    NotificationUtil("拆分后的累计折旧与拆分前不符", "warning")
                    return
                }
                if (netAssetValueSum != $("#info-0-netAssetValue").val()) {
                    NotificationUtil("拆分后的资产净值与拆分前不符", "warning")
                    return
                }
                if (splitList.length != $("#splitNumber").val()) {
                    NotificationUtil("卡片拆分数量和实际数量不相符", "warning")
                    return
                }
                var eiInfo = new EiInfo();
                eiInfo.setByNode("info")
                eiInfo.set("splitList", splitList);
                EiCommunicator.send("FACF0102", "saveSplitByValueInfo", eiInfo, {
                    onSuccess: function (ei) {
                        closeParentWindow()
                    }
                })
            });

            $("#confirmPass").on("click", function (e) {
                var eiInfo = new EiInfo();
                EiCommunicator.send("FACF0102", "confirmSplitByValue", eiInfo, {
                    onSuccess: function (ei) {
                        closeParentWindow()
                    }
                })
            })

            $("#confirmReject").on("click", function (e) {
                var eiInfo = new EiInfo();
                EiCommunicator.send("FACF0102", "confirmSplitByValue", eiInfo, {
                    onSuccess: function (ei) {
                        closeParentWindow()
                    }
                })
            })

            $("#applyClose").on("click", function (e) {
                closeParentWindow()
            });

            $("#confirmClose").on("click", function (e) {
                closeParentWindow()
            });

            $("#lookClose").on("click", function (e) {
                closeParentWindow()
            });
        },
        afterEdit: function (e) {
            let grid = e.sender;
            let rowIndex = e.row
            var rows = resultSplitByValueGrid.getCheckedRows();
            if (e.field == "buyCost") {
                var nowBuyCost = e.model.buyCost;
                var buyCost = $("#info-0-buyCost").val()
                var totalDepreciation = $("#info-0-totalDepreciation").val()
                var netAssetValue = $("#info-0-netAssetValue").val()
                var nowTotalDepreciation = (nowBuyCost / buyCost * totalDepreciation).toFixed(2)
                var nowNetAssetValue = (nowBuyCost / buyCost * netAssetValue).toFixed(2)
                var newNowBuyCost = (nowBuyCost * 1).toFixed(2)
                grid.setCellValue(rowIndex, "buyCost", newNowBuyCost)
                grid.setCellValue(rowIndex, "totalDepreciation", nowTotalDepreciation)
                grid.setCellValue(rowIndex, "netAssetValue", nowNetAssetValue)
                // $.each(rows, function (index, item) {
                //     resultSplitByValueGrid.setCellValue(item, "buyCost", nowBuyCost)
                // });
                return;
            }
            // if (e.field == "netAssetValue") {
            //     var nowNetAssetValue = e.model.netAssetValue;
            //     var netAssetValue = $("#info-0-netAssetValue").val();
            //     $.each(rows, function (index, item) {
            //         resultSplitByValueGrid.setCellValue(item, "netAssetValue", nowNetAssetValue)
            //     });
            //     return;
            // }
            // if (e.field == "totalDepreciation") {
            //     var nowTotalDepreciation = e.model.totalDepreciation;
            //     var totalDepreciation = $("#info-0-totalDepreciation").val();
            //     $.each(rows, function (index, item) {
            //         resultSplitByValueGrid.setCellValue(item, "totalDepreciation", nowTotalDepreciation)
            //     });
            //     return;
            // }
        },
    }
}

function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultAGrid"].dataSource.page(1);
    // window.parent["resultBGrid"].dataSource.page(1);
    // window.parent["resultCGrid"].dataSource.page(1);
}

// 创建kendo.data.Model
function createModel(id) {
    var gridRow = kendo.data.Model.define({
        id: "id",
        fields: {
            "faInfoId": {type: "string"},
            "goodsNum": {type: "string"},
            "goodsName": {type: "string"},
            "amount": {type: "string"},
            "buyCost": {type: "string"},
            "totalDepreciation": {type: "string"},
            "netAssetValue": {type: "string"},
        }
    });
    var model = new gridRow({id: id});
    return model;
}

// 计算2个日期相差多少月
function getDistanceMonth(startTime) {
    startTime = new Date(startTime)
    endTime = new Date(getNowDate())
    var dateToMonth = 0
    var startDate = startTime.getDate() + startTime.getHours() / 24 + startTime.getMinutes() / 24 / 60
    var endDate = endTime.getDate() + endTime.getHours() / 24 + endTime.getMinutes() / 24 / 60
    if (endDate >= startDate) {
        dateToMonth = 0
    } else {
        dateToMonth = -1
    }
    var yearToMonth = (endTime.getYear() - startTime.getYear()) * 12
    var monthToMonth = endTime.getMonth() - startTime.getMonth()
    return yearToMonth + monthToMonth + dateToMonth + 1
}

// 获取当前时间YYYYMMDD
function getNowDate() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (day >= 0 && day <= 9) {
        day = "0" + day;
    }
    return year + '-' + month + '-' + day
}

// 计算公式
// 计算拆分后的编号
function goodsNum(i) {
    var goodsNumMax = $("#info-0-goodsNumMax").val();
    var goodsNum = $("#info-0-goodsNum").val();
    if (parseInt(i) == 0) {
        return goodsNum;
    }
    return goodsNumMax.substring(0, goodsNumMax.length - 4) + (Array(4).join(0) + (parseInt(goodsNumMax.substring(goodsNumMax.length - 4, goodsNumMax.length)) + parseInt(i - 1))).slice(-4);
}

// 计算最后一栏价格
function buyCost(splitNumber) {
    return ($("#info-0-buyCost").val() - ($("#info-0-buyCost").val() / splitNumber).toFixed(2) * (splitNumber - 1)).toFixed(2);
}

// 计算最后一栏累计折旧
function totalDepreciation(splitNumber) {
    return ($("#info-0-totalDepreciation").val() - ($("#info-0-totalDepreciation").val() / splitNumber).toFixed(2) * (splitNumber - 1)).toFixed(2);
}

// 计算最后一行净值
function netAssetValue(splitNumber) {
    return ($("#info-0-netAssetValue").val() - ($("#info-0-netAssetValue").val() / splitNumber).toFixed(2) * (splitNumber - 1)).toFixed(2);
}

// 计算最后一行预计净残值
function estimatedResidualValue(splitNumber) {
    return ($("#info-0-estimatedResidualValue").val() - ($("#info-0-estimatedResidualValue").val() / splitNumber).toFixed(2) * (splitNumber - 1)).toFixed(2);
}

// 生成UUID
function createUUID() {
    var d = new Date().getTime();
    if (window.performance && typeof window.performance.now === "function") {
        d += performance.now();
    }
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = (d + Math.random() * 16) % 16 | 0;
        d = Math.floor(d / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return uuid;
}

function isEmptyStr(s) {
    if (s == null || s === '') {
        return true
    }
    return false
}