<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>采购订单编辑/详情</title>

<EF:EFPage>
	<EF:EFRegion id="inqu" title="采购订单">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-orderNo" cname="订单号" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-contName" cname="合同名称" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-supplierName" cname="供应商" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-recCreatorName" cname="创建人" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="订单明细列表">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row" >
			<EF:EFColumn ename="contNo" cname="合同号" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="matTypeId" cname="物资分类编码" hidden="true"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="unit" cname="计量单位" hidden="true"/>
			<EF:EFColumn ename="unitName" cname="计量单位" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="price" cname="含税单价(元)" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="num" cname="本次订单数量" width="100" align="center" />
			<c:if test="${type == 'see'}">
				<EF:EFColumn ename="enterNum" cname="已入库数量" width="100" align="center" />
			</c:if>
			<EF:EFColumn ename="pictureUri" cname="图片" hidden="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="物资选择" url=" " lazyload="true" width="95%" height="90%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>