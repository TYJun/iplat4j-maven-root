<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>

<EF:EFRegion id="result" title="流程详细">
		<EF:EFGrid blockId="result" autoDraw="no" exportGrid="false" toolbarConfig="false">
			<EF:EFColumn ename="time" cname="时间" width="100" readonly="true" align="center"/>
			<EF:EFColumn ename="name" cname="姓名" width="100" readonly="true" align="center"/>
			<EF:EFColumn ename="contentType" cname="状态" width="100" readonly="true" align="center"/>
			<EF:EFColumn ename="param" cname="详细" width="100" readonly="true" hidden="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
