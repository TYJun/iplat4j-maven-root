<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="群组信息管理">
	<div class="row">
		<EF:EFRegion id="inqu" title="查询区" showClear="true">
				<EF:EFInput ename="curVarId" cname="当前选中群组主键" type="hidden"/>
				<EF:EFInput colWidth="3" ename="variableCode" cname="群组编号" />
				<EF:EFInput colWidth="3" ename="variableName" cname="群组名称" />
				<EF:EFInput colWidth="3" ename="recCreater" cname="创建人" />

		</EF:EFRegion>
		<EF:EFRegion id="resultVar" title="抄送群组信息" >
			<EF:EFGrid blockId="resultVar" autoDraw="no" serviceName="MCVM01" queryMethod="query"  readonly="true"  height="205px">
				<EF:EFColumn ename="id" cname="主键"  hidden="true"/>
				<EF:EFColumn ename="variableCode" cname="群组编号" />
				<EF:EFColumn ename="variableName" cname="群组名称" />
				<EF:EFColumn ename="recCreateTime" cname="创建时间"	/>
				<EF:EFColumn ename="recCreater" cname="创建人" />
			</EF:EFGrid>
		</EF:EFRegion>

		<EF:EFRegion id="resultPer" title="人员信息" >
			<EF:EFGrid blockId="resultPer" autoDraw="no" serviceName="MCVM01" queryMethod="queryPer"  readonly="true" height="205px">
				<EF:EFColumn ename="id" cname="主键"  hidden="true"/>
				<EF:EFColumn ename="variableName" cname="所属变量名称"  hidden="true" type="hidden" />
				<EF:EFColumn ename="variableCode" cname="所属变量编码"  hidden="true" type="hidden" />
				<EF:EFColumn ename="workNo" cname="人员工号"/>
				<EF:EFColumn ename="name" cname="人员姓名" />
				<EF:EFColumn ename="contactTel" cname="人员电话" />
			</EF:EFGrid>
		</EF:EFRegion>
		<EF:EFWindow id="popData" url=""  lazyload="true" width="50%" height="20%"  modal="true" />
		<EF:EFWindow id="popDataPer" url=""  lazyload="true" width="80%" height="90%"  modal="true" />
	</div>
</EF:EFPage>