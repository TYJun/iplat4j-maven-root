<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>发票选择</title>
<EF:EFPage>
    <EF:EFRegion id="inqu" title="选择" height="200" showClear="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-contNo" cname="合同号" readonly="true"/>
            <EF:EFInput ename="inqu_status-0-invoiceNo" cname="发票号" />
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="发票列表">
        <EF:EFGrid blockId="result" autoDraw="no" checkMode="row" autoBind="true" readonly="true" >
            <EF:EFColumn ename="id" cname="主键" hidden="true"/>
            <EF:EFColumn ename="invoiceNo" cname="发票号" width="80" align="center"/>
            <EF:EFColumn ename="contId" cname="合同ID" hidden="true"/>
            <EF:EFColumn ename="contNo" cname="合同号" width="100" align="center"/>
            <EF:EFColumn ename="contName" cname="合同名称" width="100" align="center"/>
            <EF:EFColumn ename="supplierName" cname="开票单位" width="100" align="center"/>
            <EF:EFColumn ename="supplierNum" cname="开票单位编码" hidden="true" />
            <EF:EFColumn ename="invoiceTypeName" cname="发票类型" width="80" align="center"/>
            <EF:EFColumn ename="invoiceType" cname="发票类型编码" hidden="true" />
            <EF:EFColumn ename="invoiceCost" cname="发票含税金额(元)" width="120" align="center"/>
            <EF:EFColumn ename="payDate" cname="发票开付日期" width="80" align="center"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>