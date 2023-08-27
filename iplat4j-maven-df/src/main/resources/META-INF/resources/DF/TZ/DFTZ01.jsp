<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="设备台账查询">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="machineCode" cname="设备编码：" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="machineName" cname="设备名称：" colWidth="4" ratio="3:8"/>
			<EF:EFSelect cname="设备状态：" ename="status" colWidth="4" ratio="3:8">
				<EF:EFOption label="请选择" value=""/>
				<EF:EFOption label="启用" value="启用"/>
				<EF:EFOption label="正常" value="正常"/>
				<EF:EFOption label="停用" value="停用"/>
				<EF:EFOption label="报废" value="报废"/>
				<EF:EFOption label="异常" value="异常"/>
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="设备台账管理" fitHeight="true">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" serviceName="DFTZ01"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="machineId" cname="设备Id" hidden="true"/>
			<EF:EFColumn ename="machineCode" cname="设备编码" width="100" align="center"/>
			<EF:EFColumn ename="machineName" cname="设备名称" width="150" align="center"/>
			<EF:EFColumn ename="models" cname="规格型号" width="100" align="center"/>
 			<EF:EFColumn ename="fixedPlace" cname="安装地点" width="200" align="center"/>
			<EF:EFColumn ename="STATUS" cname="设备状态" width="80" align="center"/>
			<EF:EFColumn ename="diSchemaCount" cname="巡检基准数" width="100" align="center"/>
			<EF:EFColumn ename="diTaskCount" cname="巡检任务数" width="100" align="center"/>
			<EF:EFColumn ename="dkSchemaCount" cname="保养基准数" width="100" align="center"/>
			<EF:EFColumn ename="dkTaskCount" cname="保养任务数" width="100" align="center"/>
			<EF:EFColumn ename="mtTaskCount" cname="维修工单数" width="100" align="center"/>
			<EF:EFColumn ename="bjTaskCount" cname="保洁次数" width="100" align="center"/>
			<EF:EFColumn ename="rhTaskCount" cname="润滑次数" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="设备台账详情" modal="true" />
</EF:EFPage>