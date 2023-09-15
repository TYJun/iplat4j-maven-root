<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>资产盘点</title>
<EF:EFPage title="资产盘点">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-inventoryNo" colWidth="3" cname="盘点单号"/>
            <EF:EFInput ename="inqu_status-0-inventoryDeptName" colWidth="3" cname="盘点科室"/>
            <EF:EFInput ename="inqu_status-0-inventorySpotName" colWidth="3" cname="盘点地点"/>
			<EF:EFInput ename="inqu_status-0-remark" colWidth="3" cname="备注"/>
			<EF:EFDateSpan startName="inqu_status-0-singleSystenDateS" startCname="制单日期起"
			endName="inqu_status-0-singleSystenDateE" endCname="制单日期止" ratio="3:3"
						   startRatio="4:8" endRatio="4:8" readonly="true"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
		</div>
    </EF:EFRegion>
	<EF:EFTab id="FaDaTab">
		<div title="待完结盘点单">
			<EF:EFGrid blockId="resultA" autoDraw="no" autoBind="true" readonly="true" rowNo="true" checkMode="multiple,row" queryMethod="unCheckedQuery" height="460px">
				<EF:EFColumn ename="faInventoryId" cname="faInventoryId"  align="center" hidden="true"/>
				<EF:EFColumn ename="inventoryNo" cname="盘点单号" align="center" displayType="url" enable="false"/>
				<EF:EFColumn ename="inventoryDeptName" cname="盘点科室"  align="center"/>
				<EF:EFColumn ename="inventorySpotName" cname="盘点地点" align="center"/>
				<EF:EFColumn ename="singleSystenDate" cname="制单日期"  align="center"/>
				<EF:EFColumn ename="singleSystemName" cname="制单人"  align="center"/>
				<EF:EFColumn ename="beforeInvenNum" cname="资产数量"  align="center"/>
				<EF:EFColumn ename="remark" cname="备注" align="center"/>
				<EF:EFColumn ename="statusCode" cname="盘点状态" align="center"/>
			</EF:EFGrid>
		</div>
		<div title="已完结盘点单">
			<EF:EFGrid blockId="resultB" autoDraw="no" autoBind="true" readonly="true" rowNo="true" checkMode="multiple,row" queryMethod="checkedQuery" height="460px">
				<EF:EFColumn ename="faInventoryId" cname="faInventoryId"  align="center" hidden="true"/>
				<EF:EFColumn ename="inventoryNo" cname="盘点单号"  align="center" displayType="url" enable="false"/>
				<EF:EFColumn ename="inventoryDeptName" cname="盘点科室"  align="center"/>
				<EF:EFColumn ename="inventorySpotName" cname="盘点地点" align="center"/>
				<EF:EFColumn ename="singleSystenDate" cname="制单日期"  align="center"/>
				<EF:EFColumn ename="singleSystemName" cname="制单人"  align="center"/>
				<EF:EFColumn ename="checkDate" cname="确认日期"  align="center"/>
				<EF:EFColumn ename="checkMenName" cname="确认人"  align="center"/>
				<EF:EFColumn ename="beforeInvenNum" cname="盘点前数量"  align="center"/>
				<EF:EFColumn ename="afterInvenNum" cname="盘点后数量"  align="center"/>
				<EF:EFColumn ename="remark" cname="备注" align="center"/>
			</EF:EFGrid>
		</div>
	</EF:EFTab>
    <EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="资产盘点单" modal="true" />
</EF:EFPage>