$(function() {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        toolbar: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false,cancel: false,save: false,'delete': false,
            buttons: [{name: "SURE",text: "确定",layout: "3", click: function () {
                        let list = resultGrid.getCheckedRows();
                        if (list.length == 0) {
                            NotificationUtil("请选择合同信息", "error");
                            return;
                        }
                        window.parent.recoverCont(list[0]);
                        window.parent['contChooseWindow'].close();
                    }
                }
            ]
        }
    }).buildToolBarGrid();


    /**查询**/
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        $("#inqu_status-0-deptNum").val(__eiInfo.get("inqu_status-0-deptNum"));
        //$("#inqu_status-0-deptNum").val(__eiInfo.get("inqu_status-0-deptNum"));
        resultGrid.dataSource.page(1);
    });
})