$(function () {
    /**回车键查询**/
    keydown("inqu", "QUERY");

    IPLAT.EFInput.readonly($("#info-0-netAssetValue"), true)
    IPLAT.EFInput.readonly($("#info-0-buyCost"), true)
    IPLAT.EFInput.readonly($("#info-0-estimateCost"), true)
    IPLAT.EFPopupInput.setAllFields($("#info-0-surpNum"), __ei.surpNum, __ei.surpName)
    IPLAT.EFInput.value($("#info-0-purchaseDept"), __ei.purchaseDept);
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
                IPLAT.EFInput.readonly($("#info-0-netAssetValue"), true)
                $("#modificationValue").hide();
            } else {
                IPLAT.EFInput.readonly($("#info-0-buyCost"), false)
                IPLAT.EFInput.readonly($("#info-0-estimateCost"), false)
                IPLAT.EFInput.readonly($("#info-0-netAssetValue"), false)
                $("#modificationValue").show();
            }
        },
    }
};

IPLATUI.EFGrid = {
    "result": {
        pageable: {
            pageSize: 50,
            pageSizes: [50, 100, 500, 1000]
        },
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [{
                name: "ef_popup_grid_fillback", text: "确定", layout: "3",
                click: function () {
                    var checkRows = resultGrid.getCheckedRows();
                    var model = checkRows[0];
                    if (model) {
                        var goodsCategoryCode = model['typeCode'];
                        var goodsCategoryName = model['typeName'];
                        if (goodsCategoryCode.substring(0, 1) == "C") {
                            goodsCategoryCode = "A" + goodsCategoryCode.substring(1);
                        } else if (goodsCategoryCode.substring(0, 1) == "B") {
                            goodsCategoryCode = "A08" + goodsCategoryCode.substring(1);
                        }
                        $("#info-0-goodsCategoryCode").val(goodsCategoryCode);
                        $("#info-0-goodsCategoryCode_textField").val(goodsCategoryName);
                        $("#info-0-useYears").val(model['useYears']);
                        var eiInfo = new EiInfo();
                        eiInfo.set("goodsCategoryCode", model['typeCode']);
                        EiCommunicator.send("FADA0101", "backGoodsCategoryCode", eiInfo, {
                            onSuccess: function (ei) {
                                var list = ei.extAttr.list
                                var goodsTypeCode = list.parentCode;
                                var goodsClassifyCode = list.typeCode;
                                if (goodsTypeCode.substring(0, 1) == "C") {
                                    goodsTypeCode = "A" + goodsTypeCode.substring(1);
                                } else if (goodsTypeCode.substring(0, 1) == "B") {
                                    goodsTypeCode = "A08000000" + goodsTypeCode.substring(1);
                                }
                                if (goodsClassifyCode.substring(0, 1) == "C") {
                                    goodsClassifyCode = "A" + goodsClassifyCode.substring(1);
                                } else if (goodsClassifyCode.substring(0, 1) == "B") {
                                    goodsClassifyCode = "A08" + goodsClassifyCode.substring(1);
                                }
                                $("#info-0-goodsTypeCode").val(goodsTypeCode);
                                $("#info-0-goodsTypeName").val(list.parentName);
                                $("#info-0-goodsClassifyCode").val(goodsClassifyCode);
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
                var changeRows = resultValueGrid.getDataItems();
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
                if ($("#info-0-purchaseDept").val() == "") {
                    NotificationUtil("请填写采购科室", "warning")
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
                if (unitName == "--请选择--") {
                    IPLAT.EFInput.value($("#info-0-unitName"), "");
                } else {
                    IPLAT.EFInput.value($("#info-0-unitName"), unitName);
                }
                var assetGetWayName = IPLAT.EFSelect.text($("#info-0-assetGetWayCode"));
                if (assetGetWayName == "--请选择--") {
                    IPLAT.EFInput.value($("#info-0-assetGetWayName"), "");
                } else {
                    IPLAT.EFInput.value($("#info-0-assetGetWayName"), assetGetWayName);
                }
                var assetUseName = IPLAT.EFSelect.text($("#info-0-assetUseCode"));
                if (assetUseName == "--请选择--") {
                    IPLAT.EFInput.value($("#info-0-assetUseName"), "");
                } else {
                    IPLAT.EFInput.value($("#info-0-assetUseName"), assetUseName);
                }
                var manufacturerNatyName = IPLAT.EFSelect.text($("#info-0-manufacturerNatyCode"));
                if (manufacturerNatyName == "--请选择--") {
                    IPLAT.EFInput.value($("#info-0-manufacturerNatyName"), "");
                } else {
                    IPLAT.EFInput.value($("#info-0-manufacturerNatyName"), manufacturerNatyName);
                }
                var fundingSourceName = IPLAT.EFSelect.text($("#info-0-fundingSourceNum"));
                if (fundingSourceName == "--请选择--") {
                    IPLAT.EFInput.value($("#info-0-fundingSourceName"), "");
                } else {
                    IPLAT.EFInput.value($("#info-0-fundingSourceName"), fundingSourceName);
                }
                var eiInfo = new EiInfo();
                eiInfo.set("changeRows",changeRows)
                eiInfo.setByNode("info");
                // 资产变更提交
                EiCommunicator.send("FABG0101", "batchModificationInfo", eiInfo, {
                    onSuccess: function (ei) {
                        if (ei.status == "-1") {
                            NotificationUtil(ei.msg, "warning");
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
    "resultValue": {
        pageable: false,
        exportGrid: false,
        columns: [{
                field: "goodsNum",
                title: "卡片编号",
                readonly: true,
                width: 20,
            },
            {
                field: "goodsName",
                title: "资产名称",
                readonly: true,
                width: 20,
            }, {
                field: "buyDate",
                title: "入账日期",
                readonly: true,
                width: 20,
            }, {
                field: "buyCost",
                title: "原值<br/>（元）",
                readonly: true,
                width: 20,
            }, {
                field: "equityFund",
                title: "自有资金<br/>（元）",
                readonly: true,
                width: 20,
            }, {
                field: "otherFund",
                title: "其他资金<br/>（元）",
                readonly: true,
                width: 20,
            }, {
                field: "netAssetValue",
                title: "净值<br/>（元）",
                readonly: true,
                width: 20,
            }, {
                field: "totalDepreciation",
                title: "累计折旧<br/>（元）",
                readonly: true,
                width: 20,
            },{
            field: "change",
            title: "变化量",
            columns: [
                {
                    field: "changeBuyCost",
                    title: "原值<br/>（元）",
                    width: 20,
                },
                {
                    field: "changeEquityFund",
                    title: "自有资金<br/>（元）",
                    width: 20,
                },
                {
                    field: "changeOtherFund",
                    title: "其他资金<br/>（元）",
                    width: 20,
                }, {
                    field: "changeNetAssetValue",
                    title: "净值<br/>（元）",
                    width: 20,
                }, {
                    field: "changeTotalDepreciation",
                    title: "累计折旧<br/>（元）",
                    width: 20,
                }]
            },
            {
                field: "after",
                title: "变化后",
                columns: [
                    {
                        field: "afterBuyCost",
                        title: "原值<br/>（元）",
                        width: 20,
                        template: "<span id='afterBuyCost'>#=afterBuyCost#</span>",
                    },
                    {
                        field: "afterEquityFund",
                        title: "自有资金<br/>（元）",
                        width: 20,
                        template: "<span id='afterEquityFund'>#=afterEquityFund#</span>",
                    },
                    {
                        field: "afterOtherFund",
                        title: "其他资金<br/>（元）",
                        width: 20,
                        template: "<span id='afterOtherFund'>#=afterOtherFund#</span>",
                    }, {
                        field: "afterNetAssetValue",
                        title: "净值<br/>（元）",
                        width: 20,
                        template: "<span id='afterNetAssetValue'>#=afterNetAssetValue#</span>",
                    }, {
                        field: "afterTotalDepreciation",
                        title: "累计折旧<br/>（元）",
                        width: 20,
                        template: "<span id='afterTotalDepreciation'>#=afterTotalDepreciation#</span>",
                    }
                ]
            }
        ],
        loadComplete: function (e) {

        },
        afterEdit: function (e) {
            let grid = e.sender;
            let rowIndex = e.row
            if (e.field == "changeBuyCost") {
                // 获取变化前的资产原值
                var beforeBuyCost = e.model.buyCost;
                // 获取当前变化值
                var changeBuyCost = e.model.changeBuyCost;
                // 计算变化后的资产原值
                var afterBuyCost = Addtr(beforeBuyCost, changeBuyCost);
                var element = document.getElementById("afterBuyCost"); // 通过id获取元素
                element.innerText = afterBuyCost;
                resultValueGrid.setCellValue(0, "afterBuyCost", afterBuyCost)
            } else if (e.field == "changeEquityFund") {
                // 获取变化前的自有资金
                var beforeEquityFund = e.model.equityFund;
                // 获取当前变化值
                var changeEquityFund = e.model.changeEquityFund;
                // 计算变化后的自有资金
                var afterEquityFund = Addtr(beforeEquityFund, changeEquityFund);
                var element = document.getElementById("afterEquityFund"); // 通过id获取元素
                element.innerText = afterEquityFund;
                resultValueGrid.setCellValue(0, "afterEquityFund", afterEquityFund)
            } else if (e.field == "changeOtherFund") {
                // 获取变化前的其他资金
                var beforeOtherFund = e.model.otherFund;
                // 获取当前变化值
                var changeOtherFund = e.model.changeOtherFund;
                // 计算变化后的其他资金
                var afterOtherFund = Addtr(beforeOtherFund, changeOtherFund);
                var element = document.getElementById("afterOtherFund"); // 通过id获取元素
                element.innerText = afterOtherFund;
                resultValueGrid.setCellValue(0, "afterOtherFund", afterOtherFund)
            } else if (e.field == "changeNetAssetValue") {
                // 获取变化前的净值
                var beforeNetAssetValue = e.model.netAssetValue;
                // 获取当前变化值
                var changeNetAssetValue = e.model.changeNetAssetValue;
                // 计算变化后的净值
                var afterNetAssetValue = Addtr(beforeNetAssetValue, changeNetAssetValue);
                var element = document.getElementById("afterNetAssetValue"); // 通过id获取元素
                element.innerText = afterNetAssetValue;
                resultValueGrid.setCellValue(0, "afterNetAssetValue", afterNetAssetValue)
            } else if (e.field == "changeTotalDepreciation") {
                // 获取变化前的累计折旧
                var beforeTotalDepreciation = e.model.totalDepreciation;
                // 获取当前变化值
                var changeTotalDepreciation = e.model.changeTotalDepreciation;
                // 计算变化后的累计折旧
                var afterTotalDepreciation = Addtr(beforeTotalDepreciation, changeTotalDepreciation);
                var element = document.getElementById("afterTotalDepreciation"); // 通过id获取元素
                element.innerText = afterTotalDepreciation;
                resultValueGrid.setCellValue(0, "afterTotalDepreciation", afterTotalDepreciation)
            }
        }
    },
    "resultAfter": {
        pageable: false,
        exportGrid: false,

    }
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
    // 数据库中的净值与原值
    var netAssetValue = __ei.netAssetValue;
    var oldBuyCost = __ei.buyCost;
    // 获取当前的原值
    var nowBuyCost = $("#info-0-buyCost").val();
    // 计算变化后的原值
    var modificationBuyCost = Subtr(nowBuyCost, oldBuyCost)
    // 计算变化后的净值
    var modificationNetAssetValue = Addtr(netAssetValue, modificationBuyCost)
    var modifyType = IPLAT.EFSelect.value($("#info-0-modifyType"));
    if (modificationBuyCost > 0) {
        IPLAT.EFInput.value($("#info-0-modificationLabel"), "资产升值")
        IPLAT.EFInput.value($("#info-0-modificationStatus"), "021")
        IPLAT.EFInput.value($("#info-0-modificationCost"), "+" + modificationBuyCost)
        IPLAT.EFInput.value($("#info-0-modificationValue"), modificationBuyCost)
        if (modifyType == '20') {
            IPLAT.EFInput.value($("#info-0-netAssetValue"), modificationNetAssetValue)
        }
    } else if (modificationBuyCost < 0) {
        IPLAT.EFInput.value($("#info-0-modificationLabel"), "资产贬值")
        IPLAT.EFInput.value($("#info-0-modificationStatus"), "026")
        IPLAT.EFInput.value($("#info-0-modificationCost"), modificationBuyCost)
        IPLAT.EFInput.value($("#info-0-modificationValue"), modificationBuyCost)
        if (modifyType == '20') {
            IPLAT.EFInput.value($("#info-0-netAssetValue"), modificationNetAssetValue)
            // 净值最少为0
            if (modificationNetAssetValue < 0) {
                IPLAT.EFInput.value($("#info-0-netAssetValue"), 0)
            }
        }
    } else {
        IPLAT.EFInput.value($("#info-0-modificationLabel"), "保持不变")
        IPLAT.EFInput.value($("#info-0-modificationCost"), 0.00)
        if (modifyType == '20') {
            IPLAT.EFInput.value($("#info-0-netAssetValue"), modificationNetAssetValue)
        }
    }
}

// 暂估值
function modificationEstimateCostValue() {
    // 数据库中的资产原值
    var netAssetValue = __ei.netAssetValue;
    var oldEstimateCost = __ei.estimateCost;
    var nowEstimateCost = $("#info-0-estimateCost").val();
    var modificationEstimateCost = Subtr(nowEstimateCost, oldEstimateCost)
    var modificationNetAssetValue = Subtr(netAssetValue, -modificationEstimateCost)
    console.log(modificationEstimateCost)
    if (modificationEstimateCost > 0) {
        IPLAT.EFInput.value($("#info-0-modificationLabel"), "资产升值")
        IPLAT.EFInput.value($("#info-0-modificationStatus"), "021")
        IPLAT.EFInput.value($("#info-0-modificationCost"), "+" + modificationEstimateCost)
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

// 减法函数，用来得到精确的减法结果
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

// 加法函数，用来得到精确的减法结果
function Addtr(arg1, arg2) {
    arg1 = arg1.toString(), arg2 = arg2.toString(); // 将传入的数据转化为字符串
    var arg1Arr = arg1.split("."), // 将小数的数据从小数点的位置拆开
        arg2Arr = arg2.split("."),
        d1 = arg1Arr.length == 2 ? arg1Arr[1] : "", // 获取第一个数的小数点的长度
        d2 = arg2Arr.length == 2 ? arg2Arr[1] : ""; // 获取第二个数的小数点的长度
    var maxLen = Math.max(d1.length, d2.length); // 获取小数点长度较大的值
    var m = Math.pow(10, maxLen); // 这里表示10的小数点长度次方 也就是说如果小数点长度为2 m的值就是100 如果小数点长度是3 m的值就是1000如果不懂请自行查找api
    var result = Number(((arg1 * m + arg2 * m) / m).toFixed(maxLen)); // 将小数转化为整数后相加在除掉两个数乘过的倍数然后去小数点较长的长度的小数位数
    var d = arguments[2]; // 第三个参数用户可以自行决定是否要传递 用来定义要保留的小数长度
    return typeof d === "number" ? Number((result).toFixed(d)) : result;
}