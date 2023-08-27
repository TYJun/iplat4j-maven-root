<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>医院标识分类管理</title>
<EF:EFPage title="医院标识分类">
	<div class="col-md-3">
		<EF:EFRegion id="R1" title="医院标识分类" fitHeight="true">
			<EF:EFTree id="tree01" textField="text" valueField="label"
				hasChildren="leaf" serviceName="HIFL01" methodName="queryTree">
			</EF:EFTree>
		</EF:EFRegion>
	</div>
	<div class="col-md-9">
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<div class="row">
				<EF:EFInput ename="inqu_status-0-classifyNum" cname="医院标识分类编码" />
				<EF:EFInput ename="inqu_status-0-classifyName" cname="医院标识分类名称" />
				<EF:EFInput ename="inqu_status-0-parentId" type="hidden" />
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="医院标识分类列表">
			<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" readonly="true">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="classifyNum" cname="医院标识分类编码" width="150" align="center"/>
				<EF:EFColumn ename="classifyName" cname="医院标识分类名称" width="200" align="center"/>
				<EF:EFColumn ename="superClassifyName" cname="上级分类" width="200" align="center"/>
				<EF:EFColumn ename="remark" cname="备注" width="200" align="center"/>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " title="医院标识分类" lazyload="true" width="38%" height="55%"></EF:EFWindow>