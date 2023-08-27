var moneyAll = 0.00;//组合进价总额
$(function () {
    IPLATUI.EFGrid = {
        "result": {
            pageable: {
                pageSize: 50,
                pageSizes: [50, 100, 500, 1000]
            },
            loadComplete: function (e) {
                $("#ef_grid_toolbar_result").prepend("<div style='float:left;font-size:13px;'>" +
                    "组合进价总额：<span id='moneyCount' style='color: red'>0</span>元</div>")
            },
            beforeRequest: function(e) {
                // 数据源发生改变（切换条数、切换页）时，组合进价总额清零
                moneyAll = 0.00;
                $("#moneyCount").text(moneyAll);
            },
            onCheckAllRows: function (e) {
                // 全选事件会调用n次单选事件，如果先单选了几条再去全选，此时进价总额会重复计算单选的那几条
                // 要把进价总额清零，进价总额会在每次单选事件中重新计算
                if (moneyAll > 0 && e.checked) {
                    moneyAll = 0.00;
                }
            },
            onCheckRow: function (e) {
                let model = e.model;
                if (e.checked) {
                    // 每次勾选把总金额加上
                    let enterAmount = $.isNumeric(model["enterAmount"]) ? +model["enterAmount"] : 0;
                    moneyAll += enterAmount
                } else {
                    // 每次取消勾选把总金额减去
                    let enterAmount = $.isNumeric(model["enterAmount"]) ? +model["enterAmount"] : 0;
                    moneyAll -= enterAmount
                }
                // 先全选，再取消几条数据，再取消全选，此时会多扣去单独取消的那几条，最终进价总额变成负数，不符合逻辑
                // 进价总额减为负数时，直接清零
                if (moneyAll < 0) {
                    moneyAll = 0.00
                }
                $("#moneyCount").text(moneyAll.toFixed(2));
            }
        },
    }
    /**回车键查询**/
    keydown("inqu", "QUERY");

    //查询
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });

    //重置按钮
    $("#RESET").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        resultGrid.dataSource.page(1);
    });

    //打印
    $("#PRINT").on("click", function(e) {
        var rows = resultGrid.getCheckedRows();
        if( rows.length < 1 ){
            NotificationUtil("请选择一条记录", "warning");
            return;
        }
        let bill = [];
        for(let i=0;i<=rows.length-1;i++){
            bill.push(rows[i].enterBillDetailNo);
        }
        let b =bill.join("','");
        // 当前页面地址
        let pageUrl = window.location.href;
        // 获取报表地址前缀
        let baseUrl = pageUrl.split('/')[0]+"//"+pageUrl.split('/')[2]+"/";
        //小代码中的报表具体地址
        let defaultUrlList = _getEi().blocks['defaultFrUrl'].getMappedRows();
        let defaultUrl = baseUrl + defaultUrlList[0].label;
        let url = defaultUrl+'&'+'name='+loginName+'&'+'billNo='+b;
        window.open(url);
    });

    // 物资分类自动补全
    $("#matTypeName").kendoAutoComplete({
        dataSource: dataSourceConfig("/service/SIRK06/queryMatTypeBySupplier", "matType",["matTypeName","supplierName"]),
        dataTextField: "matTypeName",
        filter:"contains"
    });
})

/** kendoAutoComplete的dataSource配置*/
function dataSourceConfig(url,blockId,param){
    return new kendo.data.DataSource({
        serverFiltering: true,//每次输入重新获取数据
        transport: {
            read: {
                url:IPLATUI.CONTEXT_PATH + url,
                type: 'POST',
                dataType: "json",
                contentType: "application/json"
            },
            parameterMap: function (options, operation) {
                var info = new EiInfo();
                if(param != null){
                    for(var index in param){ info.set(param[index],$("#"+param[index]).val()); }
                }
                return  info.toJSONString();
            }
        },
        schema: {
            data: function (response) {
                ajaxEi = EiInfo.parseJSONObject(response);
                var block = ajaxEi.getBlock(blockId);
                var array = new Array();
                for(var index in  block.getRows()){
                    array.push(block.getMappedObject( block.getRows()[index]));
                }
                return array;
            }
        }
    })
}

/**
 * kendoAutoComplete查询所有数据
 * @param selector
 */
function showAll(selector){
    let autocomplete = $("#"+selector).data("kendoAutoComplete");
    autocomplete.search("");
}