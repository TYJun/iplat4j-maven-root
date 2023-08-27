<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>人员配置信息</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-surpName" cname="物业公司"/>
			<EF:EFInput ename="inqu_status-0-serviceDeptName" cname="服务部门"/>
			<EF:EFInput ename="inqu_status-0-position" cname="岗位"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="RESET" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="人员配置信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="false" readonly="true" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" align="center" enable="false"/>
			<EF:EFColumn ename="surpName" cname="物业公司" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="serviceDeptName" cname="服务部门" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="position" cname="岗位" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="peopleLines" cname="招标配额" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="peopleLinesStable" cname="稳定配额" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="memo" cname="备注" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="creatorName" cname="登记人" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="createTime" cname="登记时间" width="100" align="center" enable="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="新增信息" url=" " lazyload="true" width="45%" height="65%"></EF:EFWindow>