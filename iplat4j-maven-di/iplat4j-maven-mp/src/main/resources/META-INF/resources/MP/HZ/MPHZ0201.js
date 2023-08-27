$(function (){
    IPLATUI.EFGrid = {
        "detail": {
            pageable:false,
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "PASS",text: "通过",layout: "3",
                    click: function () {
                        let eiInfo = new EiInfo();
                        eiInfo.set("id", $("#inqu_status-0-id").val())
                        //调用后台保存方法
                        EiCommunicator.send("MPHZ02", "pass", eiInfo, {
                            onSuccess: function (ei) {
                                if (ei.getStatus() === -1) {
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
                        let eiInfo = new EiInfo();
                        eiInfo.set("id", $("#inqu_status-0-id").val())
                        //调用后台保存方法
                        EiCommunicator.send("MPHZ02", "reject", eiInfo, {
                            onSuccess: function (ei) {
                                if (ei.getStatus() === -1) {
                                    NotificationUtil(ei.getMsg(), "error");
                                    return;
                                }
                                NotificationUtil("驳回成功", "success");
                                window.parent['popDataWindow'].close();
                                window.parent['resultGrid'].dataSource.page(1);
                            },
                            onFail: function (errorMsg, status, e) {
                                NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
                            }
                        });
                    }
                }]
            }
        }
    }
})
