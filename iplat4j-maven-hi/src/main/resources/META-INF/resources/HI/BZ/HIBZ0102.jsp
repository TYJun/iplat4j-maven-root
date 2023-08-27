<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>医院标准信息</title>
<EF:EFPage>
	<EF:EFRegion id="result" title="分类信息" fitHeight="true" >
		<div class="row">
			<EF:EFInput ename="standardName" cname="标准名称" type="text" required="true" placeholder="请输入医院分类名称"/>
			<EF:EFTreeInput ename="classifyId" cname="标识分类" readonly="true" required="true"
							serviceName="HIBZ01" methodName="queryTree" textField="text"
							valueField="label" hasChildren="leaf"
							root="{label: 'root',text: '分类'}" popupTitile="上级分类" clear ="true" >
			</EF:EFTreeInput>
			<EF:EFInput ename="remark" type="textarea" cname="备注" placeholder="请输入备注"/>
			<EF:EFInput ename="type" type="hidden" cname="操作"/>
			<EF:EFInput ename="id" type="hidden" cname="主键"/>
			<EF:EFInput ename="standardNum" type="hidden" cname="医院标准编码"/>
			<EF:EFInput ename="superClassifyNum" type="hidden" cname="上级医院标准编码"/>
			<EF:EFInput ename="superClassifyName" type="hidden" cname="上级医院标准名称"/>
			<EF:EFInput ename="remark" type="hidden" cname="备注"/>
		</div>

		<div title="附件上传">
			<EF:EFGrid blockId="resultB" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
					   checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryFile">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
				<EF:EFColumn ename="relationId" cname="relationId" width="100" hidden="true"/>
				<EF:EFColumn ename="docId" cname="附件id" width="200" hidden="true"/>
				<EF:EFColumn ename="filePath" cname="附件路径" width="200" hidden="true"/>
				<EF:EFColumn ename="fileName" cname="附件名称" width="200"/>
				<EF:EFColumn ename="fileSize" cname="附件大小" width="100"/>
				<EF:EFColumn ename="remark" cname="附件说明" width="200"/>
				<EF:EFColumn ename="recCreator" cname="上传人" width="200"/>
				<EF:EFColumn ename="recCreateTime" cname="上传时间" />

			</EF:EFGrid>
		</div>
	</EF:EFRegion>
	<!-- 附件上传窗口 -->
	<EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%" title="附件上传">
		<EF:EFRegion id="upload" title="文件上传">
			<EF:EFUpload ename="contentFile" docTag="co_file" path="Content"/>
		</EF:EFRegion>
	</EF:EFWindow>
</EF:EFPage>