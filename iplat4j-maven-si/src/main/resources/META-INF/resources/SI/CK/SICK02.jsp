<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>仓库出库查看</title>--%>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-outBillNo" cname="出库单号"/>
			<EF:EFSelect ename="inqu_status-0-outType" cname="出库类别">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="wilp.si.outType"/>
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-userDeptName" cname="领用科室"/>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="制单日期起"
				role="date" format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="beginTime" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="制单日期止"
				role="date" format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="endTime" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库出库信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row"
			autoBind="true" readonly="true" >
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="outBillNo" cname="出库单号" width="100" displayType="url" align="center" />
			<EF:EFColumn ename="outTypeName" cname="出库类别" width="100" align="center" />
			<EF:EFColumn ename="originBillNo" cname="来源单据号" width="100" align="center"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库" width="100" align="center"/>
			<EF:EFColumn ename="userDeptName" cname="领用科室" width="100" align="center"/>
			<EF:EFColumn ename="costDeptName" cname="成本科室" width="100" align="center"/>
			<EF:EFColumn ename="billMakeTime" cname="制单日期" width="100" align="center"/>
			<EF:EFColumn ename="billMakerName" cname="制单人员" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="出库详情" url=" " lazyload="true" width="90%" height="90%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/SI/common/si-initTime.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>