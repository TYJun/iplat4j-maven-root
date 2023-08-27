<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>新增公告</title>

<EF:EFPage title="新增公告">
    <EF:EFRegion id="result" title="新增公告" fitHeight="true" >
        <div class="row" >
            <EF:EFInput ename="noticeTitle" cname="公告标题" />
        </div>
        <div class="row" >
            <EF:EFInput ename="noticeContent" cname="公告内容" type="textarea" placeholder="不能超过200字" colWidth="4"
                        style="height:200px;"/>
        </div>
    </EF:EFRegion>
</EF:EFPage>