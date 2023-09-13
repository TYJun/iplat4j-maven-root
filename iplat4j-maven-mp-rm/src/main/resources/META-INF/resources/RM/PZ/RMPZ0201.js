$(function() {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        resultId: "mat",
        showImg: true,
        toolbar: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false,cancel: false,save: false,'delete': false,
            buttons:[{
                name: "SAVE",text: "保存",layout:"3",
                click: function () {
                    let checkRows = matGrid.getCheckedRows();
                    if (checkRows && checkRows.length > 0) {
                        //参数处理
                        let eiInfo = new EiInfo();
                        eiInfo.set("list", checkRows);

                        //调用后台保存方法
                        EiCommunicator.send("RMPZ0201", "save", eiInfo, {
                            onSuccess: function (ei) {
                                if (ei.getStatus() == -1) {
                                    NotificationUtil(ei.getMsg(), "error");
                                    return;
                                }

                                NotificationUtil("保存成功", "success");
                                window.parent['popDataWindow'].close();
                                window.parent['resultGrid'].dataSource.page(1);
                            },
                            onFail: function (errorMsg, status, e) {
                                NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
                            }
                        });
                    } else {
                        NotificationUtil("请选择物资", "warning");
                    }
                }
            }]
        },
    }).buildToolBarGrid();

    /**回车键查询**/
    keydown("inqu", "QUERY");

    /**查询**/
    $("#QUERY").on("click", function(e) {
        matGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        matGrid.dataSource.page(1);
    });
});