var datagrid = null;
$(function() {
    console.log(__ei);
    console.log(__ei.role);
    if (__ei.role == 'DMSPR_XSSS' || __ei.role == 'DMZSR'){
        $("#hiddenDiv").hide();
    }
    //查询
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.query(1);
    });

    // 重置按钮.
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        resultGrid.dataSource.page(1);
    });

    //EFGrid单击事件，获取选中行数据（定义全部变量）
    IPLATUI.EFGrid = {
        "result" : {
            onCheckRow : function(e) {
                if (!e.fake) {
                    datagrid = null;
                    var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
                    datagrid = model;
                }
            },
            loadComplete: function (grid) {
                // 通过按钮
                $("#PASSAPPLY").on("click", function(e) {
                    console.log("通过");
                    // 获取选中的行数据.
                    var checkRows = resultGrid.getCheckedRows();
                    var manIdList = "";
                    var manNoList = "";
                    // 判断是否有选中行.
                    if (checkRows.length > 0) {
                        for (var i = 0; i < checkRows.length; i++) {
                            // 当遍历最后一个值时，省略最后的"，"
                            if (i == checkRows.length - 1) {
                                manIdList += checkRows[i]["manId"];
                                manNoList += checkRows[i]["manNo"];
                            } else {
                                manIdList += checkRows[i]["manId"] + ',';
                                manNoList += checkRows[i]["manNo"] + ',';
                            }
                        }
                        var statusCode = "01";
                        IPLAT.confirm({
                            message: '<b>将会通过所勾选的入住申请！</br><font color="red">是否确定？</font></b>',
                            okFn: function (e) {
                                //提交数据
                                var eiInfo = new EiInfo();
                                eiInfo.set("manIdList", manIdList);
                                eiInfo.set("manNoList", manNoList);
                                eiInfo.set("statusCode", statusCode);
                                EiCommunicator.send("DMSH01", "batchUpdateStatusCode", eiInfo, {
                                    onSuccess : function(ei) {
                                        if(ei.msg!=''){
                                            IPLAT.alert({
                                                message: ei.msg,
                                                okFn: function (e) {
                                                    setTimeout(function() {
                                                        window.parent.location.reload()
                                                    }, 600);
                                                },
                                                title: '注意'
                                            });
                                            IPLAT.NotificationUtil('操作成功');
                                        }else{
                                            IPLAT.NotificationUtil('操作成功');
                                            setTimeout(function() {
                                                window.parent.location.reload()
                                            }, 600);
                                        }
                                    }
                                });
                            },
                            title: '提醒'
                        });
                    } else {
                        NotificationUtil("请选择想要通过的宿舍入住申请", "warning")
                    }
                });

                // 拒绝按钮
                $("#REFUSEAPPLY").on("click", function(e) {
                    console.log("拒绝");
                    // 获取选中的行数据.
                    var checkRows = resultGrid.getCheckedRows();
                    var manIdList = "";
                    var manNoList = "";
                    // 判断是否有选中行.
                    if (checkRows.length > 0) {
                        for (var i = 0; i < checkRows.length; i++) {
                            // 当遍历最后一个值时，省略最后的"，"
                            if (i == checkRows.length - 1) {
                                manIdList += checkRows[i]["manId"];
                                manNoList += checkRows[i]["manNo"];
                            } else {
                                manIdList += checkRows[i]["manId"] + ',';
                                manNoList += checkRows[i]["manNo"] + ',';
                            }
                        }
                        var statusCode = "99";
                        IPLAT.confirm({
                            message: '<b>将会拒绝所勾选的入住申请！</br><font color="red">是否确定？</font></b>',
                            okFn: function (e) {
                                //提交数据
                                var eiInfo = new EiInfo();
                                eiInfo.set("manIdList", manIdList);
                                eiInfo.set("statusCode", statusCode);
                                eiInfo.set("manNoList", manNoList);
                                EiCommunicator.send("DMSH01", "batchUpdateStatusCode", eiInfo, {
                                    onSuccess : function(ei) {
                                        IPLAT.NotificationUtil(ei.getMsg());
                                        setTimeout(function() {
                                            window.parent.location.reload()
                                        }, 600);
                                    }
                                });
                            },
                            title: '提醒'
                        });
                    } else {
                        NotificationUtil("请选择想要拒绝的宿舍入住申请", "warning")
                    }
                });

                // 查看按钮.
                $("#SHOWINFO").on("click", function(e) {
                    console.log("查看按钮");
                    var eiInfo = new EiInfo();
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        var manId = checkRows[0]["id"];
                        popDatashow(manId);
                    } else {
                        NotificationUtil("请选择想要查看详情的入住申请", "warning")
                    }
                });
            }
        }
    }
});

// 查看详情
function popDatashow(manId) {
    var url = IPLATUI.CONTEXT_PATH + "/web/DMRZ0103?initLoad&manId=" + manId;
    var popData = $("#popDatashow");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url,
            });
        }
    });
    // 打开工作流节点人员选择弹窗
    popDatashowWindow.open().center();
}