<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>物资库存批次选择</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称"
						 resultId="result" textField="wareHouseName" valueField="wareHouseNo"
						 serviceName="SIWH01" methodName="selectUseWareHouse" optionLabel="请选择">
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-matNumLK" cname="物资编码" />
			<EF:EFInput ename="inqu_status-0-matName" cname="物资名称" />
			<EF:EFInput ename="inqu_status-0-batchNoLK" cname="批次号" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="物资批次列表">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="row" autoBind="true" readonly="true" sort="single">
			<EF:EFColumn ename="batchNo" cname="批次号" width="150" align="center" locked="true" sort="false"/>
			<EF:EFColumn ename="wareHouseNo" cname="仓库编码"  hidden="true" sort="false"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库名称" align="center" locked="true" sort="false"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center" locked="true" sort="true"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="150" align="center" locked="true" sort="true"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="150" align="center" sort="true"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="unit" cname="单位"  hidden="true" sort="false"/>
			<EF:EFColumn ename="unitName" cname="物资单位" width="80" align="center" sort="false"/>
			<EF:EFColumn ename="enterPrice" cname="单价(元)" width="80" align="center" sort="true"/>
			<EF:EFColumn ename="enterNum" cname="库存数量" width="80" align="center" sort="true"/>
			<EF:EFColumn ename="enterTime" cname="入库时间" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="enterDate" cname="入库日期" hidden="true" sort="false"/>
			<EF:EFColumn ename="surpNum" cname="供应商编码" hidden="true" sort="false"/>
			<EF:EFColumn ename="surpName" cname="供应商名称" hidden="true" sort="false"/>
			<EF:EFColumn ename="scrapNum" cname="报废数量" hidden="true" sort="true" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>