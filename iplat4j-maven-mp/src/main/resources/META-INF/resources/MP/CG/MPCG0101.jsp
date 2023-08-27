<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>采购计划新增</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="采购计划">
		<div class="row">
			<EF:EFInput ename="id" cname="主键" readonly="true" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-recCreateTime" cname="创建日期" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-recCreator" cname="创建人" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-purchaseRemark" cname="采购计划描述" />
			<EF:EFSelect ename="inqu_status-0-purchaseType" cname="采购类别" resultId="purchaseType" optionLabel="请选择"
						 serviceName="MPPZ03" methodName="queryConfigs" required="true"
						 textField="purchaseTypeCost" valueField="value">
			</EF:EFSelect>

		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="采购计划明细列表">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true"  readonly="true"  rowNo="true">
			<EF:EFColumn ename="id" cname="id" hidden="true" />
			<EF:EFColumn ename="matNum" cname="物资编码"  align="center" enable="false"/>
			<EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false"/>
			<EF:EFColumn ename="matTypeId" cname="物资分类编码" hidden="true"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" align="center" enable="false"/>
			<EF:EFColumn ename="matModel" cname="物资型号" align="center" enable="false"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" align="center" enable="false"/>
			<EF:EFColumn ename="unit" cname="计量单位" hidden="true"/>
			<EF:EFColumn ename="unitName" cname="计量单位" align="center" enable="false"/>
			<EF:EFColumn ename="price" cname="单价" align="center" />
			<EF:EFColumn ename="num" cname="采购数量" align="center" />
			<EF:EFColumn ename="pictureUri" cname="图片" hidden="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData1" title="物资信息" url=" " lazyload="true" width="90%" height="90%"></EF:EFWindow>