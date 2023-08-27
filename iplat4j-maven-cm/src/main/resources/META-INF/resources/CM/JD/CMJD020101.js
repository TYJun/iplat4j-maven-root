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
        "result": {
            dataBound: function (e) {

            },
            loadComplete: function (e) {
                // 确定
                $("#CONFIRMPG").on("click",function (e){
                    var checkRows = resultGrid.getCheckedRows();
                    var eiInfo = new EiInfo();
                    if (checkRows.length > 0) {
                        window.parent.addRows(checkRows);
                        window.parent["scheduleSelectWindow"].close();
                    } else {
                        IPLAT.NotificationUtil("请选择合同节点","error");
                    }
                });
            }
        }
    }
});