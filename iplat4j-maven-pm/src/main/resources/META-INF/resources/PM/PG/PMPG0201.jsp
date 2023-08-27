<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage>
    <%-- 界面 --%>
    <EF:EFRegion id="inqu">
        <div class="row">
            <EF:EFInput ename="typeName" cname="类型名称" colWidth="4" ratio="3:8"
                        data-errorPrompt="请不要输入;" data-regex="/^[^;\x22]+$/" required="true"/>
            <EF:EFInput ename="typeRemark" cname="类型备注" colWidth="4" ratio="3:8"
                        data-errorPrompt="请不要输入;" data-regex="/^[^;\x22]+$/"/>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result">
        <EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" rowNo="true" autoBind="true">
            <EF:EFColumn ename="stageId" cname="项目阶段Id" align="center" hidden="true"/>
            <EF:EFColumn ename="stageCode" cname="阶段编码" align="center" enable="false"/>
            <EF:EFColumn ename="stageName" cname="阶段名称" align="center" enable="false"/>
            <EF:EFColumn ename="stageRemark" cname="阶段备注" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
    <EF:EFWindow id="stageSelect" title="选择项目阶段配置" url="" lazyload="true" width="85%" height="88%"/>
</EF:EFPage>