<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>生成采购计划</title>

<EF:EFPage title="生成采购计划">
	<EF:EFRegion id="inqu" title="采购计划" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-collectNo" cname="汇总单号" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-collectDate" cname="汇总日期" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-recCreatorName" cname="汇总人" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-id" cname="需求汇总ID" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-deptNum" cname="科室编码" type="hidden"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="采购计划明细">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" checkMode="row">
			<EF:EFColumn ename="matNum" cname="物资编码" align="center" enable="false"/>
			<EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false"/>
			<EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true" />
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" align="center" enable="false"/>
			<EF:EFColumn ename="matModel" cname="物资型号" align="center" enable="false"/>
			<EF:EFColumn ename="unit" cname="计量单位" hidden="true" />
			<EF:EFColumn ename="unitName" cname="计量单位" align="center" />
			<EF:EFColumn ename="price" cname="参考单价" align="center" enable="false"/>
			<EF:EFColumn ename="collectNum" cname="汇总数量" align="center" enable="false"/>
			<EF:EFColumn ename="num" cname="采购数量" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>

