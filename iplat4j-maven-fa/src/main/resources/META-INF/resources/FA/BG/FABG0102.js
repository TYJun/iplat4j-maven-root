$(function () {
    if ("edit" == $("#type").val() || "look" == $("#type").val()) {
        $("#newBuild").val(__ei.build);
        $("#newFloor").val(__ei.floor);
        $("#newGoodsLocation").val(__ei.installLocation);
        $("#newGoodsLocationNum").val(__ei.installLocationNum);
    }

    $("#QUERY").on("click", function () {
        resultGrid.dataSource.page(1);
    })

    $("#REQUERY").on("click", function () {
        $("#inqu_status-0-faTypeId").val("")
        $("#inqu_status-0-typeName").val("")
        resultGrid.dataSource.page(1);
    })

    // 定义结点的各属性名
    var NODE = {
        nodeldField: "id",
        nodeTextField: "typeName",
        parentIdField: "parentId",
        nodeTypeField: "isLeaf"
    };

    IPLATUI.EFTree = {
        "menu": {
            ROOT: {
                id: "root",
                typeName: "根节点",
                isLeaf: true
            },
            /**
             * 树节点内容点击触发回调函数（点击节点内容前展开收起符号不会触发）
             */
            select: function (e) {
                var _data = this.dataItem(e.node);
                $("#menu").val(_data[NODE.nodeldField]);
                $("#inqu_status-0-faTypeId").val(_data[NODE.nodeldField]);
                $("#typeId").val(_data[NODE.nodeldField]);
                $("#typeName").val(_data[NODE.nodeTextField]);
                resultGrid.dataSource.page(1);
            },
            /**
             * 树加载完成后的回调函数
             * @param options: 树的配置项
             */
            loadComplete: function (options) {
                // 保持节点展开状态
                var expanded = Cookies.get('expanded');
                if (expanded) {
                    Cookies.remove('expanded');
                    expanded = JSON.parse(expanded);
                    $("#menu").data("kendoTreeView").expandPath(
                        _.keys(expanded)
                    );
                }
            },
            /**
             * 数据源改变后回调，增删改查节点信息都会触发
             * @param e
             * e.node：被操作结点的父结点
             */
            dataBound: function (e) {

            },
            /**
             * expand(e)：结点展开前回调函数, e.node 指被展开的结点;
             * collapse(e)：结点收起前回调函数, e.node 指被收起的结点;
             * saveExpanded()：结点展开和收起时，记录结点展开状态
             */
            expand: function () {
                saveExpanded()
            },
            collapse: function () {
                saveExpanded();
            }
        },
    };

    // Cookies 保存结点展开状态 Fn
    function saveExpanded() {
        var treeview = $("#menu").data("kendoTreeView");
        var expandedItemsIds = {};
        treeview.element.find(".k-item").each(function () {
            var item = treeview.dataItem(this);
            if (item.expanded) {
                expandedItemsIds[item.id] = true;
            }
        });
        Cookies.set('expanded', kendo.stringify(expandedItemsIds));
    }

    IPLATUI.EFGrid = {
        "result": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "ef_popup_grid_fillback", text: "确定", layout: "3",
                    click: function () {
                        var checkRows = resultGrid.getCheckedRows();
                        var model = checkRows[0];
                        if (model) {
                            $("#info-0-goodsTypeCode").val(model['typeCode']);
                            $("#info-0-goodsTypeCode_textField").val(model['typeName']);
                            $("#info-0-goodsClassifyCode").val(model['parentId']);
                            $("#info-0-goodsClassifyName").val(model['parentName']);
                            var popupGridWindow = $("#ef_popup_grid").data("kendoWindow");
                            popupGridWindow.close();
                        } else {
                            NotificationUtil("请选择资产类别", "warning");
                        }
                    }
                }]
            },
            loadComplete: function (e) {
                $("#CLOSE").on("click", function (e) {
                    closeParentWindow()
                });
            }
        },
    }
});


function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultGrid"].dataSource.page(1);
}


// -------------------自定义自动补全--------------
/** 楼自动补全 */
$("#newBuild").kendoAutoComplete({
    // 以building为参数，调用FADA01/selectSpotBuildingName本地服务进行查询，将返回的building作为第二个参数。
    dataSource: dataSourceConfig("/service/FADA01/selectSpotBuildingName", "building", ["newBuild"]),
    // blockId 下的属性 building
    dataTextField: "building",
    filter: "contains",
    minLength: 0,
    select: function (e) {
        let dataItem = this.dataItem(e.item.index());
        $("#newBuild").val(dataItem.building)
        $("#info-0-build").val(dataItem.building)
    },
    change: function (e) {
        if ($("#info-0-build").val() != "" && $("#info-0-build").val() != $("#newBuild").val()) {
            $("#newBuild").val("");
            $("#newFloor").val("");
            $("#newGoodsLocationNum").val("");
            $("#newGoodsLocation").val("")
            $("#info-0-build").val("")
            $("#info-0-floor").val("")
            $("#info-0-installLocationNum").val("")
            $("#info-0-installLocation").val("")
        }
    },
});

/** 层自动补全 */
$("#newFloor").kendoAutoComplete({
    // 以building和floor为参数，调用FADA01/selectSpotFloorName本地服务进行查询，将返回的floor作为第二个参数。
    dataSource: dataSourceConfig("/service/FADA01/selectSpotFloorName", "floor", ["newBuild", "newFloor"]),
    // blockId 下的属性 floor
    dataTextField: "floor",
    filter: "contains",
    select: function (e) {
        let dataItem = this.dataItem(e.item.index());
        $("#newFloor").val(dataItem.floor)
        $("#info-0-floor").val(dataItem.floor)
    },
    change: function (e) {
        if ($("#info-0-floor").val() != "" && $("#info-0-floor").val() != $("#newFloor").val()) {
            $("#newFloor").val("")
            $("#newGoodsLocationNum").val("")
            $("#newGoodsLocation").val("")
            $("#info-0-floor").val("")
            $("#info-0-installLocationNum").val("")
            $("#info-0-installLocation").val("")
        }
    },
});

/** 地点自动补全 */
$("#newGoodsLocation").kendoAutoComplete({
    // 以building和floor和reqSpotName为参数，调用FADA01/selectSpotRoomName本地服务进行查询，将返回的room作为第二个参数。
    dataSource: dataSourceConfig("/service/FADA01/selectSpotRoomName", "room", ["newBuild", "newFloor", "newGoodsLocation"]),
    // blockId 下的属性 spotName
    dataTextField: "spotName",
    filter: "contains",
    select: function (e) {
        let dataItem = this.dataItem(e.item.index())
        $("#newGoodsLocationNum").val(dataItem.spotNum)
        $("#newGoodsLocation").val(dataItem.spotName);
        $("#info-0-installLocationNum").val(dataItem.spotNum)
        $("#info-0-installLocation").val(dataItem.spotName)
    },
    change: function (e) {
        if ($("#info-0-installLocation").val() != "" && $("#info-0-installLocation").val() != $("#newGoodsLocation").val()) {
            $("#newGoodsLocationNum").val("");
            $("#newGoodsLocation").val("")
            $("#info-0-installLocationNum").val("")
            $("#info-0-installLocation").val("")
        }
    },
});

// ---------------------------------------------

/** kendoAutoComplete的dataSource配置*/
function dataSourceConfig(url, blockId, param) {
    return new kendo.data.DataSource({
        serverFiltering: true,//每次输入重新获取数据
        transport: {
            read: {
                url: IPLATUI.CONTEXT_PATH + url,
                type: 'POST',
                dataType: "json",
                contentType: "application/json"
            },
            parameterMap: function (options, operation) {
                var info = new EiInfo();
                if (param != null) {
                    for (var index in param) {
                        info.set(param[index], $("#" + param[index]).val());
                    }
                }
                return info.toJSONString();
            }
        },
        schema: {
            data: function (response) {
                ajaxEi = EiInfo.parseJSONObject(response);
                var block = ajaxEi.getBlock(blockId);
                var array = new Array();
                for (var index in block.getRows()) {
                    array.push(block.getMappedObject(block.getRows()[index]));
                }
                return array;
            }
        }
    })
}

// 显示所有，自动补齐
function showAll(selector) {
    var autocomplete = $("#" + selector).data("kendoAutoComplete");
    autocomplete.search("");
}

// 计算资产原值
function countBuyCost() {
    var buyCost = $("#info-0-amount").val() * $("#info-0-price").val();
    $("#info-0-buyCost").val(buyCost)
}

// 计算预计净残值
function countEstimatedResidualValue() {
    var estimatedResidualValue = $("#info-0-buyCost").val() * $("#info-0-residualRate").val() / 100
    $("#info-0-estimatedResidualValue").val(estimatedResidualValue)
}

// 计算月折旧值
function countMonthDepreciation() {
    var monthDepreciation = $("#info-0-buyCost").val() / $("#info-0-useYears").val() / 12
    $("#info-0-monthDepreciation").val(monthDepreciation)
}

// 计算总折旧值
function countTotalDepreciation(){
    if (!isEmptyStr($("#info-0-buyDate").val())){
        var totalDepreciation = (getDistanceMonth($("#info-0-buyDate").val()) * $("#info-0-monthDepreciation").val()).toFixed(2);
        if (parseInt(totalDepreciation) >= parseInt($("#info-0-buyCost").val())){
            $("#info-0-totalDepreciation").val(($("#info-0-buyCost").val() - $("#info-0-estimatedResidualValue").val()).toFixed(2))
        }else {
            $("#info-0-totalDepreciation").val(totalDepreciation)
        }
    }
}

// 计算资产净值
// function countNetAssetValue(){
//     var netAssetValue = ($("#info-0-buyCost").val() - $("#info-0-totalDepreciation").val()).toFixed(2)
//     $("#info-0-netAssetValue").val(netAssetValue)
// }

// 计算2个日期相差多少月
function getDistanceMonth(startTime) {
    startTime = new Date(startTime)
    endTime = new Date(getNowDate())
    var dateToMonth = 0
    var startDate = startTime.getDate() + startTime.getHours() / 24 + startTime.getMinutes() / 24 / 60
    var endDate = endTime.getDate() + endTime.getHours() / 24 + endTime.getMinutes() / 24 / 60
    if (endDate >= startDate) {
        dateToMonth = 0
    } else {
        dateToMonth = -1
    }
    var yearToMonth = (endTime.getYear() - startTime.getYear()) * 12
    var monthToMonth = endTime.getMonth() - startTime.getMonth()
    return yearToMonth + monthToMonth + dateToMonth + 1
}

// 获取当前时间YYYYMMDD
function getNowDate() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (day >= 0 && day <= 9) {
        day = "0" + day;
    }
    return year + '-' + month + '-' + day
}

function isEmptyStr(s){
    if (s == null || s === '') {
        return true
    }
    return false
}