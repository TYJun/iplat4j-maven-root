<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="{pageContext.request.contextPath}" />

<EF:EFPage title="新增宿舍公告">
	<EF:EFRegion id="register">
		<div class="row">
			<EF:EFInput ename="noticeNo" cname="公告序号" colWidth="6" required = "true" ratio="3:8"/>
			<EF:EFSelect ename="noticeType" cname="公告类别" colWidth="6" required = "true" ratio="3:8">
				<EF:EFOption label="请选择公告类别" value=""/>
				<EF:EFOption label="入住公告"  value="入住公告"/>
				<EF:EFOption label="换宿公告"  value="换宿公告"/>
				<EF:EFOption label="退宿公告"  value="退宿公告"/>
			</EF:EFSelect>
			<EF:EFInput ename="notice" cname="公告内容" colWidth="6" type="textarea"
				required = 'true' ratio="3:8" rows="3"/>
		</div>
	</EF:EFRegion>
</EF:EFPage>

