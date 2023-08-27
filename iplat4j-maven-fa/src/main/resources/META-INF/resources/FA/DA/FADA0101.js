$(function () {
    // 控制资产项目是否显示
    // var fundProjectCode = IPLAT.EFInput.value($("#info-0-fundProjectCode"));
    // if (fundProjectCode == "100" || fundProjectCode == null) {
    //     $("#fundProject").hide();
    // } else {
    //     $("#fundProject").show();
    // }
    // 计算入库总值
    countEnterAmount();

    // 塞值
    IPLAT.EFInput.value($("#info-0-purchaseDept"), __ei.purchaseDept);
    $("#info-0-deptName").val(__ei.deptName)
    IPLAT.EFInput.value($("#info-0-acquitvDate"), __ei.buyDate);
    IPLAT.EFPopupInput.setAllFields($("#info-0-surpNum"), __ei.surpNum, __ei.surpName)
    IPLAT.EFPopupInput.setAllFields($("#info-0-goodsTypeCode"), __ei.goodsTypeCode, __ei.goodsTypeName)
    IPLAT.EFPopupInput.setAllFields($("#info-0-confirmLocationNum"), __ei.installLocationNum, __ei.installLocation)

    // 动态监控input值的改变
    document.getElementById("info-0-enterNum").addEventListener("input", () => {
        countBuyCost();
        countEnterAmount();
        countEstimatedResidualValue();
    })

    document.getElementById("info-0-enterNum").addEventListener("propertychange", () => {
        countBuyCost();
        countEnterAmount();
        countEstimatedResidualValue();
    })

    document.getElementById("info-0-enterPrice").addEventListener("input", () => {
        countBuyCost();
        countEnterAmount();
        countEstimatedResidualValue();
    })

    document.getElementById("info-0-enterPrice").addEventListener("propertychange", () => {
        countBuyCost();
        countEnterAmount();
        countEstimatedResidualValue();
    })

    document.getElementById("info-0-enterAmount").addEventListener("input", () => {
        countEnterAmount();
    })

    document.getElementById("info-0-enterAmount").addEventListener("propertychange", () => {
        countEnterAmount();
    })

    document.getElementById("info-0-buyCost").addEventListener("input", () => {
        countPrice();
        countEnterAmount();
        countEstimatedResidualValue();
    })

    document.getElementById("info-0-buyCost").addEventListener("propertychange", () => {
        countPrice();
        countEnterAmount();
        countEstimatedResidualValue();
    })

    document.getElementById("info-0-residualRate").addEventListener("input", () => {
        countEstimatedResidualValue();
    })

    document.getElementById("info-0-residualRate").addEventListener("propertychange", () => {
        countEstimatedResidualValue();
    })

    document.getElementById("info-0-equityFund").addEventListener("input", () => {
        countEquityFund();
    })

    document.getElementById("info-0-equityFund").addEventListener("propertychange", () => {
        countEquityFund();
    })

    document.getElementById("info-0-otherFund").addEventListener("input", () => {
        countOtherFund();
    })

    document.getElementById("info-0-otherFund").addEventListener("propertychange", () => {
        countOtherFund();
    })

    if ("edit" == $("#type").val()) {
        $("#newBuild").val(__ei.build);
        $("#newFloor").val(__ei.floor);
        $("#newGoodsLocation").val(__ei.installLocation);
        $("#newGoodsLocationNum").val(__ei.installLocationNum);
        IPLAT.EFInput.readonly($("#info-0-enterNum"), true);
    }

    $("#QUERY").on("click", function () {
        $("#inqu_status-0-faTypeId").val("root")
        resultGrid.dataSource.page(1);
    })

    $("#REQUERY").on("click", function () {
        $("#inqu_status-0-faTypeId").val("root")
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
        "tree02": { //tree02对应jsp中EFTree的bindId属性
            ROOT: "root", // 虚拟节点不渲染，仅作为初始查询条件
            select: function (e) {
                var _data = this.dataItem(e.node);
                $("#deviceClassifyCode").val(_data.code);
                result1Grid.dataSource.page(1);
            }
        }
    };

    IPLATUI.EFDatePicker = {
        "info-0-acquitvDate": {
            change: function (e) {
                var acquitvDate = $("#info-0-acquitvDate").data("kendoDatePicker").value();
                if (acquitvDate == "" || acquitvDate == null) {
                    $("#info-0-acquitvYear").val(acquitvDate);
                } else {
                    acquitvDate = kendo.toString(acquitvDate, "yyyy-MM-dd");
                    acquitvDate = acquitvDate.substring(0, 4);
                    $("#info-0-acquitvYear").val(acquitvDate);
                }
            }
        }
    }

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
        // "info-0-fundingSourceNum": {
        //     select: function (e) {
        //         if ("100" == e.dataItem.valueField || "" == e.dataItem.valueField) {
        //             $("#fundProject").hide();
        //             IPLAT.EFInput.value($("#info-0-fundProjectCode"), "");
        //             IPLAT.EFInput.value($("#info-0-fundProject"), "");
        //         } else {
        //             $("#fundProject").show();
        //         }
        //     },
        // }
    };

    IPLATUI.EFPopupInput = {
        "info-0-confirmLocationNum": {
            query: function (e) {
                var queryInfo = new EiInfo();
                queryInfo.set("confirmDeptNum", $("#info-0-confirmDeptNum").val());
                return queryInfo;
            },
            backFill: function (e) {
                IPLAT.EFInput.value($("#info-0-confirmBuild"), e.model['building']);
                IPLAT.EFInput.value($("#info-0-confirmFloor"), e.model['floor']);
            },
        },
    }

    IPLATUI.EFAutoComplete = {
        "info-0-purchaseDept": {
            change: function (e) {
                var context = $("#info-0-purchaseDept").data("kendoAutoComplete");
                if (context.listView._dataItems.length > 0) {
                    var purchaseDeptNum = context.listView._dataItems[0].deptNum;
                    var purchaseDeptName = context.listView._dataItems[0].deptName;
                    if (purchaseDeptNum == "") {
                        NotificationUtil("该科室不存在编码请重新选择", "warning")
                        IPLAT.EFInput.value($("#info-0-purchaseDeptNum"), "");
                        IPLAT.EFInput.value($("#info-0-purchaseDeptName"), "");
                    } else {
                        IPLAT.EFInput.value($("#info-0-purchaseDeptNum"), purchaseDeptNum);
                        IPLAT.EFInput.value($("#info-0-purchaseDeptName"), purchaseDeptName);
                    }
                } else {
                    NotificationUtil("该科室不存在请重新选择", "warning")
                    IPLAT.EFInput.value($("#info-0-purchaseDeptNum"), "");
                }
                // console.log("编码：" + confirmDeptNum + "--" + "名称：" + confirmDeptName)
            }
        },
        "info-0-deptName": {
            change: function (e) {
                var context = $("#info-0-deptName").data("kendoAutoComplete");
                if (context.listView._dataItems.length > 0) {
                    var purchaseDeptNum = context.listView._dataItems[0].deptNum;
                    console.log(purchaseDeptNum)
                    var purchaseDeptName = context.listView._dataItems[0].deptName;
                    if (purchaseDeptNum == "") {
                        NotificationUtil("该科室不存在编码请重新选择", "warning")
                        IPLAT.EFInput.value($("#info-0-confirmDeptNum"), "");
                        IPLAT.EFInput.value($("#info-0-confirmDeptName"), "");
                    } else {
                        IPLAT.EFInput.value($("#info-0-confirmDeptNum"), purchaseDeptNum);
                        IPLAT.EFInput.value($("#info-0-confirmDeptName"), purchaseDeptName);
                    }
                } else {
                    NotificationUtil("该科室不存在请重新选择", "warning")
                    IPLAT.EFInput.value($("#info-0-confirmDeptNum"), "");
                    IPLAT.EFInput.value($("#info-0-confirmDeptName"), "");
                }
                // console.log("编码：" + confirmDeptNum + "--" + "名称：" + confirmDeptName)
            }
        }
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
                            $("#info-0-goodsClassifyCode").val(model['parentCode']);
                            $("#info-0-goodsClassifyName").val(model['parentName']);
                            $("#info-0-useYears").val(model['useYears']);
                            var popupGridWindow = $("#ef_popup_grid").data("kendoWindow");
                            popupGridWindow.close();
                        } else {
                            NotificationUtil("请选择资产类别", "warning");
                        }
                    },

                }]
            },
            loadComplete: function (e) {
                $("#SAVE").on("click", function (e) {
                    var eiInfo = new EiInfo();
                    eiInfo.setByNode("info");
                    var validator = IPLAT.Validator({
                        id: "info"
                    });
                    var costType = IPLAT.EFSelect.value($("#info-0-costType"));
                    if (costType == "00") {
                        $("#info-0-estimateCost").val(0);
                    } else {
                        $("#info-0-buyCost").val(0);
                    }
                    validator.validate();
                    if ($("#info-0-goodsName").val() == "") {
                        NotificationUtil("请填写资产名称", "warning")
                        return
                    }
                    if ($("#info-0-goodsTypeCode").val() == "") {
                        NotificationUtil("请选择资产类别", "warning")
                        return
                    }
                    if (isNaN($("#info-0-buyCost").val())) {
                        NotificationUtil("请检查资产原值类型", "warning")
                        return
                    }
                    if (isNaN($("#info-0-estimateCost").val())) {
                        NotificationUtil("请检查暂估价值", "warning")
                        return
                    }
                    if (isNaN($("#info-0-useYears").val())) {
                        NotificationUtil("请检查使用年限类型", "warning")
                        return
                    }
                    var warranty = $("#info-0-warranty").val();
                    console.log(warranty)
                    if ($("#info-0-warranty").val() == "") {
                        NotificationUtil("请检查保质期", "warning")
                        return
                    }
                    if ($("#info-0-warranty").val() != "") {
                        if ($("#info-0-warranty").val() % 1 !== 0) {
                            NotificationUtil("请检查保质期", "warning")
                            return
                        }
                    }
                    if (isNaN($("#info-0-warranty").val())) {
                        NotificationUtil("请检查保质期", "warning")
                        return
                    }
                    eiInfo.set("createType", "write");
                    if (IPLAT.EFSelect.value("#info-0-unitNum") != ""){
                        eiInfo.set("unitName", IPLAT.EFSelect.text("#info-0-unitNum"));
                    } else {
                        eiInfo.set("unitName", "");
                    }
                    if (IPLAT.EFSelect.value("#info-0-fundingSourceNum") != ""){
                        eiInfo.set("fundingSourceName", IPLAT.EFSelect.text("#info-0-fundingSourceNum"));
                    } else {
                        eiInfo.set("fundingSourceName", "");
                    }
                    if (IPLAT.EFSelect.value("#info-0-assetGetWayCode") != ""){
                        eiInfo.set("assetGetWayName", IPLAT.EFSelect.text("#info-0-assetGetWayCode"));
                    } else {
                        eiInfo.set("assetGetWayName", "");
                    }
                    if (IPLAT.EFSelect.value("#info-0-assetUseCode") != ""){
                        eiInfo.set("assetUseName", IPLAT.EFSelect.text("#info-0-assetUseCode"));
                    } else {
                        eiInfo.set("assetUseName", "");
                    }
                    if (IPLAT.EFSelect.value("#info-0-deprectCode") != ""){
                        eiInfo.set("deprectName", IPLAT.EFSelect.text("#info-0-deprectCode"));
                    } else {
                        eiInfo.set("deprectName", "");
                    }
                    if (IPLAT.EFSelect.value("#info-0-manufacturerNatyCode") != ""){
                        eiInfo.set("manufacturerNatyName", IPLAT.EFSelect.text("#info-0-manufacturerNatyCode"));
                    } else {
                        eiInfo.set("manufacturerNatyName", "");
                    }
                    EiCommunicator.send("FADA0101", "saveFaInfo", eiInfo, {
                        onSuccess: function (ei) {
                            console.log(ei)
                            closeParentWindow()
                        }
                    })
                });

                $("#CLOSE").on("click", function (e) {
                    closeParentWindow()
                });
            }
        },
        "result1": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "ef_popup_grid_sb_fillback", text: "确定", layout: "3",
                    click: function () {
                        var checkRows = result1Grid.getCheckedRows();
                        var model = checkRows[0];
                        if (model) {
                            $("#info-0-deviceCode").val(model['machineCode']);
                            $("#info-0-deviceCode_textField").val(model['machineName']);
                            var popupGridWindow = $("#ef_popup_grid_sb").data("kendoWindow");
                            popupGridWindow.close();
                        } else {
                            NotificationUtil("请选择设备", "warning");
                        }
                    }
                }]
            },
            loadComplete: function (e) {
                $("#queryFADevice").on("click", function () {
                    result1Grid.dataSource.page(1);
                })
            }
        }
    }
});


function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultBGrid"].dataSource.page(1);
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

// 单价和资产原值双向绑定
function countBuyCost() {
    var buyCost = $("#info-0-enterPrice").val();
    $("#info-0-buyCost").val(buyCost)
}

// 资产原值和单价双向绑定
function countPrice() {
    var price = $("#info-0-buyCost").val();
    $("#info-0-enterPrice").val(price)
}

// 计算
function countEnterAmount() {
    // 计算入库总价
    var enterNum = IPLAT.EFInput.value($("#info-0-enterNum"));
    var enterPrice = IPLAT.EFInput.value($("#info-0-enterPrice"));
    var enterAmount = enterNum * enterPrice;
    IPLAT.EFInput.value($("#info-0-enterAmount"), enterAmount);
}

function countEstimatedResidualValue() {
    var estimatedResidualValue = $("#info-0-buyCost").val() * $("#info-0-residualRate").val() / 100
    $("#info-0-estimatedResidualValue").val(estimatedResidualValue)
}

function countMonthDepreciation() {
    var monthDepreciation = $("#info-0-buyCost").val() / $("#info-0-useYears").val() / 12
    $("#info-0-monthDepreciation").val(monthDepreciation)
}

function countEquityFund() {
    var equityFund = $("#info-0-equityFund").val();
    var buyCost = $("#info-0-buyCost").val();
    $("#info-0-otherFund").val(buyCost - equityFund);
}

function countOtherFund() {
    var otherFund = $("#info-0-otherFund").val();
    var buyCost = $("#info-0-buyCost").val();
    $("#info-0-equityFund").val(buyCost - otherFund);
}