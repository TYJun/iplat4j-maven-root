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
                //分配按钮
                $("#APPORTION").on("click", function(e) {
                    console.log("分配");
                    // 获取选中的行数据.
                    var checkRows = resultGrid.getCheckedRows();
                    // 判断是否有选中行.
                    if (checkRows.length > 1){
                        NotificationUtil("请勿勾选多条待分配信息", "warning");
                        return;
                    }else if (checkRows.length < 1){
                        NotificationUtil("请选择想要分配宿舍的人员信息", "warning");
                        return;
                    }
                    var manId = checkRows[0]["id"];
                    var url = IPLATUI.CONTEXT_PATH + "/web/DMFP0101?initLoad&manId=" + manId;
                    var popData = $("#popData");
                    popData.data("kendoWindow").setOptions({
                        open : function() {
                            popData.data("kendoWindow").refresh({
                                url : url
                            });
                        }
                    });
                    popDataWindow.open().center();
                });

                //批量分配按钮
                $("#BATCHAPPORTION").on("click", function(e) {
                    console.log("批量分配");
                    // 获取选中的行数据.
                    var checkRows = resultGrid.getCheckedRows();
                    // 判断是否有选中行.
                    var manIdList = "";
                    if (checkRows.length < 1) {
                        NotificationUtil("请选择一条/多条入住信息进行确认", "warning");
                        return;
                    }
                    for (var i = 0; i < checkRows.length; i++) {
                        // 当遍历最后一个值时，省略最后的"，"
                        if (i == checkRows.length - 1) {
                            manIdList += checkRows[i]["manId"];
                        } else {
                            manIdList += checkRows[i]["manId"] + ',';
                        }
                    }
                    var url = IPLATUI.CONTEXT_PATH + "/web/DMFP0201?initLoad&manIdList=" + manIdList;
                    var popData = $("#popData");
                    popData.data("kendoWindow").setOptions({
                        open : function() {
                            popData.data("kendoWindow").refresh({
                                url : url
                            });
                        }
                    });
                    popDataWindow.open().center();
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
                        NotificationUtil("请选择想要查看详情的入住信息", "warning")
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