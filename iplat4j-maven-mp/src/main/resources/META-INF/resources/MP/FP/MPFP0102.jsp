<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>采购计划明细选择</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-orderNo" cname="订单号"/>
			<EF:EFInput ename="inqu_status-0-orderId" type = "hidden"/>
			<EF:EFInput ename="inqu_status-0-contId" type = "hidden"/>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="订单状态" readonly="true">
				<EF:EFCodeOption codeName="wilp.mp.purchase.OrderStatus"/>
			</EF:EFSelect>
			<div class="button-region" id="buttonDiv">
				<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
				<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="订单列表">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true" readonly="true" height="200">
			<EF:EFColumn ename="id" cname="主键" align="center" hidden="true" />
			<EF:EFColumn ename="orderNo" cname="订单号"  align="center"/>
			<EF:EFColumn ename="orderNum" cname="订单总数量" align="center" />
			<EF:EFColumn ename="orderCost" cname="订单总价" align="center"/>
			<EF:EFColumn ename="contNo" cname="合同号"  align="center"/>
			<EF:EFColumn ename="contName" cname="合同名称"  align="center"/>
			<EF:EFColumn ename="supplierName" cname="供应商"  align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>

	<EF:EFRegion id="detail" title="订单明细列表">
		<EF:EFGrid blockId="detail" autoDraw="no" checkMode="row" autoBind="true" readonly="true" queryMethod="queryDetail">
			<EF:EFColumn ename="orderId" cname="采购订单id" hidden="true" />
			<EF:EFColumn ename="contNo" cname="合同号" hidden="true"/>
			<EF:EFColumn ename="orderNo" cname="订单号" width="100"  align="center"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="80" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="80" align="center"/>
			<EF:EFColumn ename="matTypeId" cname="物资分类编码" width="80" hidden="true"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" width="80"  align="center"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="80" align="center"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="80"  align="center"/>
			<EF:EFColumn ename="unit" cname="计量单位" hidden="true"  align="center"/>
			<EF:EFColumn ename="unitName" cname="计量单位" width="70"  align="center"/>
			<EF:EFColumn ename="price" cname="含税单价(元)" width="80"  align="center"/>
			<EF:EFColumn ename="num" cname="订单数量" width="80"  align="center"/>
			<EF:EFColumn ename="orderCost" cname="订单总价" width="80"  align="center"/>
			<EF:EFColumn ename="pictureUri" cname="图片" hidden="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

