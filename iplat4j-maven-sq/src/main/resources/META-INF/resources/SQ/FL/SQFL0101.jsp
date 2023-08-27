<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="分组名称" showClear="true">
			<EF:EFInput ename="perGroupNo" cname="人员组编号：" colWidth="5" ratio="2:8" required="true"/>
			<EF:EFInput ename="perGroupName" cname="人员组名称：" colWidth="5" ratio="2:8" required="true"/>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="人员列表" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="SQFL0101" rowNo="true" isFloat="true" autoBind="true">
			<EF:EFColumn ename="id" cname="主键" hidden="true" align="center" />
			<EF:EFColumn ename="workNo" cname="人员工号" enable="false" align="center" />
			<EF:EFColumn ename="name" cname="人员名称" enable="false" align="center" />
			<EF:EFColumn ename="deptNum" cname="科室编码" enable="false" align="center" hidden="true"/>
			<EF:EFColumn ename="deptName" cname="科室名称" enable="false" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="90%" height="95%">
</EF:EFWindow>
