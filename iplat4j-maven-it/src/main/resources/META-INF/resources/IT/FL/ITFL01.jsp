<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>独立任务类别管理</title>
<EF:EFPage>
    <div class="row">
        <div class="col-md-2">
            <EF:EFRegion id="itClassifyMenu" title="类别名称">
                <EF:EFTree id="menu" valueField="id" textField="classifyName" hasChildren="isLeaf"
                serviceName="ITFL01" methodName="queryItClassifyTree" style="height:560px;"/>
            </EF:EFRegion>
        </div>
        <div class="col-md-10">
            <EF:EFRegion id="inqu" title="查询条件">
                <div class="row">
                    <EF:EFInput ename="typeId" cname="typeId" type="hidden"/>
                    <EF:EFInput ename="typeName" cname="typeName" type="hidden"/>
                    <EF:EFInput ename="inqu_status-0-classifyNum" cname="分类编码"/>
                    <EF:EFInput ename="inqu_status-0-classifyName" cname="分类名称"/>
                </div>
                <div class="button-region" id="buttonDiv">
                    <EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
                    <EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
                </div>
            </EF:EFRegion>
            <EF:EFRegion id="result" title="独立任务分类列表">
                <EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true">
                    <EF:EFColumn ename="itClassifyId" cname="独立任务分类表主键" width="100" enable="false" align="center" hidden="true"/>
                    <EF:EFColumn ename="classifyNum" cname="独立任务分类编码" width="100" enable="false" align="center"/>
                    <EF:EFColumn ename="classifyName" cname="独立任务分类名称" width="100" enable="false" align="center"/>
                    <EF:EFColumn ename="parentId" cname="上级类别ID" width="100" enable="false" align="center" hidden="true"/>
                    <EF:EFColumn ename="parentName" cname="上级类别名称" width="100" enable="false" align="center"/>
                    <EF:EFColumn ename="level" cname="类别层级" width="100" enable="false" align="center" hidden="true"/>
                    <EF:EFColumn ename="remark" cname="备注" width="100" enable="false" align="center"/>
                    <EF:EFColumn ename="sortNo" cname="排序" width="100" enable="false" align="center" hidden="true"/>
                </EF:EFGrid>
            </EF:EFRegion>
            <EF:EFWindow id="popData" url="" lazyload="true" width="40%" height="60%" title="独立任务类别录入" modal="true" />
        </div>
    </div>
</EF:EFPage>

