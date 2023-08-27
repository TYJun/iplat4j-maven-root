<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<EF:EFInput ename="batchNo" cname="问卷号" colWidth="4" ratio="4:8" readonly="true"/>
		<EF:EFInput ename="workName" cname="答卷人：" colWidth="4" ratio="4:8"/>
		<EF:EFInput ename="projectName" cname="项目：" colWidth="4" ratio="4:8"/>
		<EF:EFInput ename="deptName" cname="所属科室：" colWidth="4" ratio="4:8"/>
		<EF:EFSelect cname="是否过滤无意见" ename="advice" colWidth="4" ratio="4:8" required="true">
			<EF:EFOption label="是" value="无意见" />
			<EF:EFOption label="否" value="" />
		</EF:EFSelect>
		<EF:EFSelect cname="是否过滤满意率为100%" ename="percent" colWidth="4" ratio="4:8" required="true">
			<EF:EFOption label="是" value="100.0%" />
			<EF:EFOption label="否" value="" />
		</EF:EFSelect>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="批次信息">
		<EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true" sumType="all">
			<EF:EFColumn ename="sqManageScoreId" cname="id" width="100" hidden="true" enable="false" align="center"/>
			<EF:EFColumn ename="batchNo" cname="批次号" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="projectName" cname="项目" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="itemName" cname="题目" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="workName" cname="答卷人" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="deptName" cname="所属科室" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="score" cname="得分" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="point" cname="有效总分" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="percent" cname="满意度" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="advice" cname="意见或建议" width="100" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

