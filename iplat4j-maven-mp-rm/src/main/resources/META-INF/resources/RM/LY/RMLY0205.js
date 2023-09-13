let typeCount = 0; // 计算物资种类
let addFlag = true;// afterEdit会执行两次，总价会重复计算两次，用该变量控制只计算一次
$(function() {
    /** 领用科室自动补全 */
    /*$("#add_claim-0-deptName").kendoAutoComplete({
        dataSource: getDataSources("RMTY01/queryUserDept", "dept",[{name:'deptName',id:"add_claim-0-deptName"}]),
        dataTextField: "deptName",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#add_claim-0-deptNum").val(dataItem.deptCode);
            if(!$("#add_claim-0-costDeptName").val()) {
                $("#add_claim-0-costDeptName").val(dataItem.deptName);
                $("#add_claim-0-costDeptNum").val(dataItem.deptCode);
            }else{
                if ($("#add_claim-0-deptNum").val() != $("#add_claim-0-costDeptNum").val()){
                    IPLAT.confirm({
                        message: '<b>请注意所选择的"领用科室"与"成本归集科室"不一致!</b>',
                        okFn: function (e) {
                        },
                        title: '提醒'
                    });
                }
            }
        }
    });*/

    /** 成本科室自动补全 */
    $("#add_claim-0-costDeptName").kendoAutoComplete({
        dataSource: getDataSources("RMTY01/selectBusinessDept", "b_dept",[{name:'deptName',id:"add_claim-0-costDeptName"}]),
        dataTextField: "deptName",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#add_claim-0-costDeptNum").val(dataItem['deptNum']);
            $("#add_claim-0-deptNum").val(dataItem['parentDeptNum']);
            $("#add_claim-0-deptName").val(dataItem['parentDeptName']);
            /*if ($("#add_claim-0-deptName").val()){
                if ($("#add_claim-0-costDeptNum").val() != $("#add_claim-0-deptNum").val()){
                    IPLAT.confirm({
                        message: '<b>请注意所选择的"领用科室"与"成本归集科室"不一致!</b>',
                        okFn: function (e) {
                        },
                        title: '提醒'
                    });
                }
            }*/
        }
    });

    /** 领用人自动补全 */
    $("#add_claim-0-applyUserName").kendoAutoComplete({
        dataSource: getDataSources("RMTY01/selectPerson", "person",[{name:'name',id:"add_claim-0-applyUserName"}]),
        dataTextField: "name",
        filter:"contains",
        template:'<span>#:name#-#:workNo#</span>',
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#add_claim-0-applyUserNo").val(dataItem.workNo);
        }
    });

    IPLATUI.EFSelect = {
        "add_claim-0-wareHouseNo": {
            select: function (e) { //获取勾选值
                let dataItem = e.dataItem;
                $("#add_claim-0-wareHouseName").val(dataItem['textField']);
            }
        }
    }

    /**表格初始化处理**/
    let submitFlag = true;
    IPLATUI.EFGrid = {
        "mat": {
            height:  window.innerHeight - 210,
            pageable: {pageSize: 50, pageSizes: [10, 20, 50, 100]},
            onRowDblClick: function (e) {
                let checkRows = matGrid.getCheckedRows();
                addRows(checkRows);
            },
            //onCheckRow: function (e) {if (e.checked) {}}
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
            afterEdit: function (e) {
                let grid = e.sender; let rowIndex = e.row
                let price = $.isNumeric(e.model["price"]) ? +e.model["price"] : 0;
                let num = $.isNumeric(e.model["num"]) ? +e.model["num"] : 0;
                if (addFlag){
                    let result = resultGrid.getDataItems();
                    let moneyCount = 0.00,numCount=0; // 计算总金额和总数量
                    typeCount = 0; // 物资种类计算前需清零
                    for (i in result) {
                        let price2 = $.isNumeric(result[i].price) ? +result[i].price : 0;
                        let num2 = $.isNumeric(result[i].num) ? +result[i].num : 0;
                        if (num2 !=0) typeCount++;
                        moneyCount = moneyCount + price2*num2;
                        numCount = numCount + num2;
                    }
                    $("#typeCount").text(typeCount);
                    $("#numCount").text(numCount);
                    $("#moneyCount").text(moneyCount.toFixed(2));
                    addFlag = false;
                }
                grid.setCellValue(rowIndex, "claimAmount", (price*num).toFixed(2)+"");
                addFlag = true;
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false,cancel: false,save: false,'delete': false,
                buttons:[{
                    name: "DEL",text: "删除",layout: "3",
                    click: function () {
                        let checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            resultGrid.removeRows(checkRows);
                            // 删除表格数据后，重新计算物资种类、总金额、总数量
                            let result = resultGrid.getDataItems();
                            let moneyCount = 0.00,numCount=0; // 计算总金额和总数量
                            typeCount = 0; // 物资种类计算前需清零
                            for (i in result) {
                                let price2 = $.isNumeric(result[i].price) ? +result[i].price : 0;
                                let num2 = $.isNumeric(result[i].num) ? +result[i].num : 0;
                                if (num2 !=0) typeCount++;
                                moneyCount = moneyCount + price2*num2;
                                numCount = numCount + num2;
                            }
                            $("#typeCount").text(typeCount);
                            $("#numCount").text(numCount);
                            $("#moneyCount").text(moneyCount.toFixed(2));
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
                        save("RMLY0201", "save");
                        submitFlag = true;
                    }
                },{
                    name: "SAVE_OUT",text: "保存并出库",layout: "3",
                    click: function () {
                        // 防止连续提交
                        $("#SAVE .k-grid-SAVE_OUT").attr("disabled", true);
                        setTimeout(function () {$("#SAVE .k-grid-SAVE_OUT").attr("disabled", false);}, 5000);
                        if(!submitFlag) { return; } submitFlag = false;
                        save("RMLY0205", "saveAndOutStock");
                        submitFlag = true;
                    }
                }],
            },
            loadComplete:function (e) {
                $("#ef_grid_toolbar_result").prepend("<div style='float:left;font-size:13px;'>" +
                    "物资种类：<span id='typeCount' style='color: red'>" + typeCount + "</span>类，" +
                    "领用总数：<span id='numCount' style='color: red'>0</span>件，" +
                    "领用总金额：<span id='moneyCount' style='color: red'>0</span>元</div>")
            }
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
});

/**
 * 物资选择数据返回
 * @param checkRows
 */
function addRows(checkRows) {
    checkRows.forEach(row => row['matTypeId'] = row['matTypeNum']);
    distinctGridAdd("result", true, checkRows, ["num","claimAmount"],"matNum");
}

/**
 * 保存领用单
 */
function save(serviceName, method) {
    //参数处理
    let eiInfo = new EiInfo();
    eiInfo.setByNode("add_claim");

    //参数校验
    if(!eiInfo.get("add_claim-0-deptName")) {
        IPLAT.NotificationUtil("领用科室不能为空", "error");
        return;
    }else{
        if (eiInfo.get("add_claim-0-deptNum") == null || eiInfo.get("add_claim-0-deptNum").length == 0) {
            IPLAT.NotificationUtil("请在设定好的列表中进行\"领用科室\"选择", "error");
            return;
        }
    }

    //参数校验
    if(!eiInfo.get("add_claim-0-costDeptName")) {
        IPLAT.NotificationUtil("成本归集科室不能为空", "error");
        return;
    }else{
        if (eiInfo.get("add_claim-0-costDeptNum") == null || eiInfo.get("add_claim-0-costDeptNum").length == 0) {
            IPLAT.NotificationUtil("请在设定好的列表中进行\"成本归集科室\"选择", "error");
            return false;
        }
    }

    if(serviceName == 'RMLY0205' && !eiInfo.get("add_claim-0-wareHouseNo")) {
        IPLAT.NotificationUtil("出库仓库不能为空", "error");
        return;
    }

   /* if(!eiInfo.get("add_claim-0-recCreatorName")) {
        NotificationUtil("领用人不能为空", "error");
        return;
    }*/
    let list = resultGrid.getDataItems();
    if(!list || list.length == 0) {
        IPLAT.NotificationUtil("领用明细列表不能为空", "error");
        return
    }

    for(let data in list ){
         let num = list[data].num;
         let claimAmount = list[data].claimAmount;
         if(!isNaN(parseFloat(num)) && isFinite(num)){}else {
             IPLAT.NotificationUtil("领用数量和领用金额(元)只能为数字", "error");
             return;
         }
        if(!isNaN(parseFloat(claimAmount)) && isFinite(claimAmount)){}else {
            IPLAT.NotificationUtil("领用数量和领用金额(元)只能为数字", "error");
            return;
        }
    }

    renameBlock(eiInfo, "add_claim", "inqu_status")
    eiInfo.set("detailList", list);

    //调用后台保存方法
    EiCommunicator.send(serviceName, method, eiInfo, {
        onSuccess: function (ei) {
            if (ei.getStatus() == -1) {
                IPLAT.NotificationUtil(ei.getMsg(), "error");
                return;
            }

            IPLAT.NotificationUtil("保存成功", "success");
            window.parent['popDataWindow'].close();
            window.parent['resultGrid'].dataSource.page(1);
        },
        onFail: function (errorMsg, status, e) {
            IPLAT.NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
        }
    });
}