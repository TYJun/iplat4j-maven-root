$(function (){
    // 查询
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#RESET").on("click", function (e) {
        $("#inqu_status-0-realName").val("");
        $("#inqu_status-0-jobCode").val("");
        $("#inqu_status-0-company").val("");
        $("#inqu_status-0-workNo").val("");
        IPLAT.EFSelect.value($("#inqu_status-0-statusCode"), "");
        $("#inqu_status-0-deptNum").val("");
        resultGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "result": {
            loadComplete: function (grid) {
                // 新增
                $("#SEE").on("click", function (e) {
                    var  rows=resultGrid.getCheckedRows();
                    var id =rows[0].id;
                    var url = IPLATUI.CONTEXT_PATH + "/web/HRXX0201?id=" + id;
                    var popData = $("#popData");
                    popData.data("kendoWindow").setOptions({
                        open: function () {
                            popData.data("kendoWindow").refresh({
                                url: url
                            });
                        }
                    });
                    popDataWindow.open().center();
                });
            }
        },

    }
})
