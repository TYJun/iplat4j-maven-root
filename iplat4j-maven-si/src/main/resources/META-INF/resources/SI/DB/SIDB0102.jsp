<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>物资选择</title>
<EF:EFPage>
		<EF:EFRegion id="inqu" title="物资选择" height="200">
			<div class="row" style="height: 40px; line-height: 40px">
				<EF:EFInput ename="inqu_status-0-matName" cname="物资名称" />
				<EF:EFTreeInput ename="inqu_status-0-matTypeNum" cname="物资分类" 
					serviceName="SIKC01" methodName="getMateriaType" textField="text"
					valueField="label" hasChildren="leaf" root="{label: 'root',text: '分类'}"
					popupTitile="上级分类" clear="true" placeholder="请选择">
				</EF:EFTreeInput>
				<EF:EFInput type="hidden" ename="inqu_status-0-outWareHouseNo" cname="仓库号" />
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="物质列表">
			<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row" readonly="true" height="330"
				sort="single">
				<EF:EFColumn ename="matNum" cname="物资编码" width="60" align="center" sort="true"/>
				<EF:EFColumn ename="matName" cname="物资名称" width="60" align="center" sort="true"/>
				<EF:EFColumn ename="matSpec" cname="物资规格" width="60" align="center" sort="true"/>
				<EF:EFColumn ename="matModel" cname="物资型号" width="60" align="center" sort="false"/>
				<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true" sort="false"/>
				<EF:EFColumn ename="matTypeName" cname="物资分类" width="60" align="center" sort="false"/>
				<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true"  sort="false"/>
				<EF:EFColumn ename="unitName" cname="物资单位" width="60" align="center" sort="false"/>
				<EF:EFColumn ename="totalNum" cname="当前数量" width="60" align="center" sort="true"/>
			</EF:EFGrid>
		</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>