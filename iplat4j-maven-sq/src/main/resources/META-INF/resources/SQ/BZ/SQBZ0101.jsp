<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" head="hidden">
		<div class="row">
			<EF:EFInput ename="type" colWidth="6" ratio="3:7" cname="操作类型" type="hidden"/>
			<EF:EFInput ename="standardId" colWidth="6" ratio="3:7" cname="问卷ID" type="hidden"/>
			<EF:EFInput ename="pProjectId" colWidth="6" ratio="3:7" cname="项目ID" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-standardName" cname="问卷主题" colWidth="6" ratio="3:7" type="text" required="true"/>
				<%-- <EF:EFSelect ename="sqType" cname="问卷类型"
                    resultId="sqType" textField="label" valueField="value"
                    serviceName="SQBZ04" methodName="sqType" optionLabel="请选择"
                    colWidth="5" ratio="2:8" required="true" /> --%>
<%--			<EF:EFSelect ename="sqType" cname="问卷类型" colWidth="6" ratio="3:7" >--%>
<%--				<EF:EFOption label="请选择" value=""/>--%>
<%--				<EF:EFCodeOption codeName="WILP.sq.sqType" textField="label" valueField="value"/>--%>
<%--			</EF:EFSelect>--%>
			<EF:EFInput colWidth="6" ratio="3:7" type="textarea" ename="inqu_status-0-remark" cname="备注" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="保存" ename="SURE" layout="3"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<div class="col-md-5">
		<EF:EFRegion id="result" title="项目">
			<EF:EFGrid blockId="resultProject" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
					   checkMode="single,row" isFloat="true">
				<EF:EFColumn ename="orderNo" cname="序号" width="1" align="center" required="true"/>
				<EF:EFColumn ename="projectId" cname="项目ID" width="2" enable="false" hidden="true"/>
				<EF:EFColumn ename="projectName" cname="项目名称" width="2" align="center" />
				<EF:EFColumn ename="projectRemark" cname="项目备注" width="2" align="center" />
				<EF:EFColumn ename="operation" cname="操作" width="1" align="center" enable="false"/>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
	<div class="col-md-7">
		<EF:EFRegion id="result" title="题目">
			<EF:EFGrid blockId="resultProblem" needAuth="true" autoDraw="no" autoBind="false" autoFit="true"
					   checkMode="single,row" readonly="true" isFloat="true" sumType="all"
					   queryMethod="queryInstanceByProjectId">
				<EF:EFColumn ename="orderNo" cname="序号" width="1" align="center" required="true"/>
				<EF:EFColumn ename="projectId" cname="项目ID" width="2" enable="false" hidden="true"/>
				<EF:EFColumn ename="instanceId" cname="题目ID" width="2" enable="false" hidden="true"/>
				<EF:EFColumn ename="instanceName" cname="题目描述" width="6" align="center" enable="false"/>
				<EF:EFColumn ename="pointType" cname="打分编码" width="2" align="center" enable="false" hidden="true"/>
				<EF:EFColumn ename="pointName" cname="打分方式" width="2" align="center" enable="false"/>
				<EF:EFColumn ename="point" cname="分值" width="2" align="center" enable="false"/>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
</EF:EFPage>
<EF:EFWindow id="popData" url="" lazyload="true" width="50%" height="80%" title="题目详情" modal="true" />