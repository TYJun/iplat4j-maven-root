$(function () {
    /**回车键查询**/
    keydown("inqu", "QUERY");

    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1)
    });

    $("#REQUERY").on("click", function (e) {
        // 避免重置查出所有
        // $("#inqu_status-0-faTypeId").val("")
        $("#inqu_status-0-typeCode").val("")
        $("#inqu_status-0-typeName").val("")
        $("#inqu_status-0-parentName").val("")
        $("#inqu_status-0-codeRule").val("")
        $("#inqu_status-0-useYearsS").val("")
        $("#inqu_status-0-useYearsE").val("")
        $("#inqu_status-0-remark").val("")
        resultGrid.dataSource.page(1)
    });

    $("#download").click(function () {
        window.location.href = IPLATUI.CONTEXT_PATH + "/fa/FALBImport";
    });


    $("#IMPORTSUBMIT").on("click", function (e) {
        var uploading = false;
        // 防止连续提交
        $("#SUBMIT").attr("disabled", true);
        setTimeout(function () {
            $("#SUBMIT").attr("disabled", false);
        }, 5000);
        //参数处理
        var form = new FormData();
        form.append("fileUpload", $('#excelFile')[0].files[0]);
        //数据校验
        if ($('#excelFile')[0].files[0] == null) {
            NotificationUtil("请上传文件", "warning");
            return;
        }
        if (uploading) {
            NotificationUtil("数据正在提交中，请不要重复点击提交", "warning");
            return;
        }
        //数据提交
        $.ajax({
            url: IPLATUI.CONTEXT_PATH + "/fa/FALBImport",
            type: 'POST',
            cache: false,
            processData: false,
            contentType: false,
            dataType: 'json',
            data: form,
            beforeSend: function () {
                uploading = true;
            },
            success: function (data) {
                uploading = false;
                if (data.msg == "all") {
                    NotificationUtil("数据导入成功", "success");
                    downloadFileByBase64('data:application/xls;base64,' + data.base64, "FALB_success.xls");
                    window['execlImportWindow'].close();
                    setTimeout(function () {
                        window.parent.location.reload()
                    }, 600);
                } else if (data.msg == "part") {
                    NotificationUtil("导入数据存在问题，请查看返回文件", "warning");
                    downloadFileByBase64('data:application/xls;base64,' + data.base64, "FALB_error.xls");
                    window['execlImportWindow'].close();
                    setTimeout(function () {
                        window.parent.location.reload()
                    }, 600);
                } else if (data.msg == "error") {
                    NotificationUtil("数据导入失败", "warning");
                    window['execlImportWindow'].close();
                }
            }
        });
    });

    /**
     * 关闭数据导入窗口
     */
    $("#IMPORTCLOSE").on("click", function (e) {
        execlImportWindow.close();
    });

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
                // // 保持节点展开状态
                // var expanded = Cookies.get('expanded');
                // if (expanded) {
                //     Cookies.remove('expanded');
                //     expanded = JSON.parse(expanded);
                //     $("#menu").data("kendoTreeView").expandPath(
                //         _.keys(expanded)
                //     );
                // }
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
                        name: "upload",
                        text: "数据导入",
                        layout: "3",
                        click: function () {
                            execlImportWindow.open().center()
                        }
                    },
                    {
                        name: "enter",
                        text: "录入",
                        layout: "3",
                        click: function () {
                            popDataWindow.setOptions({"title": "资产类别录入"});
                            var typeName;
                            if ($("#typeName").val().includes(']')){
                                typeName = $("#typeName").val().split(']')[1];
                            } else {
                                typeName = $("#typeName").val();
                            }
                            fixedAssetsWindow("enter", "", $("#typeId").val(), typeName)
                        }
                    },
                    {
                        name: "edit",
                        text: "编辑",
                        layout: "3",
                        click: function () {
                            if (checkRows()) {
                                popDataWindow.setOptions({"title": "资产类别编辑"});
                                fixedAssetsWindow("edit", checkRows())
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
                                        let eiInfo = new EiInfo();
                                        eiInfo.set("faTypeId", checkRows());
                                        EiCommunicator.send("FALB01", "delete", eiInfo, {
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
                        }
                    },
                ]
            },
            loadComplete: function (grid) {

            }
        }
    };
})

// 自定义固定资产弹窗
function fixedAssetsWindow(type, id, typeId, typeName) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FALB0101?initLoad&faTypeId=" + id + "&type=" + type + "&typeId=" + typeId + "&typeName=" + typeName;
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

function checkRows() {
    const checkRows = resultGrid.getCheckedRows();
    if (checkRows.length < 1) {
        NotificationUtil("请先选择一条记录", "warning");
        return false;
    } else {
        return checkRows[0].faTypeId;
    }
}

/**
 * 将文件base64转成文件
 * @param base64
 * @param name
 */
function downloadFileByBase64(base64, name) {
    let myBlob = dataURLtoBlob(base64)
    let myUrl = URL.createObjectURL(myBlob)
    let failFile = document.createElement("a")//创建a标签
    failFile.setAttribute("href", myUrl)
    failFile.setAttribute("download", name)
    failFile.setAttribute("target", "_blank")
    let clickEvent = document.createEvent("MouseEvents");
    clickEvent.initEvent("click", true, true);
    failFile.dispatchEvent(clickEvent);
}

/**
 * 将base64转成数据流
 * @param dataurl
 * @returns {Blob}
 */
function dataURLtoBlob(dataurl) {
    let arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new Blob([u8arr], {type: mime});
}

