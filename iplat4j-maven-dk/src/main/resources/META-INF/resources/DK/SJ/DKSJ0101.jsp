<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="保养实绩维护">
	<EF:EFRegion id="EDIT" title="基本信息">
		<div class="row">
			<EF:EFInput id="schemeID"  ename="schemeID" cname="基准id码" colWidth="4" type="hidden" readonly="true"/>
			<EF:EFInput id="taskID"  ename="taskID" cname="任务id" colWidth="4" type="hidden" readonly="true"/>
			<EF:EFInput ename="taskCode" cname="任务单号" colWidth="4" type="text" readonly="true"/>
			<EF:EFInput ename="schemeName" cname="基准名称" colWidth="4" type="text" required="true" readonly="true"/>
			<EF:EFInput ename="machineName" cname="设备" colWidth="4" type="text" required="true" readonly="true"/>
		    <%-- <EF:EFSelect ename="schemeDept" cname="保养单位:" colWidth="4"
				resultId="dept" textField="deptName" valueField="deptNum"
				filter="contains" serviceName="DKJZ0101"
				methodName="queryDept" required="true">
			</EF:EFSelect>
			<EF:EFSelect ename="schemeMan" cname="保养管理人:" colWidth="4"
				resultId="person" textField="name" valueField="id"
				filter="contains" serviceName="DKJZ0101"
				methodName="queryMan" required="true">
			</EF:EFSelect> --%>
			<EF:EFInput ename="schemeDept" cname="保养单位" colWidth="4" type="text" required="true" readonly="true"/>
			<EF:EFInput ename="schemeMan" cname="保养管理人" colWidth="4" type="text" required="true" readonly="true"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion title="作业说明">
		<div class="row padding20">
			<EF:EFInput colWidth="12" ratio="0:12" type="textarea" ename="jobContent" rows="3" readonly="false"></EF:EFInput>
		</div>
	</EF:EFRegion>
		
	<EF:EFRegion title="保养项目">
		<EF:EFGrid blockId="item" autoDraw="no" serviceName="DKSJ0101">
			<EF:EFColumn ename="id" cname="id" enable="false"  hidden="true"/>
			<EF:EFColumn ename="itemId" cname="itemId" hidden="true" enable="false"/>
			<EF:EFColumn ename="code" cname="code" enable="false"  hidden="true"/>
			<EF:EFColumn ename="content" align="center" cname="保养项目" enable="false" width="60"/>
			<EF:EFColumn ename="projectDesc" align="center" cname="项目描述" enable="false" width="120"/>
			<%-- EF:EFComboColumn ename="successFlag" cname="保养结果" width="40" >
				<EF:EFCodeOption codeName="dk.maintainResult"/>
			</EF:EFComboColumn> --%>
			<%-- <EF:EFColumn ename="successFlag" align="center" cname="保养结果" width="120"/> --%>
			<EF:EFColumn ename="writeValue" align="center" cname="保养结果" width="120"/>
			<EF:EFColumn ename="referenceValue" align="center" cname="保养项目参考值" enable="false" />
			<EF:EFColumn ename="actualValueUnit" align="center" cname="保养实际单位" enable="false" />
		</EF:EFGrid>
	</EF:EFRegion>
	
	<EF:EFRegion title="执行人员"> 
		<EF:EFGrid blockId="exman" autoDraw="no" serviceName="DKJZ0101">
			<EF:EFColumn ename="id" cname="id" enable="false" hidden="true"/>
			<EF:EFColumn ename="workNo" cname="manId" hidden="true" enable="false"/>
			<EF:EFColumn ename="deptName" align="center" cname="执行部门" enable="false" />
			<EF:EFColumn ename="deptNum" align="center" cname="执行部门编号" hidden="true" enable="false"/>
			<EF:EFColumn ename="name" align="center" cname="执行人" enable="false" />
			<EF:EFColumn ename="workNo" align="center" cname="工号" enable="false" />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popDataExman" url="" lazyload="true" width="50%" height="82%" title="添加执行人" modal="true" />
</EF:EFPage>