<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <%-- 查询区域 --%>
    <EF:EFRegion id="inqu" title="查询条件" >
        <div class="row">
            <EF:EFInput ename="fileName" cname="文件名称" />
        </div>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="查询" ename="QUERY" layout="0" ></EF:EFButton>
            <EF:EFButton cname="重置" ename="REQUERY" layout="0" ></EF:EFButton>
        </div>
    </EF:EFRegion>
    <%-- 数据集 --%>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
                   autoFit="true" checkMode="single,row" readonly="true" rowNo="true"
                   isFloat="true" serviceName="CMMB01" queryMethod="query">
            <EF:EFColumn ename="fileId" cname="文件ID" width="10" align="center" enable="false" hidden="true"/>
            <EF:EFColumn ename="filePath" cname="文件路径" width="12" align="center" enable="false" hidden="true"/>
            <EF:EFColumn ename="fileName" cname="文件名称" width="20" align="center" enable="false"/>
            <EF:EFColumn ename="fileSize" cname="文件大小（M）" width="12" align="center" enable="false"/>
            <EF:EFColumn ename="fileStatus" cname="文件状态" width="10" align="center" enable="false"/>
            <EF:EFColumn ename="recCreator" cname="创建人" width="10" align="center" enable="false"/>
            <EF:EFColumn ename="recCreateTime" cname="创建日期" width="10" align="center" enable="false"/>
            <EF:EFColumn ename="recRevisor" cname="修改人" width="10" align="center" enable="false"/>
            <EF:EFColumn ename="recReviseTime" cname="修改日期" width="10" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
    <%--上传--%>
    <%--docTag:附件的标签，由业务方生成，用于附件的分组--%>
    <%--path:附件上传时的相对路径--%>
    <%--readonly:只展示已上传的文件--%>
    <EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%" title="附件上传">
        <EF:EFRegion id="upload" title="文件上传">
            <EF:EFUpload ename="templateFile" docTag="template" path="contract/template" readonly="false"/>
        </EF:EFRegion>
    </EF:EFWindow>
</EF:EFPage>