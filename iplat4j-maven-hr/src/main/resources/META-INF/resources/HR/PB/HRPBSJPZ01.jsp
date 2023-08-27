<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 订餐时间配置 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <script type="text/javascript" src="../HR/common/js/DRCommon.js"></script>
    <link rel="stylesheet" type="text/css" href="../HR/common/css/DRCommon.css">
    <style>
        .k-link-date {
            display: none !important;
        }
    </style>

    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
    <EF:EFInput ename="inqu_status-0-userId" disabled="true" style="display:none;"/>
        <div class="row">
            <EF:EFSelect ename="inqu_status-0-deptNum" cname="班组名称" optionLabel="请选择"
                         resultId="deptData" filter="contains" valueField="deptNum" textField="deptName"
                         serviceName="HRPBSJPZ01" methodName="queryDept" colWidth="4" ratio="3:8">

            </EF:EFSelect>
            <EF:EFInput ename="inqu_status-0-scheduleName" cname="班次名称" colWidth="4" ratio="3:8"/>
        </div>
    </EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="false" checkMode="row">
		    <!-- 隐藏列 -->
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="day_span" cname="是否跨天(0-否，1-是)" width="100" hidden="true" />
			<EF:EFColumn ename="status" cname="状态(0-停用，1-启用)" width="100" hidden="true" />
			<!-- 展示列 -->
			<EF:EFColumn ename="startTime" cname="开始时间" width="150" align="center" readonly="true"/>
			<EF:EFColumn ename="endTime" cname="结束时间" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="restStartTime" cname="休息开始时间" width="150" align="center" />
			<EF:EFColumn ename="restEndTime" cname="休息结束时间" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="sfkt" cname="是否跨天" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="scheduleName" cname="班次名称" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="deptNum" cname="班组编码" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="deptName" cname="班组名称" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="zt" cname="状态" width="100" align="center" readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<!-- 弹窗 -->
    <EF:EFWindow id="shEdit" width="735px" height="300px" modal="true" url="" title="录入">
        <EF:EFRegion id="edit" title="" showClear="true">
            <div class="row" >
                <%--<EF:EFSelect ename="deptNum" cname="班组名称" optionLabel="请选择" colWidth="10" required="true">
                    <EF:EFOptions blockId="deptData" textField="deptName" valueField="deptNum" />
                </EF:EFSelect>--%>
                <EF:EFSelect ename="deptNum" cname="班组名称" colWidth="6"
                             required="true" resultId="deptData" optionLabel="请选择"
                             serviceName="HRPBSJPZ01" methodName="queryDept" filter="contains"
                             valueField="deptNum" textField="deptName">
                </EF:EFSelect>
                <EF:EFInput ename="scheduleName" cname="班次名称" colWidth="6" required="true" maxLength="20" />
            </div>
            <div class="row" >
                
            </div>
            <div class="row" >
                <EF:EFDatePicker ename="startTime" role="time" colWidth="6" required="true" readonly="false"
                                 format="HH:mm" cname="班次开始时间">
                </EF:EFDatePicker>
                <EF:EFDatePicker ename="endTime" role="time" colWidth="6" required="true" readonly="false"
                                 format="HH:mm" cname="班次结束时间">
                </EF:EFDatePicker>
                <%-- <EF:EFDateSpan startName="startTime" endName="endTime" startCname="班次开始时间" endCname="班次结束时间" bindWidth="10" extChar="至"
                               role="datetime" required="true" readonly="true" placeholder="请选择时间"
                               parseFormats="['HH:mm']" format="HH:mm"/> --%>
            </div>
            <div class="row">
                <%-- <EF:EFDateSpan startName="restStartTime" endName="restEndTime" startCname="休息开始时间" endCname="休息结束时间" bindWidth="10" extChar="至"
                               role="datetime" readonly="true" placeholder="请选择时间"
                               parseFormats="['HH:mm']" format="HH:mm"/> --%>
                <EF:EFDatePicker ename="restStartTime" role="time" colWidth="6" readonly="false"
                                 format="HH:mm" cname="休息开始时间">
                </EF:EFDatePicker>
                <EF:EFDatePicker ename="restEndTime" role="time" colWidth="6" readonly="false"
                                 format="HH:mm" cname="休息结束时间">
                </EF:EFDatePicker>
            </div>
            <div class="row">
                <%-- <EF:EFDateSpan startName="clockStartTime" endName="clockEndTime" startCname="出勤开始时间" endCname="下班截止时间" bindWidth="10" extChar="至"
                               role="datetime" readonly="true" placeholder="请选择时间"
                               parseFormats="['HH:mm']" format="HH:mm"/> --%>
                <EF:EFDatePicker ename="clockStartTime" role="time" colWidth="6" readonly="false"
                                 format="HH:mm" cname="出勤开始时间">
                </EF:EFDatePicker>
                <EF:EFDatePicker ename="clockEndTime" role="time" colWidth="6" readonly="false"
                                 format="HH:mm" cname="下班截止时间">
                </EF:EFDatePicker>
            </div>
            
            <div class="row">
                <EF:EFSelect ename="daySpan" cname="是否跨天" optionLabel="请选择" defaultValue="0" colWidth="6" >
                    <EF:EFOption label="是" value="1"/>
                    <EF:EFOption label="否" value="0"/>
                </EF:EFSelect>
                <EF:EFSelect ename="status" cname="班次状态" optionLabel="请选择" defaultValue="1" colWidth="6" >
                    <EF:EFOption label="启用" value="1"/>
                    <EF:EFOption label="停用" value="0"/>
                </EF:EFSelect>
            </div>
        </EF:EFRegion>
        <EF:EFButton ename="cancel" cname="取消"  class="k-button window-btn" style="float:left;"/>
        <EF:EFButton ename="submit" cname="提交"  class="k-button window-btn" style="float:right;"/>
    </EF:EFWindow>
</EF:EFPage>

<script type="text/javascript">
	//提交类型
	var submitType = "insert";
	var editValidator = null;
    IPLATUI.EFGrid = {
        "result": {
            dataBinding: function (e) {
                //grid数据绑定时对属性进行处理
                for (var i = 0, length = e.items.length; i < length; i++) {
                    if(isAvailable(e.items[i].status)){
                        e.items[i].zt = e.items[i].status == "0" ? "停用" : "在用";
                    }
                    if(isAvailable(e.items[i].daySpan)){
                        e.items[i].sfkt = e.items[i].daySpan == "0" ? "不跨天" : "跨天";
                    }
                }
            },
            beforeRequest: function (e) {
                //查询之前添加参数，避免点击清除按钮后条件失效
                IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
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
        $(".k-picker-wrap").on("click", function(e) {
            $($("#startTime_dateview").closest('.k-animation-container')[0]).remove();
            $($("#endTime_dateview").closest('.k-animation-container')[0]).remove();
            $($("#restStartTime_dateview").closest('.k-animation-container')[0]).remove();
            $($("#restEndTime_dateview").closest('.k-animation-container')[0]).remove();
            $($("#clockStartTime_dateview").closest('.k-animation-container')[0]).remove();
            $($("#clockEndTime_dateview").closest('.k-animation-container')[0]).remove();
        });
	    // 录入
	    $("#ADD").on("click", function(e) {
	        submitType = "insert";
	        shEditWindow.setOptions({
	            "title" : "录入"
	        });
            //新增时班组不可变更
            IPLAT.EFSelect.enable( $("#deptNum"), true );
            IPLAT.EFSelect.value($("#daySpan"), "0");
            IPLAT.EFSelect.value($("#status"), "1");
	        // 打开弹窗
	        shEditWindow.open().center();
	    });
	    // 编辑
	    $("#EDIT").on("click",function(e) {
            submitType = "update";
            shEditWindow.setOptions({
                "title" : "编辑"
            });
            var rows = resultGrid.getCheckedRows();
            if (rows.length == 1) {
            	//编辑时班组不可变更
            	IPLAT.EFSelect.enable( $("#deptNum"), false );
                // 打开弹窗
                shEditWindow.open().center();
                // 为表单赋值
                var value = rows[0];
                IPLAT.EFSelect.value($("#deptNum"), value["deptNum"]);
                IPLAT.EFInput.value($("#scheduleName"), value["scheduleName"]);
                $("#startTime").data("kendoTimePicker").value(value["startTime"]);
                $("#endTime").data("kendoTimePicker").value(value["endTime"]);
                $("#restStartTime").data("kendoTimePicker").value(value["restStartTime"]);
                $("#restEndTime").data("kendoTimePicker").value(value["restEndTime"]);
                $("#clockStartTime").data("kendoTimePicker").value(value["clockStartTime"]);
                $("#clockEndTime").data("kendoTimePicker").value(value["clockEndTime"]);
                IPLAT.EFSelect.value($("#daySpan"), value["daySpan"]);
                IPLAT.EFSelect.value($("#status"), value["status"]);
            } else {
            	NotificationUtil('请选择一条记录', "warning");
            }
        });
	    // 提交
	    $("#submit").on("click", function(e) {
	        if (!editValidator.validate()) {
	            // 校验不通过
	            return;
	        }
	        var eiInfo = new EiInfo();
	        // 获取编辑的值
	        var value = {
                deptNum : IPLAT.EFSelect.value($("#deptNum")),
                deptName : IPLAT.EFSelect.text($("#deptNum")),
                scheduleName : IPLAT.EFInput.value($("#scheduleName")),
                startTime : $("#startTime").data("kendoTimePicker")._oldText,
                endTime : $("#endTime").data("kendoTimePicker")._oldText,
                restStartTime : $("#restStartTime").data("kendoTimePicker")._oldText,
                restEndTime : $("#restEndTime").data("kendoTimePicker")._oldText,
                clockStartTime : $("#clockStartTime").data("kendoTimePicker")._oldText,
                clockEndTime : $("#clockEndTime").data("kendoTimePicker")._oldText,
                daySpan : IPLAT.EFSelect.value($("#daySpan")),
                status : IPLAT.EFSelect.value($("#status"))
	        };
	        var selectRow = resultGrid.getCheckedRows()[0];
	        if (!selectRow) {
	            selectRow = {};
	        }
	        // 复制数据
	        $.extend(selectRow, value);
	        // 提交数据
	        var rows = [];
	        rows[0] = selectRow;
	        eiInfo.set("submit", rows);
	        EiCommunicator.send("HRPBSJPZ01", submitType, eiInfo, {
	            onSuccess : function(ei) {
	                if (ei.status <= 0) {
	                    NotificationUtil(ei.getMsg(), "warning");
	                } else {
	                    NotificationUtil(ei.getMsg(), "success");
	                    // 刷新grid
	                    refreshResultGrid();
	                }
	            }
	        });
	        // 关闭弹窗
	        $("#cancel").click();
	    });
        //启用 停用
        $("#EFFECT").on("click", function(e) {
            var rows = resultGrid.getCheckedRows();
            if(rows.length >= 1){
                IPLAT.confirm({
                    message: '<b>将会变更所选记录的启用状态！</br><font color="red">是否确定？</font></b>',
                    okFn: function (e) {
                        //变更状态
                        for (var i = 0; i < rows.length; i++) {
                            if(rows[i].status == "1"){
                                rows[i].status = "0";
                                rows[i].zt = "停用";
                            }else if(rows[i].status == "0"){
                                rows[i].status = "1";
                                rows[i].zt = "在用";
                            }
                        }
                        //提交数据
                        var eiInfo = new EiInfo();
                        eiInfo.set("submit", rows);
                        EiCommunicator.send("HRPBSJPZ01", "update", eiInfo, {
                            onSuccess : function(ei) {
                                if(ei.status <= 0) {
                                    NotificationUtil(ei.getMsg(), "warning");
                                }else {
                                    NotificationUtil(ei.getMsg(), "success");
                                    //刷新grid
                                    refreshResultGrid();
                                }
                            }
                        });
                    },
                    title: '提醒'
                });
            }else{
                NotificationUtil("请选择一条记录", "warning");
            }
        });

	    // 取消
	    $("#cancel").on("click", function(e) {
	        window['shEditWindow'].close();
	    });
	    // 查询
	    $("#QUERY").on("click", function(e) {
	        refreshResultGrid();
	    });
	    // 页面加载时查询一次
	    refreshResultGrid();
	});
	$(function() {
        //登录用户
        IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
        //隐藏控件
        $("#inqu_status-0-userId").closest(".col-md-4").attr("style","display:none;");
	    // 页面加载时查询一次
	    refreshResultGrid();
	    // 启用校验
	    editValidator = IPLAT.Validator({
	        id : "edit"
	    });
	});

    //校验时间
    function validataTime(){
        var startTime = $("#startTime").data("kendoDateTimePicker")._oldText;
        var endTime = $("#endTime").data("kendoDateTimePicker")._oldText;
        var restStartTime = $("#restStartTime").data("kendoDateTimePicker")._oldText;
        var restEndTime = $("#restEndTime").data("kendoDateTimePicker")._oldText;
        var clockStartTime = $("#clockStartTime").data("kendoDateTimePicker")._oldText;
        var clockEndTime = $("#clockEndTime").data("kendoDateTimePicker")._oldText;
        //判断休息开始时间和休息结束时间
        if(restStartTime && restEndTime){
            //是否跨天
            var daySpan = IPLAT.EFSelect.value($("#daySpan"))
            if(daySpan == "1"){
                var startDateTime = getTody() + " " + startTime;
                var endDateTime = getTomorrow() + " " + endTime;
                var restStartDateTime = getTody() + " " + startTime;
                var restEndDateTime = getTody() + " " + endTime;
                compareDateTime();
            }
        }
    }
</script>