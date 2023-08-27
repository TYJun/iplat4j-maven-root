<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>独立任务登记管理</title>
<EF:EFPage>
    <div class="row">
        <EF:EFRegion id="inqu" title="查询条件">
            <div class="row">
                <EF:EFInput ename="inqu_status-0-taskNo" cname="任务单号"/>
                <EF:EFTreeInput ename="inqu_status-0-parentId" cname="任务类别" serviceName="ITFL01" methodName="queryItClassifyTree"
                                valueField="id" textField="classifyName" hasChildren="isLeaf" readonly="true"
                                root="{id: 'root', classifyName: '根节点'}" ratio="4:8">
                </EF:EFTreeInput>
                <EF:EFInput ename="inqu_status-0-reqDeptName" cname="需求科室"/>
                <EF:EFInput ename="inqu_status-0-serveDeptName" cname="服务科室"/>
                <EF:EFDateSpan startName="inqu_status-0-recCreateTimeS"  endName="inqu_status-0-recCreateTimeE"
                               startCname="创建时间起" endCname="创建时间止"/>
            </div>
            <div class="button-region" id="buttonDiv">
                <EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
                <EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
            </div>
        </EF:EFRegion>
        <EF:EFRegion id="result" title="独立任务列表">
            <EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true">
                <EF:EFColumn ename="itTaskId" cname="独立任务表主键" width="100" enable="false" align="center" hidden="true"/>
                <EF:EFColumn ename="taskNo" cname="任务单号" width="100" enable="false" align="center" displayType="url"/>
                <EF:EFColumn ename="reqStaffName" cname="需求人" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="reqTelNum" cname="需求电话" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="reqDeptName" cname="需求科室" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="classifyName" cname="任务分类" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="serveContent" cname="服务内容" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="spotName" cname="服务地点" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="serveDeptName" cname="服务科室" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="remark" cname="备注" width="100" enable="false" align="center" hidden="true"/>
                <EF:EFColumn ename="recCreateTime" cname="创建时间" width="100" enable="false" align="center"/>
            </EF:EFGrid>
        </EF:EFRegion>
        <EF:EFWindow id="popData" url="" lazyload="true" width="80%" height="80%" title="独立任务登记" modal="true" />
    </div>
</EF:EFPage>

