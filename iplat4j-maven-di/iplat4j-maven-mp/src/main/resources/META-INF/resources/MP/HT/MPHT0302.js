$(function() {
    IPLATUI.EFGrid = {
        "result": {
            loadComplete:function(){
                //确定
                $("#COMFIRM").on("click", function(e) {
                    let checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        window.parent.addRows(checkRows);
                        window.parent["popDataWindow"].close();
                    } else {
                        IPLAT.NotificationUtil("请选择发票");
                    }
                });
            },
        }
    }
    //查询
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });

    //重置按钮
    $("#REQUERY").on("click", function(e) {
        $("#inqu_status-0-invoiceNo").val('');
        resultGrid.dataSource.page(1);
    });
})