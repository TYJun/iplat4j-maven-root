<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="contNo" cname="合同号" colWidth="4" ratio="4:8"/>
			<EF:EFInput ename="contName" cname="合同名称" colWidth="4" ratio="4:8"/>
			<EF:EFInput ename="contDeptName" cname="所属科室" colWidth="4" ratio="4:8" type="hidden"/>
			<%-- <EF:EFSelect ename="contTypeNum" cname="合同类型" colWidth="4" ratio="4:8"
				resultId="contTypeName" textField="contTypeName"
				valueField="contTypeNum" serviceName="CMDJ0101"
				methodName="getContTypeNum" optionLabel="请选择">
			</EF:EFSelect> --%>
		</div>
		<div class="row">
			<EF:EFDatePicker ename="contSignTimeS" cname="签订日期起"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" colWidth="4" ratio="4:8"/>
			<EF:EFDatePicker ename="contSignTimeE" cname="签订日期止"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" colWidth="4" ratio="4:8"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="合同信息管理">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
			<EF:EFColumn ename="contNo" cname="合同号" width="120" align="center"/>
			<EF:EFColumn ename="contName" cname="合同名称" width="100" align="center"/>
			<EF:EFColumn ename="contTypeNum" cname="合同类型" width="100" align="center"/>
			<EF:EFColumn ename="contSignTime" cname="合同签订日期" width="100" align="center"/>
			<EF:EFColumn ename="planTakeefTime" cname="计划生效日期" width="100" align="center"/>
			<EF:EFColumn ename="planFinishTime" cname="计划终止日期" width="100" align="center"/>
			<EF:EFColumn ename="currType" cname="币种" width="100" align="center"/>
			<EF:EFColumn ename="budget" cname="预算(元)" width="100" align="center" format="{0:c2}"/>
			<EF:EFColumn ename="contTaxIncludeAmount" cname="合同含税金额" width="100" hidden="true" align="center"/>
			<EF:EFColumn ename="quarPeriod" cname="质保期(月)" width="100" align="center"/>
			<EF:EFColumn ename="contStatus" cname="合同状态" width="100" align="center"/>
			<EF:EFColumn ename="contAdmin" cname="合同管理员" width="100" align="center"/>
			<EF:EFColumn ename="billMaker" cname="制单人" width="100" align="center"/>
			<EF:EFColumn ename="billMakeTime" cname="制单时间" width="100" align="center"/>
			<EF:EFColumn ename="checkMaker" cname="审批人" width="100" hidden="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 弹窗配置 -->
<EF:EFWindow id="popData" url="" lazyload="true" width="95%" height="95%" title="合同登记管理"/>

<script type="text/javascript">
	
</script>