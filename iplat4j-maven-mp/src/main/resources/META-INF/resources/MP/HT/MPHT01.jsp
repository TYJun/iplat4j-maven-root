<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--合同登记管理--%>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-contNo" cname="合同号" />
			<EF:EFInput ename="inqu_status-0-contName" cname="合同名称" />
			<EF:EFDatePicker ename="inqu_status-0-recCreateTimeS" cname="创建日期起"
							 format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" />
		</div>
		<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-recCreateTimeE" cname="创建日期止"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']"/>

			<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态">
				<EF:EFOption label="全部" value="" />
				<EF:EFCodeOption codeName="wilp.mp.contract.Status"/>
			</EF:EFSelect>

			<EF:EFInput ename="inqu_status-0-manageDeptNum" cname="科室编码" type="hidden" />
			<EF:EFInput ename="inqu_status-0-manageDeptName" cname="科室名称" readonly="true" />
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-recCreator" cname="创建人" type = "hidden" />
			<EF:EFInput ename="inqu_status-0-recCreatorName" cname="创建人" readonly="true" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
		</div>

	</EF:EFRegion>
	<EF:EFRegion id="result" title="合同信息列表">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
			checkMode="single,row" readonly="true" rowNo="true" >
			<EF:EFColumn ename="id" cname="id" hidden="true" />
			<EF:EFColumn ename="contNo" cname="合同号" align="center" displayType="url"/>
			<EF:EFColumn ename="itemNum" cname="项目号" align="center"/>
			<EF:EFColumn ename="contName" cname="合同名称" align="center"/>

			<EF:EFComboColumn ename="statusCode" cname="状态" align="center">
				<EF:EFCodeOption codeName="wilp.mp.contract.Status"/>
			</EF:EFComboColumn>

			<EF:EFComboColumn ename="contClassify" cname="合同分类" align="center">
				<EF:EFCodeOption codeName="wilp.mp.contractclassification"/>
			</EF:EFComboColumn>

			<EF:EFComboColumn ename="contType" cname="合同类型"  align="center">
				<EF:EFCodeOption codeName="wilp.mp.cont.contType"/>
			</EF:EFComboColumn>

			<EF:EFColumn ename="contCost" cname="合同金额" align="center" format="{0:0.00}"/>
			<EF:EFColumn ename="signDate" cname="签订日期" align="center"/>
			<EF:EFColumn ename="validDate" cname="生效日期" align="center" />
			<EF:EFColumn ename="supplierName" cname="供应商"  align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url="" lazyload="true" width="90%" height="95%" title="合同信息"/>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>
