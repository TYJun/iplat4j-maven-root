<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="false">
	    <EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden" />
	    <EF:EFInput ename="inqu_status-0-contTermNum" cname="合同条款编码：" type="hidden" />
		<EF:EFInput ename="inqu_status-0-contTermName" cname="合同条款名称："
			colWidth="4" ratio="3:8" type="text" required="true" />
		<EF:EFInput ename="inqu_status-0-remark" cname="合同条款内容：" colWidth="4"
			ratio="3:8" type="textarea" />
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="确定" ename="SAVEPR" layout="0"/>
			<EF:EFButton cname="取消" ename="CANCEL" layout="0"/>
		</div>
	</EF:EFRegion>
	<!--多页面显示-->
	<EF:EFTab id="tab-tab_grid" active="0">
		<div title="条款详细内容">
			<EF:EFGrid blockId="resultA" needAuth="false" autoDraw="no" autoBind="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryDefine">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="content" cname="合同条款内容" width="100" align="center"/>
			</EF:EFGrid>
		</div>
	</EF:EFTab>
</EF:EFPage>
