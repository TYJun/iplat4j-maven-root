<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-matCode" cname="物资编码:" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库物资信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single"
			readonly="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="matCode" cname="物资编码" width="100"  />
			<EF:EFColumn ename="matName" cname="物资名称" width="100"  />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

<EF:EFWindow id="popData" url=" " lazyload="true" width="50%"
	height="60%">

</EF:EFWindow>
