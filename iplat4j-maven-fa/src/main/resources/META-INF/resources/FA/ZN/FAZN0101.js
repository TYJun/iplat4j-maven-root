$(function () {
    // 树结构初始化赋值
    IPLAT.EFTreeInput.setAllFields( $("#inqu_status-0-parentId") , __ei.parentId,__ei.parentName);
    // 保存方法
    $("#SAVE").on("click", function (e) {
        if ($("#info-0-parentId").val() == ""){
            NotificationUtil("请选择归口科室", "warning")
            return
        }
        if ($("#info-0-deptNum").val() == ""){
            NotificationUtil("请选择下属科室", "warning")
            return
        }
        // 获取参数
        var eiInfo = new EiInfo();
        eiInfo.setByNode("info");
        // 固资类别录入提交
        EiCommunicator.send("FAZN0101", "saveDeptInfo", eiInfo, {
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