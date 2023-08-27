<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row" style="height: 1px;">
			<%-- <EF:EFInput ename="inqu_status-0-cardcode" cname="卡片代码:" /> --%>
			<EF:EFInput ename="inqu_status-0-cardname" cname="卡片名称:" />
		</div>
	</EF:EFRegion>
		<EF:EFRegion id="result" title="卡片">
			<EF:EFGrid blockId="result" autoDraw="no" checkMode="single"
				refresh="true">
				<EF:EFColumn ename="cardid" cname="cardid" align="center"
					readonly="true" enable="false" hidden="true" />
				<EF:EFColumn ename="cardcode" cname="卡片代码" align="center"
					readonly="true" enable="false" />
				<EF:EFColumn ename="cardname" cname="卡片名称" align="center"
					readonly="true" enable="false" />
			</EF:EFGrid>
		</EF:EFRegion>
		<div class="button-region" id="buttonDiv" style="float: right">
		   <EF:EFButton cname="保存" ename="SAVE" layout="0" ></EF:EFButton>
	   </div>
</EF:EFPage>
