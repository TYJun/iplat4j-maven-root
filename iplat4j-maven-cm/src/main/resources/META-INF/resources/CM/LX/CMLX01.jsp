<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="contTypeName" cname="合同类型名称" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="合同类型定义">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
		    <EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="contTypeNum" cname="合同类型编码" width="100" align="center"/>
			<EF:EFColumn ename="contTypeName" cname="合同类型名称" width="100" align="center"/>
			<EF:EFColumn ename="payType" cname="合同付款方向" width="100" align="center"/>
			<EF:EFColumn ename="remark" cname="备注" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 弹窗配置 -->
<EF:EFWindow id="popData" url="" lazyload="true" width="45%" height="50%" title="新增合同类型定义"/> 
