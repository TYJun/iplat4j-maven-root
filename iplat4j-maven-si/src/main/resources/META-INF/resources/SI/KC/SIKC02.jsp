<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>库存操作履历</title>--%>
<title>库房日志</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称"
				resultId="result" textField="wareHouseName" valueField="wareHouseNo"
				serviceName="SIWH01" methodName="queryWareHouse" optionLabel="请选择">
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-matNumLK" cname="物资编码" />
			<EF:EFInput ename="inqu_status-0-matName" cname="物资名称" />
			<EF:EFInput ename="inqu_status-0-originBillNoLK" cname="来源单据号" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="库存操作履历信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true" readonly="true"
		sort="single">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" sort="false"/>
			<EF:EFColumn ename="originBillNo" cname="来源单据号" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="originBillTypeName" cname="来源单据类型" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="wareHouseNo" cname="仓库号" hidden="true" sort="false"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center" sort="false" />
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" sort="false"/>
			<EF:EFColumn ename="unitName" cname="计量单位" width="60" align="center" sort="false"/>
			<EF:EFColumn ename="beforeNum" cname="操作前数量" width="80" align="center" sort="true"/>
			<EF:EFColumn ename="beforeAmount" cname="操作前总价(元)" width="80" align="center" sort="true"/>
			<EF:EFColumn ename="afterNum" cname="操作后数量" width="80" align="center" sort="true"/>
			<EF:EFColumn ename="afterAmount" cname="操作后总价(元)" width="80" align="center" sort="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>