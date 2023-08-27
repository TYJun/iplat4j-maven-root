<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="保养实绩查询">
	<EF:EFRegion id="inqu" title="查询区">
		<div class="row">
			<EF:EFInput ename="taskCode" cname="任务单号"  colWidth="4"/>
			<EF:EFInput ename="taskName" cname="任务名称" colWidth="3"/>
			<%-- <EF:EFInput ename="spotName" cname="单位部门" colWidth="3"/> --%>
			<%-- <EF:EFSelect ename="schemeDept" cname="责任单位部门:" colWidth="3"
				resultId="dept" textField="deptName" valueField="deptNum"
				filter="contains" serviceName="DKSJ0101"
				methodName="queryDept" required="true">
			</EF:EFSelect> --%>
			<EF:EFInput ename="schemeDept" cname="保养单位" colWidth="3"/>
			</div>
		<div class="row">
			<EF:EFSelect cname="状态:" ename="status" >
				<EF:EFOption label="全部" value="" />
				<EF:EFOption label="执行中" value="1" />
				<EF:EFOption label="已完工" value="2" />
				<EF:EFOption label="已超时" value="-1" />
				<EF:EFOption label="关闭" value="3" />
			</EF:EFSelect>
			<EF:EFDatePicker ename="startTimeS" cname="开始日期起：" role="datetime"
				colWidth="3" ratio="4:8" format="yyyy-MM-dd HH:mm:ss"
				parseFormats="['yyyy-MM-dd HH:mm:ss']" />
			<EF:EFDatePicker ename="startTimeE" cname="开始日期止：" role="datetime"
				colWidth="3" ratio="4:8" format="yyyy-MM-dd HH:mm:ss"
				parseFormats="['yyyy-MM-dd HH:mm:ss']" />
		</div>

	</EF:EFRegion>
	<EF:EFRegion id="result" title="数据集" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DKSJ01">
			<EF:EFColumn ename="taskID" cname="主键" hidden="true" />
			<EF:EFColumn ename="schemeID" cname="基准的ID号" hidden="true" />
			<EF:EFColumn ename="status" cname="状态 " readonly="true" width="50"/>
			<EF:EFColumn ename="taskCode" cname="任务单号" readonly="true"  width="100" align="center"/>
			<EF:EFColumn ename="schemeName" cname="任务名称  " readonly="true"  width="100"/>
			<EF:EFColumn ename="jobContent" cname="作业说明 " readonly="true"  width="150"/>
			<EF:EFColumn ename="deptName" cname="保养单位 " readonly="true"  width="100" align="center"/>
			<EF:EFColumn ename="name" cname="保养管理人" readonly="true" align="center" width="80"/>
			<EF:EFColumn ename="finishManName" cname="完工操作人 " readonly="true"  width="80"/>
			<EF:EFColumn ename="createTime" cname="计划开始时间 " readonly="true"  width="100"/>
			<EF:EFColumn ename="finishTime" cname="完成时间 " readonly="true"  width="100"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="editItem" url="" lazyload="true" width="90%" height="90%" title="维护实绩" modal="true" />
</EF:EFPage>
