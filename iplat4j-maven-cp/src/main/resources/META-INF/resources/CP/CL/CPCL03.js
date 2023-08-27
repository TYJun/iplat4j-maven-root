$(function () {
    // 查询
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#RESET").on("click", function (e) {
        $("#inqu_status-0-billNo").val("")
        $("#inqu_status-0-complaintName").val("")
        $("#inqu_status-0-complaintDept").val("")
        $("#inqu_status-0-complaintDateS").val("")
        $("#inqu_status-0-complaintDateE").val("")
        $("#inqu_status-0-complaintContent").val("")
        resultGrid.dataSource.page(1);
    });
})

IPLATUI.EFGrid = {
    "result": {
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [
                {
                name: "goodsCon", text: "处理登记", layout: "3",
                click: function () {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length < 1) {
                        IPLAT.NotificationUtil("请选择一条数据", "error")
                        return
                    } else {
                        var id = checkRows[0].id;
                        var billNo = checkRows[0].billNo;
                        var url = IPLATUI.CONTEXT_PATH + "/web/CPCL0101?initLoad" + "&id=" + id +"&billNo=" +billNo + "&type=" + "look";
                        var popData = $("#popData2");
                        popData.data("kendoWindow").setOptions({
                            open: function () {
                                popData.data("kendoWindow").refresh({
                                    url: url,
                                });
                            }
                        });
                        // 打开工作流节点人员选择弹窗
                        popData2Window.open().center();
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