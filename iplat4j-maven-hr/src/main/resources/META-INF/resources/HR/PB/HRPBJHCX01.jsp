<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 订餐时间配置 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <script type="text/javascript" src="../HR/common/js/DRCommon.js"></script>
    <link rel="stylesheet" type="text/css" href="../DR/common/css/DRCommon.css">
    <style>
        .selectedCell{
            border-left: #FC5531 1px solid !important;
            border-bottom: #FC5531 1px solid !important;
            border-right: #FC5531 1px solid !important;
            border-top: #FC5531 1px solid !important;
        }

    </style>
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <EF:EFInput ename="inqu_status-0-deptName" disabled="true" style="display:none;"/>
        <div class="row">
            <EF:EFDatePicker ename="inqu_status-0-planDate" cname="排班日期" ratio="3:8"
                             format="yyyy-MM-dd" role="date" colWidth="4" />

            <EF:EFSelect ename="inqu_status-0-weekAbc" cname="周次" colWidth="4" ratio="3:8" optionLabel="请选择">
                <EF:EFOptions blockId="planWeek" textField="typeName" valueField="typeCode" />
            </EF:EFSelect>
            <EF:EFSelect ename="inqu_status-0-deptNum" cname="班组名称" optionLabel="请选择"
                         resultId="deptData" filter="contains" valueField="deptNum" textField="deptName"
                         serviceName="HRPBSJPZ01" methodName="queryDept" colWidth="4" ratio="3:8">
            </EF:EFSelect>

            <EF:EFInput ename="inqu_status-0-scheduleName" cname="班次名称" colWidth="4" ratio="3:8"/>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="false"  >
            <!-- 展示列 -->
            <EF:EFColumn ename="workName" cname="姓名" width="150" align="center" readonly="true"/>
            <EF:EFColumn ename="week_0" cname="周一" width="100" align="center" />
            <EF:EFColumn ename="week_1" cname="周二" width="100" align="center" />
            <EF:EFColumn ename="week_2" cname="周三" width="100" align="center" />
            <EF:EFColumn ename="week_3" cname="周四" width="100" align="center" />
            <EF:EFColumn ename="week_4" cname="周五" width="100" align="center" />
            <EF:EFColumn ename="week_5" cname="周六" width="100" align="center" />
            <EF:EFColumn ename="week_6" cname="周日" width="100" align="center" />
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">
    var ctx = "${ctx}";
    var weekdays = getBlocksMappedRows("planDate")[0];
    var dayWeekMap = {};
    //例：{"2022-01-17": "week_0"}
    var dayHeaderMap = {};
    //例：{"week_0" :"2022-01-17"}
    var headerDayMap = {};
    //班次列表
    var scheduleList = null;
    //初始化日期周次集合
    for(var key in weekdays){
        // console.log("属性：" + key + ",值：" + weekdays[key]);
        //记录周次对应的七天日期
        dayWeekMap["Week"+ key] = weekdays[key];
    }
    //提交类型
    var submitType = "insert";
    var editValidator = null;
    var oldDatePicker = "";
    var selectedFiled = "";
    var $oldSelectCell = null;
    /*** 获取省名称 ***/
    function provinceName(province) {
        var provinces = __eiInfo.getBlock("province").getMappedRows();
        for (var i = 0; i < provinces.length; i++) {
            if (provinces[i]['provinceEName'] == province) {
                return provinces[i]['provinceCName'] || "";
            }
        }
        return "";
    }
    IPLATUI.EFGrid = {
        "result": {
            dataBinding: function (e) {
                //grid数据绑定时对属性进行处理
                for (var i = 0, length = e.items.length; i < length; i++) {
                    if(isAvailable(e.items[i].planContent)){
                        //拆分重组数据
                        var planContent = e.items[i].planContent;
                        var contents = planContent.split(",");
                        var item = e.items[i];
                        for (var j = 0; j < contents.length; j++) {
                            var content = contents[j].split("@@");
                            //拆分第一位日期，第二位班次名称，第三位排班id，第四位班次id，第五位排班状态
                            var planDate = content[0], scheduleName = content[1],planId = content[2],scheduleId = content[3],planStatus = content[4];
                            //根据日期对应关系，将班次名称填充到grid
                            var weekHeader = dayHeaderMap[planDate];
                            //处理在岗/休息状态名称
                            if(planStatus == "00"){
                                item[weekHeader+"_planStatusName"] = "(休息)";
                            }else if(planStatus == "01"){
                                item[weekHeader+"_planStatusName"] = "(在岗)";
                            }
                            item[weekHeader+"_planDate"] = planDate;
                            item[weekHeader+""] = scheduleName + item[weekHeader+"_planStatusName"];
                            item[weekHeader+"_planId"] = planId;
                            item[weekHeader+"_scheduleId"] = scheduleId;
                            item[weekHeader+"_scheduleName"] = scheduleName;
                            item[weekHeader+"_planStatus"] = planStatus;
                        }
                    }
                }
            },
            onCellClick: function (e) {
                //单元格点击事件
                if (e.field && e.field.startsWith("week_")) {
                    //当前选中列
                    selectedFiled = e.field;
                    var thisIndex = resultGrid.select().index();
                    console.log("当前选中行" + thisIndex + ",列：" + selectedFiled);
                    //清除旧样式
                    if($oldSelectCell){
                        $oldSelectCell.attr("class","");
                    }
                    //添加新样式
                    $oldSelectCell = e.td;
                    $oldSelectCell.attr("class","selectedCell");
                }
            },
            beforeRequest: function (e) {
                if(e.type == "update"){
                    //添加班组名称到条件
                    IPLAT.EFInput.value($("#inqu_status-0-deptName"),IPLAT.EFSelect.text($("#inqu_status-0-deptNum")));
                }
            },
            columns: [
                {
                    field: "week_0",
                    editor:function (container, options) {
                        //渲染下拉框
                        drawGridEditorSelect("scheduleSelect",options,container,scheduleList);
                    },
                    template: function(dataItem){
                        // 通过模版方法，可以修改 cell 中的展示值
                        return cellTemplate(dataItem,'week_0');
                    }
                },
                {
                    field: "week_1",
                    editor:function (container, options) {
                        //渲染下拉框
                        drawGridEditorSelect("scheduleSelect",options,container,scheduleList);
                    },
                    template: function(dataItem){
                        // 通过模版方法，可以修改 cell 中的展示值
                        return cellTemplate(dataItem,'week_1');
                    }
                },
                {
                    field: "week_2",
                    editor:function (container, options) {
                        //渲染下拉框
                        drawGridEditorSelect("scheduleSelect",options,container,scheduleList);
                    },
                    template: function(dataItem) {
                        // 通过模版方法，可以修改 cell 中的展示值
                        return cellTemplate(dataItem,'week_2');
                    }
                },
                {
                    field: "week_3",
                    editor:function (container, options) {
                        //渲染下拉框
                        drawGridEditorSelect("scheduleSelect",options,container,scheduleList);
                    },
                    template: function(dataItem) {
                        // 通过模版方法，可以修改 cell 中的展示值
                        return cellTemplate(dataItem,'week_3');
                    }
                },
                {
                    field: "week_4",
                    editor:function (container, options) {
                        //渲染下拉框
                        drawGridEditorSelect("scheduleSelect",options,container,scheduleList);
                    },
                    template: function(dataItem) {
                        // 通过模版方法，可以修改 cell 中的展示值
                        return cellTemplate(dataItem,'week_4');
                    }
                },
                {
                    field: "week_5",
                    editor:function (container, options) {
                        //渲染下拉框
                        drawGridEditorSelect("scheduleSelect",options,container,scheduleList);
                    },
                    template: function(dataItem){
                        // 通过模版方法，可以修改 cell 中的展示值
                        return cellTemplate(dataItem,'week_5');
                    }
                },
                {
                    field: "week_6",
                    editor:function (container, options) {
                        //渲染下拉框
                        drawGridEditorSelect("scheduleSelect",options,container,scheduleList);
                    },
                    template: function(dataItem) {
                        // 通过模版方法，可以修改 cell 中的展示值
                        return cellTemplate(dataItem,'week_6');
                    }
                }
            ]
        }
    }

    // 设置日期控件的事件
    IPLATUI.EFDatePicker = {
        "inqu_status-0-planDate": {
            // 设置不同的日期会触发 change 事件
            change: function (e) {
                //e 是事件参数对象，e.sender 是日期控件对象(valueDatePicker)
                console.log(e.sender.value());
                //格式化获取到的值
                console.log(kendo.toString(e.sender.value(),"yyyy-MM-dd"));

                //判断年月是否发生变化
                var newDate = kendo.toString(e.sender.value(),"yyyy-MM");
                var oldDate = kendo.toString(oldDatePicker,"yyyy-MM");
                if(newDate != oldDate){
                    //年和月发生了变化,设置周次下拉框数据源
                    var date = kendo.toString(e.sender.value(),"yyyy-MM-dd");
                    var eiInfo = new EiInfo();
                    eiInfo.set("date", date);
                    EiCommunicator.send("HRPBJHGL01", "queryPlanWeek", eiInfo, {
                        onSuccess : function(ei) {
                            var rows = ei.blocks["planWeek"].getMappedRows();
                            weekdays = rows[0];
                            console.log(rows);
                            var list = [];
                            dayWeekMap = {};
                            for(var key in rows[0]){
                                //console.log("属性：" + key + ",值：" + rows[0][key]);
                                list.push({
                                    valueField:"Week"+ key,
                                    textField:"第"+(Number.parseInt(key)+1)+"周"
                                });
                                //记录周次对应的七天日期
                                dayWeekMap["Week"+ key] = rows[0][key];
                            }
                            var dataSource = new kendo.data.DataSource({ data: list });
                            IPLAT.EFSelect.setDataSource($("#inqu_status-0-weekAbc"), dataSource);
                            //切换后默认选中第一周
                            IPLAT.EFSelect.value("#inqu_status-0-weekAbc","Week0",true);
                            //清除表头变更
                            var headers = getGridHeader("week_");
                            for (var i = 0; i < headers.length; i++) {
                                headers[i].html(headers[i].html().split("(")[0]);
                            }
                        }
                    });
                }
                //更新全局变量old日期
                oldDatePicker = kendo.toString(e.sender.value(),"yyyy-MM-dd");
            }
        }
    }

    // 关闭事件
    IPLATUI.EFWindow = {
        "shEdit" : {
            close : function(e) {
                // EFRegion的id-trash
                $("#edit-trash").click();
            }
        }
    }

    $(function() {
        //设置初始日期
        $("#inqu_status-0-planDate").data("kendoDatePicker").value(new Date());
        oldDatePicker = kendo.toString($("#inqu_status-0-planDate").data("kendoDatePicker").value(),"yyyy-MM-dd");

        // 取消
        $("#cancel").on("click", function(e) {
            window['shEditWindow'].close();
        });
    });
    $(function() {
        //登录用户
        // IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
        //隐藏控件
        $("#inqu_status-0-deptName").closest(".col-md-4").attr("style", "display:none;");
        // 启用校验
        editValidator = IPLAT.Validator({
            id : "edit"
        });
    });
</script>