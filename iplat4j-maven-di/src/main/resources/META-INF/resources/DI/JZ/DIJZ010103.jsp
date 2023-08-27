<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="项目添加">
	<div class="col-md-4 i-fit-height">
		<EF:EFRegion id="tree" title="巡检项目分类" fitHeight="true">
			<EF:EFTree id="menu" valueField="id" textField="classifyName" hasChildren="hasChildren"
					   serviceName="DIXM01" methodName="queryTypeTree" />
		</EF:EFRegion>
	</div>
	<div class="col-md-8">
		<EF:EFRegion id="inqu" title="查询区" showClear="true">
			<div class="row">
				<EF:EFInput ename="itemName" cname="巡检项目" colWidth="8" ratio="3:9"/>
				<EF:EFInput ename="typeId" cname="巡检项目" type="hidden"/>
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="数据集" fitHeight="true">
			<EF:EFGrid blockId="result" autoDraw="no" serviceName="DIXM01">
				<EF:EFColumn ename="id" cname="主键" hidden="true"/>
				<EF:EFColumn ename="code" cname="项目编码" hidden="true"/>
				<EF:EFColumn ename="content" cname="项目名称" readonly="true" align="center" width="150"/>
				<EF:EFColumn ename="typeName" cname="上级分类" hidden="true"/>
				<EF:EFColumn ename="projectDesc" cname="项目描述" hidden="true"/>
				<EF:EFColumn ename="referenceValue" cname="项目参考值" readonly="true" align="center" width="150"/>
				<EF:EFColumn ename="actualValueUnit" cname="实际值单位" readonly="true" align="center" width="80"/>
				<EF:EFColumn ename="memo" cname="备注" hidden="true"/>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
</EF:EFPage>