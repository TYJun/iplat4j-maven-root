$(function (){
    $("#SUBMIT").on("click", function (e) {
        var discussName = $("#info-0-discussName").val();
        var checkRows = resultGrid.getDataItems();
        if (discussName == ""){
            NotificationUtil("会议名称不能为空", "warning")
            return;
        }
        if (!checkRows.length > 0){
            NotificationUtil("上会资产不能为空", "warning")
            return;
        }
        // 获取参数
        var eiInfo = new EiInfo();
        eiInfo.setByNode("info");
        eiInfo.set("list",checkRows)
        // 固资类别录入提交
        EiCommunicator.send("FASH0101", "insertFaDiscuss", eiInfo, {
            onSuccess : function(ei) {
                window.parent['popDataWindow'].close();
                window.parent.location.reload()
            }
        })
    });

    $("#CANCEL").on("click", function (e) {
        window.parent['popDataWindow'].close();
    });

    IPLATUI.EFGrid = {
        "result": {
            pageable : false,
            exportGrid : false,
            // pageable: {
            //     pageSize: 15
            // },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "remove",
                        text: "移除资产",
                        layout: "3",
                        click: function () {
                            var checkRows = resultGrid.getCheckedRows()
                            resultGrid.removeRows(checkRows);
                        }
                    },
                ]
            },
            loadComplete: function (grid) {
                var checkRows = window.parent.resultAGrid.getCheckedRows()
                if (checkRows.length > 0) {
                    grid.addRows(checkRows);
                    grid.unCheckAllRows();
                }
            }
        },
    }
});