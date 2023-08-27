$(function (){
    // 查询
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#REQUERY").on("click", function(e) {
        $("#inqu_status-0-scheduleAutoNo").val("");
        $("#inqu_status-0-scheduleName").val("");
        IPLAT.EFInput.value($("#inqu_status-0-scheduleAutoNo"),"");
        IPLAT.EFInput.value($("#inqu_status-0-scheduleName"),"");
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
                    schedule("","add");
                    scheduleWindow.setOptions({"title":"新增合同进程信息"});
                });

                // 编辑
                $("#EDIT").on("click", function(e) {
                    var checkRows = resultGrid.getCheckedRows();
                    var scheduleId;
                    if(checkRows.length > 0){
                        scheduleId = checkRows[0].scheduleId
                    }else{
                        NotificationUtil("没有勾选数据","error");
                        return;
                    }
                    schedule(scheduleId,"edit");
                    scheduleWindow.setOptions({"title":"编辑合同进程信息"});
                });

                // 删除
                $("#DELETER").on("click", function(e) {
                    var checkRows = resultGrid.getCheckedRows();
                    var info = new EiInfo();

                    if (checkRows.length > 0) {
                        var scheduleId = checkRows[0].scheduleId;
                        info.set("scheduleId",scheduleId);
                        IPLAT.confirm({
                            message: '<b>确定删除节点吗？</b></i>',
                            okFn: function (e) {
                                EiCommunicator.send("CMJD02", "delete", info, {
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

function schedule(id,type){
    var url = IPLATUI.CONTEXT_PATH + "/web/CMJD0201?initLoad&id="+id +"&type="+type;
    var popData = $("#schedule");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url,
            });
        }
    });
    // 打开弹窗
    scheduleWindow.open().center();
}