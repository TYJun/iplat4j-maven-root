<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>离职登记</title>
<EF:EFPage>
	<EF:EFRegion id="result" title="人员配置信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="false" readonly="true" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" align="center" enable="false"/>
			<EF:EFColumn ename="realName" cname="姓名" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="workNo" cname="工号" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="sex" cname="性别" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="deptNum" cname="所属部门" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="serviceDeptNum" cname="服务部门" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="managementDeptNum" cname="管理部门" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="inTime" cname="入职时间" width="150" align="center" enable="false"/>
			<EF:EFColumn ename="preOutTime" cname="预离职时间" editType="date" dateFormat="yyyy-MM-dd"
						 parseFormats="['yyyy-MM-dd']" width="150" readonly="true" required="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popDataPerson" title="人员选择" url=" " lazyload="true" width="90%" height="92%"></EF:EFWindow>