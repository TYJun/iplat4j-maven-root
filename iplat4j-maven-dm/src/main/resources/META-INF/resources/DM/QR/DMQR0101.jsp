<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="{pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result">
		<div class="row">
			<EF:EFInput ename="idList" cname="id" colWidth="5" type="hidden"/>
			<EF:EFInput ename="manIdList" cname="manId" colWidth="5" type="hidden"/>
			<EF:EFInput ename="manNoList" cname="manNo" colWidth="5" type="hidden"/>
			<EF:EFDatePicker ename="actualInDate" cname="实际入住时间"
							 colWidth="5"  type="text" required="true"
							 placeholder="请选择该人员实际入住的时间" />
		</div>
	</EF:EFRegion>
</EF:EFPage>

