<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--付款信息--%>
<EF:EFPage>
    <EF:EFRegion id="inqu" title="采购付款" showClear="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-payNo" cname="付款号" readonly="true"/>
            <EF:EFSelect ename="inqu_status-0-contId" cname="合同" required="true"
                         resultId="cont" textField="text" valueField="label"
                         serviceName="MPFP0101" methodName="queryCont" optionLabel="请选择"
                         filter="contains">
            </EF:EFSelect>
            <EF:EFSelect ename="inqu_status-0-payWay" cname="付款方式" optionLabel="请选择">
                <EF:EFOption label="发票付款" value="01"/>
                <EF:EFOption label="预付款" value="02"/>
            </EF:EFSelect>
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-payCost" cname="付款含税金额(元)" required="true"
                        maxLength="10" data-regex="/^(([1-9]\d*)(\.\d{1,2})?)$|^(0\.0?([1-9]\d?))$/" data-errorprompt="请输入数字"/>
            <EF:EFDatePicker ename="inqu_status-0-payDate" cname="付款日期" readonly="true" required="true"/>
            <EF:EFInput ename="inqu_status-0-payContent" cname="付款内容"  type="textarea"/>
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-id" cname="主键" type = "hidden"/>
            <EF:EFInput ename="inqu_status-0-contNo" cname="合同号" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-payWayName" cname="付款方式" type="hidden"/>
            <EF:EFInput ename="type" cname="操作" type = "hidden"/>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="发票明细列表">
        <EF:EFGrid blockId="result" autoDraw="no">
            <EF:EFColumn ename="id" cname="主键" hidden="true"/>
            <EF:EFColumn ename="invoiceId" cname="发票ID" hidden="true" />
            <EF:EFColumn ename="invoiceNo" cname="发票号" width="80" align="center" enable="false"/>
            <EF:EFColumn ename="contId" cname="合同ID" hidden="true"/>
            <EF:EFColumn ename="contNo" cname="合同号" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="contName" cname="合同名称" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="supplierName" cname="开票单位" width="100" align="center" enable="false"/>
            <EF:EFColumn ename="supplierNum" cname="开票单位编码" hidden="true" />
            <EF:EFColumn ename="invoiceTypeName" cname="发票类型" width="80" align="center" enable="false"/>
            <EF:EFColumn ename="invoiceType" cname="发票类型编码" hidden="true" />
            <EF:EFColumn ename="invoiceCost" cname="发票含税金额(元)" width="120" align="center" enable="false"/>
            <EF:EFColumn ename="payDate" cname="发票开付日期" width="80" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url="" lazyload="true" width="90%" height="90%" title="发票选择"/>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>