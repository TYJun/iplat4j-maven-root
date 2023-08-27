<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="保养项目">
	    <EF:EFRegion id="inqu" title="查询区">
		  <div class="row">
			<EF:EFInput ename="id" cname="项目 id"  type="hidden"  colWidth="3"/>
			<EF:EFInput ename="itemName" cname="保养项目 " ratio="2:4"  colWidth="3"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="数据集" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DKKP010101">
			<EF:EFColumn ename="id" cname="项目id " readonly="true" hidden="true"  width="100"/>
			<EF:EFColumn ename="content" cname="保养项目 " readonly="true"  width="150"/>
			<EF:EFColumn ename="projectDesc" cname="项目描述" readonly="true"  width="50"/>
			<EF:EFColumn ename="actualValueUnit" cname="实际值单位 " readonly="true"  width="100"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>