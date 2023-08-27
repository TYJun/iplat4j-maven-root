<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!--采购科室配置信息-->
<EF:EFPage>
	<EF:EFRegion id="inqu" title="采购科室配置信息">
		<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-purchaseYear" cname="年份" format="yyyy" required="true"/>
			<EF:EFSelect ename="inqu_status-0-purchaseType" cname="采购类别" optionLabel="请选择" required="true">
				<EF:EFCodeOption codeName="wilp.mp.purchase.purchaseType"/>
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-purchaseCost" cname="采购份额(元)" required="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" type="hidden" />
			<EF:EFInput ename="type" type="hidden" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="确定" ename="SUBMIT" layout="0" ></EF:EFButton>
			<EF:EFButton cname="关闭" ename="CANCEL" layout="0" ></EF:EFButton>
		</div>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/MP/common/mp-autoComplete.js"></script>
<script type="text/javascript">
	$(function() {
		let op = $("#type").val();//获取操作
		//通过点击查看弹窗是隐藏提交按钮
		if (op == "see") {
			$("#SUBMIT").css("display", "none");
			$("#CANCEL").css("display", "none");
		}
	});
</script>