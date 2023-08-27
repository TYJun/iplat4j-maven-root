<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="资产变更录入">
	<EF:EFRegion id="info" head="hidden">
		<div class="row">
			<EF:EFInput ename="info-0-faInfoId" cname="faInfoId" type="hidden"/>
			<EF:EFInput ename="type" cname="操作类型" type="hidden"/>
			<EF:EFInput ename="info-0-archiveFlag" cname="归档标记" type="hidden"/>
			<EF:EFInput ename="info-0-goodsNum" cname="资产编码" readOnly="true"/>
			<EF:EFInput ename="info-0-goodsName" cname="资产名称" required="true"/>
			<EF:EFPopupInput ename="info-0-goodsTypeCode" cname="资产类别" required="true" readOnly="true"
							 containerId="ef_popup_grid" popupWidth="900" popupHeight="500" popupTitle="资产类别"
							 resizable="true" center="true">
			</EF:EFPopupInput>
			<EF:EFInput ename="info-0-goodsClassifyCode" cname="资产类别编码"  required="true" type="hidden"/>
			<EF:EFInput ename="info-0-goodsClassifyName" cname="资产类别名称"  required="true" type="hidden"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-rfidCode" cname="RFID码" readOnly="true"/>
			<EF:EFInput ename="info-0-model" cname="型号规格"  />
			<EF:EFSelect ename="info-0-unitName"  cname="资产单位" >
				<EF:EFOption label="KG" value="KG"/>
				<EF:EFOption label="个" value="个"/>
				<EF:EFOption label="台" value="台"/>
				<EF:EFOption label="部" value="部"/>
				<EF:EFOption label="件" value="件"/>
			</EF:EFSelect>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-deptNum" cname="所属科室编码" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-deptNum_textField" cname="所属科室" readonly="true"/>
			<EF:EFInput ename="info-0-manufacturer" cname="制造厂商"  />
			<EF:EFInput ename="info-0-surpName" cname="供应商"  />
		</div>
		<div class="row">
			<EF:EFDateSpan startName="info-0-buyDate" startCname="购入日期"
							endName="info-0-useDate" endCname="使用日期"
							startRatio="4:8" endRatio="4:8" readonly="true"/>
			<EF:EFInput ename="info-0-statusCodeMean" cname="资产状态" readonly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-build" cname="楼" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-floor" cname="层" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-installLocationNum" cname="安装位置编码" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-installLocation" cname="安装位置" readonly="true" type="hidden"/>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label data-for="newBuild" class="col-xs-4 control-label">
						<span>楼</span>
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
						<span>层</span>
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
						<span style="color:red">*</span>地点
					</label>
					<div class="col-xs-8">
						<input id="newGoodsLocation" name="newGoodsLocation"
							   placeholder="地点"
							   ondblclick="showAll('newGoodsLocation')">
					</div>
				</div>
			</div>
			<EF:EFInput ename="newGoodsLocationNum" cname="地点编码" type="hidden"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-amount" cname="数量" value="1" format="{0:n0}"/>
			<EF:EFInput ename="info-0-price" cname="单价(元)" format="{0:c}"/>
			<EF:EFInput ename="info-0-buyCost" cname="资产原值(元)" format="{0:c}" placeholder="数量*单价(元)" onFocus="countBuyCost()"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-estimateCost" cname="暂估价值(元)" format="{0:c}" placeholder="暂估价值(元)"/>
			<EF:EFInput ename="info-0-residualRate" cname="残值率(%)" format="{0:n0}"/>
			<EF:EFInput ename="info-0-estimatedResidualValue" cname="预计净残值(元)" format="{0:c}" placeholder="资产原值(元)*残值率(%)" onFocus="countEstimatedResidualValue()"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-useYears" cname="使用年限" format="{0:n0}" placeholder="使用年限(年)"/>
			<EF:EFSelect ename="info-0-fundsSource"  cname="资金来源" >
				<EF:EFOption label="医院自筹" value="01"/>
				<EF:EFOption label="复合费用来源" value="02"/>
				<EF:EFOption label="财政资金" value="03"/>
				<EF:EFOption label="教育资金" value="04"/>
			</EF:EFSelect>
			<EF:EFInput ename="info-0-finaceClassNum" cname="财务类编码"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-invoiceNo" cname="发票号"/>
			<EF:EFDatePicker ename="info-0-invoiceDate" cname="发票日期" role="date" readonly="true"
							 format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']" />
			<EF:EFDatePicker ename="info-0-inAccountDate" cname="入账时间" role="date" readonly="true"
							 format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']" />
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-monthDepreciation" cname="月折旧值(元)" format="{0:c}" placeholder="资产原值(元)/(使用年限*12)" onFocus="countMonthDepreciation()"/>
			<EF:EFInput ename="info-0-totalDepreciation" cname="累计折旧值(元)" format="{0:c}" placeholder="月折旧值(元)*使用月份" onFocus="countTotalDepreciation()"/>
			<EF:EFInput ename="info-0-netAssetValue" cname="资产净值(元)" format="{0:c}" placeholder="资产原值(元)-累计折旧值(元)"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-recCreateName" cname="创建人" readonly="true"/>
			<EF:EFDatePicker ename="info-0-recCreateTime" cname="创建时间" role="date" readonly="true" disabled="true"
							 format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']"/>
			<EF:EFInput ename="info-0-statusCodeMean" cname="审批状态" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-remark" cname="备注" colWidth="8" type="textarea" ratio="2:10"
						rows="3" placeholder="不能超过200字" maxLength="200"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-changeReason" cname="变更理由" colWidth="8" type="textarea" ratio="2:10"
						rows="3" placeholder="不能超过200字" maxLength="200"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
		</div>
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
							<EF:EFColumn ename="typeCode" cname="资产类别编码" width="100" enable="false" align="center"/>
							<EF:EFColumn ename="typeName" cname="资产类别名称" width="100" enable="false" align="center"/>
							<EF:EFColumn ename="parentId" cname="上级资产类别ID" width="100" enable="false" align="center" hidden="true"/>
							<EF:EFColumn ename="parentName" cname="上级资产类别名称" width="100" enable="false" align="center"/>
							<EF:EFColumn ename="level" cname="类别层级" width="100" enable="false" align="center" hidden="true"/>
							<EF:EFColumn ename="remark" cname="备注" width="100" enable="false" align="center"/>
						</EF:EFGrid>
					</EF:EFRegion>
				</div>
			</EF:EFPage>
		</div>
	</EF:EFRegion>
</EF:EFPage>