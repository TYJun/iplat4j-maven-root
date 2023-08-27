$(function () {
    //查看方法
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    })

    //重置按钮
    $("#REQUERY").on("click", function (e) {
        IPLAT.EFInput.value($("#inqu_status-0-standardName"), "");
        IPLAT.EFSelect.value($("#inqu_status-0-sqType"), "");
        IPLAT.EFSelect.text($("#inqu_status-0-sqType"), "");
        resultGrid.dataSource.page(1);
    });
});
IPLATUI.EFGrid = {
    "result": {
        toolbarConfig: {
            hidden: false,
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [{
                name: "new", text: "问卷录入", layout: "3",
                click: function () {
                    popDataWindow.setOptions({"title": "问卷录入"});
                    popData("", "add");
                }
            }, {
                name: "edit", text: "问卷编辑", layout: "3",
                click: function () {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        var standardId = checkRows[0].standardId;
                        popDataWindow.setOptions({"title": "问卷编辑"});
                        popData(standardId, "edit");
                    } else {
                        NotificationUtil("请选择要编辑的行", "error");
                    }
                }
            }, {
                name: "deleter", text: "问卷删除", layout: "3",
                click: function () {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        var eiInfo = new EiInfo();
                        var standardId = checkRows[0].standardId;
                        eiInfo.set("standardId", standardId);
                        IPLAT.confirm({
                            message: '<b style="color: red">确定执行删除操作吗？该操作不可恢复</b></i>',
                            okFn: function (e) {
                                EiCommunicator.send("SQBZ01", "deleteAll", eiInfo, {
                                    onSuccess: function (ei) {
                                        resultGrid.dataSource.page(1);
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
        loadComplete: function (grid) {
            //编辑主题
            $("#ADD2").on("click", function (e) {
                var checkRows = resultGrid.getCheckedRows();
                if (checkRows.length < 1) {
                    NotificationUtil("请选择要确认的行", "error");
                    return;
                }
                var id = checkRows[0].id;
                var url = IPLATUI.CONTEXT_PATH + "/web/SQBZ04?id=" + id;
                var popData = $("#popDataTwo");
                popData.data("kendoWindow").setOptions({
                    open: function () {
                        popData.data("kendoWindow").refresh({
                            url: url
                        });
                    }
                });
                popDataTwoWindow.open().center();
            });
            //删除主题
            $("#DELETE1").on("click", function (e) {
                var checkRows = resultGrid.getCheckedRows();
                if (checkRows.length < 1) {
                    NotificationUtil("请选择要删除的行", "error");
                    return;
                }
                var id = checkRows[0].id;
                var info = new EiInfo();
                info.set("id", id)
                EiCommunicator.send("SQBZ01", "delete", info, {
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
        },
    }
}


// 问卷录入/问卷编辑
function popData(id, type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/SQBZ0101?initLoad&id=" + id + "&type=" + type;
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