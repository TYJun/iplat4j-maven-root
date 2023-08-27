<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
	<EF:EFRegion id="inqu" title="用户信息" showClear="true">
			<div class="row">
				<EF:EFInput ename="perId" cname="员工ID" type="hidden"  />
				<EF:EFInput ename="perWorkNo" cname="工号" readOnly="true"   />
				<EF:EFInput ename="perName" cname="员工名" readOnly="true"   />
			</div>
		</EF:EFRegion>
    <EF:EFRegion id="result" title="角色列表">
			<EF:EFGrid blockId="result" autoDraw="no" serviceName="AURU0102"  readonly="true">
				<EF:EFColumn ename="roleName" cname="角色名称" width="100"  />
				<EF:EFColumn ename="remark" cname="备注信息" width="100"  />
			</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>