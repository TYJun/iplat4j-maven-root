<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--合同台账--%>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-contNo" cname="合同号" />
			<EF:EFInput ename="inqu_status-0-contName" cname="合同名称" />
			<EF:EFDatePicker ename="inqu_status-0-recCreateTimeS" cname="创建日期起"/>
		</div>
		<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-recCreateTimeE" cname="创建日期止"/>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态">
				<EF:EFOption label="全部" value="" />
				<EF:EFCodeOption codeName="wilp.mp.contract.Status"/>
			</EF:EFSelect>
		</div>

		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
		</div>

	</EF:EFRegion>
	<EF:EFRegion id="result" title="采购合同信息">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
			checkMode="single,row" readonly="true" rowNo="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="contNo" cname="合同号" width="120" align="center" displayType="url"/>
			<EF:EFColumn ename="contName" cname="合同名称" width="100" align="center"/>
			<EF:EFColumn ename="statusName" cname="状态" width="100" align="center"/>
			<EF:EFComboColumn ename="contClassify" cname="合同分类" width="100" align="center">
				<EF:EFCodeOption codeName="wilp.mp.contractclassification"/>
			</EF:EFComboColumn>
			<EF:EFComboColumn ename="contType" cname="合同类型" width="100" align="center">
				<EF:EFCodeOption codeName="wilp.mp.cont.contType"/>
			</EF:EFComboColumn>

			<EF:EFColumn ename="contCost" cname="合同金额" width="100" align="center" format="{0:0.00}"/>
			<EF:EFColumn ename="signDate" cname="签订日期" width="100" align="center"/>
			<EF:EFColumn ename="validDate" cname="生效日期" width="100" align="center" />
			<EF:EFColumn ename="supplierName" cname="供应商" width="100"  align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url="" lazyload="true" width="93%" height="95%" title="合同详情"/>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>


