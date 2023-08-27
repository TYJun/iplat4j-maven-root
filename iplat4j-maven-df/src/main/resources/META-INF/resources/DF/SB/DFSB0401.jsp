<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="特种设备台账基本情况">
	<EF:EFRegion id="FormDeivce" title="特种设备基本情况">
		<div class="row">
			<EF:EFInput ename="machineCode" cname="设备编号：" colWidth="4" ratio="3:8" readonly="true"/>
			<EF:EFInput ename="machineName" cname="设备名称：" colWidth="4" ratio="3:8" readonly="true"/>
			<EF:EFInput ename="statusCode" cname="设备状态：" colWidth="4" ratio="3:8" readonly="true"/>
			<EF:EFInput ename="models" cname="规格型号：" colWidth="4" ratio="3:8" readonly="true"/>
			<EF:EFInput ename="fixedTime" cname="安装日期：" colWidth="4" ratio="3:8" readonly="true"/>
			<EF:EFInput ename="fixedPlace" cname="安装地点：" colWidth="4" ratio="3:8" readonly="true"/>
		</div>
	</EF:EFRegion>
	<EF:EFTab id="tab-tab_df">
		<div title="巡检基准">
			<EF:EFGrid blockId="diScheme" autoDraw="no" autoBind="true" serviceName="DFSB0401" queryMethod="queryDiScheme">
				<EF:EFColumn ename="schemeID" cname="计划ID" hidden="true"/>
				<EF:EFColumn ename="schemeCode" cname="计划代码" enable="false"/>
				<EF:EFColumn ename="schemeName" cname="计划名称" enable="false"/>
				<EF:EFColumn ename="jobContent" cname="作业说明" enable="false"/>
				<EF:EFColumn ename="statusName" cname="状态" enable="false"/>
				<EF:EFColumn ename="createTime" cname="创建时间" enable="false"/>
				<EF:EFColumn ename="createMan" cname="创建人" enable="false"/>
				<EF:EFColumn ename="modifyTime" cname="修改时间" enable="false"/>
				<EF:EFColumn ename="modifyMan" cname="修改人" enable="false"/>
			</EF:EFGrid>
		</div>
		<div title="巡检任务">
			<EF:EFGrid blockId="diTask" autoDraw="no" autoBind="true" serviceName="DFSB0401" queryMethod="queryDiTask">
				<EF:EFColumn ename="taskID" cname="任务ID" hidden="true"/>
				<EF:EFColumn ename="schemeID" cname="基准ID" hidden="true"/>
				<EF:EFColumn ename="STATUS" cname="状态" enable="false"/>
				<EF:EFColumn ename="taskCode" cname="任务单号" enable="false"/>
				<EF:EFColumn ename="schemeName" cname="任务名称" enable="false"/>
				<EF:EFColumn ename="jobContent" cname="作业说明" enable="false"/>
				<EF:EFColumn ename="departName" cname="责任单位部门" enable="false"/>
				<EF:EFColumn ename="managerName" cname="责任人" enable="false"/>
				<EF:EFColumn ename="createTime" cname="创建时间" enable="false" />
				<EF:EFColumn ename="finishMan" cname="完工人" enable="false" />
				<EF:EFColumn ename="finishTime" cname="完成时间" enable="false" />
			</EF:EFGrid>
		</div>
		<div title="保养基准">
			<EF:EFGrid blockId="dkScheme" autoDraw="no"  autoBind="true" serviceName="DFSB0401" queryMethod="queryDkScheme">
				<EF:EFColumn ename="schemeID" cname="计划ID" hidden="true"/>
				<EF:EFColumn ename="schemeCode" cname="计划代码" enable="false"/>
				<EF:EFColumn ename="schemeName" cname="计划名称" enable="false"/>
				<EF:EFColumn ename="jobContent" cname="作业说明" enable="false"/>
				<EF:EFColumn ename="statusName" cname="状态" enable="false"/>
				<EF:EFColumn ename="createTime" cname="创建时间" enable="false"/>
				<EF:EFColumn ename="createMan" cname="创建人" enable="false"/>
				<EF:EFColumn ename="modifyTime" cname="修改时间" enable="false"/>
				<EF:EFColumn ename="modifyMan" cname="修改人" enable="false"/>
			</EF:EFGrid>
		</div>
		<div title="保养任务">
			<EF:EFGrid blockId="dkTask" autoDraw="no" autoBind="true" serviceName="DFSB0401" queryMethod="queryDkTask">
				<EF:EFColumn ename="taskID" cname="主键" hidden="true"/>
				<EF:EFColumn ename="schemeID" cname="主键" hidden="true"/>
				<EF:EFColumn ename="statusName" cname="状态" enable="false"/>
				<EF:EFColumn ename="taskCode" cname="任务单号" enable="false"/>
				<EF:EFColumn ename="schemeName" cname="任务名称" enable="false"/>
				<EF:EFColumn ename="jobContent" cname="作业说明" enable="false"/>
				<EF:EFColumn ename="departName" cname="责任单位部门" enable="false"/>
				<EF:EFColumn ename="managerName" cname="责任人" enable="false"/>
				<EF:EFColumn ename="createTime" cname="创建时间" enable="false"/>
				<EF:EFColumn ename="finishName" cname="完工人" enable="false" />
				<EF:EFColumn ename="finishTime" cname="完成时间" enable="false"/>
			</EF:EFGrid>
		</div>
		<div title="维修记录">
			<EF:EFGrid blockId="mtTask" autoDraw="no" autoBind="true" serviceName="DFSB0401" queryMethod="queryMtTask" >
				<EF:EFColumn ename="taskId" cname="工单ID" hidden="true"/>
				<EF:EFColumn ename="taskNo" cname="工单号" enable="false"/>
				<EF:EFColumn ename="statusName" cname="工单状态" enable="false"/>
				<EF:EFColumn ename="reqDeptName" cname="报修科室" enable="false"/>
				<EF:EFColumn ename="itemName" cname="维修事项" enable="false"/>
				<EF:EFColumn ename="wgroupName" cname="维修科室" enable="false"/>
				<EF:EFColumn ename="excutor" cname="执行人" enable="false"/>
				<EF:EFColumn ename="finshTime" cname="完工时间" enable="false"/>
				<EF:EFColumn ename="comments" cname="备注" enable="false"/>
			</EF:EFGrid>
		</div>
	</EF:EFTab>
</EF:EFPage>