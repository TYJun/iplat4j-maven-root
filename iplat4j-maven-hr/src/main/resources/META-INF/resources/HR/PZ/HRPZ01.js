$(function (){
    // 查询
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#RESET").on("click", function (e) {
        $("#inqu_status-0-surpName").val("");
        $("#inqu_status-0-serviceDeptName").val("");
        $("#inqu_status-0-position").val("");
        resultGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "result": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "new", text: "新增", layout: "3",
                    click: function () {
                        popDataWindow.setOptions({"title":"新增人员信息配置"});
                        popData("","add");
                    }
                }, {
                    name: "deleter", text: "删除", layout: "3",
                    click: function () {
                        var checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            var id = checkRows[0].id;
                            var eiInfo = new EiInfo();
                            eiInfo.set("id",id);
                            IPLAT.confirm({
                                message: '<b>确定删除操作吗？</b></i>',
                                okFn: function (e) {
                                    EiCommunicator.send("HRPZ01", "delete", eiInfo, {
                                        onSuccess: function (ei) {
                                            IPLAT.NotificationUtil(ei.msg)
                                            resultGrid.dataSource.page(1);
                                        }
                                    })
                                },
                                cancelFn: function (e) {}
                            })
                        } else {
                            IPLAT.NotificationUtil("请选择一记录","error")
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
})

function popData(id,type){
    var url = IPLATUI.CONTEXT_PATH + "/web/HRPZ0101";
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url,
            });
        }
    });
    // 打开工作流节点人员选择弹窗
    popDataWindow.open().center();
}