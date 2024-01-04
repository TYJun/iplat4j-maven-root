<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="资产变更录入">
	<EF:EFRegion title="大类信息">
		<EF:EFInput ename="matNum" cname="物资编码" readOnly="true"/>
		<EF:EFInput ename="enterBillNo" cname="入库单号" readOnly="true"/>
		<EF:EFInput ename="outBillNo" cname="出库单号" readOnly="true"/>
		<EF:EFInput ename="goodsTypeName" cname="类组" readOnly="true"/>
		<EF:EFInput ename="goodsClassifyCode" cname="资产类别编码" type="hidden"/>
		<EF:EFInput ename="goodsClassifyName" cname="资产类别" readOnly="true"/>
		<EF:EFInput ename="goodsCategoryName" cname="资产末级" readOnly="true"/>
	</EF:EFRegion>
	<EF:EFRegion title="基础信息">
		<EF:EFInput ename="faInfoId" cname="faInfoId" type="hidden"/>
		<EF:EFInput ename="archiveFlag" cname="归档标记" type="hidden"/>
		<EF:EFInput ename="contNo" cname="合同号" readOnly="true" type="hidden"/>
		<EF:EFInput ename="purchaseDept" cname="采购科室" readOnly="true"/>
		<EF:EFInput ename="goodsNum" cname="资产编码" readOnly="true"/>
		<EF:EFInput ename="rfidCode" cname="RFID码" readOnly="true" type="hidden"/>
		<EF:EFInput ename="goodsName" cname="资产名称" readOnly="true"/>
		<EF:EFInput ename="model" cname="型号" readOnly="true"/>
		<EF:EFInput ename="spec" cname="规格" readOnly="true"/>
		<EF:EFSelect ename="unitNum"  cname="计量单位" >
			<EF:EFCodeOption codeName="wilp.ac.gm.unit" textField="label" valueField="value"/>
		</EF:EFSelect>
		<EF:EFSelect ename="assetGetWayCode" cname="获取方式">
			<EF:EFCodeOption codeName="wilp.fa.assetGetWayCode"/>
		</EF:EFSelect>
		<EF:EFSelect ename="assetUseCode" cname="资产用途">
			<EF:EFCodeOption codeName="wilp.fa.assetUseCode"/>
		</EF:EFSelect>
		<EF:EFSelect ename="manufacturerNatyCode" resultId="result" cname="国别"
					 textField="label" valueField="value" required="true" filter="contains"
					 serviceName="FAQR01" methodName="queryManufacturerNatyCode"
		/>
		<EF:EFInput ename="deptName" cname="所属科室" readOnly="true"/>
		<EF:EFInput ename="manufacturer" cname="制造厂商" readOnly="true"/>
		<EF:EFInput ename="surpName" cname="供应商" readOnly="true"/>
		<EF:EFInput ename="buyDate" cname="购入日期" readOnly="true"/>
		<EF:EFInput ename="useDate" cname="使用日期" readOnly="true"/>
		<EF:EFInput ename="build" cname="楼" readOnly="true"/>
		<EF:EFInput ename="floor" cname="层" readOnly="true"/>
		<EF:EFInput ename="installLocation" cname="地点" readOnly="true"/>
		<EF:EFInput ename="recCreateName" cname="创建人" readonly="true"/>
		<EF:EFInput ename="recCreateTime" cname="创建时间" readonly="true"/>
		<EF:EFInput ename="statusCodeMean" cname="资产状态" readOnly="true"/>
		<EF:EFInput ename="remark" cname="备注" colWidth="8" type="textarea" ratio="2:10"
					readOnly="true" rows="3"/>
	</EF:EFRegion>
	<EF:EFRegion title="价值信息">
		<EF:EFSelect ename="deprectCode" cname="折旧方式">
			<EF:EFCodeOption codeName="wilp.fa.deprectCode"/>
		</EF:EFSelect>
		<EF:EFInput ename="invoiceDate" cname="发票日期" readOnly="true" type="hidden"/>
		<EF:EFSelect ename="fundingSourceNum" cname="资金来源">
			<EF:EFCodeOption codeName="wilp.mp.source"/>
		</EF:EFSelect>
		<EF:EFInput ename="amount" cname="数量" readOnly="true"/>
		<EF:EFInput ename="price" cname="单价(元)" readOnly="true"/>
		<EF:EFInput ename="buyCost" cname="资产原值(元)" readOnly="true"/>
		<EF:EFInput ename="estimateCost" cname="暂估价值(元)" readOnly="true"/>
		<EF:EFInput ename="useYears" cname="使用年限" readOnly="true"/>
		<EF:EFInput ename="residualRate" cname="残值率(%)" readOnly="true"/>
		<EF:EFInput ename="estimatedResidualValue" cname="预计净残值(元)" readOnly="true"/>
		<EF:EFInput ename="netAssetValue" cname="资产净值(元)" readOnly="true"/>
		<EF:EFInput ename="monthDepreciation" cname="月折旧值(元)" readOnly="true"/>
		<EF:EFInput ename="totalDepreciation" cname="累计折旧值(元)" readOnly="true"/>
	</EF:EFRegion>
	<EF:EFRegion title="资产变更记录">
		<EF:EFGrid blockId="resultModificationRecord" autoDraw="no" rowNo="true" autoBind="true" readonly="true" checkMode="single,row">
			<EF:EFColumn ename="id" cname="id"  align="center" locked="true" hidden="true"/>
			<EF:EFColumn ename="faInfoId" cname="faInfoId"  align="center" hidden="true"/>
			<EF:EFColumn ename="key" cname="编码" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="label" cname="变更字段"   align="center" width="200"/>
			<EF:EFColumn ename="previous" cname="变更前"   align="center" width="200"/>
			<EF:EFColumn ename="later" cname="变更后"  align="center" width="200"/>
			<EF:EFColumn ename="changeValue" cname="变化量"  align="center" width="200"/>
			<EF:EFColumn ename="time" cname="变更时间"  align="center" width="200"/>
			<EF:EFColumn ename="recCreateName" cname="变更人"  align="center" width="200"/>
			<EF:EFColumn ename="changeReason" cname="变更原因"  align="center" width="200"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>