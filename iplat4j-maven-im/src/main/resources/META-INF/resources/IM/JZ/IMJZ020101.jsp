<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询区">
		<div class="row">
			<EF:EFTreeInput ename="machineTypeId" cname="设备分类："
				serviceName="DFFL01" methodName="queryDFFLTree"
				textField="classifyName" valueField="id" hasChildren="isLeaf"
				popupTitle="设备分类" root="{id: 'root',classifyName: '分类信息'}"
				clear="true" colWidth="4" ratio="3:8" />
			<EF:EFInput ename="inqu_status-0-machineName" cname="设备名称：" colWidth="4" ratio="3:8" />
			<EF:EFInput ename="inqu_status-0-machineCode" cname="设备代码：" colWidth="4" ratio="3:8" />
		</div>
	</EF:EFRegion>
	
	<EF:EFRegion id="result" title="问题信息">
		<EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" hidden="true" align="center" />
			<EF:EFColumn ename="paramKey" cname="设备代码" enable="false" align="center" />
			<EF:EFColumn ename="paramName" cname="设备名称" enable="false" align="center" />
			<EF:EFColumn ename="classifyName" cname="安装地点" enable="false" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

