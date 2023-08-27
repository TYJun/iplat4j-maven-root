$(function (){
    // 查询
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });

    // 重置按钮
    $("#RESET").on("click", function (e) {
        $("#inqu_status-0-purchaseNo").val("");
        $("#inqu_status-0-statusCode").val("");
        resetTime("inqu_status-0-recCreateTimeS", "inqu_status-0-recCreateTimeE");
        resultGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "result": {
            columns:[ {field: "purchaseCost", readonly: true, template: "<span>#=purchaseCost#元</span>",}],
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "new", text: "新增", layout: "3",
                    click: function () {
                        popDataWindow.setOptions({"title":"新增采购计划"});
                        popData("","add","MPCG0101");
                    }
                }, {
                    name: "edit", text: "编辑", layout: "3",
                    click: function () {
                        var checkRows = resultGrid.getCheckedRows();
                        var statusCode = checkRows[0].statusCode;
                        if (checkRows.length > 0) {
                            if(statusCode == "01" || statusCode == "20"){
                                popData(checkRows[0].id,"edit","MPCG0105");
                            }else {
                                IPLAT.NotificationUtil("该状态不可编辑","error")
                            }
                        } else {
                            IPLAT.NotificationUtil("请选择一条采购计划进行编辑","error")
                        }
                    }
                }, {
                    name: "deleter", text: "删除", layout: "3",
                    click: function () {
                        var checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            var id = checkRows[0].id;
                            var eiInfo = new EiInfo();
                            eiInfo.set("id",id);
                            IPLAT.confirm({
                                message: '<b>确定删除操作吗？</b></i>',
                                okFn: function (e) {
                                    EiCommunicator.send("MPCG01", "deleter", eiInfo, {
                                        onSuccess: function (ei) {
                                            IPLAT.NotificationUtil(ei.msg)
                                            resultGrid.dataSource.page(1);
                                        }
                                    })
                                },
                                cancelFn: function (e) {}
                            })
                        } else {
                            IPLAT.NotificationUtil("请选择一条采购计划进行删除","error")
                        }
                    }
                }
                // ,
                //     {
                //     name: "goodsConfig", text: "拆分", layout: "3",
                //     click: function () {
                //         var checkRows = resultGrid.getCheckedRows();
                //         if (checkRows.length > 0) {
                //             var id = checkRows[0].id;
                //             popData(id,"split","MPCG0103");
                //         } else {
                //             IPLAT.NotificationUtil("请选择一条采购计划进行拆分","error")
                //         }
                //     }
                // }
                , {
                    name: "submit", text: "提交", layout: "3",
                    click: function () {
                        var checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {

                            var message = '';

                            var info = new EiInfo();
                            info.set("purchaseType",checkRows[0].purchaseType);
                            info.set("purchaseCost",checkRows[0].purchaseCost);
                            EiCommunicator.send("MPCG01", "comparePurchaseConfigCost", info, {
                                onSuccess: function (ei) {
                                    if(ei.getStatus() === -1){
                                        message = '<b>已超过剩余额度'+ ei.extAttr.limit +' 确定提交吗？</b></i>';
                                    } else {
                                        message = '<b>确定提交吗？</b></i>'
                                    }

                                    var id = checkRows[0].id;
                                    var status = checkRows[0].statusCode;
                                    var eiInfo = new EiInfo();
                                    eiInfo.set("id",id);
                                    eiInfo.set("statusCode",status);
                                    IPLAT.confirm({
                                        message: message,
                                        okFn: function (e) {
                                            EiCommunicator.send("MPCG01", "submit", eiInfo, {
                                                onSuccess: function (ei) {
                                                    if(ei.getStatus() ===500){
                                                        IPLAT.alert(ei.getMsg());
                                                    }else if(ei.getStatus() === 200 || ei.getStatus() >= 0 ) {
                                                        resultGrid.dataSource.page(1);
                                                    }
                                                }
                                            })
                                        },
                                        cancelFn: function (e) {}
                                    })
                                }
                            })
                        } else {
                            IPLAT.NotificationUtil("请选择一条采购计划进行提交","error")
                        }
                    }
                }, {
                        name: "withdraw", text: "撤回", layout: "3",
                        click: function () {
                            var checkRows = resultGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                var id = checkRows[0].id;
                                var status = checkRows[0].statusCode;
                                var eiInfo = new EiInfo();
                                eiInfo.set("id",id);
                                eiInfo.set("statusCode",status);
                                IPLAT.confirm({
                                    message: '<b>确定撤回吗？</b></i>',
                                    okFn: function (e) {
                                        EiCommunicator.send("MPCG01", "withdraw", eiInfo, {
                                            onSuccess: function (ei) {
                                                if(ei.getStatus() ===500){
                                                    IPLAT.NotificationUtil(ei.getMsg());
                                                }else {
                                                    resultGrid.dataSource.page(1);
                                                }
                                            }
                                        })
                                    },
                                    cancelFn: function (e) {}
                                })
                            } else {
                                IPLAT.NotificationUtil("请选择一条采购计划进行撤回","error")
                            }
                        }
                    },
                    {
                        name: "see", text: "详情", layout: "3",
                        click: function () {
                            let checkRows = resultGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                    let id = checkRows[0].id;
                                    popData(id,"see","MPCG0106");
                            } else {
                                IPLAT.NotificationUtil("请选择一条采购计划进行查看","error")
                            }
                        }
                    }
                    // ,
                    // {
                    //     name: "contract", text: "生成合同", layout: "3",
                    //     click: function () {
                    //         var checkRows = resultGrid.getCheckedRows();
                    //         var statusCode = checkRows[0].statusCode;
                    //         if (checkRows.length > 0) {
                    //             if(statusCode == "30" || statusCode == "40"){
                    //                 var id = checkRows[0].id;
                    //                 popData(id,"contract","MPCG0104");
                    //             }else {
                    //                 IPLAT.NotificationUtil("请选择一条审批通过的工单进行生成合同","error")
                    //             }
                    //         } else {
                    //             IPLAT.NotificationUtil("请选择一条采购计划进行生成合同","error")
                    //         }
                    //     }
                    // }
                    ]
            },
            dataBound: function (e) {

            },
            loadComplete: function (e) {

            }
        }
    }
})


/**
 * 时间格式化
 * @param fmt ： 格式
 * @returns {*}
 * @constructor
 */
Date.prototype.Format = function (fmt) {
    let o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (let k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 * 设置日期初始值
 */
function resetTime(beginId, endId) {
    let lastDate = new Date();
    lastDate.setMonth(lastDate.getMonth()-1);
    $("#"+beginId).val(lastDate.Format("yyyy-MM-dd"));
    $("#"+endId).val(new Date().Format("yyyy-MM-dd"));
}


/**
 * 打开新增/编辑窗口
 * @param id
 * @param type
 * @param service
 */
function popData(id, type, service) {
    let url = IPLATUI.CONTEXT_PATH + "/web/"+ service +"?initLoad&id=" + id + "&type=" + type;
    let popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url,
            });
        }
    });
    popDataWindow.open().center();
}
