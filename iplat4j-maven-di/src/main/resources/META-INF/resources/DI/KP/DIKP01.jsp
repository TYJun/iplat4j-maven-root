<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row" style="height: 1px;">
			<EF:EFInput ename="cardcode" cname="卡片代码:" />
			<EF:EFInput ename="cardname" cname="卡片名称:" />
			<EF:EFSelect ename="status" cname="状态:" width="100">
				<EF:EFOption label="请选择" value="" />
				<EF:EFOption label="新建" value="0" />
			   <EF:EFOption label="启用" value="1" />
			   <EF:EFOption label="禁用" value="-1" />
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="设备巡查卡片管理">
		<EF:EFGrid checkMode="single" blockId="result" autoDraw="no">
			<EF:EFColumn readonly="true" ename="cardid" cname="cardid" width="100" hidden="true" />
			<EF:EFColumn required="true" readonly="true" ename="cardcode" cname="卡片代码" width="50" align="center" />
			<EF:EFColumn required="true" readonly="true" ename="cardname" cname="卡片名称" width="50" align="center" />
			<EF:EFColumn  readonly="true" ename="status" cname="状态" width="50" align="center" />
			<EF:EFColumn  readonly="true" ename="createtime" cname="创建时间" width="50" align="center" />
			<EF:EFColumn  readonly="true" ename="createman" cname="创建人" width="50" align="center" />
			<EF:EFColumn  readonly="true" ename="modifytime" cname="修改时间" width="50" align="center" />
			<EF:EFColumn  readonly="true" ename="modifyman" cname="修改人" width="50" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="60%"
	height="90%">
</EF:EFWindow>	


