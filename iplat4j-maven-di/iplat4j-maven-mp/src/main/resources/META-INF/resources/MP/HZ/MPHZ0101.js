$(function() {
    IPLATUI.EFTreeInput = {
        "inqu_status-0-matTypeId": {
            /*ROOT: {label: __eiInfo.get("inqu_status-0-matTypeId"),
                text: __eiInfo.get("inqu_status-0-matTypeName"),
                code: __eiInfo.get("inqu_status-0-matTypeNum")
            },*/
            ROOT: {label:"root", text:"物资分类", code:""},
            backFill: function (e) {
                $("#inqu_status-0-matTypeNum").val(e.node.code);
                reloadGrid();
            },
        }
    }
    let validator = IPLAT.Validator({id: "inqu"});
    IPLAT.EFTreeInput.setAllFields( $("#inqu_status-0-matTypeId") , __eiInfo.get("inqu_status-0-matTypeId"),
        __eiInfo.get("inqu_status-0-matTypeName"));

    IPLATUI.EFTab = {
        "tab-tab_grid": {
            show: function (e) {
                $(e.contentElement).find("div[data-role='grid']").data("kendoGrid").refresh()
            },
           /* select: function (e) {
                let grid = $(e.contentElement).find("div.k-grid").data("kendoGrid");
                if(grid.options.blockId == "detail" && isDelete){
                    let planList = resultGrid.getDataItems();
                    grid.dataSource.page(1);
                }
            }*/
        }
    }

    IPLATUI.EFGrid = {
        "result": {
            pageable: false,
            toolbarConfig: __ei.type == 'see' ? undefined : {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false,cancel: false,save: false,'delete': false,
                buttons: [/*{
                    name: "DEL",text: "删除",layout: "3",
                    click: function (){
                        let checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            resultGrid.removeRows(checkRows);
                            isDelete = true;
                        } else {
                            IPLAT.NotificationUtil("请选择需要删除的数据", "warning")
                        }
                    }
                },*/{
                    name: "SAVE",text: "保存",layout: "3",
                    click: function (){ save(validator);}
                }]
            }
        },
        "detail": {
            pageable: false,
        }
    }
});

/**
 * 保存汇总信息
 * @param validator
 */
function save(validator) {
    if(validator.validate()) {
        $("#SAVE").attr("disabled", true);
        setTimeout(function () {$("#SAVE").attr("disabled", false);}, 5000);
        //参数处理
        let eiInfo = new EiInfo();
        eiInfo.setByNode("inqu");
        //获取表格数据
        let planDetailList = resultGrid.getDataItems();
        let detailList = detailGrid.getDataItems();
        if(planDetailList.length < 1) {  NotificationUtil("需求计划明细不能为空", "error"); }
        detailList.forEach(detail => detail.eiMetadata = '');

        //保存
        eiInfo.set("planDetailList", planDetailList);
        eiInfo.set("detailList", detailList);
        //调用后台保存方法
        EiCommunicator.send("MPHZ0101", "save", eiInfo, {
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
    } else {
        NotificationUtil("请选择汇总类型", "error");
    }
}

/**
 * 重新加载所有表格数据
 */
function reloadGrid() {
    let ei = new EiInfo();
    ei.setByNode("inqu");
    EiCommunicator.send("MPHZ0101", "query", ei, {
        onSuccess: function (ei) {
            resultGrid.setEiInfo(ei);
            detailGrid.setEiInfo(ei);
        }, onFail: function () { }
    });
}