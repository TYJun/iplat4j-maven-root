<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="contTypeName" cname="合同条款名称" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="合同条款定义">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" align="center"/>
			<EF:EFColumn ename="contTermNum" cname="合同条款编码" width="100" align="center"/>
			<EF:EFColumn ename="contTermName" cname="合同条款名称" width="100" align="center"/>
			<EF:EFColumn ename="remark" cname="合同条款内容" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 弹窗配置 -->
<EF:EFWindow id="popData" url="" lazyload="true" width="40%" height="35%" title="查看合同条款定义"/>

<script type="text/javascript">
	
</script>