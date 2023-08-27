<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>仓库新增/编辑</title>
<EF:EFPage>
	<EF:EFRegion id="result" title="仓库信息" fitHeight="true">
		<br />
		<%--<EF:EFInput ename="wareHouseNo" cname="仓库编号" bindId="wareHouseNo"
			colWidth="5" ratio="2:8" type="text" required="true"
			placeholder="请输入仓库编号" />
		<br />--%>
		<EF:EFInput ename="wareHouseName" cname="仓库名称" bindId="wareHouseName"
			colWidth="5" ratio="2:8" type="text" required="true"
			placeholder="请输入仓库名称" />
		<br />
		<EF:EFSelect ename="wareHouseType" cname="仓库类型" colWidth="5" ratio="2:8">
			<EF:EFCodeOption codeName="wilp.ci.wareHouseType"/>
		</EF:EFSelect>
		<br />
		<EF:EFSelect ename="priceType" cname="计价方式" colWidth="5" ratio="2:8">
			<EF:EFCodeOption codeName="wilp.ci.priceType"/>
		</EF:EFSelect>
		<EF:EFInput type="hidden" ename="type" cname="操作类型" />
		<EF:EFInput type="hidden" ename="id" cname="主键" />
		<div style="height: 50px; line-height: 50px"></div>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript">
$(function() {
	var op = $("#type").val();//获取操作
	//通过点击查看弹窗是隐藏提交按钮
	if (op == "see") {
		$("#SUBMIT").css("display", "none");
		$("#CANCEL").css("display", "none");
	}
});
</script>