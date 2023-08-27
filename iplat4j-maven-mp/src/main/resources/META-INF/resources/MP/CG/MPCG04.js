$(function (){

    // 查询
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#RESET").on("click", function (e) {
        $("#inqu_status-0-purchaseNo").val("");
        resetTime("inqu_status-0-recCreateTimeS", "inqu_status-0-recCreateTimeE");
        resultGrid.dataSource.page(1);
    });


    /**
     * 设置日期初始值
     */
    function resetTime(beginId, endId) {
        let lastDate = new Date();
        lastDate.setMonth(lastDate.getMonth()-1);
        $("#"+beginId).val(lastDate.Format("yyyy-MM-dd"));
        $("#"+endId).val(new Date().Format("yyyy-MM-dd"));
    }


    IPLATUI.EFGrid = {
        "result": {
            /*columns:[ {
                field: "price",
                readonly: true,
                template: "<span>#=price#元</span>",
            }],*/
            onRowClick: function (e) {
                e.preventDefault();
                $("#inqu_status-0-purchaseId").val( e.model['id'])
                window["detailGrid"].dataSource.page(1);
            },
            toolbarConfig: {
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "Approval", text: "审批", layout: "3",
                    click: function () {
                        let checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            let id = checkRows[0].id;
                            popData(id,"MPCG0401");
                        } else {
                            IPLAT.NotificationUtil("请选择一条采购计划进行审批","error")
                        }
                    }
                },
                    {
                        name: "BatchThrough", text: "批量通过", layout: "3",
                        click: function () {
                            let checkRows = resultGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                let planIds = checkRows.map(row => row['id'])
                                let eiInfo = new EiInfo(); eiInfo.set("planIds", planIds);
                                EiCommunicator.send("MPCG0401", "pass", eiInfo, {
                                    onSuccess: function (ei) {
                                        if(ei.getStatus() === -1){
                                            IPLAT.NotificationUtil(ei.getMsg());
                                        } else{
                                            NotificationUtil("批量通过成功", "success");
                                            window.parent['popDataWindow'].close();
                                            window.parent.resultGrid.dataSource.page(1);
                                        }
                                    }
                                })
                            } else {
                                IPLAT.NotificationUtil("请选择采购计划进行审批","error")
                            }
                        }
                    },
                    {
                        name: "BulkRejection", text: "批量驳回", layout: "3",
                        click: function () {
                            let checkRows = resultGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                let planIds = checkRows.map(row => row['id'])
                                let eiInfo = new EiInfo(); eiInfo.set("planIds", planIds);
                                EiCommunicator.send("MPCG0401", "reject", eiInfo, {
                                    onSuccess: function (ei) {
                                        if(ei.getStatus() === -1){
                                            IPLAT.NotificationUtil(ei.getMsg());
                                        } else{
                                            NotificationUtil("批量驳回成功", "success");
                                            window.parent['popDataWindow'].close();
                                            window.parent.resultGrid.dataSource.page(1);
                                        }
                                    }
                                })
                            } else {
                                IPLAT.NotificationUtil("请选择采购计划进行审批","error")
                            }
                        }
                    }
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
 * 打开窗口
 * @param id
 * @param service
 */
function popData(id, service) {
    let url = IPLATUI.CONTEXT_PATH + "/web/"+ service +"?inqu_status-0-id=" + id;
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
