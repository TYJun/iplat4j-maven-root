<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>仓库入库管理</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-enterBillNo" cname="入库单号" />
			<EF:EFInput ename="inqu_status-0-enterBillNo" cname="入库单号" />
			<EF:EFSelect ename="inqu_status-0-enterType" cname="入库类别">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="wilp.ci.enterType"/>
			</EF:EFSelect>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="制单时间起" role="date" 
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="beginTime" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="制单时间止" role="date" 
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="endTime" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库入库信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row"
			autoBind="true" readonly="true" height="300">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="enterBillNo" cname="入库单号" width="150" displayType="url" align="center"/>
			<EF:EFColumn ename="enterTypeName" cname="入库类别" width="100" align="center"/>
			<EF:EFColumn ename="originBillNo" cname="来源单据号" width="100" align="center"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库" width="100" align="center"/>
			<EF:EFColumn ename="billMakeTime" cname="制单日期" width="100" align="center"/>
			<EF:EFColumn ename="billMakerName" cname="制单人员" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>

	<EF:EFRegion id="processRegion" title="入库明细">
		<EF:EFGrid blockId="processsResult" autoDraw="no"  checkMode="single,row" autoBind="true" readonly="false" rowNo="true"  height="400">
			<EF:EFColumn ename="id" cname="主键 " readonly="true" hidden="true"/>
			<EF:EFColumn ename="enterBillNo" cname="入库单号 " readonly="true"/>
			<EF:EFColumn ename="matNum" cname="物资编码 " readonly="true"/>
			<EF:EFColumn ename="matName" cname="物资名称 " readonly="true"/>
			<EF:EFColumn ename="matSpec" cname="物资规格 " readonly="true"/>
			<EF:EFColumn ename="matModel" cname="物资型号 " readonly="true"/>
			<EF:EFColumn ename="unit" cname="计量单位编码 " readonly="true" hidden="true"/>
			<EF:EFColumn ename="unitName" cname="计量单位名称 " readonly="true"/>
			<EF:EFColumn ename="enterNum" cname="入库数量 " readonly="true"/>
			<EF:EFColumn ename="enterPrice" cname="入库单价 " readonly="true"/>
			<EF:EFColumn ename="enterAmount" cname="入库总价 " readonly="true"/>
			<EF:EFColumn ename="enterDate" cname="入库日期 " readonly="true"/>
			<EF:EFColumn ename="enterTime" cname="入库时间 " readonly="true"/>
			<EF:EFColumn ename="recCreator" cname="记录创建责任者 " readonly="true"/>
			<EF:EFColumn ename="recCreateTime" cname="制单时间 " readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>

</EF:EFPage>
<!-- 入库子页面 -->
<EF:EFWindow id="popData" title="入库信息" url=" " lazyload="true" width="90%" height="85%"></EF:EFWindow>