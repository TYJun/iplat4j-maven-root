<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <EF:EFTab id="info" contentType="iframe">
        <ul>
            <li>项目阶段配置</li>
            <li>项目类型配置</li>
        </ul>
        <div id="PMPG01" title="项目阶段配置" data-src="${ctx}/web/PMPG01"></div>
        <div id="PMPG02" title="项目类型配置" data-src="${ctx}/web/PMPG02"></div>
    </EF:EFTab>
</EF:EFPage>