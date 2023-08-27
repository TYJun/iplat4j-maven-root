// 开启校验
var validator = IPLAT.Validator({
    id: "result"
});




IPLATUI.EFSelect = {
    "inqu_status-0-payAgreNum": {
        change: function (e) {
            FailGrid.dataSource.page(1);
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
            model["contId"] = "";
            model["docId"] = e.response.docId;
            model["filePath"] = e.response.docUrl;
            model["fileName"] = file.name;
            model["fileSize"] = file.size;
            var uploadTime = e.response.uploadTime;
            var uploadTimeNice = timeSet(uploadTime);
            model["recCreateTime"] = uploadTimeNice;
            model["remark"] = "";
            FailGrid.addRows(model);
            fileChooseWindow.close();
        },
    }
}

/* 合同管理员自动带出科室 */
IPLATUI.EFPopupInput = {
    "inqu_status-0-contAdmin": {
        backFill: function (e) {
            IPLAT.EFPopupInput.value($("#inqu_status-0-contDeptNum"), e.model.deptNum);
            IPLAT.EFPopupInput.text($("#inqu_status-0-contDeptNum"), e.model.deptName);
        }
    }
}


/* 分页处理 */
$(function () {
    console.log(__ei);
    var id = __ei.id;
    IPLAT.EFPopupInput.value($("#inqu_status-0-id"),id);
    var buttonList = [];
    var fileList = [];
    //开启校验
    IPLATUI.EFGrid = {
        "Details": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false,
                cancel: false,
                save: false,
                'delete': false,
                buttons: []
            },
        },
        "Fail": {
            toolbarConfig: {
                pageable: false,
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
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
                            var checkRows = FailGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                FailGrid.removeRows(checkRows);
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
                ]
            },
            dataBinding: function (e) {
                for (var i = 0, length = e.items.length; i < length; i++){
                    if(isAvailable(e.items[i].payAmount)){
                        e.items[i].payAmount = parseFloat(e.items[i].payAmount);
                    }
                    if(isAvailable(e.items[i].payRate)){
                        e.items[i].payRate = parseFloat(e.items[i].payRate);
                    }
                }
            },
        },
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
        var contSignDate = eiInfo.get("inqu_status-0-signDate");
        var contValidDate = eiInfo.get("inqu_status-0-validDate");
        var contOverDate = eiInfo.get("inqu_status-0-overDate");
        if (contSignDate > contOverDate) {
            IPLAT.NotificationUtil("合同签订日期不能大于合同终止日期", "warning");
            return false;
        }
        if (contValidDate > contOverDate) {
            IPLAT.NotificationUtil("合同生效日期不能大于合同终止时间", "warning");
            return false;
        }
        //获取参数
        var eiInfo = new EiInfo();
        eiInfo.setByNode("result");
        //获取tab数据
        var hta = DetailsGrid.getDataItems();
        var file = FailGrid.getDataItems();
        var deleteFile = fileList;
        // var checkRows = resultGrid.getCheckedRows();
        // var id =checkRows.id;
        eiInfo.set("hta", hta)
        eiInfo.set("file", file)
        eiInfo.set("deleteFile", deleteFile)
        // eiInfo.set("id",id)
        if(!validatePR(eiInfo)){ return; }
        //提交
        EiCommunicator.send("MPCG0104", "saveMaterialContent", eiInfo, {
            onSuccess: function (ei) {
                if (ei.getStatus() === 500) {
                    IPLAT.alert(ei.getMsg());
                } else {
                closeCurrentWindow();
                IPLAT.NotificationUtil(ei.msg)
                window.parent["resultGrid"].dataSource.page(1);
            }
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
    var checkRows = FailGrid.getCheckedRows();
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

//子参数
//添加物资（过滤重复）
// function addRows(checkRows){
//     var matList = resultGrid.getDataItems();
//     for (var index in checkRows) {
//         var model = checkRows[index];
//         var isExist = false;
//         for(var i in matList) {
//             var mat = matList[i];
//             if(mat.matNum == model.matNum){
//                 isExist = true;
//             }
//         }
//         if(!isExist){
//             model['enterNum'] = '0';
//             model['enterPrice'] = '0';
//             model['enterAmount'] = '0';
//             resultGrid.addRows(model)
//         }
//     }
// }

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
            "contId": {
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


//参数校验
function validatePR(eiInfo) {
    if (isEmpty(eiInfo.get("inqu_status-0-contNo"))) {
        IPLAT.NotificationUtil("合同号不能为空","error");
        return false;
    }
    if (isEmpty(eiInfo.get("inqu_status-0-contName"))) {
        IPLAT.NotificationUtil("合同名称不能为空","error");
        return false;
    }

    if (isEmpty(eiInfo.get("inqu_status-0-contClassify"))) {
        IPLAT.NotificationUtil("合同分类不能为空","error");
        return false;
    }

    if (isEmpty(eiInfo.get("inqu_status-0-contType"))) {
        IPLAT.NotificationUtil("合同类型不能为空","error");
        return false;
    }

    if (isEmpty(eiInfo.get("inqu_status-0-supplierNum"))) {
        IPLAT.NotificationUtil("供应商不能为空","error");
        return false;
    }

    if (isEmpty(eiInfo.get("inqu_status-0-contCost"))) {
        IPLAT.NotificationUtil("金额不能为空","error");
        return false;
    }

    if (isEmpty(eiInfo.get("inqu_status-0-signDate"))) {
        IPLAT.NotificationUtil("签订日期不能为空","error");
        return false;
    }

    if (isEmpty(eiInfo.get("inqu_status-0-validDate"))) {
        IPLAT.NotificationUtil("生效日期不能为空","error");
        return false;
    }

    if (isEmpty(eiInfo.get("inqu_status-0-overDate"))) {
        IPLAT.NotificationUtil("终止日期不能为空","error");
        return false;
    }

    if (isEmpty(eiInfo.get("inqu_status-0-manageDeptNum"))) {
        IPLAT.NotificationUtil("合同所属部门不能为空","error");
        return false;
    }

    if (isEmpty(eiInfo.get("inqu_status-0-managerNum"))) {
        IPLAT.NotificationUtil("管理员不能为空","error");
        return false;
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
function timeSet(uploadTime) {
    var uploadTimew = uploadTime.replace(/(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/g, '$1-$2-$3 $4:$5:$6');
    return  uploadTimew;
}