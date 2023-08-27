<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>职工心声配置管理</title>
<EF:EFPage>
	<div class="row">
		<div class="col-md-2">
			<EF:EFRegion id="deptNameMenu" title="人员绑定科室">
				<EF:EFTree id="menu" valueField="deptNo" textField="deptName" hasChildren="isLeaf"
						   serviceName="CPPZ01" methodName="queryCpDept" style="height:648px;"/>
			</EF:EFRegion>
		</div>
		<div class="col-md-10">
			<EF:EFRegion id="inqu" title="查询条件">
				<div class="row">
					<EF:EFInput ename="inqu_status-0-workName" cname="员工姓名"/>
					<EF:EFInput ename="inqu_status-0-deptNo" cname="部门编码" type="hidden"/>
					<EF:EFInput ename="inqu_status-0-deptName" cname="所属部门"/>
				</div>
				<div class="button-region" id="buttonDiv">
					<EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
					<EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="员工部门对应信息">
				<EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true">
					<EF:EFColumn ename="id" cname="id" width="100" enable="false" align="center" hidden="true"/>
					<EF:EFColumn ename="workName" cname="员工姓名" width="100" enable="false" align="center"/>
					<EF:EFColumn ename="deptName" cname="所属部门" width="100" enable="false" align="center"/>
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="popData" url="" lazyload="true" width="90%" height="60%" title="科室绑定" modal="true" />
		</div>
	</div>
</EF:EFPage>