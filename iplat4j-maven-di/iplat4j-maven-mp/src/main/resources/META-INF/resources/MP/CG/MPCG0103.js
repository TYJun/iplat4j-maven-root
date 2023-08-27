$(function (){
    IPLATUI.EFGrid = {
        "result": {
            pageable:false,
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "sure", text: "确认", layout: "3",
                    click: function () {
                        var eiInfo = new EiInfo();
                        var recCreateTime = IPLAT.EFInput.value($("#inqu_status-0-recCreateTime"));
                        var recCreator = IPLAT.EFInput.value($("#inqu_status-0-recCreator"));
                        var id = IPLAT.EFInput.value($("#id"));
                        var purchaseNo = IPLAT.EFInput.value($("#inqu_status-0-purchaseNo"));
                        var checkRows = resultGrid.getDataItems();
                        if (checkRows.length > 0) {
                            var block = resultGrid.model2EiBlock(checkRows)
                            eiInfo.setByNode("result");
                            eiInfo.addBlock(block);
                        }
                        eiInfo.set("recCreateTime",recCreateTime);
                        eiInfo.set("recCreator",recCreator);
                        eiInfo.set("id",id);
                        eiInfo.set("purchaseNo",purchaseNo);
                        EiCommunicator.send("MPCG0103", "saveProcurementSplit", eiInfo, {
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
                                    window.parent['popDataWindow'].close();
                                    window.parent.resultGrid.dataSource.page(1);
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
// function addRows(checkRows){
//     var matList = resultGrid.getDataItems();
//     for (var index in checkRows) {
//         var model = checkRows[index];
//         var isExist = false;
//         for(var i in matList) {
//             var mat = matList[i];
//             if(mat.matNum == model.matNum){
//                 isExist = true;
//             }
//         }
//         if(!isExist){
//             model['enterNum'] = '0';
//             model['enterPrice'] = '0';
//             model['enterAmount'] = '0';
//             resultGrid.addRows(model)
//         }
//     }
// }