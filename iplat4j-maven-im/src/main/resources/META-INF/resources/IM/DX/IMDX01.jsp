<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="安全巡查对象管理">
	<EF:EFRegion id="inqu" title="查询区">
			<EF:EFInput ename="inqu_status-0-objName" cname="巡查对象:" colWidth="5" ratio="4:8"/>
			<EF:EFInput ename="inqu_status-0-spotName" cname="巡查地点名称:" colWidth="5" ratio="4:8"/>
			<EF:EFInput ename="inqu_status-0-spotCode" cname="巡查地点编码:" colWidth="5" ratio="4:8"/>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="数据集" fitHeight="true" >
		<EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="id" width="150" cname="对象id" enable="false" hidden="true"/>
			<EF:EFColumn ename="objName" width="150" cname="巡查对象" enable="false" align="center"/>
			<EF:EFColumn ename="spotName" width="150" cname="巡查地点" enable="false" align="center"/>
			<EF:EFColumn ename="spotCode" width="150" cname="巡查地点编码" enable="false" align="center"/>
			<EF:EFColumn ename="objRemark" width="150" cname="备注" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="600" height="550" title="对象信息管理" modal="true" />
</EF:EFPage>