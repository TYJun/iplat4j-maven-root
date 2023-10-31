<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>月度库存查询</title>--%>
<title>每月库存</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-month" cname="月份" format="yyyy-MM"></EF:EFDatePicker>
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称"
				resultId="result" textField="wareHouseName" valueField="wareHouseNo"
				serviceName="SIWH01" methodName="queryWareHouse" optionLabel="请选择">
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-matNum" cname="物资编码" />
			<EF:EFInput ename="inqu_status-0-matName" cname="物资名称" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="月度库存列表">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true" readonly="true"
			sort="single">
			<EF:EFColumn ename="month" cname="月份" width="100" align="center" locked="true" sort="false"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库" width="100" align="center" locked="true" sort="false"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center" locked="true" sort="true"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="100" align="center" locked="true" sort="true"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="unitName" cname="计量单位" width="80" align="center" sort="false"/>
			<EF:EFColumn ename="firstNum" cname="月初库存总量" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="firstAmount" cname="月初库存总价(元)" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="lastNum" cname="月末库存总量" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="lastAmount" cname="月末库存总价(元)" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="enterNum" cname="入库数量" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="enterAmount" cname="入库总金额(元)" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="transferEnterNum" cname="调拨入库数量" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="transferEnterAmount" cname="调拨入库总金额(元)" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="outNum" cname="出库数量" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="outAmount" cname="出库总金额(元)" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="transferOutNum" cname="调拨出库数量" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="transferOutAmount" cname="调拨出库总金额(元)" width="80" align="center" sort="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>