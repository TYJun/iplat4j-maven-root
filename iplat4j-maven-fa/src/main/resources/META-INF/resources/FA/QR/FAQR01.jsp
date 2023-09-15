<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>资产确认</title>
<EF:EFPage title="资产确认">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-enterPriceS" cname="入库单价起" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-enterPriceE" cname="入库单价止" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-enterAmountS" cname="入库总价起" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-enterAmountE" cname="入库总价止" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-enterBillNo" cname="入库单号" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-matNum" cname="物资编码" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-matName" cname="物资名称" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-matTypeNum" cname="物资类别编码" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-matTypeName" cname="物资类别名称" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-unitName" cname="计量单位" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-surpName" cname="供应商" colWidth="3"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFTab id="FaDaTab">
<%--		<div title="已确认资产信息">--%>
<%--			<EF:EFGrid blockId="resultA" autoDraw="no" rowNo="true" autoBind="true" readonly="true" checkMode="multiple,row" queryMethod="confirmedQuery">--%>
<%--				<EF:EFColumn ename="id" cname="id"  align="center" locked="true" hidden="true"/>--%>
<%--				<EF:EFColumn ename="enterBillNo" cname="入库单号"  align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="matTypeNum" cname="物资类别编码" align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="matTypeName" cname="物资类别名称"  align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="amount" cname="资产数量"   align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="unitName" cname="计量单位"  align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="price" cname="资产单价(元)"  align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="contractNo" cname="合同号"   align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="invoiceNo" cname="发票号"   align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="manufacturer" cname="制造商"   align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="surpName" cname="供应商"   align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="model" cname="规格型号"   align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="useYears" cname="使用年限"   align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="confirmStatus" cname="使用状态"   align="center" enable="false"/>--%>
<%--			</EF:EFGrid>--%>
<%--		</div>--%>
		<div title="待确认资产信息">
			<EF:EFGrid blockId="resultB" autoDraw="no" rowNo="true" autoBind="false" readonly="true" checkMode="multiple,row" queryMethod="unconfirmedQuery" height="460px">
				<EF:EFColumn ename="id" cname="id"  align="center" locked="true" hidden="true"/>
				<EF:EFColumn ename="faConfirmId" cname="faConfirmId"  align="center" locked="true" hidden="true"/>
				<EF:EFColumn ename="receiveType" cname="入库方式" align="center" enable="false" width="100" hidden="true"/>
				<EF:EFColumn ename="receiveTypeName" cname="入库方式"   align="center" enable="false" width="120"/>
				<EF:EFColumn ename="enterBillNo" cname="入库单号"  align="center" width="200" displayType="url" enable="false"/>
				<EF:EFColumn ename="matNum" cname="物资编码" align="center" enable="false" width="200"/>
				<EF:EFColumn ename="matName" cname="物资名称"  align="center" enable="false" width="200"/>
				<EF:EFColumn ename="matTypeNum" cname="物资类别编码" align="center" enable="false" width="200"/>
				<EF:EFColumn ename="matTypeName" cname="物资类别名称"  align="center" enable="false" width="200"/>
				<EF:EFColumn ename="enterNum" cname="入库数量"   align="center" enable="false" width="200"/>
				<EF:EFColumn ename="unitName" cname="计量单位"  align="center" enable="false" width="200"/>
				<EF:EFColumn ename="enterPrice" cname="入库单价(元)"  align="center" enable="false" width="200"/>
				<EF:EFColumn ename="enterAmount" cname="入库总价(元)"   align="center" enable="false" width="200"/>
				<EF:EFColumn ename="surpName" cname="供应商"   align="center" enable="false" width="200"/>
			</EF:EFGrid>
		</div>
<%--		<div title="已删除资产信息">--%>
<%--			<EF:EFGrid blockId="resultC" autoDraw="no" rowNo="true" autoBind="false" readonly="true" checkMode="multiple,row" queryMethod="removeQuery">--%>
<%--				<EF:EFColumn ename="id" cname="id"  align="center" locked="true" hidden="true"/>--%>
<%--				<EF:EFColumn ename="enterBillNo" cname="入库单号"  align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="matTypeNum" cname="物资类别编码" align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="matTypeName" cname="物资类别名称"  align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="amount" cname="资产数量"   align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="unitName" cname="计量单位"  align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="price" cname="资产单价(元)"  align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="contractNo" cname="合同号"   align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="invoiceNo" cname="发票号"   align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="manufacturer" cname="制造商"   align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="surpName" cname="供应商"   align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="model" cname="规格型号"   align="center" enable="false"/>--%>
<%--				<EF:EFColumn ename="useYears" cname="使用年限"   align="center" enable="false"/>--%>
<%--			</EF:EFGrid>--%>
		</div>
	</EF:EFTab>
	<EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="资产卡片生成" modal="true" />
</EF:EFPage>