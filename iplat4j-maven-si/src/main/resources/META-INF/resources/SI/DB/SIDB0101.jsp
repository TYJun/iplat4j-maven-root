<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>库存调拨信息</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="仓库信息">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-outWareHouseNo" cname="调出仓库："
				resultId="result" textField="wareHouseName" required="true"
				valueField="wareHouseNo" serviceName="SIWH01"
				methodName="selectUseWareHouse" optionLabel="请选择" colWidth="4" ratio="4:8">
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-inWareHouseNo" cname="调入仓库："
				resultId="result" textField="wareHouseName" required="true"
				valueField="wareHouseNo" serviceName="SIWH01"
				methodName="selectUseWareHouse" optionLabel="请选择" colWidth="4" ratio="4:8">
			</EF:EFSelect>
			<EF:EFInput type="hidden" ename="type" cname="操作" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="库存调拨信息">
		<EF:EFGrid blockId="result" autoDraw="no" height="400" checkMode="row">
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="unit" cname="单位" width="100" hidden="true" />
			<EF:EFColumn ename="unitName" cname="单位" width="60" enable="false" align="center"/>
			<EF:EFColumn ename="totalNum" cname="当前数量" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="transNum" cname="调拨数量" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData1" title="物资选择" url=" " lazyload="true" width="95%" height="95%"></EF:EFWindow>
<script type="text/javascript">
$(function() {
	//隐藏操作按钮
	let op = $("#type").val();
	if (op === "see") {
		$("#COMFIRM").css("display", "none");
	}
});
</script>