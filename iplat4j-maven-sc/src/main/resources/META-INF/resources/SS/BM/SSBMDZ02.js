var datagrid = null;


IPLATUI.EFGrid = {
    "result" : {
        loadComplete: function (grid) {
            //查询
            $("#QUERY").on("click", function(e) {
                resultGrid.dataSource.query(1);
            });

            //编辑按钮
            $("#EDIT").on("click", function(e) {
                if (datagrid == null) {
                    IPLAT.alert("请先选择一条记录进行修改");
                } else {
                    popData(datagrid.id, "edit");
                    datagrid = null;
                }
            });

            //停用按钮
            $("#REDELETE").on("click", function (e) {

                var id;
                var rows = resultGrid.getCheckedRows();
                if(rows.length > 0){
                    id = rows[0].id;
                }else{
                    NotificationUtil("没有勾选数据", "error");
                    return;
                }
                var info = new EiInfo();
                info.set("id", id);
                EiCommunicator.send("SSBMDZ02", "delete", info, {
                    onSuccess : function(ei) {
                        if(ei.getMsg()=='删除成功'){
                            NotificationUtil(ei.getMsg(), "success");
                        }else{
                            NotificationUtil(ei.getMsg(), "faild");
                        }
                        resultGrid.dataSource.page(1);
                    }
                });
            });

            //启用按钮
            $("#REDELETE1").on("click", function (e) {

                var id;
                var rows = resultGrid.getCheckedRows();
                if(rows.length > 0){
                    id = rows[0].id;
                }else{
                    NotificationUtil("没有勾选数据", "error");
                    return;
                }
                var info = new EiInfo();
                info.set("id", id);
                EiCommunicator.send("SSBMDZ02", "delete1", info, {
                    onSuccess : function(ei) {
                        if(ei.getMsg()=='删除成功'){
                            NotificationUtil(ei.getMsg(), "success");
                        }else{
                            NotificationUtil(ei.getMsg(), "faild");
                        }
                        resultGrid.dataSource.page(1);
                    }
                });
            });

            //新增按钮
            $("#ADD").on("click", function(e) {
                popData("", "add");
            });

        },

    }
}

function popData(id, type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/SSBMDZ0201?initLoad&id=" + id
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