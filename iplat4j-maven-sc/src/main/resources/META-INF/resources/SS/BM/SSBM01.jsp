<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="typeName" cname="员工类型:" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库档案信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single"
			readonly="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="typeCode" cname="typeCode" width="100" hidden="true" />
			<EF:EFColumn ename="mealTimeCode" cname="mealTimeCode" width="100" hidden="true" />
			<EF:EFColumn ename="typeName" cname="员工类型" width="100" />
			<EF:EFColumn ename="mealTimeName" cname="餐次名称" width="100" />
			<EF:EFColumn ename="menuName" cname="优惠套餐名称" width="100" />
			<EF:EFColumn ename="discountAmount" cname="优惠金额" width="100" />
			<EF:EFColumn ename="recCreater" cname="是否启用" width="100" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

<EF:EFWindow id="popData" url=" " lazyload="true" width="50%"
	height="60%">

</EF:EFWindow>
