$(function () {
    // 前端生成UUID编码
    // 题目ID
    var instanceId = createUUID();

    // 问卷保存按钮
    $("#SURE").on("click", function (e) {
        var info = new EiInfo();
        var type = IPLAT.EFInput.value($("#type"));
        var standardId = IPLAT.EFInput.value($("#standardId"));
        var pProjectId = IPLAT.EFInput.value($("#pProjectId"));
        var standardName = IPLAT.EFInput.value($("#inqu_status-0-standardName"));
        // var sqType = IPLAT.EFSelect.value($("#sqType"));
        var sqType = "01";
        // var sqTypeName = IPLAT.EFSelect.text($("#sqType"));
        var sqTypeName = "01";
        var remark = IPLAT.EFInput.value($("#inqu_status-0-remark"));
        var ProjectItems = resultProjectGrid.getDataItems();
        if (standardName == "") {
            IPLAT.NotificationUtil("请填写问卷主题", "error")
            return;
        } else if (sqType == "") {
            IPLAT.NotificationUtil("请选择问卷类型", "error")
            return;
        } else if (checkProjectName(ProjectItems)) {
            IPLAT.NotificationUtil("请填写有效的项目名称", "error")
            return;
        } else if (checkOrderNo(ProjectItems)) {
            IPLAT.NotificationUtil("请填写有效的序号", "error")
            return;
        }
        info.set("type", type);
        info.set("standardId", standardId);
        info.set("pProjectId", pProjectId);
        info.set("standardName", standardName);
        info.set("sqType", sqType);
        info.set("sqTypeName", sqTypeName);
        info.set("remark", remark);
        info.set("ProjectItems", ProjectItems);
        EiCommunicator.send("SQBZ0101", "insertStandard", info, {
            onSuccess: function (ei) {
                window.parent['popDataWindow'].close();
                window.parent["resultGrid"].dataSource.page(1);
            }
        });
    });


    IPLATUI.EFGrid = {
        "resultProject": {
            pageable: false,
            exportGrid: false,
            columns: [{
                field: "operation",
                headerTemplate: "操作",
                align: "center",
                template: "<button id='operation' class='btn-danger'>添加题目</button>"
            }],
            onCellClick: function (e) {
                var items = resultProblemGrid.getDataItems();
                resultProblemGrid.removeRows(items);
                var info = new EiInfo();
                var projectId = e.model.projectId;
                info.set("projectId", projectId);
                IPLAT.EFInput.value($("#pProjectId"), projectId);
                resultProblemGrid.dataSource.page(1);
                /*EiCommunicator.send("SQBZ0101", "queryInstanceByProjectId", info, {
                    onSuccess: function (ei) {
                        这种方法暂未实现，不能刷新界面
                        个人推荐这种方式
                    }
                });*/
                if (e.field == "operation") {
                    var projectId = e.model.projectId;
                    popDataWindow.setOptions({"title": "新增题目详情"});
                    popData(projectId, "", "add");
                }
            },
            onRowDblClick: function (e) {
            },
            beforeEdit: function (e) {
                // 在编辑前调用
                // 禁止单元格编辑
                // e.preventDefault();
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "new", text: "新增", layout: "3",
                    click: function () {
                        var projectId = createUUID();
                        var model = createModel(projectId);
                        model["projectId"] = projectId;
                        model["projectName"] = "";
                        model["projectRemark"] = "";
                        model["orderNo"] = "";
                        resultProjectGrid.addRows(model);
                    }
                }, {
                    name: "deleter", text: "删除", layout: "3",
                    click: function () {
                        var checkRows = resultProjectGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            var eiInfo = new EiInfo();
                            var projectId = checkRows[0].projectId;
                            eiInfo.set("projectId", projectId);
                            IPLAT.confirm({
                                message: '<b style="color: red">确定执行删除操作吗？该操作不可恢复</b></i>',
                                okFn: function (e) {
                                    EiCommunicator.send("SQBZ0101", "deleteProjectAndInstanceAndItem", eiInfo, {
                                        onSuccess: function (ei) {
                                            resultProjectGrid.removeRows(checkRows);
                                            resultProblemGrid.removeRows(resultProblemGrid.getDataItems());
                                        }
                                    })
                                },
                                cancelFn: function (e) {
                                }
                            })
                        } else {
                            IPLAT.NotificationUtil("请选择需要删除的信息", "error")
                        }
                    }
                }]
            }
        },
        "resultProblem": {
            pageable: false,
            exportGrid: false,
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "edit", text: "编辑", layout: "3",
                    click: function () {
                        var checkRows = resultProblemGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            popDataWindow.setOptions({"title": "编辑题目详情"});
                            popData(checkRows[0].projectId, checkRows[0].instanceId, "edit");
                        } else {
                            NotificationUtil("请选择需要编辑的题目", "error");
                        }
                    }
                }, {
                    name: "deleter", text: "删除", layout: "3",
                    click: function () {
                        var eiInfo = new EiInfo();
                        var checkRows = resultProblemGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            var instanceId = checkRows[0].instanceId;
                            eiInfo.set("instanceId", instanceId);
                            IPLAT.confirm({
                                message: '<b style="color: red">确定执行删除操作吗？该操作不可恢复</b></i>',
                                okFn: function (e) {
                                    EiCommunicator.send("SQBZ0101", "deleteInstanceAndItem", eiInfo, {
                                        onSuccess: function (ei) {
                                            resultProblemGrid.removeRows(checkRows);
                                        }
                                    })
                                },
                                cancelFn: function (e) {
                                }
                            })
                        } else {
                            IPLAT.NotificationUtil("请选择需要删除的信息", "error")
                        }
                    }
                }]
            }
        }
    }

    //确定方法
    $("#ADD").on("click", function (e) {
        //调查类型编码
        // var assessTypeCode = IPLAT.EFSelect.value($("#sqType"));
        var assessTypeCode = "01";
        //调查类型名
        // var assessTypeName = IPLAT.EFSelect.text($("#sqType"));
        var assessTypeName = "01";
        //考核主题
        var standardName = $("#inqu_status-standardName").val();
        //考核对象
        var asseccDeptName = $("#inqu_status-asseccDeptName").val();
        //负责人
        var assessWorkNameleader = $("#inqu_status-assessWorkNameleader").val();
        //邮箱
        var assessMail = $("#inqu_status-assessMail").val();
        //备注
        var remark = $("#inqu_status-remark").val();
        //id
        var id = $("#id").val();
        //参数校验
        if (assessTypeCode == "" || assessTypeName == "" || standardName == "" || assessWorkNameleader == "") {
            NotificationUtil("红色  * 为必填项", "error");
            return;
        }
        //校验邮箱格式
        if (assessMail != "") {
            var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
            if (!reg.test(assessMail)) {
                NotificationUtil("请按正确格式输入邮箱", "error");
                return;
            }
        }
        var info = new EiInfo();
        info.set("assessTypeCode", assessTypeCode);
        info.set("assessTypeName", assessTypeName);
        info.set("standardName", standardName);
        info.set("asseccDeptName", asseccDeptName);
        info.set("assessWorkNameleader", assessWorkNameleader);
        info.set("assessMail", assessMail);
        info.set("remark", remark);
        info.set("id", id);
        EiCommunicator.send("SQBZ04", "add", info, {
            onSuccess: function (ei) {
                var status = ei.getStatus();
                if (status == -1) {
                    IPLAT.alert(ei.getMsg());
                } else {
                    setTimeout(function () {
                        NotificationUtil(ei.getMsg(), "success");
                        window.parent.location.reload()
                    }, 300);
                }
                resultGrid.dataSource.page(1);
            }
        });
    });
});

//创建kendo.data.Model
function createModel(id) {
    var gridRow = kendo.data.Model.define({
        id: "id",
        fields: {
            "projectId": {type: "string"},
            "projectName": {type: "string"},
            "projectRemark": {type: "string"},
            "operation": {type: "string"},
            "orderNo": {type: "int"},
        }
    });
    var model = new gridRow({id: id});
    return model;
}

//查看
function popData(projectId, instanceId, type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/SQBZ010101?initLoad&projectId=" + projectId + "&instanceId=" + instanceId + "&type=" + type;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url,
            });
        }
    });
    popDataWindow.open().center();
}

// 添加题目成功后回显题目
function addInstanceRows(model) {
    // 去重处理
    var items = resultProblemGrid.getDataItems();
    var row = model;
    var isExist = false;
    for (var i in items) {
        var item = items[i];
        if (item.instanceId == row.instanceId) {
            resultProblemGrid.removeRows(item);
        }
    }
    if (!isExist) {
        resultProblemGrid.addRows(model);
    }
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

function checkProjectName(ProjectItems) {
    for (let i = 0; i < ProjectItems.length; i++) {
        let projectName = ProjectItems[i].projectName;
        if (projectName == "") {
            return true;
        }
    }
    return false;
}

function checkOrderNo(ProjectItems) {
    for (let i = 0; i < ProjectItems.length; i++) {
        let orderNo = ProjectItems[i].orderNo;
        if (orderNo == "") {
            return true;
        } else {
            return checkRate(orderNo);
        }
    }
    return false;
}

function checkRate(nubmer) {
    var re = /^[0-9]+.?[0-9]*/;//判断字符串是否为数字//判断正整数/[1−9]+[0−9]∗]∗/
    if (re.test(nubmer)) {
        return false;
    }
    return true;
}