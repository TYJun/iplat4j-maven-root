<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<title>宿舍满意度评价历史</title>
<EF:EFPage title="宿舍满意度评价历史">
	<EF:EFRegion id="inqu" title="查询区" showClear="true">
		<div class="row">
			<EF:EFInput ename="manId" cname="人员入住信息表id" type="hidden"/>
		</div>
		<div class="row">
			<EF:EFDatePicker ename="evalMonth" cname="当前年月"
							 type="text"  role="date" format="yyyy-MM" parseFormats="['yyyy-mm']"
							 placeholder="请选择要查询的评价结果的年月份" readonly="true"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="相关信息" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DMPJ0102" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="id" cname="id" readonly="true" hidden="true"/>
			<EF:EFColumn ename="manId" cname="manId" readonly="true" hidden="true"/>
			<EF:EFColumn ename="roomId" cname="roomId" readonly="true" hidden="true"/>
			<EF:EFColumn ename="manNo" cname="工号" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="manName" cname="姓名" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="building" cname="楼" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="floor" cname="层" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="roomNo" cname="宿舍号" readonly="true" width="70" align="center"/>
			<EF:EFColumn ename="evalMonth" cname="评价年月份" readonly="true" width="80" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popDataShow" url="" lazyload="true" width="90%" height="90%" title="满意度评价结果" modal="true" />
</EF:EFPage>