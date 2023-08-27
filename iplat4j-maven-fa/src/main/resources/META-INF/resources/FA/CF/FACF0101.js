$(function () {

});

IPLATUI.EFGrid = {
    "resultSplitByNumber": {
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [{
                name: "addByNumber", text: "新增", layout: "3",
                click: function () {
                    var rows = resultSplitByNumberGrid.getDataItems();
                    resultSplitByNumberGrid.removeRows(rows);
                    var splitNumber = $("#splitNumber").val();
                    if (1 == parseInt($("#amount").val())){
                        NotificationUtil("数量为1,请走按价值拆分", "warning")
                        return
                    }
                    if (isNaN(parseInt(splitNumber))) {
                        NotificationUtil("请检查数量类型", "warning")
                        return
                    } else {
                        if (parseInt(splitNumber) <= parseInt($("#amount").val())) {
                            var i = 0;
                            for (i; i < splitNumber - 1; i++) {
                                var uuid = createUUID()
                                var model = createModel(i);
                                model["goodsId"] = uuid
                                model["goodsNum"] = goodsNum(i);
                                model["amount"] = 1;
                                model["price"] = $("#price").val();
                                model["buyCost"] = ($("#buyCost").val() / $("#amount").val()).toFixed(2);
                                model["totalDepreciation"] = ($("#totalDepreciation").val() / $("#amount").val()).toFixed(2);
                                model["netAssetValue"] = ($("#netAssetValue").val() / $("#amount").val()).toFixed(2);
                                model["estimatedResidualValue"] = ($("#estimatedResidualValue").val() / $("#amount").val()).toFixed(2);
                                resultSplitByNumberGrid.addRows(model);
                            }
                            var uuid = createUUID()
                            model["goodsId"] = uuid
                            model["goodsNum"] = goodsNum(i);
                            model["amount"] = amount(splitNumber);
                            model["price"] = $("#price").val();
                            model["buyCost"] = buyCost(splitNumber);
                            model["totalDepreciation"] = totalDepreciation(splitNumber);
                            model["netAssetValue"] = netAssetValue(splitNumber);
                            model["estimatedResidualValue"] = estimatedResidualValue(splitNumber);
                            resultSplitByNumberGrid.addRows(model);
                        } else {
                            NotificationUtil("拆分数量不得大于已有数量", "warning")
                            return
                        }

                    }
                }
            }, {
                name: "remove", text: "删除", layout: "3",
                click: function () {
                    var checkRows = resultSplitByNumberGrid.getCheckedRows();
                    if (checkRows.length < 1) {
                        NotificationUtil("请选择要删除的行", "warning");
                        return;
                    }
                    resultSplitByNumberGrid.removeRows(checkRows);
                }
            }]
        },
        loadComplete: function (e) {
            if (isEmptyStr($("#totalDepreciation").val())){
                if (!isEmptyStr($("#buyDate").val())){
                    var totalDepreciation = (getDistanceMonth($("#buyDate").val()) * $("#monthDepreciation").val()).toFixed(2);
                    if (parseInt(totalDepreciation) >= parseInt($("#buyCost").val())){
                        $("#totalDepreciation").val(($("#buyCost").val() - $("#estimatedResidualValue").val()).toFixed(2))
                    }else {
                        $("#totalDepreciation").val(totalDepreciation)
                    }
                }
            }

            $("#netAssetValue").val(($("#buyCost").val() - $("#totalDepreciation").val()).toFixed(2))


            $("#SAVE").on("click", function (e) {
                var validator = IPLAT.Validator({
                    id: "info"
                });
                validator.validate();
                var splitList = resultSplitByNumberGrid.getDataItems();
                if (splitList == false){
                    NotificationUtil("请选择需要拆分资产的数量", "warning")
                    return
                }
                if ($("#splitReason").val() == "") {
                    NotificationUtil("请填写拆分理由", "warning")
                    return
                }
                // 遍历拆分后的数据是否等于原属性
                var amountSum = 0,buyCostSum = 0,totalDepreciationSum = 0,netAssetValueSum = 0,estimatedResidualValueSum = 0;
                for (var i = 0; i < splitList.length; i++) {
                    amountSum += Number(splitList[i].amount);
                    buyCostSum += Number(splitList[i].buyCost);
                    totalDepreciationSum += Number(splitList[i].totalDepreciation);
                    netAssetValueSum += Number(splitList[i].netAssetValue);
                    estimatedResidualValueSum += Number(splitList[i].estimatedResidualValue);
                    if ($("#price").val() != splitList[i].price){
                        NotificationUtil("拆分后的单价与拆分前不符", "warning")
                        return
                    }
                }
                if (amountSum != $("#amount").val()){
                    NotificationUtil("拆分后的数量与拆分前不符", "warning")
                    return
                }
                if (buyCostSum != $("#buyCost").val()){
                    NotificationUtil("拆分后的资产原值与拆分前不符", "warning")
                    return
                }
                if (totalDepreciationSum != $("#totalDepreciation").val()){
                    NotificationUtil("拆分后的累计折旧与拆分前不符", "warning")
                    return
                }
                if (netAssetValueSum != $("#netAssetValue").val()){
                    NotificationUtil("拆分后的资产净值与拆分前不符", "warning")
                    return
                }
                if (estimatedResidualValueSum != $("#estimatedResidualValue").val()){
                    NotificationUtil("拆分后的预计净残值与拆分前不符", "warning")
                    return
                }
                var eiInfo = new EiInfo();
                eiInfo.set("faInfoId",$("#faInfoId").val());
                eiInfo.set("splitList",splitList);
                eiInfo.set("splitReason",$("#splitReason").val());
                EiCommunicator.send("FACF0101", "saveSplitByNumberInfo", eiInfo, {
                    onSuccess: function (ei) {
                        closeParentWindow()
                    }
                })
            });

            $("#CLOSE").on("click", function (e) {
                closeParentWindow()
            });
        }
    }
}

function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultGrid"].dataSource.page(1);
}

// 创建kendo.data.Model
function createModel(id) {
    var gridRow = kendo.data.Model.define({
        id: "id",
        fields: {
            "goodsId": {type: "string"},
            "goodsNum": {type: "string"},
            "amount": {type: "string"},
            "price": {type: "string"},
            "buyCost": {type: "string"},
            "totalDepreciation": {type: "string"},
            "netAssetValue": {type: "string"},
            "estimatedResidualValue": {type: "string"},
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
function goodsNum(i){
    return $("#goodsNum").val() + "-" + (i + 1);
}
// 计算最后一栏数量
function amount(splitNumber){
    return $("#amount").val() - (splitNumber - 1);
}

// 计算最后一栏价格
function buyCost(splitNumber){
    return ($("#buyCost").val() - ($("#buyCost").val() / $("#amount").val()).toFixed(2) * (splitNumber - 1)).toFixed(2);
}
// 计算最后一栏累计折旧
function totalDepreciation(splitNumber){
    return ($("#totalDepreciation").val() - ($("#totalDepreciation").val() / $("#amount").val()).toFixed(2) * (splitNumber - 1)).toFixed(2);
}

// 计算最后一行净值
function netAssetValue(splitNumber){
    return ($("#netAssetValue").val() - ($("#netAssetValue").val() / $("#amount").val()).toFixed(2) * (splitNumber - 1)).toFixed(2);
}

// 计算最后一行预计净残值
function estimatedResidualValue(splitNumber){
    return ($("#estimatedResidualValue").val() - ($("#estimatedResidualValue").val() / $("#amount").val()).toFixed(2) * (splitNumber - 1)).toFixed(2);
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

function isEmptyStr(s){
    if (s == null || s === '') {
        return true
    }
    return false
}