<!DOCTYPE html>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	//构建页面数据
	StringBuilder sb  = new StringBuilder("[");
	sb.append("{\"configCode\":\"OAApproval\",\"configName\":\"是否开启采购计划分管领导审批\",\"hasRadio\":true,\"hasText\":false}").append(",");
	sb.append("{\"configCode\":\"deanApproval\",\"configName\":\"是否开启采购计划院长审批\",\"hasRadio\":true,\"hasText\":false}").append(",");
	sb.append("{\"configCode\":\"dockFixedAsset\",\"configName\":\"固定资产对接分类\",\"hasRadio\":true,\"hasText\":true}");
	sb.append("]");
	//数据转换
	List<Map> list = JSON.parseArray(sb.toString(), Map.class);
	request.setAttribute("list", list);
%>

<!--物资采购配置-->
<EF:EFPage title="物资采购配置">
	<EF:EFRegion id="inqu" title="" showClear="true">
		<div class="row">
			<table class="table">
				<%--<tr id="TT1"><td colspan="2">物资领用配置</td></tr>--%>
				<c:forEach items="${list}" var="item" varStatus="itemStatus">
					<tr>
						<td class="main-td">
							<EF:EFInput type="hidden" ename="inqu_status-${itemStatus.index}-configCode"
										cname="配置编码" value="${item.configCode}"/>
							<EF:EFInput type="hidden" ename="inqu_status-${itemStatus.index}-configName"
										cname="配置名称" value="${item.configName}"/>
							<EF:EFInput type="hidden" ename="inqu_status-${itemStatus.index}-id" cname="配置ID"/>
							<span>${item.configName}</span>
						</td>
						<td>
							<div class="td-class">
								<c:if test="${item.hasRadio}">
									<EF:EFInput ename="inqu_status-${itemStatus.index}-configValueRadio" cname="是" value="Y" type="radio" inline="true"/>
									<EF:EFInput ename="inqu_status-${itemStatus.index}-configValueRadio" cname="否" value="N" type="radio" inline="true"/>
								</c:if>
								<c:if test="${item.hasText}">
									<EF:EFInput ename="inqu_status-${itemStatus.index}-configValueText" inline="true"/>
								</c:if>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="保存" ename="SUBMIT" layout="0" ></EF:EFButton>
			<EF:EFButton cname="刷新" ename="REFRESH" layout="0" ></EF:EFButton>
		</div>
	</EF:EFRegion>
</EF:EFPage>
<style type="text/css">
	.table {
		border:1px solid #6495ed;
		border-collapse:collapse;
	}

	.table td {
		line-height: 30px;
		border:1px solid #6495ed;
	}

	.title td {
		background-color: #d2e8ff;
		font-weight:bold;
		font-size: 18px;
		color: #0000FF;
	}

	.main-td {
		text-align: center;
		color: #0000FF;
		font-size: 16px;
		width: 250px;
	}
	.td-class {
		margin-left: 15px;
	}

	input[type="text"] {
		width: 650px;
	}
</style>