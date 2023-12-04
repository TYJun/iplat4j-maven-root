<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>科室领用成本表</title>
<EF:EFPage>
    <EF:EFRegion id="R2" title="科室领用成本表" fitHeight="true">
        <iframe id="lyFrame" name="cxFrame" width="100%" height="100%" security="restricted"
                sandbox="allow-same-origin allow-forms allow-downloads allow-popups allow-scripts"></iframe>
    </EF:EFRegion>
</EF:EFPage>