$(function() {
    IPLATUI.EFGrid = {
        "result": {
            loadComplete:function(){
                //确定
                $("#COMFIRM").on("click", function(e) {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        window.parent.addRows(checkRows);
                        window.parent["popDataPersonWindow"].close();
                    } else {
                        IPLAT.NotificationUtil("请选择人员");
                    }
                });

                //关闭
                $("#CLOSE").on("click", function(e) {
                    window.parent['popDataPersonWindow'].close();
                });
            }
        }
    }
    //查询
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });

    //重置按钮
    $("#RESET").on("click", function(e) {
        $("#inqu_status-0-realName").val("");
        $("#inqu_status-0-workNo").val("");
        resultGrid.dataSource.page(1);
    });


});

