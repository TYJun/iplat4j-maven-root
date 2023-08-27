<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>报表配置页面</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-reportName" cname="报表名称"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="报表配置信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="row" autoBind="true" readonly="false" >
			<EF:EFColumn ename="id" cname="id" hidden="true" />
			<EF:EFColumn ename="reportName" cname="报表名称" align="center" width="100"/>
			<EF:EFColumn ename="reportUrl" cname="报表地址" align="center" width="300"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>