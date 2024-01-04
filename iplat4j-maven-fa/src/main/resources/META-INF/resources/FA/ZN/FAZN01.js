$(function () {
    /**回车键查询**/
    keydown("inqu", "QUERY");

    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1)
    });

    $("#REQUERY").on("click", function (e) {
        $("#inqu_status-0-identifyDeptName").val("")
        $("#inqu_status-0-functionDeptName").val("")
        resultGrid.dataSource.page(1)
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
                $("#inqu_status-0-deptId").val(_data[NODE.nodeldField]);
                $("#functionDeptNum").val(_data[NODE.nodeValueField]);
                $("#functionDeptName").val(_data[NODE.nodeTextField]);
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
            pageable: {
                pageSize: 50,
                pageSizes: [50, 100, 500, 1000]
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "enter",
                        text: "录入",
                        layout: "3",
                        click: function () {
                            popDataWindow.setOptions({"title": "科室绑定"});
                            fixedAssetsWindow("enter", $("#functionDeptCode").val(), $("#functionDeptName").val())
                        }
                    },
                    {
                        name: "remove",
                        text: "删除",
                        layout: "3",
                        click: function () {
                            var checkRows = resultGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                if (checkRows.length > 1) {
                                    NotificationUtil("请勿选择多行", "error");
                                } else {
                                    IPLAT.confirm({
                                        message: '<b style="color: red">您确定要删除吗？该操作不可恢复，并且删除该节点下的所有节点！</b>',
                                        okFn: function (e) {
                                            let eiInfo = new EiInfo();
                                            eiInfo.set("deptId", checkRows[0].deptId);
                                            EiCommunicator.send("FAZN01", "delete", eiInfo, {
                                                onSuccess: function (eiInfo) {
                                                    if (eiInfo.getStatus() == 200) {
                                                        window.location.reload()
                                                    }
                                                    NotificationUtil(eiInfo.getMsg());
                                                }
                                            });
                                        },
                                        cancelFn: function (e) {
                                        }
                                    })
                                }
                            } else {
                                NotificationUtil("请选择要删除的行", "error");
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

// 自定义资产弹窗
function fixedAssetsWindow(type, functionDeptCode, functionDeptName) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FAZN0101?initLoad&type=" + type + "&functionDeptCode=" + functionDeptCode + "&functionDeptName=" + functionDeptName;
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