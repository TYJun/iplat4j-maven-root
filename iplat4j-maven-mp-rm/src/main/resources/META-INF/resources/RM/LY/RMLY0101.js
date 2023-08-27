let verify =false;
$(function() {

    /** 成本科室自动补全 */
   /* $("#costDeptName").kendoAutoComplete({
        dataSource: getDataSources("RMTY01/selectDept", "dept",[{name:'deptName',id:"costDeptName"}]),
        dataTextField: "deptName",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#costDeptNum").val(dataItem.deptNum);
        }
    });*/
    //$("#costDeptName").val(__eiInfo.get("inqu_status-0-costDeptName"));
    //$("#costDeptNum").val(__eiInfo.get("inqu_status-0-costDeptNum"));

    IPLATUI.EFSelect = {
        "inqu_status-0-costDeptNum": {
            select: function (e) {
                let dataItem = e.dataItem;
                $("#inqu_status-0-costDeptName").val(dataItem['textField']);
            }
        },
    }

    let submitFlag = true;
    if("see" == __ei.type) {
        new WilpGrid({detail: false, add: false, edit: false, del: false}).buildGrid();
    } else {
        //表格初始化处理
        IPLATUI.EFGrid = new WilpGrid({
            showPage : false,
            afterEditConfig: {isShow : true, call: function (e) {
                let grid = e.sender; let rowIndex = e.row
                let price = $.isNumeric(e.model["price"]) ? +e.model["price"] : 0;
                let num = $.isNumeric(e.model["num"]) ? +e.model["num"] : 0;
                let packfactor = $.isNumeric(e.model["packfactor"]) ? +e.model["packfactor"] : 0;
                if(packfactor > 0 && num%packfactor!=0){
                    IPLAT.alert("必须按照包装系数的倍数进行申领")
                    // IPLAT.NotificationUtil("必须按照包装系数的倍数进行申领", "warning")
                }
                grid.setCellValue(rowIndex, "claimAmount", (price*num).toFixed(2)+"");
            }},
            toolbar: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false,cancel: false,save: false,'delete': false,
                buttons:[{
                    name: "COMMON_CHOOSE",text: "常用物资选择",layout:"3",
                    click: function () { popData("RMPZ0203?inqu_status-0-isClaim=Y"); }
                },{
                    name: "CHOOSE",text: "选择物资",layout:"3",
                    click: function () { popData("RMLY0103?inqu_status-0-isClaim=Y"); }
                },{
                    name: "DEL",text: "删除",layout: "3",
                    click: function () {
                        let checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            resultGrid.removeRows(checkRows);
                        } else {
                            IPLAT.alert("请选择需要删除的数据")
                            // IPLAT.NotificationUtil("请选择需要删除的数据", "warning")
                        }
                    }
                },{
                    name: "SAVE",text: "保存",layout: "3",
                    click: function () {
                        // 防止连续提交
                        $("#SAVE .k-grid-SAVE").attr("disabled", true);
                        setTimeout(function () {$("#SAVE .k-grid-SAVE").attr("disabled", false);}, 5000);

                        if(!submitFlag) { return; } submitFlag = false;
                        save("N"); submitFlag = true;
                    }
                },{
                    name: "SAVE_AND_SUBMIT",text: "保存并提交",layout: "3",
                    click: function () {
                        // 防止连续提交
                        $("#SAVE_AND_SUBMIT .k-grid-SAVE_AND_SUBMIT").attr("disabled", true);
                        setTimeout(function () {$("#SAVE_AND_SUBMIT .k-grid-SAVE_AND_SUBMIT").attr("disabled", false);}, 5000);

                        if(!submitFlag) { return; } submitFlag = false;
                        save("Y"); submitFlag = true;
                    }
                }]
            },
        }).buildToolBarGrid();
    }

});

/**
 * 保存
 * @param submitFlag : string 提交标记,N=不提交,Y=提交
 */
function save(submitFlag) {
    //参数处理
    let eiInfo = new EiInfo();
    eiInfo.setByNode("inqu");
    eiInfo.set("submitFlag", submitFlag)
    /*if(!$("#costDeptName").val()){
        NotificationUtil("成本归集科室不能为空", "error");
        return;
    }
    eiInfo.set("inqu_status-0-costDeptNum", $("#costDeptNum").val());
    eiInfo.set("inqu_status-0-costDeptName", $("#costDeptName").val());*/
    //校验物资列表
    let list = resultGrid.getDataItems();
    if(!list || list.length == 0) {
        IPLAT.alert("领用明细列表不能为空")
        // IPLAT.NotificationUtil("领用明细列表不能为空", "warning");
        return;
    }

    for(data in list){
        let num = list[data].num;
        let claimAmount = list[data].claimAmount;
        if(!isNaN(parseFloat(num)) && isFinite(num)){}else {
            IPLAT.alert("领用数量和领用金额(元)只能为数字")
            // IPLAT.NotificationUtil("领用数量和领用金额(元)只能为数字", "warning");
            return;
        }
        if(!isNaN(parseFloat(claimAmount)) && isFinite(claimAmount)){}else {
            IPLAT.alert("领用数量和领用金额(元)只能为数字")
            // IPLAT.NotificationUtil("领用数量和领用金额(元)只能为数字", "warning");
            return;
        }
    }


    eiInfo.set("detailList", list);
     if(Inspection(list)){
         IPLAT.alert("必须按照包装系数的倍数进行申领")
         // IPLAT.NotificationUtil("必须按照包装系数的倍数进行申领", "warning")
         return;
     }
    //调用后台保存方法
    EiCommunicator.send("RMLY0101", "save", eiInfo, {
        onSuccess: function (ei) {
            if (ei.getStatus() == -1) {
                IPLAT.alert(ei.getMsg());
                // IPLAT.NotificationUtil(ei.getMsg(), "error");
                return;
            }
            IPLAT.NotificationUtil("保存成功", "success");
            window.parent['popDataWindow'].close();
            window.parent['resultGrid'].dataSource.page(1);
        },
        onFail: function (errorMsg, status, e) {
            IPLAT.alert("保存失败，原因[" + errorMsg + "]");
            // IPLAT.NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
        }
    });
}

function Inspection(list) {
    for(let i = 0; i < list.length;i++){
        let num = $.isNumeric(list[i].num) ? +list[i].num : 0;
        let  packfactor= $.isNumeric(list[i].packfactor) ? +list[i].packfactor : 0;
        if(packfactor>0&&num%packfactor!=0){
           return true;
        }
    }
}

/**
 * 物资选择数据返回
 * @param checkRows
 */
function addRows(checkRows) {
    distinctGridAdd("result", false, checkRows, ["num", "claimAmount"],"matNum");
}


