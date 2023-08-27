$(function () {
    var type = IPLAT.EFInput.value($("#type"));
    if (type == "look") {
        setTimeout(
            function(){
                // $("#CONFIRM").hide();
                // $("#CONFIRM").attr("style", "display:none");
                document.getElementById("CONFIRM").style.display="none";
            },100
        );
    }
    // 开启校验
    var validator = IPLAT.Validator({
        id: "result"
    });

    // 确认
    $("#CONFIRM").on("click", function (e) {
        var eiInfo = new EiInfo();
        var resultA = resultAGrid.getDataItems();
        var resultB = resultBGrid.getDataItems();
        eiInfo.setByNode("result");
        eiInfo.set("resultA", resultA);
        eiInfo.set("resultB", resultB);
        if (!validator.validate()) {
            IPLAT.NotificationUtil("校验不通过", "error")
            //校验不通过
            return;
        }
        //提交
        EiCommunicator.send("CMZB0101", "saveContentBid", eiInfo, {
            onSuccess: function (ei) {
                window.parent.bidDataWindow.close();
                IPLAT.NotificationUtil(ei.msg)
                window.parent["resultGrid"].dataSource.page(1);
            }
        })
    });
    // 取消
    $("#CANCEL").on("click", function (e) {
        window.parent.bidDataWindow.close();
    });

    IPLATUI.EFUpload = {
        "bidFile": {
            loadComplete: function (e) {
                // 清空所有历史上传文件
                var uploader = e.sender.uploader;
                uploader.clearAllFiles();
            },
            localization: {
                // 不支持格式提示信息
                invalidFileExtension: "文件格式不支持, 上传失败",
                invalidMaxFileSize: "上传文件超过1G, 请重新选择",
            },
            validation: {
                // 文件格式过滤
                allowedExtensions: [".jpg", "jpeg", ".png", ".gif", ".bmp", ".txt", ".doc", "docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".zip", "rar", ".7z"],
                maxFileSize: 1073741824,
            },
            success: function (e) {
                var file = e.files[0];
                var fileSize = file.size / 1024 / 1024;
                fileSize = fileSize.toFixed(2);
                var docId = e.response.docId;
                var model = createModel(docId);
                model["filePath"] = e.response.docUrl;
                model["fileName"] = file.name;
                model["fileSize"] = fileSize;
                var eiInfo = new EiInfo();
                eiInfo.set("uploadId", model.uploadId)
                eiInfo.set("fileName", model.fileName);
                eiInfo.set("filePath", model.filePath);
                eiInfo.set("fileSize", model.fileSize);
                fileChooseWindow.close();
                if (e.operation == "upload") {
                    resultBGrid.addRows(model);
                    EiCommunicator.send("CMZB01", "saveContentBidFile", eiInfo, {
                        onSuccess: function (ei) {
                            IPLAT.NotificationUtil(ei.msg);
                        }
                    })
                } else if (e.operation == "remove") {
                    resultBGrid.removeRows(model);
                }
            }
        }
    }

    IPLATUI.EFGrid = {
        "resultA": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "new", text: "添加", layout: "3",
                    click: function () {
                        supplierWindows();
                    }
                }, {
                    name: "deleter", text: "删除", layout: "3",
                    click: function () {
                        var checkRows = resultAGrid.getCheckedRows();
                        resultAGrid.removeRows(checkRows);
                    }
                }]
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
                    name: "upload", text: "上传", layout: "3",
                    click: function () {
                        fileChooseWindow.open().center()
                    }
                }, {
                    name: "download", text: "下载", layout: "3",
                    click: function () {
                        var checkRows = resultBGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            for (var index in checkRows) {
                                var docId = checkRows[index].uploadId;
                                var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
                                window.location.href = href;
                            }
                        } else {
                            IPLAT.NotificationUtil("请选择需要下载的文件", "error")
                        }
                    }
                }, {
                    name: "deleter", text: "删除", layout: "3",
                    click: function () {
                        var checkRows = resultBGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            var eiInfo = new EiInfo();
                            var fileId = checkRows[0].uploadId;
                            var filePath = checkRows[0].filePath;
                            var fileName = checkRows[0].fileName;
                            var model = createModel(fileId);
                            model["filePath"] = filePath;
                            model["fileName"] = fileName;
                            eiInfo.set("fileId", fileId);
                            eiInfo.set("filePath", filePath);
                            IPLAT.confirm({
                                message: '<b>确定删除操作吗？</b></i>',
                                okFn: function (e) {
                                    EiCommunicator.send("CMZB01", "deleteContentBidFile", eiInfo, {
                                        onSuccess: function (ei) {
                                            resultBGrid.removeRows(model);
                                            IPLAT.NotificationUtil(ei.msg)
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
            },
            dataBound: function (e) {

            },
            loadComplete: function (e) {

            }
        }
    }
});

//创建kendo.data.Model
function createModel(id) {
    var gridRow = kendo.data.Model.define({
        id: "uploadId",
        fields: {
            "id": {type: "string"},
            "docId": {type: "string"},
            "filePath": {type: "string"},
            "fileName": {type: "string"},
            "fileSize": {type: "string"},
        }
    });
    var model = new gridRow({uploadId: id});
    return model;
}

function supplierWindows() {
    var url = IPLATUI.CONTEXT_PATH + "/web/CMZB00?methodName=initLoad";
    var popData = $("#supplierChoose");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url,
            });
        }
    });
    // 打开弹窗
    supplierChooseWindow.open().center();
}

function addRows(checkRows) {
    var List = resultAGrid.getDataItems();
    checkRows.reverse();
    for (var index in checkRows) {
        var model = checkRows[index];
        var isExist = false;
        for (var i in List) {
            var stage = List[i];
            if (stage.surpNum == model.surpNum) {
                isExist = true;
            }
        }
        if (!isExist) {
            resultAGrid.addRows(model)
        }
    }
}