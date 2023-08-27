<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>科室常用物资</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<EF:EFInput ename="matName" cname="物资名称" />
			<EF:EFInput ename="deptNum" cname="科室编码" type="hidden" />
			<EF:EFInput ename="deptName" cname="科室名称" type="hidden" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="计划清单">
		<EF:EFGrid blockId="result" autoDraw="no"  autoBind="true" readonly="true"  >
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="matTypeNum" cname="物资分类编码" width="100" align="center" hidden="true"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="unit" cname="计量单位编码" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="unitName" cname="计量单位名称" width="100" align="center" enable="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>