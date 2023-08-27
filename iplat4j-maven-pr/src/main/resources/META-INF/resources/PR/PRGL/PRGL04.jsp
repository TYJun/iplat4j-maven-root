<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row" style="height: 10px;">
			<EF:EFDatePicker ename="inqu_status-0-beginDate" cname="登记开始时间:"
				role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd"
				parseFormats="['yyyy-MM-dd']" />
			<EF:EFDatePicker ename="inqu_status-0-endDate" cname="登记截至时间:"
				role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd"
				parseFormats="['yyyy-MM-dd']" />
			<EF:EFSelect ename="inqu_status-0-localTypeCode" cname="问题分类:"
						 resultId="saftyType" textField="label" valueField="value"
						 serviceName="PRGL03" methodName="safty" optionLabel="请选择" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="挂账问题">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row">
			<EF:EFColumn ename="id" cname="id" width="80" enable="false" hidden="true"/>
			<EF:EFColumn ename="dangerid" cname="dangerid" width="80" enable="false" hidden="true"/>
			<EF:EFColumn ename="statusCode" cname="流程状态" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="dangercode" cname="编号" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="typeName" cname="问题大类" width="70" enable="false" align="center"/>
			<EF:EFColumn ename="dangerwhere" cname="问题地点" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="contentdesc" cname="问题描述" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="createman" cname="问题登记人" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="createtime" cname="登记日期" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="requiredesc" cname="整改要求" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="requiredtime" cname="整改要求完成时间" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="typeName" cname="问题类型" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="realName" cname="挂账人" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="logsTime" cname="挂账时间" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="rejectReason" cname="挂账原因" width="80" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="50%"
	height="80%">
</EF:EFWindow>

