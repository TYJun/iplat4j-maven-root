/* 获取选中的全局变量 */
var datagrid = null;
$(function () {
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    })

    $("#RESET").on("click",function(e){
        $("#inqu_status-0-nterviewerName").val("")
        $("#inqu_status-0-visitingDeptName").val("")
        $("#inqu_status-0-vistingBeginDateS").val("")
        $("#inqu_status-0-vistingBeginDateE").val("")
        $("#inqu_status-0-auditorStep").val("")
        IPLAT.EFSelect.text($("#inqu_status-0-auditorStep"), "请选择");
        resultGrid.dataSource.page(1);
    });
});

IPLATUI.EFGrid = {
    "result": {
        exportGrid : false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [{
                name: "goods", text: "查看", layout: "3",
                click: function () {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length < 1) {
                        IPLAT.NotificationUtil("请选择一条数据", "error")
                    }
                    var id = checkRows[0].batNo;
                    var type = checkRows[0].creatorIdentity;
                    var url = IPLATUI.CONTEXT_PATH + "/web/VIZH0101?initLoad" + "&id=" + id + "&type=" + type;
                    var popData = $("#popData");
                    popData.data("kendoWindow").setOptions({
                        open: function () {
                            popData.data("kendoWindow").refresh({
                                url: url,
                            });
                        }
                    });
                    // 打开工作流节点人员选择弹窗
                    popDataWindow.open().center();
                }
            }]
        }
    },
    dataBound: function (e) {

    },
    loadComplete: function (e) {

    },
}