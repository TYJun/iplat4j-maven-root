$(function() {
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
            toolbarConfig:{
                hidden: false,
                add: false,cancel: false,save: false,'delete': false,
                buttons:[{
                    name: "EDIT",text: "编辑",layout:"3",
                    click: function () { edit();}
                },{
                    name: "DEL",text: "删除",layout:"3",
                    click: function () { del();}
                }]
            },
        }
    }

    //保存
    $("#SAVE").unbind('click').on('click', function(event) {
        // 防止连续提交
        $("#SAVE").attr("disabled", true);
        setTimeout(function () {$("#SAVE").attr("disabled", false);}, 5000);
        save();
    });1

});

//保存
function save() {
    let validator = IPLAT.Validator({id: "save_data"});

    //获取表单数据
    let eiInfo = new EiInfo();
    eiInfo.set("type", $("#type").val());
    eiInfo.setByNode("save_data");

    let wareHouseName = IPLAT.EFSelect.text($("#save_data-0-wareHouseNo"));
    let relateMatTypeName = _.map($("#save_data-0-relateMatType").data("kendoMultiSelect").dataItems(),
        _.iteratee("textField"));
    let applyMatTypeName = _.map($("#save_data-0-applyMatType").data("kendoMultiSelect").dataItems(),
        _.iteratee("textField"));
    eiInfo.set("save_data-0-wareHouseName", wareHouseName);
    eiInfo.set("save_data-0-relateMatTypeName", relateMatTypeName.join(","));
    eiInfo.set("save_data-0-applyMatTypeName", applyMatTypeName.join(","));

    if(validator.validate()) {
        //调用后台保存方法
        EiCommunicator.send("SIPZ03", "save", eiInfo, {
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
                EiCommunicator.send("SIPZ03", "delete", eiInfo, {
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
    IPLAT.EFSelect.value($("#save_data-0-wareHouseNo"), setFlag ? row['wareHouseNo'] : '');
    $("#save_data-0-relateMatType").data("kendoMultiSelect").value(setFlag ? row['relateMatType'].split(',') : '');
    $("#save_data-0-applyMatType").data("kendoMultiSelect").value(setFlag ? row['applyMatType'].split(',') : '');
    if(!setFlag) {$("#type").val("add")}
}