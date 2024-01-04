$(function () {
    /**回车键查询**/
    keydown("inqu", "QUERY");
    // 查询
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#REQUERY").on("click", function(e) {
        document.getElementById("inqu-trash").click();
        var deptMenu = $("#deptMenu").data("kendoTreeView");
        var tree = $("#menu").data("kendoTreeView");
        deptMenu.reload("root");
        tree.reload("root");
        resultGrid.dataSource.page(1);
    });

    var dataSource = new kendo.data.DataSource({
        transport: {
            read: {
                url: IPLATUI.CONTEXT_PATH + "/service/FASQ01/queryDeptTree",
                type: 'POST',
                dataType: "json",
                contentType: "application/json"
            },
            parameterMap: function (options, operation) {
                var info = new EiInfo();
                info.set("deptName",$("#info-0-deptName").val());
                return info.toJSONString();
            }
        },
        schema: {
            data: function (response) {
                ajaxEi = EiInfo.parseJSONObject(response);
                return ajaxEi.getBlock("root").getMappedRows();
            }
        },
        pageSize: 1000,
        serverFiltering: true
    });

    var options = {
        url: ctx,
        serviceName: "FASQ01",
        methodName: "queryDeptTree",
        textField: "deptName",
        valueField: "deptCode",
        hasChildren: "isLeaf"
    };

    $("#info-0-deptName").kendoAutoComplete({
        dataSource: dataSource,
        minLength: 1,
        enforceMinLength: true,
        select: function(e) {
            var treeview = $("#deptMenu").data("kendoTreeView");
            var selectName = e.dataItem.deptName + " [" + e.dataItem.deptCode + "]";
            var decor = treeview.findByText(selectName);
            if(e.dataItem.deptCode != "root"){
                $("#inqu_status-0-deptNum").val(e.dataItem.deptCode);
                $("#inqu_status-0-deptName").val(e.dataItem.deptName);
            } else {
                $("#inqu_status-0-deptNum").val("");
                $("#inqu_status-0-deptName").val("");
            }
            treeview.select(decor);
            IPLATUI.EFTree["deptMenu"].select(decor);
        },
        dataTextField: "deptName",
        dataValueField: "deptCode",
    })

    // 定义结点的各属性名
    var NODE = {
        nodeldField: "roleCode",
        nodeTextField: "roleName",
        parentIdField: "parentId",
        nodeTypeField: "isLeaf"
    };

    // 定义结点的各属性名
    var DEPTNODE = {
        nodeldField: "deptCode",
        nodeTextField: "deptName",
        parentIdField: "parentId",
        nodeTypeField: "isLeaf"
    };

    IPLATUI.EFTree = {
        "deptMenu":{
            ROOT: {
                deptCode: "root",
                deptName: "业务科室",
                isLeaf: true
            },
            template:"#= item.deptName # [#= item.deptCode #]",
            /**
             * 树节点内容点击触发回调函数（点击节点内容前展开收起符号不会触发）
             */
            select: function (e) {
                var eleTop = "", _data = "";
                if (e.node){
                    eleTop = $(e.node).offset().top;
                    _data = this.dataItem(e.node);
                    if (_data[DEPTNODE.nodeldField] != "root"){
                        $("#inqu_status-0-deptNum").val(_data[DEPTNODE.nodeldField]);
                        $("#inqu_status-0-deptName").val(_data[DEPTNODE.nodeTextField]);
                    } else {
                        $("#inqu_status-0-deptNum").val("");
                        $("#inqu_status-0-deptName").val("");
                    }
                } else {
                    eleTop = $(e[0]).offset().top;
                }
                var treeScrollTop = $("#deptMenu").scrollTop();
                var treeTop = $("#deptMenu").offset().top;
                $("#deptMenu").animate({
                    scrollTop: (treeScrollTop + eleTop) - treeTop
                });
                resultGrid.dataSource.page(1);
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
        },
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
                var _data = this.dataItem(e.node);
                if (_data[NODE.nodeldField] != "root"){
                    $("#inqu_status-0-roleCode").val(_data[NODE.nodeldField]);
                    $("#inqu_status-0-roleName").val(_data[NODE.nodeTextField]);
                } else {
                    $("#inqu_status-0-roleCode").val("");
                    $("#inqu_status-0-roleName").val("");
                }
                resultGrid.dataSource.page(1);
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
                        name: "quickRoles",
                        text: "快捷授权",
                        layout: "3",
                        click: function () {
                            popData2Window.setOptions({"title":"资产快捷授权"});
                            var deptNum = $("#inqu_status-0-deptNum").val()
                            var deptName = $("#inqu_status-0-deptName").val()
                            if (deptName != "" && deptNum != ""){
                                quickPermissionsWindow(deptNum,deptName);
                            } else {
                                IPLAT.NotificationUtil("请先选择业务科室", "warning")
                                return;
                            }
                        }
                    },
                    // {
                    //     name: "addRoles",
                    //     text: "新增",
                    //     layout: "3",
                    //     click: function () {
                    //         popDataWindow.setOptions({"title":"新增资产授权关系"});
                    //         permissionsWindow("enter","");
                    //     }
                    // },
                    // {
                    //     name: "editRoles",
                    //     text: "编辑",
                    //     layout: "3",
                    //     click: function () {
                    //         popDataWindow.setOptions({"title":"编辑资产授权关系"});
                    //         permissionsWindow("edit","");
                    //     }
                    // },
                    {
                        name: "deleteRoles",
                        text: "删除",
                        layout: "3",
                        click: function () {
                            var checkRows = resultGrid.getCheckedRows();
                            if (checkRows.length > 0){
                                IPLAT.confirm({
                                    message: '<b>确定执行该操作吗？</b>',
                                    okFn: function (e) {
                                        var eiInfo = new EiInfo();
                                        eiInfo.set("workNo",checkRows[0].workNo);
                                        eiInfo.set("roleCode",checkRows[0].roleCode);
                                        eiInfo.set("deptNum",checkRows[0].deptNum);
                                        EiCommunicator.send("FASQ01", "deleteRoles", eiInfo, {
                                            onSuccess : function(ei) {
                                                if(ei.getStatus() === -1){
                                                    IPLAT.alert(ei.getMsg());
                                                }else{
                                                    NotificationUtil("操作成功", "success")
                                                    resultGrid.dataSource.page(1);
                                                }
                                            }
                                        })
                                    },
                                    cancelFn: function (e) {
                                    }
                                })
                            } else {
                                IPLAT.NotificationUtil("请选择需要删除的记录", "warning")
                                return;
                            }
                        }
                    },
                ]
            },
        }
    }
});

// 自定义资产弹窗
function permissionsWindow(type, permissions) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FASQ0101?initLoad&permissions=" + permissions + "&type=" + type;
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

// 快捷授权
function quickPermissionsWindow(deptNum,deptName) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FASQ00?initLoad&deptNum=" + deptNum + "&deptName=" + deptName;
    var popData = $("#popData2");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url,
            });
        }
    });
    // 新窗口打开居中
    popData2Window.open().center();
}