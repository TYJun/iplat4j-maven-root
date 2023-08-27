$(function() {

    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        showPage : false, showExport : true,
        toolbar: "see" == __ei.type ? undefined : {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false,cancel: false,save: false,'delete': false,
            buttons: [{name: "SURE",text: "物资选择",layout: "3",
                click: function (){
                    let url = "MPDD0101?inqu_status-0-contId="+ __eiInfo.get("inqu_status-0-contId") +"&type=edit";
                    popData(url);
                }
            },{
                name: "SAVE",text: "保存",layout: "3",
                click: function (){ save()}
            }]
        }
    }).buildToolBarGrid();
});

/**
 * 保存
 */
function save() {
    // 防止连续提交
    $("#SAVE").attr("disabled", true);
    setTimeout(function () {$("#SAVE").attr("disabled", false);}, 5000);

    //参数处理
    let eiInfo = new EiInfo();
    eiInfo.setByNode("inqu");
    let rows = resultGrid.getDataItems();
    eiInfo.set("list", rows);

    //调用后台保存方法
    EiCommunicator.send("MPDD0102", "updateOrder", eiInfo, {
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
    checkRows.forEach(row => {
        row['contNum'] = row['num'];
        row['num'] = row['number'];
    })
    distinctGridAdd("result", checkRows, undefined, "matNum", "contNo");
}