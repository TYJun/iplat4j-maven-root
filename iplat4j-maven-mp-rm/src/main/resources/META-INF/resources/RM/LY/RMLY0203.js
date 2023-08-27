$(function() {


    IPLATUI.EFSelect = {
        "inqu_status-0-wareHouseNo": {
            select: function (e) { //获取勾选值
                let dataItem = e.dataItem;
                $("#inqu_status-0-wareHouseName").val(dataItem['textField']);
            }
        }
    }

    //表格初始化处理
    let formValidator = IPLAT.Validator({id: "inqu"}); let submitFlag = true;

    IPLATUI.EFGrid = {
        "result" : {
            pageable: false,
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false,cancel: false,save: false,'delete': false,
                buttons:[{
                    name: "SUBMIT",text: "出库确认",layout: "3",
                    click: function () {
                        // 防止连续提交
                        $("#SUBMIT .k-grid-SUBMIT").attr("disabled", true);
                        setTimeout(function () {$("#SUBMIT .k-grid-SUBMIT").attr("disabled", false);}, 5000);
                        if(!submitFlag) { return; } submitFlag = false;
                        outSure(formValidator); submitFlag = true;
                    }
                }]
            },
            dataBound: function (e) {
                let grid = e.sender;
                let trs = grid.table.find("tr");
                $.each(trs, function (i, tr) {
                    let stockNum = e.sender.dataItems()[i].stockNum;
                    let num = e.sender.dataItems()[i].num;
                    let outNum = e.sender.dataItems()[i].outNum;
                    if ((num>0 && num !=outNum && num-stockNum >0  )) {
                        tr.style.background = "#FF6347"
                    }
                });
            }
        }
    }

    // IPLATUI.EFGrid = new WilpGrid({
    //     showPage: false,
    //     toolbar: {
    //         hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
    //         add: false,cancel: false,save: false,'delete': false,
    //         buttons:[{
    //             name: "SUBMIT",text: "出库确认",layout: "3",
    //             click: function () {
    //                 // 防止连续提交
    //                 $("#SUBMIT .k-grid-SUBMIT").attr("disabled", true);
    //                 setTimeout(function () {$("#SUBMIT .k-grid-SUBMIT").attr("disabled", false);}, 5000);
    //                 if(!submitFlag) { return; } submitFlag = false;
    //                 outSure(formValidator); submitFlag = true;
    //             }
    //         }]
    //     },
    // }).buildToolBarGrid();

});

/**
 * 出库确认
 * @param formValidator
 */
function outSure(formValidator) {
    if(formValidator.validate()) {
        let eiInfo = new EiInfo();
        eiInfo.setByNode("inqu");

        //let list = resultGrid.getDataItems();
        let list = resultGrid.getCheckedRows();
        if(!list || list.length == 0) {
            IPLAT.NotificationUtil("请勾选需要出库的物资", "error");
            return;
        }
        //出库数量校验
        if(!validateNum(list)) { return; }
        eiInfo.set("token", eiInfo.get("inqu_status-0-claimNo") + eiInfo.get("inqu_status-0-statusCode"))
        eiInfo.set("list", list);

        //调用后台保存方法
        EiCommunicator.send("RMLY0203", "outStock", eiInfo, {
            onSuccess: function (ei) {
                if (ei.getStatus() == -1) {
                    IPLAT.NotificationUtil(ei.getMsg(), "error");
                    return;
                }
                NotificationUtil("出库成功", "success");
                if (window.parent['MatCKPopDataWindow'] != null){
                    window.parent['MatCKPopDataWindow'].close();
                    window.parent.parent['detailPopDataWindow'].close();
                    window.parent.parent['resultGrid'].dataSource.page(1);
                }else{
                    window.parent['detailPopDataWindow'].close();
                    window.parent['resultGrid'].dataSource.page(1);
                }
                //弹出打印出库单
                //window.parent.printOutBill(ei.get("outBillNo"))
            },
            onFail: function (errorMsg, status, e) {
                NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
            }
        });
    }
}

/**
 * 出库数量校验
 * @param list
 */
function validateNum(list) {
    for(let item of list) {
        //领用数量
        let num = Number(item['num']);
        //库存数量
        let stockNum = Number(item['stockNum']);
        //已出库数量
        let outNum = Number(item['outNum']);
        //本次出库数量
        let outAmount = Number(item['outAmount']) == NaN ? 0 : Number(item['outAmount']);
        if(num - outNum - outAmount < 0) {
            NotificationUtil(`${item.matName}的物资本次出库数量超过剩余可出库数量`, "error");
            return false;
        }

        if(stockNum - outAmount < 0) {
            NotificationUtil(`${item.matName}的物资库存数量不足`, "error");
            return false;
        }
    }
    return true;
}



