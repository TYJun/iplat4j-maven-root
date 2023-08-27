$(function() {
    IPLATUI.EFDatePicker = {
        "inqu_status-0-purchaseYear":{
            start: "decade",
            depth: "decade"
        }
    }

    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({}).buildGrid();

    /**查询**/
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        resultGrid.dataSource.page(1);
    });

});