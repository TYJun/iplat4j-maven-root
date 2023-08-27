<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="项目添加">
    <div class="col-md-3">
		<EF:EFRegion id="R1" title="安全巡查项目分类" fitHeight="true">
			<EF:EFTree id="tree01" textField="text" valueField="label"
					   hasChildren="leaf" serviceName="IMJZ010103" methodName="queryTree">
			</EF:EFTree>
		</EF:EFRegion>
	</div>
	<div class="col-md-9">
			<EF:EFRegion id="inqu" title="查询区">
				<div class="row">
					<EF:EFInput ename="content" cname="巡查项目名称:" ratio="5:7"/>
					<EF:EFInput ename="moduleId" cname="分类ID"  type="hidden"  colWidth="3"/>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="数据集" fitHeight="true" >
				<EF:EFGrid blockId="result" autoDraw="no" serviceName="IMJZ010103" checkMode="row" rowNo="true">
					<EF:EFColumn ename="id" cname="主键" hidden="true" align="center" width="100"/>
					<EF:EFColumn ename="itemId" cname="itemId" hidden="true" align="center" width="100"/>
					<EF:EFColumn ename="content" cname="巡查项目名称" readonly="true" align="center" width="80"/>
					<EF:EFColumn ename="referenceValue" cname="巡查项目参考值" readonly="true" align="center" width="150"/>
					<EF:EFColumn ename="projectDesc" cname="项目描述" readonly="true" align="center" width="150" hidden="true"/>
					<EF:EFColumn ename="actualValueUnit" cname="实际值单位" readonly="true" align="center" width="80"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
</EF:EFPage>