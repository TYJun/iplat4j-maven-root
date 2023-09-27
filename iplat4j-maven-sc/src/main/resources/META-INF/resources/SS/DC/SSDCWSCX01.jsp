<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 职工外送订单订单综合查询 -->
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
                ename="inqu_status-0-endDate" cname="用餐截止日期">
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
            <EF:EFInput ename="inqu_status-0-userCode" colWidth="3" ratio="3:8" cname="工号" />
            <EF:EFInput ename="inqu_status-0-recCreateName" colWidth="3" ratio="3:8" cname="姓名" />
            <EF:EFInput ename="inqu_status-0-userTelNumber" colWidth="3" ratio="3:8" cname="电话" />
            <EF:EFInput ename="inqu_status-0-deptName" colWidth="3" ratio="3:8" cname="科室名称" />
            <EF:EFInput ename="inqu_status-0-reqSendTime" colWidth="3" ratio="3:8" cname="送餐时间" />
        </div>
    </EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="false">
            <!-- 隐藏列 -->
            <EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
            <EF:EFColumn ename="isDelete" cname="isdelete" width="100" hidden="true" />
            <EF:EFColumn ename="payType" cname="付款方式" width="100" hidden="true" />
            <!-- 展示列 -->
            <EF:EFColumn ename="billNo" cname="订单号" width="150" align="center" readonly="true"/>
            <EF:EFColumn ename="userName" cname="用户名" width="150" align="center" readonly="true" />
            <EF:EFColumn ename="userTelNumber" cname="联系电话" width="150" align="center" readonly="true" />
            <EF:EFColumn ename="deptName" cname="科室名称" width="100" align="center" readonly="true"/>
            <EF:EFColumn ename="room" cname="送餐地点" width="100" align="center" readonly="true"  />
            <EF:EFColumn ename="recCreateTime" cname="下单时间" width="100" align="center" readonly="true"/>
            <EF:EFColumn ename="needDate" cname="送餐时间" width="100" align="center" readonly="true"/>
<%--            <EF:EFColumn ename="reqSendTime" cname="送餐时间" width="100" align="center" readonly="true"/>--%>
            <EF:EFColumn ename="mealName" cname="餐次" width="100" align="center" readonly="true"/>
            <EF:EFColumn ename="canteenName" cname="食堂名称" width="100" align="center" readonly="true"/>
            <%--<EF:EFColumn ename="menuNumber" cname="订餐数量" width="100" align="center" readonly="true"/>--%>
            <EF:EFColumn ename="billTotalPrice" cname="订单总价" format="{0:n}" width="100" align="center" readonly="true"/>
            <EF:EFColumn ename="payTypeName" cname="付款方式" width="100" align="center" readonly="true"/>
            <EF:EFColumn ename="statusName" cname="订单状态" width="100" align="center" readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
    <!-- 弹窗 -->
    <EF:EFWindow id="shEdit" width="590px" height="629px" modal="true" url="" title="查看" style="display:none;">
        <EF:EFRegion id="edit" title="订单明细" showClear="true" >
            <div class="row" >
                <EF:EFInput ename="userName" cname="用户姓名" ratio="2:10" colWidth="12" readonly="true" required="true" maxLength="200" />
            </div>
            <div class="row">
                <EF:EFInput ename="userTelNumber" cname="用户电话" ratio="2:10" colWidth="12" readonly="true" required="true" maxLength="200" />
            </div>
            <div class="row" >
                <EF:EFInput ename="deptName" cname="科室名称" ratio="2:10" colWidth="12" readonly="true" required="true" maxLength="200" />
            </div>
            <div class="row" >
                <EF:EFInput ename="room" cname="订餐地点" ratio="2:10" colWidth="12" readonly="true" required="true" maxLength="200" />
            </div>
            <div class="row" >
                <EF:EFInput ename="mealName" cname="餐次名称" ratio="2:10" colWidth="12" readonly="true" required="true" maxLength="200" />
            </div>
            <div class="row" >
                <EF:EFInput ename="canteenName" cname="食堂名称" ratio="2:10" colWidth="12" readonly="true" required="true" maxLength="200" />
            </div>
            <div class="row" >
                <EF:EFInput ename="reqSendTime" cname="需求时间" ratio="2:10" colWidth="12" readonly="true" required="true" maxLength="200" />
            </div>
            <div class="row" >
                <EF:EFInput ename="billRemark" cname="订单备注" type="textarea" ratio="2:10" readonly="true" colWidth="12" maxLength="200" />
            </div>
        </EF:EFRegion>
        <!-- 菜品组成 -->
        <EF:EFRegion id="composition " title="菜品明细" showClear="true" style="height:280px;">
            <EF:EFGrid blockId="menus" autoDraw="no" rowNo="false" autoBind="false" serviceName="SSDCWSGL01" queryMethod="queryMenus">
                <!-- 展示列 -->
                <EF:EFColumn ename="menuName" cname="菜品名称" width="150" align="center" />
                <EF:EFColumn ename="menuPrice" cname="单价" width="80" align="center" />
                <EF:EFColumn ename="menuNumber" cname="数量" width="80" align="center" />
                <EF:EFColumn ename="menuTotalPrice" cname="总价" width="80" align="center" />
            </EF:EFGrid>
        </EF:EFRegion>
        <EF:EFButton ename="cancel" cname="取消"  class="k-button window-btn" style="float:right;"/>
    </EF:EFWindow>
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
                        if(e.items[i].payType == "0401"){
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
                        if(e.items[i].mealNum == "001"){
                            e.items[i].mealName = '早餐';
                        }
                        if(e.items[i].mealNum == "002"){
                            e.items[i].mealName = '午餐';
                        }
                        if(e.items[i].mealNum == "003"){
                            e.items[i].mealName = '晚餐';
                        }
                    }
                }
            },
            onRowDblClick: function (e) {
                rowDbClick(e);
            }
		}
	}
    //双击行
    function rowDbClick(e){
        //向弹窗中填值
        IPLAT.EFInput.value($("#userName"),e.model["userName"]);
        IPLAT.EFInput.value($("#userTelNumber"),e.model["userTelNumber"]);
        IPLAT.EFInput.value($("#deptName"),e.model["deptName"]);
        IPLAT.EFInput.value($("#room"),e.model["room"]);
        IPLAT.EFInput.value($("#mealName"),e.model["mealName"]);
        IPLAT.EFInput.value($("#canteenName"),e.model["canteenName"]);
        IPLAT.EFInput.value($("#reqSendTime"),e.model["reqSendTime"]);
        IPLAT.EFInput.value($("#billRemark"),e.model["billRemark"]);
        //打开弹窗
        shEditWindow.open().center();
        //加载menusGrid菜品组成数据
        var eiInfo = new EiInfo();
        eiInfo.set("billNo", e.model["billNo"]);
        EiCommunicator.send("SSDCWSGL01", "queryMenus", eiInfo, {
            onSuccess : function(ei) {
                //为menusGrid赋值
                menusGrid.setEiInfo(ei);
            }
        });
    }
    // 弹窗关闭事件
    IPLATUI.EFWindow = {
        "shEdit": {
            close: function (e) {
                //EFRegion的id-trash
                $("#edit-trash").click();
                //清空menusGrid菜品组成
                menusGrid.removeRows(menusGrid.getDataItems());
            }
        }
    }
	$(function() {
        //撤单
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
                            eiInfo.set("userCode", IPLAT.EFInput.value($("#inqu_status-0-userId")));
	                        EiCommunicator.send("SSACDDGL01", "cancelOrder", eiInfo, {
	                            onSuccess : function(ei) {
	                                if(ei.status == 0) {
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
		//$("#DELBTN").attr("class","window-btn");
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
        $("#QUERY").click();
	});
	
</script>