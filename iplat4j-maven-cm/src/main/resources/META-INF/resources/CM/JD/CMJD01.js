$(function (){
    // 查询
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#REQUERY").on("click", function(e) {
        $("#inqu_status-0-nodeAutoNo").val("");
        $("#inqu_status-0-nodeName").val("");
        IPLAT.EFInput.value($("#inqu_status-0-nodeAutoNo"),"");
        IPLAT.EFInput.value($("#inqu_status-0-nodeName"),"");
        resultGrid.dataSource.page(1);
    });
    // 必须写在框架里按钮才能生效
    IPLATUI.EFGrid = {
        "result" : {
            dataBound: function (e){

            },
            loadComplete: function (e){
                // 新增
                $("#APPEND").on("click", function(e) {
                    node("","add");
                    nodeWindow.setOptions({"title":"新增节点信息"});
                });

                // 编辑
                $("#EDIT").on("click", function(e) {
                    var checkRows = resultGrid.getCheckedRows();
                    var nodeId;
                    if(checkRows.length > 0){
                        nodeId = checkRows[0].nodeId
                    }else{
                        NotificationUtil("没有勾选数据","error");
                        return;
                    }
                    node(nodeId,"edit");
                    nodeWindow.setOptions({"title":"编辑节点信息"});
                });

                // 删除
                $("#DELETER").on("click", function(e) {
                    var checkRows = resultGrid.getCheckedRows();
                    var info = new EiInfo();
                    if (checkRows.length > 0) {
                        var nodeId = checkRows[0].nodeId;
                        info.set("nodeId",nodeId);
                        IPLAT.confirm({
                            message: '<b>确定删除节点吗？</b></i>',
                            okFn: function (e) {
                                EiCommunicator.send("CMJD01", "delete", info, {
                                    onSuccess : function(ei) {
                                        NotificationUtil(ei.getMsg(), "success");
                                        resultGrid.dataSource.page(1);
                                    }
                                });
                            },
                            cancelFn: function (e) {}
                        })
                    } else {
                        IPLAT.NotificationUtil("请选择需要删除的信息","error")
                    }
                });
            }
        }
    }
});

function node(id,type){
    var url = IPLATUI.CONTEXT_PATH + "/web/CMJD0101?initLoad&id="+id +"&type="+type;
    var popData = $("#node");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url,
            });
        }
    });
    // 打开弹窗
    nodeWindow.open().center();
}