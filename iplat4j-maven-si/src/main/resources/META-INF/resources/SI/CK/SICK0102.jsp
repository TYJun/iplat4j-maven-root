<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>红冲出库</title>--%>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		 <div class="row">
			<EF:EFInput ename="outBillNo" cname="出库单号" ratio="4:8" readonly="true"/>
			<EF:EFInput ename="outTypeName" cname="出库类别"  ratio="4:8" readonly="true"/>
			<EF:EFInput ename="wareHouseNo" cname="仓库号" type="hidden" />
			<EF:EFInput ename="wareHouseName" cname="仓库名称" ratio="4:8" readonly="true"/>
			<EF:EFInput ename="userDeptNum" cname="领用科室" type="hidden" />
			<EF:EFInput ename="userDeptName" cname="领用科室"  ratio="4:8" readonly="true"/>
			 <EF:EFInput ename="costDeptNum" cname="领用科室" type="hidden" />
			 <EF:EFInput ename="costDeptName" cname="成本科室"  ratio="4:8" readonly="true"/>
			 <EF:EFInput ename="inqu_status-0-remark" cname="红冲原因" type="textarea" placeholder="不能超过200字" />
		</div> 
	</EF:EFRegion>
		<EF:EFRegion id="result" title="出库单明细">
		<EF:EFGrid blockId="result" autoDraw="no" height="380" checkMode="row">
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="150" enable="false" align="center"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="80" enable="false" align="center"/>
			<%--<EF:EFColumn ename="matModel" cname="物资型号" width="80" enable="false" align="center"/>--%>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
			<EF:EFColumn ename="unitName" cname="物资单位" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="outNum" cname="出库数量" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="totalRedRushNum" cname="已红冲数量" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="redRushNum" cname="红冲数量" width="100" />
			<EF:EFColumn ename="batchNo" cname="批次" width="60" hidden="true" />
			<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" width="100" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>