$(function () {
    $("#QUERY").on("click", function (e) {
        resultAGrid.dataSource.page(1);
        // resultBGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
        resultDGrid.dataSource.page(1);
        resultEGrid.dataSource.page(1);
    });

    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resultAGrid.dataSource.page(1);
        // resultBGrid.dataSource.page(1);
        resultCGrid.dataSource.page(1);
        resultDGrid.dataSource.page(1);
        resultEGrid.dataSource.page(1);
    });

    IPLATUI.EFTab = {
        "FaDaTab": {
            select: function (e) {
                var grid = $(e.contentElement).find("div.k-grid").data("kendoGrid")
                if (grid != undefined) {
                    if (grid.options.blockId === "resultA") {
                        if (__ei.role == "admin") {
                            $("#role").show();
                        }
                        $("#one").show();
                        $("#other").hide();
                    } else {
                        $("#one").hide();
                        $("#role").hide();
                        $("#other").show();
                    }
                    setTimeout(function () {
                        grid.dataSource.page(1);
                        if (grid.options.blockId === "resultC"){
                            resultDetailsC2Grid.dataSource.page(1);
                        } else if (grid.options.blockId === "resultD"){
                            resultDetailsD2Grid.dataSource.page(1);
                        } else if (grid.options.blockId === "resultE"){
                            resultDetailsE2Grid.dataSource.page(1);
                        }
                    }, 500)
                }
            }
        }
    }

    IPLATUI.EFGrid = {
        "resultA": {
            pageable: {
                pageSize: 500,
                pageSizes: [15, 50, 100, 500]
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: []
            },
            onCellClick: function (e) {
                if (e.field === "goodsNum") {
                    popDataWindow.setOptions({"title": "资产卡片详情"});
                    fixedAssetsWindowDetail(e.model.faInfoId)
                }
            },
            loadComplete: function (grid) {
                var myArraySize = 0,
                    myArrayMoney = 0;
                if (localStorage.getItem("myArray") != null){
                    myArraySize = [...new Set(JSON.parse(localStorage.getItem("myArray")))].length.toFixed(2);
                }
                if (localStorage.getItem("myArrayMoney") != null){
                    myArrayMoney = Number(localStorage.getItem("myArrayMoney")).toFixed(2);
                }
                $("#ef_grid_toolbar_resultA").prepend("<div id='storageCount' style='float:left;font-size:13px;'>" +
                    "勾选缓存记录：<span id='numberCount' style='color: red'>" + myArraySize + "</span>条," +
                    "勾选记录金额：<span id='numberMoney' style='color: red'>" + myArrayMoney + "</span>元</div>")

                // 仓库调拨申请
                $("#ADMINAPPLY").on("click", function (e) {
                    var myArraySize = [...new Set(JSON.parse(localStorage.getItem("myArray")))].length.toFixed(2);
                    var checkRows = resultAGrid.getCheckedRows();
                    for (let i = 0; i < checkRows.length; i++) {
                        if (checkRows[i].statusCode == "调拨中") {
                            NotificationUtil("权限不足，请选择在用或待用状态的资产", "warning");
                            return
                        }
                    }
                    if (checkRows.length > 0 || Number(myArraySize) > 0){
                        popDataWindow.setOptions({"title": "仓库调拨申请"});
                        fixedAssetsDetailWindow("admin", "")
                    } else {
                        NotificationUtil("请选择需要调拨的资产", "warning");
                        return
                    }
                });

                // 科室调拨申请
                $("#TRANSFERAPPLY").on("click", function (e) {
                    popDataWindow.setOptions({"title": "科室调拨申请"});
                    var checkRows = resultAGrid.getCheckedRows();
                    // 是否存在电签--后续补充一下
                    if (checkRows.length > 0) {
                        localStorage.removeItem("myArray")
                        $("#numberCount").text(0.00);
                        var flag = true;
                        for (let i = 0; i < checkRows.length; i++) {
                            if (checkRows[i].statusCode == "待用" || checkRows[i].statusCode == "调拨中") {
                                NotificationUtil("权限不足，请选择在用状态的资产", "warning");
                                flag = false;
                                return
                            }
                        }
                        // for (let i = 0; i < checkRows.length; i++) {
                        //     if (__ei.deptName != checkRows[i].deptName) {
                        //         NotificationUtil("权限不足，非当前科室无法进行调拨", "warning");
                        //         return;
                        //     }
                        // }
                        if (flag) {
                            // fixedAssetsTransferWindow("enter", "fileCode");
                            // setConfig("testzw", "赵伟", "FA");
                            setConfig(__ei.workNo, __ei.name, "FA");
                            getSign(fileCode => {
                                if (fileCode) {
                                    fixedAssetsTransferWindow("enter", fileCode);
                                } else {
                                    NotificationUtil("电子签名校验失败", "warning");
                                }
                            });
                        }
                    } else {
                        NotificationUtil("请选择一条记录", "warning");
                    }
                })
            },
            onCheckRow: function (e) {
                // 缓存选中记录
                var myArray = localStorage.getItem("myArray");
                var myArrayMoney = localStorage.getItem("myArrayMoney");
                var newArray = [];
                var newMyArrayMoney = 0;
                if (myArray != null) {
                    newArray = JSON.parse(localStorage.getItem("myArray"))
                }
                if (myArrayMoney != null) {
                    newMyArrayMoney = Number(myArrayMoney);
                }
                if (e.checked){
                    var checkRows = resultAGrid.getCheckedRows()
                    for (let i = 0; i < checkRows.length; i++) {
                        newArray.push(checkRows[i].goodsNum)
                    }
                    if ([...new Set(newArray)].length.toFixed(2) != [...new Set(JSON.parse(myArray))].length.toFixed(2)){
                        newMyArrayMoney = Number(newMyArrayMoney) + Number(e.model.buyCost)
                    }
                    localStorage.setItem("myArray",JSON.stringify([...new Set(newArray)]))
                    localStorage.setItem("myArrayMoney",Number(newMyArrayMoney))
                } else {
                    for (let i = 0; i < newArray.length; i++) {
                        if (newArray[i] == e.model.goodsNum){
                            newArray.splice(i,1)
                        }
                    }
                    newArray = newArray.filter(item => item !== e.model.goodsNum);
                    if ([...new Set(newArray)].length.toFixed(2) != [...new Set(JSON.parse(myArray))].length.toFixed(2)){
                        newMyArrayMoney = Number(newMyArrayMoney) - Number(e.model.buyCost)
                    }
                    localStorage.setItem("myArray",JSON.stringify([...new Set(newArray)]))
                    localStorage.setItem("myArrayMoney",Number(newMyArrayMoney))
                }
                $("#numberCount").text([...new Set(newArray)].length.toFixed(2));
                $("#numberMoney").text(Number(newMyArrayMoney).toFixed(2));
            }
        },
        "resultB": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "detail",
                        text: "详情",
                        layout: "3",
                        click: function () {
                            var checkRows = resultBGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                if (checkRows.length > 1) {
                                    NotificationUtil("请勿选择多条记录", "warning");
                                } else {
                                    popDataWindow.setOptions({"title": "资产调拨详情"});
                                    fixedAssetsWindow("confirm", "", checkRows[0].transferNo);
                                }
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    },
                    {
                        name: "batchRevocation",
                        text: "批量撤销",
                        layout: "3",
                        click: function () {
                            var checkRows = resultBGrid.getCheckedRows();
                            var transferNoList = [];
                            if (checkRows.length > 0) {
                                IPLAT.confirm({
                                    message: '<b style="color: red">您确定要撤销吗？该操作不可恢复！</b>',
                                    okFn: function (e) {
                                        var eiInfo = new EiInfo()
                                        for (let i = 0; i < checkRows.length; i++) {
                                            transferNoList.push(checkRows[i].transferNo)
                                        }
                                        eiInfo.set("transferNoList", transferNoList)
                                        // 固资删除
                                        EiCommunicator.send("FADB01", "batchRevocation", eiInfo, {
                                            onSuccess: function (ei) {
                                                if (ei.status == -1) {
                                                    NotificationUtil(ei.msg, "warning");
                                                } else {
                                                    IPLAT.alert(
                                                        ei.msg,
                                                        function (e) {
                                                        },
                                                        "提示",
                                                        300
                                                    );
                                                    resultBGrid.dataSource.page(1);
                                                }
                                            }
                                        })
                                    },
                                    cancelFn: function (e) {
                                    }
                                })
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    }
                ]
            },
            loadComplete: function (grid) {

            },
        },
        "resultC": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "detail",
                        text: "接收科室确认",
                        layout: "3",
                        click: function () {
                            var checkRows = resultCGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                if (checkRows.length > 1) {
                                    NotificationUtil("请勿选择多条记录", "warning");
                                    return
                                } else {
                                    // if(__ei.deptName == checkRows[0].applyDeptName){
                                    //     NotificationUtil("请勿选择自己科室申请的调拨单", "warning");
                                    //     return
                                    // } else {
                                    //     popDataWindow.setOptions({"title": "接收科室确认"});
                                    //     fixedAssetsWindow("confirm", "", checkRows[0].transferNo, "fileCode");
                                    // setConfig("testzw", "赵伟", "FA");
                                    setConfig(__ei.workNo, __ei.name, "FA");
                                    getSign(fileCode => {
                                        if (fileCode) {
                                            popDataWindow.setOptions({"title": "接收科室确认"});
                                            fixedAssetsWindow("confirm", "", checkRows[0].transferNo, fileCode);
                                        } else {
                                            NotificationUtil("电子签名校验失败", "warning");
                                        }
                                    });
                                    // }
                                }
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                                return;
                            }
                        }
                    }
                ]
            },
            onCellClick: function (e) {
                if (e.field === "transferNo") {
                    // popDataWindow.setOptions({"title": "接收科室确认"});
                    // fixedAssetsWindow("confirm", "", e.model.transferNo, "fileCode");
                    // setConfig("testzw", "赵伟", "FA");
                    setConfig(__ei.workNo, __ei.name, "FA");
                    getSign(fileCode => {
                        if (fileCode) {
                            popDataWindow.setOptions({"title": "接收科室确认"});
                            fixedAssetsWindow("confirm", "", e.model.transferNo, fileCode);
                        } else {
                            NotificationUtil("电子签名校验失败", "warning");
                        }
                    });
                }
            },
            onRowClick: function (e) {
                var eiInfo = new EiInfo();
                eiInfo.set("transferNo", e.model.transferNo)
                eiInfo.set("block", "resultDetailsC2")
                // 调用小代码
                EiCommunicator.send("FADB01", "transferDetailResult", eiInfo, {
                    onSuccess: function (ei) {
                        resultDetailsC2Grid.setEiInfo(ei);
                    }
                });
            },
            // 设置背景色
            // dataBound: function (e) {
            //     var grid = e.sender;
            //     var trs = grid.table.find("tr")
            //     let dataItems = grid.getDataItems();
            //     for (let i = 0; i < dataItems.length; i++) {
            //         if (dataItems[i].applyDeptName === __ei.deptName){
            //             trs[i].style.background = "#DFEAA6";
            //         }
            //     }
            // },
            onSuccess: function (e) {

            },
            loadComplete: function (grid) {

            }
        },
        "resultDetailsC2": {
            pageable: false,
            exportGrid: false,
        },
        "resultD": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: []
            },
            onCellClick: function (e) {
                if (e.field === "transferNo") {
                    // popDataWindow.setOptions({"title": "资产科审批"});
                    // fixedAssetsWindow("audit", "", e.model.transferNo, "fileCode");
                    // setConfig("testzw", "赵伟", "FA");
                    setConfig(__ei.workNo, __ei.name, "FA");
                    getSign(fileCode => {
                        if (fileCode) {
                            popDataWindow.setOptions({"title": "资产科审批"});
                            fixedAssetsWindow("audit", "", e.model.transferNo, fileCode);
                        } else {
                            NotificationUtil("电子签名校验失败", "warning");
                        }
                    });
                }
            },
            onRowClick: function (e) {
                var eiInfo = new EiInfo();
                eiInfo.set("transferNo", e.model.transferNo)
                eiInfo.set("block", "resultDetailsD2")
                // 点击查看明细
                EiCommunicator.send("FADB01", "transferDetailResult", eiInfo, {
                    onSuccess: function (ei) {
                        resultDetailsD2Grid.setEiInfo(ei);
                    }
                });
            },
            loadComplete: function (grid) {
                // 批量审批
                $("#BATCHAPPROVAL").on("click", function (e) {
                    var checkRows = resultDGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        // acceptWindow.open().center();
                        // // 通过
                        // $("#pass").on("click", function (e) {
                        //     var checkRows = resultDGrid.getCheckedRows();
                        //     let transferNoList = [];
                        //     let applyDeptList = [];
                        //     let confirmDeptList = [];
                        //     for (let i = 0; i < checkRows.length; i++) {
                        //         transferNoList.push(checkRows[i].transferNo);
                        //         applyDeptList.push(
                        //             {
                        //                 applyDeptNum: checkRows[i].applyDeptNum,
                        //                 applyDeptName: checkRows[i].applyDeptName
                        //             }
                        //         );
                        //         confirmDeptList.push(
                        //             {
                        //                 confirmDeptNum: checkRows[i].confirmDeptNum,
                        //                 confirmDeptName: checkRows[i].confirmDeptName
                        //             }
                        //         );
                        //     }
                        //     var eiInfo = new EiInfo();
                        //     var auditReason = IPLAT.EFInput.value($("#info-0-auditReason"));
                        //     if (auditReason == "" || auditReason == null) {
                        //         NotificationUtil("请填写审批意见", "warning");
                        //         return
                        //     }
                        //     eiInfo.set("transferNoList", transferNoList);
                        //     eiInfo.set("applyDeptList", applyDeptList);
                        //     eiInfo.set("confirmDeptList", confirmDeptList);
                        //     eiInfo.set("auditReason", auditReason);
                        //     eiInfo.set("auditFileCode", auditFileCode);
                        //     eiInfo.set("type", "pass");
                        //     EiCommunicator.send("FADB01", "batchApproval", eiInfo, {
                        //         onSuccess: function (ei) {
                        //             if (ei.status == -1) {
                        //                 NotificationUtil(ei.msg, "warning");
                        //             } else {
                        //                 // IPLAT.alert(
                        //                 //     ei.msg,
                        //                 //     function (e) {
                        //                 //     },
                        //                 //     "提示",
                        //                 //     300
                        //                 // );
                        //                 acceptWindow.close();
                        //                 IPLAT.EFInput.value($("#info-0-auditReason"), "");
                        //                 resultDGrid.dataSource.page(1);
                        //             }
                        //         }
                        //     })
                        // });
                        // // 驳回
                        // $("#reject").on("click", function (e) {
                        //     var checkRows = resultDGrid.getCheckedRows();
                        //     var transferNoList = new Array();
                        //     var applyDeptList = new Array();
                        //     var confirmDeptList = new Array();
                        //     for (let i = 0; i < checkRows.length; i++) {
                        //         transferNoList.push(checkRows[i].transferNo);
                        //         applyDeptList.push(
                        //             {
                        //                 applyDeptNum: checkRows[i].applyDeptNum,
                        //                 applyDeptName: checkRows[i].applyDeptName
                        //             }
                        //         );
                        //         confirmDeptList.push(
                        //             {
                        //                 confirmDeptNum: checkRows[i].confirmDeptNum,
                        //                 confirmDeptName: checkRows[i].confirmDeptName
                        //             }
                        //         );
                        //     }
                        //     transferNoList = transferNoList;
                        //     applyDeptList = applyDeptList;
                        //     confirmDeptList = confirmDeptList;
                        //     var eiInfo = new EiInfo();
                        //     var auditReason = IPLAT.EFInput.value($("#info-0-auditReason"));
                        //     if (auditReason == "" || auditReason == null) {
                        //         NotificationUtil("请填写审批意见", "warning");
                        //         return
                        //     }
                        //     eiInfo.set("transferNoList", transferNoList);
                        //     eiInfo.set("applyDeptList", applyDeptList);
                        //     eiInfo.set("confirmDeptList", confirmDeptList);
                        //     eiInfo.set("auditReason", auditReason);
                        //     eiInfo.set("auditFileCode", auditFileCode);
                        //     eiInfo.set("type", "reject");
                        //     EiCommunicator.send("FADB01", "batchApproval", eiInfo, {
                        //         onSuccess: function (ei) {
                        //             if (ei.status == -1) {
                        //                 NotificationUtil(ei.msg, "warning");
                        //             } else {
                        //                 // IPLAT.alert(
                        //                 //     ei.msg,
                        //                 //     function (e) {
                        //                 //     },
                        //                 //     "提示",
                        //                 //     300
                        //                 // );
                        //                 acceptWindow.close();
                        //                 IPLAT.EFInput.value($("#info-0-auditReason"), "");
                        //                 resultDGrid.dataSource.page(1);
                        //             }
                        //         }
                        //     })
                        // });
                        // // 取消
                        // $("#cancel").on("click", function (e) {
                        //     acceptWindow.close();
                        // });
                        // setConfig("testzw", "赵伟", "FA");
                        setConfig(__ei.workNo, __ei.name, "FA");
                        var auditFileCode;
                        getSign(fileCode => {
                            if (fileCode) {
                                auditFileCode = fileCode;
                                IPLAT.EFInput.value($("#info-0-auditFileCode"), auditFileCode);
                                // getSignatureImg(auditFileCode, "audit")
                                // 批量获取调拨单号
                                acceptWindow.open().center();
                                // 通过
                                $("#pass").on("click", function (e) {
                                    var checkRows = resultDGrid.getCheckedRows();
                                    let transferNoList = [];
                                    let applyDeptList = [];
                                    let confirmDeptList = [];
                                    for (let i = 0; i < checkRows.length; i++) {
                                        transferNoList.push(checkRows[i].transferNo);
                                        applyDeptList.push(
                                            {
                                                applyDeptNum: checkRows[i].applyDeptNum,
                                                applyDeptName: checkRows[i].applyDeptName
                                            }
                                        );
                                        confirmDeptList.push(
                                            {
                                                confirmDeptNum: checkRows[i].confirmDeptNum,
                                                confirmDeptName: checkRows[i].confirmDeptName
                                            }
                                        );
                                    }
                                    var eiInfo = new EiInfo();
                                    var auditReason = IPLAT.EFInput.value($("#info-0-auditReason"));
                                    if (auditReason == "" || auditReason == null) {
                                        NotificationUtil("请填写审批意见", "warning");
                                        return
                                    }
                                    eiInfo.set("transferNoList", transferNoList);
                                    eiInfo.set("applyDeptList", applyDeptList);
                                    eiInfo.set("confirmDeptList", confirmDeptList);
                                    eiInfo.set("auditReason", auditReason);
                                    eiInfo.set("auditFileCode", auditFileCode);
                                    eiInfo.set("type", "pass");
                                    EiCommunicator.send("FADB01", "batchApproval", eiInfo, {
                                        onSuccess: function (ei) {
                                            if (ei.status == -1) {
                                                NotificationUtil(ei.msg, "warning");
                                            } else {
                                                // IPLAT.alert(
                                                //     ei.msg,
                                                //     function (e) {
                                                //     },
                                                //     "提示",
                                                //     300
                                                // );
                                                acceptWindow.close();
                                                IPLAT.EFInput.value($("#info-0-auditReason"), "");
                                                resultDGrid.dataSource.page(1);
                                            }
                                        }
                                    })
                                });
                                // 驳回
                                $("#reject").on("click", function (e) {
                                    var checkRows = resultDGrid.getCheckedRows();
                                    var transferNoList = new Array();
                                    var applyDeptList = new Array();
                                    var confirmDeptList = new Array();
                                    for (let i = 0; i < checkRows.length; i++) {
                                        transferNoList.push(checkRows[i].transferNo);
                                        applyDeptList.push(
                                            {
                                                applyDeptNum: checkRows[i].applyDeptNum,
                                                applyDeptName: checkRows[i].applyDeptName
                                            }
                                        );
                                        confirmDeptList.push(
                                            {
                                                confirmDeptNum: checkRows[i].confirmDeptNum,
                                                confirmDeptName: checkRows[i].confirmDeptName
                                            }
                                        );
                                    }
                                    transferNoList = transferNoList;
                                    applyDeptList = applyDeptList;
                                    confirmDeptList = confirmDeptList;
                                    var eiInfo = new EiInfo();
                                    var auditReason = IPLAT.EFInput.value($("#info-0-auditReason"));
                                    if (auditReason == "" || auditReason == null) {
                                        NotificationUtil("请填写审批意见", "warning");
                                        return
                                    }
                                    eiInfo.set("transferNoList", transferNoList);
                                    eiInfo.set("applyDeptList", applyDeptList);
                                    eiInfo.set("confirmDeptList", confirmDeptList);
                                    eiInfo.set("auditReason", auditReason);
                                    eiInfo.set("auditFileCode", auditFileCode);
                                    eiInfo.set("type", "reject");
                                    EiCommunicator.send("FADB01", "batchApproval", eiInfo, {
                                        onSuccess: function (ei) {
                                            if (ei.status == -1) {
                                                NotificationUtil(ei.msg, "warning");
                                            } else {
                                                // IPLAT.alert(
                                                //     ei.msg,
                                                //     function (e) {
                                                //     },
                                                //     "提示",
                                                //     300
                                                // );
                                                acceptWindow.close();
                                                IPLAT.EFInput.value($("#info-0-auditReason"), "");
                                                resultDGrid.dataSource.page(1);
                                            }
                                        }
                                    })
                                });
                                // 取消
                                $("#cancel").on("click", function (e) {
                                    acceptWindow.close();
                                });
                            } else {
                                NotificationUtil("电子签名校验失败", "warning");
                            }
                        });
                    } else {
                        NotificationUtil("请选择一条记录", "warning");
                    }
                });
            }
        },
        "resultDetailsD2": {
            pageable: false,
            exportGrid: false,
        },
        "resultE": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "printCardTransfer",
                        text: "资产卡片-调拨",
                        layout: "3",
                        click: function () {
                            var checkRows = resultEGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                var eiInfo = new EiInfo();
                                // 调用小代码
                                EiCommunicator.send("FAAP01", "automaticallyURL", eiInfo, {
                                    onSuccess: function (ei) {
                                        // 当前页面地址
                                        var pageUrl = window.location.href;
                                        // 获取报表地址前袋
                                        var baseUrl = pageUrl.split('/')[0]+"//"+pageUrl.split('/')[2]+"/";
                                        var BaseUrl = "fr/ReportServer?reportlet=v5stable/";
                                        if (ei.extAttr.url != undefined) {
                                            BaseUrl = ei.extAttr.url;
                                        }
                                        console.log(baseUrl)
                                        // 适用于直接点击超链接，浏览器会自动补全格式
                                        var url = baseUrl + BaseUrl + "梅州市人民医院固定资产卡片-调拨卡片.cpt&transferNo=" + checkRows[0].transferNo;
                                        console.log(url)
                                        if (checkRows[0].transferNo) {
                                            var popData = $("#popData");
                                            popData.data("kendoWindow").setOptions({
                                                open: function () {
                                                    popData.data("kendoWindow").refresh({
                                                        url: url
                                                    });
                                                }
                                            });
                                            popDataWindow.setOptions({"title": ""});
                                            popDataWindow.open().center();
                                            // window.open(url)
                                        }
                                    },
                                });
                            } else {
                                NotificationUtil("请先选择一条记录", "warning");
                            }
                        }
                    },
                    {
                        name: "detail",
                        text: "详情",
                        layout: "3",
                        click: function () {
                            var checkRows = resultEGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                if (checkRows.length > 1) {
                                    NotificationUtil("请勿选择多条记录", "warning");
                                } else {
                                    popDataWindow.setOptions({"title": "资产调拨详情"});
                                    fixedAssetsWindow("all", "", checkRows[0].transferNo);
                                }
                            } else {
                                NotificationUtil("请选择一条记录", "warning");
                            }
                        }
                    },
                    // {
                    //     name: "printInTransfer",
                    //     text: "调拨单打印",
                    //     layout: "3",
                    //     click: function () {
                    //         var checkRows = resultEGrid.getCheckedRows();
                    //         if (checkRows.length > 0) {
                    //             var eiInfo = new EiInfo();
                    //             // 调用小代码
                    //             EiCommunicator.send("FAAP01", "automaticallyURL", eiInfo, {
                    //                 onSuccess: function (ei) {
                    //                     var BaseUrl = "http://121.36.30.129:7225/fr/ReportServer?reportlet=v5stable/";
                    //                     if (ei.extAttr.url != undefined) {
                    //                         BaseUrl = ei.extAttr.url;
                    //                     }
                    //                     // 适用于直接点击超链接，浏览器会自动补全格式
                    //                     var url = BaseUrl + "14.梅州市人民医院固定资产调拨表.cpt&transferNo=" + checkRows[0].transferNo;
                    //                     console.log(url)
                    //                     var popData = $("#popData");
                    //                     popData.data("kendoWindow").setOptions({
                    //                         open: function () {
                    //                             popData.data("kendoWindow").refresh({
                    //                                 url: url
                    //                             });
                    //                         }
                    //                     });
                    //                     popDataWindow.setOptions({"title": ""});
                    //                     popDataWindow.open().center();
                    //                 },
                    //             });
                    //         } elsePRINTINTRANSFER {
                    //             NotificationUtil("请先选择一条记录", "warning");
                    //         }
                    //     }
                    // }
                ]
            },
            onCellClick: function (e) {
                if (e.field === "transferNo") {
                    popDataWindow.setOptions({"title": "资产调拨详情"});
                    fixedAssetsWindow("all", "", e.model.transferNo);
                }
            },
            onRowClick: function (e) {
                var eiInfo = new EiInfo();
                eiInfo.set("transferNo", e.model.transferNo)
                eiInfo.set("block", "resultDetailsE2")
                // 点击查看明细
                EiCommunicator.send("FADB01", "transferDetailResult", eiInfo, {
                    onSuccess: function (ei) {
                        resultDetailsE2Grid.setEiInfo(ei);
                    }
                });
            },
            loadComplete: function (grid) {
                // 打印调拨单PRINTINTRANSFER
                $("#PRINTINTRANSFER").on("click", function (e) {
                    var checkRows = resultEGrid.getCheckedRows();
                    if (checkRows.length > 0) {
                        var eiInfo = new EiInfo();
                        // 调用小代码
                        EiCommunicator.send("FAAP01", "automaticallyURL", eiInfo, {
                            onSuccess: function (ei) {
                                // 当前页面地址
                                var pageUrl = window.location.href;
                                // 获取报表地址前袋
                                var baseUrl = pageUrl.split('/')[0] + "//" + pageUrl.split('/')[2] + "/";
                                var BaseUrl = "fr/ReportServer?reportlet=v5stable/";
                                if (ei.extAttr.url != undefined) {
                                    BaseUrl = ei.extAttr.url;
                                }
                                console.log(baseUrl)
                                // 适用于直接点击超链接，浏览器会自动补全格式
                                var url = baseUrl + BaseUrl + "14.梅州市人民医院固定资产调拨表.cpt&transferNo=" + checkRows[0].transferNo;
                                console.log(url)
                                var popData = $("#popData");
                                popData.data("kendoWindow").setOptions({
                                    open: function () {
                                        popData.data("kendoWindow").refresh({
                                            url: url
                                        });
                                    }
                                });
                                popDataWindow.setOptions({"title": ""});
                                popDataWindow.open().center();
                            },
                        });
                    } else {
                        NotificationUtil("请先选择一条记录", "warning");
                    }
                });
            }
        },
        "resultDetailsE2": {
            pageable: false,
            exportGrid: false,
        }
    }
});

// 自定义资产弹窗
function fixedAssetsWindow(type, id, transferNo, fileCode) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FADB0101?initLoad&faTransferId=" + id + "&type=" + type + "&transferNo=" + transferNo + "&fileCode=" + fileCode;
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

// 自定义资产弹窗
function fixedAssetsDetailWindow(type, id) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FADB0102?initLoad&faTransferId=" + id + "&type=" + type;
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


// 资产详情展示
function fixedAssetsWindowDetail(faInfoId) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FADA0102?initLoad&faInfoId=" + faInfoId;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open: function () {
            popData.data("kendoWindow").refresh({
                url: url
            });
        }
    });
    popDataWindow.open().center();
}

function fixedAssetsTransferWindow(type, fileCode) {
    fixedAssetsWindow(type, "", "", fileCode);
}

function relaodStorage(){
    var reloadSize = 0,
        reloadMoney = 0;
    if (localStorage.getItem("myArray") != null){
        reloadSize = [...new Set(JSON.parse(localStorage.getItem("myArray")))].length.toFixed(2);
    }
    if (localStorage.getItem("myArrayMoney") != null){
        reloadMoney = Number(localStorage.getItem("myArrayMoney")).toFixed(2);
    }
    $("#numberCount").text(reloadSize);
    $("#numberMoney").text(Number(reloadMoney).toFixed(2));
}