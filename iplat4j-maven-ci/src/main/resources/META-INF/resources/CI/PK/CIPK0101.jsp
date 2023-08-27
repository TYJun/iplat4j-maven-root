<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>库存盘库信息</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="盘库信息" showClear="true">
		<div class="row">
			<EF:EFInput ename="wareHouseName" cname="仓库名称" readonly="true"/>
			<EF:EFInput ename="invenBillNo" cname="盘点单号" readonly="true"/>
			<EF:EFInput ename="type" cname="操作" type="hidden"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="盘库明细信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="row">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="invenBillDetailNo" cname="盘点明细号" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="150" enable="false" align="center"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" enable="false" hidden="true"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="60" enable="false" align="center"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="60" enable="false" align="center"/>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
			<EF:EFColumn ename="unitName" cname="物资单位" width="60" enable="false" align="center"/>
			<EF:EFColumn ename="beforeInvenNum" cname="盘点前数量" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="afterInvenNum" cname="盘点后数量" width="100" align="center"/>
			<EF:EFColumn ename="price" cname="盘点单价" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript">
$(function() {
	//页面按钮隐藏
	var op = $("#type").val();
	if (op == "see") {
		$("#PASS").css("display", "none");
		$("#COMFIRM").css("display", "none");
	}else if (op == "pdxxxwh") {
		$("#PASS").css("display", "none");
	}else{
		$("#COMFIRM").css("display", "none");
	}
});
</script>