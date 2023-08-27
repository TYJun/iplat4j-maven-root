<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="资产按价值拆分">
	<EF:EFRegion title="资产信息" id="info">
		<EF:EFInput ename="type" cname="操作类型" readOnly="true" type="hidden"/>
		<EF:EFInput ename="info-0-goodsNumMax" cname="资产编号" readOnly="true" type="hidden"/>
		<EF:EFInput ename="info-0-goodsNum" cname="资产编码" readOnly="true"/>
		<EF:EFInput ename="info-0-goodsName" cname="资产名称" readOnly="true"/>
		<EF:EFInput ename="info-0-goodsTypeName" cname="资产类别名称" readOnly="true"/>
		<EF:EFInput ename="info-0-buyCost" cname="资产原值(元)" readOnly="true"/>
		<EF:EFInput ename="info-0-totalDepreciation" cname="累计折旧值(元)" readOnly="true"/>
		<EF:EFInput ename="info-0-netAssetValue" cname="资产净值(元)" readOnly="true"/>
		<EF:EFInput ename="info-0-splitReason" cname="拆分原因" colWidth="8" type="textarea" ratio="2:10"
					required="true" rows="3" placeholder="不能超过200字" maxLength="200"/>
	</EF:EFRegion>
	<EF:EFRegion title="业务信息">
		<div class="row">
			<div class="col-md-8">
				<div class="form-group">
					<label data-for="splitNumber" class="col-xs-5 control-label"></label>
					<div class="col-xs-6">
						<span style="font-size: 16px">将当前卡片拆分成</span><input id="splitNumber" type="number" min="2" step="1" style="width: 50px"><span style="font-size: 16px">张卡片，拆分后的结果如下</span>
					</div>
				</div>
			</div>
		</div>
		<EF:EFGrid blockId="resultSplitByValue" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
				   checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="goodsId" cname="goodsId" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="goodsNum" cname="资产编码" align="center" enable="false" width="200"/>
			<EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="200"/>
			<EF:EFColumn ename="amount" cname="数量" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="buyCost" cname="资产原值" align="center" width="200"/>
			<EF:EFColumn ename="totalDepreciation" cname="累计折旧" align="center" width="200"/>
			<EF:EFColumn ename="netAssetValue" cname="资产净值" align="center" width="200"/>
		</EF:EFGrid>
		<div class="row">
			<div class="button-region" id="apply" style="display: none">
				<EF:EFButton cname="提交" ename="save" layout="3"></EF:EFButton>
				<EF:EFButton cname="关闭" ename="applyClose" layout="3"></EF:EFButton>
			</div>
			<div class="button-region" id="confirm" style="display: none">
				<EF:EFButton cname="通过" ename="confirmPass" layout="3"></EF:EFButton>
				<EF:EFButton cname="驳回" ename="confirmReject" layout="3"></EF:EFButton>
				<EF:EFButton cname="关闭" ename="confirmClose" layout="3"></EF:EFButton>
			</div>
			<div class="button-region" id="look" style="display: none">
				<EF:EFButton cname="关闭" ename="lookClose" layout="3"></EF:EFButton>
			</div>
		</div>
	</EF:EFRegion>
</EF:EFPage>