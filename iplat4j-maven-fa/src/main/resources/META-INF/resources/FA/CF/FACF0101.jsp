<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="资产按数量拆分">
	<EF:EFRegion title="资产信息">
		<EF:EFInput ename="goodsNum" cname="资产编号" readOnly="true"/>
		<EF:EFInput ename="goodsName" cname="资产名称" readOnly="true"/>
		<EF:EFInput ename="goodsTypeName" cname="资产类别名称" readOnly="true"/>
		<EF:EFInput ename="buyCost" cname="资产原值(元)" readOnly="true"/>
		<EF:EFInput ename="totalDepreciation" cname="累计折旧值(元)" readOnly="true"/>
		<EF:EFInput ename="netAssetValue" cname="资产净值(元)" readOnly="true"/>
	</EF:EFRegion>
	<EF:EFRegion title="业务信息">
		<div class="row">
			<EF:EFInput ename="splitReason" cname="拆分原因" colWidth="8" type="textarea" ratio="2:10"
						required="true" rows="3" placeholder="不能超过200字" maxLength="200"/>
		</div>
		<div class="row">
			<div class="col-md-8">
				<div class="form-group">
					<label data-for="splitNumber" class="col-xs-1 control-label"></label>
					<div class="col-xs-10">
						<span style="font-size: 16px">将当前卡片拆分成</span><input id="splitNumber" type="number" min="2" step="1" style="width: 50px"><span style="font-size: 16px">张卡片，拆分后的结果如下</span>
					</div>
				</div>
			</div>
		</div>
		<EF:EFGrid blockId="resultSplitByNumber" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
				   checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="goodsId" cname="goodsId" align="center" width="100" hidden="true"/>
			<EF:EFColumn ename="goodsNum" cname="资产编号" align="center" enable="false" width="200"/>
			<EF:EFColumn ename="amount" cname="数量" align="center" width="100"/>
			<EF:EFColumn ename="price" cname="单价" align="center" width="100" enable="false"/>
			<EF:EFColumn ename="buyCost" cname="资产原值" align="center" width="100"/>
			<EF:EFColumn ename="totalDepreciation" cname="累计折旧" align="center" width="100"/>
			<EF:EFColumn ename="netAssetValue" cname="资产净值" align="center" width="100"/>
			<EF:EFColumn ename="estimatedResidualValue" cname="预计净残值" align="center" width="100"/>
		</EF:EFGrid>
		<div class="row">
			<div class="button-region" id="buttonDiv">
				<EF:EFButton cname="提交" ename="SAVE" layout="3"></EF:EFButton>
				<EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
			</div>
		</div>
	</EF:EFRegion>
</EF:EFPage>