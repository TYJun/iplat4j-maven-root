<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>需求计划汇总</title>

<EF:EFPage title="需求计划汇总信息">
	<EF:EFRegion id="inqu" title="汇总信息">
		<div class="row">
			<EF:EFTreeInput ename="inqu_status-0-matTypeId" cname="物资分类" required="true" save="false"
							serviceName="MPHZ0101" methodName="queryMatType" textField="text"
							valueField="label" hasChildren="leaf" popupTitile="物资分类" clear="true" placeholder="请选择">
			</EF:EFTreeInput>
			<EF:EFInput ename="inqu_status-0-matTypeNum" cname="物资分类" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-collectDate" cname="汇总日期" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-recCreatorName" cname="汇总人" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-recCreator" cname="汇总人" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-id" cname="需求汇总ID" type="hidden"/>
		</div>
	</EF:EFRegion>

	<EF:EFTab id="tab-tab_grid" active="0">
		<%--<div title="需求计划明细">
			<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row" readonly="true" >
				<EF:EFColumn ename="id" cname="主键" hidden="true" />
				<EF:EFColumn ename="planNo" cname="需求计划单号" align="center" />
				<EF:EFColumn ename="matNum" cname="物资编码" align="center" />
				<EF:EFColumn ename="matName" cname="物资名称" align="center" />
				<EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true" />
				<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" />
				<EF:EFColumn ename="matSpec" cname="物资规格" align="center" />
				<EF:EFColumn ename="matModel" cname="物资型号" align="center" />
				<EF:EFColumn ename="unit" cname="计量单位" hidden="true" />
				<EF:EFColumn ename="unitName" cname="计量单位" align="center" />
				<EF:EFColumn ename="price" cname="参考单价" align="center" />
				<EF:EFColumn ename="num" cname="需求数量" align="center"/>
			</EF:EFGrid>
		</div>--%>
		<div title="汇总明细">
			<EF:EFGrid blockId="detail" autoDraw="no" autoBind="true" checkMode="row" readonly="true">
				<EF:EFColumn ename="matNum" cname="物资编码" align="center" />
				<EF:EFColumn ename="matName" cname="物资名称" align="center" />
				<EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true" />
				<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" />
				<EF:EFColumn ename="matSpec" cname="物资规格" align="center" />
				<EF:EFColumn ename="matModel" cname="物资型号" align="center" />
				<EF:EFColumn ename="unit" cname="计量单位" hidden="true" />
				<EF:EFColumn ename="unitName" cname="计量单位" align="center" />
				<EF:EFColumn ename="price" cname="参考单价" align="center" />
				<EF:EFColumn ename="num" cname="汇总数量" align="center"/>
			</EF:EFGrid>
		</div>
	</EF:EFTab>
</EF:EFPage>
