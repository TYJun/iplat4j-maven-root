$(function() {
    let  formValidator = IPLAT.Validator({id: "inqu"}); let submitFlag = true;

    $("#recCreator").val(__eiInfo.get("inqu_status-0-recCreator"));
    $("#recCreatorName").val(__eiInfo.get("inqu_status-0-recCreatorName"));
    /**数据回显**/
    if("add" != __ei.type) {
        $("#deptPrincipal").val(__eiInfo.get("inqu_status-0-deptPrincipal"));
        $("#deptPrincipalName").val(__eiInfo.get("inqu_status-0-deptPrincipalName"));
    }

    /** 申请人自动补全 */
    $("#recCreatorName").kendoAutoComplete({
        dataSource: getDataSources("RMTY01/selectPerson", "person",[{name:'name',id:"recCreatorName"}]),
        dataTextField: "name",
        filter:"contains",
        template:'<span>#:name#-#:workNo#</span>',
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#recCreator").val(dataItem.workNo);
        }
    });

    /** 负责人自动补全 */
    $("#deptPrincipalName").kendoAutoComplete({
        dataSource: getDataSources("RMTY01/selectPerson", "person",[{name:'name',id:"deptPrincipalName"}]),
        dataTextField: "name",
        filter:"contains",
        template:'<span>#:name#-#:workNo#</span>',
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#deptPrincipal").val(dataItem.workNo);
        }
    });

    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        showPage : false,
        toolbar: 'see'==__ei.type ? undefined : {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false,cancel: false,save: false,'delete': false,
            buttons:[{
                name: "COMMON_CHOOSE",text: "常用物资选择",layout:"3",
                click: function () { popData("RMPZ0203"); }
            },{
                name: "CHOOSE",text: "选择物资",layout:"3",
                click: function () { popData("RMPZ0202"); }
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
                    save(formValidator, "N"); submitFlag = true;
                }
            },{
                name: "SAVE_AND_SUBMIT",text: "保存并提交",layout: "3",
                click: function () {
                    // 防止连续提交
                    $("#SAVE_AND_SUBMIT .k-grid-SAVE_AND_SUBMIT").attr("disabled", true);
                    setTimeout(function () {$("#SAVE_AND_SUBMIT .k-grid-SAVE_AND_SUBMIT").attr("disabled", false);}, 5000);
                    if(!submitFlag) { return; } submitFlag = false;
                    save(formValidator, "Y"); submitFlag = true;
                }
            }]
        },
    }).buildToolBarGrid();
});

/**
 * 保存临时需求计划
 * @param formValidator
 * @param submitFlag
 */
function save(formValidator, submitFlag) {
    if(formValidator.validate()) {
        //参数处理
        let eiInfo = new EiInfo();
        eiInfo.setByNode("inqu");
        eiInfo.set("submitFlag",submitFlag);
        eiInfo.set("inqu_status-0-recCreator", eiInfo.get("recCreator"));
        eiInfo.set("inqu_status-0-recCreatorName", eiInfo.get("recCreatorName"));
        eiInfo.set("inqu_status-0-deptPrincipal", eiInfo.get("deptPrincipal"));
        eiInfo.set("inqu_status-0-deptPrincipalName", eiInfo.get("deptPrincipalName"));

        //校验物资列表
        let list = resultGrid.getDataItems();
        if(!list || list.length == 0) {
            NotificationUtil("需求明细列表不能为空", "error");
        }
        eiInfo.set("detailList", list);

        //调用后台保存方法
        EiCommunicator.send("RMXQ0301", "save", eiInfo, {
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
    } else {
        IPLAT.NotificationUtil("必填参数不能为空", "warning")
    }
}

/**
 * 物资选择数据返回
 * @param checkRows
 */
function addRows(checkRows) {
    distinctGridAdd("result", false, checkRows, ["num"],"matNum");
}