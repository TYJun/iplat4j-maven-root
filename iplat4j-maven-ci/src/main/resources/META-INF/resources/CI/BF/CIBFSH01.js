//报废审核
$(function(){

    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });

    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "result": {
            loadComplete: function (grid) {
                //审核
                $("#EXAMINE").on("click", function (e) {
                    var id;
                    var rows = resultGrid.getCheckedRows();
                    for (var i = 0; i < rows.length; i++) {
                        if(rows[i].applySign == "已审批"){
                            NotificationUtil("不能编辑已审批的工单", "error");
                            return;
                        }

                    }
                    if(rows.length > 0){
                        id = rows[0].id;
                        /*deptName=rows[0].applyDeptName;*/
                        /*deptNum=rows[0].applyDeptNum;*/
                        applyBillNo=rows[0].outBillNo;
                    }else{
                        NotificationUtil("没有勾选数据", "error");
                        return;
                    }
                    var url = IPLATUI.CONTEXT_PATH + "/web/CIBFSH0201?id="+id+"&applyBillNo=" + applyBillNo;
                    var popDataModify = $("#popDataModify");
                    popDataModify.data("kendoWindow").setOptions({
                        open : function() {
                            popDataModify.data("kendoWindow").refresh({
                                url : url
                            });
                        }
                    });
                    popDataModifyWindow.open().center();
                });

                //驳回
                $("#EXAMINEBACK").on("click", function (e) {
                    var checkRows = resultGrid.getCheckedRows();
                    if(checkRows.length<1){
                        NotificationUtil("没有勾选数据", "error");
                        return;
                    }

                    var idList = [];
                    for (var i = 0; i < checkRows.length; i++) {/*
                        if(checkRows[i].statusCode == "1"||checkRows[i].statusCode == "0"){
                            NotificationUtil("只能审核待审核工单", "error");
                            return;
                        }*/

                        idList[i] = checkRows[i].id;
                    }
                    if(rows.length > 0){
                        id = rows[0].id;
                        /* deptName=rows[0].applyDeptName;*/
                        applyBillNo=rows[0].applyBillNo
                    }else{
                        NotificationUtil("没有勾选数据", "error");
                        return;
                    }
                    var url = IPLATUI.CONTEXT_PATH + "/web/CICG0102?id="+id+"&applyBillNo=" + applyBillNo;
                    var popDataModify = $("#popDataModify");
                    popDataModify.data("kendoWindow").setOptions({
                        open : function() {
                            popDataModify.data("kendoWindow").refresh({
                                url : url
                            });
                        }
                    });
                    popDataModifyWindow.open().center();
                });

            }
        }
    }

});