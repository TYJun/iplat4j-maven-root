<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>临时访客综合查询</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<div id="search" style="display: none">
				<EF:EFInput ename="inqu_status-0-nterviewerName" cname="被访者姓名" colWidth="3" ratio="4:8"/>
				<EF:EFInput ename="inqu_status-0-visitingDeptName" cname="被访问科室" colWidth="3" ratio="4:8"/>
			</div>
			<EF:EFDateSpan startName="inqu_status-0-vistingBeginDateS" startCname="访问日期起"
						   endName="inqu_status-0-vistingBeginDateE" endCname="访问日期止" ratio="3:3" startRatio="4:8" endRatio="4:8"/>
			<EF:EFSelect ename="inqu_status-0-auditorStep" cname="审批状态" colWidth="3">
				<EF:EFOption label="请选择" value=""/>
				<EF:EFOption label="通过" value="1"/>
				<EF:EFOption label="待审批" value="0"/>
				<EF:EFOption label="驳回" value="-1"/>
			</EF:EFSelect>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="RESET" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="来访信息">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				   checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="visitId" cname="id" width="100" hidden="true"/>
			<EF:EFColumn ename="nterviewerName" cname="被访者姓名" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="visitingDeptName" cname="被访问科室" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="vistingBeginDate" cname="访问日期" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="createTime" cname="创建时间" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="auditTime" cname="审批时间" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="auditorMemo" cname="审批意见" width="110" align="center" enable="false"/>
			<EF:EFColumn ename="auditorStepMean" cname="审批状态" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="creatorIdentity" cname="创建标识" width="100" align="center" enable="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="" url=" " lazyload="true" width="100%" height="100%"></EF:EFWindow>
