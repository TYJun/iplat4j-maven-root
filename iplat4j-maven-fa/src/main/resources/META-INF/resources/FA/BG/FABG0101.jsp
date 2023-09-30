<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="资产变更录入">
	<EF:EFRegion id="info" head="hidden">
		<EF:EFRegion title="大类信息">
<%--			<EF:EFPopupInput ename="info-0-goodsTypeCode" cname="资产类别(三级)" required="true" readOnly="true"--%>
<%--							 containerId="ef_popup_grid" popupWidth="1200" popupHeight="600" popupTitle="资产类别选择"--%>
<%--							 resizable="true" center="true">--%>
<%--			</EF:EFPopupInput>--%>
			<EF:EFInput ename="info-0-goodsClassifyCode" cname="编码" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-goodsClassifyName" cname="资产类别(三级)" readonly="true"/>
			<EF:EFPopupInput ename="info-0-goodsCategoryCode" cname="末级类别" required="true" readOnly="true"
							 containerId="ef_popup_grid" popupWidth="1200" popupHeight="600" popupTitle="资产类别选择"
							 resizable="true" center="true">
			</EF:EFPopupInput>
			<!-- 固资类别选择弹出窗  -->
			<div id="ef_popup_grid" style="display: none;">
				<EF:EFPage>
					<div class="col-md-3">
						<EF:EFRegion id="FaTypeMenu" title="资产类别名称">
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
								<EF:EFColumn ename="typeCode" cname="资产类别编码" width="100" enable="false" align="center"/>
								<EF:EFColumn ename="typeName" cname="资产类别名称" width="100" enable="false" align="center"/>
								<EF:EFColumn ename="parentId" cname="上级资产类别ID" width="100" enable="false" align="center" hidden="true"/>
								<EF:EFColumn ename="parentName" cname="上级资产类别名称" width="100" enable="false" align="center"/>
								<EF:EFColumn ename="useYears" cname="使用年限(年)" width="100" enable="false" align="center"/>
								<EF:EFColumn ename="codeRule" cname="编码规则" width="100" enable="false" align="center"/>
								<EF:EFColumn ename="level" cname="类别层级" width="100" enable="false" align="center" hidden="true"/>
								<EF:EFColumn ename="remark" cname="备注" width="100" enable="false" align="center"/>
							</EF:EFGrid>
						</EF:EFRegion>
					</div>
				</EF:EFPage>
			</div>
		</EF:EFRegion>
		<EF:EFRegion title="基础信息">
			<EF:EFInput ename="info-0-faInfoId" cname="faInfoId" type="hidden"/>
			<EF:EFInput ename="info-0-archiveFlag" cname="归档标记" type="hidden"/>
			<EF:EFInput ename="info-0-contractNo" cname="合同号" type="hidden"/>
			<EF:EFInput ename="info-0-goodsNum" cname="资产编码" required="true" type="hidden"/>
			<EF:EFInput ename="info-0-goodsName" cname="资产名称" required="true"/>
			<EF:EFInput ename="info-0-goodsClassifyCode" cname="资产类别编码"  required="true" type="hidden"/>
			<EF:EFInput ename="info-0-goodsClassifyName" cname="资产类别名称"  required="true" type="hidden"/>
			<EF:EFInput ename="info-0-model" cname="型号"  />
			<EF:EFInput ename="info-0-spec" cname="规格"  />
			<EF:EFInput ename="info-0-unitName" cname="计量单位" type="hidden"/>
			<EF:EFSelect ename="info-0-unitNum"  cname="计量单位" >
				<EF:EFCodeOption codeName="wilp.ac.gm.unit" textField="label" valueField="value"/>
			</EF:EFSelect>
			<EF:EFInput ename="info-0-assetGetWayName" cname="获取方式" type="hidden"/>
			<EF:EFSelect ename="info-0-assetGetWayCode" cname="获取方式">
				<EF:EFCodeOption codeName="wilp.fa.assetGetWayCode"/>
			</EF:EFSelect>
			<EF:EFInput ename="info-0-assetUseName" cname="资产用途" type="hidden"/>
			<EF:EFSelect ename="info-0-assetUseCode" cname="资产用途">
				<EF:EFCodeOption codeName="wilp.fa.assetUseCode"/>
			</EF:EFSelect>
			<EF:EFInput ename="info-0-manufacturerNatyName" cname="国别" type="hidden"/>
			<EF:EFSelect ename="info-0-manufacturerNatyCode" resultId="result" cname="国别"
						 textField="label" valueField="value" required="true" filter="contains"
						 serviceName="FAQR01" methodName="queryManufacturerNatyCode"
			/>
			<EF:EFInput ename="info-0-deptNum" cname="所属科室"  readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-deptName" cname="所属科室"  readonly="true"/>
			<EF:EFInput ename="info-0-manufacturer" cname="制造厂商" type="hidden"/>
			<EF:EFPopupInput ename="info-0-surpNum" cname="供应商" popupTitle="供应商选择" readonly="true"
							 popupType="ServiceGrid" resultId="supplier" serviceName="FADA01" methodName="querySupplier"
							 valueField="surpNum" textField="surpName"  columnEnames="surpNum,supplierName"
							 columnCnames="供应商编码,供应商名称"/>
			<EF:EFDateSpan startName="info-0-buyDate" startCname="购入日期"
						   endName="info-0-useDate" endCname="使用日期"
						   startRatio="4:8" endRatio="4:8" readonly="true"/>
			<%--		<div class="row">--%>
			<%--			<div id="inAccountDate" style="display: none">--%>
			<%--				<EF:EFDatePicker ename="info-0-inAccountDate" cname="入账时间" role="date" readonly="true"--%>
			<%--								 format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']" />--%>
			<%--			</div>--%>
			<%--		</div>--%>
			<EF:EFInput ename="info-0-build" cname="楼" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-floor" cname="层" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-installLocationNum" cname="安装位置编码" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-installLocation" cname="安装位置" readonly="true" type="hidden"/>
			<div class="col-md-4">
				<div class="form-group">
					<label data-for="newBuild" class="col-xs-4 control-label">
						楼
					</label>
					<div class="col-xs-8">
						<input id="newBuild" name="newBuild" value=""
							   placeholder="楼" ondblclick="showAll('newBuild')">
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label data-for="newFloor" class="col-xs-4 control-label">
						层
					</label>
					<div class="col-xs-8">
						<input id="newFloor" name="newFloor" value=""
							   placeholder="层" ondblclick="showAll('newFloor')">
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label data-for="newGoodsLocation" class="col-xs-4 control-label">
						地点
					</label>
					<div class="col-xs-8">
						<input id="newGoodsLocation" name="newGoodsLocation"
							   placeholder="地点"
							   ondblclick="showAll('newGoodsLocation')">
					</div>
				</div>
			</div>
			<EF:EFInput ename="info-0-room" cname="具体位置"/>
			<EF:EFInput ename="newGoodsLocationNum" cname="地点编码" type="hidden"/>
			<div class="invoiceDate" style="display: none">
				<EF:EFDatePicker ename="info-0-invoiceDate" cname="发票日期" role="date" readonly="true"
								 format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']" />
			</div>
			<div class="inAccountDate" style="display: none">
				<EF:EFDatePicker ename="info-0-inAccountDate" cname="入账时间" role="date" readonly="true"
								 format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']" />
				<EF:EFInput ename="info-0-recCreateName" cname="创建人" readonly="true"/>
				<EF:EFDatePicker ename="info-0-recCreateTime" cname="创建时间" role="date" readonly="true" disabled="true"
								 format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']"/>
				<EF:EFSelect ename="info-0-statusCode" cname="资产状态" colWidth="3" ratio="4:8">
					<EF:EFCodeOption codeName="wilp.fa.statusCode"/>
				</EF:EFSelect>
			</div>

			<%--			<EF:EFTreeInput ename="info-0-deviceTypeCode" cname="设备分类"  readonly="true"--%>
			<%--							serviceName="MTSX01" methodName="queryTree" textField="text"--%>
			<%--							valueField="label" hasChildren="leaf" root="{label: 'root',text: '分类'}"--%>
			<%--							popupTitile="设备分类" clear="true" placeholder="请选择">--%>
			<%--			</EF:EFTreeInput>--%>
			<%--			<EF:EFPopupInput ename="info-0-deviceCode" cname="设备名称"  readOnly="true"--%>
			<%--							 containerId="ef_popup_grid_sb" popupWidth="850" popupHeight="500" popupTitle="设备名称选择" resizable="true" center="true">--%>
			<%--			</EF:EFPopupInput>--%>

			<EF:EFInput ename="info-0-remark" cname="备注" colWidth="8" type="textarea" ratio="2:10"
						rows="3" placeholder="不能超过200字" maxLength="200"/>
			<!-- 设备选择弹出窗  -->
			<div id="ef_popup_grid_sb" style="display: none;">
				<EF:EFPage>
					<div class="col-md-4">
						<EF:EFRegion id="R2" title="设备选择" style="height:430px;" >
							<EF:EFTree id="tree02" textField="text" valueField="label"  style="height:320px;"
									   hasChildren="leaf" serviceName="FADA01" methodName="queryDeviceTypeTree">
							</EF:EFTree>
						</EF:EFRegion>
					</div>
					<div class="col-md-8">
						<EF:EFRegion id="inqu" title="查询条件" showClear="true">
							<div class="row">
								<EF:EFInput ename="machineName" cname="设备名称"  colWidth="8"/>
								<EF:EFInput ename="deviceClassifyCode" cname="" type="hidden" />
								<EF:EFButton cname="查询" ename="queryFADevice" layout="1" class="k-button"></EF:EFButton>
							</div>
						</EF:EFRegion>
						<EF:EFRegion id="result1" title="固资类别列表" >
							<EF:EFGrid blockId="result1" autoDraw="no" checkMode="single,row" readonly="true"
									   serviceName="FADA01" queryMethod="queryDeviceList" height="305">
								<EF:EFColumn ename="id" cname="设备id" width="100" hidden="true" />
								<EF:EFColumn ename="machineCode" cname="设备编码" width="100" />
								<EF:EFColumn ename="machineName" cname="设备名称" width="100" />
								<EF:EFColumn ename="machineTypeCode" cname="设备分类编码" width="100" hidden="true"/>
								<EF:EFColumn ename="machineTypeName" cname="设备分类名称" width="100" />
							</EF:EFGrid>
						</EF:EFRegion>
					</div>
				</EF:EFPage>
			</div>
		</EF:EFRegion>
		<EF:EFRegion title="价值信息">
			<EF:EFInput ename="info-0-fundingSourceName" cname="资金来源" type="hidden"/>
			<EF:EFSelect ename="info-0-fundingSourceNum" cname="资金来源">
				<EF:EFCodeOption codeName="wilp.mp.source"/>
			</EF:EFSelect>
			<EF:EFSelect ename="info-0-modifyType" cname="变更原因">
				<EF:EFOption label="保持不变" value="00"/>
				<EF:EFOption label="价值变更" value="10"/>
			</EF:EFSelect>
			<EF:EFSelect ename="info-0-costType" cname="价值类型">
				<EF:EFOption label="资产原值" value="00"/>
				<EF:EFOption label="暂估价值" value="10"/>
			</EF:EFSelect>
			<div id="buyCost">
				<EF:EFInput ename="info-0-buyCost" cname="资产原值(元)"/>
			</div>
			<div id="estimateCost" style="display: none">
				<EF:EFInput ename="info-0-estimateCost" cname="暂估价值(元)" placeholder="暂估价值(元)"/>
			</div>
			<EF:EFInput ename="info-0-amount" cname="数量" maxLength="4" type="hidden" readonly="true"/>
			<EF:EFInput ename="info-0-price" cname="单价(元)" placeholder="单价(元)" type="hidden"/>
			<EF:EFInput ename="info-0-useYears" cname="使用年限" placeholder="使用年限(年)"/>
			<EF:EFInput ename="info-0-netAssetValue" cname="资产净值(元)"/>
			<div id="modificationValue" style="display: none">
				<EF:EFInput ename="info-0-modificationCost" cname="变更金额(元)" readonly="true"/>
				<EF:EFInput ename="info-0-modificationValue" cname="变更金额(元)" readonly="true" type="hidden"/>
				<EF:EFInput ename="info-0-modificationLabel" cname="变更类型" readonly="true"/>
				<EF:EFInput ename="info-0-modificationStatus" cname="变更值" readonly="true" type="hidden"/>
			</div>
			<EF:EFInput ename="info-0-residualRate" cname="残值率(%)" placeholder="残值率(%)" type="hidden"/>
			<EF:EFInput ename="info-0-estimatedResidualValue" cname="预计净残值(元)" type="hidden"/>
			<%--			<EF:EFInput ename="info-0-monthDepreciation" cname="月折旧值(元)" format="{0:c}" placeholder="资产原值(元)/(使用年限*12)" onFocus="countMonthDepreciation()"/>--%>
		</EF:EFRegion>
		<EF:EFRegion title="变更理由">
			<EF:EFInput ename="type" cname="操作类型" type="hidden"/>
			<EF:EFInput ename="info-0-changeReason" cname="变更理由" colWidth="8" type="textarea" ratio="2:10"
						rows="3" placeholder="不能超过200字" maxLength="200" required="true"/>
			<div class="button-region" id="buttonDiv">
				<EF:EFButton cname="提交" ename="SAVE" layout="3"></EF:EFButton>
				<EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
			</div>
		</EF:EFRegion>
	</EF:EFRegion>
<%--	<EF:EFRegion title="修改信息">--%>
<%--		<EF:EFGrid blockId="resultEdit" autoDraw="no" checkMode="single,row">--%>
<%--			<EF:EFComboColumn ename="modification" cname="修改字段" width="100" filter="contains">--%>
<%--				<EF:EFOption label="合同号" value="contractNo"/>--%>
<%--				<EF:EFOption label="固资名称" value="goodsName"/>--%>
<%--				<EF:EFOption label="规格型号" value="model"/>--%>
<%--				<EF:EFOption label="资产单位" value="unit"/>--%>
<%--				<EF:EFOption label="制造厂商" value="manufacturer"/>--%>
<%--				<EF:EFOption label="供应商" value="surpName"/>--%>
<%--				<EF:EFOption label="发票号" value="invoiceNo"/>--%>
<%--				<EF:EFOption label="购入日期" value="buyDate"/>--%>
<%--				<EF:EFOption label="使用日期" value="useDate"/>--%>
<%--			</EF:EFComboColumn>--%>
<%--			<EF:EFColumn ename="before" cname="修改前" width="100" enable="false"/>--%>
<%--			<EF:EFColumn ename="after" cname="修改后" width="100" />--%>
<%--		</EF:EFGrid>--%>
<%--	</EF:EFRegion>--%>
</EF:EFPage>