<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>资产拆分</title>
<EF:EFPage title="资产拆分">
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
<%--			<EF:EFInput ename="inqu_status-0-deptName" cname="所属科室" colWidth="3"/>--%>
			<EF:EFMultiSelect ename="inqu_status-0-deptName" cname="所属科室" colWidth="3" ratio="4:8"
							  serviceName="FADA01" queryMethod="queryDept" filter="contains">
				<EF:EFOptions blockId="dept" textField="deptName" valueField="deptName"/>
			</EF:EFMultiSelect>
			<EF:EFSelect ename="inqu_status-0-goodsClassifyCode" cname="资产类别"
						 resultId="goodsClassifyCode" textField="goodsClassifyName" colWidth="3" ratio="4:8"
						 valueField="goodsClassifyName" serviceName="FADA01" filter="contains"
						 methodName="queryGoodsClassifyName" optionLabel="--请选择--" >
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-goodsTypeCode" cname="类组"
						 resultId="goodsTypeCode" textField="goodsTypeName" colWidth="3" ratio="4:8"
						 valueField="goodsTypeName" serviceName="FADA01" filter="contains"
						 methodName="queryGoodsGoodsTypeName" optionLabel="--请选择--" >
			</EF:EFSelect>
<%--			<EF:EFInput ename="inqu_status-0-goodsClassifyCode" colWidth="3" ratio="4:8" cname="资产类别"/>--%>
<%--			<EF:EFInput ename="inqu_status-0-goodsTypeCode" colWidth="3" ratio="4:8" cname="类组"/>--%>
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
			<EF:EFGrid blockId="resultA" autoDraw="no" autoBind="true" rowNo="true" readonly="true" checkMode="single,row" queryMethod="confirmedQuery" height="418px" sort="setted">
				<EF:EFColumn ename="faInfoId" cname="faInfoId" align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="120" displayType="url" enable="false" sort="true"/>
				<EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="180" sort="true"/>
				<EF:EFColumn ename="spec" cname="型号规格"  align="center" width="150" sort="true"/>
				<EF:EFColumn ename="deptName" cname="所属科室" align="center" width="150" sort="true"/>
				<EF:EFColumn ename="buyCost" cname="资产原值"   align="center" width="130" sort="true"/>
				<EF:EFColumn ename="netAssetValue" cname="资产净值"   align="center" width="130" sort="true"/>
				<EF:EFColumn ename="useDate" cname="使用日期"   align="center" width="130" sort="true"/>
				<EF:EFColumn ename="room" cname="具体位置"   align="center" width="150" sort="true"/>
				<EF:EFColumn ename="remark" cname="备注"  align="center" width="150" sort="true"/>
				<EF:EFColumn ename="outRemark" cname="出库备注"  align="center" width="150" sort="true"/>
				<EF:EFColumn ename="statusCode" cname="资产状态" align="center" width="200" sort="true"/>
				<EF:EFColumn ename="build" cname="楼"   align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="floor" cname="层"   align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="installLocation" cname="地点"   align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="goodsClassifyName" cname="资产类别"  align="center" width="200"/>
				<EF:EFColumn ename="goodsTypeCode" cname="类别名称"  align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="goodsTypeName" cname="类组"  align="center" width="200"/>
				<EF:EFColumn ename="manufacturer" cname="制造厂商"  align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="surpName" cname="供应商"   align="center" width="200"/>
				<EF:EFColumn ename="buyDate" cname="购入日期"   align="center" width="200"/>
				<EF:EFColumn ename="useYears" cname="使用年限"   align="center" width="200"/>
				<%--			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" />--%>
				<EF:EFColumn ename="recCreateName" cname="创建人"   align="center" width="200"/>
				<EF:EFColumn ename="rfidCode" cname="RFID"  align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="cardStatus" cname="是否发卡"  align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="lockFlag" cname="变更状态"  align="center" hidden="true"/>
			</EF:EFGrid>
		</div>
<%--		<div title="资产拆分记录">--%>
<%--			<EF:EFGrid blockId="resultC" autoDraw="no" autoBind="true" readonly="true" checkMode="multiple,row" queryMethod="splitRecordQuery">--%>
<%--				<EF:EFColumn ename="id" cname="固定资产调拨表主键"  align="center" locked="true" hidden="true"/>--%>
<%--				<EF:EFColumn ename="splitNo" cname="拆分单号"  align="center" locked="true" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="goodsName" cname="固定资产编码"  align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="goodsNum" cname="固定资产名称"  align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="buyCost" cname="资产原值"  align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="totalDepreciation" cname="累计折旧"  align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="netAssetValue" cname="资产净值"  align="center" enable="false" width="200"/>--%>
<%--			</EF:EFGrid>--%>
<%--		</div>--%>
	</EF:EFTab>
    <EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="资产拆分录入" modal="true" />
	<script type="text/javascript" src="${ctx}/FA/common/js/fa-keydown.js"></script>
</EF:EFPage>