<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="发票信息">
    <EF:EFRegion id="inqu" title="发票信息" fitHeight="false">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-invoiceNo" cname="发票号" required="true"/>
            <EF:EFSelect ename="inqu_status-0-contId" cname="合同" required="true"
                         resultId="cont" textField="text" valueField="label"
                         serviceName="MPFP0101" methodName="queryCont" optionLabel="请选择"
                         filter="contains">
            </EF:EFSelect>
            <EF:EFSelect ename="inqu_status-0-invoiceType" cname="发票类型" optionLabel="请选择" required="true">
                <EF:EFCodeOption codeName="wilp.mp.cont.invoiceType"/>
            </EF:EFSelect>
        </div>
        <div class="row">
            <EF:EFDatePicker ename="inqu_status-0-payDate" cname="开付日期" readonly="true" required="true"/>
           <%-- <EF:EFSelect ename="inqu_status-0-supplierNum" cname=" 开票单位" required="true"
                 resultId="supplier" textField="supplierName" valueField="supplierNum"
                 serviceName="MPTY01" methodName="selectSupplier" optionLabel="请选择" filter="contains">
            </EF:EFSelect>--%>
            <EF:EFInput ename="inqu_status-0-supplierName" cname="开票单位" required="true" readonly ="true"/>
            <EF:EFInput ename="inqu_status-0-supplierNum" cname="开票单位" type="hidden"/>
            <EF:EFSelect ename="inqu_status-0-currencyCode" cname="币种" optionLabel="请选择">
                 <EF:EFCodeOption codeName="wilp.mp.cont.currency"/>
            </EF:EFSelect>
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-invoiceCost" cname="发票含税金额(元)"  maxLength="10" required="true"
                        data-rules="non_negative_number" data-errorprompt="请输入数字" />
           <%-- <EF:EFInput ename="inqu_status-0-invoiceTaxCost" cname="发票税额(元)" maxLength="10"
                        data-rules="non_negative_number" data-errorprompt="请输入数字"/>--%>
            <EF:EFInput ename="inqu_status-0-remark" cname="备注"  type="textarea" />
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-contNo" cname="合同号" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-contName" cname="合同名称" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-invoiceTypeName" cname="发票类型" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-currencyCodeName" cname="币种名称" type="hidden"/>
            <EF:EFInput ename="type" cname="操作类型" type="hidden"/>
        </div>
    </EF:EFRegion>

    <EF:EFRegion id="result" title="发票明细列表">
        <EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" checkMode="row" readonly="true">
            <EF:EFColumn ename="orderId" cname="采购订单id" hidden="true" />
            <EF:EFColumn ename="contNo" cname="合同号" hidden="true"/>
            <EF:EFColumn ename="orderNo" cname="订单号" width="100" enable="false" align="center"/>
            <EF:EFColumn ename="matNum" cname="物资编码" width="80" enable="false" align="center"/>
            <EF:EFColumn ename="matName" cname="物资名称" width="80" enable="false" align="center"/>
            <EF:EFColumn ename="matTypeId" cname="物资分类编码" width="80" hidden="true"/>
            <EF:EFColumn ename="matTypeName" cname="物资分类" width="80" enable="false" align="center"/>
            <EF:EFColumn ename="matSpec" cname="物资规格" width="80" enable="false" align="center"/>
            <EF:EFColumn ename="matModel" cname="物资型号" width="80" enable="false" align="center"/>
            <EF:EFColumn ename="unit" cname="计量单位" hidden="true"/>
            <EF:EFColumn ename="unitName" cname="计量单位" width="70" enable="false" align="center"/>
            <EF:EFColumn ename="price" cname="含税单价(元)" width="80" enable="false" align="center"/>
            <EF:EFColumn ename="num" cname="订单数量" width="80" enable="false" align="center"/>
            <EF:EFColumn ename="orderCost" cname="订单总价" width="80" enable="false" align="center"/>
            <EF:EFColumn ename="pictureUri" cname="图片" hidden="true"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<!-- 采购计划明细选择弹窗 -->
<EF:EFWindow id="popData" url="" lazyload="true" width="90%" height="95%" title="采购订单明细选择"/>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>