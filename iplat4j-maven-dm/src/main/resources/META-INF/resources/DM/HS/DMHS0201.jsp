<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息">
		<div class="row">
			<EF:EFInput ename="id" cname="id" readonly="true"
						colWidth="5" type="hidden"/>
			<EF:EFInput ename="roomManId" cname="id" readonly="true"
						colWidth="5" type="hidden"/>
			<EF:EFInput ename="bedNo" cname="bedNo" readonly="true"
						colWidth="5" type="hidden"/>
			<EF:EFInput ename="changeRoomNote" cname="changeRoomNote" readonly="true"
						colWidth="5" type="hidden"/>
			<EF:EFInput ename="applyChangeDate" cname="applyChangeDate" readonly="true"
						colWidth="5" type="hidden"/>
			<EF:EFInput ename="actualInDate" cname="actualInDate" readonly="true"
						colWidth="5" type="hidden"/>
			<EF:EFInput ename="roomId" cname="roomId" readonly="true"
						colWidth="5" type="hidden"/>
			<EF:EFInput ename="remainingBedNum" cname="remainingBedNum" readonly="true"
						colWidth="5" type="hidden"/>
			<EF:EFInput ename="manNo" cname="工号" readonly="true"
				colWidth="5"  type="text"  />
			<EF:EFInput ename="manName" cname="姓名" readonly="true"
				colWidth="5"  type="text"  />
		</div>
		<div class="row">
			<EF:EFInput ename="age" cname="年龄" readonly="true"
				colWidth="5"  type="text"  />
			<EF:EFInput ename="sexMeaning" cname="性别" readonly="true"
						colWidth="5"  type="text"  />
		</div>
		<div class="row">
			<EF:EFInput ename="educationBackground" cname="学历" readonly="true"
						colWidth="5"  type="text"  />
			<EF:EFInput ename="deptName" cname="部门科室" readonly="true"
						colWidth="5"  type="text" />
		</div>
		<div class="row">
			<EF:EFInput ename="employmentNature" cname="职工属性" readonly="true"
				colWidth="5"  type="text"  />
			<EF:EFInput ename="phone" cname="电话" readonly="true"
				colWidth="5"  type="text"  />
		</div>
		<div class="row">
			<EF:EFInput ename="maritalStatus" cname="婚否" readonly="true"
						colWidth="5"  type="text"  />
			<EF:EFInput ename="hiredate" cname="入职时间" readonly="true"
						colWidth="5"  type="text"  />
		</div>
		<div class="row">
			<EF:EFInput ename="note" cname="备注" ratio="2:10"
						colWidth="10"  type="textarea" readonly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="keepRoomDays" cname="宿舍保留天数" required="true"
						colWidth="5" />
		</div>

	</EF:EFRegion>
	<EF:EFRegion id="selectDormItem" title="宿舍选择">
		<EF:EFGrid blockId="resultDormItem" autoDraw="no" autoBind="true"
				   checkMode="multiple,row">
			<!-- 谁排前谁为id -->
			<EF:EFColumn ename="roomId" cname="id" readonly="true"
						 align="center" enable="false" hidden="true"/>
			<EF:EFColumn ename="building" cname="楼" readonly="true"
						 align="center" enable="false"/>
			<EF:EFColumn ename="floor" cname="层" readonly="true"
						 align="center" enable="false" />
			<EF:EFColumn ename="roomNo" cname="房间号" readonly="true"
						 align="center" enable="false" />
			<EF:EFColumn ename="typeCodeName" cname="宿舍类型" readonly="true"
						 align="center" enable="false" />
			<EF:EFColumn ename="elevatorRoom" cname="电梯房" readonly="true" width="50" align="center" />
			<EF:EFColumn ename="remainingBedNum" cname="所剩床位数" readonly="true"
						 align="center" enable="false" hidden="true" />
			<EF:EFColumn ename="rent" cname="房租" readonly="true"
						 align="center" enable="false" />
			<EF:EFColumn ename="manageFee" cname="管理费" readonly="true"
						 align="center" enable="false" />
			<EF:EFColumn ename="annualFee" cname="年费" readonly="true"
						 align="center" enable="false" />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popDataItem" url="" lazyload="true" width="95%"
				 height="90%" title="宿舍选择" modal="true" />
</EF:EFPage>