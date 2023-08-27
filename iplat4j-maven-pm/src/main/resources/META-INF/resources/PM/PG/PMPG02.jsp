<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <%-- 查询区域 --%>
    <EF:EFRegion id="inqu" title="查询条件" >
        <div class="row">
            <EF:EFInput ename="inqu_status-0-typeCode" cname="类型编码" />
            <EF:EFInput ename="inqu_status-0-typeName" cname="类型名称" />
        </div>
    </EF:EFRegion>
    <%-- 数据集 --%>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
                   autoFit="true" checkMode="single,row" readonly="true" rowNo="true"
                   isFloat="true" serviceName="PMPG02" queryMethod="queryPmTypeMsg">
            <EF:EFColumn ename="typeCode" cname="类型编码" width="12" align="center" enable="false"/>
            <EF:EFColumn ename="typeName" cname="类型名称" width="13" align="center" enable="false"/>
            <EF:EFColumn ename="typeRemark" cname="类型备注" width="13" align="center" enable="false"/>
            <EF:EFColumn ename="stageName" cname="绑定阶段名称" width="30" align="center" enable="false"/>
            <EF:EFColumn ename="createName" cname="创建人" width="8" align="center" enable="false"/>
            <EF:EFColumn ename="createTime" cname="创建日期" width="8" align="center" enable="false"/>
            <EF:EFColumn ename="updateName" cname="修改人" width="8" align="center" enable="false"/>
            <EF:EFColumn ename="updateTime" cname="修改日期" width="8" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>

    <!-- 项目类型新增/编辑 -->
    <EF:EFWindow id="typeNew" title="新增项目类型配置" url="" lazyload="true" width="85%" height="88%"/>
    <EF:EFWindow id="typeEdit" title="编辑项目类型配置" url="" lazyload="true" width="85%" height="88%"/>
</EF:EFPage>