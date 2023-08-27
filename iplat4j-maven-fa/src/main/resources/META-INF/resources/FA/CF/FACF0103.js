$(function () {

});

IPLATUI.EFGrid = {
    "resultSplitByValue": {
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
        },
        loadComplete: function (e) {

        }
    }
}
