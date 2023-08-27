$(function () {
    // 树结构初始化赋值
    IPLAT.EFTreeInput.setAllFields( $("#inqu_status-0-parentId") , __ei.parentId,__ei.parentName);
    // 保存方法
    $("#SAVE").on("click", function (e) {
        // 参数校验
        if ($("#inqu_status-0-classifyName").val() == ""){
            NotificationUtil("请填写分类名称", "error")
            return
        }
        if ($("#inqu_status-0-parentId").val() == ""){
            NotificationUtil("请选择上级类别", "error")
            return
        }
        // 获取参数
        var eiInfo = new EiInfo();
        eiInfo.setByNode("info");
        // 固资类别录入提交
        EiCommunicator.send("ITFL0101", "saveItClassifyInfo", eiInfo, {
            onSuccess : function(ei) {
                closeParentWindow()
                window.parent.location.reload()
            }
        })
    });

    $("#CLOSE").on("click", function (e) {
        closeParentWindow()
    });
})

function closeParentWindow() {
    window.parent['popDataWindow'].close();
}