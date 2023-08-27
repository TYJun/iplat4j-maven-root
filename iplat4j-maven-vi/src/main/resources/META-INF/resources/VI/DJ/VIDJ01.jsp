<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-interviewerWorkNo" cname="被访人工号" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-interviewerName" cname="被访人姓名" type="hidden"/>
			<EF:EFDatePicker ename="inqu_status-0-vistingBeginDate" cname="访问日期" role="date"
							 format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']"/>
		</div>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
            <EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
        </div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="临时访客管理">
		<EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="id" cname="id" enable="false" align="center" hidden="true"/>
			<EF:EFColumn ename="nterviewerName" cname="被访者姓名" enable="false" align="center"/>
			<EF:EFColumn ename="visitingDeptName" cname="被访问科室" enable="false" align="center"/>
			<EF:EFColumn ename="vistingBeginDate" cname="访问日期" enable="false" align="center"/>
			<EF:EFColumn ename="createTime" cname="创建时间" enable="false" align="center"/>
			<EF:EFColumn ename="creatorType" cname="创建人类型" enable="false" align="center"/>
			<EF:EFColumn ename="creatorIdentity" cname="创建人标识" enable="false" align="center"/>
			<EF:EFColumn ename="auditorStep" cname="审批状态" enable="false" align="center" hidden="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" modal="true" />
</EF:EFPage>

