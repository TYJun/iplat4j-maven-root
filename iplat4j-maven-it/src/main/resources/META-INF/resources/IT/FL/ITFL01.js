$(function () {
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1)
    });

    $("#REQUERY").on("click", function (e) {
        $("#typeId").val("")
        $("#typeName").val("")
        $("#inqu_status-0-classifyNum").val("")
        $("#inqu_status-0-classifyName").val("")
        resultGrid.dataSource.page(1)
    });

    // 定义结点的各属性名
    var NODE = {
        nodeldField: "id",
        nodeTextField: "classifyName",
        parentIdField: "parentId",
        nodeTypeField: "isLeaf"
    };


    IPLATUI.EFTree = {
        "menu": {
            ROOT: {
                id: "root",
                classifyName: "根节点",
                isLeaf: true
            },
            /**
             * 树节点内容点击触发回调函数（点击节点内容前展开收起符号不会触发）
             */
            select: function (e) {
                var _data = this.dataItem(e.node);
                $("#menu").val(_data[NODE.nodeldField]);
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
                saveExpanded()
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
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "enter",
                        text: "录入",
                        layout: "3",
                        click: function () {
                            popDataWindow.setOptions({"title": "独立任务类别录入"});
                            customWindow("enter", "", $("#typeId").val(), $("#typeName").val())
                        }
                    },
                    {
                        name: "edit",
                        text: "编辑",
                        layout: "3",
                        click: function () {
                            if (checkRows()) {
                                popDataWindow.setOptions({"title": "独立任务类别编辑"});
                                customWindow("edit", checkRows().itClassifyId)
                            }
                        }
                    },
                    {
                        name: "remove",
                        text: "删除",
                        layout: "3",
                        click: function () {
                            if (checkRows()) {
                                IPLAT.confirm({
                                    message: '<b style="color: red">您确定要删除吗？该操作不可恢复，并且删除该节点下的所有节点！</b>',
                                    okFn: function (e) {
                                        var eiInfo = new EiInfo();
                                        eiInfo.set("itClassifyId", checkRows().itClassifyId);
                                        EiCommunicator.send("ITFL01", "remove", eiInfo, {
                                            onSuccess: function (eiInfo) {
                                                if (eiInfo.getStatus() == 199) {
                                                    NotificationUtil("删除失败，请先删除正在使用的任务分类名称","warning")
                                                } else {
                                                    NotificationUtil("删除成功")
                                                    window.location.reload()
                                                }
                                            }
                                        });

                                    },
                                    cancelFn: function (e) {
                                    }
                                })
                            }
                        }
                    },
                ]
            },
            loadComplete: function (grid) {

            }
        }
    };
})

// 自定义弹窗
function customWindow(type, id, typeId, typeName) {
    var url = IPLATUI.CONTEXT_PATH + "/web/ITFL0101?initLoad&itClassifyId=" + id + "&type=" + type + "&parentId=" + typeId + "&parentName=" + typeName;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url,
            });
        }
    });
    // 新窗口打开居中
    popDataWindow.open().center();
}

// 选中行
function checkRows() {
    const checkRows = resultGrid.getCheckedRows();
    if (checkRows.length < 1) {
        NotificationUtil("请先选择一条记录", "error");
        return false;
    } else {
        return checkRows[0];
    }
}