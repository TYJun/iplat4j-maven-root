$(function () {
    // 保存
    $("#SURE").on("click", function (e) {
        var eiInfo = new EiInfo();
        var model = createModel(1);
        var projectId = IPLAT.EFInput.value($("#projectId"));
        var instanceId = IPLAT.EFInput.value($("#instanceId"));
        var instanceName = IPLAT.EFInput.value($("#inqu_status-0-instanceName"));
        var pointType = IPLAT.EFSelect.value($("#inqu_status-0-pointType"));
        var pointName = IPLAT.EFSelect.text($("#inqu_status-0-pointType"));
        var type = IPLAT.EFInput.value($("#type"));
        var orderNo = IPLAT.EFInput.value($("#orderNo"));
        var point = IPLAT.EFInput.value($("#inqu_status-0-point"));
        var item = resultGrid.getDataItems();
        if (orderNo == "") {
            IPLAT.NotificationUtil("请填写题目序号", "error")
            return;
        } else if (checkRate(orderNo)) {
            IPLAT.NotificationUtil("题目序号请输入数字", "error")
            return;
        } else if (instanceName == "") {
            IPLAT.NotificationUtil("请填写题目描述", "error")
            return;
        } else if (pointName == "") {
            IPLAT.NotificationUtil("请选择问题类型", "error")
            return;
        } else if (point == "") {
            IPLAT.NotificationUtil("请填写分值", "error")
            return;
        } else if (checkRate(point)) {
            IPLAT.NotificationUtil("分值请输入数字", "error")
            return;
        } else if (checkItem(pointName)) {
            IPLAT.NotificationUtil("请填写题目选项", "error")
            return;
        } else if (checkItemName(item)) {
            IPLAT.NotificationUtil("请填写有效的选项", "error")
            return;
        } else if (checkOrderNo(item)) {
            IPLAT.NotificationUtil("请填写序号", "error")
            return;
        }
        model["projectId"] = projectId;
        model["instanceId"] = instanceId;
        model["instanceName"] = instanceName;
        model["pointType"] = pointType;
        model["pointName"] = pointName;
        model["point"] = point;
        model["orderNo"] = orderNo;
        eiInfo.set("projectId", projectId);
        eiInfo.set("instanceId", instanceId);
        eiInfo.set("instanceName", instanceName);
        eiInfo.set("pointType", pointType);
        eiInfo.set("pointName", pointName);
        eiInfo.set("point", point);
        eiInfo.set("type", type);
        eiInfo.set("orderNo", orderNo);
        eiInfo.set("item", item);
        EiCommunicator.send("SQBZ010101", "save", eiInfo, {
            onSuccess: function (ei) {
                IPLAT.NotificationUtil(ei.msg);
                window.parent['popDataWindow'].close();
                window.parent.addInstanceRows(model);
            }
        });
    });
});
IPLATUI.EFSelect = {
    "inqu_status-0-pointType": {
        change: function (e) {
            var pointType = IPLAT.EFSelect.value($("#inqu_status-0-pointType"));
            if (pointType == "3") {
                $("#point").hide();
                IPLAT.EFInput.readonly($("#inqu_status-0-point"), true);
                IPLAT.EFInput.value($("#inqu_status-0-point"), "0");
            } else {
                $("#point").show();
                IPLAT.EFInput.readonly($("#inqu_status-0-point"), false);
            }
        }
    }
}

IPLATUI.EFGrid = {
    "result": {
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [{
                name: "new", text: "新增", layout: "3",
                click: function () {
                    var model = createItemModel(1);
                    model["instanceId"] = IPLAT.EFInput.value($("#instanceId"));
                    model["itemName"] = "";
                    model["itemScore"] = 0;
                    model["orderNo"] = "";
                    resultGrid.addRows(model);
                }
            }, {
                name: "deleter", text: "删除", layout: "3",
                click: function () {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        resultGrid.removeRows(checkRows);
                    } else {
                        IPLAT.NotificationUtil("请选择需要删除的信息", "error")
                    }
                }
            }]
        },
        dataBinding: function (e) {

        },
        loadComplete: function (grid) {
            var pointType = IPLAT.EFSelect.value($("#inqu_status-0-pointType"));
            if (pointType == "3") {
                $("#point").hide();
            }
        },
    }
}


//创建kendo.data.Model
function createModel(id) {
    var gridRow = kendo.data.Model.define({
        id: "id",
        fields: {
            "projectId": {type: "string"},
            "instanceId": {type: "string"},
            "instanceName": {type: "string"},
            "pointType": {type: "string"},
            "pointName": {type: "string"},
            "point": {type: "int"},
            "orderNo": {type: "int"},
        }
    });
    var model = new gridRow({id: id});
    return model;
}

//创建kendo.data.Model
function createItemModel(id) {
    var gridRow = kendo.data.Model.define({
        id: "id",
        fields: {
            "instanceId": {type: "string"},
            "itemName": {type: "string"},
            "itemScore": {type: "int"},
            "orderNo": {type: "int"},
        }
    });
    var model = new gridRow({id: id});
    return model;
}

function checkRate(nubmer) {
    var re = /^[0-9]+.?[0-9]*/;//判断字符串是否为数字//判断正整数/[1−9]+[0−9]∗]∗/
    if (re.test(nubmer)) {
        return false;
    }
    return true;
}

function checkItem(pointName) {
    if (pointName == "问答题") {
        return false;
    } else {
        if (resultGrid.getDataItems().length == 0) {
            return true;
        } else {
            return false;
        }
    }
}

function checkItemName(item) {
    for (let i = 0; i < item.length; i++) {
        let itemName = item[i].itemName;
        if (itemName == "") {
            return true;
        }
    }
    return false;
}

function checkOrderNo(item) {
    for (let i = 0; i < item.length; i++) {
        let orderNo = item[i].orderNo;
        if (orderNo == "") {
            return true;
        }
    }
    return false;
}