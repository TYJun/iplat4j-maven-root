$(function () {
    /**回车键查询**/
    keydown("inqu", "QUERY");

    // 查询
    $("#QUERY").on("click", function (e) {
        resultPersonGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultPersonGrid.dataSource.page(1);
    });

    // 定义结点的各属性名
    var NODE = {
        nodeldField: "roleCode",
        nodeTextField: "roleName",
        parentIdField: "parentId",
        nodeTypeField: "isLeaf"
    };

    IPLATUI.EFTree = {
        "menu": {
            ROOT: {
                roleCode: "root",
                roleName: "人员权限",
                isLeaf: true
            },
            // template:"#= item.roleName # [#= item.roleCode #]",
            /**
             * 树节点内容点击触发回调函数（点击节点内容前展开收起符号不会触发）
             */
            select: function (e) {
                console.log(e)
                var _data = this.dataItem(e.node);
                if (_data[NODE.nodeldField] != "root"){
                    $("#info-0-roleCode").val(_data[NODE.nodeldField]);
                    $("#info-0-roleName").val(_data[NODE.nodeTextField]);
                } else {
                    $("#info-0-roleCode").val("");
                    $("#info-0-roleName").val("");
                }
            },
            /**
             * 树加载完成后的回调函数
             * @param options: 树的配置项
             */
            loadComplete: function (options) {

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
        "resultPerson": {
            pageable: {
                pageSize: 50
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "addPersonnel",
                        text: "添加人员",
                        layout: "3",
                        click: function () {
                            var roleCode = IPLAT.EFInput.value($("#info-0-roleCode"));
                            var roleName = IPLAT.EFInput.value($("#info-0-roleName"));
                            var deptNum = IPLAT.EFInput.value($("#info-0-deptNum"));
                            var deptName = IPLAT.EFInput.value($('#info-0-deptName'));
                            var checkedRows = resultPersonGrid.getCheckedRows();
                            if (roleCode != ""){
                                if (checkedRows.length > 0) {
                                    var eiInfo = new EiInfo();
                                    eiInfo.set("roleCode", roleCode)
                                    eiInfo.set("roleName", roleName)
                                    eiInfo.set("deptNum", deptNum)
                                    eiInfo.set("deptName", deptName)
                                    eiInfo.set("workNo", checkedRows[0].workNo)
                                    eiInfo.set("name", checkedRows[0].name)
                                    eiInfo.set("name", checkedRows[0].name)
                                    EiCommunicator.send("FASQ00", "addPersonnel", eiInfo, {
                                        onSuccess: function (ei) {
                                            if (ei.getStatus() == -1) {
                                                NotificationUtil(ei.getMsg(), "warning");
                                                return;
                                            } else {
                                                NotificationUtil(ei.getMsg(), "success");
                                                window.parent["resultGrid"].dataSource.page(1);
                                                return;
                                            }
                                        }
                                    });
                                } else {
                                    IPLAT.NotificationUtil("请勾选平台人员信息进行授权", "warning")
                                    return;
                                }
                            } else {
                                IPLAT.NotificationUtil("请选择人员权限进行授权", "warning")
                                return;
                            }
                        }
                    },
                ]
            },
        }
    }
});
