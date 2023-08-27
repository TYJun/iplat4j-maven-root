$(function (){
    IPLATUI.EFGrid = {
        "result": {
            pageable:false,
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                    name: "procurement", text: "物资选择", layout: "3",
                    click: function () {
                        popData1Window.setOptions({"title":"物资选择"});
                        popData1();
                    }
                }, {
                    name: "sure", text: "保存", layout: "3",
                    click: function () {
                        var eiInfo = new EiInfo();
                        var recCreateTime = IPLAT.EFInput.value($("#inqu_status-0-recCreateTime"));
                        var recCreator = IPLAT.EFInput.value($("#inqu_status-0-recCreator"));
                        var purchaseRemark = IPLAT.EFInput.value($("#inqu_status-0-purchaseRemark"));
                        let purchaseType = IPLAT.EFSelect.value($("#inqu_status-0-purchaseType"));
                        var id = IPLAT.EFInput.value($("#id"));
                        var checkRows = resultGrid.getDataItems();
                        if (checkRows.length > 0) {
                            var block = resultGrid.model2EiBlock(checkRows)
                            eiInfo.setByNode("result");
                            eiInfo.addBlock(block);
                        }
                        eiInfo.set("recCreateTime",recCreateTime);
                        eiInfo.set("recCreator",recCreator);
                        eiInfo.set("purchaseRemark",purchaseRemark);
                        eiInfo.set("purchaseType", purchaseType);
                        eiInfo.set("id",id);
                        EiCommunicator.send("MPCG0101", "saveProcurementPlanning", eiInfo, {
                            onSuccess: function (ei) {
                                if(ei.getStatus() === -1){
                                    IPLAT.alert(ei.getMsg());
                                } else if(ei.getStatus() === -2){
                                    IPLAT.alert(ei.getMsg());
                                }else if(ei.getStatus() === -3) {
                                    IPLAT.alert(ei.getMsg());
                                } else if(ei.getStatus() === -4){
                                    IPLAT.alert(ei.getMsg());
                                }
                                else{
                                    NotificationUtil("提交成功", "success");
                                    window.parent.resultGrid.dataSource.page(1);
                                    window.parent['popDataWindow'].close();
                                }

                            }
                        })
                    }
                },{
                    name: "DEL",text: "删除",layout:"3",
                    click: function () {
                        var checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            resultGrid.removeRows(checkRows);
                        } else {
                            IPLAT.NotificationUtil("请选择需要删除的物资")
                        }
                    }
                }]
            },
            dataBound: function (e) {

            },
            loadComplete: function (e) {

            }
        }
    }
})

//物资选择弹窗
function popData1() {
    var url = IPLATUI.CONTEXT_PATH + "/web/MPCG0102?initLoad";
    var popData = $("#popData1");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url,
            });
        }
    });
    popData1Window.open().center();
}

//添加物资（过滤重复）
function addRows(checkRows){
    var matList = resultGrid.getDataItems();
    for (var index in checkRows) {
        var model = checkRows[index];
        var isExist = false;
        for(var i in matList) {
            var mat = matList[i];
            if(mat.matNum == model.matNum){
                isExist = true;
            }
        }
        if(!isExist){
            // model['enterNum'] = '0';
            // model['enterPrice'] = '0';
            // model['enterAmount'] = '0';
            // resultGrid.addRows(model)
            distinctGridAdd("result", checkRows, ["num"],"matNum");
        }
    }
}

/**
 * grid添加数据并去重
 * @param resultId : string
 * @param checkedRow : array
 * @param addFields : array
 * @param args
 */
function distinctGridAdd(resultId, checkRows, addFields, ...args) {
    let rows = window[resultId+"Grid"].getDataItems();
    if(rows && rows.length > 0 && args && args.length > 0) {
        for (let i = 0; i < checkRows.length; i++) {
            let model = checkRows[i],isExist = false;
            rows.forEach(e => {
                let isEqual = true;
                args.forEach(field => isEqual = isEqual && e[field] == model[field])
                isExist = isExist || isEqual;
            });
            if(!isExist) {
                if(addFields) {
                    addFields.forEach(field => model[field] = "")
                }
                window[resultId+"Grid"].addRows(model);
            }
        }
    } else {
        if(addFields) {
            checkRows.forEach(row =>{
                addFields.forEach(field => row[field] = "")
            })
        }
        window[resultId+"Grid"].addRows(checkRows)
    }
}

