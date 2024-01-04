$(function () {
    /**回车键查询**/
    keydown("inqu", "QUERY");

    var parentId = $("#info-0-parentId").val();
    var goodsClassifyName = $("#info-0-goodsClassifyName").val();
    IPLAT.EFInput.value($("#info-0-purchaseDept"), __ei.deptName);
    $("#info-0-equityFund").val($("#info-0-buyCost").val());
    $("#info-0-otherFund").val(0.00);
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
    // 回显资产类别
    // IPLAT.EFPopupInput.setAllFields($("#info-0-goodsTypeCode"), __ei.goodsTypeCode, __ei.goodsTypeName)
    IPLAT.EFPopupInput.setAllFields($("#info-0-surpNum"), __ei.surpNum, __ei.surpName)
    // IPLAT.EFPopupInput.enable($("#info-0-surpNum"), false)
    $("#info-0-confirmDeptNum").val(__ei.deptNum);
    $("#info-0-confirmDeptName").val(__ei.deptName);
    $("#info-0-deptName").val(__ei.deptName);

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
                id: parentId,
                typeName: goodsClassifyName,
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
            clearInput: function (e) {
                IPLAT.EFPopupInput.clear($("#info-0-confirmLocationNum"), true)
                IPLAT.EFInput.value($("#info-0-confirmBuild"), "");
                IPLAT.EFInput.value($("#info-0-confirmFloor"), "");
            }
        },
        "info-0-goodsCategoryCode": {
            clearInput: function (e) {
                IPLAT.EFPopupInput.clear($("#info-0-goodsCategoryCode"), true)
            }
        }
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
                            $("#info-0-goodsCategoryCode").val(model['typeCode']);
                            $("#info-0-goodsCategoryCode_textField").val(model['typeName']);
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
                    $("#SAVE").attr("disabled",true);
                    var eiInfo = new EiInfo();
                    eiInfo.setByNode("info");
                    var validator = IPLAT.Validator({
                        id: "info"
                    });
                    var costType = IPLAT.EFSelect.value($("#info-0-costType"));
                    var receiveType = $("#receiveType").val();
                    if (costType == "00") {
                        $("#info-0-estimateCost").val(0);
                    } else {
                        $("#info-0-buyCost").val(0);
                    }
                    validator.validate();
                    if ($("#info-0-purchaseDept").val() == "") {
                        NotificationUtil("请填写采购科室", "warning")
                        $("#SAVE").attr("disabled", false);
                        return
                    }
                    if ($("#info-0-goodsName").val() == "") {
                        NotificationUtil("请填写资产名称", "warning")
                        $("#SAVE").attr("disabled", false);
                        return
                    }
                    if ($("#info-0-goodsTypeCode").val() == "") {
                        NotificationUtil("请选择资产类别", "warning")
                        $("#SAVE").attr("disabled", false);
                        return
                    }
                    if ($("#info-0-goodsCategoryCode").val() == ""){
                        NotificationUtil("请选择资产末级", "warning")
                        $("#SAVE").attr("disabled", false);
                        return
                    }
                    if (isNaN($("#info-0-buyCost").val())) {
                        NotificationUtil("请检查资产原值类型", "warning")
                        $("#SAVE").attr("disabled", false);
                        return
                    }
                    if (isNaN($("#info-0-warranty").val())) {
                        NotificationUtil("请检查保修期类型", "warning")
                        $("#SAVE").attr("disabled", false);
                        return
                    }
                    if (isNaN($("#info-0-estimateCost").val())) {
                        NotificationUtil("请检查暂估价值", "warning")
                        $("#SAVE").attr("disabled", false);
                        return
                    }
                    if (isNaN($("#info-0-useYears").val())) {
                        NotificationUtil("请检查使用年限类型", "warning")
                        $("#SAVE").attr("disabled", false);
                        return
                    }
                    eiInfo.set("createType", "card");
                    eiInfo.set("receiveType", receiveType);
                    if (IPLAT.EFSelect.value("#info-0-unitNum") != "") {
                        eiInfo.set("unitName", IPLAT.EFSelect.text("#info-0-unitNum"));
                    } else {
                        eiInfo.set("unitName", "");
                    }
                    if (IPLAT.EFSelect.value("#info-0-fundingSourceNum") != "") {
                        eiInfo.set("fundingSourceName", IPLAT.EFSelect.text("#info-0-fundingSourceNum"));
                    } else {
                        eiInfo.set("fundingSourceName", "");
                    }
                    if (IPLAT.EFSelect.value("#info-0-assetGetWayCode") != "") {
                        eiInfo.set("assetGetWayName", IPLAT.EFSelect.text("#info-0-assetGetWayCode"));
                    } else {
                        eiInfo.set("assetGetWayName", "");
                    }
                    if (IPLAT.EFSelect.value("#info-0-assetUseCode") != "") {
                        eiInfo.set("assetUseName", IPLAT.EFSelect.text("#info-0-assetUseCode"));
                    } else {
                        eiInfo.set("assetUseName", "");
                    }
                    if (IPLAT.EFSelect.value("#info-0-deprectCode") != "") {
                        eiInfo.set("deprectName", IPLAT.EFSelect.text("#info-0-deprectCode"));
                    } else {
                        eiInfo.set("deprectName", "");
                    }
                    if (IPLAT.EFSelect.value("#info-0-manufacturerNatyCode") != "") {
                        eiInfo.set("manufacturerNatyName", IPLAT.EFSelect.text("#info-0-manufacturerNatyCode"));
                    } else {
                        eiInfo.set("manufacturerNatyName", "");
                    }
                    // 资产末级校验
                    var goodsCategoryCodeStr = $("#info-0-goodsCategoryCode").val();
                    var goodsClassifyCodeStr = $("#info-0-goodsClassifyCode").val();
                    var goodsCategoryCodeStr1 = goodsCategoryCodeStr.substring(0,1);
                    var goodsClassifyCodeStr1 = goodsClassifyCodeStr.substring(0,1);
                    if (goodsCategoryCodeStr1 == 'B' && goodsClassifyCodeStr1 == 'B') {
                        goodsCategoryCodeStr = goodsCategoryCodeStr.substring(1,3);
                        goodsClassifyCodeStr = goodsClassifyCodeStr.substring(1,3);
                    } else {
                        goodsCategoryCodeStr = goodsCategoryCodeStr.substring(1,5);
                        goodsClassifyCodeStr = goodsClassifyCodeStr.substring(1,5);
                    }
                    if (goodsCategoryCodeStr != goodsClassifyCodeStr){
                        NotificationUtil("请检查资产末级与资产类别是否属于同一类别", "warning")
                        $("#SAVE").attr("disabled", false);
                        return
                    }
                    loadingShow();
                    EiCommunicator.send("FADA0101", "saveFaInfo", eiInfo, {
                        onSuccess: function (ei) {
                            loadingRemove();
                            closeParentWindow()
                            $("#SAVE").attr("disabled", false);
                        }
                    })
                });

                $("#CLOSE").on("click", function (e) {
                    closeParentWindow()
                });
            }
        },
    }
});


function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultBGrid"].dataSource.page(1);
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

//显示转圈中等待
function loadingShow() {
    qrCodeWindow.setOptions({"title": "提交中，请稍等..."});
    qrCodeWindow.open().center();
    var parent = $("#qrCode");
    var loading = '<i id="loading"  class="fa fa-spinner fa-spin fa-3x fa-fw" style="margin: auto;text-align: center"></i>';
    var div = $(loading);
    div.appendTo(parent);
}

//移除转圈中等待
function loadingRemove() {
    qrCodeWindow.close();
}