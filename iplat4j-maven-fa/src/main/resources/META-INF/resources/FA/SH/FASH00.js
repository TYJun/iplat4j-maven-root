$(function () {
    $("#QUERY").on("click", function (e) {
        resultAGrid.dataSource.page(1);
    });

    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultAGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "resultA": {
            pageable: {
                pageSize: 15
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "enter",
                        text: "增加上会资产",
                        layout: "3",
                        click: function () {
                            var checkRows = resultAGrid.getCheckedRows()
                            if (checkRows.length > 0) {
                                var eiInfo = new EiInfo();
                                eiInfo.set("discussId",__ei.discussId);
                                eiInfo.set("faInfoList", checkRows);
                                eiInfo.set("lockFlag", 1);
                                EiCommunicator.send("FASH00", "updateFaInfoLock", eiInfo, {
                                    onSuccess : function(ei) {
                                        addRowsCheck(checkRows);
                                        window.parent['popDataWindow'].close();
                                    }
                                });
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    },
                ]
            },
            loadComplete: function (grid) {

            }
        },
    }
});

function addRowsCheck(rows) {
    var flag = false;
    var checkRows =  window.parent.resultGrid.getDataItems();
    if (checkRows.length > 0){
        for (var j = 0; j < rows.length; j++) {
            for (var i = 0; i < checkRows.length; i++) {
                if (checkRows[i].goodsNum != rows[j].goodsNum){
                    flag = true
                } else {
                    flag = false
                    break;
                }
            }
            if (flag){
                window.parent.resultGrid.addRows(rows[j])
            }
        }
    } else {
        window.parent.resultGrid.addRows(rows)
    }
    window.parent.resultGrid.unCheckAllRows();
}