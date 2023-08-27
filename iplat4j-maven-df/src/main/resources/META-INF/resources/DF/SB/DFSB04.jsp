<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="特种设备台账查询">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<%-- <EF:EFInput ename="machineTypeId" cname="设备类型：" colWidth="4" ratio="3:8"/> --%>
			<EF:EFInput ename="machineCode" cname="设备编码：" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="machineName" cname="设备名称：" colWidth="4" ratio="3:8"/>				
			<EF:EFSelect cname="设备状态：" ename="statusCode" colWidth="4" ratio="3:8">
				<EF:EFOption label="请选择" value=""/>
				<EF:EFOption label="新建" value="0"/>
				<EF:EFOption label="停用" value="-1"/>
				<EF:EFOption label="启用" value="1"/>
				
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="特种设备台账管理" fitHeight="true">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" serviceName="DFSB04"
			checkMode="single,row" readonly="false" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="设备Id" hidden="true"/>
			<EF:EFColumn ename="machineCode" cname="设备编码" enable="false"/>
			<EF:EFColumn ename="machineName" cname="设备名称" enable="false"/>
			<EF:EFColumn ename="models" cname="规格型号" enable="false"/>
 			<EF:EFColumn ename="fixedPlace" cname="安装地点" enable="false"/>
			<EF:EFComboColumn ename="statusCode" cname="设备状态" width="100" >	
			    <EF:EFOption label="新建" value="0"/>
				<EF:EFOption label="启用" value="1"/>
				<EF:EFOption label="停用" value="-1"/>
			</EF:EFComboColumn>
			<EF:EFColumn ename="diSchemaCount" cname="巡检基准数" width="100" align="center"/>
			<EF:EFColumn ename="diTaskCount" cname="巡检任务数" width="100" align="center"/>
			<EF:EFColumn ename="dkSchemaCount" cname="保养基准数" width="100" align="center"/>
			<EF:EFColumn ename="dkTaskCount" cname="保养任务数" width="100" align="center"/>
			<EF:EFColumn ename="mtTaskCount" cname="维修工单数" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="特种设备台账详情" modal="true" />
</EF:EFPage>