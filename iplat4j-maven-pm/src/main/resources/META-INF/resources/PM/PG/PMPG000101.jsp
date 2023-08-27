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
                   autoFit="true" checkMode="multiple,row" readonly="true" rowNo="true"
                   isFloat="true" serviceName="PMPG01" queryMethod="queryPmStageMsg">
            <EF:EFColumn ename="stageId" cname="阶段ID" width="100" align="center" enable="false" hidden="true"/>
            <EF:EFColumn ename="stageCode" cname="阶段编码" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="stageName" cname="阶段名称" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="stageRemark" cname="阶段备注" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="createName" cname="创建人" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="createTime" cname="创建日期" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="updateName" cname="修改人" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="updateTime" cname="修改日期" width="100" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>