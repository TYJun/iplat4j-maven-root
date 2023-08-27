//按钮绑定事件
$(function(){
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });
    //页面加载时查询一次
    //refreshResultGrid();
    //启用校验
    editValidator=IPLAT.Validator({id:"edit"});
    
    //$("#addWnd").css("display", "none");
    //登录用户
    //IPLAT.EFInput.value($("#inqu_status-0-userId"), IPLATUI.USER_ID);
    //隐藏控件
   //
    //绑定下拉框change事件
    IPLATUI.EFSelect={
        "inqu_status-0-weekAbc" : {
            change : function(e){
                if(e.sender._old){
                    //下拉时切换表头
                    var headers = getGridHeader("week_");
                    var days = weekdays[e.sender._old.split("Week")[1]];
                    days = days.replace("[","").replace("]","").split(",");
                    dayHeaderMap = {};
                    headerDayMap = {};
                    for (var i = 0; i < headers.length; i++) {
                        headers[i].html(headers[i].html().split("(")[0]);
                        headers[i].html(headers[i].html() + "("+ days[i] +")");
                        dayHeaderMap[days[i].trim()] = "week_"+i;
                        headerDayMap["week_"+i] = days[i].trim();
                    }
                    //选择有变化时判断日期和班组是否已选
                    var planDatePicker = $("#inqu_status-0-planDate").data("kendoDatePicker").value();
                    var deptNumSelect = IPLAT.EFSelect.value($("#inqu_status-0-deptNum"));
                    if(planDatePicker && deptNumSelect){
                    	//已选择自动加载数据
                    	resultGrid.dataSource.page(1);
                    }
                }else{
                    //清除表头变更
                    var headers = getGridHeader("week_");
                    for (var i = 0; i < headers.length; i++) {
                        headers[i].html(headers[i].html().split("(")[0]);
                    }
                }
            }
        },
        "inqu_status-0-deptNum": {
            change : function(e){
                if(e.sender._old){
                    //选择时加载该班组对应的排班配置保存到scheduleList
                    var eiInfo = new EiInfo();
                    eiInfo.set("deptNum", e.sender._old);
                    EiCommunicator.send("HRPBJHGL01", "queryScheduleList", eiInfo, {
                        onSuccess : function(ei) {
                            var rows = ei.blocks["result"].getMappedRows();
                            //清空原数据
                            scheduleList = [];
                            for (var i = 0; i < rows.length; i++) {
                                //添加新数据
                                scheduleList.push({
                                    valueField:rows[i]["id"],
                                    textField:rows[i]["scheduleName"]
                                });
                            }
                        }
                    });
                    //判断周次是否已选择
                    var weekAbcValue = IPLAT.EFSelect.value("#inqu_status-0-weekAbc");
                    if(!weekAbcValue){
                        //没选的默认选择第一周
                        IPLAT.EFSelect.value("#inqu_status-0-weekAbc","Week0",true);
                    }
                    //重新加载表格
                    resultGrid.dataSource.page(1);
                }else{
                    //清除scheduleList
                    scheduleList = null;
                }
            }
        }
    };
});

//     function(dataItem) {
//     // 通过模版方法，可以修改 cell 中的展示值
//     if(scheduleList){
//         //根据下拉框选择结果调整显示内容
//         for (var i = 0; i < scheduleList.length; i++) {
//             if(scheduleList[i]['valueField'] == dataItem['week_0']){
//                 //添加选项信息到行
//                 dataItem["week_0"] = scheduleList[i]['valueField'];
//                 dataItem["week_0_scheduleName"] = scheduleList[i]['textField'];
//                 var planStatusName = dataItem['week_0_planStatusName'];
//                 planStatusName = planStatusName ? planStatusName : "(在岗)"
//                 dataItem['week_0_planStatusName'] = planStatusName;
//                 dataItem['week_0_planDate'] = headerDayMap["week_0"];
//                 return scheduleList[i]['textField'] + planStatusName;
//             }
//         }
//     }
//     return dataItem['week_0'] ? dataItem['week_0'] : '';//初始化
// }
function cellTemplate(dataItem,field){
    // 通过模版方法，可以修改 cell 中的展示值
    if(scheduleList){
        //根据下拉框选择结果调整显示内容
        for (var i = 0; i < scheduleList.length; i++) {
            if(scheduleList[i]['valueField'] == dataItem[field]){
                //添加选项信息到行
                dataItem[field] = scheduleList[i]['valueField'];
                dataItem[field+"_scheduleName"] = scheduleList[i]['textField'];
                var planStatusName = dataItem[field+'_planStatusName'];
                planStatusName = planStatusName ? planStatusName : "(在岗)"
                dataItem[field+'_planStatusName'] = planStatusName;
                dataItem[field+'_planDate'] = headerDayMap[field];
                return scheduleList[i]['textField'] + planStatusName;
            }
        }
    }
    return dataItem[field] ? dataItem[field] : '';//初始化
}

//上传文件
function uploadFile3(eiInfo){
	EiCommunicator.send("HRPBJHGL01", "uploadPlan", eiInfo, {
        onSuccess : function(ei) {
            console.log(ei);
            if(ei.status == 1){
                NotificationUtil(ei.msg, "success");
                //刷新grid
                refreshResultGrid();
            }else{
                NotificationUtil(ei.msg, "warning");
            }
        }
    });
}
