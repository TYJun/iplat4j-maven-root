<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="安全巡查NFC卡片管理">
		<EF:EFRegion id="inqu" title="查询区">
				<EF:EFInput ename="inqu_status-0-objName" cname="巡查对象:" colWidth="5" ratio="4:8"/>
				<EF:EFInput ename="inqu_status-0-spotName" cname="巡查地点名称:" colWidth="5" ratio="4:8"/>
				<EF:EFInput ename="inqu_status-0-spotNum" cname="巡查地点编码:" colWidth="5" ratio="4:6"/>
				<EF:EFInput ename="inqu_status-0-NFCCode" cname="NFC卡号:" colWidth="5" ratio="4:8"/>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="数据集" fitHeight="true">
			<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" rowNo="true">
				<%-- <EF:EFColumn ename="id" cname="主键" readonly="true" hidden="true"/>
				<EF:EFColumn ename="spotNum" cname="地点编码" readonly="true" />
				<EF:EFColumn ename="spotName" cname="地点名称" readonly="true" /> --%>
				<EF:EFColumn ename="id" width="150" cname="对象id" enable="false" hidden="true"/>
				<EF:EFColumn ename="spotId" width="150" cname="地点id" enable="false" hidden="true"/>
				<EF:EFColumn ename="objName" width="150" cname="巡查对象" enable="false" align="center"/>
				<EF:EFColumn ename="spotName" width="150" cname="巡查地点" enable="false" align="center"/>
				<EF:EFColumn ename="spotCode" width="150" cname="巡查地点编码" enable="false" align="center"/>
				<EF:EFColumn ename="NFCCode" width="150" cname="NFC卡号" enable="false" align="center"/>
			</EF:EFGrid>
		</EF:EFRegion>
		<EF:EFWindow id="popData" url="" lazyload="true" width="700" height="350" title="NFC发卡" modal="true" />
	</div>
</EF:EFPage>