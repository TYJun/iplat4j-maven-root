<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="workNo" cname="员工工号" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="name" cname="员工姓名" colWidth="4" ratio="3:8"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="multiple,row" readonly="true" rowNo="true" isFloat="true" serviceName="CMDJ0103" methodName="query">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false" align="center"/>
			<EF:EFColumn ename="workNo" cname="工号" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="name" cname="姓名" width="150" enable="false" align="center"/>
			<EF:EFColumn ename="contactTel" cname="联系电话" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="deptNum" cname="科室编码" width="150" hidden="true" enable="false" align="center"/>
			<EF:EFColumn ename="deptName" cname="科室名称" width="150" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

</script>