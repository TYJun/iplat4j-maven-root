$(function() {

    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({detail: false, add: false, edit: false, del: false}).buildGrid();





    $.extend(true, IPLATUI.Config, { //全局配置
        EFGrid: {
            pageable: {
                pageSize: 50, // 每页显示的条数，优先级低于 DataSource 中的 pageSize
                pageSizes: [10, 20, 50, 100, 500]
            }
        }
    })

    /**库存不足时变红**/
    IPLATUI.EFGrid = {
        "result" : {
            dataBound: function (e) {
                let grid = e.sender;
                let trs = grid.table.find("tr");
                $.each(trs, function (i, tr) {
                    let stockNum = e.sender.dataItems()[i].stockNum;
                    let num = e.sender.dataItems()[i].num;
                    let outNum = e.sender.dataItems()[i].outNum;
                    if ((num>0 && num !=outNum && num-stockNum >0  )) {
                        tr.style.background = "#FF6347"
                    }
                });
            }
        }
    }
});