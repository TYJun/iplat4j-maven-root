<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!--采购类型份额配置-->
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-purchaseYear" cname="年份" format="yyyy"/>
			<EF:EFSelect ename="inqu_status-0-purchaseType" cname="采购类别" optionLabel="请选择">
				<EF:EFCodeOption codeName="wilp.mp.purchase.purchaseType"/>
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="配置列表">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" readonly="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" align="center"/>
			<EF:EFColumn ename="purchaseYear" cname="年份" align="center"/>
			<EF:EFColumn ename="purchaseType" cname="采购类别" hidden="true" align="center" />
			<EF:EFColumn ename="purchaseTypeName" cname="采购类别名称 " align="center"/>
			<EF:EFColumn ename="purchaseCost" cname="采购份额(元)" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="32%" height="40%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>