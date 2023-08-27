$(function () {

});

// 定义结点的各属性名
var NODE = {
    nodeldField: "id",
    nodeValueField: "deptNum",
    nodeTextField: "deptName",
    parentIdField: "parentId",
    nodeTypeField: "isLeaf"
};


IPLATUI.EFTree = {
    "menu": {
        ROOT: {
            id: "root",
            deptName: "根节点",
            isLeaf: true
        },
        /**
         * 树节点内容点击触发回调函数（点击节点内容前展开收起符号不会触发）
         */
        select: function (e) {
            var _data = this.dataItem(e.node);
            $("#menu").val(_data[NODE.nodeldField]);
            $("#info-0-parentId").val(_data[NODE.parentIdField]);
            $("#info-0-identifyDeptNum").val(_data[NODE.nodeValueField]);
            $("#info-0-identifyDeptName").val(_data[NODE.nodeTextField]);
            $("#info-0-id").val(_data[NODE.nodeldField]);
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
    }
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
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [

            ]
        },
        onCellClick: function (e) {
            if (e.field === "goodsNum") {
                popDataWindow.setOptions({"title": "资产卡片详情"});
                fixedAssetsWindowDetail(e.model.faInfoId)
            }
        },
        onCheckRow: function (e) {
            var grid = e.sender,
                model = e.model,
                $tr = e.tr,
                row = e.row;
            $tr.css({
                background: "white",
                color: "#000000A6"
            });
            grid.unCheckAllRows();
        },
        loadComplete: function (grid) {
            var checkRows = window.parent.resultBGrid.getCheckedRows()
            if (checkRows.length > 0) {
                grid.addRows(checkRows);
                grid.unCheckAllRows();
            }
            $("#SUMBIT").on("click", function (e) {
                var checkRows = resultGrid.getDataItems()
                var eiInfo = new EiInfo();
                var goodsList = [];
                var detailIdList = [];
                var map = new Map();
                for (let i = 0; i < checkRows.length; i++) {
                    detailIdList.push(checkRows[i].detailId);
                    var goodsClassifyCode = checkRows[i].goodsClassifyCode;
                    var goodsClassifyName = checkRows[i].goodsClassifyName;
                    if (map.has(goodsClassifyCode)){
                        continue;
                    } else {
                        var goods = {
                            goodsClassifyCode:"",
                            goodsClassifyName:""
                        }
                        map.set(goodsClassifyCode,goodsClassifyName);
                        goods.goodsClassifyCode = goodsClassifyCode;
                        goods.goodsClassifyName = goodsClassifyName;
                        goodsList.push(goods)
                    }
                }
                eiInfo.set("goods",goodsList);
                eiInfo.set("detailIdList",detailIdList);
                eiInfo.setByNode("info")
                if ($("#info-0-identifyDeptName").val() == "") {
                    NotificationUtil("请选择鉴定科室", "warning")
                    return
                }
                // 资产指定鉴定科室，并将资产类别保存进鉴定科室
                EiCommunicator.send("FABF0101", "sumbitFaScrapInfo", eiInfo, {
                    onSuccess: function (ei) {
                        closeParentWindow()
                    }
                })
            });

            $("#CLOSE").on("click", function (e) {
                closeParentWindow();
            });
        }
    },
}

function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultBGrid"].dataSource.page(1);
}