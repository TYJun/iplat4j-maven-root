<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>人员离职管理</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="离职时间" head="hidden">
		<div class="row">
			<EF:EFInput ename="id" cname="id" type="hidden"/>
			<EF:EFDateSpan startName="preOutTime" startCname="预离职时间"
						   endName="outTime" endCname="离职时间"
						   startRatio="4:8" endRatio="4:8" role="date" colWidth="4"  readonly="true" required="true"/>
		</div>
	</EF:EFRegion>
</EF:EFPage>