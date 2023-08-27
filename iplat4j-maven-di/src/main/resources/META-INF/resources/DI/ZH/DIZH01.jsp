<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="巡检综合查询">
	<EF:EFRegion id="inqu" title="查询区">
		<div class="row">
			<EF:EFSelect ename="taskNameType" cname="任务类型"
						 resultId="task" textField="taskNameType" valueField="taskNameType"
						 serviceName="DIZH01" methodName="queryTaskName" optionLabel="请选择"
						 colWidth="4" filter="contains">
			</EF:EFSelect>
			<EF:EFSelect ename="deptName" cname="科室"
						 resultId="dept" valueField="deptName" textField="deptName"
						 serviceName="DIZH01" methodName="queryDept"
						 optionLabel="请选择" filter="contains" />
			<EF:EFInput ename="taskCode" cname="任务编码"  colWidth="4"/>
		    </div>
		<div class="row">
			<EF:EFInput ename="taskName" cname="任务名称" colWidth="4"/>
			<EF:EFInput ename="spotName" cname="地点名称" colWidth="4" type="hidden"/>
			<EF:EFInput ename="machineName" cname="设备名称" colWidth="4"/>
			<EF:EFSelect cname="状态:" ename="status" >
				<EF:EFOption label="请选择" value="" />
				<EF:EFOption label="执行中" value="1" />
				<EF:EFOption label="已完工" value="2" />
				<EF:EFOption label="已超时" value="-1" />
				<EF:EFOption label="关闭" value="3" />
			</EF:EFSelect>
		</div>
		<div class="row">
			<EF:EFDatePicker ename="startTimeS" cname="开始日期起：" role="date"
				colWidth="4" ratio="4:8" format="yyyy-MM-dd"
				parseFormats="['yyyy-mm-dd']" />
			<EF:EFDatePicker ename="startTimeE" cname="开始日期止：" role="date"
				colWidth="4" ratio="4:8" format="yyyy-MM-dd"
				parseFormats="['yyyy-mm-dd']" />
		</div>

	</EF:EFRegion>
	<EF:EFRegion id="result" title="数据集" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DIZH01">
			<EF:EFColumn ename="taskID" cname="主键" hidden="true" />
			<EF:EFColumn ename="schemeID" cname="基准的ID号" hidden="true" />
			<EF:EFColumn ename="status" cname="状态 " readonly="true" width="50"/>
			<EF:EFColumn ename="taskCode" cname="任务单号" readonly="true"  width="100"
			 displayType="url"/>
			<EF:EFColumn ename="schemeName" cname="任务名称  " readonly="true"  width="100"/>
			<EF:EFColumn ename="exceptionFlag" cname="异常状态 " readonly="true"  width="100"/>
			<EF:EFColumn ename="machineName" cname="设备名称 " readonly="true"  width="150"/>
			<EF:EFColumn ename="machineCode" cname="设备代码" readonly="true"  width="50"/>
			<EF:EFColumn ename="jobContent" cname="作业说明 " readonly="true"  width="100"/>
			<EF:EFColumn ename="deptName" cname="责任单位部门 " readonly="true"  width="100"/>
			<EF:EFColumn ename="name" cname="责任人" readonly="true"  width="80"/>
			<EF:EFColumn ename="finishName" cname="完工操作人 " readonly="true"  width="80"/>
			<EF:EFColumn ename="createTime" cname="计划开始时间 " readonly="true"  width="100"/>
			<EF:EFColumn ename="finishTime" cname="完成时间 " readonly="true"  width="100"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="80%"
		height="90%" title="巡检综合查询详情" modal="true" />
	</div>
</EF:EFPage>