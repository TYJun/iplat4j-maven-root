<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>合同发票管理</title>

<EF:EFPage title="合同发票管理">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-deptNum" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-invoiceNo" cname="发票号"/>
			<EF:EFInput ename="inqu_status-0-contName" cname="合同名称"/>
			<EF:EFSelect ename="inqu_status-0-invoiceType" cname="发票类型" >
				<EF:EFOption label="全部" value="" />
				<EF:EFCodeOption codeName="wilp.mp.cont.invoiceType"/>
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-supplierName" cname="开票单位"/>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态" >
				<EF:EFOption label="全部" value="" />
				<EF:EFCodeOption codeName="wilp.mp.cont.invoiceStatus"/>
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="合同发票管理">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
				   checkMode="single,row" readonly="true" rowNo="true" >
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="invoiceNo" cname="发票号" align="center" displayType="url"/>
			<EF:EFColumn ename="invoiceTypeName" cname="发票类型" align="center"/>
			<EF:EFColumn ename="contNo" cname="合同号" align="center"/>
			<EF:EFColumn ename="contName" cname="合同名称" align="center"/>
			<EF:EFColumn ename="statusName" cname="状态" align="center"/>
			<EF:EFColumn ename="supplierName" cname="开票单位" align="center"/>
			<EF:EFColumn ename="invoiceCost" cname="发票含税金额(元)" align="center"/>
			<%--<EF:EFColumn ename="invoiceTaxCost" cname="发票税额" align="center" />--%>
			<EF:EFColumn ename="payDate" cname="发票开付日期"  align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url="" lazyload="true" width="90%" height="95%" title="发票信息"/>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>

