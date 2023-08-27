<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>污水站综合数据查询页面</title>
<EF:EFPage title="污水站综合数据电子台账">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFDatePicker ename="startTime" cname="记录日期起" role="date"
				colWidth="4" ratio="3:9" format="yyyy-MM-dd"
				parseFormats="['yyyy-mm-dd']" />
			<EF:EFDatePicker ename="endTime" cname="记录日期止" role="date"
				colWidth="4" ratio="3:9" format="yyyy-MM-dd"
				parseFormats="['yyyy-mm-dd']" />
		</div>
		<div class="row">
			<EF:EFInput ename="typeChoose" colWidth="4" ratio="3:9" cname="分类" />
			<EF:EFInput ename="spotName" colWidth="4" ratio="3:9" cname="点位名称" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="数据列表">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" readonly="true" serviceName="MXXJ01" checkMode="single,row">
			<EF:EFColumn ename="taskNo" cname="点位编码" align="center"/>
			<EF:EFColumn ename="showStatusCode" cname="点位名称" align="center"/>
			<EF:EFColumn ename="timeDiffNow" cname="运行情况" align="center"/>
    		<EF:EFColumn ename="reqStaffName" cname="活性氧余量" align="center"/>
    		<EF:EFColumn ename="reqTelNum" cname="PH值" align="center"/>
			<EF:EFColumn ename="reqDeptName" cname="记录时间" align="center"/>
			<EF:EFColumn ename="itemName" cname="数据备注" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>