<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="宿舍信息管理">

	<EF:EFRegion id="result" title="宿舍本月用电量展示" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no">
			<EF:EFColumn ename="id" cname="id" readonly="true" hidden="true" />
			<EF:EFColumn ename="pointerTag" cname="大楼 " readonly="true" width="40" />
			<EF:EFColumn ename="dormitoryNo" cname="地点名 " readonly="true" width="70" />
			<EF:EFColumn ename="lastElec" cname="当月用电量(度) " readonly="true"
				width="70" />
			<EF:EFColumn ename="elec" cname="当月采集电度数(度) " readonly="true"
				width="70" />
			<EF:EFColumn ename="operationDate" cname="采集时间" readonly="true"
				width="100" />

		</EF:EFGrid>
	</EF:EFRegion>
	
</EF:EFPage>


