<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!--采购科室配置-->
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" />
			<EF:EFInput ename="inqu_status-0-matTypeName" cname="物资分类名称" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="采购科室配置列表">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" readonly="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="deptName" cname="科室名称" displayType="url" />
			<EF:EFColumn ename="matTypeName" cname="物资分类名称 " />
			<EF:EFColumn ename="remark" cname="备注" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="32%" height="40%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>