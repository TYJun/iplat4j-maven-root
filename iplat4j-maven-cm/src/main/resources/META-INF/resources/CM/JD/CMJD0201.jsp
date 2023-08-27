<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage>
    <%-- 界面 --%>
    <EF:EFRegion id="inqu">
        <div class="row">
            <EF:EFInput ename="scheduleId" cname="合同进度Id" colWidth="4" ratio="3:8" type="hidden"/>
            <EF:EFInput ename="scheduleName" cname="合同进度名称" colWidth="4" ratio="3:8" required="true"/>
            <EF:EFInput ename="scheduleRemark" cname="合同进度备注" colWidth="4" ratio="3:8"/>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result">
        <EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" rowNo="true" autoBind="true">
            <EF:EFColumn ename="nodeId" cname="节点Id" align="center" hidden="true"/>
            <EF:EFColumn ename="nodeAutoNo" cname="节点编码" align="center" enable="false"/>
            <EF:EFColumn ename="nodeName" cname="节点名称" align="center" enable="false"/>
            <EF:EFColumn ename="nodeRemark" cname="节点备注" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
    <EF:EFWindow id="scheduleSelect" title="选择合同节点配置" url="" lazyload="true" width="85%" height="88%"/>
</EF:EFPage>