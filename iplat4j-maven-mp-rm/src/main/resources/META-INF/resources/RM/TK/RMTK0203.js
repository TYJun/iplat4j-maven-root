$(function() {
    /** 科室自动补全 */
    $("#inqu_status-0-deptName").kendoAutoComplete({
        dataSource: getDataSources("MPTY01/selectDept", "dept",[{name:'deptName',id:"inqu_status-0-deptName"}]),
        dataTextField: "deptName",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#inqu_status-0-deptNum").val(dataItem.deptNum);
        }
    });

    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        showPage : false,
        toolbar: "see" == __ei.type ? undefined : {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false,cancel: false,save: false,'delete': false,
            buttons:[{
                name: "COMMON_CHOOSE",text: "领用明细选择",layout:"3",
                click: function () {
                    if($("#inqu_status-0-deptNum").val()) {
                        let url = "RMTK0102?inqu_status-0-deptName="+ $("#inqu_status-0-deptName").val()
                            + "&inqu_status-0-deptNum=" + $("#inqu_status-0-deptNum").val()
                        popData(url);
                    } else {
                        IPLAT.NotificationUtil("请选择退库科室", "warning")
                    }
                }
            },{
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
                    $("#SAVE").attr("disabled", true);
                    setTimeout(function () {$("#SAVE").attr("disabled", false);}, 5000);
                    save("30");
                }
            }]
        },
    }).buildToolBarGrid();
});

/**
 * 保存
 * @param submitFlag
 */
function save(statusCode) {
    //参数处理
    let eiInfo = new EiInfo();
    eiInfo.setByNode("inqu");
    eiInfo.set("inqu_status-0-statusCode", statusCode)
    //校验物资列表
    let list = resultGrid.getDataItems();
    if(!list || list.length == 0) {
        NotificationUtil("退库明细列表不能为空", "error");
    }

    //校验数量
    if(!validateBackOutNum(list)) {
        NotificationUtil("退库数量不能大于实际领用数量", "error");
        return;
    }
    eiInfo.set("list", list);

    //调用后台保存方法
    EiCommunicator.send("RMTK0101", "save", eiInfo, {
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

/**
 * 物资选择数据返回
 * @param checkRows
 */
function addRows(checkRows) {
    checkRows.map(row => {
        row["num"] = row["backOutNum"] ?? row["outNum"];
        row["actualClaimNum"] =  row["outNum"];
        row["outNum"] = 0
    })
    distinctGridAdd("result", false, checkRows, undefined,"matNum", "matTypeId");
}

function resetCostDept(deptNum, deptName) {
    $("#inqu_status-0-costDeptNum").val(deptNum);
    $("#inqu_status-0-costDeptName").val(deptName);
}


/**
 * 退库数量校验
 * @param list
 * @returns {boolean}
 */
function validateBackOutNum(list) {
    for(let row of list) {
        let actualClaimNum = Number(row['actualClaimNum']);
        let num = Number(row['num']) == NaN ? 0 : Number(row['num']);
        if(num - actualClaimNum > 0) {
            return false;
        }
    }
    return true;
}