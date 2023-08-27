<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="">
	<EF:EFRegion id="dfda" title="设备信息">
		<div class="row">
			<EF:EFInput ename="scrapNo" cname="报废单号" colWidth="4" ratio="3:8" required="true" readonly="true"/>
			<EF:EFInput ename="machineName" cname="设备名称：" colWidth="4" ratio="3:8" required="true" readonly="true"/>
			<EF:EFInput ename="fixedPlace" cname="安装地点" colWidth="4" ratio="3:8" readonly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="scrapWay" cname="报废方式" colWidth="4" ratio="3:8" readonly="true"/>
			<EF:EFInput ename="status" colWidth="4" ratio="3:8" cname="设备状态：" readonly="true"/>
			<EF:EFInput ename="scrapDate" cname="报废日期：" colWidth="4" ratio="3:8" format="yyyy-MM-dd" readonly="true"/>
		</div>

	<div class="row">
		<EF:EFInput ename="scrapReason" cname="报废原因" colWidth="4" ratio="3:8" readonly="true"/>
	</div>
	</EF:EFRegion>
</EF:EFPage>