/*********************************************************维修流程图（开始）************************************************/




/***************************************************kendoAutoComplete自动补全（开始）******************************************/

/**
 * kendoAutoComplete的dataSource配置
 * @param url : 请求接口路径
 * @param blockId : 返回EiInfo的EiBlockId
 * @param selector : *[] 选择器ID数组
 * @param params : *[] 参数名称数组
 * @returns {*}
 */
function dataSourceConfig(url,blockId, selector=[], params=[]){
    return new kendo.data.DataSource({
        serverFiltering: true,//每次输入重新获取数据; false:初始获取数据后,就不再重新获取
        transport: {
            read: {
                url:IPLATUI.CONTEXT_PATH + url,
                type: 'POST',
                dataType: "json",
                contentType: "application/json"
            },
            parameterMap: function (options, operation) {
                let info = new EiInfo();
                if(selector.length > 0){
                    for(let index in selector){
                        info.set(params[index] === undefined ? selector[index] : params[index], $("#"+selector[index]).val());
                    }
                }
                return  info.toJSONString();
            }
        },
        schema: {
            data: function (response) {
                ajaxEi = EiInfo.parseJSONObject(response);
                let block = ajaxEi.getBlock(blockId);
                let array = new Array();
                for(let index in  block.getRows()){
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

