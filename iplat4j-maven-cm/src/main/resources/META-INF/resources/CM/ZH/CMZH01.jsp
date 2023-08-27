<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="contNo" cname="合同号" />
			<EF:EFInput ename="contName" cname="合同名称" />
			<EF:EFSelect ename="contTypeNum" cname="合同类型"
				resultId="contTypeName" textField="contTypeName"
				valueField="contTypeNum" serviceName="CMDJ0101"
				methodName="getContTypeNum" optionLabel="请选择">
			</EF:EFSelect>

		</div>
		<div class="row">
			<EF:EFDatePicker ename="contSignTimeS" cname="签订日期起"
					format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" />
			<EF:EFDatePicker ename="contSignTimeE" cname="签订日期止"
					format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']"/>
			<EF:EFSelect ename="contStatus" cname="合同状态">
				<EF:EFOption label="请选择" value="" />
				<EF:EFOption label="登记" value="0" />
				<EF:EFOption label="执行" value="1" />
				<EF:EFOption label="冻结" value="2" />
				<EF:EFOption label="终止" value="3" />
				<EF:EFOption label="结案" value="4" />
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="合同综合管理">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
			checkMode="single" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="contNo" cname="合同号" width="120" align="center" displayType="url"/>
			<EF:EFColumn ename="contName" cname="合同名称" width="120" align="center"/>
			<EF:EFColumn ename="contTypeNum" cname="合同类型" width="100" align="center"/>
			<EF:EFColumn ename="contSignTime" cname="签订日期" width="100" align="center"/>
			<EF:EFColumn ename="planTakeefTime" cname="计划生效日期" width="100" align="center"/>
			<EF:EFColumn ename="planFinishTime" cname="计划终止日期" width="100" align="center"/>
			<EF:EFColumn ename="currType" cname="币种" width="100" align="center"/>
			<EF:EFColumn ename="budget" cname="预算(元)" width="100" align="center" format="{0:0.00}"/>
			<EF:EFColumn ename="contTaxIncludeAmount" cname="合同含税金额" width="100" hidden="true" align="center"/>
			<EF:EFColumn ename="quarPeriod" cname="质保期" width="100" align="center"/>
			<EF:EFColumn ename="contStatus" cname="合同状态" width="100" align="center"/>
			<EF:EFColumn ename="contAdmin" cname="合同管理员" width="100" align="center"/>
			<EF:EFColumn ename="billMaker" cname="制单人" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 弹窗配置 -->
<EF:EFWindow id="popData" url=" " lazyload="true" width="95%" height="95%" title="查看合同信息"/>
