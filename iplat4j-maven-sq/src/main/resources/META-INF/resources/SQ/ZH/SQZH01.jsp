<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<EF:EFSelect ename="inqu_status-0-standardName" cname="调查考核主题：" resultId="standardName"
					 textField="standardName" valueField="standardCode" serviceName="SQBZ01" colWidth="4" ratio="4:8"
					 methodName="standardName" optionLabel="请选择">
		</EF:EFSelect>
		<EF:EFDatePicker ename="inqu_status-0-beginDate" cname="开始日期："
						 role="date" colWidth="3" ratio="4:8" format="yyyy-MM-dd"
						 parseFormats="['yyyy-MM-dd']" />
		<EF:EFDatePicker ename="inqu_status-0-endDate" cname="截至日期："
						 role="date" colWidth="3" ratio="4:8" format="yyyy-MM-dd"
						 parseFormats="['yyyy-MM-dd']" />
		<EF:EFSelect cname="状态：" ename="inqu_status-0-statusCode" colWidth="2" ratio="4:8" >
			<EF:EFOption label="全部" value="" />
			<%-- <EF:EFOption label="新增" value="00" /> --%>
			<EF:EFOption label="执行" value="10" />
			<EF:EFOption label="完成" value="99" />
		</EF:EFSelect>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="问卷信息">
		<EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true" sumType="all">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false" align="center"/>
			<EF:EFColumn ename="batchNo" cname="批次号" width="100" enable="false" align="center" displayType="url"/>
			<EF:EFColumn ename="standardName" cname="主题" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="beginDate" cname="开始时间" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="endDate" cname="结束时间" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="assessTypeName" cname="类型" width="100" enable="false" align="center" hidden="true"/>
			<EF:EFColumn ename="assessRange" cname="问卷范围" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="statusCode" cname="状态" width="100" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="批次信息" modal="true" />
</EF:EFPage>

