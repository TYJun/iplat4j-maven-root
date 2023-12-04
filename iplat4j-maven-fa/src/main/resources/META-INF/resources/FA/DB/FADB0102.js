$(function () {
    IPLATUI.EFSelect = {
        "info-0-turnType": {
            // 点击下拉选项时触发
            select:function(e) { //获取勾选值
                let dataItem = e.dataItem;
                if (dataItem['valueField'] == "dept"){
                    $("#backWarehouse").show();
                } else {
                    $("#backWarehouse").hide();
                }
            }
        },
    };

    IPLATUI.EFPopupInput = {
        "info-0-confirmLocationNum": {
            query: function (e) {
                var queryInfo = new EiInfo();
                queryInfo.set("confirmDeptNum", $("#info-0-turnDeptNum").val());
                return queryInfo;
            },
            backFill: function (e) {
                IPLAT.EFInput.value($("#info-0-confirmBuild"), e.model['building']);
                IPLAT.EFInput.value($("#info-0-confirmFloor"), e.model['floor']);
            },
        },
    }

    IPLATUI.EFAutoComplete = {
        "info-0-deptName": {
            change: function (e) {
                var context = $("#info-0-deptName").data("kendoAutoComplete");
                if (context.listView._dataItems.length > 0){
                    var confirmDeptNum = context.listView._dataItems[0].deptNum;
                    var confirmDeptName = context.listView._dataItems[0].deptName;
                    if (confirmDeptNum == ""){
                        IPLAT.EFInput.value($("#info-0-turnDeptNum"), "");
                        IPLAT.EFInput.value($("#info-0-turnDeptName"), "");
                        IPLAT.EFInput.value($("#info-0-confirmLocationNum"), "");
                        IPLAT.EFInput.value($("#info-0-confirmLocationNum_textField"), "");
                        IPLAT.EFInput.value($("#info-0-confirmBuild"), "");
                        IPLAT.EFInput.value($("#info-0-confirmFloor"), "");
                        NotificationUtil("该科室不存在编码请重新选择", "warning")
                    } else {
                        IPLAT.EFInput.value($("#info-0-deptNum"), confirmDeptNum);
                        IPLAT.EFInput.value($("#info-0-turnDeptNum"), confirmDeptNum);
                        IPLAT.EFInput.value($("#info-0-turnDeptName"), confirmDeptName);
                    }
                } else {
                    IPLAT.EFInput.value($("#info-0-turnDeptNum"), "");
                    IPLAT.EFInput.value($("#info-0-confirmLocationNum"), "");
                    IPLAT.EFInput.value($("#info-0-confirmLocationNum_textField"), "");
                    IPLAT.EFInput.value($("#info-0-confirmBuild"), "");
                    IPLAT.EFInput.value($("#info-0-confirmFloor"), "");
                    NotificationUtil("该科室不存在请重新选择", "warning")
                }
                // console.log("编码：" + confirmDeptNum + "--" + "名称：" + confirmDeptName)
            }
        }
    }

    IPLATUI.EFGrid = {
        "result": {
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: []
            },
            loadComplete: function (grid) {

            }
        },
        "resultFixedAssests": {
            pageable: false,
            exportGrid: false,
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: []
            },
            onCheckRow: function (e) {
                // var grid = e.sender,
                //     model = e.model,
                //     $tr = e.tr,
                //     row = e.row;
                // $tr.css({
                //     background: "white",
                //     color: "#000000A6"
                // });
                // grid.unCheckAllRows();
            },
            loadComplete: function (grid) {
                let element = document.getElementsByClassName("col-xs-4")[1];
                element.setAttribute("id", "info-0-deptNum");
                var myArray = localStorage.getItem("myArray");
                // console.log(JSON.parse(myArray))
                var eiInfo = new EiInfo();
                eiInfo.set("myArray",myArray)
                loadingShow("加载中，请稍等...");
                if (JSON.parse(myArray).length < 100){
                    loadingRemove(500);
                } else if (JSON.parse(myArray).length < 1000) {
                    loadingRemove(1500);
                } else {
                    loadingRemove(3000);
                }
                EiCommunicator.send("FADB0102", "queryFaInfo", eiInfo, {
                    onSuccess : function(ei) {
                        // console.log(ei)
                        grid.setEiInfo(ei)
                        // var checkRows = window.parent.resultAGrid.getCheckedRows()
                        // console.log(checkRows)
                        // grid.addRows(checkRows);
                    }
                })

                // 获取前一个页面的值
                // var checkRows = window.parent.resultAGrid.getCheckedRows()
                // if (checkRows.length > 0) {
                //     grid.addRows(checkRows);
                //     grid.unCheckAllRows();
                // }

                $("#save").on("click", function (e) {
                    // 防止连续提交
                    $("#save").attr("disabled",true);
                    // setTimeout(function(){$("#save").attr("disabled",false);},3000);
                    // 出现分支调拨回科室和调拨回仓库
                    // 调拨回科室
                    if ($("#info-0-turnType").val() == "dept"){
                        if ($("#info-0-turnDeptNum").val() == "") {
                            NotificationUtil("请选择接收科室", "warning");
                            $("#save").attr("disabled",false);
                            return;
                        }
                        var checkRows = resultFixedAssestsGrid.getDataItems();
                        if (checkRows.length > 0) {
                            var newFaInfo = [],useFaInfo = [];
                            for (let i = 0; i < checkRows.length; i++) {
                                if (checkRows[i].statusCode == "待用") {
                                    newFaInfo.push(checkRows[i])
                                } else if (checkRows[i].statusCode == "在用") {
                                    useFaInfo.push(checkRows[i])
                                }
                            }
                            var eiInfo = new EiInfo();
                            eiInfo.setByNode("info");
                            eiInfo.set("newFaInfo", newFaInfo)
                            eiInfo.set("useFaInfo", useFaInfo)
                            loadingShow("提交中，请稍等...")
                            EiCommunicator.send("FADB0102", "assetOutStorage", eiInfo, {
                                onSuccess: function (ei) {
                                    if (ei.status == -1) {
                                        IPLAT.alert(
                                            ei.msg,
                                            function (e) {
                                            },
                                            "提示",
                                            300
                                        );
                                    } else {
                                        localStorage.removeItem("myArray")
                                        localStorage.removeItem("myArrayMoney")
                                        closeParentWindow()
                                        var outBillNo = ei.extAttr.outBillNo;
                                        var transferNo = ei.extAttr.transferNo;
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
                                                if (outBillNo != undefined) {
                                                    // 适用于直接点击超链接，浏览器会自动补全格式
                                                    var url = baseUrl + BaseUrl + "梅州市人民医院固定资产卡片-财务.cpt&outBillNo=" + outBillNo;
                                                } else if (transferNo != undefined){
                                                    // 适用于直接点击超链接，浏览器会自动补全格式
                                                    var url = baseUrl + BaseUrl + "梅州市人民医院固定资产卡片-调拨卡片.cpt&transferNo=" + transferNo;
                                                }
                                                if (outBillNo != undefined || transferNo != undefined){
                                                    var popData = $("#popData2");
                                                    popData.data("kendoWindow").setOptions({
                                                        open: function () {
                                                            popData.data("kendoWindow").refresh({
                                                                url: url
                                                            });
                                                        }
                                                    });
                                                    popData2Window.setOptions({"title": ""});
                                                    popData2Window.open().center();
                                                    // window.open(url)
                                                }
                                                parent.relaodStorage();
                                            },
                                        });
                                    }
                                    $("#save").attr("disabled",false);
                                }
                            });
                        } else {
                            NotificationUtil("请先选择一条记录", "warning");
                            $("#save").attr("disabled",false);
                            return;
                        }
                    } // 调拨回仓库
                    else if ($("#info-0-turnType").val() == "warehouse"){
                        var checkRows = resultFixedAssestsGrid.getDataItems();
                        if (checkRows.length > 0) {
                            var backZFaInfo = [];
                            var backSFaInfo = [];
                            // 出库单号为空不能进行资产退库
                            for (let i = 0; i < checkRows.length; i++) {
                                if (checkRows[i].outBillNo == "" || checkRows[i].outBillNo == null) {
                                    NotificationUtil("请选择存在出库单的资产卡片进行退库", "warning");
                                    return;
                                } else if(checkRows[i].statusCode == "待用"){
                                    NotificationUtil("'待用'状态的资产卡片，不能进行退库", "warning");
                                    return;
                                } else if (checkRows[i].operationType == "直入直出"){
                                    NotificationUtil("'直入直出'状态的资产不能进行调拨回仓库，请在仓库模块进行退货操作", "warning");
                                    return;
                                    backZFaInfo.push(checkRows[i])
                                } else if (checkRows[i].operationType == "手工入库" || checkRows[i].operationType == "采购入库"){
                                    backSFaInfo.push(checkRows[i])
                                }
                            }
                            var eiInfo = new EiInfo();
                            eiInfo.setByNode("info");
                            eiInfo.set("backZFaInfo", backZFaInfo)
                            eiInfo.set("backSFaInfo", backSFaInfo)
                            loadingShow("提交中，请稍等...")
                            EiCommunicator.send("FADB0102", "assetOutWarehouse", eiInfo, {
                                onSuccess: function (ei) {
                                    if (ei.status == -1) {
                                        IPLAT.alert(
                                            ei.msg,
                                            function (e) {
                                            },
                                            "提示",
                                            300
                                        );
                                    } else {
                                        localStorage.removeItem("myArray")
                                        closeParentWindow()
                                    }
                                    $("#save").attr("disabled",false);
                                }
                            });
                        } else {
                            NotificationUtil("请先选择一条记录", "warning");
                            $("#save").attr("disabled",false);
                        }
                    }
                });

                $("#close").on("click", function (e) {
                    closeParentWindow()
                });

                $("#remove").on("click",function (e) {
                    var rows = resultFixedAssestsGrid.getCheckedRows();
                    resultFixedAssestsGrid.removeRows(rows)
                    var newArray = JSON.parse(localStorage.getItem("myArray"))
                    var newArrayMoney = Number(localStorage.getItem("myArrayMoney"))
                    for (let i = 0; i < newArray.length; i++) {
                        for (let j = 0; j < rows.length; j++) {
                            if (newArray[i] == rows[j].goodsNum){
                                newArray.splice(i,1)
                            }
                        }
                    }
                    for (let i = 0; i < rows.length; i++) {
                        newArrayMoney = Number(newArrayMoney) - Number(rows[i].buyCost)
                    }
                    localStorage.setItem("myArray",JSON.stringify([...new Set(newArray)]))
                    localStorage.setItem("myArrayMoney",Number(newArrayMoney))
                    parent.relaodStorage();
                });
            }
        }
    }
});

function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultAGrid"].dataSource.page(1);
    // window.parent["resultBGrid"].dataSource.page(1);
    window.parent["resultCGrid"].dataSource.page(1);
    window.parent["resultDGrid"].dataSource.page(1);
    window.parent["resultEGrid"].dataSource.page(1);
}

//显示转圈中等待
function loadingShow(msg) {
    qrCodeWindow.setOptions({"title": msg});
    qrCodeWindow.open().center();
    var parent = $("#qrCode");
    var loading = '<i id="loading"  class="fa fa-spinner fa-spin fa-3x fa-fw" style="margin: auto;text-align: center"></i>';
    var div = $(loading);
    div.appendTo(parent);
}

//移除转圈中等待
function loadingRemove(time) {
    //设置多少毫秒，进行弹框关闭
    setTimeout(function () {
        qrCodeWindow.close();
        $('#loading').remove();
    }, time);
}