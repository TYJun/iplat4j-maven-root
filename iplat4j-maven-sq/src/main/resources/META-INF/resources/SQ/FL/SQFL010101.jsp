<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="分组名称" showClear="true">
<%--			<EF:EFSelect ename="dept" cname="科室名称：" resultId="dept" colWidth="5" ratio="2:8"--%>
<%--				textField="deptName" valueField="deptNum" serviceName="SQFL010101"--%>
<%--				methodName="queryDept" optionLabel="请选择" filter="contains">--%>
<%--			</EF:EFSelect>--%>
			<EF:EFInput ename="inqu_status-0-name" cname="人员名称：" colWidth="5" ratio="2:8" />
		    <EF:EFInput ename="inqu_status-0-post" cname="职务：" colWidth="5" ratio="2:8" />
		    <EF:EFInput ename="inqu_status-0-deptPost" cname="默认职务：" colWidth="5" ratio="2:8" />
	</EF:EFRegion>
	<EF:EFRegion id="result" title="人员列表">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" height="400" readonly="true" serviceName="SQFL010101" queryMethod="query"
			checkMode="multiple,row">
			<EF:EFColumn ename="workNo" cname="人员工号" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="name" cname="人员名称" width="200" enable="false" align="center"/>
			<EF:EFColumn ename="deptNum" cname="科室编码" width="200" enable="false" align="center" hidden="true"/>
			<EF:EFColumn ename="deptName" cname="科室名称" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="deptPost" cname="默认科室职务" enable="false" align="center" />
			<EF:EFColumn ename="post" cname="职务" enable="false" align="center" />
			<EF:EFColumn ename="userId" cname="是否是科室负责人" enable="false" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData1" url=" " lazyload="true" width="90%" height="90%"/>	