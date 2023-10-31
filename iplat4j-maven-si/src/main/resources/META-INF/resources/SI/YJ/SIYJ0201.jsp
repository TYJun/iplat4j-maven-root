<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%--生成月度需求计划--%>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="需求计划信息">
		<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-planTime" cname="月份" format="yyyy-MM" readonly="true" colWidth="5"></EF:EFDatePicker>
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室" readonly="true" colWidth="5"/>
			<EF:EFInput ename="inqu_status-0-deptNum" cname="科室" type="hidden"/>
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-planType" cname="需求计划类型" value="02" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-planDesc" cname="需求计划描述" type="textarea" colWidth="5" placeholder="不能超过200字"/>
			<EF:EFInput ename="inqu_status-0-remark" cname="备注" type="textarea" colWidth="5" placeholder="不能超过200字"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="需求计划明细列表">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row" sort="single">
			<EF:EFColumn ename="matNum" cname="物资编码" enable="false" align="center" sort="true"/>
			<EF:EFColumn ename="matName" cname="物资名称" enable="false" align="center" sort="true"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" enable="false" align="center" sort="true"/>
			<EF:EFColumn ename="matModel" cname="物资型号" enable="false" align="center" sort="false"/>
			<EF:EFColumn ename="matTypeNum" cname="物资分类编码"  hidden="true" sort="false"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称"  enable="false" align="center" sort="false"/>
			<EF:EFColumn ename="unit" cname="计量单位" hidden="true" sort="false"/>
			<EF:EFColumn ename="unitName" cname="计量单位" width="80" enable="false" align="center" sort="false"/>
			<EF:EFColumn ename="price" cname="单价" enable="false" width="80" align="center" sort="true"/>
			<EF:EFColumn ename="minNum" cname="库存下限" enable="false" align="center" sort="true"/>
			<EF:EFColumn ename="maxNum" cname="库存上限" enable="false" align="center" sort="false"/>
			<EF:EFColumn ename="totalNum" cname="库存存量" enable="false" align="center" sort="true"/>
			<EF:EFColumn ename="num" cname="需求数量" align="center" sort="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>