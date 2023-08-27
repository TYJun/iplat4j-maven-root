<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <%-- 界面 --%>
    <EF:EFRegion id="result">
        <div class="row">
            <EF:EFInput ename="stageName" cname="阶段名称" colWidth="4" ratio="3:8"
                data-errorPrompt="请不要输入;" data-regex="/^[^;\x22]+$/" required="true"/>
            <EF:EFInput ename="stageRemark" cname="阶段备注" colWidth="4" ratio="3:8"
                data-errorPrompt="请不要输入;" data-regex="/^[^;\x22]+$/"/>
        </div>
    </EF:EFRegion>
</EF:EFPage>