$(function () {
    //重置按钮
    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultGrid.dataSource.page(1);
    });
    //查询方法
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });
});
IPLATUI.EFGrid = {
    "result": {
        toolbarConfig:{
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false,cancel: false,save: false,'delete': false,
            buttons:[{
                // 周期计划
                name: "cyclePlan",text: "终止周期计划",layout:"3",
                click: function () {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length < 1) {
                        NotificationUtil("请选择要终止周期计划的问卷", "error");
                        return;
                    }
                    if (checkRows[0].isCycle=="否"){
                        NotificationUtil("请选择未终止周期生成的问卷", "error");
                        return;
                    }
                    IPLAT.confirm({
                        message: '<b style="color: red">确定执行终止周期计划操作吗？该操作不可恢复</b></i>',
                        okFn: function (e) {
                            var manageId = checkRows[0].manageId;
                            var batchNo = checkRows[0].batchNo;
                            var eiInfo = new EiInfo();
                            eiInfo.set("manageId", manageId);
                            eiInfo.set("batchNo", batchNo);
                            EiCommunicator.send("SQWJ01", "stopCycle", eiInfo, {
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
                                }
                            })
                        },
                        cancelFn: function (e) {}
                    })
                }
            }, {
                // 关联上级问卷
                name: "link",text: "关联上级问卷",layout:"3",
                click: function () {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length < 1) {
                        NotificationUtil("请选择要关联的问卷", "error");
                        return;
                    }
                    var manageId = "";
                    for (var i = 0; i < checkRows.length; i++) {
                        manageId += checkRows[i].manageId + ",";
                    }
                    popData2(manageId, "add");
                }
            }]
        },
        loadComplete: function (grid) {
            //新增按钮
            $("#ADD").on("click", function (e) {
                popData("", "add");
            });

            //编辑问卷
            $("#UPDT").on("click", function (e) {
                var checkRows = resultGrid.getCheckedRows();
                if (checkRows.length != 1) {
                    NotificationUtil("请选择一条要编辑的问卷", "error");
                    return;
                }

                var statusCode = checkRows[0].statusCode;
                if (statusCode != "新增") {
                    NotificationUtil("只能编辑新增问卷，已经开始调查，或调查结束的问卷不能编辑", "error");
                    return;
                }

                var manageId = checkRows[0].manageId;
                popData(manageId, "edit");
            });

            //删除按钮
            $("#DELET").on("click", function (e) {
                var checkRows = resultGrid.getCheckedRows();
                if (checkRows.length != 1) {
                    NotificationUtil("请选择要一条要删除的问卷", "error");
                    return;
                }
                var statusCode = checkRows[0].statusCode;
                if (statusCode != "新增") {
                    NotificationUtil("只能删除新增问卷，已经开始调查，或调查结束的问卷不能删除", "error");
                    return;
                }
                IPLAT.confirm({
                    message: '<b style="color: red">确定执行删除操作吗？该操作不可恢复</b></i>',
                    okFn: function (e) {
                        var manageId = checkRows[0].manageId;
                        var batchNo = checkRows[0].batchNo;
                        var eiInfo = new EiInfo();
                        eiInfo.set("manageId", manageId);
                        eiInfo.set("batchNo", batchNo);
                        EiCommunicator.send("SQWJ01", "delete", eiInfo, {
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
                            }
                        })
                    },
                    cancelFn: function (e) {}
                })
            })

            //开始按钮
            $("#UPD1").on("click", function (e) {
                var checkRows = resultGrid.getCheckedRows();
                if (checkRows.length != 1) {
                    NotificationUtil("请选择一条要开始的问卷", "error");
                    return;
                }else if (checkRows[0].parentsName ) {
                    NotificationUtil("请选择上级问卷开始调查", "error");
                    return;
                }


                var beginDate = checkRows[0].beginDate;
                var endDate = checkRows[0].endDate;
                var date = new Date();
                var seperator1 = "-";
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var strDate = date.getDate();
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }
                if (strDate >= 0 && strDate <= 9) {
                    strDate = "0" + strDate;
                }
                var currentdate = year + seperator1 + month + seperator1 + strDate;
                if (endDate < currentdate) {
                    NotificationUtil("该问卷已到结束时间", "error");
                    return;
                }
                var manageId = checkRows[0].manageId;
                var eiInfo = new EiInfo();
                eiInfo.set("manageId", manageId);
                eiInfo.set("UPD", "UPD1");
                EiCommunicator.send("SQWJ01", "update", eiInfo, {
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
                    }
                })
            });

            //终止按钮
            $("#UPD").on("click", function (e) {
                var checkRows = resultGrid.getCheckedRows();
                if (checkRows.length != 1) {
                    NotificationUtil("请选择一条要终止的问卷", "error");
                    return;
                }
                var endDate = checkRows[0].endDate;
                var date = new Date();
                var seperator1 = "-";
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var strDate = date.getDate();
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }
                if (strDate >= 0 && strDate <= 9) {
                    strDate = "0" + strDate;
                }
                var currentdate = year + seperator1 + month + seperator1 + strDate;
                /*if(endDate > currentdate){
                    NotificationUtil("还未到问卷结束时间", "error");
                    return;
                }*/
                var manageId = checkRows[0].manageId;
                var eiInfo = new EiInfo();
                eiInfo.set("manageId", manageId);
                eiInfo.set("UPD", "UPD");
                EiCommunicator.send("SQWJ01", "update", eiInfo, {
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
                    }
                })
            });
        }
    }
}

function popData(manageId, type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/SQWJ02?initLoad&manageId=" + manageId + "&type=" + type;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url,
                //resultGrid:datagrid
            });
        }
    });
    // 打开生成弹窗
    popDataWindow.open().center();
}

function popData2(manageId, type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/SQWJ03?initLoad&manageId=" + manageId + "&type=" + type;
    var popData2 = $("#popData2");
    popData2.data("kendoWindow").setOptions({
        open: function () {
            popData2.data("kendoWindow").refresh({
                url: url,
                //resultGrid:datagrid
            });
        }
    });
    // 打开生成弹窗
    popData2Window.open().center();
}