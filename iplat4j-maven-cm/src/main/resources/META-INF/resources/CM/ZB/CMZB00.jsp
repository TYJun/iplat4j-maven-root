<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-supplierName" cname="供应商名称" />
        </div>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="查询" ename="QUERY" layout="0" ></EF:EFButton>
            <EF:EFButton cname="重置" ename="REQUERY" layout="0" ></EF:EFButton>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="供应商选择">
        <EF:EFGrid blockId="supplier" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
                   checkMode="row" readonly="true" rowNo="true" isFloat="true" serviceName="CMDJ0101" queryMethod="querySupplier">
            <EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
            <EF:EFColumn ename="surpNum" cname="供应商编码" width="100" align="center"/>
            <EF:EFColumn ename="surpName" cname="供应商名称" width="100" align="center"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>

