$(function () {
    if ("look" == $("#type").val()){
        $("#info-0-deptName").val(__ei.deptName);
        $("#newBuild").val(__ei.newBuild);
        $("#newFloor").val(__ei.newFloor);
        $("#newGoodsLocationNum").val(__ei.newGoodsLocationNum);
        $("#newGoodsLocation").val(__ei.newGoodsLocation);
        $("#tab").show();
    }

    IPLATUI.EFGrid = {
        "resultInventoryDetail": {
            pageable: false,
            exportGrid: false,
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
            }
        },
        loadComplete:function (e) {

        }
    }
    // -------------------自定义自动补全--------------
    /** 楼自动补全 */
    $("#newBuild").kendoAutoComplete({
        // 以building为参数，调用FADA01/selectSpotBuildingName本地服务进行查询，将返回的building作为第二个参数。
        dataSource: dataSourceConfig("/service/FADA01/selectSpotBuildingName", "building", ["newBuild"]),
        // blockId 下的属性 building
        dataTextField: "building",
        filter:"contains",
        minLength :0,
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#newBuild").val(dataItem.building)
            $("#info-0-newBuild").val(dataItem.building)
        },
        change:function (e){
            if ($("#info-0-newBuild").val() != "" && $("#info-0-newBuild").val() != $("#newBuild").val()){
                $("#newBuild").val("");
                $("#newFloor").val("");
                $("#newGoodsLocationNum").val("");
                $("#newGoodsLocation").val("")
                $("#info-0-newBuild").val("")
                $("#info-0-newFloor").val("")
                $("#info-0-newGoodsLocationNum").val("")
                $("#info-0-newGoodsLocation").val("")
            }
        },
    });

    /** 层自动补全 */
    $("#newFloor").kendoAutoComplete({
        // 以building和floor为参数，调用FADA01/selectSpotFloorName本地服务进行查询，将返回的floor作为第二个参数。
        dataSource: dataSourceConfig("/service/FADA01/selectSpotFloorName", "floor",["newBuild","newFloor"]),
        // blockId 下的属性 floor
        dataTextField: "floor",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#newFloor").val(dataItem.floor)
            $("#info-0-newFloor").val(dataItem.floor)
        },
        change:function (e){
            if ($("#info-0-newFloor").val() != "" && $("#info-0-newFloor").val() != $("#newFloor").val()){
                $("#newFloor").val("")
                $("#newGoodsLocationNum").val("")
                $("#newGoodsLocation").val("")
                $("#info-0-newFloor").val("")
                $("#info-0-newGoodsLocationNum").val("")
                $("#info-0-newGoodsLocation").val("")
            }
        },
    });

    /** 地点自动补全 */
    $("#newGoodsLocation").kendoAutoComplete({
        // 以building和floor和reqSpotName为参数，调用FADA01/selectSpotRoomName本地服务进行查询，将返回的room作为第二个参数。
        dataSource: dataSourceConfig("/service/FADA01/selectSpotRoomName", "room",["newBuild","newFloor","newGoodsLocation"]),
        // blockId 下的属性 spotName
        dataTextField: "spotName",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index())
            $("#newGoodsLocationNum").val(dataItem.spotNum)
            $("#newGoodsLocation").val(dataItem.spotName);
            $("#info-0-newGoodsLocationNum").val(dataItem.spotNum)
            $("#info-0-newGoodsLocation").val(dataItem.spotName)
        },
        change:function (e){
            if ($("#info-0-newGoodsLocation").val() != "" && $("#info-0-newGoodsLocation").val() != $("#newGoodsLocation").val()){
                $("#newGoodsLocationNum").val("");
                $("#newGoodsLocation").val("")
                $("#info-0-newGoodsLocationNum").val("")
                $("#info-0-newGoodsLocation").val("")
            }
        },
    });

// ---------------------------------------------

    $("#CLOSE").on("click", function (e) {
        closeParentWindow()
    });


})

function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultGrid"].dataSource.page(1);
}

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

// 显示所有，自动补齐
function showAll(selector){
    var autocomplete = $("#"+selector).data("kendoAutoComplete");
    autocomplete.search("");
}