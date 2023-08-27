<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.baosight.iplat4j.core.ei.EiInfo" %>
<%@ page import="com.baosight.iplat4j.core.ei.EiConstant" %>
<%@ page import="com.baosight.iplat4j.core.service.soa.XLocalManager" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">
	#classHeader {
		font-size: 18px;
		color: #0000FF;
	}

	.self-control-label {
		padding-right: 0;
		margin-bottom: 0;
		text-align: left;
	}
</style>
<%
	// 获取满意度评价项目列表
	EiInfo info = new EiInfo();
	info.set(EiConstant.serviceName, "DMPJ0101");
	info.set(EiConstant.methodName, "getSatisfactionList");
	EiInfo outInfo = XLocalManager.call(info);
	List<Map<String, Object>> list = (List<Map<String, Object>>) outInfo.getBlock("list").getRows();
	request.setAttribute("list",list);
%>
<%
	System.out.println("list:"+request.getAttribute("list") );
%>
<EF:EFPage>
	<EF:EFRegion id="result" title="信息详情">
		<div class="row">
			<EF:EFInput ename="id" cname="id" readOnly="true"
						colWidth="4" type="hidden"/>
			<EF:EFInput ename="manId" cname="id" readOnly="true"
						colWidth="4" type="hidden"/>
			<EF:EFInput ename="manNo" cname="工号" readOnly="true"
						colWidth="4"  type="text"/>
			<EF:EFInput ename="manName" cname="姓名" readOnly="true"
						colWidth="4"  type="text"/>
			<EF:EFInput ename="evalMonth" cname="评价年月份"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="employmentNature" cname="职工属性"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="phone" cname="电话"
						colWidth="4"  type="text" readOnly="true"/>
		</div>

		<c:forEach items="${requestScope.list}" var ="classify" >
			<div>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label data-for="${classify.classifyCode}" class="col-xs-4 control-label">
								<span id="classHeader">${classify.classifyName}</span>
							</label>
						</div>
					</div>
				</div>
				<c:forEach items="${classify.itemList}" var ="item" >
					<div class="row">
						<div class="col-md-8">
							<div class="form-group">
								<label data-for="${item.code}" class="col-xs-4 self-control-label">
									<span id="itemCss" class="${item.code}">${item.content}</span>
								</label>
								<EF:EFInput ename="${item.code}" inline="true" value="5" cname="满意"
											type="radio" readonly="true" />
								<EF:EFInput ename="${item.code}" inline="true" value="4" cname="比较满意"
											type="radio" readonly="true" />
								<EF:EFInput ename="${item.code}" inline="true" value="3" cname="合格"
											type="radio" readonly="true" />
								<EF:EFInput ename="${item.code}" inline="true" value="2" cname="不满意"
											type="radio" readonly="true" />
								<EF:EFInput ename="${item.code}" inline="true" value="1" cname="不清楚"
											type="radio" readonly="true" />
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:forEach>

		<div class="row">
			<EF:EFInput ename="evalContent" cname="意见和建议"
						colWidth="8" rows="3" ratio="2:10" type="textarea" readOnly="true"/>
		</div>
	</EF:EFRegion>
</EF:EFPage>