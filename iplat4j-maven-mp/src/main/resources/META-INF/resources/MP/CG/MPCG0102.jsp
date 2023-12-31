<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>物资选择</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="物资选择" height="200" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-matTypeName" cname="物资分类名称" />
			<EF:EFInput ename="inqu_status-0-matNum" cname="物资编码" />
			<EF:EFInput ename="inqu_status-0-matName" cname="物资名称" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="物资列表">
		<EF:EFGrid blockId="mat" autoDraw="no" checkMode="row" autoBind="true" readonly="true">
			<EF:EFColumn ename="pictureUri" cname="图片" align="center" width="50" />
			<EF:EFColumn ename="id" cname="id" hidden="true" />
			<EF:EFColumn ename="matNum" cname="物资编码" align="center" />
			<EF:EFColumn ename="matName" cname="物资名称" align="center" />
			<EF:EFColumn ename="matTypeId" cname="物资分类编码" hidden="true" />
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" />
			<EF:EFColumn ename="matSpec" cname="物资规格" align="center" />
			<EF:EFColumn ename="matModel" cname="物资型号" align="center" />
			<EF:EFColumn ename="unit" cname="计量单位" hidden="true" />
			<EF:EFColumn ename="unitName" cname="计量单位" align="center" />
			<EF:EFColumn ename="price" cname="参考单价" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>