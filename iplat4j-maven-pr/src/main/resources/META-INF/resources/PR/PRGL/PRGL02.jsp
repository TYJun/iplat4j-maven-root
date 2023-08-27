<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<EF:EFDatePicker ename="inqu_status-0-beginDate" cname="登记开始时间:"
			role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd HH:mm:ss"
			parseFormats="['yyyy-MM-dd HH:mm:ss']" />
		<EF:EFDatePicker ename="inqu_status-0-endDate" cname="登记截至时间:"
			role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd HH:mm:ss"
			parseFormats="['yyyy-MM-dd HH:mm:ss']" />
		<EF:EFSelect ename="inqu_status-0-localTypeCode" cname="问题分类:"
					 resultId="saftyType" textField="label" valueField="value"
					 serviceName="PRGL03" methodName="safty" optionLabel="请选择" />
	</EF:EFRegion>
	<EF:EFRegion id="result" title="问题信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row"
			readonly="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
			<EF:EFColumn ename="statusCode" cname="statusCode" width="100" hidden="true"/>
			<EF:EFColumn ename="contentType" cname="流程状态" width="100" align="center"/>
			<EF:EFColumn ename="dangercode" cname="编号" width="100" align="center"/>
			<EF:EFColumn ename="typename" cname="问题分类" width="100" align="center"/>
			<EF:EFColumn ename="contentdesc" cname="问题描述" width="100" align="center"/>
			<EF:EFColumn ename="creatorName" cname="问题登记人" width="100" align="center"/>
			<EF:EFColumn ename="createtime" cname="登记日期" width="100" align="center"/>
			<EF:EFColumn ename="requiredesc" cname="整改要求" width="100" align="center"/>
			<EF:EFColumn ename="requiredtime" cname="整改要求完成时间" width="200" align="center"/>
			<EF:EFColumn ename="dangerlevel" cname="问题级别" width="100" align="center"/>
			<EF:EFColumn ename="rejectReason" cname="备注" width="100" align="center"/>

		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

<EF:EFWindow id="popData" url=" " lazyload="true" width="70%"
	height="70%">
</EF:EFWindow>
<EF:EFWindow id="popData2" url=" " lazyload="true" width="50%"
	height="80%">
</EF:EFWindow>
<EF:EFWindow id="popData3" url=" " lazyload="true" width="50%"
	height="20%">
</EF:EFWindow>