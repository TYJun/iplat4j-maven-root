<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 病员POS订单管理 -->
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
    
    <EF:EFTab id="info" showClose="false" active="0">
       <div title="待处理">
        <EF:EFRegion id="result" title="记录集">
            <EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="true" checkMode="multiple, cell">
                <!-- 隐藏列 -->
                <EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
                <EF:EFColumn ename="isDelete" cname="isdelete" width="100" hidden="true" />
                <EF:EFColumn ename="mealNum" cname="餐次编码" width="100" align="center" readonly="true" hidden="true" />
                <!-- 展示列 -->
                <EF:EFColumn ename="billNo" cname="订单号" width="150" align="center" readonly="true"/>
                <EF:EFColumn ename="userName" cname="用户名" width="150" align="center" readonly="true" />
                <EF:EFColumn ename="openId" cname="住院号" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="address" cname="订餐地点" width="100" align="center" readonly="true" hidden="true" />
                <EF:EFColumn ename="recCreateTime" cname="下单时间" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="canteenName" cname="食堂名称" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="needDate" cname="用餐时间" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="mealName" cname="餐次" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="menuNumber" cname="订餐数量" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="billTotalPrice" cname="订单总价" format="{0:n}" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="payType" cname="付款方式" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="statusName" cname="订单状态" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="cz" cname="操作" displayType="url" width="100" align="center" readonly="true"/>
            </EF:EFGrid>
        </EF:EFRegion>
       </div>
       <div title="已处理">
         <EF:EFRegion id="result2" title="记录集">
            <EF:EFGrid blockId="result2" serviceName="PSBMPOS01" queryMethod="query2" autoDraw="no" rowNo="true" readonly="true" >
                <!-- 隐藏列 -->
                <EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
                <EF:EFColumn ename="isDelete" cname="isdelete" width="100" hidden="true" />
                <EF:EFColumn ename="mealNum" cname="餐次编码" width="100" align="center" readonly="true" hidden="true"/>
                <!-- 展示列 -->
                <EF:EFColumn ename="billNo" cname="订单号" width="150" align="center" readonly="true"/>
                <EF:EFColumn ename="userName" cname="用户名" width="150" align="center" readonly="true" />
                <EF:EFColumn ename="openId" cname="住院号" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="address" cname="订餐地点" width="100" align="center" readonly="true" hidden="true"/>
                <EF:EFColumn ename="recCreateTime" cname="下单时间" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="canteenName" cname="食堂名称" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="needDate" cname="用餐时间" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="mealName" cname="餐次" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="menuNumber" cname="订餐数量" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="billTotalPrice" cname="订单总价" format="{0:n}" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="payType" cname="付款方式" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="statusName" cname="订单状态" width="100" align="center" readonly="true"/>
            </EF:EFGrid>
         </EF:EFRegion>
       </div>
    </EF:EFTab>
    
</EF:EFPage>
<script type="text/javascript">
//提交类型
var submitType = "insert";
IPLATUI.EFGrid = {
    "result": {
        dataBinding: function (e) {
            //grid数据绑定时对属性进行处理
            for (var i = 0, length = e.items.length; i < length; i++) {
                if(isAvailable(e.items[i].statusCode)){
                    e.items[i].cz = formatOrderCz(e.items[i]);
                    if(e.items[i].statusCode == "02"){
                        e.items[i].statusName = '待配送餐品';
                    }
                    if(e.items[i].payType == "0000"){
                        e.items[i].payType = '现金';
                    };
                    if(e.items[i].payType == "03"){
                        e.items[i].payType = '一卡通';
                    };
                    if(e.items[i].payType == "0301"){
                        e.items[i].payType = '一卡通';
                    };
                    if(e.items[i].payType == "0101"){
                        e.items[i].payType = '支付宝支付';
                    };
                    if(e.items[i].payType == "0201"){
                        e.items[i].payType = '微信支付';
                    }
                    if(e.items[i].payType == "0501"){
                        e.items[i].payType = '农行掌银';
                    }
                }
            }
        }
    },
    "result2": {
        dataBinding: function (e) {
            //grid数据绑定时对属性进行处理
            for (var i = 0, length = e.items.length; i < length; i++) {
                if(isAvailable(e.items[i].payType)){
                    if(e.items[i].payType == "0000"){
                        e.items[i].payType = '现金';
                    };
                    if(e.items[i].payType == "03"){
                        e.items[i].payType = '一卡通';
                    };
                    if(e.items[i].payType == "0301"){
                        e.items[i].payType = '一卡通';
                    };
                    if(e.items[i].payType == "0101"){
                        e.items[i].payType = '支付宝支付';
                    };
                    if(e.items[i].payType == "0201"){
                        e.items[i].payType = '微信支付';
                    }
                    if(e.items[i].payType == "0501"){
                        e.items[i].payType = '农行掌银';
                    }
                }
            }
        }
    }
}

function formatOrderCz(row){
    var htm = "";
    var id = row.id;
    var code = row.statusCode;
    if(code=="02"){
    	//POS订单管理功能与病员订单管理功能相同
        return "<a style='color:blue;' href=\"#\" onclick=\"operationPatientOrder('"+id+"','PSBMDDGL01?confirmSend','送餐');\">开始送餐</a>";
    }
}
//订单操作事件
function operationPatientOrder(id,url,opt) {
    if(id!=null||id!=""){
    	 IPLAT.confirm({
             message: '<b>你确定进行'+opt+' 操作吗? </br><font color="red">是否确定？</font></b>',
             okFn: function (e) {
                 var split = url.split("?");
                 //提交数据
                 var eiInfo = new EiInfo();
                 eiInfo.set("id", id);
                 EiCommunicator.send(split[0], split[1], eiInfo, {
                     onSuccess : function(ei) {
                         if(ei.status < 0) {
                             NotificationUtil(ei.getMsg(), "error");
                         }else {
                             NotificationUtil(ei.getMsg(), "success");
                             //刷新grid
                             refreshResultGrid();
                             result2Grid.dataSource.page(1);
                         }
                     }
                 });
             }
         });
    }
}

function setDate(){
    $("#inqu_status-0-beginDate").data("kendoDatePicker").value(new Date());
    $("#inqu_status-0-endDate").data("kendoDatePicker").value(new Date());
}
/*绑定日期控件时间*/
IPLATUI.EFDatePicker = {
    beginDate:{
        change: function(e){
            checkDate();
        }
    }   
}
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

/* 按钮绑定事件 */
$(function() {
	//登录用户
    IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
    //隐藏控件
    $("#inqu_status-0-userId").closest(".col-md-4").attr("style","display:none;");
    //查询
    $("#QUERY").on("click", function(e) {
        if(checkDate()){
            refreshResultGrid();
            result2Grid.dataSource.page(1);
        }
    });
    //初始化日期控件
    setDate();
    //页面加载时查询一次
    $("#QUERY").click();
});
    
</script>