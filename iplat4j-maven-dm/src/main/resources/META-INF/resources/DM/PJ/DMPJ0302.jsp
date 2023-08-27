<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<title>查看评价履历页面</title>
<EF:EFPage title="查看评价履历">
	<EF:EFRegion id="result" title="评价履历" fitHeight="true">
		<EF:EFInput ename="manId" cname="manId" type="hidden"/>
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DMPJ0302" rowNo="true" checkMode="single,row">
			<EF:EFColumn ename="manBehaviorMean" cname="行为等级" readonly="true" />
			<EF:EFColumn ename="evalContent" cname="评价备注" readonly="true" />
			<EF:EFColumn ename="evalCreateName" cname="评价人" readonly="true" />
			<EF:EFColumn ename="evalTime" cname="评价时间" readonly="true" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>