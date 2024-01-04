$(function () {
    var type = $("#type").val()
    switch (type) {
        // 新建工单
        case "enter":
            $("#apply").show()
            $("#confirmDeptNum").show()
            $("#enterButton").show()
            break
        // 申请
        case "apply":
            $("#apply").show()
            IPLAT.EFInput.enable($("#info-0-applyReason"), false)
            IPLAT.EFPopupInput.enable($("#info-0-confirmDeptNum"), false)
            IPLAT.EFPopupInput.setAllFields($("#info-0-confirmDeptNum"), __ei.confirmDeptNum, __ei.confirmDeptName)
            $("#applyButton").show()
            break
        // 确认
        case "confirm":
            $("#apply").show()
            IPLAT.EFInput.enable($("#info-0-applyReason"), false)
            IPLAT.EFPopupInput.enable($("#info-0-confirmDeptNum"), false)
            IPLAT.EFPopupInput.setAllFields($("#info-0-confirmDeptNum"), __ei.confirmDeptNum, __ei.confirmDeptName)
            $("#confirm").show()
            $("#confirmButton").show()
            break
        // 审批
        case "audit":
            $("#apply").show()
            IPLAT.EFInput.enable($("#info-0-confirmRoom"), false)
            IPLAT.EFInput.enable($("#info-0-applyReason"), false)
            IPLAT.EFPopupInput.enable($("#info-0-confirmDeptNum"), false)
            IPLAT.EFPopupInput.setAllFields($("#info-0-confirmDeptNum"), __ei.confirmDeptNum, __ei.confirmDeptName)
            IPLAT.EFPopupInput.setAllFields($("#info-0-confirmLocationNum"), __ei.confirmLocationNum, __ei.confirmLocationName)
            $("#confirm").show()
            IPLAT.EFPopupInput.enable($("#info-0-confirmLocationNum"), false)
            IPLAT.EFInput.enable($("#info-0-confirmReason"), false)
            $("#audit").show()
            if (__ei.role == "admin") {
                $("#auditButton").show()
            } else if (__ei.role == "user") {
                $("#auditButton2").show()
            }
            break
        // 查看所有
        case "all":
            $("#apply").show()
            IPLAT.EFInput.enable($("#info-0-confirmRoom"), false)
            IPLAT.EFInput.enable($("#info-0-applyReason"), false)
            IPLAT.EFPopupInput.enable($("#info-0-confirmDeptNum"), false)
            IPLAT.EFPopupInput.setAllFields($("#info-0-confirmDeptNum"), __ei.applyDeptNum, __ei.applyDeptName)
            $("#confirm").show()
            IPLAT.EFPopupInput.enable($("#info-0-confirmLocationNum"), false)
            IPLAT.EFPopupInput.setAllFields($("#info-0-confirmLocationNum"), __ei.confirmLocationNum, __ei.confirmLocationName)
            IPLAT.EFInput.enable($("#info-0-confirmReason"), false)
            $("#audit").show()
            IPLAT.EFInput.enable($("#info-0-auditReason"), false)
            $("#allButton").show()
            break
    }
});

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
    "info-0-deptName": {
        change: function (e) {
            var context = $("#info-0-deptName").data("kendoAutoComplete");
            if (context.listView._dataItems.length > 0) {
                var confirmDeptNum = context.listView._dataItems[0].deptNum;
                var confirmDeptName = context.listView._dataItems[0].deptName;
                if (confirmDeptNum == "") {
                    NotificationUtil("该科室不存在编码请重新选择", "warning")
                    IPLAT.EFInput.value($("#info-0-turnDeptNum"), "");
                    IPLAT.EFInput.value($("#info-0-turnDeptName"), "");
                } else {
                    IPLAT.EFInput.value($("#info-0-turnDeptNum"), confirmDeptNum);
                    IPLAT.EFInput.value($("#info-0-turnDeptName"), confirmDeptName);
                }
            } else {
                NotificationUtil("该科室不存在请重新选择", "warning")
                IPLAT.EFInput.value($("#info-0-turnDeptNum"), "");
            }
            // console.log("编码：" + confirmDeptNum + "--" + "名称：" + confirmDeptName)
        }
    }
}

IPLATUI.EFGrid = {
    "spot": {
        pageable : false,
        exportGrid: false,
        loadComplete: function (e) {

        },
        onRowDblClick: function (e) {
            if (e.event.type === "dblclick") {
                if (e.model){
                    $("#info-0-confirmLocationNum").val(e.model['spotNum']);
                    $("#info-0-confirmLocationNum_textField").val(e.model['spotName']);
                    IPLAT.EFInput.value($("#info-0-confirmBuild"), e.model['building']);
                    IPLAT.EFInput.value($("#info-0-confirmFloor"), e.model['floor']);
                    var popupGridWindow = $("#confirmLocationNum").data("kendoWindow");
                    popupGridWindow.close()
                }
            }
        },
        columns: [
            {
                field: "building",
                title: "楼",
                filterable: {
                    operators: {
                        string: {
                            contains: "包含"
                        }
                    }
                }
            }, {
                field: "floor",
                title: "层",
                filterable: {
                    operators: {
                        string: {
                            contains: "包含"
                        }
                    }
                }
            }, {
                field: "spotName",
                title: "地点",
                filterable: {
                    operators: {
                        string: {
                            contains: "包含"
                        }
                    }
                }
            }
        ]
    },
    "result": {
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: []
        },
        loadComplete: function (e) {

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
            // if (IPLAT.EFInput.value($("#type")) == "confirm") {
            //     return;
            // } else {
            //     var grid = e.sender,
            //         model = e.model,
            //         $tr = e.tr,
            //         row = e.row;
            //     $tr.css({
            //         background: "white",
            //         color: "#000000A6"
            //     });
            //     grid.unCheckAllRows();
            // }
        },
        loadComplete: function (grid) {
            let element = document.getElementsByClassName("col-xs-4")[0];
            element.setAttribute("id", "info-0-deptNum");
            var type = $("#type").val();
            // if (type == "enter" || type == "apply") {
            //     var applyFileCode = $("#info-0-applyFileCode").val();
            //     getSignatureImg(applyFileCode, "enter")
            // } else if (type == "confirm") {
            //     var applyFileCode = $("#info-0-applyFileCode").val();
            //     getSignatureImg(applyFileCode, "enter")
            //     var confirmFileCode = $("#info-0-confirmFileCode").val();
            //     getSignatureImg(confirmFileCode, "confirm")
            // } else if (type == "audit" || type == "all") {
            //     var applyFileCode = $("#info-0-applyFileCode").val();
            //     getSignatureImg(applyFileCode, "enter")
            //     var confirmFileCode = $("#info-0-confirmFileCode").val();
            //     getSignatureImg(confirmFileCode, "confirm")
            //     var auditFileCode = $("#info-0-auditFileCode").val();
            //     getSignatureImg(auditFileCode, "audit")
            // }
            var checkRows = window.parent.resultAGrid.getCheckedRows()
            switch (type) {
                case "enter":
                    if (checkRows.length > 0) {
                        grid.addRows(checkRows);
                        grid.unCheckAllRows();
                    }
                    break
                case "apply":
                    grid.removeRows(checkRows);
                    break
            }

            // 该接口只能进行保存好需要进一步审批暂时废弃
            $("#save").on("click", function (e) {
                var eiInfo = new EiInfo();
                eiInfo.setByNode("info");
                var checkedRows = resultFixedAssestsGrid.getDataItems();
                eiInfo.set("transferDetail", checkedRows)
                if (checkedRows.length < 1) {
                    NotificationUtil("请选择需要调拨的资产", "warning")
                    return
                }
                if ($("#info-0-confirmDeptNum").val() == "") {
                    NotificationUtil("请选择调入科室", "warning")
                    return
                }
                if ($("#info-0-applyReason").val() == "") {
                    NotificationUtil("请填写资产调拨原因", "warning")
                    return
                }
                // 保存资产调拨信息
                EiCommunicator.send("FADB0101", "saveFaTransferInfo", eiInfo, {
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
                            closeParentWindow()
                        }
                    }
                })
            })

            // 保存并提交
            $("#saveAndSubmit").on("click", function (e) {
                var eiInfo = new EiInfo();
                eiInfo.setByNode("info");
                var checkedRows = resultFixedAssestsGrid.getDataItems();
                eiInfo.set("transferDetail", checkedRows)
                if (checkedRows.length < 1) {
                    NotificationUtil("请选择需要调拨的资产", "warning")
                    return
                }
                if ($("#info-0-turnDeptNum").val() == "") {
                    NotificationUtil("请选择接收科室", "warning")
                    return
                }
                if ($("#info-0-applyReason").val() == "") {
                    NotificationUtil("请填写资产调拨原因", "warning")
                    return
                }
                if ($("#info-0-turnDeptName").val() == __ei.deptName) {
                    NotificationUtil("请勿自己科室调拨到自己科室", "warning")
                    return
                }
                // if ($("#info-0-applyFileCode").val() == "") {
                //     NotificationUtil("电子签名获取失败,请联系管理员", "warning")
                //     return
                // }
                // 保存资产调拨信息
                EiCommunicator.send("FADB0101", "saveFaTransferInfo", eiInfo, {
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
                            closeParentWindow()
                        }
                    }
                })
            });

            $("#submit").on("click", function (e) {
                var eiInfo = new EiInfo()
                eiInfo.set("transferNo", $("#transferNo").val())
                // 调出科室提交调拨单
                EiCommunicator.send("FADB0101", "submitFaTransferInfo", eiInfo, {
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
                            closeParentWindow()
                        }
                    }
                })
            });

            // 接收科室通过
            $("#confirmPass").on("click", function (e) {
                if ($("#info-0-confirmLocationNum").val() == "" || $("#info-0-confirmLocationNum").val() == " ") {
                    NotificationUtil("请选择存放位置", "warning")
                    return
                }
                if ($("#info-0-confirmReason").val() == "") {
                    NotificationUtil("请填写资产调拨意见", "warning")
                    return
                }
                var eiInfo = new EiInfo()
                eiInfo.setByNode("info");
                eiInfo.set("type", "pass");
                var rows = resultFixedAssestsGrid.getCheckedRows();
                // 判断是否勾选如果勾选使用勾选的数据如果没有勾选默认全部
                if (rows.length == 0) {
                    // 调入科室确认
                    EiCommunicator.send("FADB0101", "confirmFaTransferInfo", eiInfo, {
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
                                closeParentWindow()
                            }
                        }
                    })
                } else {
                    var faInfoIdList = [];
                    for (let i = 0; i < rows.length; i++) {
                        faInfoIdList[i] = rows[i].faInfoId;
                    }
                    // 调入科室部分确认
                    eiInfo.set("faInfoIdList", faInfoIdList);
                    IPLAT.confirm({
                        message: '<b>只接收选中的资产信息</br><font color="red">是否确定？</font></b>',
                        title: '提醒',
                        okFn: function (e) {
                            EiCommunicator.send("FADB0101", "confirmFaTransferInfoPart", eiInfo, {
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
                                        closeParentWindow()
                                    }
                                }
                            })
                        },
                        cancelFn: function (e) {
                            return;
                        }
                    });
                }
            });

            // 接收科室驳回
            $("#confirmReject").on("click", function (e) {
                if ($("#info-0-confirmReason").val() == "") {
                    NotificationUtil("请填写资产调拨意见", "warning")
                    return
                }
                var eiInfo = new EiInfo()
                eiInfo.setByNode("info");
                eiInfo.set("type", "reject");
                // 调入科室确认
                EiCommunicator.send("FADB0101", "confirmFaTransferInfo", eiInfo, {
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
                            closeParentWindow()
                        }
                    }
                })
            });

            // 资产科单条审批
            $("#auditPass").on("click", function (e) {
                if ($("#info-0-auditReason").val() == "") {
                    NotificationUtil("请填写资产审批意见", "warning")
                    return
                }
                var eiInfo = new EiInfo()
                eiInfo.setByNode("info");
                eiInfo.set("transferNo", IPLAT.EFInput.value($("#info-0-transferNo")));
                eiInfo.set("type", "pass");
                // 资产科审批
                EiCommunicator.send("FADB0101", "auditFaTransferInfo", eiInfo, {
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
                            closeParentWindow()
                        }
                    }
                })
            });

            // 资产科单条驳回
            $("#auditReject").on("click", function (e) {
                if ($("#info-0-auditReason").val() == "") {
                    NotificationUtil("请填写资产审批意见", "warning")
                    return
                }
                var eiInfo = new EiInfo()
                eiInfo.setByNode("info");
                eiInfo.set("transferNo", IPLAT.EFInput.value($("#info-0-transferNo")));
                eiInfo.set("type", "reject");
                // 资产科审批
                EiCommunicator.send("FADB0101", "auditFaTransferInfo", eiInfo, {
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
                            closeParentWindow()
                        }
                    }
                })
            });

            $("#close1").on("click", function (e) {
                closeParentWindow()
            });

            $("#close2").on("click", function (e) {
                closeParentWindow()
            });

            $("#close3").on("click", function (e) {
                closeParentWindow()
            });

            $("#close4").on("click", function (e) {
                closeParentWindow()
            });

            $("#close5").on("click", function (e) {
                closeParentWindow()
            });

            $("#close6").on("click", function (e) {
                closeParentWindow()
            });
        }
    }
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
        $("#info-0-newBuild").val(dataItem.building)
    },
    change: function (e) {
        if ($("#info-0-newBuild").val() != "" && $("#info-0-newBuild").val() != $("#newBuild").val()) {
            $("#newBuild").val("");
            $("#newFloor").val("");
            $("#newGoodsLocationNum").val("");
            $("#newGoodsLocation").val("")
            $("#info-0-newBuild").val("")
            $("#info-0-newFloor").val("")
            $("#info-0-newGoodsLocationNum").val("")
            $("#info-0-newGoodsLocation").val("")
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
        $("#info-0-newFloor").val(dataItem.floor)
    },
    change: function (e) {
        if ($("#info-0-newFloor").val() != "" && $("#info-0-newFloor").val() != $("#newFloor").val()) {
            $("#newFloor").val("")
            $("#newGoodsLocationNum").val("")
            $("#newGoodsLocation").val("")
            $("#info-0-newFloor").val("")
            $("#info-0-newGoodsLocationNum").val("")
            $("#info-0-newGoodsLocation").val("")
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
        $("#info-0-newGoodsLocationNum").val(dataItem.spotNum)
        $("#info-0-newGoodsLocation").val(dataItem.spotName)
    },
    change: function (e) {
        if ($("#info-0-newGoodsLocation").val() != "" && $("#info-0-newGoodsLocation").val() != $("#newGoodsLocation").val()) {
            $("#newGoodsLocationNum").val("");
            $("#newGoodsLocation").val("")
            $("#info-0-newGoodsLocationNum").val("")
            $("#info-0-newGoodsLocation").val("")
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

function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultAGrid"].dataSource.page(1);
    // window.parent["resultBGrid"].dataSource.page(1);
    window.parent["resultCGrid"].dataSource.page(1);
    window.parent["resultDGrid"].dataSource.page(1);
    window.parent["resultEGrid"].dataSource.page(1);
}


// 通过fileCode获取图片
function getSignatureImg(fileCode, type) {
    let eiInfo = new EiInfo();
    eiInfo.set("fileCode", fileCode);
    eiInfo.set("isBackSignatureImg", 1);
    EiCommunicator.send("XQMS03", "verifySignData", eiInfo, {
        onSuccess: function (ei) {
            if (type == "enter") {
                var signatureImg = ei.extAttr.data.signatureImg;
                $("#applyPic").html("");
                var img = $('<img style="width:260px;height:100px" class="signature">');
                img.attr('src', "data:image/jpeg;base64," + signatureImg);
                var pic = $("#applyPic");
                pic.append(img);
            } else if (type == "confirm") {
                var signatureImg = ei.extAttr.data.signatureImg;
                $("#confirmPic").html("");
                var img = $('<img style="width:260px;height:100px" class="signature">');
                img.attr('src', "data:image/jpeg;base64," + signatureImg);
                var pic = $("#confirmPic");
                pic.append(img);
            } else if (type == "audit") {
                var signatureImg = ei.extAttr.data.signatureImg;
                $("#auditPic").html("");
                var img = $('<img style="width:260px;height:100px" class="signature">');
                img.attr('src', "data:image/jpeg;base64," + signatureImg);
                var pic = $("#auditPic");
                pic.append(img);
            }
        }
    });
}