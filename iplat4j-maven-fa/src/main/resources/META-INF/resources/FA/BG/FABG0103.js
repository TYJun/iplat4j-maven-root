$(function () {
    if ("edit" == $("#type").val() || "look" == $("#type").val()) {
        $("#newBuild").val(__ei.build);
        $("#newFloor").val(__ei.floor);
        $("#newGoodsLocation").val(__ei.installLocation);
        $("#newGoodsLocationNum").val(__ei.installLocationNum);
    }

    $("#APPROVAL").on("click", function (e) {
        var eiInfo = new EiInfo();
        eiInfo.set("faInfoId",$("#faInfoId").val());
        // 固资类别录入提交
        EiCommunicator.send("FABG01", "approval", eiInfo, {
            onSuccess: function (ei) {
                closeParentWindow()
            }
        })
    });

    $("#CLOSE").on("click", function (e) {
        closeParentWindow()
    });

    IPLATUI.EFGrid = {
        "resultModificationRecord": {
            pageable: false,
            exportGrid: false,
            loadComplete: function (e) {

            }
        },
    }
});


function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultGrid"].dataSource.page(1);
}