<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="contTypeName" cname="合同条款名称" colWidth="4" ratio="3:8"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="multiple,row">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="contTermName" cname="合同条款" width="150" />
			<EF:EFColumn ename="remark" cname="条款内容" width="150" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">
	
</script>




