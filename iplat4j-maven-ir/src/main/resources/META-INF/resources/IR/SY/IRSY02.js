$(function () {

    /**
     * 查询
     */
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });

    /**
     * 重置
     */
    $("#RESET").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultGrid.dataSource.query(1);
    });

    IPLATUI.EFGrid = {
        "result": {
            dataBound: function (e) {
                var grid = e.sender;
                var trs = grid.table.find("tr");
                $.each(trs, function (i, tr) {
                    // 获取状态的标签，然后渲染标签最内部的数据
                    var statusTdStr = $(tr).find('td').last().text();
                    if(statusTdStr == '1'){
                        $(tr).find('td').last().text('启用');
                    } else {
                        $(tr).find('td').last().text('停用');
                    }
                });
            },
            loadComplete: function (grid) {
                // 新增
                $("#ADD").on("click", function (e) {
                    var url = IPLATUI.CONTEXT_PATH + "/web/IRSY0201";
                    var insertNoticePopData = $("#insertNoticePopData");
                    insertNoticePopData.data("kendoWindow").setOptions({
                        open : function() {
                            insertNoticePopData.data("kendoWindow").refresh({
                                url : url
                            });
                        }
                    });
                    insertNoticePopDataWindow.open().center();
                });
                $("#EDIT").on("click", function (e) {
                    var checkRows = resultGrid.getCheckedRows();
                    if(checkRows.length <= 0){
                        NotificationUtil("请选择一条记录！", "error");
                        return;
                    } else if(checkRows.length > 1){
                        NotificationUtil("不能选中多行记录！", "error");
                        return;
                    }
                    var url = IPLATUI.CONTEXT_PATH + "/web/IRSY0202?initLoad&id=" + checkRows[0].id;
                    var editNoticePopData = $("#editNoticePopData");
                    editNoticePopData.data("kendoWindow").setOptions({
                        open : function() {
                            editNoticePopData.data("kendoWindow").refresh({
                                url : url
                            });
                        }
                    });
                    editNoticePopDataWindow.open().center();
                });

                // 根据id删除公告
                $("#DELETENOTICE").on("click", function (e) {
                    var checkRows = resultGrid.getCheckedRows();

                    if(checkRows.length <= 0){
                        NotificationUtil("请至少选择一条记录！", "error");
                        return;
                    }

                    var idStr ="";
                    for(var index = 0; index < checkRows.length; index++){
                        console.log(index + ', ' + checkRows[index].id + ', ' + idStr);
                        if(index == checkRows.length-1){
                            idStr += checkRows[index].id;
                        } else {
                            idStr += checkRows[index].id + ",";
                        }
                    }

                    var eiInfo = new EiInfo();
                    eiInfo.set("idStr", idStr);

                    EiCommunicator.send("IRSY02", "deleteNoticeRecordById", eiInfo, {
                        onSuccess : function(ei) {
                            if(ei.status == '1'){ //修改成功
                                NotificationUtil(ei.getMsg(), "success");
                            } else {
                                NotificationUtil(ei.getMsg(), "error");
                            }

                            setTimeout(function() {
                                window.location.reload()
                            }, 100);
                        }
                    });

                });

                // 批量停用
                $("#BATCHDISABLE").on("click", function (e) {
                    var checkRows = resultGrid.getCheckedRows();

                    if(checkRows.length <= 0){
                        NotificationUtil("请至少选择一条记录！", "error");
                        return;
                    }

                    var idStr ="";
                    for(var index = 0; index < checkRows.length; index++){
                        console.log(index + ', ' + checkRows[index].id + ', ' + idStr);
                        if(index == checkRows.length-1){
                            idStr += checkRows[index].id;
                        } else {
                            idStr += checkRows[index].id + ",";
                        }
                    }

                    var eiInfo = new EiInfo();
                    eiInfo.set("idStr", idStr);

                    EiCommunicator.send("IRSY02", "batchDisableNotice", eiInfo, {
                        onSuccess : function(ei) {
                            if(ei.status == '1'){ //修改成功
                                NotificationUtil(ei.getMsg(), "success");
                            } else {
                                NotificationUtil(ei.getMsg(), "error");
                            }

                            setTimeout(function() {
                                window.location.reload()
                            }, 100);
                        }
                    });

                });
            }
        }
    }
});