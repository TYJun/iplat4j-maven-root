$(function () {
    var validator = IPLAT.Validator({
        id: "result"
    });

    $("#EXCUTE").on("click", function (e) {
        if (validator.validate()) {
            var eiInfo = new EiInfo();
            var nodeId = IPLAT.EFInput.value($("#nodeId"));
            var nodeName = IPLAT.EFInput.value($("#nodeNames"));
            var nodeRemark = IPLAT.EFInput.value($("#nodeRemark"));
            eiInfo.set("nodeId",nodeId);
            eiInfo.set("nodeName",nodeName);
            eiInfo.set("nodeRemark",nodeRemark);
            nodeClose();
            EiCommunicator.send("CMJD0101", "save", eiInfo, {
                onSuccess: function (ei) {
                    NotificationUtil(ei.getMsg(), "success");
                    window.parent["resultGrid"].dataSource.page(1);
                }
            });
        }
    });

    $("#CANCEL").on("click", function (e) {
        nodeClose();
    });
});

function nodeClose(){
    window.parent['nodeWindow'].close();
}