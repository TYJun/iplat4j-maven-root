<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>人员选择</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-realName" cname="姓名" colWidth="3" ratio="3:8"/>
			<EF:EFInput ename="inqu_status-0-workNo" cname="工号" colWidth="3" ratio="3:8"/>
			<EF:EFInput ename="view" cname="调派 " colWidth="3" ratio="3:8" type="hidden"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="RESET" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="人员配置信息">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="false" readonly="true" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" align="center" enable="false"/>
			<EF:EFColumn ename="realName" cname="姓名" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="workNo" cname="工号" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="sex" cname="性别" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="deptNum" cname="所属部门" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="serviceDeptNum" cname="服务部门" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="managementDeptNum" cname="管理部门" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="inTime" cname="入职时间" width="150" align="center" enable="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
