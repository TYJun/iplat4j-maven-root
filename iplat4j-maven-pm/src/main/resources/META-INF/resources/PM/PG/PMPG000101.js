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
                        window.parent["stageSelectWindow"].close();
                    } else {
                        IPLAT.NotificationUtil("请选择项目阶段");
                    }
                });
            }
        }
    }
});