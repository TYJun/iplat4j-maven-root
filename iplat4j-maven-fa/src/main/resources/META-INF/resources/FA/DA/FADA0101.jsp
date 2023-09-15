<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage>
	<EF:EFRegion id="info" head="hidden">
		<EF:EFRegion title="入库信息">
			<EF:EFInput ename="info-0-faConfirmId" cname="faConfirmId" type="hidden"/>
			<EF:EFInput ename="type" cname="操作类型" type="hidden"/>
			<EF:EFInput ename="info-0-archiveFlag" cname="归档标记" type="hidden"/>
<%--			<EF:EFInput ename="info-0-enterBillNo" cname="入库单号"/>--%>
			<EF:EFInput ename="info-0-contNo" cname="合同号"/>
			<EF:EFInput ename="info-0-purchaseVouch" cname="采购凭证"/>
			<EF:EFInput ename="info-0-purchaseDeptNum" cname="科室编码" type="hidden"/>
			<EF:EFInput ename="info-0-purchaseDeptName" cname="科室名称" type="hidden"/>
			<EF:EFAutoComplete ename="info-0-purchaseDept" cname="采购科室" noDataTemplate="没有数据"
							   filter="contains" serviceName="FADA01" queryMethod="queryDept"
							   resultId="dept" dataField="deptName" required="true"/>
			<%--			<EF:EFPopupInput ename="info-0-purchaseDept" cname="采购科室"--%>
			<%--							 popupTitle="科室选择" readOnly="true"--%>
			<%--							 popupType="ServiceGrid" serviceName="FADA01" methodName="queryDept" resultId="dept"--%>
			<%--							 valueField="deptNum" textField="deptName"--%>
			<%--							 columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />--%>
			<EF:EFInput ename="info-0-purchaseStaffName" cname="采购人"/>
			<EF:EFInput ename="info-0-matNum" cname="物资编码"/>
			<EF:EFInput ename="info-0-matName" cname="物资名称"/>
			<EF:EFInput ename="info-0-matTypeNum" cname="物资分类编码"/>
			<EF:EFInput ename="info-0-matTypeName" cname="物资分类"/>
			<EF:EFInput ename="info-0-matSpec" cname="物资规格"/>
			<EF:EFInput ename="info-0-model" cname="物资型号"/>
			<EF:EFSelect ename="info-0-unitNum"  cname="计量单位" optionLabel="--请选择--">
				<EF:EFCodeOption codeName="wilp.ac.gm.unit" textField="label" valueField="value"/>
			</EF:EFSelect>
			<EF:EFPopupInput ename="info-0-surpNum" cname="供应商" popupTitle="供应商选择" readonly="true"
							 popupType="ServiceGrid" resultId="supplier" serviceName="FADA01" methodName="querySupplier"
							 valueField="surpNum" textField="surpName"  columnEnames="surpNum,supplierName"
							 columnCnames="供应商编码,供应商名称"/>
			<EF:EFDatePicker ename="info-0-acquitvDate" cname="购置日期" role="date"
							 format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']" />
			<EF:EFInput ename="info-0-acquitvYear" cname="购置年度"/>
			<EF:EFInput ename="info-0-invoiceNo" cname="发票号"/>
		</EF:EFRegion>
		<EF:EFRegion title="资产信息">
			<EF:EFInput ename="info-0-goodsNum" cname="资产编号" readOnly="true" type="hidden"/>
			<EF:EFInput ename="info-0-goodsName" cname="资产名称" required="true"/>
<%--			<EF:EFPopupInput ename="info-0-goodsTypeCode" cname="资产类别" required="true" readOnly="true"--%>
<%--							 containerId="ef_popup_grid" popupWidth="1200" popupHeight="600" popupTitle="资产类别选择"--%>
<%--							 resizable="true" center="true">--%>
<%--			</EF:EFPopupInput>--%>
			<EF:EFInput ename="info-0-goodsClassifyName" cname="资产类别" readonly="true"/>
			<EF:EFInput ename="info-0-goodsClassifyCode" cname="资产类别编码"  required="true" type="hidden"/>
			<EF:EFInput ename="info-0-goodsTypeName" cname="类组" readonly="true"/>
			<EF:EFInput ename="info-0-goodsTypeCode" cname="类别编码"  required="true" type="hidden"/>
			<!-- 固资类别选择弹出窗  -->
			<div id="ef_popup_grid" style="display: none;">
				<EF:EFPage>
					<div class="col-md-3">
						<EF:EFRegion id="FaTypeMenu" title="类别名称">
							<EF:EFTree id="menu" valueField="id" textField="typeName" hasChildren="isLeaf"
									   serviceName="FALB01" methodName="queryFaTypeTree" style="height:560px;"/>
						</EF:EFRegion>
					</div>
					<div class="col-md-9">
						<EF:EFRegion id="inqu" title="查询条件">
							<div class="row">
								<EF:EFInput ename="typeId" cname="typeId" type="hidden"/>
								<EF:EFInput ename="typeName" cname="typeName" type="hidden"/>
								<EF:EFInput ename="inqu_status-0-faTypeId" cname="资产类别ID" type="hidden"/>
								<EF:EFInput ename="inqu_status-0-typeName" cname="资产类别名称" colWidth="10"/>
							</div>
							<div class="button-region" id="buttonDiv">
								<EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
								<EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
							</div>
						</EF:EFRegion>
						<EF:EFRegion id="result" title="资产类别信息">
							<EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true" serviceName="FALB01" queryMethod="query">
								<EF:EFColumn ename="faTypeId" cname="资产类别Id" width="100" enable="false" align="center" hidden="true"/>
								<EF:EFColumn ename="typeCode" cname="类别编码" width="100" enable="false" align="center"/>
								<EF:EFColumn ename="typeName" cname="类别名称" width="100" enable="false" align="center"/>
								<EF:EFColumn ename="parentId" cname="上级类别ID" width="100" enable="false" align="center" hidden="true"/>
								<EF:EFColumn ename="parentCode" cname="上级类别编码" width="100" enable="false" align="center"/>
								<EF:EFColumn ename="parentName" cname="上级类别名称" width="100" enable="false" align="center"/>
								<EF:EFColumn ename="useYears" cname="使用年限(年)" width="100" enable="false" align="center"/>
								<EF:EFColumn ename="codeRule" cname="编码规则" width="100" enable="false" align="center"/>
								<EF:EFColumn ename="level" cname="类别层级" width="100" enable="false" align="center" hidden="true"/>
								<EF:EFColumn ename="remark" cname="备注" width="100" enable="false" align="center"/>
							</EF:EFGrid>
						</EF:EFRegion>
					</div>
				</EF:EFPage>
			</div>
			<EF:EFSelect ename="info-0-assetGetWayCode" cname="获取方式" optionLabel="--请选择--">
				<EF:EFCodeOption codeName="wilp.fa.assetGetWayCode"/>
			</EF:EFSelect>
			<EF:EFSelect ename="info-0-assetUseCode" cname="资产用途" optionLabel="--请选择--">
				<EF:EFCodeOption codeName="wilp.fa.assetUseCode"/>
			</EF:EFSelect>
			<EF:EFPopupInput ename="info-0-goodsCategoryCode" cname="资产末级" required="true" readOnly="true"
							 containerId="ef_popup_grid" popupWidth="1200" popupHeight="600" popupTitle="资产末级选择"
							 resizable="true" center="true">
			</EF:EFPopupInput>
			<EF:EFInput ename="info-0-manufacturer" cname="制造厂商" type="hidden"/>
			<EF:EFSelect ename="info-0-manufacturerNatyCode" resultId="result" cname="国别"
						 textField="label" valueField="value" required="true" filter="contains"
						 serviceName="FAQR01" methodName="queryManufacturerNatyCode"
			/>
			<EF:EFInput ename="info-0-useYears" cname="使用年限" placeholder="使用年限(年)"/>
			<%--所属科室、楼、层、地点、具体位置--%>
			<EF:EFInput ename="info-0-confirmDeptName" cname="科室名称" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-confirmDeptNum" cname="科室编码" readonly="true" type="hidden"/>
			<EF:EFAutoComplete ename="info-0-deptName" cname="所属科室" noDataTemplate="没有数据"
							   filter="contains" serviceName="FADA01" queryMethod="queryDept"
							   resultId="dept" dataField="deptName" required="true"/>
			<EF:EFPopupInput ename="info-0-confirmLocationNum" cname="存放位置" readonly="true" popupWidth = "550"
							 popupType="ServiceGrid" popupTitle="存放位置选择" serviceName="FADB0101" methodName="querySpot" resultId="spot" autofit="true"
							 valueField="spotNum" textField="spotName" backFillFieldIds="spotName" backFillColumnIds="spotName" columnEnames="building,floor,spotName" columnCnames="楼,层,地点"/>
			<EF:EFInput ename="info-0-confirmBuild" cname="楼" readonly="true"/>
			<EF:EFInput ename="info-0-confirmFloor" cname="层" readonly="true"/>
			<EF:EFInput ename="info-0-confirmRoom" cname="具体位置" />
			<EF:EFInput ename="info-0-brandDesc" cname="出厂编号" type="textarea"
						rows="1" maxLength="200"/>
			<EF:EFInput ename="info-0-warranty" cname="保修期(月)" value="0"/>
			<EF:EFInput ename="info-0-remark" cname="备注" colWidth="8" type="textarea" ratio="2:10"
						rows="3" placeholder="不能超过200字" maxLength="200"/>
		</EF:EFRegion>
		<EF:EFRegion title="价值信息">
			<EF:EFInput ename="info-0-enterNum" cname="入库数量" maxLength="4"/>
			<EF:EFInput ename="info-0-enterPrice" cname="入库单价(元)"/>
			<EF:EFInput ename="info-0-enterAmount" cname="入库总价(元)"/>
			<EF:EFSelect ename="info-0-costType"  cname="价值类型">
				<EF:EFOption label="资产原值" value="00"/>
				<EF:EFOption label="暂估价值" value="10"/>
			</EF:EFSelect>
			<div id="buyCost">
				<EF:EFInput ename="info-0-buyCost" cname="资产原值(元)" placeholder="入库单价(元)"/>
			</div>
			<div id="estimateCost" style="display: none">
				<EF:EFInput ename="info-0-estimateCost" cname="暂估价值(元)" placeholder="暂估价值(元)"/>
			</div>
			<EF:EFSelect ename="info-0-deprectCode" cname="折旧方式">
				<EF:EFCodeOption codeName="wilp.fa.deprectCode"/>
			</EF:EFSelect>
			<EF:EFInput ename="info-0-equityFund" cname="自有资金(元)"/>
			<EF:EFSelect ename="info-0-fundingSourceNum" cname="资金来源" optionLabel="--请选择--">
				<EF:EFCodeOption codeName="wilp.mp.source"/>
			</EF:EFSelect>
			<EF:EFInput ename="info-0-otherFund" cname="其它资金(元)"/>
			<div id="fundProject">
				<EF:EFInput ename="info-0-fundProjectCode" cname="资金项目编码"/>
				<EF:EFInput ename="info-0-fundProject" cname="资金项目"/>
			</div>
			<EF:EFInput ename="info-0-residualRate" cname="残值率(%)" placeholder="残值率(%)" type="hidden"/>
			<EF:EFInput ename="info-0-estimatedResidualValue" cname="预计净残值(元)" placeholder="资产原值(元)*残值率(%)" type="hidden"/>
		</EF:EFRegion>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="提交" ename="SAVE" layout="3"></EF:EFButton>
			<EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
		</div>
	</EF:EFRegion>
</EF:EFPage>