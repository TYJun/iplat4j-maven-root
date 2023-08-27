<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 订餐时间配置 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
    <EF:EFInput ename="inqu_status-0-userId" disabled="true" style="display:none;"/>
        <div class="row">
            <EF:EFSelect ename="inqu_status-0-canteenNum" cname="食堂名称" 
                optionLabel="请选择" colWidth="4" ratio="3:8">
                <EF:EFOptions blockId="canteenData" textField="typeName" valueField="typeCode" />
            </EF:EFSelect>
            <EF:EFSelect ename="inqu_status-0-mealNum" cname="餐次" 
             optionLabel="请选择" colWidth="4" ratio="3:8">
                <EF:EFOptions blockId="mealNum" textField="typeName" valueField="typeCode" />
            </EF:EFSelect>
        </div>
    </EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="false" checkMode="row">
		    <!-- 隐藏列 -->
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="canteenNum" cname="canteenNum" width="100" hidden="true" />
			<EF:EFColumn ename="mealNum" cname="餐次编码" width="100" hidden="true" />
			<!-- 展示列 -->
			<EF:EFColumn ename="sendTime" cname="送餐时间" width="150" align="center" readonly="true"/>
			<EF:EFColumn ename="advanceTime" cname="提前时间  " width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="todayOrderTime" cname="今日点餐时间 " width="150" align="center" />
			<EF:EFColumn ename="tomorrowOrderTime" cname="明日点餐时间 " width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="mealName" cname="餐次名称" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="canteenName" cname="食堂名称" width="100" align="center" readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<!-- 弹窗 -->
    <EF:EFWindow id="shEdit" width="550px" height="365px" modal="true" url="" title="录入">
        <EF:EFRegion id="edit" title="" showClear="true">
            <div class="row" >
                <EF:EFSelect ename="canteenNum" cname="食堂名称" optionLabel="请选择" colWidth="10" required="true">
                    <EF:EFOptions blockId="canteenData" textField="typeName" valueField="typeCode" />
                </EF:EFSelect>
            </div>
            <div class="row" >
                <EF:EFSelect ename="mealNum" cname="餐次名称" optionLabel="请选择" colWidth="10" required="true">
                    <EF:EFOptions blockId="mealNum" textField="typeName" valueField="typeCode" />
                </EF:EFSelect>
            </div>
            <div class="row">
                <EF:EFInput ename="sendTime" cname="送餐时间" colWidth="10" required="true" maxLength="50" 
                data-regex="/^(20|21|22|23|[0-1]\d):[0-5]\d$/" placeholder="例23:05,07:30"
                            data-errorprompt="请输入正确的时间格式，例23:05,07:30"/>
                <%-- <EF:EFDatePicker ename="sendTime" cname="送餐时间" colWidth="10" required="true" 
	                  format="HH:mm" interval="15" role="time"/> --%>
            </div>
            <div class="row">
                <EF:EFInput ename="advanceTime" cname="取消订单提前时间"
                 data-rules="positive_integer" placeholder="请输入正整数(单位分钟)" colWidth="10" required="true" maxLength="50" 
                 data-errorprompt="请输入正整数(单位分钟)"/>
            </div>
            <div class="row">
                <EF:EFInput ename="todayOrderTime" cname="点今日餐截止时间" colWidth="10" required="true" maxLength="50" 
                data-regex="/^(20|21|22|23|[0-1]\d):[0-5]\d$/" placeholder="例23:05,07:30" data-errorprompt="请输入正确的时间格式，例23:05,07:30"/>
            </div>
            <div class="row">
                <EF:EFInput ename="tomorrowOrderTime" cname="点明日餐截止时间" colWidth="10" required="true" maxLength="50" 
                data-regex="/^(20|21|22|23|[0-1]\d):[0-5]\d$/" placeholder="例23:05,07:30" data-errorprompt="请输入正确的时间格式，例23:05,07:30"/>
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
	    // 录入
	    $("#ADD").on("click", function(e) {
	        submitType = "insert";
	        shEditWindow.setOptions({
	            "title" : "录入"
	        });
	        //录入时食堂和餐次可变更
            IPLAT.EFSelect.enable( $("#mealNum"), true )
            IPLAT.EFSelect.enable( $("#canteenNum"), true )
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
            	//编辑时食堂和餐次不可变更
            	IPLAT.EFSelect.enable( $("#mealNum"), false )
            	IPLAT.EFSelect.enable( $("#canteenNum"), false )
                // 打开弹窗
                shEditWindow.open().center();
                // 为表单赋值
                var value = rows[0];
                IPLAT.EFSelect.value($("#canteenNum"), value["canteenNum"]);
                IPLAT.EFSelect.value($("#mealNum"), value["mealNum"]);
                IPLAT.EFInput.value($("#sendTime"), value["sendTime"]);
                IPLAT.EFInput.value($("#advanceTime"), value["advanceTime"]);
                IPLAT.EFInput.value($("#todayOrderTime"), value["todayOrderTime"]);
                IPLAT.EFInput.value($("#tomorrowOrderTime"), value["tomorrowOrderTime"]);
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
        		canteenNum : IPLAT.EFSelect.value($("#canteenNum")),
        		canteenName : IPLAT.EFSelect.text($("#canteenNum")),
        		mealNum : IPLAT.EFSelect.value($("#mealNum")),
        		mealName : IPLAT.EFSelect.text($("#mealNum")),
        		sendTime : IPLAT.EFInput.value($("#sendTime")),
        		advanceTime : IPLAT.EFInput.value($("#advanceTime")),
        		todayOrderTime : IPLAT.EFInput.value($("#todayOrderTime")),
        		tomorrowOrderTime : IPLAT.EFInput.value($("#tomorrowOrderTime"))
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
	        EiCommunicator.send("SSBMDCSJ01", submitType, eiInfo, {
	            onSuccess : function(ei) {
	                if (ei.status == 0) {
	                    NotificationUtil(ei.getMsg(), "error");
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
</script>