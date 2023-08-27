<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="info" head="hidden">
		<EF:EFInput ename="type" cname="操作类型" ratio="4:6" type="hidden"/>
		<EF:EFInput ename="inqu_status-0-deptNum" cname="所属部门编码" ratio="4:6" type="hidden"/>
		<EF:EFSelect ename="inqu_status-0-deptName" cname="所属部门" colWidth="4"
					resultId="dept" valueField="deptNum" textField="deptName"
					serviceName="CPPZ01" methodName="queryDept"
					optionLabel="请选择" filter="contains" template="#=textField#-#=valueField#"/>
		<EF:EFInput ename="inqu_status-0-workNo" cname="工号" ratio="4:6" type="hidden"/>
		<EF:EFSelect ename="inqu_status-0-worker" cname="员工" colWidth="4"
					resultId="worker" textField="name" valueField="workNo"
					serviceName="CPPZ01" methodName="queryWorker"
					optionLabel="请选择" filter="contains" template="#=textField#-#=valueField#"/>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="提交" ename="SAVE" layout="3"></EF:EFButton>
			<EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
		</div>
	</EF:EFRegion>
</EF:EFPage>