$(function() {
// 开启校验

    console.log(__ei);
    var id = __ei.id;
    // 设备分类
    var classifyId = __ei.classifyId;
    var classifyName = __ei.classifyName;
    IPLAT.EFPopupInput.value($("#inqu_status-0-classifyId"),classifyId);
    IPLAT.EFPopupInput.text($("#inqu_status-0-classifyId"),classifyName);
    // 安装地方
    var fixedPlaceId = __ei.spotId;
    var fixedPlaceNum = __ei.spotCode;
    var fixedPlaceName = __ei.spotName;
    IPLAT.EFPopupInput.value($("#inqu_status-0-fixedPlace"),fixedPlaceNum);
    IPLAT.EFPopupInput.text($("#inqu_status-0-fixedPlace"),fixedPlaceName);

    // 管理员
    var workNo = __ei.managerNo;
    var name = __ei.managerName;
    IPLAT.EFPopupInput.value($("#inqu_status-0-managerNo"),workNo);
    IPLAT.EFPopupInput.text($("#inqu_status-0-managerName"),name);
    // 管理科室
    var applyDeptNum = __ei.applyDeptNum;
    var applyDeptName = __ei.applyDeptName;
    IPLAT.EFPopupInput.value($("#inqu_status-0-applyDeptNum"),applyDeptNum);
    IPLAT.EFPopupInput.text($("#inqu_status-0-applyDeptName"),applyDeptName);


    var validator = IPLAT.Validator({
        id: "result"
    });

    if (__ei.type === "edit") {
        IPLAT.EFTreeInput.setAllFields($("#inqu_status-0-classifyId"), __ei.classifyId, __ei.classifyName);
        $("#inqu_status-0-classifyId_textField").val(__ei.classifyName);
    }

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
                model["remark"] = "";
                resultDGrid.addRows(model);
                fileChooseWindow.close();
            },
        }
    }


    /* 合同管理员自动带出科室 */
    IPLATUI.EFPopupInput = {
        "inqu_status-0-fixedPlace": {
            backFill: function (e) {
                IPLAT.EFInput.value($("#inqu_status-0-building"), e.model['building']);
                IPLAT.EFInput.value($("#inqu_status-0-floor"), e.model['floor']);
                IPLAT.EFInput.value($("#inqu_status-0-spotId"), e.model['spotId']);
                IPLAT.EFInput.value($("#inqu_status-0-spotCode"), e.model['spotNum']);
                IPLAT.EFInput.value($("#inqu_status-0-spotName"), e.model['spotName']);
            },
        },
        "inqu_status-0-managerName": {
            backFill: function (e) {
                IPLAT.EFPopupInput.value($("#inqu_status-0-applyDeptName"), e.model.deptNum);
                IPLAT.EFPopupInput.text($("#inqu_status-0-applyDeptName"), e.model.deptName);
            }
        },
        /*工程项目自动带出附件*/
        "Pm": {
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
        var fileList = [];
        //开启校验
        IPLATUI.EFGrid = {
            "resultD": {
                //pageable : false,
                toolbarConfig: {
                    buttons:[
                       {
                            name: "downLoadFile",
                            text: "下载",
                            layout: "3",
                            click: function () {
                                downLoadFile()
                            }
                        },{
                            name: "lookFile",
                            text: "预览",
                            layout: "3",
                            click: function () {
                                var checkRows = resultDGrid.getCheckedRows();
                                if (checkRows.length > 0) {
                                    var docPath = checkRows[0].filePath;
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
                    ],

                    // buttons: buttonList,
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

        //通过
        $("#PASS").on("click", function () {
            var eiInfo = new EiInfo();
            eiInfo.setByNode("result");
            //获取参数
            var eiInfo = new EiInfo();
            eiInfo.setByNode("result");
            var file = resultDGrid.getDataItems();
            let classifyId = $("#inqu_status-0-classifyId").val();
            var id = IPLAT.EFInput.value($("#inqu_status-0-id"));
            var emo = IPLAT.EFInput.value($("#inqu_status-0-emo4"));
            var status = IPLAT.EFInput.value($("#inqu_status-0-status"));
            var deleteFile = fileList;
            eiInfo.set("id",id)
            eiInfo.set("emo",emo)
            eiInfo.set("file", file)
            eiInfo.set("deleteFile", deleteFile)
            eiInfo.set("classifyId", classifyId)
            eiInfo.set("status",status)
            if (!validatePR(eiInfo)) {
                return;
            }
            //提交
            EiCommunicator.send("HISQ0107", "evaluationPass", eiInfo, {
                onSuccess: function (ei) {
                    closeCurrentWindow();
                    IPLAT.NotificationUtil(ei.msg)
                    window.parent["resultGrid"].dataSource.page(1);
                }
            })
        });
  //驳回
    $("#OVERRULE").on("click", function () {
        var eiInfo = new EiInfo();
        eiInfo.setByNode("result");
        //获取参数
        var eiInfo = new EiInfo();
        eiInfo.setByNode("result");
        var file = resultDGrid.getDataItems();
        var id = IPLAT.EFInput.value($("#inqu_status-0-id"));
        let classifyId = $("#inqu_status-0-classifyId").val();
        var deleteFile = fileList;
        var emo = IPLAT.EFInput.value($("#inqu_status-0-emo4"));
        var status = IPLAT.EFInput.value($("#inqu_status-0-status"));
        if(validate(emo)) {
            eiInfo.set("id", id)
            eiInfo.set("emo", emo)
            eiInfo.set("file", file)
            eiInfo.set("deleteFile", deleteFile)
            eiInfo.set("classifyId", classifyId)
            eiInfo.set("status",status)
            if (!validatePR(eiInfo)) {
                return;
            }
            //提交
            EiCommunicator.send("HISQ0107", "evaluationOverrule", eiInfo, {
                onSuccess: function (ei) {
                    NotificationUtil("审批成功", "success");
                    closeCurrentWindow();
                    IPLAT.NotificationUtil(ei.msg)
                    window.parent["resultGrid"].dataSource.page(1);
                }
            })
        }
    });



    function validate(emo) {
        if(emo == "" ){
            NotificationUtil("请填写驳回理由","error");
            return false;
        }else if(emo == null){
            NotificationUtil("请填写驳回理由","error");
            return false;
        }else if(emo == undefined){
            NotificationUtil("请填写驳回理由","error");
            return false;
        }else {
            return true;
        }
    }




        //取消
        $("#CANCEL").on("click", function () {
            closeCurrentWindow();
        });


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
        if (isEmpty(eiInfo.get("inqu_status-0-iconName"))) {
            IPLAT.NotificationUtil("标识名称不能为空", "error");
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

})