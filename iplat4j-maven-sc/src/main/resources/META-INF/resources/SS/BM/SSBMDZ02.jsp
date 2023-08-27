<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 订餐公告 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="building" cname="楼" />
			<EF:EFInput ename="floor" cname="层" />
<%--			<EF:EFSelect ename="inqu_status-0-typeCode" cname="地址"--%>
<%--				optionLabel="请选择" colWidth="4" ratio="3:8">--%>
<%--				<EF:EFOptions blockId="noticeType" textField="typeName" valueField="typeCode" />--%>
<%--			</EF:EFSelect>--%>
			<EF:EFInput ename="deptName" cname="地址" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single"
				   readonly="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="building" cname="楼" width="100" />
			<EF:EFColumn ename="floor" cname="层" width="100" />
			<EF:EFColumn ename="deptName" cname="地址" width="100" />
			<EF:EFColumn ename="takeEffect" cname="状态" width="100" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="50%"
			 height="60%">
</EF:EFWindow>