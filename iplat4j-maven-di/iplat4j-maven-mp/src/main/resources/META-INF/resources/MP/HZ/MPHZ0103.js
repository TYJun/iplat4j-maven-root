$(function() {

    IPLATUI.EFCascadeSelect = {
        "inqu_status-0-deptNum" : {
            change : function(e){

                IPLAT.EFInput.value($("#inqu_status-0-collectDeptNum"), this.value())
                IPLAT.EFInput.value($("#inqu_status-0-collectDeptName"), this.text())
                resultGrid.cancelChanges();
            }

        }
    }
    /**表格初始化处理**/
    IPLATUI.EFGrid = {
        "mat": {
            height:  window.innerHeight - 210,
            pageable: {pageSize: 20, pageSizes: [10, 20, 50, 100]},
            onRowDblClick: function (e) {
                let checkRows = matGrid.getCheckedRows();
                addRows(checkRows);
            },
            toolbarConfig: {
                buttons: [{
                    name: "SURE",text: "确定",layout:"3",
                    click: function () {
                        let checkRows = matGrid.getCheckedRows();
                        if (checkRows && checkRows.length > 0) {
                            addRows(checkRows);

                        } else {
                            NotificationUtil("请选择物资", "warning");
                        }
                    }
                }]
            }
        },
        "result":{
            height:  window.innerHeight - 210,
            pageable: false,
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false,cancel: false,save: false,'delete': false,
                buttons:[{
                    name: "DEL",text: "删除",layout: "3",
                    click: function () {
                        let checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            resultGrid.removeRows(checkRows);
                        } else {
                            IPLAT.NotificationUtil("请选择需要删除的数据", "warning")
                        }
                    }
                },{
                    name: "SAVE",text: "保存",layout: "3",
                    click: function (){ save();}
                }],
            },
        },
    };


    /**查询**/
    $("#QUERY").on("click", function(e) {
        matGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        matGrid.dataSource.page(1);
    });

});

/**
 * 物资选择数据返回
 * @param checkRows
 */
function addRows(checkRows) {
    distinctGridAdd("result", checkRows, undefined,"matNum");
}

/**
 * 保存汇总信息
 * @param validator
 */
function save() {
    var method = "save";
    let validator = IPLAT.Validator({id: "add_claim"});
    if (!validator.validate()) {
        //校验不通过
        return;
    }

    $("#SAVE").attr("disabled", true);
    setTimeout(function () {$("#SAVE").attr("disabled", false);}, 5000);
    //参数处理
    let eiInfo = new EiInfo();
    eiInfo.setByNode("inqu");
    //获取表格数据
    let planDetailList = resultGrid.getDataItems();
    if(planDetailList.length < 1) {
        NotificationUtil("需求计划汇总不能为空", "error");
        return;
    }

    eiInfo.set("edit", __ei.type);
    if (__ei.type === "edit") {
        eiInfo.set("collectId", __eiInfo.get("inqu_status-0-id"));
        method = "update";
    }

    //保存
    eiInfo.set("planDetailList", planDetailList);
    eiInfo.set("recCreatorName", IPLAT.EFInput.value($("#inqu_status-0-recCreatorName")));
    eiInfo.set("recCreator", IPLAT.EFInput.value($("#inqu_status-0-recCreator")));
    eiInfo.set("collectDeptNum", IPLAT.EFInput.value($("#inqu_status-0-collectDeptNum")));
    eiInfo.set("collectDeptName", IPLAT.EFInput.value($("#inqu_status-0-collectDeptName")));
    eiInfo.set("purchaseType", IPLAT.EFSelect.value($("#inqu_status-0-purchaseType")));
    //调用后台保存方法
    EiCommunicator.send("MPHZ0103", method, eiInfo, {
        onSuccess: function (ei) {
            if (ei.getStatus() == -1) {
                NotificationUtil(ei.getMsg(), "error");
                return;
            }

            NotificationUtil("保存成功", "success");
            window.parent['popDataWindow'].close();
            window.parent['resultGrid'].dataSource.page(1);
        },
        onFail: function (errorMsg, status, e) {
            NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
        }
    });

}