$(function() {
    /** 业务科室自动补全 */
    $("#save_data-0-deptName").kendoAutoComplete({
        dataSource: getDataSources("SITY02/selectBusinessDept", "b_dept",[{name:'deptName',id:"save_data-0-deptName"}]),
        dataTextField: "deptName",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#save_data-0-deptCode").val(dataItem['deptNum']);
        }
    });

    /** 姓名人自动补全 */
    $("#save_data-0-name").kendoAutoComplete({
        dataSource: getDataSources("SITY02/selectPerson", "person",[{name:'name',id:"save_data-0-name"}]),
        dataTextField: "name",
        filter:"contains",
        template:'<span>#:name#-#:workNo#</span>',
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#save_data-0-workNo").val(dataItem['workNo']);
        }
    });

    /**回车键查询**/
    keydown("inqu", "QUERY");

    $("#QUERY").on('click', function(e){ resultGrid.dataSource.page(1);})

    $("#REQUERY").on('click', function(e){
        document.getElementById("inqu-trash").click();
        resultGrid.dataSource.page(1);
    })

    IPLATUI.EFGrid = {
        "result": {
            pageable: {
                pageSize: 50,
                pageSizes: [10, 20, 50, 100, 500]
            },
            loadComplete: function (e) {
                $("#EDIT").on("click", function(e) { edit();});
                $("#DEL").on("click", function(e) { del();});
            }
        }
    }

    //保存
    $("#SAVE").unbind('click').on('click', function(event) {
        // 防止连续提交
        $("#SAVE").attr("disabled", true);
        setTimeout(function () {$("#SAVE").attr("disabled", false);}, 5000);

        //获取表单数据
        let eiInfo = new EiInfo();
        eiInfo.set("type", $("#type").val());
        eiInfo.setByNode("save_data");

        if(validate(eiInfo)) {
            //调用后台保存方法
            EiCommunicator.send("SIPZ02", "save", eiInfo, {
                onSuccess : function(ei) {
                    if(ei.getStatus() == -1){
                        NotificationUtil(ei.getMsg(), "error");
                        return;
                    }

                    NotificationUtil("保存成功", "success");
                    resultGrid.dataSource.page(1);
                    setParam(null);
                },
                onFail : function(errorMsg, status, e) {
                    NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
                }
            });
        }
    });

});

/**
 * 数据校验
 * @param eiInfo
 * @returns {boolean}
 */
function validate(eiInfo) {
    if(!eiInfo.get("save_data-0-workNo")){
        NotificationUtil("请选择人员信息", "error");
        return false;
    }

    if(!eiInfo.get("save_data-0-name")){
        NotificationUtil("请选择人员信息", "error");
        return false;
    }

    if(!eiInfo.get("save_data-0-deptCode")){
        NotificationUtil("请选择科室", "error");
        return false;
    }

    if(!eiInfo.get("save_data-0-deptName")){
        NotificationUtil("请选择科室", "error");
        return false;
    }

    return true;
}

/**
 * 编辑
 */
function edit() {
    let checkRows = resultGrid.getCheckedRows()
    if(checkRows.length != 1) {
        NotificationUtil("请选择一条需要编辑的数据","error");
    } else {
        $("#type").val("edit");
        setParam(checkRows[0])
    }
}

/**
 * 删除
 */
function del() {
    let checkRows = resultGrid.getCheckedRows()
    if(checkRows.length < 1) {
        NotificationUtil("请选择需要删除的数据","error");
    } else {
        IPLAT.confirm({
            message: '<b>您确定要删除吗？</b>',
            okFn: function (e) {
                let ids = checkRows.map(row => row['id']);
                let eiInfo = new EiInfo(); eiInfo.set("ids", ids);
                //调用后台删除方法
                EiCommunicator.send("SIPZ02", "delete", eiInfo, {
                    onSuccess : function(ei) {
                        if(ei.getStatus() == -1){
                            NotificationUtil(ei.getMsg(), "error");
                            return;
                        }

                        NotificationUtil("删除成功", "success");
                        resultGrid.dataSource.page(1);
                    },
                    onFail : function(errorMsg, status, e) {
                        NotificationUtil("删除失败，原因[" + errorMsg + "]", "error");
                    }
                });
            },
            cancelFn: function (e) {
            }
        })
    }
}

/**
 * 参数赋值或清空
 * @param row
 */
function setParam(row) {
    let setFlag = row == null ?  false : true;
    $("#save_data-0-id").val(setFlag ? row['id'] : '');
    $("#save_data-0-workNo").val(setFlag ? row['workNo'] : '');
    $("#save_data-0-name").val(setFlag ? row['name'] : '');
    $("#save_data-0-deptCode").val(setFlag ? row['deptCode'] : '');
    $("#save_data-0-deptName").val(setFlag ? row['deptName'] : '');
    if(!setFlag) {$("#type").val("add")}
}