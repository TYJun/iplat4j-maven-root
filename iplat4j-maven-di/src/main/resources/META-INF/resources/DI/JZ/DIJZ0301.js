$(function() {
    $("#SUMBMIT").click(function() {
        var configureNum = IPLAT.EFInput.value($("#configureName"));
        var configureName = IPLAT.EFInput.value($("#configureName"));
        var configureTime = IPLAT.EFInput.value($("#configureTime"));
        var inInfo = new EiInfo();
        inInfo.set("configureNum", configureNum);
        inInfo.set("configureName", configureName);
        inInfo.set("configureTime", configureTime);
        console.log(configureName)
        if (configureName == null || configureName == "") {
            IPLAT.alert("请输入要配置的巡检基准")
        } else if(configureTime == null || configureTime == ""){
            IPLAT.alert("请输入所需要的时间")
        }else {
            EiCommunicator.send("DIJZ0301", "insert", inInfo, {
                onSuccess : function(ei) {
                    NotificationUtil("新增成功", "");
                    window.parent.resultGrid.dataSource.page(1);
                    //关闭弹窗
                    window.parent['popDataWindow'].close();
                }
            });
        }
    });
});