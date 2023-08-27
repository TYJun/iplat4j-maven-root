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
    IPLATUI.EFGrid = new WilpGrid({
        showPage : false,
        toolbar: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false,cancel: false,save: false,'delete': false,
            buttons: [{name: "DEL",text: "删除",layout: "3",
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
                click: function (){ save()}
            }]
        }
    }).buildToolBarGrid();
    $.extend(true, IPLATUI.Config, { //全局配置
        EFGrid: {
            columns: [{
                field:"curEnterNum",
                // template:"#=curEnterNum#",
                template:function (item) {
                    var num=item['num']-item['enterNum']
                if(item['curEnterNum']=='0.00'){
                    item['curEnterNum']=Number(num).toFixed(2)
                   return Number(num).toFixed(2)
                }
                if(item['curEnterNum']==Number(num).toFixed(2)){
                    return item['curEnterNum']
                }
                if(item['curEnterNum']!=Number(num).toFixed(2)){
                    return item['curEnterNum']
                }

                }

            }]


        }
    })
});

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

    let list = resultGrid.getDataItems();
    if(!list || list.length == 0) {
        NotificationUtil("入库明细不能为空", "error");
        return;
    }
    eiInfo.set("list", list);

    //调用后台保存方法
    EiCommunicator.send("MPDD0201", "enterStock", eiInfo, {
        onSuccess: function (ei) {
            if (ei.getStatus() == -1) {
                NotificationUtil(ei.getMsg(), "error");
                return;
            }

            NotificationUtil("入库成功", "success");
            window.parent['popDataWindow'].close();
            window.parent['resultGrid'].dataSource.page(1);
        },
        onFail: function (errorMsg, status, e) {
            NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
        }
    });
}