$(function () {

});

IPLATUI.EFGrid = {
    "resultFixedAssests":{
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: []
        },
        loadComplete:function (grid) {
            var checkRows = window.parent.resultAGrid.getCheckedRows()
            if (checkRows.length > 0) {
                grid.addRows(checkRows);
                grid.unCheckAllRows();
            }

            $("#close").on("click", function (e) {
                closeParentWindow()
            });
        }
    }
}


function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultAGrid"].dataSource.page(1);
    window.parent["resultBGrid"].dataSource.page(1);
}