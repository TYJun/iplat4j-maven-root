<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>仓库出库管理</title>--%>
<title>出库单查询</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-outBillNo" cname="出库单号" colWidth="3" ratio="4:8"/>
			<EF:EFSelect ename="inqu_status-0-outType" cname="出库类别" colWidth="3" ratio="4:8">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="wilp.si.outType"/>
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称" colWidth="3" ratio="4:8"
						 resultId="result" textField="wareHouseName" valueField="wareHouseNo"
						 serviceName="SIWH01" methodName="queryWareHouse" optionLabel="请选择">
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-billMakerName" cname="制单人" colWidth="3" ratio="4:8"/>
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-userDeptName" cname="领用科室" colWidth="3" ratio="4:8"/>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="制单日期起"
				role="date" format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="beginTime" colWidth="3" ratio="4:8"/>
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="制单日期止"
				role="date" format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="endTime" colWidth="3" ratio="4:8"/>
			<EF:EFSelect ename="inqu_status-0-isCheck" cname="状态" colWidth="3" ratio="4:8">
				<EF:EFOption label="全部" value=""/>
				<EF:EFOption label="待仓库确认" value="0"/>
				<EF:EFOption label="已签收" value="1"/>
				<EF:EFOption label="待签收" value="2"/>
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-printFlag" cname="是否已打印" colWidth="3" ratio="4:8">
				<EF:EFOption label="全部" value=""/>
				<EF:EFOption label="是" value="1"/>
				<EF:EFOption label="否" value="0"/>
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-matName" cname="物资名称" colWidth="3" ratio="4:8"/>
			<EF:EFInput ename="inqu_status-0-matNum" cname="物资编码" colWidth="3" ratio="4:8"/>
			<EF:EFInput ename="mainOutBillNo" type="hidden" value="no data"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库出库信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="row" autoBind="true" readonly="true" height="300">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="outBillNo" cname="出库单号" width="100" displayType="url" align="center" />
			<EF:EFColumn ename="outTypeName" cname="出库类别" width="100" align="center" />
			<EF:EFColumn ename="originBillNo" cname="来源单据号" width="100" align="center"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库" width="100" align="center"/>
			<EF:EFColumn ename="userDeptName" cname="领用科室" width="100" align="center"/>
			<EF:EFColumn ename="costDeptName" cname="成本科室" width="100" align="center"/>
			<EF:EFColumn ename="totalAmount" cname="出库总金额(元)" width="100" align="center"/>
			<EF:EFColumn ename="userWorkerName" cname="申领人" width="100" align="center"/>
			<EF:EFColumn ename="billMakeTime" cname="制单日期" width="100" align="center"/>
			<EF:EFColumn ename="billMakerName" cname="制单人员" width="100" align="center"/>
			<EF:EFColumn ename="billCheckTime" cname="签收日期" width="100" align="center"/>
			<EF:EFColumn ename="billCheckerName" cname="签收人员" width="100" align="center"/>
			<EF:EFColumn ename="isCheck" cname="状态" width="100" align="center"/>
			<EF:EFColumn ename="printFlag" cname="是否已打印" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFRegion id="detail" title="出库明细信息">
		<EF:EFGrid blockId="detail" autoDraw="no" checkMode="single,row"
				   autoBind="true" readonly="true" serviceName="SICK04" queryMethod="queryDetail" sort="single">
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="150" align="center" sort="true"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="80" align="center" sort="true"/>
			<%--<EF:EFColumn ename="matModel" cname="物资型号" width="80" align="center"/>--%>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" sort="false"/>
			<EF:EFColumn ename="unitName" cname="物资单位" width="80" align="center" sort="false"/>
			<EF:EFColumn ename="outNum" cname="出库数量" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="outAmount" cname="出库总价" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true" sort="false"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" width="100" align="center" sort="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="出库信息" url=" " lazyload="true" width="90%" height="85%"></EF:EFWindow>
<EF:EFWindow id="redPopData" title="出库信息" url=" " lazyload="true" width="100%" height="100%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/SI/common/si-initTime.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>