<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">

		<div class="row">
			<EF:EFInput ename="variableName" cname="群组名称"
				colWidth="3" ratio="2:4" required="true" placeholder="请输入群组名称"
			/>
		</div>
		
	</EF:EFRegion>
</EF:EFPage>
