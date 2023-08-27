IPLATUI.EFGrid = {
    "result" : {
        loadComplete: function (grid) {
            //问题认领按钮
            $("#CLAIM").on("click", function(e) {
                var checkRows = resultGrid.getCheckedRows();
                if(checkRows.length < 1){
                    NotificationUtil("请选择要认领的问题", "error");
                    return;
                }else{
                    var id = checkRows[0].id;
                    var statusCode = checkRows[0].statusCode;
                    if(10 == statusCode){
                        NotificationUtil("任务已被领取，请勿重复操作", "error");
                        return;
                    }
                    var eiInfo = new EiInfo();
                    eiInfo.set("id", id);
                    eiInfo.set("statusCode", statusCode);
                    //访问方法
                    EiCommunicator.send("PRGL02", "claim", eiInfo, {
                        onSuccess : function(ei) {
                            var status = ei.getStatus();
                            var msg = ei.getMsg();
                            if(-1 == status){
                                NotificationUtil(msg, "error");
                            }else{
                                NotificationUtil(msg, "success");
                                //刷新当前页面
                                setTimeout(function() {
                                    window.parent.location.reload()
                                }, 500);
                            }
                        }
                    });
                }
            });
            //问题撤销按钮
            $("#REJECT").on("click", function(e) {
                var checkRows = resultGrid.getCheckedRows();
                if(checkRows.length < 1){
                    NotificationUtil("请选择要撤销的问题", "error");
                    return;
                }else{
                    var id = checkRows[0].id;
                    var statusCode = checkRows[0].statusCode;
                    if("00" != statusCode){
                        NotificationUtil("已认领的问题不能撤销", "error");
                        return;
                    }
                    var eiInfo = new EiInfo();
                    eiInfo.set("id", id);
                    //访问方法
                    EiCommunicator.send("PRGL02", "reject", eiInfo, {
                        onSuccess : function(ei) {
                            var status = ei.getStatus();
                            var msg = ei.getMsg();
                            if(-1 == status){
                                NotificationUtil(msg, "error");
                            }else{
                                NotificationUtil(msg, "success");
                                //刷新当前页面
                                setTimeout(function() {
                                    window.parent.location.reload()
                                }, 500);
                            }
                        }
                    });
                }
            });
            //增加整改情况功能弹窗
            $("#ADDRECTIFICATION").on("click", function(e) {
                var checkRows = resultGrid.getCheckedRows();
                if(checkRows.length < 1){
                    NotificationUtil("请选择要整改的问题", "error");
                    return;
                }
                var id = checkRows[0].id;
                var statusCode = checkRows[0].statusCode;
                if (null == id) {
                    NotificationUtil("请选择需要增加整改情况的问题", "error");
                    return;
                }else if("00" == statusCode){
                    NotificationUtil("未认领的问题不能添加整改情况", "error");
                    return;
                }else if("09" == statusCode){
                    NotificationUtil("认领被驳回的问题不能添加整改情况", "error");
                    return;
                }else{
                    popData(id);
                }
            });
            //查看详情按钮
            $("#DETAIL").on("click", function(e) {
                var checkRows = resultGrid.getCheckedRows();
                if(checkRows.length < 1){
                    NotificationUtil("请选择需要查看详情的问题", "error");
                    return;
                }
                var id = checkRows[0].id;
                popData2(id);
            });
            //问题挂账按钮
            $("#ONACCOUNT").on("click", function(e) {
                var checkRows = resultGrid.getCheckedRows();
                if(checkRows.length < 1){
                    NotificationUtil("请选择需要挂账的问题", "error");
                    return;
                }
                var id = checkRows[0].id;
                var statusCode = checkRows[0].statusCode;
                if(19 == statusCode ){
                    NotificationUtil("挂账待认领的问题不能再次挂账", "error");
                    return;
                }else{
                    popData3(id);
                }
            });
        }
    }
}
//整改弹窗
function popData(id) {
    var url = IPLATUI.CONTEXT_PATH + "/web/PRGL0201?initLoad&id=" + id;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url
            });
        }
    });
    // 打开弹窗
    popDataWindow.open().center();
};
//详情弹窗
function popData2(id) {
    var url = IPLATUI.CONTEXT_PATH + "/web/PRGL0302?initLoad&id=" + id;
    var popData = $("#popData2");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url
            });
        }
    });
    // 打开弹窗
    popData2Window.open().center();
};
//挂账弹窗
function popData3(id) {
    var url = IPLATUI.CONTEXT_PATH + "/web/PRGL0202?initLoad&id=" + id;
    var popData = $("#popData3");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url
            });
        }
    });
    // 打开弹窗
    popData3Window.open().center();
};




$(function() {
    //弹窗页面
    function popData(id) {
        var url = IPLATUI.CONTEXT_PATH + "/web/PRGL0201?initLoad&id=" + id;
        var popData = $("#popData");
        popData.data("kendoWindow").setOptions({
            open : function() {
                popData.data("kendoWindow").refresh({
                    url : url
                });
            }
        });
        // 打开弹窗
        popDataWindow.open().center();
    };
    //详情弹窗
    function popData2(id) {
        var url = IPLATUI.CONTEXT_PATH + "/web/PRGL0302?initLoad&id=" + id;
        var popData = $("#popData");
        popData.data("kendoWindow").setOptions({
            open : function() {
                popData.data("kendoWindow").refresh({
                    url : url
                });
            }
        });
        // 打开弹窗
        popDataWindow.open().center();
    };
    //挂账弹窗
    function popData3(id) {
        var url = IPLATUI.CONTEXT_PATH + "/web/PRGL0202?initLoad&id=" + id;
        var popData = $("#popData");
        popData.data("kendoWindow").setOptions({
            open : function() {
                popData.data("kendoWindow").refresh({
                    url : url
                });
            }
        });
        // 打开弹窗
        popDataWindow.open().center();
    };
    //查询按钮
    $(function() {
        //查询
        $("#QUERY").on("click", function(e) {
            resultGrid.dataSource.query(1);
        });
        //重置按钮
        $("#RESET").on("click", function(e) {
            document.getElementById("inqu-trash").click();
            resultGrid.dataSource.page(1);
        });
    })
})