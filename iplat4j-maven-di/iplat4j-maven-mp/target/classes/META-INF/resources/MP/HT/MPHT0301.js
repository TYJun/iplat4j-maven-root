$(function () {
    let validator = IPLAT.Validator({id: "inqu"});

    /**下拉框选择事件**/
    IPLATUI.EFSelect = {
        "inqu_status-0-contId": {
            select: function (e) {
                let dataItem = e.dataItem;
                let contArray = dataItem['textField'].split("-");
                $("#inqu_status-0-contNo").val(contArray[1]);
            }
        },
        "inqu_status-0-payWay": {
            select: function (e) { $("#inqu_status-0-payWayName").val(e.dataItem['textField']); }
        },
    };

    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        showPage: false,
        toolbar: 'see' == __ei.type ? undefined : {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [{
                name: "ADD", text: "选择发票", layout: "3",
                click: function () {
                    let contNo = $("#inqu_status-0-contNo").val();
                    if(contNo) {
                        popData("MPHT0302?inqu_status-0-contNo=" + contNo);
                    } else {
                        IPLAT.NotificationUtil("请选择合同", "warning")
                    }
                }
            }, {
                name: "DEL", text: "删除", layout: "3",
                click: function () {
                    let checkRows = resultGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        resultGrid.removeRows(checkRows);
                    } else {
                        IPLAT.NotificationUtil("请选择需要删除的数据", "warning")
                    }
                }
            }, {
                name: "SAVE", text: "保存", layout: "3",
                click: function () {
                    save(validator);
                }
            }]
        }
    }).buildToolBarGrid();
});

/**
 * 保存付款信息
 * @param validator
 */
function save(validator) {
    $("#SAVE").attr("disabled", true);
    setTimeout(function () {$("#SAVE").attr("disabled", false);}, 5000);

    if (!validator.validate()) {
        NotificationUtil("必填参数不能为空", "error");
    } else {
        //参数处理
        let eiInfo = new EiInfo();
        eiInfo.setByNode("inqu");
        //处理明细
        let rows = resultGrid.getDataItems();
        if(!rows || rows.length == 0) {
            NotificationUtil("发票信息不能为空", "error");
            return;
        }
        let invoiceIds = [];
        rows.map(row => {
            if(row.id) {
                invoiceIds.push(row.id);
            } else {
                invoiceIds.push(row.invoiceId);
            }
        });
        eiInfo.set("list", invoiceIds);
        //调用后台保存方法
        EiCommunicator.send("MPHT0301", "save", eiInfo, {
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
    checkRows.forEach(row => {
        row['invoiceId'] = row['id'];
    })
    distinctGridAdd("result", checkRows, undefined, "id");
}
