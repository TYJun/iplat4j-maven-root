<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 订餐公告 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="groupName" cname="分组名称" />
			<EF:EFInput ename="deptName" cname="科室名称" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single"
				   readonly="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="groupNum" cname="分组编码" width="100" hidden="true" />
			<EF:EFColumn ename="groupName" cname="分组名称" width="100" />
			<EF:EFColumn ename="deptNum" cname="科室编码" width="100" hidden="true" />
			<EF:EFColumn ename="deptName" cname="科室名称" width="100" />
			<EF:EFColumn ename="createTime" cname="创建时间" width="100" hidden="true" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="50%"
			 height="60%">
</EF:EFWindow>