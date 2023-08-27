<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="添加巡查对象">
	<EF:EFRegion id="inqu" title="查询区">
		<div class="row">
			<EF:EFInput ename="objName" cname="巡查对象名称:" />
			<EF:EFInput ename="spotName" cname="巡查地点:"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="数据集" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="row" serviceName="IMJZ010101">
			<EF:EFColumn ename="objId" cname="主键"  align="center" width="100" hidden="true"/>
			<EF:EFColumn ename="objName" cname="对象名称" readonly="true" align="center" width="80"/>
			<EF:EFColumn ename="objRemark" cname="对象备注" readonly="true" align="center" width="150" hidden="true"/>
			<EF:EFColumn ename="spotCode" cname="安装地点编码" readonly="true" align="center" width="150"/>
			<EF:EFColumn ename="spotName" cname="安装地点" readonly="true" align="center" width="150"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>