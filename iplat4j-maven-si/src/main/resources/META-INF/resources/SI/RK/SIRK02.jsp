<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>仓库入库查看</title>--%>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-enterBillNo" cname="入库单号" />
			<EF:EFSelect ename="inqu_status-0-enterType" cname="入库类别">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="wilp.si.enterType"/>
			</EF:EFSelect>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="制单时间起" role="date" 
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="beginTime" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="制单时间止" role="date" 
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="endTime" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库入库信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row"
			autoBind="true" readonly="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="enterBillNo" cname="入库单号" width="150" displayType="url" align="center"/>
			<EF:EFComboColumn ename="enterType" cname="入库类别" width="100" align="center">
				<EF:EFCodeOption codeName="wilp.si.enterType"/>
			</EF:EFComboColumn>
			<EF:EFColumn ename="originBillNo" cname="来源单据号" width="100" align="center"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库" width="100" align="center"/>
			<EF:EFColumn ename="billMakeTime" cname="制单日期" width="100" align="center"/>
			<EF:EFColumn ename="billMakerName" cname="制单人员" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="入库详情" url=" " lazyload="true" width="90%" height="85%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/SI/common/si-initTime.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>