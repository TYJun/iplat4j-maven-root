<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="巡检NFC卡片管理">
	<div class="row">
		<EF:EFRegion id="inqu" title="查询区">
			<div class="row">
				<EF:EFInput ename="spotNum" cname="地点编码" />
				<EF:EFInput ename="spotName" cname="地点名称" />
				<EF:EFInput ename="NFCCode" cname="NFC卡号" />
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="数据集" fitHeight="true">
			<EF:EFGrid blockId="result" autoDraw="no" serviceName="DIFK01" checkMode="single">
				<EF:EFColumn ename="id" cname="主键" readonly="true" hidden="true"/>
				<EF:EFColumn ename="spotNum" cname="地点编码" readonly="true" />
				<EF:EFColumn ename="spotName" cname="地点名称" readonly="true" />
				<EF:EFColumn ename="NFCCode" cname="NFC卡号" readonly="true" />
				<%-- <EF:EFColumn ename="machineNum" cname="设备数量" readonly="true" /> --%>
			</EF:EFGrid>
		</EF:EFRegion>
		<EF:EFWindow id="popData" url="" lazyload="true" width="700" height="350" title="NFC发卡" modal="true" />
	</div>
</EF:EFPage>