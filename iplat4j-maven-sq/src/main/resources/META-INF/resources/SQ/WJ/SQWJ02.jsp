<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage >
	<EF:EFRegion id="inqu" title="维护问卷" showClear="true">
		<EF:EFInput ename="manageId" cname="考试ID" colWidth="5" ratio="4:8" type="hidden"/>
		<EF:EFInput ename="type" cname="操作类型" colWidth="5" ratio="4:8" type="hidden"/>
        <EF:EFSelect ename="inqu_status-0-standardName" cname="问卷主题"
			resultId="standardName" filter="contains" optionLabel="请选择"
			textField="standardName" valueField="standardCode" colWidth="5" ratio="4:8"
			serviceName="SQBZ01" methodName="standardName" template="#=valueField#-#=textField#" required="true">
		</EF:EFSelect>
		<EF:EFPopupInput ename="inqu_status-0-workNameLeader" cname="问卷负责人" readonly="true"
						 popupTitle="问卷负责人选择" popupType="ServiceGrid" serviceName="SQBZ01"
						 methodName="queryAdmin" resultId="contAdmin" valueField="workNo"
						 textField="name" colWidth="4" ratio="4:8" popupWidth="500"
						 columnEnames="workNo,name" columnCnames="员工工号,员工姓名" required="true"/>
		<EF:EFCascadeSelect ename="inqu_status-0-canteenNum" autoBind="true"
							cname="问卷范围" textField="label" valueField="value"
							methodName="query2" resultId="canteenData" colWidth="4"
							ratio="4:8" required="true">
		</EF:EFCascadeSelect>
		<div id="range" >
			<EF:EFCascadeSelect ename="inqu_status-0-mealNum"
								cascadeFrom="inqu_status-0-canteenNum"
								cname="" textField="perGroupName" valueField="perGroupNo"
								methodName="query3" resultId="mealNum" colWidth="4"
								ratio="4:8" required="true">
			</EF:EFCascadeSelect>
		</div>
		<EF:EFDateSpan startName="inqu_status-0-beginDate" startCname="开始日期"
					   endName="inqu_status-0-endDate" endCname="截至日期" disableDates="true"
					   colWidth="4" startRatio="4:8" endRatio="4:8" required="true" readonly="true"/>
		<EF:EFSelect ename="inqu_status-0-isCycle" cname="是否按周期生成">
			<EF:EFOption label="否" value="0"/>
			<EF:EFOption label="是" value="1"/>
		</EF:EFSelect>
		<div  id="cycleTimeUnit">
			<EF:EFInput ename="inqu_status-0-cycleTime" cname="周期" ratio="4:8"/>
			<div>
				<center>
					<EF:EFInput class="cycleTimeUnit" inline="true" type="radio" ename="inqu_status-0-cycleTimeUnit" cname="年" value="year"/>
					<EF:EFInput class="cycleTimeUnit" inline="true" type="radio" ename="inqu_status-0-cycleTimeUnit" cname="月" value="month"/>
					<EF:EFInput class="cycleTimeUnit" inline="true" type="radio" ename="inqu_status-0-cycleTimeUnit" cname="日" value="day"/>
				</center>
			</div>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="保存" ename="SURE" layout="3"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
		</div>
	</EF:EFRegion>
</EF:EFPage>