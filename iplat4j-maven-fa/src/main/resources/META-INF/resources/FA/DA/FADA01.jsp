<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>资产档案</title>
<EF:EFPage title="资产档案">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-goodsNum" colWidth="3" ratio="4:8" cname="资产编码"/>
			<EF:EFInput ename="inqu_status-0-goodsName" colWidth="3" ratio="4:8" cname="资产名称"/>
			<EF:EFInput ename="inqu_status-0-surpName" colWidth="3" ratio="4:8" cname="供应商"/>
			<EF:EFInput ename="inqu_status-0-remark" colWidth="3" ratio="4:8" cname="备注"/>
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
			<EF:EFInput ename="inqu_status-0-useYear" colWidth="3" ratio="4:8" cname="使用年限"/>
			<EF:EFInput ename="inqu_status-0-goodsClassifyCode" colWidth="3" ratio="4:8" cname="资产类别"/>
			<EF:EFInput ename="inqu_status-0-goodsTypeCode" colWidth="3" ratio="4:8" cname="类组"/>
<%--			<EF:EFTreeInput ename="inqu_status-0-goodsClassifyCode" cname="资产类别" serviceName="FALB01" methodName="queryFaTypeTree"--%>
<%--							valueField="id" textField="typeName" hasChildren="isLeaf" readonly="true"--%>
<%--							root="{id: 'root', typeName: '根节点'}" colWidth="3" ratio="4:8">--%>
<%--			</EF:EFTreeInput>--%>
			<EF:EFSelect ename="inqu_status-0-fundingSourceNum" cname="资金来源" colWidth="3" ratio="4:8" >
				<EF:EFOption label="--请选择--" value=""/>
				<EF:EFCodeOption codeName="wilp.mp.source"/>
			</EF:EFSelect>
<%--			<EF:EFSelect ename="inqu_status-0-goodsTypeCode" resultId="result" serviceName="FALB01" methodName="faTypeEFSelect" filter="contains"--%>
<%--						 colWidth="3" ratio="4:8" optionLabel="--请选择--" cname="类别名称" textField="text" valueField="value" />--%>
<%--			<EF:EFInput ename="inqu_status-0-deptName" colWidth="3" ratio="4:8" cname="所属科室"/>--%>
			<EF:EFMultiSelect ename="inqu_status-0-deptName" cname="所属科室" colWidth="3" ratio="4:8"
							  serviceName="FADA01" queryMethod="queryDept" filter="contains">
				<EF:EFOptions blockId="dept" textField="deptName" valueField="deptName"/>
			</EF:EFMultiSelect>
<%--			<EF:EFAutoComplete ename="inqu_status-0-deptName" cname="所属科室" colWidth="3" ratio="4:8"--%>
<%--			noDataTemplate="没有数据" filter="contains" serviceName="FADA01" queryMethod="queryDept"--%>
<%--			resultId="dept" dataField="deptName"/>--%>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="资产状态" colWidth="3" ratio="4:8">
				<EF:EFOption label="--请选择--" value=""/>
				<EF:EFCodeOption codeName="wilp.fa.statusCode"/>
			</EF:EFSelect>
<%--			<EF:EFSelect ename="inqu_status-0-cardStatus" cname="是否发卡" colWidth="3" ratio="4:8">--%>
<%--				<EF:EFOption label="--请选择--" value=""/>--%>
<%--				<EF:EFOption label="是"  value="1"/>--%>
<%--				<EF:EFOption label="否"  value="0"/>--%>
<%--			</EF:EFSelect>--%>
			<EF:EFInput ename="inqu_status-0-rfidCode" colWidth="3" ratio="4:8" cname="RFID"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFTab id="FaDaTab">
		<div title="资产台账">
			<EF:EFGrid blockId="resultB" autoDraw="no" rowNo="true" autoBind="true" readonly="true" checkMode="multiple,row" queryMethod="confirmedQuery" height="418px">
				<EF:EFColumn ename="id" cname="id"  align="center" locked="true" hidden="true"/>
				<EF:EFColumn ename="faInfoId" cname="faInfoId"  align="center" hidden="true"/>
				<EF:EFColumn ename="operationType" cname="出库类型" align="center" width="200"/>
				<EF:EFColumn ename="goodsNum" cname="资产编码"  align="center" width="200" displayType="url" enable="false"/>
				<EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="200"/>
				<EF:EFColumn ename="spec" cname="型号规格"  align="center" width="200" />
				<EF:EFColumn ename="deptName" cname="所属科室"   align="center" width="200"/>
				<EF:EFColumn ename="room" cname="具体位置"   align="center" width="200"/>
				<EF:EFColumn ename="statusCode" cname="资产状态"   align="center" width="200"/>
				<EF:EFColumn ename="remark" cname="备注"  align="center" width="200"/>
				<EF:EFColumn ename="goodsCategoryName" cname="资产末级"  align="center" width="200"/>
				<EF:EFColumn ename="goodsClassifyName" cname="资产类别"  align="center" width="200"/>
				<EF:EFColumn ename="goodsTypeName" cname="类组"  align="center" width="200"/>
				<EF:EFColumn ename="model" cname="型号"  align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="manufacturer" cname="制造厂商"  align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="surpName" cname="供应商"   align="center" width="200"/>
				<EF:EFColumn ename="buyDate" cname="购入日期"   align="center" width="200"/>
				<EF:EFColumn ename="useDate" cname="使用日期"   align="center" width="200"/>
				<EF:EFColumn ename="buyCost" cname="资产原值"   align="center" width="200"/>
				<EF:EFColumn ename="netAssetValue" cname="资产净值"   align="center" width="200"/>
				<EF:EFColumn ename="useYears" cname="使用年限"   align="center" width="200"/>
				<%--			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" />--%>
				<EF:EFColumn ename="recCreateName" cname="创建人"   align="center" width="200"/>
				<EF:EFColumn ename="build" cname="楼"   align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="floor" cname="层"   align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="installLocation" cname="地点"   align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="rfidCode" cname="RFID"  align="center" width="200"/>
			</EF:EFGrid>
			<EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="资产卡片录入/编辑" modal="true" />
			<EF:EFWindow id="qrCode" url="" lazyload="true" width="15%" height="8%" title="资产卡片录入/编辑" modal="true" />
			<EF:EFWindow id="execlImport" url="" lazyload="true" refresh="true" width="40%" height="50%" title="资产卡片模板导入">
				<EF:EFRegion id="FADAUpload" head="hidden">
					<h4>资产卡片导入规则：</h4>
					<li>资产名称（必填; 字符长度小于等于40）</li>
					<li>资产类别名称（必填; 取值为系统中已录入的资产类别名称）</li>
					<li>上级资产类别名称（必填; 取值为系统中已录入的资产资产类别名称）</li>
					<li>安装位置（必填; 取值为系统中已录入的安装位置）</li>
					<li>使用科室名称（必填; 取值为系统中已录入的使用科室名称）</li>
					<br/>
					<form id="excelForm"  enctype="multipart/form-data">
						文件上传<input id="excelFile" type="file" name="file" ><br />
					</form>
					<button id="download">模板下载</button>
				</EF:EFRegion>
				<div class="button-region" id="buttonDiv" style="float: right">
					<EF:EFButton cname="提交" ename="IMPORTSUBMIT" layout="0" ></EF:EFButton>
					<EF:EFButton cname="关闭" ename="IMPORTCLOSE" layout="0" ></EF:EFButton>
				</div>
			</EF:EFWindow>
		</div>
		<div title="开卡操作">
			<EF:EFGrid blockId="resultC" autoDraw="no" rowNo="true" autoBind="true" readonly="true" checkMode="multiple,row" queryMethod="confirmedQueryC" height="418px">
				<EF:EFColumn ename="id" cname="id"  align="center" hidden="true"/>
				<EF:EFColumn ename="faInfoId" cname="faInfoId"  align="center" hidden="true"/>
				<EF:EFColumn ename="goodsNum" cname="资产编号"  align="center" displayType="url" enable="false"/>
				<EF:EFColumn ename="goodsName" cname="设备名称" align="center"/>
				<EF:EFColumn ename="model" cname="型号"  align="center" hidden="true"/>
				<EF:EFColumn ename="spec" cname="型号规格"  align="center"/>
				<EF:EFColumn ename="deptName" cname="使用科室"   align="center"/>
				<EF:EFColumn ename="statusCode" cname="资产状态"   align="center"/>
				<EF:EFColumn ename="installLocation" cname="地点"  align="center" hidden="true"/>
				<EF:EFColumn ename="brandDesc" cname="序列号"  align="center" hidden="true"/>
				<EF:EFColumn ename="rfidCode" cname="RFID"  align="center"/>
				<EF:EFColumn ename="useDate" cname="启用日期"   align="center" hidden="true"/>
			</EF:EFGrid>
		</div>
	</EF:EFTab>
	<script type="text/javascript" src="../FA/common/js/axios.min.js"></script>
</EF:EFPage>