<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="{pageContext.request.contextPath}" />

<EF:EFPage title="宿舍公告">

    <EF:EFRegion id="inqu" title="查询条件">
        <div class="row">
        	<EF:EFInput ename="id" cname="主键" type = "hidden"/>       	
        </div>
    </EF:EFRegion>

 <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" autoDraw="no" rowNo="true">
        	<EF:EFColumn ename="id" cname="主键" width="200" hidden="true" align="center"/>
            <EF:EFColumn ename="noticeNo" cname="公告序号" width="50" align="center"/>
            <EF:EFColumn ename="noticeType" cname="公告类别" width="50" align="center" readonly="true"/>
            <EF:EFColumn ename="notice" cname="公告内容" width="400" align="center"/>
        	<EF:EFColumn ename="recCreator" cname="创建人" width="100"  align="center"/>
        	<EF:EFColumn ename="recCreateTime" cname="创建时间" width="100"  align="center"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
	<EF:EFWindow id="popData" url="" lazyload="true" width="30%"
		height="30%" title="新增公告" />
