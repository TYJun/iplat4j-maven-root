
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>


<c:set var="ctxs" value="${pageContext.request.contextPath}" />

<EF:EFPage prefix="AQXC">
    <div style="heigth: 100%; width:100%; text-align: center;">
        <EF:EFRegion id="result" title="NFC发卡">
        </EF:EFRegion>
    </div>
    <div id="spotNameDiv" style="heigth: 100%; width:100%; text-align: center;">
    </div>
</EF:EFPage>
