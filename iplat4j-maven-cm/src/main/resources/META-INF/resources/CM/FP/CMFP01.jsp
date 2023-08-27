<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="invoiceAutoNo" cname="发票管理号" />
            <EF:EFInput ename="invoiceNo" cname="发票号" />
            <EF:EFSelect ename="invoiceStatus" cname="发票状态">
                <EF:EFOption label="全部" value=""/>
                <EF:EFOption label="发票登记" value="0"/>
                <EF:EFOption label="申请付款" value="1"/>
            </EF:EFSelect>
        </div>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
            <EF:EFButton cname="重置" ename="RESET" layout="0"></EF:EFButton>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="发票登记管理">
        <EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
                   checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
            <EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false"/>
            <EF:EFColumn ename="invoiceAutoNo" cname="发票管理号" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="invoiceNo" cname="发票号" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="surpName" cname="开票单位" width="150" align="center" enable="false"/>
            <EF:EFColumn ename="invoiceType" cname="发票类别" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="currType" cname="币种" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="invoiceTaxExcludeAmount" cname="发票金额（元）" width="100" align="center" enable="false" format="{0:c2}"/>
            <EF:EFColumn ename="invoiceTaxAmount" cname="发票税额（元）" width="100" align="center" enable="false" format="{0:c2}"/>
            <EF:EFColumn ename="invoiceIssuingTime" cname="发票开付时间" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="contNo" cname="合同号" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="remark" cname="备注" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="invoiceStatus" cname="发票状态" width="100" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="invoice" url="" lazyload="true" width="80%" height="67%" title="窗口"/>