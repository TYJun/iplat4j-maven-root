$(function () {
    // 树结构初始化赋值
    IPLAT.EFTreeInput.setAllFields( $("#inqu_status-0-parentId") , __ei.parentId,__ei.parentName);
    // 保存方法
    $("#SAVE").on("click", function (e) {
        // 参数校验
        if ($("#inqu_status-0-typeName").val() == ""){
            NotificationUtil("请填写类别名称", "warning")
            return
        }
        if ($("#inqu_status-0-parentId").val() == ""){
            NotificationUtil("请选择上级类别", "warning")
            return
        }
        if ($("#inqu_status-0-useYears").val() == ""){
            NotificationUtil("请输入使用年限", "warning")
            return
        }
        if (isNaN($("#inqu_status-0-useYears").val())){
            NotificationUtil("使用年限请输入数字", "warning")
            return
        }
        if ($("#inqu_status-0-sortNo").val() == ""){
            NotificationUtil("请填写排序", "warning")
            return
        }
        // 获取参数
        var eiInfo = new EiInfo();
        eiInfo.setByNode("info");
        // 固资类别录入提交
        EiCommunicator.send("FALB0101", "saveAessettypeInfo", eiInfo, {
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