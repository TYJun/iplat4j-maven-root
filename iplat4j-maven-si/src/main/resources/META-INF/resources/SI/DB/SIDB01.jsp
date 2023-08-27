<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>库存调拨</title>--%>
<title>调拨记录</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-inWareHouseNo" cname="调入仓库"
				resultId="result" textField="wareHouseName"
				valueField="wareHouseNo" serviceName="SIWH01"
				methodName="queryWareHouse" optionLabel="请选择" >
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-outWareHouseNo" cname="调出仓库"
				resultId="result" textField="wareHouseName"
				valueField="wareHouseNo" serviceName="SIWH01"
				methodName="queryWareHouse" optionLabel="请选择" >
			</EF:EFSelect>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="制单日期起"
				role="date" format="yyyy-MM-dd"
				parseFormats="['yyyy-MM-dd']" bindId="beginTime" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="制单日期止 "
				role="date" format="yyyy-MM-dd"
				parseFormats="['yyyy-MM-dd']" bindId="endTime" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="库存调拨信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" 
			autoBind="true" readonly="true" >
			<EF:EFColumn ename="transBillNo" cname="调拨单号" width="100" displayType="url" align="center"/>
			<EF:EFColumn ename="inWareHouseNo" cname="调入仓库" width="150" hidden="true"/>
			<EF:EFColumn ename="inWareHouseName" cname="调入仓库" width="150" align="center"/>
			<EF:EFColumn ename="outWareHouseNo" cname="调出仓库" width="150" hidden="true"/>
			<EF:EFColumn ename="outWareHouseName" cname="调出仓库" width="150" align="center"/>
			<EF:EFColumn ename="billMakeTime" cname="制单日期" width="100" align="center"/>
			<EF:EFColumn ename="billMaker" cname="制单人员" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

<EF:EFWindow id="popData" title="调拨信息" url=" " lazyload="true" width="80%" height="80%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/SI/common/si-initTime.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>
