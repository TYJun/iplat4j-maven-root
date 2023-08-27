<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="applyNo" cname="申请单号" colWidth="4" ratio="4:8"/>
<%--			<EF:EFInput ename="status" cname="状态" colWidth="4" ratio="4:8" hidden="true" />--%>
			<EF:EFInput ename="iconName" cname="标识名称" colWidth="4" ratio="4:8"/>
			<EF:EFSelect ename="status" colWidth="4" ratio="3:8" cname="申请状态" >
				<EF:EFOption label="请选择" value=""/>
				<EF:EFOption label="新增" value="01"/>
				<EF:EFOption label="待审核" value="02"/>
				<EF:EFOption label="审核通过" value="03"/>
				<EF:EFOption label="审核驳回" value="04"/>
				<EF:EFOption label="设计完成，科室审核确认" value="05"/>
				<EF:EFOption label="设计不通过，科室驳回" value="06"/>
				<EF:EFOption label="科室审核通过" value="07"/>
				<EF:EFOption label="科室审核不通过，科室驳回" value="08"/>
				<EF:EFOption label="制作完成" value="09"/>
				<EF:EFOption label="制作不通过，科室驳回" value="10"/>
				<EF:EFOption label="安装完成" value="11"/>
				<EF:EFOption label="安装未完成，科室驳回" value="12"/>
				<EF:EFOption label="验收通过" value="13"/>
				<EF:EFOption label="科室验收不通过，科室驳回" value="14"/>
			</EF:EFSelect>
		</div>
		<div class="row">
			<EF:EFDatePicker ename="recCreateTimeS" cname="申请日期起"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" colWidth="4" ratio="4:8"/>
			<EF:EFDatePicker ename="recCreateTimeE" cname="申请日期止"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" colWidth="4" ratio="4:8"/>
		</div>
	</EF:EFRegion>

	<EF:EFRegion id="result" title="医院标识管理">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
			<EF:EFColumn ename="applyNo" cname="申请单号" width="120" align="center"/>
			<EF:EFComboColumn ename="status" cname="状态" width="120" align="center">
				<EF:EFCodeOption codeName="wilp.hi.applyStatus"/>
			</EF:EFComboColumn>
			<EF:EFColumn ename="applyDeptName" cname="申请科室" width="100" align="center"/>
			<EF:EFColumn ename="iconName" cname="标识名称" width="100" align="center"/>
			<EF:EFColumn ename="iconZhContent" cname="标识中文内容" width="100" align="center"/>
			<EF:EFColumn ename="iconEnContent" cname="标识英文内容" width="100" align="center"/>
			<EF:EFColumn ename="classifyName" cname="标识分类名称" width="100" align="center"/>
			<EF:EFColumn ename="spotName" cname="安装地点" width="100" align="center"/>
			<EF:EFColumn ename="iconAmount" cname="数量" width="100" align="center" format="{0:c2}"/>
			<EF:EFColumn ename="applyReason" cname="申请理由" width="100" hidden="true" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 弹窗配置 -->
<EF:EFWindow id="popData" url="" lazyload="true" width="95%" height="95%" title="标识申请"/>
<script type="text/javascript">
</script>