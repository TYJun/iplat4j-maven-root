<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 病员POS订单综合查询 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <EF:EFInput ename="inqu_status-0-userId" disabled="true" style="display:none;"/>
        <div class="row"> 
            <EF:EFSelect ename="inqu_status-0-canteenNum" cname="食堂名称"   
                optionLabel="请选择" colWidth="3" ratio="3:8">
                <EF:EFOptions blockId="canteenData" textField="typeName" valueField="typeCode" />
            </EF:EFSelect>
            <EF:EFInput ename="inqu_status-0-billNo" colWidth="3" ratio="3:8" cname="订单号" />
            <EF:EFDatePicker format="yyyy-MM-dd" role="date" colWidth="3" ratio="3:8"
                ename="inqu_status-0-beginDate" cname="用餐开始日期">
            </EF:EFDatePicker>
            <EF:EFDatePicker format="yyyy-MM-dd" role="date" colWidth="3" ratio="3:8"
                ename="inqu_status-0-endDate" cname="用餐结束日期">
            </EF:EFDatePicker>
        </div>
        <div class="row"> 
            <EF:EFSelect ename="inqu_status-0-mealNum" cname="订餐餐次"  
                optionLabel="请选择" colWidth="3" ratio="3:8">
                <EF:EFOptions blockId="mealNum" textField="typeName" valueField="typeCode" />
            </EF:EFSelect>
<%--             <EF:EFSelect ename="inqu_status-0-spotName" cname="所属楼栋" 
                optionLabel="请选择" colWidth="3" ratio="3:8">
                <EF:EFOptions blockId="buildingData" textField="typeName" valueField="typeName" />
            </EF:EFSelect> --%>
            <EF:EFInput ename="inqu_status-0-patientCode" colWidth="3" ratio="3:8" cname="住院号" />
            <EF:EFInput ename="inqu_status-0-patientName" colWidth="3" ratio="3:8" cname="病人姓名" />
        </div>
    </EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="false">
            <!-- 隐藏列 -->
            <EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
            <EF:EFColumn ename="isDelete" cname="isdelete" width="100" hidden="true" />
            <EF:EFColumn ename="mealNum" cname="餐次编码" width="100" align="center" readonly="true" hidden="true" />
            <EF:EFColumn ename="payType" cname="付款方式" width="100" hidden="true" />
            <!-- 展示列 -->
            <EF:EFColumn ename="billNo" cname="订单号" width="150" align="center" readonly="true"/>
            <EF:EFColumn ename="userName" cname="用户名" width="150" align="center" readonly="true" />
            <EF:EFColumn ename="openId" cname="住院号" width="100" align="center" readonly="true"/>
            <EF:EFColumn ename="address" cname="订餐地点" width="100" align="center" readonly="true" hidden="true" />
            <EF:EFColumn ename="recCreateTime" cname="下单时间" width="100" align="center" readonly="true"/>
            <EF:EFColumn ename="canteenName" cname="食堂名称" width="100" align="center" readonly="true"/>
            <EF:EFColumn ename="reqSendTime" cname="用餐时间" width="100" align="center" readonly="true"/>
            <EF:EFColumn ename="mealName" cname="餐次" width="100" align="center" readonly="true"/>
            <EF:EFColumn ename="menuNumber" cname="订餐数量" width="100" align="center" readonly="true"/>
            <EF:EFColumn ename="billTotalPrice" cname="订单总价" format="{0:n}" width="100" align="center" readonly="true"/>
            <EF:EFColumn ename="payTypeName" cname="付款方式" width="100" align="center" readonly="true"/>
            <EF:EFColumn ename="statusName" cname="订单状态" width="100" align="center" readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	
</EF:EFPage>
<script type="text/javascript">
	//提交类型
	var submitType = "insert";
	IPLATUI.EFGrid = {
		"result": {
	        dataBinding: function (e) {
	            //grid数据绑定时对属性进行处理
	            for (var i = 0, length = e.items.length; i < length; i++) {
	                if(isAvailable(e.items[i].payType)){
	                    if(e.items[i].payType == "0000"){
	                        e.items[i].payTypeName = '现金';
	                    };
	                    if(e.items[i].payType == "03"){
	                        e.items[i].payTypeName = '一卡通';
	                    };
	                    if(e.items[i].payType == "0301"){
	                        e.items[i].payTypeName = '一卡通';
	                    };
	                    if(e.items[i].payType == "0101"){
	                        e.items[i].payTypeName = '支付宝支付';
	                    };
	                    if(e.items[i].payType == "0201"){
	                        e.items[i].payTypeName = '微信支付';
	                    }
	                    if(e.items[i].payType == "0501"){
	                        e.items[i].payTypeName = '农行掌银';
	                    }
	                }
	            }
	        }
		}
	}
	$(function() {
		//撤单，接口与病员订单综合查询页面相同
        $("#DELBTN").on("click", function(e) {
            var rows = resultGrid.getCheckedRows();
            if(rows && rows.length == 1){
                IPLAT.prompt({
                    message: '<b>将会撤销所选订单！</br><font color="red">请输入撤单原因</font></b>',
                    okFn: function (e) {
                        if(e){
                            //提交数据
                            var eiInfo = new EiInfo();
                            eiInfo.set("pBillNo",rows[0].billNo);
                            eiInfo.set("rejectReason", e);
                            EiCommunicator.send("PSPCDDGL01", "cancelOrder", eiInfo, {
                                onSuccess : function(ei) {
                                    if(ei.status <= 0) {
                                        NotificationUtil(ei.getMsg(), "error");
                                    }else {
                                        NotificationUtil(ei.getMsg(), "success");
                                        //刷新grid
                                        refreshResultGrid();
                                    }
                                }
                            });
                        }else{
                            NotificationUtil("请输入撤销原因！", "warning");
                        }
                    }
                });
            }else{
                NotificationUtil("请选择一条需要撤销的订单！", "warning");
            }
            
        });
    });
	
	/**校验起止日期*/
	function checkDate(){
	    var needDateStart = kendo.toString($("#inqu_status-0-beginDate").data("kendoDatePicker").value(),"yyyy-MM-dd");
	    var needDateEnd = kendo.toString($("#inqu_status-0-endDate").data("kendoDatePicker").value(),"yyyy-MM-dd");
	    if(needDateStart > needDateEnd) {
	        NotificationUtil("开始日期不能大于结束日期！", "error");
	        return false;
	    }
	    return true;
	}
	/*初始化日期控件*/
    function setDate(){
        $("#inqu_status-0-beginDate").data("kendoDatePicker").value(new Date());
        $("#inqu_status-0-endDate").data("kendoDatePicker").value(new Date());
    }
	
	/* 按钮绑定事件 */
	$(function() {
		//登录用户
        IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
        //隐藏控件
        $("#inqu_status-0-userId").closest(".col-md-4").attr("style","display:none;");
        
        $("#DELBTN").css("float","left");
		//查询
        $("#QUERY").on("click", function(e) {
        	if(checkDate()){
                refreshResultGrid();
            }
        });
      //初始化日期控件
        setDate();
        //页面加载时查询一次
        refreshResultGrid();
	});
	
</script>