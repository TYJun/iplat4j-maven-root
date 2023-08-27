<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-fileName" cname="文件名" />
		</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="上传的文件">
			<EF:EFGrid blockId="result" autoDraw="no" checkMode="single"
				autoBind="false" readonly="false">
				<EF:EFColumn ename="inqu_status-0-id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="inqu_status-0-fileId" cname="文件ID" width="100" readonly="true" />
				<EF:EFColumn ename="inqu_status-0-fileName" cname="文件名 " width="100" />
				<EF:EFColumn ename="inqu_status-0-fileSize" cname="文件大小" width="100" />
				<EF:EFColumn ename="inqu_status-0-fileType" cname="文件类型 " width="100" />
			</EF:EFGrid>
		</EF:EFRegion>
</EF:EFPage>

