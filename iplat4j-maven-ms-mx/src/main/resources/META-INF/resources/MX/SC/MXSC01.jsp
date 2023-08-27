<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage>
    <div class="col-md-3">
        <EF:EFRegion id="R1" title="分类名称" fitHeight="true">
            <EF:EFTree id="tree01" textField="text" valueField="label"
                       hasChildren="leaf" serviceName="MXSC01" methodName="queryTree">
            </EF:EFTree>
        </EF:EFRegion>
        <div id="box"></div>
    </div>
</EF:EFPage>


