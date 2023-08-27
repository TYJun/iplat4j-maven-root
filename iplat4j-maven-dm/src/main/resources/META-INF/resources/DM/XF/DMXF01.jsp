<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<title>宿舍在线选房</title>
<EF:EFPage title="宿舍在线选房">
	<EF:EFRegion id="inqu" title="查询区" showClear="true">
		<div class="row">
			<EF:EFInput ename="building" cname="宿舍楼" />
			<EF:EFInput ename="floor" cname="宿舍层" />
			<EF:EFInput ename="roomNo" cname="房间号" />
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
			<div id="hiddenDiv">
				<EF:EFSelect ename="manNature" cname="人员大类" defaultValue="职工">
					<EF:EFOption label="全部" value=""/>
					<EF:EFOption label="学生" value="学生"/>
					<EF:EFOption label="职工" value="职工"/>
				</EF:EFSelect>
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="宿舍信息" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DMXF01" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="id" cname="id" readonly="true" hidden="true"/>
			<EF:EFColumn ename="manId" cname="manId" readonly="true" hidden="true"/>
			<EF:EFColumn ename="roomId" cname="roomId" readonly="true" hidden="true"/>
			<EF:EFColumn ename="keepRoomDays" cname="keepRoomDays" readonly="true" hidden="true"/>
			<EF:EFColumn ename="manName" cname="姓名" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="building" cname="楼" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="floor" cname="层" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="roomNo" cname="宿舍号" readonly="true" width="70" align="center"/>
			<EF:EFColumn ename="keepRoomDays" cname="保留天数(天)" readonly="true" width="70" align="center"/>
			<EF:EFColumn ename="bedNum" cname="床位数" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="dormProperties" cname="宿舍属性" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="typeCodeMeaning" cname="宿舍类型" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="remainingBedNum" cname="剩余床位" readonly="true" width="60" align="center"/>
			<EF:EFColumn ename="dormPosition" cname="宿舍位置" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="elevatorRoom" cname="电梯房" readonly="true" width="50" align="center" />
			<EF:EFColumn ename="priBathroom" cname="独立卫生间" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="dormArea" cname="宿舍面积" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="rent" cname="房租" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="manageFee" cname="管理费" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="annualFee" cname="年费" readonly="true" width="60" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>