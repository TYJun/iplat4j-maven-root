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
                add: false,cancel: false,save: false,'delete': false,
                buttons : [
                    {
                        name : "register",
                        text : "登记",
                        layout : "3",
                        click : function() {
                            popDataWindow.setOptions({"title":"独立任务登记"});
                            customWindow("enter","")
                        }
                    },
                    {
                        name : "edit",
                        text : "编辑",
                        layout : "3",
                        click : function() {
                            if (checkRows()){
                                popDataWindow.setOptions({"title":"独立任务编辑"});
                                customWindow("edit",checkRows().itTaskId)
                            }
                        }
                    },
                    {
                        name : "submit",
                        text : "提交",
                        layout : "3",
                        click : function() {
                            if (checkRows()){
                                popDataWindow.setOptions({"title":"独立任务提交"});
                                customWindow("submit",checkRows().itTaskId)
                            }
                        }
                    },
                    {
                        name : "cancellation",
                        text : "作废",
                        layout : "3",
                        click : function() {
                            if (checkRows()){
                                popDataWindow.setOptions({"title":"独立任务作废"});
                                customWindow("cancellation",checkRows().itTaskId)
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
function customWindow(type,id){
    var url = IPLATUI.CONTEXT_PATH + "/web/ITDJ0101?initLoad&itTaskId="+id +"&type="+type;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url,
            });
        }
    });
    // 新窗口打开居中
    popDataWindow.open().center();
}

// 超链接弹框
function popData(taskNo,type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/ITDJ0102?initLoad&taskNo=" + taskNo + "&type=" + type;
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

function checkRows(){
    const checkRows = resultGrid.getCheckedRows();
    if (checkRows.length < 1){
        NotificationUtil("请先选择一条记录","error");
        return false;
    }else {
        return checkRows[0];
    }
}