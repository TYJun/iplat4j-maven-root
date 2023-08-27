<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="设备参数管理">
	<div class="row">
		<div class="col-md-2">
			<EF:EFRegion id="tree" title="设备系统分类" fitHeight="true">
				<EF:EFTree id="menu" valueField="id" textField="classifyName" hasChildren="isLeaf" 
				serviceName="DFFL10" methodName="queryDFFLTree" style="height:560px;"/>
			</EF:EFRegion>
		</div>
		<div class="col-md-10">
			<EF:EFRegion id="inqu" title="查询区" showClear="true">
				<div class="row">
					<EF:EFInput ename="queryModuleId" cname="设备分类编码" type="hidden"/>
					<EF:EFInput ename="queryClassifyName" cname="分类名称" />
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="数据集" fitHeight="true">
				<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="false"
				checkMode="single,row" readonly="false" rowNo="true" isFloat="true" serviceName="DFCS01">
					<EF:EFColumn ename="id" cname="主键" hidden="true"/>
					<EF:EFColumn ename="paramKey" cname="参数编码" readonly="true"/>
					<EF:EFColumn ename="paramName" cname="参数名称" readonly="true"/>
					<EF:EFColumn ename="classifyName" cname="分类名称" readonly="true"/>
					<EF:EFColumn ename="paramValue" cname="参数值" readonly="true"/>
					<EF:EFColumn ename="paramUnit" cname="参数单位" readonly="true"/>
					<EF:EFColumn ename="memo" cname="备注" readonly="true"/>
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="popData" url="" lazyload="true" width="40%" height="42%" title="设备参数" modal="true" />
		</div>
	</div>
</EF:EFPage>