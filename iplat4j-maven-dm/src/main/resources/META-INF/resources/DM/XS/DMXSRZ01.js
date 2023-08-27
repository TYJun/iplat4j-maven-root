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
                //新增按钮
                $("#APPLYRZ").on("click", function(e) {
                    console.log("新增");
                    var url = IPLATUI.CONTEXT_PATH + "/web/DMXSRZ0101";
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

                //编辑按钮
                $("#EDITINFO").on("click", function(e) {
                    console.log("编辑");
                    // 获取选中的行数据.
                    var checkRows = resultGrid.getCheckedRows();
                    // 判断是否有选中行.
                    if (checkRows.length > 0) {
                        var manId = checkRows[0]["id"];
                        console.log(manId);
                        var url = IPLATUI.CONTEXT_PATH + "/web/DMXSRZ0102?initLoad&manId=" + manId;
                        var popData = $("#popDataModify");
                        popData.data("kendoWindow").setOptions({
                            open : function() {
                                popData.data("kendoWindow").refresh({
                                    url : url
                                });
                            }
                        });
                        popDataModifyWindow.open().center();
                    } else {
                        NotificationUtil("请选择想要编辑的信息", "warning")
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
                        NotificationUtil("请选择想要查看详情的入住信息", "warning")
                    }
                });
            }
        }
    }
});


// 查看详情工单
function popDatashow(manId) {
    var url = IPLATUI.CONTEXT_PATH + "/web/DMXSRZ0103?initLoad&manId=" + manId;
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