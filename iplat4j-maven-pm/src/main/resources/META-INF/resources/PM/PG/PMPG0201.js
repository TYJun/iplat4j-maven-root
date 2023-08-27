$(function () {
    var each = $.each;
    IPLATUI.EFGrid = {
        "result": {
            pageable: false,
            exportGrid: false,
            dataBound: function (e) {

            },
            loadComplete: function (e) {
                // 新增
                $("#ADDPG").on("click", function (e) {
                    var validator = IPLAT.Validator({
                        id: "result"
                    });
                    if (validator.validate()) {
                        var resultList = resultGrid.getDataItems();
                        // console.log(resultList);
                        if (resultList.length == 0) {
                            IPLAT.NotificationUtil("请选择项目阶段信息", "error");
                            return;
                        }
                        var eiInfo = new EiInfo();
                        var typeName = IPLAT.EFInput.value($("#typeName"));
                        if (typeName == "") {
                            IPLAT.NotificationUtil("请填写项目类型名称", "error");
                            return;
                        }
                        var typeRemark = IPLAT.EFInput.value($("#typeRemark"));
                        eiInfo.set("typeName", typeName);
                        eiInfo.set("typeRemark", typeRemark);
                        eiInfo.set("resultList", resultList);
                        EiCommunicator.send("PMPG02", "insertPmTypeMsg", eiInfo, {
                            onSuccess: function (ei) {
                                NotificationUtil(ei.getMsg(), "success");
                                window.parent.resultGrid.dataSource.page(1);
                                window.parent['typeNewWindow'].close();
                            }
                        });
                    }
                });

                // 选择阶段
                $("#SELECTPG").on("click", function (e) {
                    var url = IPLATUI.CONTEXT_PATH + "/web/PMPG000101?initLoad";
                    var popData = $("#stageSelect");
                    popData.data("kendoWindow").setOptions({
                        open: function () {
                            popData.data("kendoWindow").refresh({
                                url: url,
                            });
                        }
                    });
                    // 打开弹窗
                    stageSelectWindow.open().center();
                });

                // 上移
                $("#UPPG").on("click", function (e) {
                    if (resultGrid.getCheckedRows().length === 0) {
                        WindowUtil({
                            'title': "向上移动列",
                            "content": "<div class='kendo-del-message'>没有选中的行</div>"
                        });
                    } else {
                        var index = "";
                        var newIndex = "";
                        var lockedCount = _.filter(resultGrid.getDataItems(), {column_locked: "1"}).length;
                        each(resultGrid.getCheckedRows(), function (i, dataItem) {
                            index = resultGrid.dataSource.indexOf(dataItem);
                            newIndex = Math.max(lockedCount, index - 1);
                            if (newIndex != index) {
                                var data = resultGrid.dataSource.data();
                                var upRow = data[newIndex];
                                upRow.set("orderNo", newIndex + 1);
                                data[newIndex + 1] = upRow;
                                data[newIndex] = dataItem;
                                dataItem.set("orderNo", newIndex);
                            } else {
                                WindowUtil({
                                    'title': "向上移动列",
                                    "content": "<div class='kendo-del-message'>已经移动到了顶部</div>"
                                });
                                return false;
                            }
                        });
                    }
                });

                // 下移
                $("#DOWNPG").on("click", function (e) {
                    if (resultGrid.getCheckedRows().length === 0) {
                        WindowUtil({
                            'title': "向下移动列",
                            "content": "<div class='kendo-del-message'>没有选中的行</div>"
                        });
                    } else {
                        var index = "";
                        var newIndex = "";
                        var lockedCount = _.filter(resultGrid.getDataItems(), {column_locked: "1"}).length;
                        each(resultGrid.getCheckedRows().reverse(), function (i, dataItem) {
                            index = resultGrid.dataSource.indexOf(dataItem);
                            newIndex = Math.min(resultGrid.getDataItems().length - 1, index + 1);
                            ;
                            if (newIndex != index) {
                                var data = resultGrid.dataSource.data();
                                var downRow = data[newIndex];
                                dataItem.set("orderNo", newIndex + 1);
                                data[newIndex] = dataItem;
                                data[newIndex - 1] = downRow;
                                downRow.set("orderNo", newIndex);
                            } else {
                                WindowUtil({
                                    'title': "向下移动列",
                                    "content": "<div class='kendo-del-message'>已经移动到了底部</div>"
                                });
                                return false;
                            }
                        });
                    }
                });

                // 删除
                $("#DELETEPG").on("click", function (e) {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        resultGrid.removeRows(checkRows);
                    } else {
                        IPLAT.NotificationUtil("请选择需要删除的信息")
                    }
                });

            }
        }
    }
});

function addRows(checkRows) {
    var stageList = resultGrid.getDataItems();
    for (var index in checkRows) {
        var model = checkRows[index];
        var isExist = false;
        for (var i in stageList) {
            var stage = stageList[i];
            if (stage.stageCode == model.stageCode) {
                isExist = true;
            }
        }
        if (!isExist) {
            resultGrid.addRows(model)
        }
    }
}