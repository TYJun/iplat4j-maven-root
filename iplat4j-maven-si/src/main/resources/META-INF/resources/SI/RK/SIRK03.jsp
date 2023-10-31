<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>仓库入库查询</title>--%>
<title>入库记录查询</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-enterBillNo" cname="入库单号" />
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称"
						 resultId="result" textField="wareHouseName" valueField="wareHouseNo"
						 serviceName="SIWH01" methodName="selectUseWareHouse" optionLabel="请选择">
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-enterType" cname="入库类别">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="wilp.si.enterType"/>
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-matNum" cname="物资编码" />
			<EF:EFInput ename="inqu_status-0-matName" cname="物资名称" />
			<EF:EFInput ename="inqu_status-0-supplierName" cname="供应商" />
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="制单时间起" role="date" 
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="beginTime" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="制单时间止" role="date" 
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="endTime" />
			<EF:EFInput ename="inqu_status-0-billMakerName" cname="制单人" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库入库信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row"
			autoBind="true" readonly="true" sort="single">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="enterBillNo" cname="入库单号" align="center" sort="false"/>
			<EF:EFColumn ename="enterTypeName" cname="入库类别" align="center" sort="false"/>
			<EF:EFColumn ename="originBillNo" cname="来源单据号" align="center" sort="false"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库" align="center" sort="false"/>
			<EF:EFColumn ename="matNum" cname="物资编码" align="center" sort="true"/>
			<EF:EFColumn ename="matName" cname="物资名称" align="center" sort="true"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" align="center" sort="true"/>
			<%--<EF:EFColumn ename="matModel" cname="物资型号" align="center"/>--%>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称"  align="center" sort="false"/>
			<EF:EFColumn ename="unitName" cname="计量单位" width="80" align="center" sort="false"/>
			<EF:EFColumn ename="enterNum" cname="入库数量" align="center" format="{0:n0}" sort="true"/>
			<EF:EFColumn ename="enterPrice" cname="入库单价(元)" align="center" sort="true"/>
			<EF:EFColumn ename="enterAmount" cname="入库总价(元)"  align="center" sort="true"/>
			<EF:EFColumn ename="enterTime" cname="入库时间" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="billMakeTime" cname="制单日期" align="center" sort="false"/>
			<EF:EFColumn ename="billMakerName" cname="制单人员" align="center" sort="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-initTime.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>