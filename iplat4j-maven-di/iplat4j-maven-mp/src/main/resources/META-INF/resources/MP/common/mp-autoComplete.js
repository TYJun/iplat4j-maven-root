/**
 * 获取数据源
 * @param url : string URL
 * @param blockId : string block块ID
 * @param params : Array 参数对象
 * @returns {*}
 */
function getDataSources(url, blockId, params) {
    return new kendo.data.DataSource({
        serverFiltering: true,//每次输入重新获取数据; false:初始获取数据后,就不再重新获取
        transport: {
            read: {
                url:IPLATUI.CONTEXT_PATH + "/service/"+ url,
                type: 'POST',
                dataType: "json",
                contentType: "application/json"
            },
            parameterMap: function (options, operation) {
                let info = new EiInfo();
                //遍历参数
                params.forEach(e => {
                    info.set(e.name, e.value ? e.value : $("#"+e.id).val());
                })
                return  info.toJSONString();
            }
        },
        schema: {
            data: function (response) {
                let ajaxEi = EiInfo.parseJSONObject(response);
                let block = ajaxEi.getBlock(blockId);
                let rows = block.getMappedRows();
              /*  rows.map(row => {
                    row['valueField'] = row.typeCode;
                    row['textField'] = row.typeName;
                })*/
                return rows
            }
        }
    })
}

function showAll(selector){
    let autocomplete = $("#"+selector).data("kendoAutoComplete");
    autocomplete.search("");
}