<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>职工心声评价管理</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFDateSpan startName="inqu_status-0-complaintDateS" startCname="发生日期起"
						   endName="inqu_status-0-complaintDateE" endCname="发生日期止"
						   ratio="3:3" startRatio="4:8" endRatio="4:8"/>
			<EF:EFInput ename="inqu_status-0-billNo" cname="发起单号" colWidth="3" ratio="4:8"/>
			<EF:EFInput ename="inqu_status-0-complaintDept" cname="发起人部门/单位" colWidth="3" ratio="4:8"/>
			<EF:EFInput ename="inqu_status-0-complaintContent" cname="发起内容" colWidth="3" ratio="4:8"/>
			<EF:EFDateSpan startName="inqu_status-0-dealDateS" startCname="处理日期起"
						   endName="inqu_status-0-dealDateE" endCname="处理日期止"
						   ratio="3:3" startRatio="4:8" endRatio="4:8"/>
			<EF:EFInput ename="inqu_status-0-dealWorkName" cname="处理人" colWidth="3" ratio="4:8"/>
			<EF:EFInput ename="inqu_status-0-dealDept" cname="处理部门" colWidth="3" ratio="4:8"/>
			<EF:EFDateSpan startName="inqu_status-0-returnDateS" startCname="回访日期起"
						   endName="inqu_status-0-returnDateE" endCname="回访日期止"
						   ratio="3:3" startRatio="4:8" endRatio="4:8"/>
			<EF:EFInput ename="inqu_status-0-returnWorkName" cname="回访人" colWidth="3" ratio="4:8"/>
			<EF:EFInput ename="inqu_status-0-returnDesc" cname="回访情况" colWidth="3" ratio="4:8"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="职工心声信息管理">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				   checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
			<EF:EFColumn ename="billNo" cname="发起单号" width="120" align="center"/>
			<EF:EFColumn ename="statusCode" cname="单子状态" width="100" align="center"/>
			<EF:EFColumn ename="complaintDate" cname="发生日期" width="100" align="center"/>
			<EF:EFColumn ename="complaintName" cname="发起人" width="100" align="center"/>
			<EF:EFColumn ename="complaintPhone" cname="发起人电话" width="100" align="center"/>
			<EF:EFColumn ename="complaintDept" cname="发起人部门/单位" width="100" align="center"/>
			<EF:EFColumn ename="complaintType" cname="发起类型" width="100" align="center" hidden="true"/>
			<EF:EFColumn ename="complaintWay" cname="发起方式" width="100" align="center" hidden="true"/>
			<EF:EFColumn ename="complaintContent" cname="发起内容" width="100" align="center"/>
			<EF:EFColumn ename="dealWorkName" cname="处理人" width="100" align="center"/>
			<EF:EFColumn ename="dealDept" cname="处理部门" width="100" align="center"/>
			<EF:EFColumn ename="dealDate" cname="处理日期" width="100" align="center"/>
			<EF:EFColumn ename="returnWorkName" cname="回访人" width="100" align="center"/>
			<EF:EFColumn ename="returnDate" cname="回访日期" width="100" align="center"/>
			<EF:EFColumn ename="returnDesc" cname="回访情况" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 弹窗配置 -->
<EF:EFWindow id="popData" url=" " lazyload="true" width="100%" height="100%" title="发起人评价"/>
