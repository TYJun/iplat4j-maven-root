<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>物资选择</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="物资选择" height="200" showClear="true">
		<div class="row" style="height: 40px; line-height: 40px">
			<EF:EFInput ename="matName" cname="物资名称" />
			<EF:EFTreeInput ename="matTypeNum" cname="物资分类" bindId="matTypeNum"
				serviceName="CIKC01" methodName="getMateriaType" textField="text"
				valueField="label" hasChildren="leaf" root="{label: 'root',text: '分类'}" 
				popupTitile="上级分类" clear="true" placeholder="请选择">
			</EF:EFTreeInput>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="物质列表">
		<EF:EFGrid blockId="result" autoDraw="no" readonly="true" height="350">
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="150" align="center"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" width="100" align="center"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center"/>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" align="center"/>
			<EF:EFColumn ename="unitName" cname="物资单位" width="60" align="center"/>
			<EF:EFColumn ename="price" cname="物资价格" width="60" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>