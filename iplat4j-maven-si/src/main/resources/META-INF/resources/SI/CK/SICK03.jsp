<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>仓库出库查询</title>--%>
<title>出库明细查询</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-outBillNo" cname="出库单号"/>
			<EF:EFSelect ename="inqu_status-0-outType" cname="出库类别">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="wilp.si.outType"/>
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称"
						 resultId="result" textField="wareHouseName" valueField="wareHouseNo"
						 serviceName="SIWH01" methodName="queryWareHouse" optionLabel="请选择">
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-userDeptName" cname="领用科室"/>
			<EF:EFInput ename="inqu_status-0-matNum" cname="物资编码"/>
			<EF:EFInput ename="inqu_status-0-matName" cname="物资名称"/>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="制单日期起"
				role="date" format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="beginTime" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="制单日期止"
				role="date" format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="endTime" />
			<EF:EFInput ename="inqu_status-0-surpName" cname="供应商"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库出库信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true" readonly="true"
			sort="single">
			<EF:EFColumn ename="id" cname="id" hidden="true" sort="false"/>
			<EF:EFColumn ename="outBillNo" cname="出库单号" align="center" sort="false"/>
			<EF:EFColumn ename="outTypeName" cname="出库类别" align="center" sort="false"/>
			<EF:EFColumn ename="originBillNo" cname="来源单据号" align="center" sort="false"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库" align="center" sort="false"/>
			<EF:EFColumn ename="surpNum" cname="供应商编码" align="center" hidden="true" sort="false"/>
			<EF:EFColumn ename="surpName" cname="供应商" align="center" sort="false"/>
			<EF:EFColumn ename="userDeptName" cname="领用科室" align="center" sort="false"/>
			<EF:EFColumn ename="costDeptName" cname="成本科室" align="center" sort="false"/>
			<EF:EFColumn ename="matNum" cname="物资编码" align="center" sort="true"/>
			<EF:EFColumn ename="matName" cname="物资名称" align="center" sort="true"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" align="center" sort="true"/>
			<%--<EF:EFColumn ename="matModel" cname="物资型号" align="center"/>--%>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称"  align="center" sort="false"/>
			<EF:EFColumn ename="unitName" cname="单位名称" width="80" align="center" sort="false"/>
			<EF:EFColumn ename="outNum" cname="出库数量" align="center" sort="true" />
			<EF:EFColumn ename="outPrice" cname="出库单价" align="center" format="{0:n0}" sort="true"/>
			<EF:EFColumn ename="outAmount" cname="出库总价" align="center" sort="true"/>
			<EF:EFColumn ename="remark" cname="备注" align="center" sort="false"/>
			<EF:EFColumn ename="userWorkerName" cname="申领人" align="center" sort="false"/>
			<EF:EFColumn ename="billMakeTime" cname="制单日期" align="center" sort="false"/>
			<EF:EFColumn ename="billMakerName" cname="制单人员" align="center" sort="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-initTime.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>