<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="资产拆分">
	<EF:EFRegion title="当前资产信息">
		<EF:EFInput ename="type" cname="操作类型" readOnly="true" type="hidden"/>
		<EF:EFInput ename="info-0-goodsNumMax" cname="资产编号" readOnly="true" type="hidden"/>
		<EF:EFInput ename="info-0-goodsNum" cname="资产编码" readOnly="true"/>
		<EF:EFInput ename="info-0-goodsName" cname="资产名称" readOnly="true"/>
		<EF:EFInput ename="info-0-goodsTypeName" cname="类组" readOnly="true"/>
		<EF:EFInput ename="info-0-buyCost" cname="资产原值(元)" readOnly="true"/>
		<EF:EFInput ename="info-0-totalDepreciation" cname="累计折旧值(元)" readOnly="true"/>
		<EF:EFInput ename="info-0-netAssetValue" cname="资产净值(元)" readOnly="true"/>
	</EF:EFRegion>
	<EF:EFRegion title="资产拆分记录">
		<EF:EFGrid blockId="resultSplitByValue" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
				   checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="goodsId" cname="goodsId" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="goodsNum" cname="资产编码" align="center" enable="false" width="200"/>
			<EF:EFColumn ename="goodsName" cname="资产名称" align="center" enable="false" width="200"/>
			<EF:EFColumn ename="amount" cname="数量" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="buyCost" cname="资产原值" align="center" enable="false" width="200"/>
			<EF:EFColumn ename="totalDepreciation" cname="累计折旧" enable="false" align="center" width="200"/>
			<EF:EFColumn ename="netAssetValue" cname="资产净值" enable="false" align="center" width="200"/>
			<EF:EFColumn ename="splitTime" cname="拆分时间" enable="false" align="center" width="200"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>