<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<%--<EF:EFSelect ename="inqu_status-0-standardName" cname="问卷主题" resultId="standardName"
				textField="standardName" valueField="standardCode" serviceName="SQBZ01"
				methodName="standardName" optionLabel="请选择">
			</EF:EFSelect>--%>
			<EF:EFInput ename="inqu_status-0-standardName" cname="问卷主题"/>
<%--			<EF:EFSelect ename="inqu_status-0-sqType" cname="问卷类型"--%>
<%--			resultId="sqType" textField="label" valueField="value"--%>
<%--			serviceName="SQBZ01" methodName="sqType" optionLabel="请选择" />--%>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="问卷信息">
		<EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="standardId" cname="standardId" width="100" enable="false" align="center" hidden="true"/>
			<EF:EFColumn ename="standardCode" cname="问卷编码" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="standardName" cname="问卷主题" width="100" enable="false" align="center"/>
<%--			<EF:EFColumn ename="assessTypeName" cname="问卷类型" width="100" enable="false" align="center"/>--%>
			<EF:EFColumn ename="remark" cname="备注" width="100" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="问卷" modal="true" />
</EF:EFPage>

