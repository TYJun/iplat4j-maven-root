$(function() {
    let  formValidator = IPLAT.Validator({id: "red_rush"}); let submitFlag = true;
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
                    click: function () {
                        // 防止连续提交
                        $("#SAVE .k-grid-SAVE").attr("disabled", true);
                        setTimeout(function () {$("#SAVE .k-grid-SAVE").attr("disabled", false);}, 5000);
                        if(!submitFlag) { return; } submitFlag = false;
                        save(formValidator);
                        submitFlag = true;
                    }
                }],
            },
            columns: [{field: "redRushNum", template: function (item) {
                    if ($.isNumeric(item['redRushNum']) && item['redRushNum'] !=0 ) {
                        return -Math.abs(item['redRushNum']) }
                }}
            ]
        }
    };

    /**回车键查询**/
    keydown("inqu", "QUERY");

    /**查询**/
    $("#QUERY").on("click", function(e) {
        matGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        matGrid.dataSource.page(1);
    });
})

/**
 * 物资选择数据返回
 * @param checkRows
 */
function addRows(checkRows) {
    let rows = window["resultGrid"].getDataItems();
    if(rows && rows.length > 0) {
        for (let i = 0; i < checkRows.length; i++) {
            let model = checkRows[i],isExist = false;
            rows.forEach(e => isExist = isExist || e['matNum'] == model['matNum']);
            if(!isExist) {
                model['redRushNum'] = 0
                window["resultGrid"].addRows(model);
            }
        }
    } else {
        checkRows.forEach(row => row['redRushNum'] = 0);
        window["resultGrid"].addRows(checkRows)
    }
}

/**
 * 保存
 * @param formValidator
 */
function save(formValidator) {
    if(formValidator.validate()) {
        //参数处理
        let eiInfo = new EiInfo();
        eiInfo.setByNode("red_rush");
        //校验物资列表
        let list = resultGrid.getDataItems();let newRows = new Array();
        for(let row of list){
            if($.isNumeric(row.redRushNum) && parseFloat(row.redRushNum) != 0) {
                //将数量转成正数
                row.redRushNum = Math.abs(row.redRushNum)+"";
                newRows.push(row)
            }
        }
        if(newRows.length == 0){
            NotificationUtil("请正确输入红冲数量或红冲数量不能全部为空","error");
            return;
        }
        let param = eiInfo.getBlock("red_rush").getMappedRows()[0]
        eiInfo.setAttr(param)
        eiInfo.set("outType", "05");//红冲出库
        eiInfo.set("rows", newRows);

        //调用后台保存方法
       /* EiCommunicator.send("SICK0101", "outStock", eiInfo, {
            onSuccess : function(ei) {
                if(ei.getStatus() == -1){
                    NotificationUtil(ei.getMsg(), "error");
                    return;
                }
                NotificationUtil("出库成功");
                window.parent['popDataWindow'].close();
                window.parent["resultGrid"].dataSource.page(1);
            }
        })*/
    } else {
        IPLAT.NotificationUtil("必填参数不能为空", "warning")
    }
}