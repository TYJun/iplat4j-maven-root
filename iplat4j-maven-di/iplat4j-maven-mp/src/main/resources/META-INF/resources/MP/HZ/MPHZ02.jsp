<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>需求计划汇总确认</title>--%>

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-collectNo" cname="汇总单号"/>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="汇总日期起" readonly="true" ></EF:EFDatePicker>
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="汇总日期止" readonly="true" ></EF:EFDatePicker>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="需求汇总列表">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" checkMode="single,row" readonly="true" >
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="collectNo" cname="汇总单号" align="center"/>
			<EF:EFColumn ename="collectNum" cname="汇总总数量" align="center"/>
			<EF:EFColumn ename="collectCost" cname="汇总总金额(元)" align="center"/>
			<EF:EFColumn ename="statusName" cname="状态" align="center"/>
			<EF:EFColumn ename="recCreateTimeStr" cname="汇总时间" align="center"/>
			<EF:EFColumn ename="recCreatorName" cname="汇总人"  align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url="" lazyload="true" width="90%" height="90%" title=""/>
