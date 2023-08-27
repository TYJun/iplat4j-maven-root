<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="paymentNo" cname="付款单号" />
            <EF:EFSelect ename="paymentStatus" cname="付款状态">
                <EF:EFOption label="全部" value=""/>
                <EF:EFOption label="申请付款" value="1"/>
                <EF:EFOption label="付款完成" value="2"/>
            </EF:EFSelect>
        </div>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
            <EF:EFButton cname="重置" ename="RESET" layout="0"></EF:EFButton>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="合同付款管理">
        <EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
                   checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
            <EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false"/>
            <EF:EFColumn ename="paymentAutoNo" cname="付款管理号" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="paymentNo" cname="付款单号" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="paymentContent" cname="付款内容" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="paymentType" cname="付款方式" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="contPk" cname="合同主键" width="100" align="center" enable="false" hidden="true"/>
            <EF:EFColumn ename="contNo" cname="合同号" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="contName" cname="合同名称" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="surpName" cname="供应商名称" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="currType" cname="币种" width="100" align="center" enable="false" hidden="true"/>
            <EF:EFColumn ename="paymentTaxExcludeAmount" cname="付款金额（元）" width="100" align="center" enable="false" format="{0:c2}"/>
            <EF:EFColumn ename="paymentTaxAmount" cname="付款税额（元）" width="100" align="center" enable="false" format="{0:c2}"/>
            <EF:EFColumn ename="paymentTime" cname="付款时间" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="remark" cname="备注" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="paymentStatus" cname="付款单状态" width="100" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="payment" url="" lazyload="true" width="80%" height="67%" title="窗口"/>