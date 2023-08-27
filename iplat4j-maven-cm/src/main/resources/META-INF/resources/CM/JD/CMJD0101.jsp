<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <%-- 界面 --%>
    <EF:EFRegion id="result">
        <div class="row">
            <EF:EFInput ename="nodeId" cname="节点ID" colWidth="4" ratio="3:8" type="hidden"/>
            <EF:EFInput ename="nodeNames" cname="节点名称" colWidth="4" ratio="3:8"
                        data-errorPrompt="请不要输入;" data-regex="/^[^;\x22]+$/" required="true"/>
            <EF:EFInput ename="nodeRemark" cname="节点备注" colWidth="4" ratio="3:8"
                        data-errorPrompt="请不要输入;" data-regex="/^[^;\x22]+$/"/>
        </div>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="确定" ename="EXCUTE" layout="0" ></EF:EFButton>
            <EF:EFButton cname="取消" ename="CANCEL" layout="0" ></EF:EFButton>
        </div>
    </EF:EFRegion>
</EF:EFPage>