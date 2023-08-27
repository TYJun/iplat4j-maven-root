<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-perGroupNo" cname="人员组编号" />
			<EF:EFInput ename="inqu_status-0-perGroupName" cname="人员组名称" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="人员组信息" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false" align="center" />
			<EF:EFColumn ename="perGroupNo" cname="人员组编号" width="100" enable="false" align="center" displayType="url"/>
			<EF:EFColumn ename="perGroupName" cname="人员组名称" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="createtime" cname="创建时间" width="100" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="50%" height="90%" title="人员组列表" modal="true" />
</EF:EFPage>

