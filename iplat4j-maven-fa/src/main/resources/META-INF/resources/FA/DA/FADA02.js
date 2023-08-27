$(function () {
    $("#queryFAInfoType").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "result": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "affirm",
                        text: "确认",
                        layout: "3",
                        click: function () {
                            window.parent.addSoleRows(checkRows());
                            window.parent['popDataWindow'].close();
                        }
                    },
                ]
            },
            loadComplete: function (grid) {

            }
        }
    }

    function checkRows() {
        const checkRows = resultGrid.getCheckedRows();
        if (checkRows.length < 1) {
            NotificationUtil("请先选择一条记录", "warning");
            return false;
        } else {
            return checkRows;
        }
    }
});