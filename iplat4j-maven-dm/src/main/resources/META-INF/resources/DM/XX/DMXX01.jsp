<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<title>宿舍信息管理</title>
<EF:EFPage title="宿舍信息管理">
	<EF:EFRegion id="inqu" title="查询区" showClear="true">
		<div class="row">
			<EF:EFInput ename="building" cname="楼" />
			<EF:EFInput ename="floor" cname="层" />
			<EF:EFInput ename="roomNo" cname="宿舍号" />
		</div>
		<div class="row">
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
			<EF:EFSelect ename="openRoom" cname="是否开放选房" >
				<EF:EFOption label="请选择是否开放选房" value=""/>
				<EF:EFOption label="已开放" value="1"/>
				<EF:EFOption label="未开放" value="0"/>
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="房间信息" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DMXX01" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="id" cname="id" readonly="true" hidden="true"/>
			<EF:EFColumn ename="building" cname="宿舍楼" readonly="true" width="100" align="center"  />
			<EF:EFColumn ename="floor" cname="宿舍层" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="roomNo" cname="宿舍号" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="dormProperties" cname="宿舍属性" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="typeCodeName" cname="宿舍类型" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="openRoomMean" cname="开放选房" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="bedNum" cname="床位数" readonly="true" width="50" align="center" />
			<EF:EFColumn ename="remainingBedNum" cname="剩余床位数" readonly="true" width="60" align="center"/>
			<EF:EFColumn ename="dormPosition" cname="宿舍位置" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="elevatorRoom" cname="电梯房" readonly="true" width="50" align="center" />
			<EF:EFColumn ename="priBathroom" cname="独立卫生间" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="dormArea" cname="房屋面积" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="rent" cname="房租" readonly="true" width="50" align="center" />
			<EF:EFColumn ename="manageFee" cname="管理费" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="annualFee" cname="年费" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="note" cname="备注信息" readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="新增宿舍" modal="true" />
	<EF:EFWindow id="popDataModify" url="" lazyload="true" width="100%" height="100%" title="编辑宿舍信息" modal="true" />
	<EF:EFWindow id="popDatashow" url="" lazyload="true" width="100%" height="100%" title="查看宿舍详情" modal="true" />
</EF:EFPage>