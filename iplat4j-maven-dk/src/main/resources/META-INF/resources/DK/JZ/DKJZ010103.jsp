<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="项目添加">
	<div class="col-md-3">
		<EF:EFRegion id="R1" title="保养项目分类" fitHeight="true">
			<EF:EFTree id="tree01" textField="text" valueField="label"
					   hasChildren="leaf" serviceName="DKJZ01" methodName="queryTree">
			</EF:EFTree>
		</EF:EFRegion>
	</div>
	<div class="col-md-9">
			<EF:EFRegion id="inqu" title="查询区" showClear="true">
				<div class="row">
					<EF:EFInput ename="itemName" cname="保养项目" />
					<EF:EFInput ename="moduleId" cname="分类ID"  type="hidden"  colWidth="3"/>
				</div>
				
			</EF:EFRegion>
			<EF:EFRegion id="result" title="数据集" fitHeight="true">
				<EF:EFGrid blockId="result" autoDraw="no" serviceName="DKJZ010103">
					<EF:EFColumn ename="id" cname="主键" hidden="true" align="center" width="100"/>
					<EF:EFColumn ename="itemId" cname="itemId" hidden="true" align="center" width="100"/>
					<EF:EFColumn ename="content" cname="保养项目" readonly="true" align="center" width="80"/>
					<EF:EFColumn ename="typeName" cname="上级分类" hidden="true"/>
					<EF:EFColumn ename="referenceValue" cname="保养项目参考值" readonly="true" align="center" width="150"/>
					<EF:EFColumn ename="projectDesc" cname="项目描述" readonly="true" align="center" width="150" hidden="true"/>
					<EF:EFColumn ename="actualValueUnit" cname="实际值单位" readonly="true" align="center" width="80"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
</EF:EFPage>