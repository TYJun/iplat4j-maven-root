<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <%-- 查询区域 --%>
    <EF:EFRegion id="inqu" title="查询条件" >
        <div class="row">
            <EF:EFInput ename="inqu_status-0-scheduleAutoNo" cname="合同进度编码" />
            <EF:EFInput ename="inqu_status-0-scheduleName" cname="合同进度名称" />
        </div>
    </EF:EFRegion>
    <%-- 数据集 --%>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
                   autoFit="true" checkMode="single,row" readonly="true" rowNo="true"
                   isFloat="true" serviceName="CMJD02" queryMethod="query">
            <EF:EFColumn ename="scheduleId" cname="id" width="12" align="center" enable="false" hidden="true"/>
            <EF:EFColumn ename="scheduleAutoNo" cname="合同进度编码" width="12" align="center" enable="false"/>
            <EF:EFColumn ename="scheduleName" cname="合同进度名称" width="13" align="center" enable="false"/>
            <EF:EFColumn ename="scheduleRemark" cname="合同进度备注" width="13" align="center" enable="false"/>
            <EF:EFColumn ename="nodeName" cname="合同节点名称" width="30" align="center" enable="false"/>
            <EF:EFColumn ename="recCreator" cname="创建人" width="8" align="center" enable="false"/>
            <EF:EFColumn ename="recCreateTime" cname="创建日期" width="8" align="center" enable="false"/>
            <EF:EFColumn ename="recRevisor" cname="修改人" width="8" align="center" enable="false"/>
            <EF:EFColumn ename="recReviseTime" cname="修改日期" width="8" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>

    <!-- 合同进度新增/编辑 -->
    <EF:EFWindow id="schedule" title="新增合同进度配置" url="" lazyload="true" width="85%" height="80%"/>
</EF:EFPage>