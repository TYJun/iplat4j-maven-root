<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="执行人员添加">
			<EF:EFRegion id="inqu" title="查询区" showClear="true">
				<div class="row">
					<EF:EFInput ename="deptName" cname="单位部门" />
					<EF:EFInput ename="workNo" cname="工号" />
					<EF:EFInput ename="userName" cname="姓名" />
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="数据集" fitHeight="true">
				<EF:EFGrid blockId="result" autoDraw="no" serviceName="DKJZ010104">
					<EF:EFColumn ename="id" cname="主键" hidden="true" align="center" width="100"/>
					<EF:EFColumn ename="workNo" cname="manId" hidden="true" align="center" width="100"/>
					<EF:EFColumn ename="workNo" cname="工号" readonly="true" align="center" width="100"/>
					<EF:EFColumn ename="name" cname="姓名" readonly="true" align="center" width="100"/>
					<EF:EFColumn ename="deptName" cname="单位部门" readonly="true" align="center" width="200"/>
					<EF:EFColumn ename="deptNum" cname="单位部门编号"  hidden="true" align="center" width="200"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
</EF:EFPage>