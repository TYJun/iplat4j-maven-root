<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>采购计划审批</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="采购计划">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" cname="主键" readonly="true" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-recCreateTimeStr" cname="创建日期" readonly="true" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-recCreator" cname="创建人" readonly="true" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-purchaseNo" cname="采购计划单号" readonly="true"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="采购计划明细列表">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true" readonly="true" rowNo="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="purchaseNo" cname="采购计划单号" width="100" hidden="true" />
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="matTypeId" cname="物资分类编码" width="100" align="center" enable="false" hidden="true"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="unit" cname="计量单位" hidden="true"/>
			<EF:EFColumn ename="unitName" cname="计量单位" align="center" enable="false"/>
			<EF:EFColumn ename="price" cname="单价" width="100" align="center" />
			<EF:EFColumn ename="num" cname="采购数量" width="100" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>