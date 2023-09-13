$(function() {

    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        toolbar: __ei.type == 'see' ? {} : {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false,cancel: false,save: false,'delete': false,
            buttons:[{
                name: "PASS",text: "通过",layout: "3",
                click: function () {
                    let eiInfo = new EiInfo();
                    eiInfo.set("claimId", $("#inqu_status-0-id").val())
                    //调用后台保存方法
                    EiCommunicator.send("RMLY0301", "pass", eiInfo, {
                        onSuccess: function (ei) {
                            if (ei.getStatus() == -1) {
                                NotificationUtil(ei.getMsg(), "error");
                                return;
                            }

                            NotificationUtil("审批成功", "success");
                            window.parent['popDataWindow'].close();
                            window.parent['resultGrid'].dataSource.page(1);
                        },
                        onFail: function (errorMsg, status, e) {
                            NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
                        }
                    });
                }
            },{
                name: "REJECT",text: "驳回",layout: "3",
                click: function () {
                    IPLAT.prompt({
                        message: '驳回原因',
                        okFn: function (e) { reject(e) },
                        cancelFn: function (e) {},
                        title: '领用申请审批驳回',
                        minWidth: 450
                    });
                }
            }]
        },
    }).buildToolBarGrid();
});

/**
 * 驳回
 * @param rejectReason
 */
function reject (rejectReason) {
    //参数处理
    let eiInfo = new EiInfo();
    eiInfo.set("claimId", $("#inqu_status-0-id").val());
    if(!rejectReason) {
        NotificationUtil("请输入驳回原因", "error");
        return;
    }
    eiInfo.set("rejectReason", rejectReason);

    //调用后台保存方法
    EiCommunicator.send("RMLY0301", "reject", eiInfo, {
        onSuccess: function (ei) {
            if (ei.getStatus() == -1) {
                NotificationUtil(ei.getMsg(), "error");
                return;
            }

            NotificationUtil("保存成功", "success");
            window.parent.parent['popDataWindow'].close();
            window.parent.parent['resultGrid'].dataSource.page(1);
        },
        onFail: function (errorMsg, status, e) {
            NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
        }
    });
}