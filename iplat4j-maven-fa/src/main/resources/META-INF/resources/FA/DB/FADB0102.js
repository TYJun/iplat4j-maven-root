$(function () {
    IPLATUI.EFSelect = {
        "info-0-turnType": {
            // 点击下拉选项时触发
            select:function(e) { //获取勾选值
                let dataItem = e.dataItem;
                if (dataItem['valueField'] == "dept"){
                    $("#backWarehouse").show();
                } else {
                    $("#backWarehouse").hide();
                }
            }
        },
    };

    IPLATUI.EFPopupInput = {
        "info-0-confirmLocationNum": {
            query: function (e) {
                var queryInfo = new EiInfo();
                queryInfo.set("confirmDeptNum", $("#info-0-turnDeptNum").val());
                return queryInfo;
            },
            backFill: function (e) {
                IPLAT.EFInput.value($("#info-0-confirmBuild"), e.model['building']);
                IPLAT.EFInput.value($("#info-0-confirmFloor"), e.model['floor']);
            },
        },
    }

    IPLATUI.EFAutoComplete = {
        "info-0-deptName": {
            change: function (e) {
                var context = $("#info-0-deptName").data("kendoAutoComplete");
                if (context.listView._dataItems.length > 0){
                    var confirmDeptNum = context.listView._dataItems[0].deptNum;
                    var confirmDeptName = context.listView._dataItems[0].deptName;
                    if (confirmDeptNum == ""){
                        IPLAT.EFInput.value($("#info-0-turnDeptNum"), "");
                        IPLAT.EFInput.value($("#info-0-turnDeptName"), "");
                        IPLAT.EFInput.value($("#info-0-confirmLocationNum"), "");
                        IPLAT.EFInput.value($("#info-0-confirmLocationNum_textField"), "");
                        IPLAT.EFInput.value($("#info-0-confirmBuild"), "");
                        IPLAT.EFInput.value($("#info-0-confirmFloor"), "");
                        NotificationUtil("该科室不存在编码请重新选择", "warning")
                    } else {
                        IPLAT.EFInput.value($("#info-0-deptNum"), confirmDeptNum);
                        IPLAT.EFInput.value($("#info-0-turnDeptNum"), confirmDeptNum);
                        IPLAT.EFInput.value($("#info-0-turnDeptName"), confirmDeptName);
                    }
                } else {
                    IPLAT.EFInput.value($("#info-0-turnDeptNum"), "");
                    IPLAT.EFInput.value($("#info-0-confirmLocationNum"), "");
                    IPLAT.EFInput.value($("#info-0-confirmLocationNum_textField"), "");
                    IPLAT.EFInput.value($("#info-0-confirmBuild"), "");
                    IPLAT.EFInput.value($("#info-0-confirmFloor"), "");
                    NotificationUtil("该科室不存在请重新选择", "warning")
                }
                // console.log("编码：" + confirmDeptNum + "--" + "名称：" + confirmDeptName)
            }
        }
    }

    IPLATUI.EFGrid = {
        "result": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: []
            },
            loadComplete: function (grid) {

            }
        },
        "resultFixedAssests": {
            pageable: false,
            exportGrid: false,
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: []
            },
            onCheckRow: function (e) {
                var grid = e.sender,
                    model = e.model,
                    $tr = e.tr,
                    row = e.row;
                $tr.css({
                    background: "white",
                    color: "#000000A6"
                });
                grid.unCheckAllRows();
            },
            loadComplete: function (grid) {
                var checkRows = window.parent.resultAGrid.getCheckedRows()
                if (checkRows.length > 0) {
                    grid.addRows(checkRows);
                    grid.unCheckAllRows();
                }

                $("#save").on("click", function (e) {
                    // 出现分支调拨回科室和调拨回仓库
                    // 调拨回科室
                    if ($("#info-0-turnType").val() == "dept"){
                        if ($("#info-0-turnDeptNum").val() == "") {
                            NotificationUtil("请选择接收科室", "warning");
                            return;
                        }
                        if (checkRows.length > 0) {
                            var newFaInfo = [],useFaInfo = [];
                            for (let i = 0; i < checkRows.length; i++) {
                                if (checkRows[i].statusCode == "待用") {
                                    newFaInfo.push(checkRows[i])
                                } else if (checkRows[i].statusCode == "在用") {
                                    useFaInfo.push(checkRows[i])
                                }
                            }
                            var eiInfo = new EiInfo();
                            eiInfo.setByNode("info");
                            eiInfo.set("newFaInfo", newFaInfo)
                            eiInfo.set("useFaInfo", useFaInfo)
                            EiCommunicator.send("FADB0102", "assetOutStorage", eiInfo, {
                                onSuccess: function (ei) {
                                    if (ei.status == -1) {
                                        IPLAT.alert(
                                            ei.msg,
                                            function (e) {
                                            },
                                            "提示",
                                            300
                                        );
                                    } else {
                                        closeParentWindow()
                                    }
                                }
                            });
                        } else {
                            NotificationUtil("请先选择一条记录", "warning");
                            return;
                        }
                    } // 调拨回仓库
                    else if ($("#info-0-turnType").val() == "warehouse"){
                        if (checkRows.length > 0) {
                            var backFaInfo = [];
                            // 出库单号为空不能进行资产退库
                            for (let i = 0; i < checkRows.length; i++) {
                                if (checkRows[i].outBillNo == "") {
                                    NotificationUtil("请选择存在出库单的资产卡片进行退库", "warning");
                                    return;
                                } else {
                                    backFaInfo.push(checkRows[i])
                                }
                            }
                            var eiInfo = new EiInfo();
                            eiInfo.setByNode("info");
                            eiInfo.set("backFaInfo", backFaInfo)
                            EiCommunicator.send("FADB0102", "assetOutWarehouse", eiInfo, {
                                onSuccess: function (ei) {
                                    if (ei.status == -1) {
                                        IPLAT.alert(
                                            ei.msg,
                                            function (e) {
                                            },
                                            "提示",
                                            300
                                        );
                                    } else {
                                        closeParentWindow()
                                    }
                                }
                            });
                        } else {
                            NotificationUtil("请先选择一条记录", "warning");
                        }
                    }
                });

                $("#close").on("click", function (e) {
                    closeParentWindow()
                });
            }
        }
    }
});

function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultAGrid"].dataSource.page(1);
    // window.parent["resultBGrid"].dataSource.page(1);
    window.parent["resultCGrid"].dataSource.page(1);
    window.parent["resultDGrid"].dataSource.page(1);
    window.parent["resultEGrid"].dataSource.page(1);
}