$(function() {
// 开启校验
var validator = IPLAT.Validator({
    id: "result"
});

IPLATUI.EFSelect = {
    "inqu_status-0-payAgreNum": {
        change: function (e) {
            resultBGrid.dataSource.page(1);
            resultCGrid.dataSource.page(1);
        }
    },
};
/* 附件上传 */
IPLATUI.EFUpload = {
    "contentFile": {
        loadComplete: function (e) {
            var uploader = e.sender.uploader;
            uploader.clearAllFiles();
        },
        validation: {
            allowedExtensions: [".jpg", "jpeg", ".png", ".gif", ".bmp",
                ".txt", ".doc", "docx", ".xls", ".xlsx", ".ppt",
                ".pptx", ".pdf", ".zip", "rar", ".7z"]
        },
        localization: {
            invalidFileExtension: "文件格式不支持, 上传失败"
        },
        success: function (e) {
            var file = e.files[0];
            var model = createModel(1);
            model["id"] = "";
            model["relationId"] = "";
            model["docId"] = e.response.docId;
            model["filePath"] = e.response.docUrl;
            model["fileName"] = file.name;
            model["fileSize"] = file.size;
            var uploadTime = e.response.uploadTime;
            var uploadTimeNice = timeSet(uploadTime);
            model["recCreateTime"] = uploadTimeNice;
            model["remark"] = "";
            resultDGrid.addRows(model);
            fileChooseWindow.close();
        },
    }
}


/**
 * 维修事项选择按钮事件
 */
$("#selectItem").on('click', function(){
    itemChooseWindow.open().center();
});


/* 合同管理员自动带出科室 */
IPLATUI.EFPopupInput = {
    "inqu_status-0-fixedPlace":{
        backFill: function (e) {
            IPLAT.EFInput.value($("#inqu_status-0-building"),e.model['building']);
            IPLAT.EFInput.value($("#inqu_status-0-floor"),e.model['floor']);
            IPLAT.EFInput.value($("#inqu_status-0-spotId"),e.model['spotId']);
            IPLAT.EFInput.value($("#inqu_status-0-spotCode"),e.model['spotNum']);
            IPLAT.EFInput.value($("#inqu_status-0-spotName"),e.model['spotName']);
        },
    },
    "inqu_status-0-managerName": {
        backFill: function (e) {
            IPLAT.EFPopupInput.value($("#inqu_status-0-applyDeptName"), e.model.deptNum);
            IPLAT.EFPopupInput.text($("#inqu_status-0-applyDeptName"), e.model.deptName);
        }
    },
    /*工程项目自动带出附件*/
    "inqu_status-0-Pm": {
        backFill: function (e) {
            var info = new EiInfo();
            var gridResultG = $("#ef_grid_resultG").data("kendoGrid");
            gridResultG.removeRows(gridResultG.getDataItems());
            $("#inqu_status-0-projectId").val(e.model.pid);
            info.set("id", e.model.pid);
            EiCommunicator.send("HISQ0101", "getProjectFile", info, {
                onSuccess: function (ei) {
                    NotificationUtil(ei.getMsg(), "success");
                    var param = ei.extAttr.param;

                    for (var i = 0; i < param.length; i++) {

                        var model = createModelProjectFile(i);
                        var par = param[i];

                        var id = par.id;
                        var relationId = par.relationId;
                        var docId = par.docId;
                        var filePath = par.filePath;
                        var fileName = par.fileName;
                        var fileSize = par.fileSize;
                        var remark = par.remark;
                        var recCreator = par.recCreator;
                        var recCreateTime = par.recCreateTime;

                        //debugger;
                        model["id"] = "id";
                        model["relationId"] = "relationId";
                        model["docId"] = docId;
                        model["filePath"] = filePath;
                        model["fileName"] = fileName;
                        model["fileSize"] = fileSize;
                        model["remark"] = remark;
                        model["recCreator"] = recCreator;
                        model["recCreateTime"] = recCreateTime;
                        gridResultG.addRows(model);
                    }
                }
            });
        },
    }
}

/* 分页处理 */


/* 分页处理 */
$(function () {
    var buttonList = [];
    if ($("#type").val() == "add") {
        var addFile = {
            name: "addFile",
            text: "上传",
            layout: "3",
            click: function () {
                fileChooseWindow.open().center()
            }
        }
        buttonList.push(addFile);
        var deleteFile = {
            name: "delFile",
            text: "删除",
            layout: "3",
            click: function () {
                var checkRows = resultBGrid.getCheckedRows();
                if (checkRows.length > 0) {
                    resultBGrid.removeRows(checkRows);
                    fileList.push(checkRows[0]);
                    console.log(fileList);
                } else {
                    IPLAT.NotificationUtil("请选择需要删除的附件信息")
                }
            }
        }
        buttonList.push(deleteFile);
        var loadFile = {
            name: "downLoadFile",
            text: "下载",
            layout: "3",
            click: function () {
                downLoadFile()
            }
        }
        buttonList.push(loadFile);
    } else if ($("#type").val() == "edit") {
        var addFile = {
            name: "addFile",
            text: "上传",
            layout: "3",
            click: function () {
                fileChooseWindow.open().center()
            }
        }
        buttonList.push(addFile);
        var deleteFile = {
            name: "delFile",
            text: "删除",
            layout: "3",
            click: function () {
                var checkRows = resultBGrid.getCheckedRows();
                if (checkRows.length > 0) {
                    resultBGrid.removeRows(checkRows);
                    fileList.push(checkRows[0]);
                    console.log(fileList);
                } else {
                    IPLAT.NotificationUtil("请选择需要删除的附件信息")
                }
            }
        }
        buttonList.push(deleteFile);
        var loadFile = {
            name: "downLoadFile",
            text: "下载",
            layout: "3",
            click: function () {
                downLoadFile()
            }
        }
        buttonList.push(loadFile);
        var lookFile = {
            name: "lookFile",
            text: "预览",
            layout: "3",
            click: function () {
                var checkRows = resultBGrid.getCheckedRows();
                if (checkRows.length > 0) {
                    var docPath = checkRows[0].attachPath;
                    var last = docPath.lastIndexOf("/");
                    var first = docPath.indexOf("/");
                    var fileName = docPath.substring(last + 1, docPath.length);
                    var filePath = docPath.substring(0, first);
                    if (docPath.indexOf(".pdf") >= 0) {
                        window.open(IPLATUI.CONTEXT_PATH + "/pdf?fileName=" + fileName + "&filePath=" + filePath);
                    } else {
                        IPLAT.NotificationUtil("只能查看pdf格式的文档", "error")
                        return;
                    }
                } else {
                    IPLAT.NotificationUtil("请选择需要预览的附件信息")
                }
            }
        }
        buttonList.push(lookFile);
    } else if ($("#type").val() == "see") {
        var loadFile = {
            name: "downLoadFile",
            text: "下载",
            layout: "3",
            click: function () {
                downLoadFile()
            }
        }
        buttonList.push(loadFile);
        var lookFile = {
            name: "lookFile",
            text: "预览",
            layout: "3",
            click: function () {
                var checkRows = resultBGrid.getCheckedRows();
                if (checkRows.length > 0) {
                    var docPath = checkRows[0].attachPath;
                    var last = docPath.lastIndexOf("/");
                    var first = docPath.indexOf("/");
                    var fileName = docPath.substring(last + 1, docPath.length);
                    var filePath = docPath.substring(0, first);
                    if (docPath.indexOf(".pdf") >= 0) {
                        window.open(IPLATUI.CONTEXT_PATH + "/pdf?fileName=" + fileName + "&filePath=" + filePath);
                    } else {
                        IPLAT.NotificationUtil("只能查看pdf格式的文档", "error")
                        return;
                    }
                } else {
                    IPLAT.NotificationUtil("请选择需要预览的附件信息")
                }
            }
        }
        buttonList.push(lookFile);
    }

    var fileList = [];
    //开启校验
    IPLATUI.EFGrid = {
        "resultD": {
            //pageable : false,
            toolbarConfig: {
                // buttons: buttonList,

                buttons:  [
                    {
                        name: "addFile",
                        text: "上传",
                        layout: "3",
                        click: function () {
                            fileChooseWindow.open().center()
                        }
                    },
                    {
                        name: "delFile",
                        text: "删除",
                        layout: "3",
                        click: function () {
                            var checkRows = resultDGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                resultDGrid.removeRows(checkRows);
                                fileList.push(checkRows[0]);
                                console.log(fileList);
                            } else {
                                IPLAT.NotificationUtil("请选择需要删除的附件信息")
                            }
                        }
                    },
                    {
                        name: "downLoadFile",
                        text: "下载",
                        layout: "3",
                        click: function () {
                            downLoadFile()
                        }
                    }
                ],
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false,
                cancel: false,
                save: false,
                'delete': false
            }
        },
    }
    /* 渲染分页 */
    IPLATUI.EFTab = {
        "tab-tab_grid": {
            select: function (e) {
                var grid = $(e.contentElement).find("div[data-role='grid']").data("kendoGrid")
                if (grid.getDataItems().length === 0) {
                    grid.dataSource.page(1);
                }
            }
        }
    }

    $("#scheduleQuery").on("click", function () {
        resultGrid.dataSource.page(1);
    })

    $("#scheduleReset").on("click", function () {
        $("#inqu_status-0-scheduleAutoNo").val("");
        $("#inqu_status-0-scheduleName").val("");
        resultGrid.dataSource.page(1);
    })

    var s = [];
    var l;
    $("#scheduleSave").on("click", function () {
        s = [];
        var info = new EiInfo();
        var gridResultH = $("#ef_grid_resultH").data("kendoGrid");
        var dataItems = gridResultH.getDataItems();
        gridResultH.removeRows(dataItems);
        var row = resultGrid.getCheckedRows();
        var model = row[0];
        var scheduleName = model.scheduleName;
        var scheduleAutoNo = model.scheduleAutoNo;
        var nodeAutoNo = model.nodeAutoNo;
        const nodeAutoNoStr = nodeAutoNo.split(";");
        var nodeName = model.nodeName;
        const nodeNameStr = nodeName.split(";");
        var nodeRemark = model.nodeRemark;
        const nodeRemarkStr = nodeRemark.split(";");
        for (var i = nodeAutoNoStr.length; i > 0; i--) {
            var model = createNodeModel(i);
            var nodeAutoNo = nodeAutoNoStr[i - 1];
            var nodeName = nodeNameStr[i - 1];
            var nodeRemark = nodeRemarkStr[i - 1];
            model.nodeAutoNo = nodeAutoNo;
            model.nodeName = nodeName;
            model.nodeRemark = nodeRemark;
            l = {
                textField: nodeNameStr[nodeAutoNoStr.length - i],
                valueField: nodeAutoNoStr[nodeAutoNoStr.length - i]
            }
            s.push(l);
            gridResultH.addRows(model);
        }
        $(".k-window").hide();
        IPLAT.EFPopupInput.text($("#inqu_status-0-schedule"), scheduleName);
        IPLAT.EFPopupInput.value($("#inqu_status-0-schedule"), scheduleAutoNo);
        var dataSource = new kendo.data.DataSource({
            data: s
        });
        //IPLAT.EFSelect.setDataSource($("#inqu_status-0-projectProgress"), dataSource);
    })

    //弹窗保存
    $("#SAVEPR").on("click", function () {
        var eiInfo = new EiInfo();
        eiInfo.setByNode("result");
        //获取参数
        var eiInfo = new EiInfo();
        eiInfo.setByNode("result");
        var file = resultDGrid.getDataItems();
        let classifyId = $("#inqu_status-0-classifyId").val();
        let spotName = $("#inqu_status-0-spotName").val();
        let spotCode = $("#inqu_status-0-spotCode").val();
        var deleteFile = fileList;
        eiInfo.set("file", file)
        eiInfo.set("deleteFile", deleteFile)
        eiInfo.set("classifyId", classifyId)
        eiInfo.set("spotName",spotName)
        eiInfo.set("spotCode",spotCode)
        if (!validatePR(eiInfo)) {
            return;
        }
        //提交
        EiCommunicator.send("HIXX0101", "saveIdentifyingInformation", eiInfo, {
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
    window.parent['popDataWindow'].close();
}



//文件下载
function downLoadFile() {
    var checkRows = resultDGrid.getCheckedRows();
    if (checkRows.length > 0) {
        for (var index in checkRows) {
            var docId = checkRows[index].docId;
            var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
            window.location.href = href;
        }
    } else {
        IPLAT.NotificationUtil("请选择需要下载的文件")
    }
}

//子参数
function addRows(checkRows, type) {
    for (var index in checkRows) {
        var model = checkRows[index];
        if (type == "cont") {
            model["contTermName"] = model.contTermName;
            model["cotent"] = model.contTermContent;
            model["remark"] = model.remark;
            //resultAGrid.addRows(model)
            clearRepeat(model, resultAGrid, "contTermName");
        } else {
            model["workNo"] = model.workNo;
            model["name"] = model.name;
            model["phone"] = model.number;
            //resultEGrid.addRows(model)
            clearRepeat(model, resultEGrid, "workNo");
        }
    }
}

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
            "id": {
                type: "string"
            },
            "relationId": {
                type: "string"
            },
            "docId": {
                type: "string"
            },
            "filePath": {
                type: "string"
            },
            "fileName": {
                type: "string"
            },
            "fileSize": {
                type: "number"
            },
            "remark": {
                type: "string"
            },
            "recCreateTime":{
                type:"string"
            }
        }
    });
    var model = new gridRow({uploadId: id});
    return model;
}

//创建kendo.data.Model
function createModelProjectFile(id) {
    var gridRow = kendo.data.Model.define({
        id: "uploadId",
        fields: {
            "id": {
                type: "string"
            },
            "relationId": {
                type: "string"
            },
            "docId": {
                type: "string"
            },
            "filePath": {
                type: "string"
            },
            "fileName": {
                type: "string"
            },
            "fileSize": {
                type: "number"
            },
            "remark": {
                type: "string"
            },
            "recCreator": {
                type: "string"
            },
            "recCreateTime": {
                type: "string"
            }
        }
    });
    var model = new gridRow({uploadId: id});
    return model;
}

//参数校验
function validatePR(eiInfo) {
    if (isEmpty(eiInfo.get("applyNo"))) {
        IPLAT.NotificationUtil("标识名称不能为空","warning");
        return false;
    }
    if (isEmpty(eiInfo.get("classifyId"))) {
        IPLAT.NotificationUtil("标识分类不能为空","warning");
        return false;
    }
    if (isEmpty(eiInfo.get("inqu_status-0-iconEnContent"))) {
        IPLAT.NotificationUtil("标识英文内容不能为空","warning");
        return  false;
    }

    if (isEmpty(eiInfo.get("inqu_status-0-iconZhContent"))) {
        IPLAT.NotificationUtil("标识中文内容不能为空","warning");
        return  false;
    }

    if (isEmpty(eiInfo.get("spotName"))) {
        IPLAT.NotificationUtil("安装地点不能为空","warning");
        return  false;
    }
    if (isEmpty(eiInfo.get("inqu_status-0-iconAmount"))) {
        IPLAT.NotificationUtil("数量不能为空","warning");
        return  false;
    }

    if (isEmpty(eiInfo.get("inqu_status-0-spotDesc"))) {
        IPLAT.NotificationUtil("安装地点说明不能为空","warning");
        return  false;
    }

    return true;
}

function isEmpty(str) {
    if(str == undefined){
        return true;
    }
    if(str == null){
        return true;
    }
    if(str.replace(/(^\s*)|(\s*$)/g, "") == ""){
        return true;
    }
    return false;
}

//创建kendo.data.Model
function createNodeModel(id) {
    var gridRow = kendo.data.Model.define({
        id: "nodeId",
        fields: {
            "nodeCode": {type: "string"},
            "nodeName": {type: "string"},
            "nodeRemark": {type: "string"}
        }
    });
    var model = new gridRow({nodeId: id});
    return model;
}

// $(function (){
        //标识名称选择，数据返回
        Message.on("item","itemChoose",function(param) {
            $("#applyNo").val(param['applyNo']);
            $("#iconName").val(param['iconName']);
            $("#inqu_status-0-building").val(param['building']);
            $("#inqu_status-0-floor").val(param['floor']);
            $("#inqu_status-0-spotCode").val(param['spotCode']);
            $("#inqu_status-0-spotName").val(param['spotName']);
            $("#inqu_status-0-iconAmount").val(param['iconAmount']);
            $("#inqu_status-0-iconEnContent").val(param['iconEnContent']);
            $("#inqu_status-0-iconZhContent").val(param['iconZhContent']);
            // IPLAT.EFTreeInput.setAllFields( $("#inqu_status-0-spotName") , param['spotCode'] ,param['spotName']);
            IPLAT.EFTreeInput.setAllFields( $("#inqu_status-0-classifyId") , param['classifyId'] ,param['classifyName']);
            IPLAT.EFTreeInput.setAllFields( $("#inqu_status-0-deptName") , param['applyDeptNum'] ,param['applyDeptName']);
            IPLAT.EFTreeInput.setAllFields( $("#inqu_status-0-managerName") , param['managerNo'] ,param['managerName']);
            //关闭窗口
            itemChooseWindow.close();
        });
    // })

    // $(document).ready(function(){
    //     $("#iconName").parent().css('width','82%')
    // });



    /**
     * 上传日期设置日期格式
     */


    function timeSet(uploadTime) {
        var uploadTimew = uploadTime.replace(/(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/g, '$1-$2-$3 $4:$5:$6');
        return  uploadTimew;
    }




})