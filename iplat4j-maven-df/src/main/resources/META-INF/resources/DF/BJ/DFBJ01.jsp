<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="cleanNo" cname="保洁单号" colWidth="4" ratio="4:8"/>
			<EF:EFInput ename="machineName" cname="设备名称" colWidth="4" ratio="4:8"/>
			<EF:EFInput ename="fixedPlace" cname="安装地点" colWidth="4" ratio="4:8"/>
		</div>
		<div class="row">
			<EF:EFDatePicker ename="cleanDateS" cname="保洁日期起"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" colWidth="4" ratio="4:8"/>
			<EF:EFDatePicker ename="cleanDateE" cname="保洁日期止"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" colWidth="4" ratio="4:8"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="合同信息管理">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
			<EF:EFColumn ename="cleanNo" cname="保洁单号" width="120" align="center"/>
			<EF:EFColumn ename="machineName" cname="设备名称" width="100" align="center"/>
			<EF:EFColumn ename="fixedPlace" cname="安装地点" width="100" align="center"/>
			<EF:EFColumn ename="cleanDeptName" cname="保洁单位" width="100" align="center"/>
			<EF:EFColumn ename="deptManageName" cname="负责人" width="100" align="center"/>
			<EF:EFColumn ename="cleanDate" cname="保洁日期" width="100" align="center"/>
			<EF:EFColumn ename="remark" cname="作业说明" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 弹窗配置 -->
<EF:EFWindow id="popData" url="" lazyload="true" width="85%" height="85%" title="保洁登记定义"/>

<script type="text/javascript">
	
</script>