$(function() {
    /** 成本科室自动补全 */
    $("#costDeptName").kendoAutoComplete({
        dataSource: getDataSources("MPTY01/selectDept", "dept",[{name:'deptName',id:"costDeptName"}]),
        dataTextField: "deptName",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#costDeptNum").val(dataItem.deptNum);
        }
    });
    //成本归集科室初始赋值
    $("#costDeptName").val(__eiInfo.get("detail_form-0-deptName"));
    $("#costDeptNum").val(__eiInfo.get("detail_form-0-deptNum"));

    IPLATUI.EFGrid = {
        "detail" : {
            pageable : false,
            exportGrid : true,
            toolbarConfig:{
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false,cancel: false,save: false,'delete': false,
                buttons:[{
                    name: "COMMON_CHOOSE",text: "常用物资选择",layout:"3",
                    click: function () { popData("RMPZ0203?inqu_status-0-isClaim=Y"); }
                },{
                    name: "CHOOSE",text: "选择物资",layout:"3",
                    click: function () { popData("RMLY0103?inqu_status-0-isClaim=Y"); }
                },{
                    name: "MAT_DEL",text: "删除",layout: "3",
                    click: function () {
                        let checkRows = detailGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            detailGrid.removeRows(checkRows);
                        } else {
                            IPLAT.NotificationUtil("请选择需要删除的数据", "warning")
                        }
                    }
                }]
            },
            afterEdit: function(e){
                let grid = e.sender; let rowIndex = e.row
                let price = $.isNumeric(e.model["price"]) ? +e.model["price"] : 0.00;
                let num = $.isNumeric(e.model["num"]) ? +e.model["num"] : 0.00;
                grid.setCellValue(rowIndex, "claimAmount", (price*num).toFixed(2)+"");
            },
            loadComplete:function (e) {}
        },
        "result" : {
            toolbarConfig: {
                add: false,cancel: false,save: false,'delete': false,
                buttons:[{
                    name: "DEL",text: "删除",layout:"3",
                    click: function () { deleteClaim() }
                }, {
                    name: "SUBMIT",text: "提交",layout:"3",
                    click: function () { submitClaim() }
                },{
                    name: "BACK",text: "撤回",layout:"3",
                    click: function () { backClaim() }
                },{
                    name: "SIGN",text: "签收",layout:"3",
                    click: function () { signClaim() }
                },{
                    name: "OVER",text: "结束领用",layout:"3",
                    click: function () { overClaim() }
                }]
            },
            onRowClick : function (e) {
                e.preventDefault();
                reShowClaim(e.model);
                showButton(["01", "20"].includes(e.model['statusCode']) ? true : false);
            },
        }
    }

    setTimeout(function() { resultGrid.dataSource.page(1);}, 500);

    /**回车键查询**/
    keydown("inqu", "QUERY");

    /**查询**/
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        resetParam(__eiInfo, true, true)
        resultGrid.dataSource.page(1);
    });

    /**保存**/
    $("#SAVE").unbind('click').on('click', function(e){
        // 防止连续提交
        $("#SAVE").attr("disabled", true);
        setTimeout(function () {$("#SAVE").attr("disabled", false);}, 5000);
        saveClaim("N");
    });

    /**保存并提交**/
    $("#SAVE_AND_SUBMIT").unbind('click').on('click', function(e){
        // 防止连续提交
        $("#SAVE").attr("disabled", true);
        setTimeout(function () {$("#SAVE_AND_SUBMIT").attr("disabled", false);}, 5000);
        saveClaim("Y");
    });

    /**新增并保存**/
    $("#ADD_ADN_SAVE").unbind('click').on('click', function(e){
        // 防止连续提交
        $("#ADD_ADN_SAVE").attr("disabled", true);
        setTimeout(function () {$("#ADD_ADN_SAVE").attr("disabled", false);}, 5000);
        $("#type").val("add")
        saveClaim("N");
    });

    /**新增并提交**/
    $("#ADD_AND_SUBMIT").unbind('click').on('click', function(e){
        // 防止连续提交
        $("#ADD_AND_SUBMIT").attr("disabled", true);
        setTimeout(function () {$("#ADD_AND_SUBMIT").attr("disabled", false);}, 5000);
        $("#type").val("add")
        saveClaim("Y");
    });

    /**清空表单**/
    $("#REMOVE_ALL").unbind('click').on('click', function(e){
        reShowClaim(null);
        showButton(true);
    });

});

/**
 * 物资选择数据返回
 * @param checkRows : array 领用明细集合
 */
function addRows(checkRows) {
    distinctGridAdd("detail", false, checkRows, ["num", "claimAmount"],"matNum");
}

/**
 * 删除领用单
 */
function deleteClaim() {
    let checkRows = window["resultGrid"].getCheckedRows();
    if (checkRows.length < 1) {
        NotificationUtil("请先选择需要删除的记录", "error");
    } else if (checkRows[0].statusCode && '01' != checkRows[0].statusCode) { //存在状态,但状态不满足
        NotificationUtil("该记录无法删除", "error");
    } else {
        IPLAT.confirm({
            message: '<b>您确定要删除吗？</b>',
            okFn: function (e) {
                let eiInfo = new EiInfo();
                eiInfo.set("id", checkRows[0].id);
                EiCommunicator.send("RMLY01", "delete", eiInfo, {
                    onSuccess: function (ei) {
                        if (ei.getStatus() == -1) {
                            NotificationUtil(ei.getMsg(), "error");
                            return;
                        }
                        NotificationUtil("删除成功");
                        window["resultGrid"].dataSource.page(1);
                    }
                });
            },
            cancelFn: function (e) {
            }
        })
    }
}

/**
 * 提交领用单
 */
function submitClaim() {
    let checkRows = window["resultGrid"].getCheckedRows()
    if (checkRows.length < 1) {
        NotificationUtil("请先选择需要提交的记录", "error");
    } else if (checkRows[0].statusCode && "20" == checkRows[0].statusCode){
        NotificationUtil("请先编辑完善信息后再提交", "error");
    } else if (checkRows[0].statusCode && '01' != checkRows[0].statusCode) { //存在状态,但状态不满足
        NotificationUtil("已提交的记录无需再次提交", "error");
    }else {
        IPLAT.confirm({
            message: '<b>提交后无法编辑,确定要提交吗？</b>',
            okFn: function (e) {
                let eiInfo = new EiInfo();
                eiInfo.set("id", checkRows[0].id);
                EiCommunicator.send("RMLY01", "submit", eiInfo, {
                    onSuccess: function (ei) {
                        if (ei.getStatus() == -1) {
                            NotificationUtil(ei.getMsg(), "error");
                            return;
                        }
                        NotificationUtil("提交成功");
                        window["resultGrid"].dataSource.page(1);
                    }
                });
            },
            cancelFn: function (e) {}
        })
    }
}

/**
 * 撤回领用单
 */
function backClaim() {
    let checkRows = window["resultGrid"].getCheckedRows()
    if (checkRows.length < 1) {
        NotificationUtil("请先选择需要撤回的记录", "error");
    } else if (checkRows[0].statusCode && !["10", "30", "40"].includes(checkRows[0].statusCode) ) { //存在状态,但状态不满足
        NotificationUtil("该记录无法撤回", "error");
    } else {
        let eiInfo = new EiInfo();
        eiInfo.set("id", checkRows[0].id);
        EiCommunicator.send("RMLY01", "withdraw", eiInfo, {
            onSuccess: function (ei) {
                if (ei.getStatus() == -1) {
                    NotificationUtil(ei.getMsg(), "error");
                    return;
                }
                NotificationUtil("撤回成功");
                window["resultGrid"].dataSource.page(1);
            }
        });
    }
}

/**
 * 领用单签收
 */
function signClaim() {
    let checkRows = window["resultGrid"].getCheckedRows();
    if (checkRows.length < 1) {
        NotificationUtil("请选择需要签收的记录", "error");
    } else if ("70" != checkRows[0].statusCode) {
        NotificationUtil("未完成出库或已签收的记录无法签收", "error");
    } else {
        let eiInfo = new EiInfo();
        eiInfo.set("id", checkRows[0].id);
        EiCommunicator.send("RMLY01", "signAccept", eiInfo, {
            onSuccess: function (ei) {
                if (ei.getStatus() == -1) {
                    NotificationUtil(ei.getMsg(), "error");
                    return;
                }
                NotificationUtil("签收成功");
                window["resultGrid"].dataSource.page(1);
            }
        });
    }
}

/**
 * 结束领用单
 */
function overClaim() {
    let checkRows = window["resultGrid"].getCheckedRows();
    if (checkRows.length < 1) {
        NotificationUtil("请选择需要结束的记录", "error");
    } else if (["70","80"].includes(checkRows[0].statusCode)) {
        NotificationUtil("已领用的记录无需结束", "error");
    } else {
        let eiInfo = new EiInfo();
        eiInfo.set("id", checkRows[0].id);
        EiCommunicator.send("RMLY01", "over", eiInfo, {
            onSuccess: function (ei) {
                if (ei.getStatus() == -1) {
                    NotificationUtil(ei.getMsg(), "error");
                    return;
                }
                NotificationUtil("结束成功");
                window["resultGrid"].dataSource.page(1);
            }
        });
    }
}

/**
 * 保存领用单
 * @param submitFlag : string 是否提交标记
 */
function saveClaim(submitFlag) {
    //参数处理
    let eiInfo = new EiInfo();
    eiInfo.setByNode("detail_form");
    //重命名EiBlock
    renameBlock(eiInfo, "detail_form", "inqu_status")
    eiInfo.set("submitFlag", submitFlag)
    if(!$("#costDeptName").val()){
        NotificationUtil("成本归集科室不能为空", "error");
        return;
    }
    eiInfo.set("inqu_status-0-costDeptNum", $("#costDeptNum").val());
    eiInfo.set("inqu_status-0-costDeptName", $("#costDeptName").val());
    //校验物资列表
    let list = detailGrid.getDataItems();
    if(!list || list.length == 0) {
        NotificationUtil("领用明细列表不能为空", "error");
        return;
    }
    eiInfo.set("detailList", list);

    //调用后台保存方法
    EiCommunicator.send("RMLY0101", "save", eiInfo, {
        onSuccess: function (ei) {
            if (ei.getStatus() == -1) {
                NotificationUtil(ei.getMsg(), "error");
                return;
            }
            NotificationUtil("保存成功", "success");
            reShowClaim(null);
            window['resultGrid'].dataSource.page(1);
        },
        onFail: function (errorMsg, status, e) {
            NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
        }
    });
}

/**
 * 显示按钮
 */
function showButton(showSave) {
    if(showSave) {
        $("#SAVE").show();
        $("#SAVE_AND_SUBMIT").show();
    } else {
        $("#SAVE").hide();
        $("#SAVE_AND_SUBMIT").hide();
    }
}

/**
 * 数据回显
 * @param data
 */
function reShowClaim (data) {
    if(data) {
        $("#detail_form-0-id").val(data['id']);
        $("#detail_form-0-deptNum").val(data['deptNum']);
        $("#detail_form-0-deptName").val(data['deptName']);
        $("#detail_form-0-recCreator").val(data['recCreator']);
        $("#detail_form-0-recCreatorName").val(data['recCreatorName']);
        $("#costDeptName").val(data['costDeptName']);
        $("#costDeptNum").val(data['costDeptNum']);
        $("#detail_form-0-remark").val(data['remark']);
        $("#type").val("edit");
    } else {
        $("#detail_form-0-recCreator").val(__eiInfo.get("detail_form-0-recCreator"));
        $("#detail_form-0-recCreatorName").val(__eiInfo.get('detail_form-0-recCreatorName'));
        $("#costDeptName").val(__eiInfo.get("detail_form-0-deptName"));
        $("#costDeptNum").val(__eiInfo.get("detail_form-0-deptNum"));
        $("#detail_form-0-remark").val("");
        $("#detail_form-0-id").val("");
        $("#type").val("add");
    }
    window['detailGrid'].dataSource.page(1);
}