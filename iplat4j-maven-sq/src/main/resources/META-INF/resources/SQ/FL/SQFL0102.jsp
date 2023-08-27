<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="人员列表" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" isFloat="true" height="true" readonly="true" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="workNo" cname="人员工号" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="name" cname="人员名称" width="200" enable="false" align="center"/>
			<EF:EFColumn ename="deptNum" cname="科室编码" width="200" enable="false" hidden="true" align="center"/>
			<EF:EFColumn ename="deptName" cname="科室名称" width="100" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="90%" height="90%">
</EF:EFWindow>
