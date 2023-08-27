<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <EF:EFRegion id="result" fitHeight="true">
        <EF:EFInput ename="contPk" cname="合同主键" colWidth="12" ratio="4:4" type="hidden"/>
        <EF:EFInput ename="paymentAutoNo" cname="付款管理号" colWidth="12" ratio="4:4" type="hidden"/>
        <EF:EFInput ename="paymentNo" cname="付款单号" colWidth="12" ratio="4:4"/>
        <EF:EFSelect ename="paymentType" cname="付款方式" colWidth="12" ratio="4:4">
            <EF:EFOption label="发票付款" value="FPFK"/>
            <EF:EFOption label="预付款" value="YFK"/>
        </EF:EFSelect>
        <EF:EFInput ename="paymentContent" cname="付款内容" type="textarea" colWidth="12" ratio="4:4"/>
        <EF:EFPopupInput ename="inqu_status-0-contNo" cname="合同号" colWidth="12" ratio="4:4" center="true"
            popupTitle="合同号选择" readonly="true" valueField="contNo" textField="contNo"
            containerId="ef_popup_contNo" popupWidth="1050" popupHeight="460" />
        <EF:EFPopupInput ename="inqu_status-0-invoiceAutoNo" cname="发票管理号" colWidth="12" ratio="4:4" center="true"
            popupTitle="发票号选择" readonly="true" valueField="invoiceAutoNo" textField="invoiceAutoNo"
            containerId="ef_popup_invoiceAutoNo" popupWidth="1050" popupHeight="460" />
        <EF:EFSelect ename="currType" cname="币种" colWidth="12" ratio="4:4">
            <EF:EFOption label="人民币" value="RMB"/>
        </EF:EFSelect>
        <EF:EFInput ename="paymentTaxExcludeAmount" cname="付款金额（元）" format="{0:c2}"
                    data-regex="/^[1-9]\d*|0$/" data-errorprompt="请输入数字"
                    colWidth="12" ratio="4:4"/>
        <EF:EFInput ename="paymentTaxAmount" cname="付款税额（元）" format="{0:c2}"
                    data-regex="/^[1-9]\d*|0$/" data-errorprompt="请输入数字"
                    colWidth="12" ratio="4:4"/>
        <EF:EFDatePicker ename="paymentTime" cname="付款时间" readonly="true" colWidth="12" ratio="4:4"/>
        <EF:EFInput ename="remark" cname="备注" type="textarea" colWidth="12" ratio="4:4"/>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="保存" ename="save" layout="0"></EF:EFButton>
            <EF:EFButton cname="返回" ename="return" layout="0"></EF:EFButton>
        </div>
    </EF:EFRegion>

    <div id="ef_popup_contNo" style="display: none">
        <EF:EFRegion id="inqu" title="查询条件">
            <div class="row">
                <EF:EFInput ename="inqu_status-0-contNoWindow" cname="合同号"/>
                <EF:EFInput ename="inqu_status-0-contNameWindow" cname="合同名称"/>
                <EF:EFButton ename="typeQueryA" cname="查询"></EF:EFButton>
                <EF:EFButton ename="typeResetA" cname="重置"></EF:EFButton>
                <EF:EFButton ename="typeSaveA" cname="确定" layout="20"></EF:EFButton>
            </div>
        </EF:EFRegion>
        <EF:EFRegion id="resultProjectType" title="合同号选择">
            <EF:EFGrid blockId="resultA" checkMode="single,row" autoDraw="no" refresh="true"
                       needAuth="true" readonly="true" rowNo="true"
                       isFloat="true" serviceName="CMFP0101" queryMethod="queryCmMsg">
                <EF:EFColumn ename="contPk" cname="合同主键" align="center" enable="false" locked="true" hidden="true"/>
                <EF:EFColumn ename="contNo" cname="合同号" align="center" enable="false" locked="true"/>
                <EF:EFColumn ename="contName" cname="合同名称" align="center" width="200" enable="false" locked="true"/>
                <EF:EFColumn ename="contTypeNum" cname="合同类型" align="center" enable="false"/>
                <EF:EFColumn ename="contSignTime" cname="签订日期" align="center" enable="false"/>
                <EF:EFColumn ename="planTakeefTime" cname="计划生效日期" align="center" enable="false"/>
                <EF:EFColumn ename="planFinishTime" cname="计划终止日期" align="center" enable="false"/>
                <EF:EFColumn ename="currType" cname="币种" align="center" enable="false"/>
                <EF:EFColumn ename="budget" cname="预算（元）" align="center" enable="false" format="{0:c2}"/>
                <EF:EFColumn ename="contTaxIncludeAmount" cname="合同含税金额（元）" align="center" enable="false" format="{0:c2}"/>
                <EF:EFColumn ename="quarPeriod" cname="质保期（月）" align="center" enable="false"/>
                <EF:EFColumn ename="contStatus" cname="合同状态" align="center" enable="false"/>
                <EF:EFColumn ename="contAdminName" cname="合同管理员" align="center" enable="false"/>
                <EF:EFColumn ename="billMakeTime" cname="制单时间" align="center" enable="false"/>
                <EF:EFColumn ename="billMaker" cname="制单人" align="center" enable="false"/>
                <EF:EFColumn ename="checkTime" cname="审批时间" align="center" enable="false"/>
                <EF:EFColumn ename="checkMaker" cname="审批人" align="center" enable="false"/>
            </EF:EFGrid>
        </EF:EFRegion>
    </div>

    <div id="ef_popup_invoiceAutoNo" style="display: none">
        <EF:EFRegion id="inqu" title="查询条件">
            <div class="row">
                <EF:EFInput ename="inqu_status-0-invoiceAutoNoWindow" cname="发票管理号"/>
                <EF:EFInput ename="inqu_status-0-invoiceNoWindow" cname="发票号"/>
                <EF:EFButton ename="typeQueryB" cname="查询"></EF:EFButton>
                <EF:EFButton ename="typeResetB" cname="重置"></EF:EFButton>
                <EF:EFButton ename="typeSaveB" cname="确定" layout="20"></EF:EFButton>
            </div>
        </EF:EFRegion>
        <EF:EFRegion id="resultFPType" title="发票号选择">
            <EF:EFGrid blockId="resultB" checkMode="single,row" autoDraw="no" refresh="true"
                       needAuth="true" readonly="true" rowNo="true"
                       isFloat="true" serviceName="CMFP0101" queryMethod="queryCmFPMsg">
                <EF:EFColumn ename="invoiceAutoNo" cname="发票管理号" align="center" enable="false" locked="true" />
                <EF:EFColumn ename="invoiceNo" cname="发票号" align="center" enable="false" locked="true" />
                <EF:EFColumn ename="contPk" cname="合同主键" align="center" enable="false" locked="true" hidden="true"/>
                <EF:EFColumn ename="contNo" cname="合同号" align="center" enable="false" />
                <EF:EFColumn ename="contName" cname="合同名称" align="center" width="200" enable="false" />
                <EF:EFColumn ename="surpName" cname="开票单位" align="center" enable="false"  />
                <EF:EFColumn ename="invoiceType" cname="发票类别" align="center" enable="false"  />
                <EF:EFColumn ename="currType" cname="币种" align="center" enable="false"  />
                <EF:EFColumn ename="invoiceTaxExcludeAmount" cname="发票金额（元）" align="center" enable="false" format="{0:c2}" />
                <EF:EFColumn ename="invoiceTaxAmount" cname="发票税额（元）" align="center" enable="false" format="{0:c2}" />
                <EF:EFColumn ename="invoiceIssuingTime" cname="发票开付时间" align="center" enable="false"  />
                <EF:EFColumn ename="remark" cname="备注" align="center" enable="false"/>
                <EF:EFColumn ename="invoiceStatus" cname="发票状态" align="center" enable="false"/>
            </EF:EFGrid>
        </EF:EFRegion>
    </div>
</EF:EFPage>