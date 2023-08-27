<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="题目详情">
	<EF:EFRegion id="inqu" head="hidden">
		<div class="row">
			<EF:EFInput ename="type" cname="类型" type="hidden"/>
			<EF:EFInput ename="projectId" cname="项目ID" type="hidden"/>
			<EF:EFInput ename="instanceId" cname="题目ID" type="hidden"/>
			<EF:EFInput ename="orderNo" cname="题目序号" required="true"/>
			<EF:EFInput ename="inqu_status-0-instanceName" cname="题目描述" type="textarea" rows="5" required="true"/>
			<EF:EFSelect ename="inqu_status-0-pointType" cname="问题类型" required="true">
				<EF:EFCodeOption codeName="WILP.sq.pointType" textField="label" valueField="value"/>
			</EF:EFSelect>
			<div id="point">
				<EF:EFInput ename="inqu_status-0-point" cname="分值" maxLength="3" format="{0:n0}" required="true"/>
			</div>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="保存" ename="SURE" layout="3"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="题目选项">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="single,row">
			<EF:EFColumn ename="orderNo" cname="序号" width="100" align="center" required="true"/>
			<EF:EFColumn ename="instanceId" cname="题目ID" width="100" align="center" hidden="true"/>
			<EF:EFColumn ename="itemName" cname="选项" width="300" align="center"/>
			<EF:EFColumn ename="itemScore" cname="分数" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

