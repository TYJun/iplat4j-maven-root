$(function () {
    // 打印
    $("#PRINT").on("click", function(e) {
        console.log("打印申请表");
        var eiInfo = new EiInfo();
        var manId = IPLAT.EFInput.value($('#id'));
        window.location.href = IPLATUI.CONTEXT_PATH + "/web/DMRZ0104?initLoad&manId=" + manId;
    });
});