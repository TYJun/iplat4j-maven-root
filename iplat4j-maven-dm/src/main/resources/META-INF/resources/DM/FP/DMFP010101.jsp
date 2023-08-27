<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="宿舍选择页面">
	<EF:EFRegion id="inqu" title="查询区">
		<div class="row">
			<EF:EFInput ename="roomIdList" cname="ID" type="hidden"/>
			<EF:EFInput ename="building" cname="楼" />
			<EF:EFInput ename="floor" cname="层" />
			<EF:EFInput ename="roomName" cname="宿舍全称" />
		</div>
		<div class="row">
			<EF:EFInput ename="roomNo" cname="房间号" />
			<EF:EFSelect ename="typeCode" cname="房间类型" >
				<EF:EFOption label="请选择房间类型" value=""/>
				<EF:EFOption label="待定" value="2"/>
				<EF:EFOption label="男生宿舍" value="1"/>
				<EF:EFOption label="女生宿舍" value="0"/>
			</EF:EFSelect>
			<EF:EFSelect ename="dormProperties" cname="房间属性" >
				<EF:EFOption label="请选择房间属性" value=""/>
				<EF:EFOption label="学生宿舍" value="学生宿舍"/>
				<EF:EFOption label="职工宿舍" value="职工宿舍"/>
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="房间信息" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DMFP010101">
			<EF:EFColumn ename="roomId" cname="id" readonly="true" hidden="true"/>
			<EF:EFColumn ename="building" cname="楼" readonly="true" width="100" align="center" />
			<EF:EFColumn ename="floor" cname="层" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="roomNo" cname="房间号" readonly="true" width="70" align="center"/>
			<EF:EFColumn ename="roomName" cname="宿舍全称" readonly="true" width="120" align="center"/>
			<EF:EFColumn ename="bedNum" cname="床位数" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="dormProperties" cname="宿舍属性" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="typeCodeName" cname="宿舍类型" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="remainingBedNum" cname="剩余床位" readonly="true" width="60" align="center"/>
			<EF:EFColumn ename="dormPosition" cname="宿舍位置" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="elevatorRoom" cname="电梯房" readonly="true" width="50" align="center" />
			<EF:EFColumn ename="priBathroom" cname="独立卫生间" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="dormArea" cname="房屋面积" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="rent" cname="房租" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="manageFee" cname="管理费" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="annualFee" cname="年费" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="note" cname="备注信息" readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>