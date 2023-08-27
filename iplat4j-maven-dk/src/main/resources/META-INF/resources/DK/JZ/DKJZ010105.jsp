<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="设备包选择" showClear="true">
			<EF:EFInput ename="inqu_status-0-packetName" colWidth="4" ratio="2:8"  cname="设备包名称:" />
	</EF:EFRegion>
		<EF:EFRegion id="result" title="设备包列表" fitHeight="true">
			<EF:EFGrid blockId="result" autoDraw="no" checkMode="single" refresh="true" >
				<EF:EFColumn ename="packetId" cname="id" width="100" hidden="true" readonly="true" align="center" />
				<EF:EFColumn ename="machineId" cname="id" width="100" hidden="true" readonly="true" align="center" />
				<EF:EFColumn ename="packetCode" cname="设备包编码" width="100" readonly="true" align="center" />
				<EF:EFColumn ename="packetName" cname="设备包名称" width="100" readonly="true" align="center" />
			</EF:EFGrid>
		</EF:EFRegion>
</EF:EFPage>

