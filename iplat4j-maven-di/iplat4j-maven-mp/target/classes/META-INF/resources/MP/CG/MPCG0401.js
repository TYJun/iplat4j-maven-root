$(function (){
    IPLATUI.EFGrid = {
        "result": {
            pageable:false,
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "PASS",text: "通过",layout: "3",
                    click: function () {
                        let eiInfo = new EiInfo();
                        eiInfo.set("planIds", $("#inqu_status-0-id").val().split(","))
                        //调用后台保存方法
                        EiCommunicator.send("MPCG0401", "pass", eiInfo, {
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
                            okFn: function (e) { reject(e)},
                            cancelFn: function (e) {},
                            title: '采购计划审批驳回',
                            minWidth: 450
                        });
                    }
                }]
            },
            dataBound: function (e) {

            },
            loadComplete: function (e) {

            }
        }
    }
})

function reject (rejectReason) {
    //参数处理
    let eiInfo = new EiInfo();
    eiInfo.set("planIds", $("#inqu_status-0-id").val().split(","));
    if(!rejectReason) {
        NotificationUtil("请输入驳回原因", "error");
        return;
    }
    eiInfo.set("rejectReason", rejectReason);
    //调用后台保存方法
    EiCommunicator.send("MPCG0401", "reject", eiInfo, {
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