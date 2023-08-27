$(function() {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        submit: true,submitConfig:{ status: ["01"], backStatus: [], serviceName: "MPFP01", methodName: "submit" }
    }).buildGrid();

    /**查询**/
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        $("#inqu_status-0-deptNum").val(__eiInfo.get("inqu_status-0-deptNum"));
        $("#inqu_status-0-deptName").val(__eiInfo.get("inqu_status-0-deptName"));
        resultGrid.dataSource.page(1);
    });

});