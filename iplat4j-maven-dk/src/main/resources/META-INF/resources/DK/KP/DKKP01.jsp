<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="保养卡片查询">
	<EF:EFRegion id="inqu" title="查询区">
		<div class="row">
			<EF:EFInput ename="cardID" cname="卡片id" colWidth="3" type="hidden"/>
			<EF:EFInput ename="cardCode" cname="卡片代码"  colWidth="4"/>
			<EF:EFInput ename="cardName" cname="卡片名称" colWidth="3"/>
			<EF:EFSelect cname="状态:" ename="status" >
			    <EF:EFOption label="全部" value="" />
				<EF:EFOption label="启用" value="1" />
				<EF:EFOption label="新建" value="0" />
				<EF:EFOption label="禁用" value="-1" />
			</EF:EFSelect>
			</div>

	</EF:EFRegion>
	<EF:EFRegion id="result" title="数据集" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DKKP01">
			<EF:EFColumn ename="cardID" cname="卡片id" hidden="true" />
			<EF:EFColumn ename="cardCode" cname="卡片代码" readonly="true"  width="100"
			 displayType="url"/>
			<EF:EFColumn ename="cardName" cname="卡片名称  " readonly="true"  width="100"/>
			<EF:EFColumn ename="status" cname="状态 " readonly="true"  width="100"/>
			<EF:EFColumn ename="memo" cname="备注 " readonly="true" hidden="true"  width="100"/>
			<EF:EFColumn ename="createTime" cname="创建时间 " readonly="true"  width="150"/>
			<EF:EFColumn ename="createMan" cname="创建人" readonly="true"  width="50"/>
			<EF:EFColumn ename="modifyTime" cname="修改时间 " readonly="true"  width="100"/>
			<EF:EFColumn ename="modifyMan" cname="修改人 " readonly="true"  width="100"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popDataLook" url="" lazyload="true" width="95%" height="90%" title="保养卡片查看" modal="true" />
	<EF:EFWindow id="popDataAdd" url="" lazyload="true" width="95%" height="90%" title="保养卡片新增" modal="true" />
	<EF:EFWindow id="popDataModify" url="" lazyload="true" width="95%" height="90%" title="保养卡片修改" modal="true" />
</EF:EFPage>