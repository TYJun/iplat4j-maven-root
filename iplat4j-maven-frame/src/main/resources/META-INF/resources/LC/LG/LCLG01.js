$(function () {

    $("#QUERY").on("click", function (e) {
        var operationClass = $("#inqu_status-0-operationClass").val();
        if (IPLAT.isAvailable(operationClass.trim())) {
            resultGrid.dataSource.page(1);
        }else {
            IPLAT.alert("请选择一种操作类型！！！");
        }
    });
    $("#QUERYS").on("click", function (e) {
        popup_resultGrid.dataSource.page(1);
    })

    IPLATUI.EFGrid = {
        "popup_result": {
            onCheckRow: function (e) {
                if (!e.fake) { // 手动点击触发的事件 例如使用API选中行/点击表头的checkbox 都是模拟的事件
                    var grid = e.sender,
                        model = e.model;
                    var userName = model["userName"];// 当前勾选行的"itemEname"值
                    var loginName = model["loginName"];
                    /*   var lengtha = itemEname.length;*/
                    /*    var input = IPLAT.EFInput.value($("#detail-0-itemEname1"));*/

                    $("#ef_popup_grid_userName").on("click", function (e) {
                        $("#inqu_status-0-operator").val(loginName);
                        $("#inqu_status-0-operator_textField.popupInput").val(userName);
                        var popupGridWindow = $("#ef_popup_grid").data("kendoWindow");
                        popupGridWindow.close();
                    })

                }
            }
        }
    }

});