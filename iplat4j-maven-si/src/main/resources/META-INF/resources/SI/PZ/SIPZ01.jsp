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
	sb.append("{\"configCode\":\"enterCheck\",\"configName\":\"是否开启入库验收流程\",\"hasRadio\":true,\"hasText\":false}").append(",");
	sb.append("{\"configCode\":\"checkInStorage\",\"configName\":\"是否验收后入库\",\"hasRadio\":true,\"hasText\":false}").append(",");
	sb.append("{\"configCode\":\"outStockSign\",\"configName\":\"是否开启仓库出库签字\",\"hasRadio\":true,\"hasText\":false}").append(",");
	sb.append("{\"configCode\":\"storageManagerCheck\",\"configName\":\"是否仓库签字出库\",\"hasRadio\":true,\"hasText\":false}");
	sb.append("]");
	//数据转换
	List<Map> list = JSON.parseArray(sb.toString(), Map.class);
	request.setAttribute("list", list);
%>

<!--库存配置-->
<EF:EFPage title="库存配置">
	<EF:EFRegion id="inqu" title="" showClear="true">
		<div class="row">
			<table class="table">
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