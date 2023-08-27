<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>医院标识布局查询</title>
<EF:EFPage title="医院位置信息">
	<div class="col-md-3">
		<EF:EFRegion id="R1" title="医院标识分类" fitHeight="true">
			<EF:EFTree id="tree01" textField="text" valueField="label"
				hasChildren="leaf" serviceName="HICX01" methodName="queryTree">
			</EF:EFTree>
		</EF:EFRegion>
	</div>
	<div class="col-md-9">
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<div class="row">
				<EF:EFInput ename="id" cname="标识ID" type="hidden"/>
				<EF:EFInput ename="building" cname="楼" type="hidden"/>
				<EF:EFInput ename="floor" cname="层" type="hidden"/>
				<EF:EFInput ename="inqu_status-0-iconNum" cname="标识编码" colWidth="4" ratio="4:8"/>
				<EF:EFInput ename="inqu_status-0-iconName" cname="标识名称" colWidth="4" ratio="4:8"/>
				<EF:EFInput ename="inqu_status-0-deptName" cname="使用科室" colWidth="4" ratio="4:8"/>
			</div>
			<div class = "row">
				<EF:EFDatePicker ename="inqu_status-0-installDateS" cname="安装日期起"
								 format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" colWidth="4" ratio="4:8"/>
				<EF:EFDatePicker ename="inqu_status-0-installDateE" cname="安装日期止"
								 format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" colWidth="4" ratio="4:8"/>
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="医院标识列表">
			<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" readonly="true">
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
	</div>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " title="医院标识信息" lazyload="true" width="90%" height="90%"></EF:EFWindow>