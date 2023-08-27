<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>人员信息管理</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-realName" cname="姓名" colWidth="3" ratio="3:8"/>
			<EF:EFInput ename="inqu_status-0-workNo" cname="工号" colWidth="3" ratio="3:8"/>
			<EF:EFInput ename="inqu_status-0-jobCode" cname="岗位" colWidth="3" ratio="3:8"/>
			<EF:EFInput ename="inqu_status-0-company" cname="公司名称" colWidth="3" ratio="3:8"/>
			<EF:EFInput ename="inqu_status-0-deptNum" cname="所属部门" colWidth="3" ratio="3:8"/>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态" colWidth="3" ratio="3:8">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="wilp.hr.personCode"/>
			</EF:EFSelect>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="RESET" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="人员配置信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="false" readonly="true" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" align="center" enable="false"/>
			<EF:EFColumn ename="realName" cname="姓名" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="workNo" cname="工号" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="sex" cname="性别" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="deptNum" cname="所属部门" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="serviceDeptNum" cname="服务部门" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="schoolingCode" cname="学历" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="politicalStatus" cname="政治面貌" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="preInTime" cname="入职\入场日期" width="150" align="center" enable="false"/>
			<EF:EFColumn ename="inTime" cname="入职时间" width="150" align="center"  enable="false"/>
			<EF:EFComboColumn ename="statusCode" cname="状态" align="center" enable="false">
				<EF:EFCodeOption codeName="wilp.hr.personCode"/>
			</EF:EFComboColumn>
			<EF:EFColumn ename="creatorName" cname="登记人" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="createDate" cname="登记时间" width="150" align="center" enable="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="新增信息" url=" " lazyload="true" width="97%" height="80%"></EF:EFWindow>
<EF:EFWindow id="popDataModify" title="确认入职" url=" " lazyload="true" width="30%" height="45%"></EF:EFWindow>