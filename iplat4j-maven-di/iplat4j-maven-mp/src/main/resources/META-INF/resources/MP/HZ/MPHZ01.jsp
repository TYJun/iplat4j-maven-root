<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>需求计划汇总</title>

<EF:EFPage title="需求计划汇总">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-deptNum" cname="科室编码" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-collectNo" cname="汇总单号"/>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态" >
				<EF:EFOption label="全部" value="" />
				<EF:EFCodeOption codeName="wilp.mp.require.collectStatus"/>
			</EF:EFSelect>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="汇总日期起" readonly="true" ></EF:EFDatePicker>
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="汇总日期止" readonly="true" ></EF:EFDatePicker>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="需求汇总列表">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" checkMode="single,row" readonly="true" >
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="collectNo" cname="汇总单号" align="center" displayType="url"/>
			<EF:EFColumn ename="collectNum" cname="汇总总数量" align="center"/>
			<EF:EFColumn ename="collectCost" cname="汇总总金额(元)" align="center"/>
			<EF:EFColumn ename="statusName" cname="状态" align="center"/>
			<EF:EFColumn ename="recCreateTimeStr" cname="汇总时间" align="center"/>
			<EF:EFColumn ename="recCreatorName" cname="汇总人"  align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<%--<EF:EFWindow id="popData" url="" lazyload="true" width="90%" height="90%" title=""/>--%>
<EF:EFWindow id="popData" url=" " lazyload="true" width="100%" height="100%"/>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>

