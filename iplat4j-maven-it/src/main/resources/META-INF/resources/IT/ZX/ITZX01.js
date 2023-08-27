$(function () {
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1)
    });

    $("#REQUERY").on("click", function (e) {
        $("#inqu_status-0-taskNo").val("")
        IPLAT.EFTreeInput.setAllFields( $("#inqu_status-0-parentId") , "" , "");
        $("#inqu_status-0-parentId").val("")
        $("#inqu_status-0-reqDeptName").val("")
        $("#inqu_status-0-serveDeptName").val("")
        $("#inqu_status-0-recCreateTimeS").val("")
        $("#inqu_status-0-recCreateTimeE").val("")
        resultGrid.dataSource.page(1)
    });

    IPLATUI.EFGrid = {
        "result": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "completion",
                        text: "完工",
                        layout: "3",
                        click: function () {
                            if (checkRows()) {
                                popDataWindow.setOptions({"title": "独立任务完工"});
                                customWindow("completion", checkRows().itTaskId)
                            }
                        }
                    },
                ]
            },
            onCellClick:function (e){
                if (e.field === "taskNo") {
                    var taskNo = e.model['taskNo'];
                    var type = "look";
                    if (taskNo != " " && taskNo != null) {
                        popDataWindow.setOptions({"title":"独立任务详情"});
                        popData(taskNo, type);
                    }
                }
            },
            loadComplete: function (grid) {

            }
        }
    };
})

// 自定义弹窗
function customWindow(type, id) {
    var url = IPLATUI.CONTEXT_PATH + "/web/ITZX0101?initLoad&itTaskId=" + id + "&type=" + type;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url,
            });
        }
    });
    // 新窗口打开居中
    popDataWindow.open().center();
}

// 超链接弹框
function popData(taskNo,type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/ITZX0102?initLoad&taskNo=" + taskNo + "&type=" + type;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url,
            });
        }
    });
    popDataWindow.open().center();
}


function checkRows() {
    const checkRows = resultGrid.getCheckedRows();
    if (checkRows.length < 1) {
        NotificationUtil("请先选择一条记录", "error");
        return false;
    } else {
        return checkRows[0];
    }
}