$(function (){
    // 查询
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#RESET").on("click", function (e) {
        $("#inqu_status-0-billNo").val("");
        $("#inqu_status-0-createDateS").val("");
        $("#inqu_status-0-createDateE").val("");
        $("#inqu_status-0-arriveTimeS").val("");
        $("#inqu_status-0-arriveTimeE").val("");
        $("#inqu_status-0-deptNum").val("");
        IPLAT.EFSelect.value($("#inqu_status-0-statusCode"), "");
        resultGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "result": {
            onCellClick: function (e) {
                if (e.field === "billNo") {
                    var billNo = e.model.billNo;
                    var id = e.model.id;
                    var url = IPLATUI.CONTEXT_PATH + "/web/HRDP0102?billNo=" + billNo + "&id=" + id;
                    var popData = $("#popDataModify");
                    popDataCom(popData, url);
                    popDataModifyWindow.open().center();
                }
            },

            loadComplete: function (grid) {
                // 编辑
                $("#EXAMINE").on("click", function (e) {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length < 1) {
                        NotificationUtil("请选择要确认的行", "error");
                        return;
                    }
                    if (checkRows[0].statusCode != '02') {
                        NotificationUtil("只能操作待审批状态的工单", "error");
                        return;
                    }
                    var id =checkRows[0].id;
                    var billNo =checkRows[0].billNo;
                    var url = IPLATUI.CONTEXT_PATH + "/web/HRSP0201?id=" + id +"&billNo=" + billNo;
                    var popData = $("#popDataModify");
                    popDataCom(popData,url);
                    popDataModifyWindow.open().center();
                });

            }
        }
    }
})



/**
 * 向后台传参公共方法
 * @param serviceName
 * @param methodName
 * @param param
 */
function ajax(serviceName,methodName,param){
    EiCommunicator.send(serviceName,methodName,param, {
        onSuccess: function (ei) {
            if (ei.getStatus() == -1) {
                NotificationUtil(ei.getMsg(), "error");
                return;
            }
            NotificationUtil(ei.getMsg(), "success");
            resultGrid.dataSource.query(1);
        }
    })
}

/**
 *打开弹窗公共方法
 * @param popData
 */
function popDataCom(popData,url){
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url
            });
        }
    });
}