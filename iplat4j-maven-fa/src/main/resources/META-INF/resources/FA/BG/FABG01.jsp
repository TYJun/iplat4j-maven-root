<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>资产变更</title>
<EF:EFPage title="资产变更">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-goodsNum" cname="资产编码" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-goodsName" cname="资产名称" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-surpName" cname="供应商" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-remark" cname="备注" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-buyCostS" colWidth="3" ratio="4:8" cname="原值范围起"/>
			<EF:EFInput ename="inqu_status-0-buyCostE" colWidth="3" ratio="4:8" cname="原值范围止"/>
			<EF:EFInput ename="inqu_status-0-netAssetValueS" colWidth="3" ratio="4:8" cname="净值范围起"/>
			<EF:EFInput ename="inqu_status-0-netAssetValueE" colWidth="3" ratio="4:8" cname="净值范围止"/>
			<EF:EFDateSpan startName="inqu_status-0-buyDateS" startCname="购入日期起"
						   endName="inqu_status-0-buyDateE" endCname="购入日期止"
						   ratio="3:3" startRatio="4:8" endRatio="4:8" readonly="true"/>
			<EF:EFDateSpan startName="inqu_status-0-useDateS" startCname="使用日期起"
						   endName="inqu_status-0-useDateE" endCname="使用日期止"
						   ratio="3:3" startRatio="4:8" endRatio="4:8" readonly="true"/>
<%--			<EF:EFInput ename="inqu_status-0-useYear" colWidth="3" ratio="4:8" cname="使用年限"/>--%>
			<EF:EFInput ename="inqu_status-0-deptName" cname="所属科室" colWidth="3"/>
			<EF:EFInput ename="inqu_status-0-goodsClassifyCode" colWidth="3" ratio="4:8" cname="资产类别"/>
			<EF:EFInput ename="inqu_status-0-goodsTypeCode" colWidth="3" ratio="4:8" cname="资产类别名称"/>
<%--			<EF:EFTreeInput ename="inqu_status-0-goodsClassifyCode" cname="资产类别" serviceName="FALB01" methodName="queryFaTypeTree"--%>
<%--							valueField="id" textField="typeName" hasChildren="isLeaf" readonly="true"--%>
<%--							root="{id: 'root', typeName: '根节点'}" colWidth="3" ratio="4:8">--%>
<%--			</EF:EFTreeInput>--%>
<%--			<EF:EFSelect ename="inqu_status-0-goodsTypeCode" resultId="result" serviceName="FALB01" methodName="faTypeEFSelect" filter="contains"--%>
<%--						 colWidth="3" ratio="4:8" optionLabel="--请选择--" cname="类别名称" textField="text" valueField="value" />--%>
			<EF:EFSelect ename="inqu_status-0-fundingSourceNum" cname="资金来源" colWidth="3" ratio="4:8" >
				<EF:EFOption label="--请选择--" value=""/>
				<EF:EFCodeOption codeName="wilp.mp.source"/>
			</EF:EFSelect>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFTab id="FaDaTab">
		<div title="资产信息">
			<EF:EFGrid blockId="resultA" autoDraw="no" autoBind="true" rowNo="true" readonly="true" checkMode="single,row" queryMethod="confirmedQuery" height="460px">
				<EF:EFColumn ename="id" cname="id"  align="center" locked="true" hidden="true"/>
				<EF:EFColumn ename="faInfoId" cname="faInfoId"  align="center" hidden="true"/>
				<EF:EFColumn ename="goodsNum" cname="资产编码"  align="center" width="200" displayType="url"/>
				<EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="200"/>
				<EF:EFColumn ename="deptName" cname="所属科室"   align="center" width="200"/>
<%--				<EF:EFColumn ename="build" cname="楼"   align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="floor" cname="层"   align="center" width="200"/>--%>
				<EF:EFColumn ename="installLocation" cname="地点"   align="center" width="200"/>
				<EF:EFColumn ename="room" cname="具体位置"   align="center" width="200"/>
				<EF:EFColumn ename="goodsClassifyName" cname="资产类别"  align="center" width="200"/>
				<EF:EFColumn ename="goodsTypeName" cname="资产类别名称"  align="center" width="200"/>
				<EF:EFColumn ename="model" cname="型号规格"  align="center" width="200"/>
				<EF:EFColumn ename="manufacturer" cname="制造厂商"  align="center" width="200"/>
				<EF:EFColumn ename="surpName" cname="供应商"   align="center" width="200"/>
				<EF:EFColumn ename="buyDate" cname="购入日期"   align="center" width="200"/>
				<EF:EFColumn ename="useDate" cname="使用日期"   align="center" width="200"/>
				<EF:EFColumn ename="buyCost" cname="资产原值"   align="center" width="200"/>
				<EF:EFColumn ename="netAssetValue" cname="资产净值"   align="center" width="200"/>
				<EF:EFColumn ename="useYears" cname="使用年限"   align="center" width="200"/>
				<%--			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" />--%>
				<EF:EFColumn ename="recCreateName" cname="创建人"   align="center" width="200"/>
				<EF:EFColumn ename="remark" cname="备注"  align="center" width="200"/>
				<EF:EFColumn ename="statusCode" cname="资产状态"   align="center" width="200"/>
				<EF:EFColumn ename="rfidCode" cname="RFID"  align="center" width="200" hidden="true"/>
<%--				<EF:EFColumn ename="cardStatus" cname="是否发卡"  align="center" width="200"/>--%>
			</EF:EFGrid>
		</div>
		<div title="变更记录">
			<EF:EFGrid blockId="resultC" autoDraw="no" autoBind="true" readonly="true" rowNo="true" checkMode="single,row" queryMethod="changeRecord" height="460px">
				<EF:EFColumn ename="faInfoId" cname="faInfoId"  align="center" hidden="true"/>
				<EF:EFColumn ename="goodsNum" cname="资产编码"  align="center" width="200" displayType="url"/>
				<EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="200"/>
				<EF:EFColumn ename="deptName" cname="所属科室"   align="center" width="200"/>
				<EF:EFColumn ename="goodsClassifyName" cname="资产类别"  align="center" width="200"/>
				<EF:EFColumn ename="goodsTypeName" cname="类别名称"  align="center" width="200"/>
			</EF:EFGrid>
		</div>
<%--		<div title="资产变更">--%>
<%--			<EF:EFGrid blockId="resultB" autoDraw="no" autoBind="true" readonly="true" checkMode="multiple,row" queryMethod="unconfirmedQuery">--%>
<%--				<EF:EFColumn ename="id" cname="id"  align="center" locked="true" hidden="true"/>--%>
<%--				<EF:EFColumn ename="faInfoId" cname="faInfoId"  align="center" hidden="true"/>--%>
<%--				<EF:EFColumn ename="goodsNum" cname="资产编码"  align="center" width="200" displayType="url"/>--%>
<%--				<EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="deptName" cname="所属科室"   align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="statusCode" cname="资产状态"   align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="goodsClassifyName" cname="资产类别"  align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="goodsTypeName" cname="类别名称"  align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="model" cname="型号规格"  align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="lockFlag" cname="操作节点"   align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="manufacturer" cname="制造厂商"  align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="surpName" cname="供应商"   align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="buyDate" cname="购入日期"   align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="useDate" cname="使用日期"   align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="build" cname="楼"   align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="floor" cname="层"   align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="installLocation" cname="地点"   align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="buyCost" cname="资产原值"   align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="useYears" cname="使用年限"   align="center" width="200"/>--%>
<%--				&lt;%&ndash;			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" />&ndash;%&gt;--%>
<%--				<EF:EFColumn ename="recCreateName" cname="创建人"   align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="remark" cname="备注"  align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="rfidCode" cname="RFID"  align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="cardStatus" cname="是否发卡"  align="center" width="200"/>--%>
<%--			</EF:EFGrid>--%>
<%--		</div>--%>
	</EF:EFTab>
	<EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="资产档案变更" modal="true" />
</EF:EFPage>