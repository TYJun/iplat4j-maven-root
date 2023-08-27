$(function () {
    // 查询
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#RESET").on("click", function (e) {
        $("#inqu_status-0-billNo").val("")
        $("#inqu_status-0-complaintName").val("")
        $("#inqu_status-0-complaintDept").val("")
        $("#inqu_status-0-complaintDateS").val("")
        $("#inqu_status-0-complaintDateE").val("")
        $("#inqu_status-0-complaintContent").val("")
        $("#inqu_status-0-dealDateS").val("")
        $("#inqu_status-0-dealDateE").val("")
        $("#inqu_status-0-dealWorkName").val("")
        $("#inqu_status-0-dealDept").val("")
        resultGrid.dataSource.page(1);
    });
})

IPLATUI.EFGrid = {
    "result": {
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [
            // {
            //     name: "goods", text: "查看", layout: "3",
            //     click: function () {
            //         var checkRows = resultGrid.getCheckedRows();
            //         if (checkRows.length < 0) {
            //             IPLAT.NotificationUtil("请选择一条数据", "error")
            //         }
            //         var id = checkRows[0].id;
            //         var billNo = checkRows[0].billNo;
            //         var url = IPLATUI.CONTEXT_PATH + "/web/CPZH0101?initLoad" + "&id=" + id +"&billNo=" +billNo;
            //         var popData = $("#popData");
            //         popData.data("kendoWindow").setOptions({
            //             open: function () {
            //                 popData.data("kendoWindow").refresh({
            //                     url: url,
            //                 });
            //             }
            //         });
            //         // 打开工作流节点人员选择弹窗
            //         popDataWindow.open().center();
            //     }
            // },
            {
                name: "goodsConfig", text: "回访登记", layout: "3",
                click: function () {
                    var checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length < 0) {
                        IPLAT.NotificationUtil("请选择一条数据", "error")
                    }
                    var id = checkRows[0].id;
                    var billNo = checkRows[0].billNo;
                    var url = IPLATUI.CONTEXT_PATH + "/web/CPCL0102?initLoad" + "&id=" + id +"&billNo=" +billNo;
                    var popData = $("#popData2");
                    popData.data("kendoWindow").setOptions({
                        open: function () {
                            popData.data("kendoWindow").refresh({
                                url: url,
                            });
                        }
                    });
                    // 打开工作流节点人员选择弹窗
                    popData2Window.open().center();
                }
            },
            // {
            //     name: "completed", text: "完结", layout: "3",
            //     click: function () {
            //         var checkRows = resultGrid.getCheckedRows();
            //         if (checkRows.length > 0) {
            //             var id = checkRows[0].id;
            //             var eiInfo = new EiInfo();
            //             eiInfo.set("id",id);
            //             IPLAT.confirm({
            //                 message: '<b>确定进行完结操作吗？</b></i>',
            //                 okFn: function (e) {
            //                     EiCommunicator.send("CPCL01", "completed", eiInfo, {
            //                         onSuccess: function (ei) {
            //                             IPLAT.NotificationUtil(ei.msg)
            //                             resultGrid.dataSource.page(1);
            //                         }
            //                     })
            //                 },
            //                 cancelFn: function (e) {}
            //             })
            //         } else {
            //             IPLAT.NotificationUtil("请选择一条记录进行删除","error")
            //         }
            //     }
            // }
            ]
        },
        dataBound: function (e) {

        },
        loadComplete: function (e) {

        }
    }
}