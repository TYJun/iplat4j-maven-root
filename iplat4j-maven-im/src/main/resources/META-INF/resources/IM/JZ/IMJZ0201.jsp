<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="新增">
		<div class="col-md-12">
			<div class="row" style="height: 10px;">
				<EF:EFInput ename="dangerwhere" cname="设备包名:" colWidth="3" ratio="2:8" required="true" />
				<EF:EFInput colWidth="4" ratio="2:8" type="textarea" ename="requiredesc" cname="备注说明："></EF:EFInput>
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="设备列表" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="IMJZ02" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" hidden="true" align="center" />
			<EF:EFColumn ename="paramKey" cname="设备代码" enable="false" align="center" />
			<EF:EFColumn ename="paramName" cname="设备名称" enable="false" align="center" />
			<EF:EFColumn ename="classifyName" cname="安装地点" enable="false" align="center" />
			<EF:EFColumn ename="models" cname="规格型号" enable="false" align="center" hidden="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="600" height="500" title="设备选择" modal="true" />
</EF:EFPage>

