$(function () {

    // 导入用户   取消按钮
    $("#cancelImportUser").on("click", function(e) {
        window['importUserInfoPopDataWindow'].close();
    });

    $("#saveImportUser").on("click", function(e) {
        var validator =  IPLAT.Validator({ id: "importuser" });
        //校验必填项
        if (!validator.validate()) {
            NotificationUtil("必填项参数不能为空！", "warning");
            return ;
        }
        var eiInfo = new EiInfo();

        var value = {
            userType : IPLAT.EFSelect.value($("#userType")),
            userId : IPLAT.EFInput.value($("#userId")),
            userName : IPLAT.EFInput.value($("#userName")),
            userIdcard :IPLAT.EFInput.value($("#userIdcard")),
            depName :IPLAT.EFInput.value($("#depName")),
            userPhone :IPLAT.EFInput.value($("#userPhone")),
            userEmail :IPLAT.EFInput.value($("#userEmail"))
        };
        eiInfo.set("userInfo", value);
        EiCommunicator.send("XQMS04", "importUser", eiInfo, {
            onSuccess : function(ei) {

                console.log(ei);
                if(ei.status != 0) {
                    NotificationUtil(ei.getMsg(), "error");
                } else {
                    NotificationUtil(ei.getMsg(), "success");
                    //关闭弹窗
                    $("#cancelImportUser").click();
                }
            }
        });

    });

    // 导入用户  新增按钮

    IPLATUI.EFGrid = {
        "result": {
            columns :[{
                field: "signatureImg",
                template: "<div class='signImageDiv'><img style='width: 100px' src='#=signatureImgStr#' class='signImg'></img><div>"
            }],
            dataBound: function (e) {
                var grid = e.sender;
                var trs = grid.table.find("tr");
            },
            dataBinding: function (e) {

            },
            beforeRequest: function (e) {

            },
            toolbarConfig : {
                add: false, cancel: false, save: false, 'delete': false,
                buttons : [{
                    name: "queryUserInfo",
                    text: "查询",
                    layout: "3",
                    click: function () {
                        // 先将grid置为空，防止显示就数据数据
                        resultGrid.dataSource.data([]);
                        resultGrid.dataSource.query(1);
                    }
                },{
                    name: "delUserInfo",
                    text: "删除",
                    layout: "3",
                    click: function () {
                        var checkRows = resultGrid.getCheckedRows();
                        if(checkRows.length<1){
                            NotificationUtil("请选择要删除的行", "error");
                            return;
                        } else if(checkRows.length > 1){
                            NotificationUtil("不能选中多行记录！", "error");
                            return;
                        }

                        var eiInfo = new EiInfo();
                        var userId = checkRows[0].userId;
                        eiInfo.set('userId', userId);

                        IPLAT.confirm({
                            message: '<b>你确定要删除' + userId +'在医信签的用户信息吗?</b>',
                            okFn: function (e) {
                                EiCommunicator.send("XQMS04", "delUser", eiInfo, {
                                    onSuccess : function(ei) {
                                        if(ei.status != 0) {
                                            NotificationUtil(ei.getMsg(), "error");
                                        } else {
                                            NotificationUtil(ei.getMsg(), "success");
                                            resultGrid.dataSource.query(1);
                                        }
                                    }
                                });
                            },
                            cancelFn: function() {

                            }
                        });
                    }
                },{
                    name: "importUserInfo",
                    text: "导入",
                    layout: "3",
                    click: function () {
                        importUserInfoPopDataWindow.setOptions({"title":"导入"});
                        importUserInfoPopDataWindow.open().center();
                    }
                },{
                    name: "freezeUser",
                    text: "冻结",
                    layout: "3",
                    click: function () {
                        var checkRows = resultGrid.getCheckedRows();
                        if(checkRows.length<1){
                            NotificationUtil("请选择要冻结的行", "error");
                            return;
                        } else if(checkRows.length > 1){
                            NotificationUtil("不能选中多行记录！", "error");
                            return;
                        }

                        var eiInfo = new EiInfo();
                        eiInfo.set('userId', checkRows[0].userId);

                        EiCommunicator.send("XQMS04", "freezeUser", eiInfo, {
                            onSuccess : function(ei) {
                                if(ei.status != 0) {
                                    NotificationUtil(ei.getMsg(), "error");
                                } else {
                                    NotificationUtil(ei.getMsg(), "success");
                                    resultGrid.dataSource.query(1);
                                }
                            }
                        });
                    }
                },{
                    name: "unFreezeUser",
                    text: "解冻",
                    layout: "3",
                    click: function () {
                        var checkRows = resultGrid.getCheckedRows();
                        if(checkRows.length<1){
                            NotificationUtil("请选择要解冻的行", "error");
                            return;
                        } else if(checkRows.length > 1){
                            NotificationUtil("不能选中多行记录！", "error");
                            return;
                        }

                        var eiInfo = new EiInfo();
                        eiInfo.set('userId', checkRows[0].userId);

                        EiCommunicator.send("XQMS04", "unFreezeUser", eiInfo, {
                            onSuccess : function(ei) {
                                if(ei.status != 0) {
                                    NotificationUtil(ei.getMsg(), "error");
                                } else {
                                    NotificationUtil(ei.getMsg(), "success");
                                    resultGrid.dataSource.query(1);
                                }
                            }
                        });
                    }
                }]
            }
        }
    }

});