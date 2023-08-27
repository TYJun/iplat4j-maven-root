//文件上传
IPLATUI.EFUpload = {
    "projectFile": {
        showFileList: false,
        loadComplete: function (e) {
            var uploader = e.sender.uploader;
            uploader.clearAllFiles();
        },
        validation: {
            allowedExtensions: [".jpg", "jpeg", ".png", ".gif", ".bmp", ".txt", ".doc", "docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".zip", "rar", ".7z"]
        },
        localization: {
            invalidFileExtension: "文件格式不支持, 上传失败"
        },
        success: function (e) {
            var file = e.files[0];
            var model = createModel(1);
            model["id"] = "";
            model["projectPk"] = "";
            model["attachId"] = e.response.docId;
            model["attachPath"] = e.response.docUrl;
            model["attachName"] = file.name;
            model["attachSize"] = file.size;
            model["attachDesc"] = "";
            resultCGrid.addRows(model);
            fileChooseWindow.close();
        },
    }
}
$(function () {
    IPLATUI.EFPopupInput = {
        "resultA":{
            pageable: false,
            exportGrid: false,
            dataBound: function (e) {

            },
            loadComplete: function (e) {

            }
        }
    }
    var validator = IPLAT.Validator({id: "result"});//开启校验
    //表格按钮处理
    IPLATUI.EFGrid = {
        "result": {
            pageable: false,
            exportGrid: false,
            dataBound: function (e) {

            },
            loadComplete: function (e) {
                $("#typeQuery").on("click", function () {
                    resultGrid.dataSource.page(1);
                })

                $("#typeReset").on("click", function () {
                    $("#inqu_status-0-typeCode").val("");
                    $("#inqu_status-0-typeName").val("");
                    resultGrid.dataSource.page(1);
                })

                $("#typeSave").on("click", function () {
                    var info = new EiInfo();
                    var gridResultA = $("#ef_grid_resultA").data("kendoGrid");
                    var dataItems = gridResultA.getDataItems();
                    gridResultA.removeRows(dataItems);
                    var row = resultGrid.getCheckedRows();
                    var model = row[0];
                    var typeName = model.typeName;
                    var typeCode = model.typeCode;
                    var stageCode = model.stageCode;
                    const stageCodeStr = stageCode.split(";");
                    var stageName = model.stageName;
                    const stageNameStr = stageName.split(";");
                    var stageRemark = model.stageRemark;
                    const stageRemarkStr = stageRemark.split(";");
                    for (var i = stageCodeStr.length; i > 0; i--) {
                        var model = createStageModel(i);
                        var stageCode = stageCodeStr[i - 1];
                        var stageName = stageNameStr[i - 1];
                        var stageRemark = stageRemarkStr[i - 1];
                        model.stageCode = stageCode;
                        model.stageName = stageName;
                        model.stageRemark = stageRemark;
                        gridResultA.addRows(model);
                    }
                    $(".k-window").hide();
                    IPLAT.EFPopupInput.text($("#inqu_status-0-projectTypeNum"),typeName);
                    IPLAT.EFPopupInput.value($("#inqu_status-0-projectTypeNum"),typeCode);
                })
            }
        },
        "resultA":{
            pageable: false,
            exportGrid: false,
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
            },
            dataBound: function (e) {

            },
            loadComplete: function (e) {

            }
        },
        "resultB": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "ADDSTAFF", text: "新增", layout: "3",
                    click: function () {
                        personChooseWindowOpen("STAFF");
                    }
                }, {
                    name: "DELSTAFF", text: "删除", layout: "3",
                    click: function () {
                        var checkRows = resultAGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            resultAGrid.removeRows(checkRows);
                        } else {
                            IPLAT.NotificationUtil("请选择需要删除的执行人信息", "failure")
                        }
                    }
                }]
            },
        },
        "resultC": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "ADDKNOW", text: "新增", layout: "3",
                    click: function () {
                        personChooseWindowOpen("KNOW");
                    }
                }, {
                    name: "DELKNOW", text: "删除", layout: "3",
                    click: function () {
                        var checkRows = resultBGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            resultBGrid.removeRows(checkRows);
                        } else {
                            IPLAT.NotificationUtil("请选择需要删除的执行人信息", "failure")
                        }
                    }
                }]
            }
        },
        "resultD": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "addFile", text: "上传", layout: "3",
                    click: function () {
                        fileChooseWindow.open().center()
                    }
                }, {
                    name: "delFile", text: "删除", layout: "3",
                    click: function () {
                        var checkRows = resultCGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            resultCGrid.removeRows(checkRows);
                        } else {
                            IPLAT.NotificationUtil("请选择需要删除的项目附件信息", "failure")
                        }
                    }
                }, {
                    name: "downLoadFile", text: "下载", layout: "3",
                    click: function () {
                        downLoadFile()
                    }
                }]
            }
        },
    }

    //保存
    $("#SAVEPR").on("click", function () {
        //获取参数
        var eiInfo = new EiInfo();
        eiInfo.setByNode("result");
        //获取tab数据
        var staffArray = resultAGrid.getDataItems();
        var knowArray = resultBGrid.getDataItems();
        var fileArray = resultCGrid.getDataItems();
        eiInfo.set("staffList", staffArray)
        eiInfo.set("knowList", knowArray)
        eiInfo.set("fileList", fileArray)
        //参数校验
        if (!validatePR(eiInfo)) {
            return;
        }
        //提交
        EiCommunicator.send("PM0103", "saveProject", eiInfo, {
            onSuccess: function (ei) {
                closeCurrentWindow();
                IPLAT.NotificationUtil(ei.msg)
                window.parent["resultGrid"].dataSource.page(1);
            }
        })
    });

    //取消
    $("#CANCEL").on("click", function () {
        closeCurrentWindow();
    });
})

//关闭窗口
function closeCurrentWindow() {
    window.parent['projectEditWindow'].close();
}

//打开人员选择窗口
function personChooseWindowOpen(type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/PM0104?methodName=query&inqu_status-0-personType=" + type;
    var popData = $("#personChoose");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url,
            });
        }
    });
    // 打开弹窗
    personChooseWindow.open().center();
}

//接收子页面参数
function addRows(personType, checkRows) {
    for (var index in checkRows) {
        var model = checkRows[index];
        model["number"] = model.phone;
        if ("STAFF" == personType) {
            model["execStaffId"] = model.workNo;
            model["execStaffName"] = model.name;
            //resultAGrid.addRows(model)
            clearRepeat(model, resultAGrid, "execStaffId");
        } else {
            model["notifyStaffId"] = model.workNo;
            model["notifyStaffName"] = model.name;
            //resultBGrid.addRows(model)
            clearRepeat(model, resultBGrid, "notifyStaffId");
        }
    }
}

//处理重复数据
//clearRepeat(model,resultBGrid,"notifyStaffId");
function clearRepeat(model, grid, compareField) {
    var list = grid.getDataItems();
    var isExist = false;
    for (var i in list) {
        var row = list[i];
        if (row[compareField] == model[compareField]) {
            isExist = true;
        }
    }
    if (!isExist) {
        grid.addRows(model)
    }
}

//创建kendo.data.Model
function createModel(id) {
    var gridRow = kendo.data.Model.define({
        id: "uploadId",
        fields: {
            "id": {type: "string"},
            "projectPk": {type: "string"},
            "attachId": {type: "string"},
            "attachPath": {type: "string"},
            "attachName": {type: "string"},
            "attachSize": {type: "number"},
            "attachDesc": {type: "string"}
        }
    });
    var model = new gridRow({uploadId: id});
    return model;
}

//文件下载
function downLoadFile() {
    var checkRows = resultCGrid.getCheckedRows();
    if (checkRows.length > 0) {
        for (var index in checkRows) {
            var docId = checkRows[index].attachId;
            var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
            window.location.href = href;
        }
    } else {
        IPLAT.NotificationUtil("请选择需要下载的文件")
    }
}

//参数校验
function validatePR(eiInfo) {
    if (isEmpty(eiInfo.get("inqu_status-0-projectName"))) {
        IPLAT.NotificationUtil("项目名称不能为空", "failure");
        return false;
    }
    if (isEmpty(eiInfo.get("inqu_status-0-projectProp"))) {
        IPLAT.NotificationUtil("项目性质不能为空", "failure");
        return false;
    }
    if (isEmpty(eiInfo.get("inqu_status-0-projectTypeNum"))) {
        IPLAT.NotificationUtil("项目类型不能为空", "failure");
        return false;
    }
    if (isEmpty(eiInfo.get("inqu_status-0-contorId"))) {
        IPLAT.NotificationUtil("项目负责人不能为空", "failure");
        return false;
    }
    if (isEmpty(eiInfo.get("inqu_status-0-startDate"))) {
        IPLAT.NotificationUtil("开始日期不能为空", "failure");
        return false;
    }
    if (isEmpty(eiInfo.get("inqu_status-0-finishDeadline"))) {
        IPLAT.NotificationUtil("完成期限不能为空", "failure");
        return false;
    }
    if (eiInfo.get("inqu_status-0-finishDeadline").replace("-", "") < eiInfo.get("inqu_status-0-startDate").replace("-", "")) {
        IPLAT.NotificationUtil("完成期限不能小于开始时间", "failure");
        return false;
    }
    var startTime = kendo.toString($("#inqu_status-0-startDate").data("kendoDatePicker").value(), "yyyy-MM-dd");
    var endTime = kendo.toString($("#inqu_status-0-finishDeadline").data("kendoDatePicker").value(), "yyyy-MM-dd");
    //校验
    if (startTime == null || startTime == "") {
        NotificationUtil("请输入开始时间", "error");
        return false;
    }
    if (endTime == null || endTime == "") {
        NotificationUtil("请输入完成时间", "error");
        return false;
    }
    return true;
}

function isEmpty(str) {
    if (str == undefined) {
        return true;
    }
    if (str == null) {
        return true;
    }
    if (str.replace(/(^\s*)|(\s*$)/g, "") == "") {
        return true;
    }
    return false;
}

function dateCheck(date1, date2) {
    if (date1.replace("-", "") > date1.replace("-", "")) {

    }
}


//创建kendo.data.Model
function createStageModel(id){
    var gridRow = kendo.data.Model.define({
        id: "uploadId",
        fields: {
            "stageCode": {type: "string"},
            "stageName": {type: "string"},
            "stageRemark": {type: "string"}
        }
    });
    var model = new gridRow({uploadId:id});
    return model;
}