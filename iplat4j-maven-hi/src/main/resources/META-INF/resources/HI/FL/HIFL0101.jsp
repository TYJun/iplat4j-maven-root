<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>医院标识信息</title>
<EF:EFPage>
	<EF:EFRegion id="result" title="分类信息" fitHeight="true">
		<div class="row">
			<EF:EFInput ename="classifyName" cname="医院标识分类名称" type="text" required="true" placeholder="请输入医院分类名称"/>
			</br>
			<EF:EFTreeInput ename="parentId" cname="上级分类" readonly="true" required="true"
							serviceName="HIFL01" methodName="queryTree" textField="text"
							valueField="label" hasChildren="leaf"
							root="{label: 'root',text: '分类'}" popupTitile="上级分类" clear ="true" >
			</EF:EFTreeInput>
			</br>
			<EF:EFInput ename="remark" type="textarea" cname="备注" placeholder="请输入备注"/>
			<EF:EFInput ename="type" type="hidden" cname="操作"/>
			<EF:EFInput ename="id" type="hidden" cname="主键"/>
			<EF:EFInput ename="classifyNum" type="hidden" cname="医院标识编码"/>
			<EF:EFInput ename="superClassifyNum" type="hidden" cname="上级医院标识编码"/>
			<EF:EFInput ename="superClassifyName" type="hidden" cname="上级医院标识名称"/>
			<EF:EFInput ename="remark" type="hidden" cname="备注"/>
		</div>
	</EF:EFRegion>
</EF:EFPage>