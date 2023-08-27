<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<title>校外入住登记</title>
<EF:EFPage title="校外入住登记">
	<EF:EFRegion id="inqu" title="查询区" showClear="true">
		<div class="row">
			<EF:EFInput ename="manNo" cname="工号" />
			<EF:EFInput ename="manName" cname="姓名" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="申请信息" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DMXW01" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="id" cname="id" readonly="true" hidden="true"   />
			<EF:EFColumn ename="manNo" cname="工号" readonly="true" width="80" align="center"/>
			<EF:EFColumn ename="manName" cname="姓名" readonly="true" width="70" align="center"/>
			<EF:EFColumn ename="sexMeaning" cname="性别" readonly="true" width="50" align="center"/>
			<EF:EFColumn ename="age" cname="年龄" readonly="true"  width="50" align="center"/>
			<EF:EFColumn ename="phone" cname="联系电话" readonly="true" width="80" align="center"/>
			<EF:EFColumn ename="nowAddress" cname="当前所在地" readonly="true" width="300" align="left"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="60%" height="90%" title="校外登记" modal="true" />
	<EF:EFWindow id="popDataModify" url="" lazyload="true" width="60%" height="90%" title="信息编辑" modal="true" />
	<EF:EFWindow id="popDatashow" url="" lazyload="true" width="60%" height="90%" title="查看详情" modal="true" />
</EF:EFPage>