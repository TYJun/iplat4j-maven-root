<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>采购订单管理</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-deptNum" cname="科室编码" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-orderNo" cname="订单号"/>
			<EF:EFInput ename="inqu_status-0-supplierName" cname="供应商"/>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态">
				<EF:EFOption label="全部" value="" />
				<EF:EFCodeOption codeName="wilp.mp.purchase.OrderStatus"/>
			</EF:EFSelect>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="创建日期起" readonly="true" ></EF:EFDatePicker>
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="创建日期止" readonly="true" ></EF:EFDatePicker>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="订单列表">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true" readonly="true">
			<EF:EFColumn ename="id" cname="主键" align="center" hidden="true" />
			<EF:EFColumn ename="orderNo" cname="订单号"  align="center" displayType="url"/>
			<EF:EFColumn ename="orderNum" cname="订单总数量" align="center" />
			<EF:EFColumn ename="orderCost" cname="订单总价" align="center"/>
			<EF:EFColumn ename="contNo" cname="合同号"  align="center"/>
			<EF:EFColumn ename="contName" cname="合同名称"  align="center"/>
			<EF:EFColumn ename="statusName" cname="状态"  align="center"/>
			<EF:EFColumn ename="supplierName" cname="供应商"  align="center"/>
			<EF:EFColumn ename="recCreateTimeStr" cname="创建日期"  align="center"/>
			<EF:EFColumn ename="recCreatorName" cname="创建人"  align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="90%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>

