<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="录入设备参数">
	<EF:EFRegion id="register" title="录入" fitHeight="true">
		<div class="row">
			<EF:EFTreeInput ename="machineTypeId" cname="设备分类" 
				serviceName="DFFL10" methodName="queryDFFLTree" textField="classifyName"
				valueField="id" hasChildren="isLeaf" popupTitle="设备分类"
				root="{id: 'root',classifyName: '分类信息'}" clear="true" readonly="true"
				colWidth="6" ratio="4:8" required="true" treeHeight="180">
			</EF:EFTreeInput>
			<EF:EFInput ename="moduleId" cname="设备系统分类编码" colWidth="6" type="hidden"/>
			<EF:EFInput ename="classifyName" cname="设备系统分类编码" colWidth="6" type="hidden"/>
			<EF:EFInput ename="paramName" cname=" 参数名称" colWidth="6" type="text" required="true"/>
			<EF:EFInput ename="paramValue" cname="参数值" colWidth="6" type="text"/>
			<EF:EFInput ename="paramUnit" cname="参数单位" colWidth="6" type="text"/>
			<EF:EFInput ename="memo" cname="备注" colWidth="6" type="textarea"/>
		</div>
	</EF:EFRegion>
</EF:EFPage>