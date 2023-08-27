$(function (){
    // 查询
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#REQUERY").on("click", function(e) {
        $("#inqu_status-0-stageCode").val("");
        $("#inqu_status-0-stageName").val("");
        IPLAT.EFInput.value($("#inqu_status-0-stageCode"),"");
        IPLAT.EFInput.value($("#inqu_status-0-stageName"),"");
        resultGrid.dataSource.page(1);
    });
    // 必须写在框架里按钮才能生效
    IPLATUI.EFGrid = {
        "result" : {
            dataBound: function (e){

            },
            loadComplete: function (e){
                // 新增
                $("#ADDPG").on("click", function(e) {
                    var url = IPLATUI.CONTEXT_PATH + "/web/PMPG0101?initLoad";
                    var popData = $("#stageNew");
                    popData.data("kendoWindow").setOptions({
                        open : function() {
                            popData.data("kendoWindow").refresh({
                                url : url,
                            });
                        }
                    });
                    // 打开弹窗
                    stageNewWindow.open().center();
                });

                // 编辑
                $("#EDITPG").on("click", function(e) {
                    var id;
                    var rows = resultGrid.getCheckedRows();
                    if(rows.length > 0){
                        id = rows[0].stageId;
                    }else{
                        NotificationUtil("没有勾选数据","warning");
                        return;
                    }
                    var url = IPLATUI.CONTEXT_PATH + "/web/PMPG0102?stageId="+id;
                    var popData = $("#stageEdit");
                    popData.data("kendoWindow").setOptions({
                        open : function() {
                            popData.data("kendoWindow").refresh({
                                url : url
                            });
                        }
                    });
                    stageEditWindow.open().center();
                });

                // 删除
                $("#DELETEPG").on("click", function(e) {
                    var ids;
                    var info = new EiInfo();
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        ids = checkRows[0].stageId;
                    } else {
                        IPLAT.NotificationUtil("请选择需要删除的信息")
                    }
                    info.set("id",ids);
                    EiCommunicator.send("PMPG01", "deletePmStageMsg", info, {
                        onSuccess : function(ei) {
                            NotificationUtil(ei.getMsg(), "success");
                            resultGrid.dataSource.page(1);
                        }
                    });
                });
            }
        }
    }
});