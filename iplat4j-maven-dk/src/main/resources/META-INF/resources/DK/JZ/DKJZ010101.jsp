<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="设备添加">
			<EF:EFRegion id="inqu" title="查询区" showClear="true">
				<div class="row">
					<EF:EFInput ename="machineName" cname="设备名称" />
					<EF:EFInput ename="machineCode" cname="设备代码" />
				</div>
				
			</EF:EFRegion>
			<EF:EFRegion id="result" title="数据集" fitHeight="true">
				<EF:EFGrid blockId="result" autoDraw="no" serviceName="DKJZ010101">
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