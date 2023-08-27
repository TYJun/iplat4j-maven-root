$(function () {
    // 查询
    $("#QUERY").on("click", function (e) {
        supplierGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#REQUERY").on("click", function (e) {
        $("#inqu_status-0-supplierName").val("");
        supplierGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "supplier": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "confirm", text: "确认", layout: "3",
                    click: function () {
                        var checkRows = supplierGrid.getCheckedRows();
                        window.parent.addRows(checkRows);
                        window.parent.supplierChooseWindow.close();
                    }
                }]
            }
        }
    }
});
