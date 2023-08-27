<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="{pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result">
		<div class="row">
			<EF:EFInput ename="id" cname="id" colWidth="5" type="hidden"/>
			<EF:EFInput ename="roomId" cname="roomId" colWidth="5" type="hidden"/>
			<EF:EFInput ename="manId" cname="manId" colWidth="5" type="hidden"/>
			<EF:EFInput ename="manName" cname="姓名" colWidth="5"  type="text" readonly="true"/>
			<EF:EFInput ename="roomName" cname="房间全称" colWidth="5"  type="text" readonly="true"/>
			<EF:EFInput ename="rent" cname="房租(元)" colWidth="5"  type="text" readonly="true"/>
			<EF:EFInput ename="manageFee" cname="管理费(元)" colWidth="5"  type="text" readonly="true"/>
			<EF:EFInput ename="actualRent" cname="实际房租(元)" colWidth="5"  type="text" required="true"
						placeholder="请输入实际房租" />
			<EF:EFInput ename="actualManageFee" cname="实际管理费(元)" colWidth="5"  type="text" required="true"
						placeholder="请输入实际管理费" />
			<EF:EFDatePicker ename="actualInDate" cname="实际入住时间" colWidth="5"  type="text" required="true"
							 placeholder="请选择该人员实际入住的时间" />
		</div>
	</EF:EFRegion>
</EF:EFPage>

