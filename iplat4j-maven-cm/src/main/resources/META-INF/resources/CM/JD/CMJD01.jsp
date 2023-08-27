<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <%-- 查询区域 --%>
    <EF:EFRegion id="inqu" title="查询条件" >
        <div class="row">
            <EF:EFInput ename="inqu_status-0-nodeAutoNo" cname="节点编码" />
            <EF:EFInput ename="inqu_status-0-nodeName" cname="节点名称" />
        </div>
    </EF:EFRegion>
    <%-- 数据集 --%>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
                   autoFit="true" checkMode="single,row" readonly="true" rowNo="true"
                   isFloat="true" serviceName="CMJD01" queryMethod="query">
            <EF:EFColumn ename="nodeId" cname="节点ID" width="10" align="center" enable="false" hidden="true"/>
            <EF:EFColumn ename="nodeAutoNo" cname="节点编码" width="12" align="center" enable="false"/>
            <EF:EFColumn ename="nodeName" cname="节点名称" width="20" align="center" enable="false"/>
            <EF:EFColumn ename="nodeRemark" cname="节点备注" width="35" align="center" enable="false"/>
            <EF:EFColumn ename="recCreator" cname="创建人" width="8" align="center" enable="false"/>
            <EF:EFColumn ename="recCreateTime" cname="创建日期" width="8" align="center" enable="false"/>
            <EF:EFColumn ename="recRevisor" cname="修改人" width="8" align="center" enable="false"/>
            <EF:EFColumn ename="recReviseTime" cname="修改日期" width="8" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
    <!-- 项目阶段新增/编辑 -->
    <EF:EFWindow id="node" title="新增节点信息" url="" lazyload="true" width="40%" height="25%"/>
</EF:EFPage>