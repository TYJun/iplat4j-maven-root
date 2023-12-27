<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%--<title>仓库入库验收</title>--%>
<title>入库验收</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-enterBillNo" cname="入库单号" />
			<EF:EFSelect ename="inqu_status-0-enterType" cname="入库类别">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="wilp.si.enterType"/>
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-supplierName" cname="供应商" />
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="制单时间起" role="date"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="beginTime" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="制单时间止" role="date"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="endTime" />
			<EF:EFInput ename="mainEnterBillNo" type="hidden" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库入库信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="row" autoBind="true" readonly="true" height="200">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="enterBillNo" cname="入库单号" width="150" align="center"/>
			<EF:EFColumn ename="enterTypeName" cname="入库类别" width="100" align="center"/>
			<EF:EFColumn ename="userDeptName" cname="直入直出科室名称" width="120" align="center"/>
			<EF:EFColumn ename="userDeptNum" cname="直入直出科室编码" width="100" align="center" hidden="true"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库" width="100" align="center"/>
			<EF:EFColumn ename="supplierName" cname="供应商" width="100" align="center"/>
			<EF:EFColumn ename="totalAmount" cname="入库总金额(元)" width="100" align="center"/>
			<EF:EFColumn ename="enterTime" cname="入库时间" width="100" align="center"/>
			<EF:EFColumn ename="billMakeTime" cname="制单日期" width="100" align="center"/>
			<EF:EFColumn ename="billMakerName" cname="制单人员" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFRegion id="detail" title="入库明细">
		<EF:EFGrid blockId="detail" autoDraw="no" checkMode="single,row" autoBind="true" readonly="true" queryMethod="queryDetail" sort="single">
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="200" align="center" sort="true"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" sort="true"/>
			<%--<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center"/>--%>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
			<EF:EFColumn ename="unitName" cname="物资单位" width="60" align="center" sort="false"/>
			<EF:EFColumn ename="enterNum" cname="入库数量" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="enterPrice" cname="入库单价(元)" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="enterAmount" cname="入库总价(元)" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" width="150" align="center" sort="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="扫码授权" url=" " lazyload="true" width="60%" height="85%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/SI/common/si-initTime.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-yxSign.js"></script>
