<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!--需求计划综合查询-->
<EF:EFPage title="需求计划综合查询">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-deptNum" cname="科室编码" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-planNo" cname="需求计划单号" />
			<EF:EFSelect ename="inqu_status-0-planType" cname="需求计划类型">
				<EF:EFOption label="全部" value="" />
				<EF:EFCodeOption codeName="wilp.rm.require.planType"/>
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态">
				<EF:EFOption label="全部" value="" />
				<EF:EFCodeOption codeName="wilp.rm.require.status"/>
			</EF:EFSelect>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="申请日期起(>=)"></EF:EFDatePicker>
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="申请日期止(<=)"></EF:EFDatePicker>
			<EF:EFInput ename="inqu_status-0-recCreatorName" cname="创建人"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="需求计划列表">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" readonly="true">
			<EF:EFColumn ename="id" cname="id" hidden="true" />
			<EF:EFColumn ename="planNo" cname="需求计划单号" align="center" displayType="url"/>
			<EF:EFComboColumn ename="planType" cname="需求计划类型" align="center">
				<EF:EFCodeOption codeName="wilp.rm.require.planType"/>
			</EF:EFComboColumn>
			<EF:EFColumn ename="planTime" cname="需求时间" align="center" />
			<EF:EFColumn ename="deptName" cname="科室名称" align="center" />
			<EF:EFColumn ename="planNum" cname="需求总量" align="center" />
			<EF:EFColumn ename="planCost" cname="需求总价(元)" align="center" />
			<EF:EFColumn ename="statusName" cname="状态" align="center" />
			<EF:EFColumn ename="planDesc" cname="需求计划描述" align="center" />
			<EF:EFColumn ename="remark" cname="备注" align="center" />
			<EF:EFColumn ename="recCreatorName" cname="创建人" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="80%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-keydown.js"></script>