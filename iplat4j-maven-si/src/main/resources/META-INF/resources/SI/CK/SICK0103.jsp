<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>出库详情</title>--%>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		 <div class="row">
			<EF:EFInput ename="outBillNo" cname="出库单号" readonly="true"/>
			<EF:EFInput ename="outTypeName" cname="出库类别" readonly="true"/>
			<EF:EFInput ename="wareHouseName" cname="仓库名称"  readonly="true"/>
			<EF:EFInput ename="userDeptName" cname="领用科室" readonly="true"/>
			 <EF:EFInput ename="remark" cname="备注" type="textarea" readonly="true"/>
		</div> 
	</EF:EFRegion>
	<EF:EFRegion id="result" title="出库明细信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row"
			autoBind="true" readonly="true" sort="single" >
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