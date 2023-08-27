$(function () {
    //查看方法
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    })

    //重置按钮
    $("#REQUERY").on("click", function (e) {
        IPLAT.EFInput.value($("#inqu_status-0-interviewerWorkNo"), "");
        IPLAT.EFInput.value($("#inqu_status-0-interviewerName"), "");
        IPLAT.EFInput.value($("#inqu_status-0-vistingBeginDate"), "");
        resultGrid.dataSource.page(1);
    });
});
IPLATUI.EFGrid = {
    "result": {
        exportGrid : false,
        toolbarConfig: {
            hidden: false,
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [{
                name: "", text: "访问登记", layout: "3",
                click: function () {
                    popDataWindow.setOptions({"title":"预约登记"});
                    popData("","enter");
                }
            }, {
                name: "edit", text: "审批", layout: "3",
                click: function () {
                    var rows = resultGrid.getCheckedRows();
                    if (rows.length > 0){
                        var id = rows[0].id;
                        popDataWindow.setOptions({"title":"审批"});
                        popData2(id,rows[0].creatorIdentity);
                    } else {
                        NotificationUtil("请选择需要审批的记录", "warning");
                    }
                }
            },
            // {
            //     name: "deleter", text: "数据导入", layout: "3",
            //     click: function () {
            //
            //     }
            // }
            ]
        },
        loadComplete: function (grid) {

        },
    }
}


// 录入
function popData(id, type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/VIDJ0101?initLoad&id=" + id + "&type=" + type;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url,
            });
        }
    });
    popDataWindow.open().center();
}

// 查看
function popData2(id, type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/VIDJ0102?initLoad&id=" + id + "&type=" + type;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url,
            });
        }
    });
    popDataWindow.open().center();
}