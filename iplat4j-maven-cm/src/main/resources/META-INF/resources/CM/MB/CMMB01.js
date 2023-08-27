$(function () {
    // 查询
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#REQUERY").on("click", function (e) {
        $("#fileName").val("");
        resultGrid.dataSource.page(1);
    });

    IPLATUI.EFUpload = {
        "templateFile": {
            loadComplete: function (e) {
                // 清空所以历史上传文件
                var uploader = e.sender.uploader;
                uploader.clearAllFiles();
            },
            localization: {
                // 不支持格式提示信息
                invalidFileExtension: "文件格式不支持, 上传失败",
                invalidMaxFileSize: "上传模板超过10M, 请重新选择",
            },
            validation: {
                // 文件格式过滤
                allowedExtensions: [".doc", ".docx"],
                maxFileSize: 10485760,
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
                fileChooseWindow.close();
                var eiInfo = new EiInfo();
                eiInfo.set("uploadId",model.uploadId)
                eiInfo.set("fileName",model.fileName);
                eiInfo.set("filePath",model.filePath);
                eiInfo.set("fileSize",model.fileSize);
                EiCommunicator.send("CMMB01", "saveContentTemplateFile", eiInfo, {
                    onSuccess : function(ei) {
                        IPLAT.NotificationUtil(ei.msg)
                        resultGrid.dataSource.page(1);
                    }
                })
            }
        }
    }

    IPLATUI.EFGrid = {
        "result": {
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
                        var checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            for (var index in checkRows) {
                                var docId = checkRows[index].fileId;
                                console.log(docId)
                                var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
                                window.location.href = href;
                            }
                        } else {
                            IPLAT.NotificationUtil("请选择需要下载的文件","error")
                        }
                    }
                }, {
                    name: "start", text: "启用", layout: "3",
                    click: function () {
                        var checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            if (checkRows[0].fileStatus == "停用"){
                                var eiInfo = new EiInfo();
                                var fileId = checkRows[0].id;
                                eiInfo.set("fileId",fileId);
                                IPLAT.confirm({
                                    message: '<b>确定启用操作吗？</b></i>',
                                    okFn: function (e) {
                                        EiCommunicator.send("CMMB01", "startTemplateFile", eiInfo, {
                                            onSuccess: function (ei) {
                                                IPLAT.NotificationUtil(ei.msg)
                                                resultGrid.dataSource.page(1);
                                            }
                                        })
                                    },
                                    cancelFn: function (e) {}
                                })
                            }else{
                                IPLAT.NotificationUtil("请不要重复启用","error")
                            }
                        } else {
                            IPLAT.NotificationUtil("请选择需要启用模板","error")
                        }
                    }
                }, {
                    name: "stop", text: "停用", layout: "3",
                    click: function () {
                        var checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            if (checkRows[0].fileStatus == "启用"){
                                var eiInfo = new EiInfo();
                                var fileId = checkRows[0].id;
                                eiInfo.set("fileId",fileId);
                                IPLAT.confirm({
                                    message: '<b>确定停用操作吗？</b></i>',
                                    okFn: function (e) {
                                        EiCommunicator.send("CMMB01", "stopTemplateFile", eiInfo, {
                                            onSuccess: function (ei) {
                                                IPLAT.NotificationUtil(ei.msg)
                                                resultGrid.dataSource.page(1);
                                            }
                                        })
                                    },
                                    cancelFn: function (e) {}
                                })
                            }else{
                                IPLAT.NotificationUtil("请不要重复停用","error")
                            }
                        } else {
                            IPLAT.NotificationUtil("请选择停用模板","error")
                        }
                    }
                }, {
                    name: "deleter", text: "删除", layout: "3",
                    click: function () {
                        var checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            if (checkRows[0].fileStatus == "停用"){
                                var eiInfo = new EiInfo();
                                var fileId = checkRows[0].id;
                                var filePath = checkRows[0].filePath;
                                eiInfo.set("fileId",fileId);
                                eiInfo.set("filePath",filePath);
                                IPLAT.confirm({
                                    message: '<b>确定删除操作吗？</b></i>',
                                    okFn: function (e) {
                                        EiCommunicator.send("CMMB01", "deleterTemplateFile", eiInfo, {
                                            onSuccess: function (ei) {
                                                IPLAT.NotificationUtil(ei.msg)
                                                resultGrid.dataSource.page(1);
                                            }
                                        })
                                    },
                                    cancelFn: function (e) {}
                                })
                            }else {
                                IPLAT.NotificationUtil("请先停用模板","error")
                            }
                        } else {
                            IPLAT.NotificationUtil("请选择需要删除的信息","error")
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