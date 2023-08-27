<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="保养综合查询详情">
	<EF:EFRegion id="ADD">
		<div class="row">
			<EF:EFInput ename="taskID" cname="id" colWidth="4" type="hidden" readonly="true"/>
			<EF:EFInput ename="schemeID" cname="id" colWidth="4" type="hidden" readonly="true"/>
			<EF:EFInput ename="taskCode" cname="任务单号" colWidth="4" type="text" readonly="true"/>
			<EF:EFInput ename="schemeName" cname="任务(基准)名称" colWidth="4" type="text" readonly="true"/>
			<EF:EFInput ename="machineName" cname="设备" colWidth="4" type="text" readonly="true"/>
			<EF:EFInput ename="name" readonly="true" cname="保养管理人" colWidth="4" type="text" min="0"/>
			<EF:EFInput ename="finishTime" readonly="true" cname="完成时间" colWidth="4" type="text"/>
			<EF:EFInput ename="finishManName" readonly="true" cname="完工操作人" colWidth="4" type="text"/>
		</div>
	</EF:EFRegion>	
		<EF:EFRegion title="保养说明">
			<EF:EFInput readonly="true" colWidth="14" ratio="0:12" type="textarea"
				 ename="jobContent" cname=""></EF:EFInput>
		</EF:EFRegion>
		<EF:EFRegion title="保养内容">
			<EF:EFGrid blockId="item" autoDraw="no" serviceName="DKZH0101" enable="false">
			<EF:EFColumn ename="taskID" cname="taskID" hidden="true" width="10"/>
			<EF:EFColumn ename="taskCode" cname="taskCode" hidden="true" width="10"/>
			<EF:EFColumn ename="schemeID" cname="schemeID" hidden="true" width="10"/>
				<EF:EFColumn ename="id" cname="id" readonly="true"  hidden="true" width="10"/>
				<EF:EFColumn ename="itemID" cname="itemID" hidden="true" width="10"/>
				<EF:EFColumn ename="code" cname="code" readonly="true"  hidden="true" width="10"/>
				<EF:EFColumn ename="jitemName" cname="保养作业项目" enable="false" readonly="true" width="40"/>
				<EF:EFColumn ename="itemDesc" cname="保养作业说明" enable="false" readonly="true" width="40"/>
				<EF:EFColumn ename="referenceValue" cname="参考值" enable="false" readonly="true" width="30" />
				<EF:EFColumn ename="successFlag" cname="状态" enable="false" readonly="true" width="30"/>
				<EF:EFColumn ename="writeValue" cname="结果记录" enable="false" readonly="true" width="60"/>
				<EF:EFColumn ename="actualValueUnit" cname="保养实际值单位" enable="false" readonly="true" width="30"/>
				<EF:EFColumn ename="errorContent" cname="异常故障描述" enable="false" readonly="true" width="40"/>
				<EF:EFColumn ename="exception_status" cname="异常状态" enable="false" readonly="true" width="30" />
				<EF:EFColumn ename="exception_result" cname="异常处理结果" enable="false" readonly="true" width="40"/>
			</EF:EFGrid>
		</EF:EFRegion>
		
		<EF:EFRegion title="保养执行人">
			<EF:EFGrid blockId="exman" autoDraw="no" serviceName="DKZH0101" enable="false">
				<EF:EFColumn ename="id" cname="id" enable="false" hidden="true"/>
			<EF:EFColumn ename="workNo" cname="manId" hidden="true" enable="false"/>
			<EF:EFColumn ename="deptName" align="center" cname="执行部门" enable="false" />
			<EF:EFColumn ename="deptNum" align="center" cname="执行部门编号" hidden="true" enable="false"/>
			<EF:EFColumn ename="name" align="center" cname="执行人" enable="false" />
			<EF:EFColumn ename="workNo" align="center" cname="工号" enable="false" />
			</EF:EFGrid>
		</EF:EFRegion>
		<EF:EFRegion title="设备图片">
		<EF:EFGrid blockId="result4" autoDraw="no" checkMode="single"
			readonly="true" exportGrid="false" toolbarConfig="false" enable="false">
			<EF:EFColumn ename="pathName" cname="保养后片" width="100" align="center"/>
		</EF:EFGrid>
		</EF:EFRegion>
</EF:EFPage>
