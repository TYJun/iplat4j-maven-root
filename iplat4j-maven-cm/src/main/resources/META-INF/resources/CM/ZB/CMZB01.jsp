<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="bidName" cname="招标名称" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0" ></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="0" ></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="招标管理">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true" >
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="bidNo" cname="招标号" width="100" align="center"/>
			<EF:EFColumn ename="bidName" cname="招标名称" width="100" align="center"/>
			<EF:EFColumn ename="tenderee" cname="招标负责人" width="100" align="center"/>
			<EF:EFColumn ename="budget" cname="预算费用（元）" width="100" align="center" format="{0:c2}"/>
			<EF:EFColumn ename="undertakeDeptName" cname="承办科室" width="100" align="center"/>
			<EF:EFColumn ename="bidPrice" cname="中标价（元）" width="100" align="center" format="{0:c2}"
						 data-regex="/^(([1-9]\d*)(\.\d{1,2})?)$|^(0\.0?([1-9]\d?))$/" data-errorprompt="请输入数字"/>
			<EF:EFColumn ename="bidWinner" cname="中标单位" width="100" align="center"/>
			<EF:EFColumn ename="bidContent" cname="招标内容" width="100" align="center"/>
			<EF:EFColumn ename="bidRemark" cname="招标备注" width="100" align="center"/>
			<EF:EFColumn ename="isCm" cname="生成合同" width="60" align="center"/>
			<EF:EFColumn ename="isPm" cname="生成项目" width="60" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 弹窗配置 -->
<EF:EFWindow id="bidData" url="" lazyload="true" width="80%" height="80%" title="合同招标管理"/>

