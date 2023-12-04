$(function() {
    var type = $("#type").val()
    if (type === "enter") {
        $("#enter").show()
        $("#applyReason").show()
        // getSignatureImg($("#info-0-applyFileCode").val(),"enter")
    } else if (type === "apply") {
        $("#identifyDeptNum").show()
        $("#apply").show()
        $("#applyReason").show()
        IPLAT.EFInput.enable($("#info-0-applyReason"), false)
    } else if (type === "identify") {
        $("#identify").show()
        $("#applyReason").show()
        $("#identifyReason").show()
        IPLAT.EFInput.enable($("#info-0-applyReason"), false)
    } else if (type === "function") {
        $("#function").show()
        $("#applyReason").show()
        $("#identifyReason").show()
        $("#functionReason").show()
        IPLAT.EFInput.enable($("#info-0-applyReason"), false)
        IPLAT.EFInput.enable($("#info-0-identifyReason"), false)
    } else if (type === "asset") {
        $("#asset").show()
        $("#applyReason").show()
        $("#identifyReason").show()
        $("#functionReason").show()
        $("#assetReason").show()
        IPLAT.EFInput.enable($("#info-0-applyReason"), false)
        IPLAT.EFInput.enable($("#info-0-identifyReason"), false)
        IPLAT.EFInput.enable($("#info-0-functionReason"), false)
    } else if (type === "all") {
        $("#applyReason").show()
        $("#identifyReason").show()
        $("#functionReason").show()
        $("#assetReason").show()
        IPLAT.EFInput.enable($("#info-0-applyReason"), false)
        IPLAT.EFInput.enable($("#info-0-identifyReason"), false)
        IPLAT.EFInput.enable($("#info-0-functionReason"), false)
        IPLAT.EFInput.enable($("#info-0-assetReason"), false)
        var identifyFileCode = $("#info-0-identifyFileCode").val();
        // getSignatureImg(identifyFileCode,"identify");
        var functionFileCode = $("#info-0-functionFileCode").val();
        // getSignatureImg(functionFileCode,"function");
        var assetFileCode = $("#info-0-assetFileCode").val();
        // getSignatureImg(assetFileCode,"asset");
    }
})

IPLATUI.EFGrid = {
    "result": {
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: []
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
            var type = $("#type").val()
            var checkRows = window.parent.resultAGrid.getCheckedRows()
            switch (type) {
                case "enter":
                    if (checkRows.length > 0) {
                        grid.addRows(checkRows);
                        grid.unCheckAllRows();
                    }
                    break
                case "apply":
                case "identify":
                case "function":
                case "asset":
                    grid.removeRows(checkRows);
                    break
            }

            $("#SAVE").on("click", function (e) {
                var eiInfo = new EiInfo();
                eiInfo.setByNode("info");
                eiInfo.set("scrap", checkRows)
                if (checkRows.length < 1) {
                    NotificationUtil("请选择需要报废的资产", "warning")
                    return
                }
                if ($("#info-0-applyReason").val() == "") {
                    NotificationUtil("请填写申请报废原因", "warning")
                    return
                }
                if ($("#info-0-applyReason").val().trim().length < 9) {
                    NotificationUtil("报废申请不能过于简洁", "warning")
                    return
                }
                // 资产报废
                EiCommunicator.send("FABF0101", "saveFaScrapInfo", eiInfo, {
                    onSuccess: function (ei) {
                        closeParentWindow()
                    }
                })
            })

            $("#CLOSE").on("click", function (e) {
                closeParentWindow()
            });

            // 提交资产报废信息
            $("#SUMBIT").on("click", function (e) {
                if ($("#info-0-identifyDeptNum").val() == "") {
                    NotificationUtil("请选择鉴定科室", "warning")
                    return
                }
                var eiInfo = new EiInfo();
                var scrappedNo = $("#scrappedNo").val();
                eiInfo.set("scrappedNo", scrappedNo);
                eiInfo.setByNode("info")
                EiCommunicator.send("FABF0101", "sumbitFaScrapInfo", eiInfo, {
                    onSuccess: function (ei) {
                        closeParentWindow()
                    }
                })
            })

            $("#applyClose").on("click", function (e) {
                closeParentWindow()
            });

            // 鉴定
            $("#identifyPass").on("click", function (e) {
                var eiInfo = new EiInfo();
                eiInfo.set("result","pass");
                if ($("#info-0-identifyReason").val() == "") {
                    NotificationUtil("请填写技术鉴定意见", "warning")
                    return
                }
                eiInfo.set("scrappedNo",$("#scrappedNo").val());
                eiInfo.set("identifyReason",$("#info-0-identifyReason").val());
                EiCommunicator.send("FABF0101", "sumbitIdentifyReason", eiInfo, {
                    onSuccess: function (ei) {
                        closeParentWindow()
                    }
                })
            })

            $("#identifyReject").on("click", function (e) {
                var eiInfo = new EiInfo();
                eiInfo.set("result","reject");
                if ($("#info-0-identifyReason").val() == "") {
                    NotificationUtil("请填写技术鉴定意见", "warning")
                    return
                }
                eiInfo.set("scrappedNo",$("#scrappedNo").val());
                eiInfo.set("identifyReason",$("#info-0-identifyReason").val());
                EiCommunicator.send("FABF0101", "sumbitIdentifyReason", eiInfo, {
                    onSuccess: function (ei) {
                        closeParentWindow()
                    }
                })
            })

            $("#identifyClose").on("click", function (e) {
                closeParentWindow()
            });

            $("#functionPass").on("click", function (e) {
                var eiInfo = new EiInfo();
                eiInfo.set("result","pass");
                if ($("#info-0-functionReason").val() == "") {
                    NotificationUtil("请填写归口管理部门意见", "warning")
                    return
                }
                eiInfo.set("scrappedNo",$("#scrappedNo").val());
                eiInfo.set("functionReason",$("#info-0-functionReason").val());
                EiCommunicator.send("FABF0101", "sumbitFunctionReason", eiInfo, {
                    onSuccess: function (ei) {
                        closeParentWindow()
                    }
                })
            })

            $("#functionReject").on("click", function (e) {
                var eiInfo = new EiInfo();
                eiInfo.set("result","reject");
                if ($("#info-0-functionReason").val() == "") {
                    NotificationUtil("请填写归口管理部门意见", "warning")
                    return
                }
                eiInfo.set("scrappedNo",$("#scrappedNo").val());
                eiInfo.set("functionReason",$("#info-0-functionReason").val());
                EiCommunicator.send("FABF0101", "sumbitFunctionReason", eiInfo, {
                    onSuccess: function (ei) {
                        closeParentWindow()
                    }
                })
            })

            $("#functionClose").on("click", function (e) {
                closeParentWindow()
            });

            $("#assetPass").on("click", function (e) {
                var eiInfo = new EiInfo();
                eiInfo.set("result","pass");
                if ($("#info-0-assetReason").val() == "") {
                    NotificationUtil("请填写资产管理科意见", "warning")
                    return
                }
                eiInfo.set("scrappedNo",$("#scrappedNo").val());
                eiInfo.set("assetReason",$("#info-0-assetReason").val());
                EiCommunicator.send("FABF0101", "sumbitAssetReason", eiInfo, {
                    onSuccess: function (ei) {
                        closeParentWindow()
                    }
                })
            });

            $("#assetReject").on("click", function (e) {
                var eiInfo = new EiInfo();
                eiInfo.set("result","reject");
                if ($("#info-0-assetReason").val() == "") {
                    NotificationUtil("请填写资产管理科意见", "warning")
                    return
                }
                eiInfo.set("scrappedNo",$("#scrappedNo").val());
                eiInfo.set("assetReason",$("#info-0-assetReason").val());
                EiCommunicator.send("FABF0101", "sumbitAssetReason", eiInfo, {
                    onSuccess: function (ei) {
                        closeParentWindow()
                    }
                })
            });

            $("#assetClose").on("click", function (e) {
                closeParentWindow()
            });


            $("#queryFAInfoType").on("click", function () {
                resultGrid.dataSource.page(1);
            })
        }
    }
}

function closeParentWindow() {
    window.parent['popDataWindow'].close();
    window.parent["resultAGrid"].dataSource.page(1);
    window.parent["resultBGrid"].dataSource.page(1);
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
            }else if (type == "identify") {
                var signatureImg = ei.extAttr.data.signatureImg;
                $("#identifyPic").html("");
                var img = $('<img style="width:260px;height:100px" class="signature">');
                img.attr('src', "data:image/jpeg;base64," + signatureImg);
                var pic = $("#identifyPic");
                pic.append(img);
            } else if (type == "function") {
                var signatureImg = ei.extAttr.data.signatureImg;
                $("#functionPic").html("");
                var img = $('<img style="width:260px;height:100px" class="signature">');
                img.attr('src', "data:image/jpeg;base64," + signatureImg);
                var pic = $("#functionPic");
                pic.append(img);
            } else if (type == "asset") {
                var signatureImg = ei.extAttr.data.signatureImg;
                $("#assetPic").html("");
                var img = $('<img style="width:260px;height:100px" class="signature">');
                img.attr('src', "data:image/jpeg;base64," + signatureImg);
                var pic = $("#assetPic");
                pic.append(img);
            }
        }
    });
}