<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
	
			<EF:EFDatePicker ename="inqu_status-0-beginDate" cname="开始日期："
				role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd"
				parseFormats="['yyyy-MM-dd']" />
				
			<EF:EFSelect ename="inqu_status-0-standardName" cname="调查考核主题：" resultId="standardName"
				textField="standardName" valueField="standardCode" serviceName="SQBZ01" colWidth="6" ratio="2:6"
				methodName="standardName" optionLabel="请选择">
			</EF:EFSelect>
			<EF:EFDatePicker ename="inqu_status-0-endDate" cname="截至日期："
				role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd"
				parseFormats="['yyyy-MM-dd']" />
			<EF:EFSelect cname="评分类型:" ename="inqu_status-0-statusCode" colWidth="6" ratio="2:6" required="true">
						<EF:EFOption label="全部" value="" />
						<EF:EFOption label="新增" value="00" />
						<EF:EFOption label="执行" value="10" />
						<EF:EFOption label="完成" value="99" />
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-billNo" cname="单号：" colWidth="4" ratio="4:8"/>
			
			
	</EF:EFRegion>
	<EF:EFRegion id="result" title="问卷信息">
		<EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false" align="center" />
			<EF:EFColumn ename="beginDate" cname="开始时间" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="endDate" cname="结束时间" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="assessTypeName" cname="类型" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="billNo" cname="单号" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="standardName" cname="主题" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="assessRange" cname="问卷范围" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="statusCode" cname="状态" width="100" enable="false" align="center"/>
			<%-- <EF:EFColumn ename="totalPoints" cname="满分" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="score" cname="均分" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="efficaciousNum" cname="有效抽样数" width="100" enable="false" align="center"/> --%>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="600" height="330" title="新增问卷" modal="true" />
</EF:EFPage>

