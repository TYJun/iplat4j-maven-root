<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="合同付款管理">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-deptNum" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-payNo" cname="付款单号"/>
			<EF:EFInput ename="inqu_status-0-contNo" cname="合同号"/>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="wilp.mp.cont.payStatus"/>
			</EF:EFSelect>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
		</div>

	</EF:EFRegion>
	<EF:EFRegion id="result" title="付款申请单">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
				   checkMode="single,row" readonly="true" rowNo="true">
			<EF:EFColumn ename="id" cname="id" hidden="true" />
			<EF:EFColumn ename="payNo" cname="付款号" align="center" displayType="url"/>
			<EF:EFColumn ename="statusName" cname="状态" align="center"/>
			<EF:EFColumn ename="contNo" cname="合同号" align="center"/>
			<EF:EFColumn ename="contName" cname="合同名称" align="center"/>
			<EF:EFColumn ename="payContent" cname="付款内容"  align="center"/>
			<EF:EFColumn ename="payWayName" cname="付款方式" align="center" />
			<EF:EFColumn ename="payCost" cname="付款金额" align="center" format="{0:0.00}"/>
			<EF:EFColumn ename="payDate" cname="付款日期" align="center" />
			<EF:EFColumn ename="supplierName" cname="开票单位" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url="" lazyload="true" width="90%" height="90%" title="付款信息"/>


