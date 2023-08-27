$(function() {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        showPage : false,
        toolbar: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false,cancel: false,save: false,'delete': false,
            buttons: [{
                name: "SAVE",text: "保存",layout: "3",
                click: function (){ save()}
            }]
        }
    }).buildToolBarGrid();


});

/**
 * 生成采购计划
 */
function save() {
    $("#SAVE").attr("disabled", true);
    setTimeout(function () {$("#SAVE").attr("disabled", false);}, 5000);

    //参数处理
    let eiInfo = new EiInfo();
    eiInfo.setByNode("inqu");
    //获取表格数据
    let rows = resultGrid.getDataItems();
    if(rows.length < 1) {  NotificationUtil("采购计划明细不能为空", "error"); }

    //保存
    eiInfo.set("list", rows);
    //调用后台保存方法
    EiCommunicator.send("MPHZ0102", "genPurchasePlan", eiInfo, {
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