<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!--领用单选择-->
<EF:EFPage title="领用单选择">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">

			<EF:EFInput ename="inqu_status-0-deptName" cname="科室" readonly="true" colWidth="6"/>
			<EF:EFInput ename="inqu_status-0-deptNum" cname="科室" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-claimNo" cname="领用单号" colWidth="6"/>
			<EF:EFInput ename="inqu_status-0-claimId" cname="领用ID" type="hidden"/>
		</div>
		<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="申请日期起(>=)" colWidth="6"></EF:EFDatePicker>
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="申请日期止(<=)" colWidth="6"></EF:EFDatePicker>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="领用单列表">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row" readonly="true" height="200">
			<EF:EFColumn ename="id" cname="id" hidden="true" />
			<EF:EFColumn ename="claimNo" cname="领用申请单号" align="center" displayType="url"/>
			<EF:EFColumn ename="statusName" cname="状态" align="center" />
			<EF:EFColumn ename="claimNum" cname="领用总量" align="center" />
			<EF:EFColumn ename="deptName" cname="领用科室" align="center" />
			<EF:EFColumn ename="costDeptName" cname="成本归集科室" align="center" />
			<EF:EFColumn ename="remark" cname="备注" align="center" />
			<EF:EFColumn ename="recCreateTimeStr" cname="申请时间" align="center" />
			<EF:EFColumn ename="recCreatorName" cname="领用人" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFRegion id="detail" title="领用明细列表">
		<EF:EFGrid blockId="detail" autoDraw="no" autoBind="false" checkMode="row" queryMethod="queryDetail">
			<EF:EFColumn ename="claimNo" cname="领用单号" hidden="true" />
			<EF:EFColumn ename="matNum" cname="物资编码" align="center" enable="false"/>
			<EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" align="center" enable="false"/>
			<%--<EF:EFColumn ename="matModel" cname="物资型号" align="center" enable="false"/>--%>
			<EF:EFColumn ename="unit" cname="计量单位" hidden="true" />
			<EF:EFColumn ename="unitName" cname="计量单位" align="center" enable="false"/>
			<EF:EFColumn ename="price" cname="参考单价(元)" align="center" hidden="true"/>
			<EF:EFColumn ename="outNum" cname="实际领用数量" align="center" enable="false"/>
			<EF:EFColumn ename="backOutNum" cname="退库数量" align="center" />
			<EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true" />
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-keydown.js"></script>