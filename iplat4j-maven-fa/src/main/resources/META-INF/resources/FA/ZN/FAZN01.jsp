<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>归口科室管理</title>
<EF:EFPage>
	<div class="row">
		<div class="col-md-2">
			<EF:EFRegion id="deptNameMenu" title="归口科室">
				<EF:EFTree id="menu" valueField="id" textField="deptName" hasChildren="isLeaf"
						   serviceName="FAZN01" methodName="queryFaDeptTree" style="height:640px;"/>
			</EF:EFRegion>
		</div>
		<div class="col-md-10">
			<EF:EFRegion id="inqu" title="查询条件">
				<div class="row">
					<EF:EFInput ename="inqu_status-0-deptId" cname="ID" type="hidden"/>
					<EF:EFInput ename="functionDeptNum" cname="归口科室编码" type="hidden"/>
					<EF:EFInput ename="functionDeptName" cname="归口科室" type="hidden"/>
					<EF:EFInput ename="inqu_status-0-identifyDeptName" cname="鉴定科室"/>
					<EF:EFInput ename="inqu_status-0-functionDeptName" cname="归口科室"/>
				</div>
				<div class="button-region" id="buttonDiv">
					<EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
					<EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="归口科室信息">
				<EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true" height="526px">
					<EF:EFColumn ename="deptId" cname="deptId" width="100" enable="false" align="center" hidden="true"/>
					<EF:EFColumn ename="identifyDeptName" cname="鉴定科室" width="100" enable="false" align="center"/>
					<EF:EFColumn ename="functionDeptName" cname="归口科室" width="100" enable="false" align="center"/>
					<EF:EFColumn ename="goodsClassifyName" cname="资产类别" width="100" enable="false" align="center"/>
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="popData" url="" lazyload="true" width="90%" height="60%" title="科室绑定" modal="true" />
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/FA/common/js/fa-keydown.js"></script>
</EF:EFPage>