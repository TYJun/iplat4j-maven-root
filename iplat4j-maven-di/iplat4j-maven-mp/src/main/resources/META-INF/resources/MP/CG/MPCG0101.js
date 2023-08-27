$(function (){
    var editValidator = IPLAT.Validator({ id: "inqu" });

    IPLATUI.EFGrid = {
        "result": {
            columns:[ {
                field: "price",
                readonly: true,
                template: "<span>#=price#元</span>",
            }],
            pageable:false,
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "procurement", text: "物资选择", layout: "3",
                    click: function () {
                        popData1Window.setOptions({"title":"物资选择"});
                        popData1();
                    }
                },{
                    name: "sure", text: "保存", layout: "3",
                    click: function () {
                        if (!editValidator.validate()) {
                            //校验不通过
                            return ;
                        }
                        let eiInfo = new EiInfo();
                        let recCreateTime = IPLAT.EFInput.value($("#inqu_status-0-recCreateTime"));
                        let recCreator = IPLAT.EFInput.value($("#inqu_status-0-recCreator"));
                        let purchaseRemark = IPLAT.EFInput.value($("#inqu_status-0-purchaseRemark"));
                        let purchaseType = IPLAT.EFSelect.value($("#inqu_status-0-purchaseType"));
                        let id = IPLAT.EFInput.value($("#id"));
                        let checkRows = resultGrid.getDataItems();
                        if (checkRows.length > 0) {
                            let block = resultGrid.model2EiBlock(checkRows)
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
                                if(ei.getStatus() === -1) {
                                    NotificationUtil(ei.getMsg(), "error");
                                } else{
                                    NotificationUtil("提交成功", "success");
                                    window.parent['popDataWindow'].close();
                                    window.parent.resultGrid.dataSource.page(1);
                                }

                            }
                        })
                    }
                },{
                    name: "DEL",text: "删除",layout:"3",
                    click: function () {
                        let checkRows = resultGrid.getCheckedRows();
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
    let url = IPLATUI.CONTEXT_PATH + "/web/MPCG0102?initLoad";
    let popData = $("#popData1");
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
    let matList = resultGrid.getDataItems();
    for (let index in checkRows) {
        let model = checkRows[index];
        let isExist = false;
        for(let i in matList) {
            let mat = matList[i];
            if(mat.matNum == model.matNum){
                isExist = true;
            }
        }
        if(!isExist){
            // model['enterNum'] = '0';
            // model['enterPrice'] = '0';
            // model['enterAmount'] = '0';
            resultGrid.addRows(model)
        }
    }
}