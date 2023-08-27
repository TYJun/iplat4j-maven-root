<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="安全巡查异常查询">
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-taskCode" cname="任务单号" colWidth="5" ratio="2:6"/>
			<EF:EFInput ename="inqu_status-0-jitemName" cname="任务名称" colWidth="5" ratio="2:6"/>
		</div>
		<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-startTime" cname="开始日期" role="date" colWidth="5" ratio="2:6" format="yyyy-MM-dd"
				parseFormats="['yyyy-mm-dd']" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="截至日期" role="date" colWidth="5" ratio="2:6" format="yyyy-MM-dd"
				parseFormats="['yyyy-mm-dd']" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="任务管理" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single">
			<EF:EFColumn ename="itemID" cname="主键" hidden="true" align="center"/>
			<EF:EFColumn ename="exceptionStatus" cname="异常状态" enable="false" align="center"/>
			<EF:EFColumn ename="exceptionResult" cname="异常处理结果" enable="false" align="center"/>
			<EF:EFColumn ename="taskCode" cname="任务单号" enable="false" align="center"/> 
			<EF:EFColumn ename="jitemName" cname="任务名称" enable="false" align="center"/>
			<%-- <EF:EFColumn ename="statusCode" cname="设备名称" enable="false" align="center"/>
			<EF:EFColumn ename="startTime" cname="设备代码" enable="false" align="center"/> --%>
			<EF:EFColumn ename="jobContent" cname="作业说明" enable="false" align="center"/>
			<%-- <EF:EFColumn ename="finishTime" cname="巡检作业说明" readonly="true" />
			<EF:EFColumn ename="finishTime" cname="结果记录" readonly="true" /> --%>
			<EF:EFColumn ename="errorContent" cname="异常故障描述" enable="false" align="center"/>
			<EF:EFColumn ename="finishMan" cname="完工操作人" enable="false" align="center"/>
			<%-- <EF:EFColumn ename="finishTime" cname="计划开始时间" readonly="true" /> --%>
			<EF:EFColumn ename="finishTime" cname="完成时间" enable="false" align="center"/> 
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="600" height="300" title="异常处理" modal="true" />
</EF:EFPage>

