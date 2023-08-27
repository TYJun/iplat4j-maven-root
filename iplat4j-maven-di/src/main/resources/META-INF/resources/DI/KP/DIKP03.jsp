<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="卡片项目管理">
    <EF:EFRegion id="result" title="巡查项目信息">
    	<EF:EFGrid blockId="result" autoDraw="no">
    		<EF:EFColumn ename="idd" cname="id" readonly="true" hidden="true"/>
    		<EF:EFColumn ename="content" cname="巡检项目" readonly="true" />
    		<EF:EFColumn ename="referencevalue" cname="巡检项目参考值" readonly="true" />
    		<EF:EFColumn ename="projectDesc" cname="项目描述" readonly="true" />
    		<EF:EFColumn ename="actualvalueunit" cname="实际值单位" readonly="true" />
    	</EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
