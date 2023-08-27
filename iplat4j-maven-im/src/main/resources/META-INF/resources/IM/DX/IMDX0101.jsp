<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctxs" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="">
	<div class="row" id="idName">
		<EF:EFInput colWidth="4" ratio="2:8" ename="id" cname="id" />
		<EF:EFInput colWidth="4" ratio="2:8" ename="spotId2" cname="spotId" />
	</div>
		<EF:EFInput ename="objectName" cname="对象名称:" required="true" colWidth="4" ratio="3:8"/>
		<EF:EFPopupInput ename="fixedPlace" cname="地点名称:" colWidth="4" ratio="3:8" readonly="true" popupWidth = "550"
		popupType="ServiceGrid" popupTitle="地点" required="true" serviceName="IMDX0101" methodName="querySpot" resultId="spot" autofit="true"
		valueField="spotNum" textField="spotName" backFillFieldIds="spotName" backFillColumnIds="spotName" columnEnames="spotName" columnCnames="地点"/>
		<EF:EFInput ename="remark" cname="备注:" colWidth="4" ratio="3:8"/>
	</EF:EFRegion>
</EF:EFPage>
<script>
//隐藏id输入框
$("#idName").hide(); 
</script>