<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>人员信息新增</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="入职时间">
		<EF:EFInput ename="id" cname="id" type="hidden"/>
		<EF:EFDatePicker ename="inTime" cname="入职时间" role="date" colWidth="3" ratio="3:8" format="yyyy-MM-dd"
						 parseFormats="['yyyy-mm-dd']" readonly="true" required="true"/>
	</EF:EFRegion>
</EF:EFPage>