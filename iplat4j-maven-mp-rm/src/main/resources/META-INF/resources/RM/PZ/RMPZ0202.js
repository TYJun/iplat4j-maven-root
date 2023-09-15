$(function () {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        resultId: "mat", showImg: true,
        toolbar: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [{
                name: "SURE", text: "确定", layout: "3",
                click: function () {
                    let checkRows = matGrid.getCheckedRows();
                    if (checkRows && checkRows.length > 0) {
                        window.parent.addRows(checkRows);
                        window.parent['popDataWindow'].close();
                    } else {
                        NotificationUtil("请选择物资", "warning");
                    }
                }
            }]
        },
    }).buildToolBarGrid();
    $.extend(true, IPLATUI.Config, { //全局配置
        EFGrid: {
            columns: [{
                field: "price",
                readonly: true,
                template: "<span>#=price#元</span>",
            }]

        }
    })
    /**回车键查询**/
    keydown("inqu", "QUERY");

    /**查询**/
    $("#QUERY").on("click", function (e) {
        matGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        matGrid.dataSource.page(1);
    });
});