<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 菜品数量配置 -->
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
            <EF:EFSelect ename="inqu_status-0-operateCode" cname="食堂业务" 
             optionLabel="请选择" colWidth="4" ratio="3:8">
                <EF:EFOptions blockId="mealOperate" textField="typeName" valueField="typeCode" />
            </EF:EFSelect>
            <EF:EFDatePicker ename="inqu_status-0-mealDate" cname="所属日期"
                 format="yyyy-MM-dd" role="date">
            </EF:EFDatePicker>
			<EF:EFSelect cname="餐次：" ename="inqu_status-0-mealName"
						 colWidth="4" ratio="3:8">
				<EF:EFOption label="请选择" value="" />
				<EF:EFOption label="早餐" value="001" />
				<EF:EFOption label="午餐" value="002" />
				<EF:EFOption label="晚餐" value="003" />
			</EF:EFSelect>
        </div>
    </EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="false" checkMode="row">
		    <!-- 隐藏列 -->
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<!-- 展示列 -->
			<EF:EFColumn ename="menuName" cname="菜品名称" width="150" align="center" readonly="true"/>
			<EF:EFColumn ename="menuPrice" cname="原价  " format="{0:n}" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="priceAfterDiscount" cname="折后价 " format="{0:n}" width="150" align="center" />
			<EF:EFColumn ename="mealName" cname="餐次 " width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="canteenName" cname="食堂名称" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="operatename" cname="菜品所属" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="mealDate" cname="所属日期" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="num" cname="份量" width="100" align="center" data-rules="integer" maxLength="10" />
			<EF:EFColumn ename="surplus" cname="剩余份量" width="100" align="center" readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	
</EF:EFPage>
<script type="text/javascript">
	IPLATUI.EFGrid = {
        "result": {
            beforeRequest: function (e) {
                //查询之前添加参数，避免点击清除按钮后条件失效
                IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
            },
			loadComplete: function (grid) {
				//修改按钮
				$("#SAVE1").on("click", function (e) {
					// var id;
					// var num;
					// var surplus;
					// var rows = resultGrid.getCheckedRows();
					var grid = $("#ef_grid_result").data("kendoGrid");
					if(grid.getDataItems().length > 0){
						// id = rows[0].id;
						// num = rows[0].num;
						// surplus = rows[0].surplus;
						var data = grid.getCheckedRows();
					}else{
						NotificationUtil("没有勾选数据", "error");
						return;
					}
					var info = new EiInfo();
					// info.set("id", id);
					// info.set("num", num);
					// info.set("surplus", surplus);

					info.set("rows", data);

					EiCommunicator.send("SSBMCPSL01", "update", info, {
						onSuccess : function(ei) {
							// console.log(ei.getStatus())
							if(ei.getStatus() > '0'){
								// console.log(ei.getMsg())
								NotificationUtil(ei.getMsg(), "success");
							}else{
								// console.log(ei.getMsg())
								NotificationUtil(ei.getMsg(), "faild");
								return;
							}
							resultGrid.dataSource.page(1);
						}
					});
				});
			},
        }
    }

	function setDate(){
	    $("#inqu_status-0-mealDate").data("kendoDatePicker").value(new Date());
	}

	$(function() {
        //登录用户
        IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
        //隐藏控件
        $("#inqu_status-0-userId").closest(".col-md-4").attr("style","display:none;");
		//查询
	    $("#QUERY").on("click", function(e) {
        	refreshResultGrid();
	    });
		setDate();

        //页面加载时查询一次
        refreshResultGrid();
	});
</script>