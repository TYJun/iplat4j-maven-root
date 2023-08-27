<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="{pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result">
		<div class="row">
			<EF:EFInput ename="roomId" cname="roomId" colWidth="5" type="hidden"/>
			<EF:EFInput ename="manId" cname="manId" colWidth="5" type="hidden"/>
			<EF:EFInput ename="currentMonth" cname="currentMonth" colWidth="5" type="hidden"/>
			<EF:EFInput ename="extraCharges" cname="额外费用" colWidth="5" required="true"/>
		</div>
	</EF:EFRegion>
</EF:EFPage>

