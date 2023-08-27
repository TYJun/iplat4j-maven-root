<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>资产卡片信息</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="goodsName" cname="固资资产名称"  colWidth="8"/>
			<EF:EFButton cname="查询" ename="queryFAInfoType" layout="1" class="k-button"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="固资资产列表" >
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="multiple,row" readonly="true"
				   serviceName="FADA01" queryMethod="queryAvailableFaInfo" height="305">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="goodsNum" cname="资产编码" align="center" locked="true" enable="false" width="200"/>
			<EF:EFColumn ename="goodsName" cname="资产名称" align="center" locked="true" enable="false"/>
			<EF:EFColumn ename="model" cname="型号规格" align="center" locked="true" enable="false"/>
			<EF:EFColumn ename="manufacturer" cname="制造厂商" align="center" locked="true" enable="false"/>
			<EF:EFColumn ename="buyCost" cname="资产原值" align="center" locked="true" enable="false"/>
			<EF:EFColumn ename="useYears" cname="使用年限" align="center" enable="false" />
			<EF:EFColumn ename="buyDate" cname="购入日期" align="center" enable="false" />
			<EF:EFColumn ename="useDate" cname="使用日期" align="center" enable="false" />
			<EF:EFColumn ename="deptNum" cname="所属科室编码" align="center" enable="false" hidden="true"/>
			<EF:EFColumn ename="deptName" cname="所属科室" align="center" enable="false" />
			<EF:EFColumn ename="build" cname="楼" align="center" enable="false" />
			<EF:EFColumn ename="floor" cname="层" align="center" enable="false" />
			<EF:EFColumn ename="installLocation" cname="地点" align="center" enable="false" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>