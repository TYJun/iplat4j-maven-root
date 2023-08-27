var datagrid = null;


IPLATUI.EFGrid = {
    "result" : {
        loadComplete: function (grid) {
            //查询
            $("#QUERY").on("click", function(e) {
                resultGrid.dataSource.query(1);
            });



            //新增按钮
            $("#ADD").on("click", function(e) {
                popData("", "add");
            });

            /* 删除 */
            $("#REDELETE").on("click",function(e) {
                var checkRows = resultGrid.getCheckedRows()
                if(checkRows.length <= 0) {
                    NotificationUtil("请选择需要删除的记录", "warning")
                }else {
                    var checkRows = resultGrid.getCheckedRows()
                        var eiInfo = new EiInfo();
                        var id = checkRows[0].id;
                        eiInfo.set("id",id);
                        EiCommunicator.send("SSBMDZ03", "delete", eiInfo, {
                            onSuccess : function(ei) {
                                NotificationUtil(ei.getMsg(), "success");
                                resultGrid.dataSource.page(1);
                            }
                        });
                    }
            });

        },

    }
}

function popData(id, type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/SSBMDZ0301?initLoad&id=" + id
        + "&type=" + type;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url,
            });
        }
    });
    popDataWindow.open().center();
}

$(function() {

});