$(function () {
    IPLAT.EFInput.readonly($("#info-0-netAssetValue"), true)
    IPLAT.EFInput.readonly($("#info-0-buyCost"), true)
    IPLAT.EFInput.readonly($("#info-0-estimateCost"), true)
    IPLAT.EFPopupInput.setAllFields($("#info-0-surpNum"), __ei.surpNum, __ei.surpName)

    document.getElementById("info-0-buyCost").addEventListener("input", () => {
        // 计算资产原值变更
        modificationValue();
    })

    document.getElementById("info-0-buyCost").addEventListener("propertychange", () => {
        modificationValue();
    })

    document.getElementById("info-0-estimateCost").addEventListener("input", () => {
        // 暂估值
        modificationEstimateCostValue();
    })

    document.getElementById("info-0-estimateCost").addEventListener("propertychange", () => {
        modificationEstimateCostValue();
    })

    $("#QUERY").on("click", function () {
        resultGrid.dataSource.page(1);
    })

    $("#REQUERY").on("click", function () {
        $("#inqu_status-0-faTypeId").val("")
        $("#inqu_status-0-typeName").val("")
        resultGrid.dataSource.page(1);
    })

    $("#CLOSE").on("click", function (e) {
        closeParentWindow()
    });
});

IPLATUI.EFSelect = {
    "info-0-costType": {
        select: function (e) {
            if ("00" == e.dataItem.valueField) {
                $("#buyCost").show();
                $("#estimateCost").hide();
            } else {
                $("#buyCost").hide();
                $("#estimateCost").show();
            }
        },
    },
    "info-0-modifyType": {
        select: function (e) {
            if ("00" == e.dataItem.valueField) {
                IPLAT.EFInput.readonly($("#info-0-buyCost"), true)
                IPLAT.EFInput.readonly($("#info-0-estimateCost"), true)
                $("#modificationValue").hide();
            } else {
                IPLAT.EFInput.readonly($("#info-0-buyCost"), false)
                IPLAT.EFInput.readonly($("#info-0-estimateCost"), false)
                $("#modificationValue").show();
            }
        },
    }
};

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
                        $("#info-0-goodsCategoryCode").val(model['typeCode']);
                        $("#info-0-goodsCategoryCode_textField").val(model['typeName']);
                        // $("#info-0-goodsClassifyCode").val(model['parentCode']);
                        // $("#info-0-goodsClassifyName").val(model['parentName']);
                        $("#info-0-useYears").val(model['useYears']);
                        var eiInfo = new EiInfo();
                        eiInfo.set("goodsCategoryCode", model['typeCode']);
                        EiCommunicator.send("FADA0101", "backGoodsCategoryCode", eiInfo, {
                            onSuccess: function (ei) {
                                var list = ei.extAttr.list
                                $("#info-0-goodsTypeCode").val(list.parentCode);
                                $("#info-0-goodsTypeName").val(list.parentName);
                                $("#info-0-goodsClassifyCode").val(list.typeCode);
                                $("#info-0-goodsClassifyName").val(list.typeName);
                            }
                        })
                        var popupGridWindow = $("#ef_popup_grid").data("kendoWindow");
                        popupGridWindow.close();
                    } else {
                        NotificationUtil("请选择资产类别", "warning");
                        return
                    }
                }
            }]
        },
        loadComplete: function (e) {
            if ("edit" == $("#type").val() || "look" == $("#type").val()) {
                $("#newBuild").val(__ei.build);
                $("#newFloor").val(__ei.floor);
                $("#newGoodsLocation").val(__ei.installLocation);
                $("#newGoodsLocationNum").val(__ei.installLocationNum);
                countTotalDepreciation()
            }

            // 资产变更提交
            $("#SAVE").on("click", function (e) {
                var costType = IPLAT.EFSelect.value($("#info-0-costType"));
                if (costType == "00") {
                    $("#info-0-estimateCost").val(0);
                } else {
                    $("#info-0-buyCost").val(0);
                }
                // 前端校验
                if ($("#info-0-goodsClassifyCode").val() == "") {
                    NotificationUtil("请选择末级类别", "warning")
                    return
                }
                if ($("#info-0-goodsCategoryCode").val() == "") {
                    NotificationUtil("请选择末级类别", "warning")
                    return
                }
                if ($("#info-0-goodsName").val() == "") {
                    NotificationUtil("请填写资产名称", "warning")
                    return
                }
                // if ($("#info-0-deptNum").val() == "") {
                //     NotificationUtil("请填写所属科室", "warning")
                //     return
                // }
                // if ($("#info-0-installLocation").val() == "") {
                //     NotificationUtil("请填写地点", "warning")
                //     return
                // }
                if (isNaN($("#info-0-amount").val())) {
                    NotificationUtil("请检查数量类型", "warning")
                    return
                }
                if (isNaN($("#info-0-price").val())) {
                    NotificationUtil("请检查单价类型", "warning")
                    return
                }
                if (isNaN($("#info-0-buyCost").val())) {
                    NotificationUtil("请检查资产原值类型", "warning")
                    return
                }
                if (isNaN($("#info-0-useYears").val())) {
                    NotificationUtil("请检查使用年限类型", "warning")
                    return
                }
                if (isNaN($("#info-0-residualRate").val())) {
                    NotificationUtil("请检查残值率类型", "warning")
                    return
                }
                if (isNaN($("#info-0-estimatedResidualValue").val())) {
                    NotificationUtil("请检查预计净残值类型", "warning")
                    return
                }
                if ($("#info-0-changeReason").val() == "") {
                    NotificationUtil("请填写变更理由", "warning")
                    return
                }

                // if (isNaN($("#info-0-monthDepreciation").val())) {
                //     NotificationUtil("请检查月折旧值类型", "warning")
                //     return
                // }
                // if ($("#info-0-monthDepreciation").val() == NaN) {
                //     NotificationUtil("月折旧值不能为非数字类型", "warning")
                //     return
                // }
                // if ($("#info-0-monthDepreciation").val() == Infinity) {
                //     NotificationUtil("月折旧值不能为无穷大", "warning")
                //     return
                // }
                var unitName = IPLAT.EFSelect.text($("#info-0-unitNum"));
                IPLAT.EFInput.value($("#info-0-unitName"), unitName);
                var assetGetWayName = IPLAT.EFSelect.text($("#info-0-assetGetWayCode"));
                IPLAT.EFInput.value($("#info-0-assetGetWayName"), assetGetWayName);
                var assetUseName = IPLAT.EFSelect.text($("#info-0-assetUseCode"));
                IPLAT.EFInput.value($("#info-0-assetUseName"), assetUseName);
                var manufacturerNatyName = IPLAT.EFSelect.text($("#info-0-manufacturerNatyCode"));
                IPLAT.EFInput.value($("#info-0-manufacturerNatyName"), manufacturerNatyName);
                var fundingSourceName = IPLAT.EFSelect.text($("#info-0-fundingSourceNum"));
                IPLAT.EFInput.value($("#info-0-fundingSourceName"), fundingSourceName);
                var eiInfo = new EiInfo();
                eiInfo.setByNode("info");
                // 资产变更提交
                EiCommunicator.send("FABG0101", "batchModificationInfo", eiInfo, {
                    onSuccess: function (ei) {
                        if (ei.status == "-1"){
                            NotificationUtil("资产没有发生变化，变更失败", "warning");
                            return
                        } else {
                            NotificationUtil("变更成功", "warning");
                            closeParentWindow()
                        }
                    }
                })
            });
        }
    },
}

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


function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultAGrid"].dataSource.page(1);
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
function countTotalDepreciation() {
    if (!isEmptyStr($("#info-0-buyDate").val())) {
        var totalDepreciation = (getDistanceMonth($("#info-0-buyDate").val()) * $("#info-0-monthDepreciation").val()).toFixed(2);
        console.log(totalDepreciation)
        console.log(parseInt(totalDepreciation) >= parseInt($("#info-0-buyCost").val()))
        if (parseInt(totalDepreciation) >= parseInt($("#info-0-buyCost").val())) {
            // 减去净残值
            // $("#info-0-totalDepreciation").val(($("#info-0-buyCost").val() - $("#info-0-estimatedResidualValue").val()).toFixed(2))
            // 不减净残值
            $("#info-0-totalDepreciation").val($("#info-0-buyCost").val())
        } else {
            $("#info-0-totalDepreciation").val(totalDepreciation)
        }
    }
}

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

function isEmptyStr(s) {
    if (s == null || s === '') {
        return true
    }
    return false
}

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

// 计算资产原值变更
function modificationValue() {
    // 数据库中的资产原值
    var netAssetValue = __ei.netAssetValue;
    var oldBuyCost = __ei.buyCost;
    var nowNetBuyCost = $("#info-0-buyCost").val();
    var modificationBuyCost =  Subtr(nowNetBuyCost,oldBuyCost)
    var modificationNetAssetValue =  Subtr(netAssetValue,-modificationBuyCost)
    if (modificationBuyCost > 0) {
        IPLAT.EFInput.value($("#info-0-modificationLabel"), "资产升值")
        IPLAT.EFInput.value($("#info-0-modificationStatus"), "021")
        IPLAT.EFInput.value($("#info-0-modificationCost"), "+"+modificationBuyCost)
        IPLAT.EFInput.value($("#info-0-modificationValue"), modificationBuyCost)
        IPLAT.EFInput.value($("#info-0-netAssetValue"), modificationNetAssetValue)
    } else if (modificationBuyCost < 0) {
        IPLAT.EFInput.value($("#info-0-modificationLabel"), "资产贬值")
        IPLAT.EFInput.value($("#info-0-modificationStatus"), "026")
        IPLAT.EFInput.value($("#info-0-modificationCost"), modificationBuyCost)
        IPLAT.EFInput.value($("#info-0-modificationValue"), modificationBuyCost)
        // 净值只能为0
        IPLAT.EFInput.value($("#info-0-netAssetValue"), 0)
        // IPLAT.EFInput.value($("#info-0-netAssetValue"), modificationNetAssetValue)
    } else {
        IPLAT.EFInput.value($("#info-0-modificationLabel"), "保持不变")
        IPLAT.EFInput.value($("#info-0-modificationCost"), 0.00)
    }
}

// 暂估值
function modificationEstimateCostValue() {
    // 数据库中的资产原值
    var netAssetValue = __ei.netAssetValue;
    var oldEstimateCost = __ei.estimateCost;
    var nowEstimateCost = $("#info-0-estimateCost").val();
    var modificationEstimateCost =  Subtr(nowEstimateCost,oldEstimateCost)
    var modificationNetAssetValue =  Subtr(netAssetValue,-modificationEstimateCost)
    console.log(modificationEstimateCost)
    if (modificationEstimateCost > 0) {
        IPLAT.EFInput.value($("#info-0-modificationLabel"), "资产升值")
        IPLAT.EFInput.value($("#info-0-modificationStatus"), "021")
        IPLAT.EFInput.value($("#info-0-modificationCost"), "+"+modificationEstimateCost)
        IPLAT.EFInput.value($("#info-0-modificationValue"), modificationEstimateCost)
        IPLAT.EFInput.value($("#info-0-netAssetValue"), modificationNetAssetValue)
    } else if (modificationEstimateCost < 0) {
        IPLAT.EFInput.value($("#info-0-modificationLabel"), "资产贬值")
        IPLAT.EFInput.value($("#info-0-modificationStatus"), "026")
        IPLAT.EFInput.value($("#info-0-modificationCost"), modificationEstimateCost)
        IPLAT.EFInput.value($("#info-0-modificationValue"), modificationEstimateCost)
        IPLAT.EFInput.value($("#info-0-netAssetValue"), modificationNetAssetValue)
    } else {
        IPLAT.EFInput.value($("#info-0-modificationLabel"), "保持不变")
        IPLAT.EFInput.value($("#info-0-modificationCost"), 0.00)
    }
}

//减法函数，用来得到精确的减法结果
function Subtr(arg1, arg2) {
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length
    } catch (e) {
        r1 = 0
    }
    try {
        r2 = arg2.toString().split(".")[1].length
    } catch (e) {
        r2 = 0
    }
    m = Math.pow(10, Math.max(r1, r2));
    //last modify by deeka
    //动态控制精度长度
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m).toFixed(n);
}