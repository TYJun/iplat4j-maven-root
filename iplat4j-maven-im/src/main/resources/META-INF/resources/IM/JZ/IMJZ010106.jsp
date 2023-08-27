<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row" style="height: 1px;">
			<EF:EFInput ename="inqu_status-0-cardcode" cname="卡片代码:" />
			<EF:EFInput ename="inqu_status-0-cardname" cname="卡片名称:" />
		</div>
	</EF:EFRegion>
	<div class="col-md-6">
		<EF:EFRegion id="result" title="卡片" fitHeight="true">
			<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" rowNo="true" refresh="true">
				<EF:EFColumn ename="cardid" cname="cardid" align="center"
					readonly="true" enable="false" hidden="true" />
				<EF:EFColumn ename="cardcode" cname="cardcode" align="center"
					readonly="true" enable="false" />
				<EF:EFColumn ename="cardname" cname="卡片名称" align="center"
					readonly="true" enable="false" />
				<EF:EFColumn ename="status" cname="状态" align="center"
					readonly="true" enable="false" />
				<EF:EFColumn ename="createtime" cname="创建时间" align="center"
					readonly="true" enable="false" />
				<EF:EFColumn ename="createman" cname="创建人" align="center"
					readonly="true" enable="false" />
				<EF:EFColumn ename="modifytime" cname="修改时间" align="center"
					readonly="true" enable="false" />
				<EF:EFColumn ename="modifyman" cname="修改人" align="center"
					readonly="true" enable="false" />
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
	<div class="col-md-6">
		<EF:EFRegion id="result2" title="项目信息">
			<EF:EFGrid blockId="result2" autoDraw="no" 
				refresh="true">
				<EF:EFColumn ename="itemid" cname="itemID" align="center"
					readonly="true" hidden="true" />
				<EF:EFColumn ename="cardid" cname="cardid" align="center"
					readonly="true" hidden="true" />
				<EF:EFColumn ename="content" cname="巡检项目" align="center"
					readonly="true" />
				<EF:EFColumn ename="referencevalue" cname="巡检项目参考值" align="center"
					readonly="true" />
				<EF:EFColumn ename="actualvalueunit" cname="实际值单位" align="center"
					readonly="true" />
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
</EF:EFPage>
