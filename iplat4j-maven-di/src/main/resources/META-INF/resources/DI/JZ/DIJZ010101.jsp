<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="设备添加">
	<div class="col-md-4 i-fit-height" >
		<EF:EFRegion id="R2" title="设备分类" fitHeight="true" >
			<EF:EFTree id="menu" valueField="id" textField="classifyName" hasChildren="isLeaf"
					   serviceName="DFFL10" methodName="queryDFFLTree" style="height:560px;"/>
		</EF:EFRegion>
	</div>
	<div class="col-md-8 i-fit-height">
		<EF:EFRegion id="inqu" title="查询区" showClear="true">
			<div class="row">
				<EF:EFInput ename="machineName" cname="设备名称" colWidth="6" ratio="3:9"/>
				<EF:EFInput ename="machineCode" cname="设备代码" colWidth="6" ratio="3:9"/>
				<EF:EFInput ename="machineTypeId" cname="设备分类id" type="hidden"/>
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="设备信息" fitHeight="true">
			<EF:EFGrid blockId="result" autoDraw="no" serviceName="DIJZ010101" checkMode="row">
				<EF:EFColumn ename="id" cname="主键" hidden="true" align="center" width="100"/>
				<EF:EFColumn ename="machineId" cname="machineId" hidden="true" align="center" width="100"/>
				<EF:EFColumn ename="machineCode" cname="设备代码" readonly="true" align="center" width="80"/>
				<EF:EFColumn ename="machineName" cname="设备名称" readonly="true" align="center" width="80"/>
				<EF:EFColumn ename="models" cname="规格型号" readonly="true" align="center" width="150" hidden="true"/>
				<EF:EFColumn ename="fixedPlace" cname="安装地点" readonly="true" align="center" width="150"/>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
</EF:EFPage>