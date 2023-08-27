<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">
		<div class="row">
			<EF:EFInput ename="deptId" cname="科室ID" 
				colWidth="4"  type="hidden" required="true"/>
			<EF:EFInput ename="deptName" cname="科室名称" 
				colWidth="4"  type="text" required="true"
				readOnly="true" />
				
			<EF:EFInput ename="spotId" cname="地点ID" 
				colWidth="4"  type="hidden" required="true" />
				
			<EF:EFInput ename="spotName" cname="地点名称" 
				colWidth="4"  type="text" required="true"
				readOnly="true" />
				
			<EF:EFInput ename="telNum" cname="电话号码" 
				colWidth="4"  type="text" required="true"/>
		</div>
		
		<div class="row">
			<EF:EFInput ename="remark" type="textarea" colWidth="4" cname="备注"/>
		
		</div>
		
	</EF:EFRegion>
</EF:EFPage>
