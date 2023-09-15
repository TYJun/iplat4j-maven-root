$(function () {
    //表格初始化处理
    IPLATUI.EFGrid = new WilpGrid({
        editConfig: {status: ["01", "20", "40"]},
        add: false, submit: true, back: true, addCopy: true,
        submitConfig: {status: ["01"], backStatus: ["20", "40"], serviceName: __ei.serviceName, methodName: "submit"},
        backConfig: {status: ["10", "30", "50"], serviceName: __ei.serviceName, methodName: "withdraw"},
        extentMethod: [{
            buttonId: "ADD",
            call: function () {
                let data = {};
                let checkRows = window["resultGrid"].getCheckedRows();
                data = checkRows.length > 0 ? checkRows[0] : {};
                /**判断是否有验收单**/
                EiCommunicator.send("RMLY0101", "hasUnSignClaim", new EiInfo(), {
                    onSuccess: function (ei) {
                        if (ei.getStatus() == -1) {
                            NotificationUtil(ei.getMsg(), "error");
                            return;
                        }
                        if (ei.get("unSign")) {
                            IPLAT.confirm({
                                message: '<b>您所在科室还有未签收单据，是否去签收?</b>',
                                okFn: function (e) {
                                    window.open(IPLATUI.CONTEXT_PATH + "/web/SICK04", '_blank');
                                    popData(buildUrl("RMLY0101?inqu_status-0-id=#:id#", data, true, "add"));
                                },
                                cancelFn: function (e) {
                                    popData(buildUrl("RMLY0101?inqu_status-0-id=#:id#", data, true, "add"));
                                }
                            })
                        } else {
                            popData(buildUrl("RMLY0101?inqu_status-0-id=#:id#", data, true, "add"));
                        }
                    }
                });

            }
        }, {
            buttonId: "SIGN",
            call: function () {
                let checkRows = window["resultGrid"].getCheckedRows();
                if (checkRows.length < 1) {
                    NotificationUtil("请选择需要签收的记录", "error");
                } else if ("70" != checkRows[0].statusCode) {
                    NotificationUtil("未出库完成或已签收的记录无法签收", "error");
                } else {
                    IPLAT.confirm({
                        message: '<b>您确定要签收吗？</b>',
                        okFn: function (e) {
                            let list = checkRows[0].id
                            //设置电子签名属性
                            //setConfig(__ei['workNo'], checkRows[0]['claimNo']+__ei['name'], "RMC");
                            //签收签名
                            //getSign(fileCode => {})
                            let eiInfo = new EiInfo();
                            eiInfo.set("id", checkRows[0].id); //eiInfo.set("signature", fileCode);
                            EiCommunicator.send("RMLY01", "signAccept", eiInfo, {
                                onSuccess: function (ei) {
                                    if (ei.getStatus() == -1) {
                                        NotificationUtil(ei.getMsg(), "error");
                                        return;
                                    }
                                    NotificationUtil("签收成功");
                                    window["resultGrid"].dataSource.page(1);
                                }
                            });
                        },
                        cancelFn: function (e) {
                        }
                    })
                }
            }
        }, {
            buttonId: "OVER",
            call: function () {
                let checkRows = window["resultGrid"].getCheckedRows();
                if (checkRows.length < 1) {
                    NotificationUtil("请选择需要结束的记录", "error");
                } else if (["70", "80"].includes(checkRows[0].statusCode)) {
                    NotificationUtil("已领用的记录无需结束", "error");
                } else {
                    IPLAT.confirm({
                        message: '<b>您确定要结束吗？</b>',
                        okFn: function (e) {
                            let eiInfo = new EiInfo();
                            eiInfo.set("id", checkRows[0].id);
                            EiCommunicator.send("RMLY01", "over", eiInfo, {
                                onSuccess: function (ei) {
                                    if (ei.getStatus() == -1) {
                                        NotificationUtil(ei.getMsg(), "error");
                                        return;
                                    }
                                    NotificationUtil("结束成功");
                                    window["resultGrid"].dataSource.page(1);
                                }
                            });
                        },
                        cancelFn: function (e) {
                        }
                    })
                }
            }
        }]
    }).buildGrid();

    $.extend(true, IPLATUI.Config, { //全局配置
        EFGrid: {
            pageable: {
                pageSize: 20, // 每页显示的条数，优先级低于 DataSource 中的 pageSize
                pageSizes: [10, 20, 50, 100, 500]
            }
        }
    })

    /**回车键查询**/
    keydown("inqu", "QUERY");

    /**查询**/
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });

    /**重置**/
    $("#REQUERY").on("click", function (e) {
        document.getElementById("inqu-trash").click();
        resetParam(__eiInfo, true, true)
        resultGrid.dataSource.page(1);
    });

});

function buildUrl(url, data, isOpType, type) {
    let reg = /([^?&=]+)=([^&?=]+)/ig, params = [];
    url.replace(reg, function (param, $1, $2) {
        params.push($2);
    });
    params.forEach(e => {
        if (data) {
            url = url.replace(e, data[e.substring(2, e.length - 1)])
        } else {
            url = url.replace(e, "")
        }
    })
    if (isOpType) {
        url = url + (params.length > 0 ? "&" : "?") + "type=" + type;
    }
    return url;
}