<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>报废管理</title>--%>
<title>报废记录</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-scrapNo" cname="报废单号" />
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="wilp.si.scrapStatus"/>
			</EF:EFSelect>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="报废日期起" role="date"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="beginTime" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="报废日期止" role="date"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="endTime" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库入库信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row"
			autoBind="true" readonly="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="scrapNo" cname="报废单号" displayType="url" align="center"/>
			<EF:EFColumn ename="scrapDate" cname="报废日期" align="center"/>
			<EF:EFColumn ename="statusName" cname="状态" align="center"/>
			<EF:EFColumn ename="scrapReason" cname="报废原因"  align="center"/>
			<EF:EFColumn ename="recCreatorName" cname="创建人" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 报废子页面 -->
<EF:EFWindow id="popData" title="报废信息" url=" " lazyload="true" width="90%" height="85%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/SI/common/si-initTime.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>