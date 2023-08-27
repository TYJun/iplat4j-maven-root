$(function() {
    var contNo = '';
    var contName = '';
    var contId = '';
    IPLATUI.EFTree = {
        "tree01": {
            ROOT: {label: "root", text: "合同名称", leaf: true},
            select: function(e) {
                let _data = this.dataItem(e.node);
                $("#inqu_status-0-contId").val(_data.label);
                $("#inqu_status-0-supplierNum").val(_data.supplierNum);
                $("#inqu_status-0-supplierName").val(_data.supplierName);
                $("#inqu_status-0-contNo").val(_data.code);
                $("#inqu_status-0-contName").val(_data.name);
                contNo = _data.code;
                contName = _data.name;
                contId = _data.label;
                resultGrid.dataSource.page(1);
            },
        }
    };

    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        showPage : false,
        toolbar: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false,cancel: false,save: false,'delete': false,
            buttons: buildButton()
        }
    }).buildToolBarGrid();

    /**查询**/
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        if('edit'== __ei.type) {
            $("#inqu_status-0-supplierNum").val(__eiInfo.get("inqu_status-0-supplierNum"));
            $("#inqu_status-0-supplierName").val(__eiInfo.get("inqu_status-0-supplierName"));
        }
        $("#inqu_status-0-contNo").val(contNo);
        $("#inqu_status-0-contName").val(contName);
        $("#inqu_status-0-contId").val(contId);
        resultGrid.dataSource.page(1);
    });
});

/**
 * 构建按钮
 */
function buildButton() {
    let buttons = [];
    if("add" == __ei.type) {
        buttons.push({name: "SAVE",text: "保存",layout: "3", click: function () {save()}})
    } else {
        buttons.push({name: "SURE",text: "确定",layout: "3", click: function (){
                let list = resultGrid.getCheckedRows();
                if(list.length == 0) {
                    NotificationUtil("请选择物资信息", "error");
                    return;
                }
                window.parent.addRows(list);
                window.parent['popDataWindow'].close();
        }})
    }
    return buttons;
}

/**
 * 新增采购订单
 */
function save() {
    // 防止连续提交
    $("#SAVE").attr("disabled", true);
    setTimeout(function () {$("#SAVE").attr("disabled", false);}, 5000);

    //参数处理
    let eiInfo = new EiInfo();
    eiInfo.setByNode("inqu");
    let list = resultGrid.getCheckedRows();
    if(!list || list.length == 0) {
        NotificationUtil("请选择物资信息", "error");
        return;
    }
    list.forEach(row => {
        row['contNum'] = row['num'];
        row['num'] = row['number'];
    })
    eiInfo.set("list", list);

    //调用后台保存方法
    EiCommunicator.send("MPDD0101", "addOrder", eiInfo, {
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