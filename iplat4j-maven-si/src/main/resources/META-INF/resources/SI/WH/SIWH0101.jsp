<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>仓库新增/编辑</title>
<EF:EFPage>
	<EF:EFRegion id="result" title="仓库信息" fitHeight="true">
		<EF:EFInput ename="wareHouseNo" cname="仓库编号" bindId="wareHouseNo"
			colWidth="5" ratio="2:8" type="text" required="true"
			placeholder="请输入仓库编号" />
		<EF:EFInput ename="wareHouseName" cname="仓库名称" bindId="wareHouseName"
			colWidth="5" ratio="2:8" type="text" required="true"
			placeholder="请输入仓库名称" />
		<EF:EFSelect ename="wareHouseType" cname="仓库类型" colWidth="5" ratio="2:8">
			<EF:EFCodeOption codeName="wilp.si.wareHouseType"/>
		</EF:EFSelect>
		<EF:EFSelect ename="priceType" cname="计价方式" colWidth="5" ratio="2:8">
			<EF:EFCodeOption codeName="wilp.si.priceType"/>
		</EF:EFSelect>
		<EF:EFMultiSelect ename="workNo" cname="仓库管理员" colWidth="5" ratio="2:8" autoClose="false" filter="contains">
			<EF:EFOptions blockId="person" textField="name" valueField="workNo"/>
		</EF:EFMultiSelect>
		<%--<EF:EFTreeInput ename="matTypeNum" cname="物资分类"
					serviceName="SIKC01" methodName="getMateriaType" textField="text"
					valueField="label" hasChildren="leaf" root="{label: 'root',text: '分类'}"
					popupTitile="上级分类" clear="true" placeholder="请选择" colWidth="5" ratio="2:8">
		</EF:EFTreeInput>--%>
		<EF:EFInput type="hidden" ename="type" cname="操作类型" />
		<EF:EFInput type="hidden" ename="workName" cname="操作类型" />
		<EF:EFInput type="hidden" ename="id" cname="主键" />
		<div style="height: 50px; line-height: 50px"></div>
	</EF:EFRegion>
</EF:EFPage>
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