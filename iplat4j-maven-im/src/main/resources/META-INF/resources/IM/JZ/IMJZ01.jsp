<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="安全巡查基准管理">
	<EF:EFRegion id="inqu" title="查询区">
		<div class="row">
			<EF:EFInput ename="schemeCode" cname="基准代码:" />
			<EF:EFInput ename="schemeName" cname="基准名称:" />
			<EF:EFInput ename="schemeDept" cname="地点:" />
		</div>
		<div class="row">
			<%-- <EF:EFInput ename="objId" cname="对象ID" /> --%>
			<EF:EFInput ename="objName" cname="对象名称:" />
			<EF:EFSelect cname="状态:" ename="status" >
				<EF:EFOption label="全部" value="" />
				<EF:EFOption label="新建" value="0" />
				<EF:EFOption label="启用" value="1" />
				<EF:EFOption label="禁用" value="-1" />
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="数据集" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="IMJZ01" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="schemeID" cname="主键" hidden="true" align="center" width="100"/>
			<EF:EFColumn ename="schemeCode" cname="基准代码" readonly="true" align="center" width="80"/>
			<EF:EFColumn ename="schemeName" cname="基准名称" readonly="true" align="center" width="150"/>
			<EF:EFColumn ename="objName" cname="对象名称" readonly="true" align="center" width="80"/>
			<EF:EFColumn ename="spotName" cname="地点" readonly="true" align="center" width="150"/> 
			<EF:EFColumn ename="status" cname="状态" readonly="true" align="center" width="40" hidden="true"/>
			<EF:EFColumn ename="statusName" cname="状态" readonly="true" align="center" width="40"/>
			<EF:EFColumn ename="createTime" cname="创建时间" readonly="true" align="center" width="80"/>
			<EF:EFColumn ename="createMan" cname="创建人" readonly="true" align="center" width="80"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="95%" height="95%" title="安全巡查基准新增" modal="true" />
	<EF:EFWindow id="popDataModify" url="" lazyload="true" width="95%" height="95%" title="安全巡查基准修改" modal="true" />
</EF:EFPage>