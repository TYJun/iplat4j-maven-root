<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="{pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result">
		<div class="row">
			<EF:EFInput ename="id" cname="id" colWidth="5" type="hidden"/>
			<EF:EFInput ename="manId" cname="manId" colWidth="5" type="hidden"/>
			<EF:EFInput ename="changeRoomNote" type="textarea" required="true"
						colWidth="5"  rows="3" cname="换宿原因" placeholder="请输入换宿原因"/>
		</div>
	</EF:EFRegion>
</EF:EFPage>

