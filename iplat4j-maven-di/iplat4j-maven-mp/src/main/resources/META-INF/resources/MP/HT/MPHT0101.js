$(function () {
    /**隐藏按钮**/
    setTimeout(function () {
        if (__ei.type == "see") {
            $("#SAVE_CONT").css("display", "none");
            $("#CANCEL").css("display", "none");
        }
    }, 300)

    /** 附件上传 */
    IPLATUI.EFUpload = new WilpUpload({
        id: "contentFile",
        addGridRow: {
            remark: "",
            recCreator: __eiInfo.get("inqu_status-0-recCreator"),
            recCreatorName: __eiInfo.get("inqu_status-0-recCreatorName")
        }
    }).buildGridUpload();

    /**页面数据回显**/
    $("#inqu_status-0-contNo").val(__eiInfo.get("inqu_status-0-contNo"));
    IPLAT.EFPopupInput.setAllFields( $("#inqu_status-0-manageDeptNum") , __eiInfo.get("inqu_status-0-manageDeptNum"),
        __eiInfo.get("inqu_status-0-manageDeptName"));
    IPLAT.EFPopupInput.setAllFields( $("#inqu_status-0-managerNum") , __eiInfo.get("inqu_status-0-managerNum"),
        __eiInfo.get("inqu_status-0-managerName"));
    IPLAT.EFPopupInput.setAllFields( $("#inqu_status-0-supplierNum") , __eiInfo.get("inqu_status-0-supplierNum"),
        __eiInfo.get("inqu_status-0-supplierName"));

    /**Grid初始化**/
    IPLATUI.EFGrid = {
        "detail": {
            pageable: false,
            toolbarConfig: {
                hidden: false, add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "ADD_DETAIL", text : "选择采购计划明细", layout : "3", click : function() {
                       popData("MPHT0102")
                    }
                }]
            }
        },
        "file": {
            pageable: false,
            toolbarConfig: {
                hidden: false, add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "UPLOAD", text : "上传", layout : "3", click : function() {
                        fileChooseWindow.open().center()
                    }
                },{
                    name: "DEL", text : "删除", layout : "3", click : function() {
                        let checkRows = fileGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            fileGrid.removeRows(checkRows);
                        } else {
                            IPLAT.NotificationUtil("请选择需要删除的附件信息", "warning")
                        }
                    }
                },{
                    name: "DOWNLOAD", text : "下载", layout : "3", click : function() {
                        let checkRows = fileGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            checkRows.forEach(row => downLoadFile(row.docId))
                        } else {
                            IPLAT.NotificationUtil("请选择需要下载的文件", "warning")
                        }
                    }
                }]
            }
        }
    }

    /**取消按钮**/
    $("#CANCEL").on("click", function () {
        window.parent['popDataWindow'].close();
    });

    /**保存合同**/
    let validator = IPLAT.Validator({ id: "inqu" });
    $("#SAVE_CONT").unbind('click').on('click', function() {
        // 防止连续提交
        $("#SAVE_CONT").attr("disabled", true);
        setTimeout(function () { $("#SAVE_CONT").attr("disabled", false);}, 5000);

        //参数校验
        if(!validator.validate()) {
            IPLAT.NotificationUtil("请正确输入参数")
        } else {
            //参数处理
            let eiInfo = new EiInfo();
            eiInfo.setByNode("inqu");
            //时间顺序校验
            if(!validateDate(eiInfo)) { return; }

            //特殊格式参数赋值
            eiInfo.set("inqu_status-0-currencyName", IPLAT.EFSelect.text($("#inqu_status-0-currencyCode")));
            eiInfo.set("inqu_status-0-purchaseWayName", IPLAT.EFSelect.text($("#inqu_status-0-purchaseWay")));
            eiInfo.set("inqu_status-0-payWayName", IPLAT.EFSelect.text($("#inqu_status-0-payWay")));
            eiInfo.set("inqu_status-0-fundingSourceName", IPLAT.EFSelect.text($("#inqu_status-0-fundingSourceNum")));
            eiInfo.set("inqu_status-0-supplierName", IPLAT.EFPopupInput.text($("#inqu_status-0-supplierNum")));
            eiInfo.set("inqu_status-0-manageDeptName", IPLAT.EFPopupInput.text($("#inqu_status-0-manageDeptNum")));
            eiInfo.set("inqu_status-0-managerName", IPLAT.EFPopupInput.text($("#inqu_status-0-managerNum")));

            //表格数据
            let detailList = detailGrid.getDataItems();
            //明细校验
            if(!validateDetails(detailList)) { return;}
            let fileList = fileGrid.getDataItems();
            eiInfo.set("detailList", detailList);
            eiInfo.set("fileList", fileList);

            //保存
            EiCommunicator.send("MPHT0101", "save", eiInfo, {
                onSuccess: function (ei) {
                    if (ei.getStatus() < 0) {
                        IPLAT.NotificationUtil(ei.getMsg(), "error");
                    } else{
                        IPLAT.NotificationUtil(ei.msg)
                        window.parent['popDataWindow'].close();
                        window.parent["resultGrid"].dataSource.page(1);
                    }
                }
            })
        }
    });

    /**对接合同选择按钮**/
    $("#selectCont").on("click", function () {
        popData("MPHT0104", "contChoose")
    });
})

/**
 * 合同选择数据回显
 * @param row
 */
function recoverCont(row) {
    let ei = new EiInfo();
    ei.setByNode("inqu");
    let block = ei.getBlock("inqu_status");
    Object.keys(row).forEach((key) => {
        block.setCell("0", key, row[key]);
    })
    IPLAT.fillNode(document.getElementById("inqu"), ei);
    //聚焦，显示金额（框架的一个问题，不聚焦的话，format后的金额不显示）
    //document.getElementById("inqu_status-0-contCost_textField").focus();
}


/**
 * 添加物资（过滤重复）
 */
function addRows(checkRows){
    checkRows.forEach(detail => {
        detail["purchasePlanId"] = detail['purchaseId'];
        detail["purchaseNum"] = detail['num'];
        let purchaseNum = $.isNumeric(detail['num']) ? +detail['num'] : 0;
        let contedNum = $.isNumeric(detail['contedNum']) ? +detail['contedNum'] : 0;
        detail["num"] = purchaseNum > 0 && contedNum >= 0 ? (purchaseNum - contedNum) : "0";

    });
    distinctGridAdd("detail", checkRows, undefined, "matNum", "matTypeId", "purchasePlanId")
}

/**
 * 时间顺序校验
 * @param eiInfo
 */
function validateDate(eiInfo) {
    let contSignDate = eiInfo.get("inqu_status-0-signDate");
    let contValidDate = eiInfo.get("inqu_status-0-validDate");
    let contOverDate = eiInfo.get("inqu_status-0-overDate");
    if (contSignDate > contOverDate) {
        IPLAT.NotificationUtil("合同签订日期不能大于合同终止日期", "warning");
        return false;
    }
    if (contValidDate > contOverDate) {
        IPLAT.NotificationUtil("合同生效日期不能大于合同终止时间", "warning");
        return false;
    }
    return true;
}

/**
 * 校验合同明细
 * @param detailList
 * @returns {boolean}
 */
function validateDetails(detailList) {
    if(!detailList || detailList.length == 0 ) {
        IPLAT.NotificationUtil("合同明细不能为空");
        return false;
    }
    let isAllEmpty = true;
    for (let detail of detailList) {
        //获取原先的合同数量
        let originalNum = getOriginalNum(detail)
        //校验
        let isNotEmpty = $.isNumeric(detail.num) && detail.num > 0
        let usableNum = parseFloat(detail.purchaseNum - detail.contedNum + originalNum)
        if(isNotEmpty && detail.num > usableNum) {
            IPLAT.NotificationUtil(`物资编码为${detail.matNum},物资名称为${detail.matName}的物资
                    本次合同数量超过采购计划数量减去已生成合同数量`, "error");
            return false;
        }
        isAllEmpty = isAllEmpty && !isNotEmpty;
    }
    if(isAllEmpty) {
        IPLAT.NotificationUtil("本次合同数量不能全部为空", "error");
    }
    return !isAllEmpty;
}

function getOriginalNum(detail) {
    if("add" ==__ei.type) {
        return 0;
    }
    let rows = __eiInfo.getBlock("detail").getMappedRows();
    for(let row of rows){
        if(row.matNum == detail.matNum && row.matTypeId == detail.matTypeId && row.purchasePlanId == detail.purchasePlanId) {
            return row.num;
        }
    }
    return 0;
}