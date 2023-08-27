<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>公告管理</title>

<EF:EFPage title="公告管理">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="noticeTitle" cname="公告标题"/>
            <EF:EFInput ename="noticeContent" cname="公告内容"/>
            <EF:EFSelect ename="status" cname="状态" defaultValue="">
                <EF:EFOption label="" value=""></EF:EFOption>
                <EF:EFOption label="启用" value="1"></EF:EFOption>
                <EF:EFOption label="停用" value="0"></EF:EFOption>
            </EF:EFSelect>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="记录集" >
        <EF:EFGrid blockId="result" autoDraw="no">
            <EF:EFColumn ename="id" cname="主键" width="100" hidden="true" />
            <EF:EFColumn ename="noticeTitle" cname="公告标题"  align="center"/>
            <EF:EFColumn ename="noticeContent" cname="公告内容" align="center"/>
            <EF:EFColumn ename="createTime" cname="创建日期"  align="center"/>
            <EF:EFColumn ename="creator" cname="创建人"  align="center"/>
            <EF:EFColumn ename="reviseTime" cname="修改日期"  align="center"/>
            <EF:EFColumn ename="revisor" cname="修改人"  align="center"/>
            <EF:EFColumn ename="status" cname="状态"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>

<EF:EFWindow id="insertNoticePopData" url=" " lazyload="true" width="80%" height="60%"></EF:EFWindow>
<EF:EFWindow id="editNoticePopData" url=" " lazyload="true" width="80%" height="60%"></EF:EFWindow>