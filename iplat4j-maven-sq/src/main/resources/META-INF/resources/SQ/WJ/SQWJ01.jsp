<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFDateSpan startName="inqu_status-0-beginDate" startCname="开始日期"
						   endName="inqu_status-0-endDate" endCname="截至日期"
						   colWidth="4" startRatio="4:8" endRatio="4:8" readonly="true"/>
			<%--<EF:EFDatePicker ename="inqu_status-0-beginDate" cname="开始日期"
				role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd"
				parseFormats="['yyyy-MM-dd']" />
			<EF:EFDatePicker ename="inqu_status-0-endDate" cname="截至日期"
							 role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd"
							 parseFormats="['yyyy-MM-dd']" />--%>
				<%--<EF:EFSelect ename="inqu_status-0-standardName" cname="调查考核主题" resultId="standardName"
                    textField="standardName" valueField="standardCode" serviceName="SQBZ01" colWidth="6" ratio="2:6"
                    methodName="standardName" optionLabel="请选择">
                </EF:EFSelect>--%>
				<%-- <EF:EFSelect cname="评分类型:" ename="inqu_status-0-statusCode" colWidth="6" ratio="2:6" required="true">
                            <EF:EFOption label="全部" value="" />
                            <EF:EFOption label="新增" value="00" />
                            <EF:EFOption label="执行" value="10" />
                            <EF:EFOption label="完成" value="99" />
                </EF:EFSelect> --%>
				<%--<EF:EFInput ename="inqu_status-0-billNo" cname="单号" colWidth="6" ratio="2:6"/>--%>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="问卷信息">
		<EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="row" rowNo="true">
			<EF:EFColumn ename="manageId" cname="manageId" width="100" hidden="true" enable="false" align="center" />
			<EF:EFColumn ename="batchNo" cname="批次号" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="standardName" cname="问卷主题" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="assessRange" cname="调查范围" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="workName" cname="问卷负责人" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="beginDate" cname="开始时间" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="endDate" cname="结束时间" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="isCycle" cname="是否周期生成" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="statusCode" cname="问卷状态" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="parentsName" cname="上级问卷" width="100" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="40%" height="60%" title="维护问卷" modal="true" />
	<EF:EFWindow id="popData2" url="" lazyload="true" width="40%" height="60%" title="关联问卷" modal="true" />
</EF:EFPage>

