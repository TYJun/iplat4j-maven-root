<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>生成盘库单</title>
<EF:EFPage>
	<br />
	<br />
	<EF:EFSelect ename="wareHouseNo" cname="仓库名称：" required="true"
		resultId="result" textField="wareHouseName"
		valueField="wareHouseNo" serviceName="SIWH01"
		methodName="queryWareHouse" optionLabel="请选择" colWidth="10" ratio="3:8">
	</EF:EFSelect>
	<br />
	<br />
	<br />
	<EF:EFTreeInput ename="matTypeNum" cname="物资分类" 
		serviceName="SIKC01" methodName="getMateriaType" textField="text"
		valueField="label" hasChildren="leaf" root="{label: 'root',text: '分类'}"
		popupTitile="上级分类" clear="true" placeholder="请选择" colWidth="10" ratio="3:8">
	</EF:EFTreeInput>
	<br />
	<br />
	<br />
	<br />
	<div id="w2" class="vert-center text-center" style="height: 64px;">
		<button id="COMFIRM" type="button" class="i-btn-lg">确定</button>
		<button id="CLOSE" type="button" class="i-btn-lg">关闭</button>
	</div>
</EF:EFPage>