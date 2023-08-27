<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>采购计划管理</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-purchaseNo" cname="采购计划单号"/>

			<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态">
				<EF:EFOption label="全部" value="" />
				<EF:EFCodeOption codeName="wilp.mp.purchase.planStatus"/>
			</EF:EFSelect>


			<EF:EFInput ename="inqu_status-0-deptNum" cname="科室编码" type="hidden" />
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" readonly="true" />


		</div>

		<div class="row">

			<EF:EFInput ename="inqu_status-0-recCreatorName" cname="创建人" />

			<EF:EFDatePicker ename="inqu_status-0-recCreateTimeS" cname="创建日期起" readonly="true"
							 role="date" format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="recCreateTimeS" />

			<EF:EFDatePicker ename="inqu_status-0-recCreateTimeE" cname="创建日期止" readonly="true"
							 role="date" format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="recCreateTimeE"/>


		</div>

		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="RESET" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="物资申请计划">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true" readonly="true" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" hidden="true" />
			<EF:EFColumn ename="purchaseNo" cname="采购计划单号" align="center" />
			<EF:EFColumn ename="purchaseNum" cname="计划数量" align="center" />
			<EF:EFColumn ename="purchaseCost" cname="计划总价" align="center"/>
			<EF:EFColumn ename="statusCode" cname="状态" hidden="true" />
			<EF:EFColumn ename="statusName" cname="状态" align="center" />
			<EF:EFColumn ename="purchaseRemark" cname="采购计划描述" align="center"/>
			<EF:EFColumn ename="recCreatorName" cname="创建人" align="center"/>
			<EF:EFColumn ename="recCreateTime" cname="创建日期" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="" url=" " lazyload="true" width="90%" height="90%"></EF:EFWindow>

<EF:EFWindow id="popData2" title="科室月度申请计划" url=" " lazyload="true" width="90%" height="90%"></EF:EFWindow>