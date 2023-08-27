<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="{pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result">
		<div class="row">
			<EF:EFInput ename="id" cname="人员宿舍绑定表id" colWidth="5" type="hidden"/>
			<EF:EFInput ename="roomId" cname="roomId" colWidth="5" type="hidden"/>
			<EF:EFInput ename="manId" cname="manId" colWidth="5" type="hidden"/>
			<EF:EFDatePicker ename="currentMonth" cname="年月份" colWidth="5" readonly="true"
							 type="text"  role="date" format="yyyy-MM" parseFormats="['yyyy-mm']"
							 placeholder="请选择年月份" />
<%--			<EF:EFInput ename="currentMonth" cname="当前年月" colWidth="5" readonly="true"/>--%>
			<EF:EFInput ename="manNo" cname="工号" colWidth="5" readonly="true"/>
			<EF:EFInput ename="manName" cname="姓名" colWidth="5" readonly="true"/>
			<EF:EFInput ename="building" cname="宿舍楼" colWidth="5" readonly="true"/>
			<EF:EFInput ename="floor" cname="宿舍层" colWidth="5" readonly="true"/>
			<EF:EFInput ename="roomNo" cname="宿舍号" colWidth="5" readonly="true"/>
			<EF:EFInput ename="roomName" cname="宿舍总称" colWidth="5" readonly="true"/>
			<EF:EFInput ename="currentRent" cname="本月实际月租（元）" colWidth="5" required="true"/>
			<EF:EFInput ename="currentManageFee" cname="本月实际管理费（元）" colWidth="5" required="true"/>
			<EF:EFInput ename="waterDegree" cname="本月用水量（吨）" colWidth="5"/>
			<EF:EFInput ename="elecDegree" cname="本月用电量（度）" colWidth="5" />
			<EF:EFInput ename="waterPriece" cname="本月水费（元）" colWidth="5" required="true" />
			<EF:EFInput ename="elecPriece" cname="本月电费（元）" colWidth="5" required="true" />
			<EF:EFInput ename="returnRent" cname="退房租（元）" colWidth="5" value="0"/>
			<EF:EFInput ename="returnManageFee" cname="退管理费（元）" colWidth="5" value="0"/>
			<EF:EFInput ename="returnWaterPriece" cname="退水费（元）" colWidth="5" value="0"/>
			<EF:EFInput ename="returnElecPriece" cname="退电费（元）" colWidth="5" value="0"/>
			<EF:EFInput ename="replenishRent" cname="补房租（元）" colWidth="5" value="0"/>
			<EF:EFInput ename="replenishManageFee" cname="补管理费（元）" colWidth="5" value="0"/>
			<EF:EFInput ename="replenishWaterPriece" cname="补水费（元）" colWidth="5" value="0"/>
			<EF:EFInput ename="replenishElecPriece" cname="补电费（元）" colWidth="5" value="0"/>
			<EF:EFInput ename="extraCharges" cname="额外费用（元）" colWidth="5" value="0"/>
			<EF:EFInput ename="remark" cname="备注" colWidth="5" rows="3" type="textarea"/>
		</div>
	</EF:EFRegion>
</EF:EFPage>

