$(function() {
    /**查询**/
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        resetTime("inqu_status-0-beginTime", "inqu_status-0-endTime");
        resultGrid.dataSource.page(1);
    });


    IPLATUI.EFGrid = {
        "result": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "Approval", text: "审批", layout: "3",
                    click: function () {
                        let checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            let id = checkRows[0].id;
                            popData(id,"MPHZ0201");
                        } else {
                            IPLAT.NotificationUtil("请选择一条需求计划进行审批","error")
                        }
                    }
                },{
                    name: "GEN", text: "生成采购计划", layout: "3",
                    click: function () {
                        let checkRows = resultGrid.getCheckedRows();
                        if(checkRows.length < 1) {
                            NotificationUtil("请选择需要生成采购计划的记录", "error");
                        } else if(checkRows[0].statusCode != "40") {
                            NotificationUtil("未审核记录，不可生成采购计划", "error");
                        } else {
                            popData("MPHZ0102?inqu_status-0-id="+ checkRows[0].id)
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


});


/**
 * 打开窗口
 * @param id
 * @param service
 */
function popData(uri) {
    let url = IPLATUI.CONTEXT_PATH + "/web/"+ uri;
    let popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url,
            });
        }
    });
    popDataWindow.open().center();
}