$(function() {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        add:false, edit:false, del:false,
        extentMethod:[{
            buttonId: "COLLECT",
            call: function () { popData("MPHZ0103?type=add")}
        },{
            buttonId: "EDIT",
            call: function () {
                var checkRows = resultGrid.getCheckedRows();
                if (checkRows.length > 0) {
                    var status = checkRows[0].statusCode;
                    if(status === "01" || status === "30"){
                        popData("MPHZ0103?type=edit&inqu_status-0-id=" + checkRows[0].id)
                    }else {
                        IPLAT.NotificationUtil("此需求计划无法编辑","error")
                    }

                }
                else {
                    IPLAT.NotificationUtil("请选择一条需求计划进行编辑","error")
                }
            }
        },{
            buttonId: "SUBMIT",
            call: function () {
                var checkRows = resultGrid.getCheckedRows();
                if (checkRows.length > 0) {
                    var id = checkRows[0].id;
                    var status = checkRows[0].statusCode;
                    var eiInfo = new EiInfo();
                    eiInfo.set("id",id);
                    eiInfo.set("statusCode",status);
                    IPLAT.confirm({
                        message: '<b>确定提交吗？</b></i>',
                        okFn: function (e) {
                            EiCommunicator.send("MPHZ01", "submit", eiInfo, {
                                onSuccess: function (ei) {
                                    if(ei.getStatus() < 0){
                                        IPLAT.alert(ei.getMsg());
                                    }else {
                                        resultGrid.dataSource.page(1);
                                    }
                                }
                            })
                        },
                        cancelFn: function (e) {}
                    })

                } else {
                    IPLAT.NotificationUtil("请选择一条需求计划进行提交","error")
                }
            }
        },{
            buttonId: "DEL",
            call: function () {
                var checkRows = resultGrid.getCheckedRows();
                if (checkRows.length > 0) {
                    var id = checkRows[0].id;
                    var status = checkRows[0].statusCode;
                    var eiInfo = new EiInfo();
                    eiInfo.set("id",id);
                    eiInfo.set("statusCode",status);
                    IPLAT.confirm({
                        message: '<b>确定删除操作吗？</b></i>',
                        okFn: function (e) {
                            EiCommunicator.send("MPHZ01", "delete", eiInfo, {
                                onSuccess: function (ei) {
                                    if(ei.getStatus() < 0){
                                        IPLAT.alert(ei.getMsg());
                                    }else {
                                        resultGrid.dataSource.page(1);
                                    }
                                }
                            })
                        },
                        cancelFn: function (e) {}
                    })
                } else {
                    IPLAT.NotificationUtil("请选择一条需求汇总计划进行删除","error")
                }
            }
        },{
            buttonId: "WITHDRAW",
            call: function () {
                var checkRows = resultGrid.getCheckedRows();
                if (checkRows.length > 0) {
                    var id = checkRows[0].id;
                    var status = checkRows[0].statusCode;
                    var eiInfo = new EiInfo();
                    eiInfo.set("id",id);
                    eiInfo.set("statusCode",status);
                    IPLAT.confirm({
                        message: '<b>确定撤回吗？</b></i>',
                        okFn: function (e) {
                            EiCommunicator.send("MPHZ01", "withdraw", eiInfo, {
                                onSuccess: function (ei) {
                                    if(ei.getStatus() < 0){
                                        IPLAT.alert(ei.getMsg());
                                    }else {
                                        resultGrid.dataSource.page(1);
                                    }
                                }
                            })
                        },
                        cancelFn: function (e) {}
                    })
                } else {
                    IPLAT.NotificationUtil("请选择一条需求汇总计划进行撤回","error")
                }
            }
        }]
    }).buildGrid();

    /**查询**/
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        $("#inqu_status-0-deptNum").val(__eiInfo.get("inqu_status-0-deptNum"));
        $("#inqu_status-0-deptName").val(__eiInfo.get("inqu_status-0-deptName"));
        resetTime("inqu_status-0-beginTime", "inqu_status-0-endTime");
        resultGrid.dataSource.page(1);
    });

});