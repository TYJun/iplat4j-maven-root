<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="result" title="信息详情">
		<div class="row">
			<EF:EFInput ename="manId" cname="主键" readOnly="true"
						colWidth="4"  type="hidden"/>
			<EF:EFInput ename="manNo" cname="工号" readOnly="true"
						colWidth="4"  type="text"/>
			<EF:EFInput ename="manName" cname="姓名" readOnly="true"
						colWidth="4"  type="text"/>
			<EF:EFInput ename="sexMeaning" cname="性别"  readOnly="true"
						colWidth="4"  type="text"/>
		</div>
		<div class="row">
			<EF:EFInput ename="age" cname="年龄"  readOnly="true" colWidth="4"  type="text"/>
			<EF:EFInput ename="phone" cname="电话"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="maritalStatus" cname="婚姻状态"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="deptName" cname="部门科室"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="identityCard" cname="身份证号"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="educationBackground" cname="学历"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="academicYear" cname="学年制"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="employmentNature" cname="职工属性"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="isNetwork" cname="是否联网"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="isStay" cname="是否已办暂住证"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="permanentResidence" cname="户口所在地"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="hiredate" cname="入职时间"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="building" cname="宿舍楼" colWidth="4"  type="text" readonly="true" />
			<EF:EFInput ename="floor" cname="宿舍层" colWidth="4"  type="text" readonly="true" />
			<EF:EFInput ename="roomName" cname="宿舍总称" colWidth="4"  type="text" readonly="true"  />
		</div>
		<div class="row">
			<EF:EFInput ename="bedNo" cname="床位号" colWidth="4"  type="text" readonly="true"  />
			<EF:EFInput ename="rent" cname="房租" colWidth="4"  type="text" readonly="true" />
			<EF:EFInput ename="manageFee" cname="管理费" colWidth="4"  type="text" readonly="true" />
		</div>
		<div class="row">
			<EF:EFInput ename="actualRent" cname="实际月租"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="actualManageFee" cname="实际管理费"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="estimatedInDate" cname="预计入住时间"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="actualInDate" cname="实际入住时间"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="estimatedOutDate" cname="预计退宿时间"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="actualOutDate" cname="实际退宿时间"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="applyNote" cname="申请备注"
						colWidth="4" rows="3" type="textarea" readOnly="true"/>
			<EF:EFInput ename="dormNote" type="textarea" colWidth="4"
						rows="3" cname="宿舍备注" readonly="true" />
		</div>
	</EF:EFRegion>
</EF:EFPage>