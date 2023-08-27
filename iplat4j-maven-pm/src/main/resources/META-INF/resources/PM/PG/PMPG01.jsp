<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <%-- 查询区域 --%>
    <EF:EFRegion id="inqu" title="查询条件" >
        <div class="row">
            <EF:EFInput ename="inqu_status-0-stageCode" cname="阶段编码" />
            <EF:EFInput ename="inqu_status-0-stageName" cname="阶段名称" />
        </div>
    </EF:EFRegion>
    <%-- 数据集 --%>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
                   autoFit="true" checkMode="single,row" readonly="true" rowNo="true"
                   isFloat="true" serviceName="PMPG01" queryMethod="queryPmStageMsg">
            <EF:EFColumn ename="stageId" cname="阶段ID" width="10" align="center" enable="false" hidden="true"/>
            <EF:EFColumn ename="stageCode" cname="阶段编码" width="12" align="center" enable="false"/>
            <EF:EFColumn ename="stageName" cname="阶段名称" width="20" align="center" enable="false"/>
            <EF:EFColumn ename="stageRemark" cname="阶段备注" width="35" align="center" enable="false"/>
            <EF:EFColumn ename="createName" cname="创建人" width="8" align="center" enable="false"/>
            <EF:EFColumn ename="createTime" cname="创建日期" width="8" align="center" enable="false"/>
            <EF:EFColumn ename="updateName" cname="修改人" width="8" align="center" enable="false"/>
            <EF:EFColumn ename="updateTime" cname="修改日期" width="8" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
    <!-- 项目阶段新增/编辑 -->
    <EF:EFWindow id="stageNew" title="新增项目阶段配置" url="" lazyload="true" width="40%" height="45%"/>
    <EF:EFWindow id="stageEdit" title="编辑项目阶段配置" url="" lazyload="true" width="40%" height="45%"/>
</EF:EFPage>