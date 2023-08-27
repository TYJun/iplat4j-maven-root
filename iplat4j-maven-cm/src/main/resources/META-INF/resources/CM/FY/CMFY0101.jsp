<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">
	    <EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden" />
		<EF:EFInput ename="inqu_status-0-contCostName" cname="合同费用名称" 
			colWidth="7" ratio="4:8" type="text" required="true"/>
		<EF:EFInput ename="inqu_status-0-remark" cname="备注"  colWidth="7"
			ratio="4:8" type="textarea" />
			<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="确定" ename="SAVEPR" layout="0"></EF:EFButton>
			<EF:EFButton cname="取消" ename="CANCEL" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
</EF:EFPage>

