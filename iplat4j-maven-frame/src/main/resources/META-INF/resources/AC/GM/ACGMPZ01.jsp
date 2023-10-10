<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="GroupCname" cname="群组名称:" />
			<EF:EFInput ename="GroupEname" cname="群组编码:" />
			<EF:EFInput ename="matClassCode" cname="物资分类编码:" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="物资档案配置信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single"
			readonly="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="matClassCode" cname="物资分类编码" width="100" />
			<EF:EFColumn ename="matClassName" cname="物资分类名称" width="100" />
			<EF:EFColumn ename="GroupEname" cname="群组编码" width="100"/>
			<EF:EFColumn ename="GroupCname" cname="群组名称" width="100" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

<EF:EFWindow id="popData" url=" " lazyload="true" width="50%"
	height="60%">

</EF:EFWindow>
