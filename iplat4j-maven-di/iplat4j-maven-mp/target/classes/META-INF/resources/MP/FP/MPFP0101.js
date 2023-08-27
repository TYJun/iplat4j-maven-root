$(function() {
    let  formValidator = IPLAT.Validator({id: "inqu"});

    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        showPage : false,
        toolbar: 'see' == __ei.type ? undefined : {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false,cancel: false,save: false,'delete': false,
            buttons: [{
                name: "ADD",text: "选择订单明细",layout: "3",
                click: function (){
                    let contId = $("#inqu_status-0-contId").val();
                    popData("MPFP0102?inqu_status-0-contId=" + contId);
                }
            },{
                name: "DEL",text: "删除",layout: "3",
                click: function (){
                    let checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        resultGrid.removeRows(checkRows);
                    } else {
                        IPLAT.NotificationUtil("请选择需要删除的数据", "warning")
                    }
                }
            },{
                name: "SAVE",text: "保存",layout: "3",
                click: function (){ save(formValidator);}
            }]
        }
    }).buildToolBarGrid();

    /**下拉框选择事件**/
    IPLATUI.EFSelect = {
        "inqu_status-0-contId": {
            select: function (e) {
                let dataItem = e.dataItem;
                let contArray = dataItem['textField'].split("-");
                $("#inqu_status-0-contNo").val(contArray[1]);
                $("#inqu_status-0-contName").val(contArray[0]);
                setSupplier(dataItem['valueField'])
            }
        },
        "inqu_status-0-invoiceType": {
            select: function (e) { $("#inqu_status-0-invoiceTypeName").val(e.dataItem['textField']); }
        },
       /* "inqu_status-0-supplierNum": {
            select: function (e) { $("#inqu_status-0-supplierName").val(e.dataItem['textField']); }
        },*/
        "inqu_status-0-currencyCode": {
            select: function (e) { $("#inqu_status-0-currencyCodeName").val(e.dataItem['textField']); }
        },
    };
});

/**
 * 新增采购订单
 */
function save(formValidator) {
    $("#SAVE").attr("disabled", true);
    setTimeout(function () {$("#SAVE").attr("disabled", false);}, 5000);

    if (!formValidator.validate()) {
        NotificationUtil("必填参数不能为空", "error");
    } else {
        //参数处理
        let eiInfo = new EiInfo();
        eiInfo.setByNode("inqu");
        //处理明细
        let rows = resultGrid.getDataItems();
        if(!rows || rows.length == 0) {
            NotificationUtil("发票明细信息不能为空", "error");
            return;
        }
        eiInfo.set("list", rows);
        //调用后台保存方法
        EiCommunicator.send("MPFP0101", "save", eiInfo, {
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
}

/**
 * 向表格中添加数据
 * @param checkRows
 */
function addRows (checkRows) {
    let rows = window["resultGrid"].getDataItems();
    let args = ['orderId', 'matNum', 'matTypeId'];
    if(rows && rows.length > 0) {
        for (let i = 0; i < checkRows.length; i++) {
            let model = checkRows[i],isExist = false;
            rows.forEach(e => {
                let isEqual = true;
                args.forEach(field => isEqual = isEqual && e[field] == model[field])
                isExist = isExist || isEqual;
            });
            if(!isExist) {
                window["resultGrid"].addRows(model);
            }
        }
    } else {
        window["resultGrid"].addRows(checkRows)
    }
}

/**
 * 设置开票单位
 * @param id
 */
function setSupplier(id) {
    let eiInfo = new EiInfo();
    eiInfo.set("id", id);
    //调用后台保存方法
    EiCommunicator.send("MPFP0101", "querySupplier", eiInfo, {
        onSuccess: function (ei) {
            if (ei.getStatus() == -1) {
                NotificationUtil(ei.getMsg(), "error");
                return;
            }
            let data = ei.get("data");
            $("#inqu_status-0-supplierNum").val(data['supplierNum']);
            $("#inqu_status-0-supplierName").val(data['supplierName']);
        },
        onFail: function (errorMsg, status, e) {
            NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
        }
    });
}