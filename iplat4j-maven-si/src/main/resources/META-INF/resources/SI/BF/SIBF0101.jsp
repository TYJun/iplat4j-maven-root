<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>报废新增/编辑</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="报废信息" showClear="true">
		<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-scrapDate" cname="报废日期" role="date" required="true" colWidth="5"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" />
			<EF:EFInput ename="inqu_status-0-scrapReason" cname="报废原因" colWidth="5" required="true"
						type="textarea" placeholder="不能超过200字" />
			<EF:EFInput ename="inqu_status-0-id" cname="id" type="hidden"/>
			<EF:EFInput ename="type" cname="操作" type="hidden"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="物资批次列表">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true">
			<EF:EFColumn ename="batchNo" cname="批次号" width="150" align="center" enable="false"/>
			<EF:EFColumn ename="wareHouseNo" cname="仓库编码"  hidden="true"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库名称" align="center" enable="false"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="150" enable="false" align="center"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="150" enable="false" align="center"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matTypeNum" cname="物资分类编码"  hidden="true" />
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="unit" cname="单位"  hidden="true" />
			<EF:EFColumn ename="unitName" cname="物资单位" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="enterPrice" cname="单价(元)" width="80" align="center" enable="false"/>
			<EF:EFColumn ename="enterNum" cname="库存数量" width="80" align="center" enable="false"/>
			<EF:EFColumn ename="scrapNum" cname="报废数量" width="80" align="center"/>
			<EF:EFColumn ename="enterTime" cname="入库时间" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="enterDate" cname="入库日期" hidden="true" />
			<EF:EFColumn ename="surpNum" cname="供应商编码" hidden="true" />
			<EF:EFColumn ename="surpName" cname="供应商名称" hidden="true" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 库存批次选择 -->
<EF:EFWindow id="popData" title="库存批次选择" url=" " lazyload="true" width="90%" height="85%"></EF:EFWindow>