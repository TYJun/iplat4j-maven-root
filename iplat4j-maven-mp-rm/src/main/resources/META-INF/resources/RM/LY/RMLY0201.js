$(function() {
    /** 领用科室自动补全 */
    $("#inqu_status-0-deptName").kendoAutoComplete({
        dataSource: getDataSources("RMTY01/selectDept", "dept",[{name:'deptName',id:"inqu_status-0-deptName"}]),
        dataTextField: "deptName",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#inqu_status-0-deptNum").val(dataItem.deptNum);
            if(!$("#inqu_status-0-costDeptName").val()) {
                $("#inqu_status-0-costDeptName").val(dataItem.deptName);
                $("#inqu_status-0-costDeptNum").val(dataItem.deptNum);
            }
        }
    });

    /** 成本科室自动补全 */
    $("#inqu_status-0-costDeptName").kendoAutoComplete({
        dataSource: getDataSources("MPTY01/selectDept", "dept",[{name:'deptName',id:"inqu_status-0-costDeptName"}]),
        dataTextField: "deptName",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#inqu_status-0-costDeptNum").val(dataItem.deptNum);
        }
    });

    /** 领用人自动补全 */
    $("#inqu_status-0-recCreatorName").kendoAutoComplete({
        dataSource: getDataSources("RMTY01/selectPerson", "person",[{name:'name',id:"inqu_status-0-recCreatorName"}]),
        dataTextField: "name",
        filter:"contains",
        template:'<span>#:name#-#:workNo#</span>',
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#inqu_status-0-recCreator").val(dataItem.workNo);
        }
    });

    //表格初始化处理
    let  formValidator = IPLAT.Validator({id: "inqu"}); let submitFlag = true;
    IPLATUI.EFGrid = new WilpGrid({
        showPage : false,
        afterEditConfig: {isShow : true, call: function (e) {
            let grid = e.sender; let rowIndex = e.row
            let price = $.isNumeric(e.model["price"]) ? +e.model["price"] : 0;
            let num = $.isNumeric(e.model["num"]) ? +e.model["num"] : 0;
            grid.setCellValue(rowIndex, "claimAmount", (price*num).toFixed(2)+"");
        }},
        toolbar: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false,cancel: false,save: false,'delete': false,
            buttons:[{
                name: "CHOOSE",text: "选择物资",layout:"3",
                click: function () { popData("RMLY0103"); }
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
                    $("#SAVE .k-grid-SAVE").attr("disabled", true);
                    setTimeout(function () {$("#SAVE .k-grid-SAVE").attr("disabled", false);}, 5000);
                    if(!submitFlag) { return; } submitFlag = false;
                    save();submitFlag = true;

                }
            }]
        },
    }).buildToolBarGrid();
});

/**
 * 保存领用单
 */
function save() {
    //参数处理
    let eiInfo = new EiInfo();
    eiInfo.setByNode("inqu");

    //参数校验
    if(!eiInfo.get("inqu_status-0-deptName")) {
        NotificationUtil("领用科室不能为空", "error");
        return;
    }

    //参数校验
    if(!eiInfo.get("inqu_status-0-costDeptName")) {
        NotificationUtil("成本归集科室不能为空", "error");
        return;
    }

    /*if(!eiInfo.get("inqu_status-0-recCreatorName")) {
        NotificationUtil("领用人不能为空", "error");  submitFlag = true;
        return;
    }*/
    let list = resultGrid.getDataItems();
    if(!list || list.length == 0) {
        NotificationUtil("领用明细列表不能为空", "error");
        return
    }
    eiInfo.set("detailList", list);

    //调用后台保存方法
    EiCommunicator.send("RMLY0201", "save", eiInfo, {
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
    checkRows.forEach(row => row['matTypeId'] = row['matTypeNum']);
    distinctGridAdd("result", false, checkRows, ["num","claimAmount"],"matNum");
}