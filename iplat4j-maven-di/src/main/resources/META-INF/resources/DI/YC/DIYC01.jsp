<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-deptName" cname="科室"
						     resultId="dept" valueField="deptName" textField="deptName"
						     serviceName="DIZH01" methodName="queryDept"
						     optionLabel="请选择" colWidth="4" ratio="3:8" filter="contains" />
			<EF:EFSelect ename="inqu_status-0-taskNameType" cname="任务类型"
						 resultId="task" textField="taskNameType" valueField="taskNameType"
						 serviceName="DIZH01" methodName="queryTaskName" optionLabel="请选择"
						 colWidth="4" ratio="3:8" filter="contains">
			</EF:EFSelect>

			<EF:EFInput ename="inqu_status-0-taskCode" cname="任务单号：" colWidth="4" ratio="3:8"/>
		</div>
		<div class="row">
			<%-- <EF:EFInput ename="queryTaskCode" cname="设备代码："  colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="querySpotNum" cname="设备名称：" colWidth="4" ratio="3:8"/> 
			<EF:EFInput ename="querySpotName" cname="设备类型：" colWidth="4" ratio="3:8"/>--%>
			<EF:EFInput ename="inqu_status-0-jitemName" cname="任务名称：" colWidth="4" ratio="3:8"/>
			<%-- <EF:EFInput ename="querySpotName" cname="单位部门：" colWidth="4" ratio="3:8"/> --%>
			<%-- <EF:EFInput ename="querySpotName" cname="责任人：" colWidth="4" ratio="3:8"/> --%>
			<EF:EFDatePicker ename="inqu_status-0-startTime" cname="计划开始日期：" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
				parseFormats="['yyyy-mm-dd']" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="计划截至日期：" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
				parseFormats="['yyyy-mm-dd']" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="任务管理" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single">
			<EF:EFColumn ename="taskid" cname="主键" hidden="true" />
			<EF:EFColumn ename="schemeid" cname="基准的ID号" hidden="true" />
			<EF:EFColumn ename="itemID" cname="主键" hidden="true" align="center"/>
			<EF:EFColumn ename="exceptionStatus" cname="异常状态" enable="false" align="center"/>
			<EF:EFColumn ename="exceptionResult" cname="解决措施" enable="false" align="center"/>
			<EF:EFColumn ename="taskCode" cname="任务单号" enable="false" align="center"/> 
			<EF:EFColumn ename="jitemName" cname="任务名称" enable="false" align="center"/>
			<%-- <EF:EFColumn ename="statusCode" cname="设备名称" enable="false" align="center"/>
			<EF:EFColumn ename="startTime" cname="设备代码" enable="false" align="center"/> --%>
			<EF:EFColumn ename="jobContent" cname="作业说明" enable="false" align="center"/>
			<%-- <EF:EFColumn ename="finishTime" cname="巡检作业说明" readonly="true" />
			--%>
			<EF:EFColumn ename="writeValue" cname="结果记录" readonly="true" /> 
<%--			<EF:EFColumn ename="errorContent" cname="异常故障描述" enable="false" align="center"/>--%>
			<EF:EFColumn ename="solutionType" cname="解决途径" enable="false" align="center"/>
			<EF:EFColumn ename="finishMan" cname="处理人" enable="false" align="center"/>
			<%-- <EF:EFColumn ename="finishTime" cname="计划开始时间" readonly="true" /> --%>
			<EF:EFColumn ename="finishTime" cname="处理完成时间" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="700" height="400" title="异常处理" modal="true" />
	<EF:EFWindow id="popData1" url="" lazyload="true" width="90%" height="90%" title="巡检综合查询详情" modal="true" />
</EF:EFPage>

