<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="标准信息">
		<EF:EFGrid blockId="result" autoDraw="no" copyToAdd="false"><!-- checkMode="single" -->
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false" align="center" />
			<EF:EFColumn ename="projectName" cname="调查考核项目" width="100" required="true" align="center" displayType="url"/>
			<EF:EFColumn ename="projectRemark" cname="项目备注" width="100" required="true" align="center"/>
			<EF:EFColumn ename="orderNumber" cname="调查排序" width="100" required="true" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="95%" height="95%" title="调查标准管理" modal="true" />
</EF:EFPage>

