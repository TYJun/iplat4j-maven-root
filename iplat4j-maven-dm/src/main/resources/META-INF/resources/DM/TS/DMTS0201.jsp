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
			<EF:EFInput ename="roomId" cname="roomId" colWidth="5" type="hidden"/>
			<EF:EFInput ename="bedNo" cname="bedNo" colWidth="5" type="hidden"/>
			<EF:EFInput ename="remainingBedNum" cname="remainingBedNum" colWidth="5" type="hidden"/>
			<EF:EFDatePicker ename="currentMonth" cname="当前年月" required="true"
							 type="text"  role="date" format="yyyy-MM" parseFormats="['yyyy-mm']"
							 placeholder="请选择当前录入的年月份" readonly="true"/>
			<EF:EFInput ename="currentRent" cname="当月实际房租(元)" colWidth="5"  type="text" required="true"
						placeholder="请输入当月实际房租" />
			<EF:EFInput ename="currentManageFee" cname="当月实际管理费(元)" colWidth="5"  type="text" required="true"
						placeholder="请输入当月实际管理费" />
			<EF:EFInput ename="waterPriece" cname="当月水费(元)" colWidth="5"  type="text" required="true"
						placeholder="请输入当月水费" />
			<EF:EFInput ename="elecPriece" cname="当月电费(元)" colWidth="5"  type="text" required="true"
						placeholder="请输入当月电费" />
		</div>
	</EF:EFRegion>
</EF:EFPage>

