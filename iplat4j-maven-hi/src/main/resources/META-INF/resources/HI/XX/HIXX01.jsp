<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="iconNum" cname="标识编码" colWidth="4" ratio="4:8"/>
			<EF:EFInput ename="iconName" cname="标识名称" colWidth="4" ratio="4:8"/>
			<EF:EFSelect ename="status" colWidth="4" ratio="4:8" cname="标识状态" >
				<EF:EFOption label="请选择" value=""/>
				<EF:EFOption label="新建" value="00"/>
				<EF:EFOption label="启用" value="01"/>
				<EF:EFOption label="停用" value="02"/>
			</EF:EFSelect>
		</div>
		<div class = "row">
			<EF:EFInput ename="deptName" cname="使用科室" colWidth="4" ratio="4:8"/>
			<EF:EFDatePicker ename="installDateS" cname="安装日期起"
							 format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" colWidth="4" ratio="4:8"/>
			<EF:EFDatePicker ename="installDateE" cname="安装日期止"
							 format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" colWidth="4" ratio="4:8"/>
		</div>

	</EF:EFRegion>

	<EF:EFRegion id="result" title="标识信息列表">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
			<EF:EFColumn ename="iconNum" cname="标识编码" width="120" align="center"/>
			<EF:EFColumn ename="iconName" cname="标识名称" width="100" align="center"/>
			<EF:EFColumn ename="iconZhContent" cname="标识中文内容" width="100" align="center"/>
			<EF:EFColumn ename="iconEnContent" cname="标识英文内容" width="100" align="center"/>
			<EF:EFColumn ename="classifyName" cname="标识分类名称" width="100" align="center"/>
			<EF:EFColumn ename="iconAmount" cname="数量" width="100" align="center" format="{0:c2}"/>
			<EF:EFComboColumn ename="status" cname="状态" width="120" align="center">
				<EF:EFCodeOption codeName="wilp.hi.standardStatus"/>
			</EF:EFComboColumn>
			<EF:EFColumn ename="deptName" cname="使用科室" width="100" align="center"/>
			<EF:EFColumn ename="spotName" cname="安装地点" width="100" align="center"/>
			<EF:EFColumn ename="installDate" cname="安装日期" width="100" align="center"/>
			<EF:EFColumn ename="remark" cname="备注" width="100" hidden="true" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 弹窗配置 -->
<EF:EFWindow id="popData" url="" lazyload="true" width="95%" height="95%" title="标识申请"/>
<script type="text/javascript">
</script>