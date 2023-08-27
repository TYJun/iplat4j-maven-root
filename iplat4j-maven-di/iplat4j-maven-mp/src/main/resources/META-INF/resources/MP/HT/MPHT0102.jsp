<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>采购计划明细选择</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-purchaseNo" cname="采购计划单号" />
			<EF:EFDatePicker ename="inqu_status-0-recCreateTimeS" cname="创建日期起" />
			<EF:EFDatePicker ename="inqu_status-0-recCreateTimeE" cname="创建日期止" />
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-purchaseRemark" cname="采购计划描述" />
			<EF:EFSelect ename="inqu_status-0-statusCodes" cname="状态" readonly="true">
				<EF:EFOption label="审批通过、部分生成合同" value="30,40"/>
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-deptNum" cname="科室编码" readonly="true" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" readonly="true" />
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-recCreator" cname="创建人工号" readonly="true" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-recCreatorName" cname="创建人" readonly="true" />
			<EF:EFInput ename="inqu_status-0-procurementId" cname="采购计划主键" readonly="true" type="hidden"/>

			<div class="button-region" id="buttonDiv">
				<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
				<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="采购计划(请点击采购计划单号，选择采购计划明细)">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="single,row" readonly="true" height="200">
			<EF:EFColumn ename="id" cname="主键" hidden="true"/>
			<EF:EFColumn ename="purchaseNo" cname="采购计划单号"  align="center"/>
			<EF:EFColumn ename="purchaseNum" cname="计划数量" align="center"/>
			<EF:EFColumn ename="purchaseCost" cname="计划总价" align="center"/>
			<EF:EFColumn ename="purchaseRemark" cname="采购计划描述" align="center"/>
			<EF:EFColumn ename="recCreateTimeStr" cname="创建时间" align="center"/>
			<EF:EFColumn ename="recCreatorName" cname="创建人" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>

	<EF:EFRegion id="detail" title="采购计划明细">
		<EF:EFGrid blockId="detail" autoDraw="no" checkMode="row" autoBind="true" readonly="true" queryMethod="queryDetail">
			<EF:EFColumn ename="purchaseId" cname="采购计划ID" hidden="true" />
			<EF:EFColumn ename="purchaseNo" cname="采购计划单号" align="center"/>
			<EF:EFColumn ename="matNum" cname="物资编码" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" align="center"/>
			<EF:EFColumn ename="matTypeId" cname="物资分类编码" hidden="true"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" align="center"/>
			<EF:EFColumn ename="matModel" cname="物资型号" align="center"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" align="center"/>
			<EF:EFColumn ename="unit" cname="单位" hidden="true"/>
			<EF:EFColumn ename="unitName" cname="单位" width="60" align="center"/>
			<EF:EFColumn ename="price" cname="单价(元)" width="60" align="center"/>
			<EF:EFColumn ename="num" cname="采购计划数量" width="100" align="center"/>
			<EF:EFColumn ename="contedNum" cname="已生成合同数量" width="100" align="center"/>
			<EF:EFColumn ename="pictureUri" cname="图片" hidden="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
