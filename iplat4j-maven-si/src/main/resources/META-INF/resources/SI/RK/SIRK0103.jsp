<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>退库</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="退库信息">
		 <div class="row">
			<EF:EFInput ename="enterBillNo" cname="入库单号" readonly="true"/>
			<EF:EFInput ename="enterTypeName" cname="入库类别" readonly="true"/>
			<EF:EFInput ename="wareHouseNo" cname="仓库号" type="hidden"/>
			<EF:EFInput ename="wareHouseName" cname="仓库名称" readonly="true"/>
			<EF:EFInput ename="supplierNum" cname="供应商编码" type="hidden"/>
			<EF:EFInput ename="supplierName" cname="供应商名称" readonly="true"/>
			<EF:EFInput ename="userDeptNum" cname="供应商编码" type="hidden"/>
			<EF:EFInput ename="userDeptName" cname="供应商编码" type="hidden"/>
		 	<EF:EFInput ename="inqu_status-0-remark" cname="红冲原因" type="textarea" placeholder="不能超过200字" />
		</div> 
	</EF:EFRegion>
	<EF:EFRegion id="result" title="入库明细信息">
		<EF:EFGrid blockId="result" autoDraw="no" height="380" checkMode="row">
<%--			<EF:EFColumn ename="batchNo" cname="批次号" width="100" enable="false" align="center"/>--%>
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="200" enable="false" align="center"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" enable="false" align="center"/>
		<%--	<EF:EFColumn ename="matModel" cname="物资型号" width="100" enable="false" align="center"/>--%>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
			<EF:EFColumn ename="unitName" cname="物资单位" width="60" enable="false" align="center"/>
			<EF:EFColumn ename="enterAmount" cname="入库总价(元)" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="enterPrice" cname="入库单价(元)" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="enterNum" cname="入库数量" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="totalRedRushNum" cname="已红冲数量" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="redRushNum" cname="红冲数量" width="100" align="center" />
			<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="200" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>