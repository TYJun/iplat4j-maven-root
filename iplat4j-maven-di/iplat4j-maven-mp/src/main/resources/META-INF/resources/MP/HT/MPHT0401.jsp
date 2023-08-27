<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage>
    <EF:EFRegion id="inqu" title="合同详情" fitHeight="false">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-type" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-recCreator" cname="创建人" type="hidden"/>
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-contNo" cname="合同号" />
            <EF:EFInput ename="inqu_status-0-contName" cname="合同名称" />
            <EF:EFSelect ename="inqu_status-0-contClassify" cname="合同分类" readonly="true">
                <EF:EFCodeOption codeName="wilp.mp.contractclassification"/>
            </EF:EFSelect>
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-itemNum" cname="项目号" readonly="true"/>
            <EF:EFSelect ename="inqu_status-0-contType" cname="合同类型" readonly="true">
                <EF:EFCodeOption codeName="wilp.mp.cont.contType"/>
            </EF:EFSelect>
            <EF:EFInput ename="inqu_status-0-supplierName" cname="供应商" />
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-contCost" cname="总金额(元)" format="{0:c2}" />
            <EF:EFDatePicker ename="inqu_status-0-signDate" cname="合同签订日期" readonly="true"/>
            <EF:EFDatePicker ename="inqu_status-0-validDate" cname="合同生效日期" readonly="true"/>
        </div>
        <div class="row">
            <EF:EFDatePicker ename="inqu_status-0-overDate" cname="合同终止日期" readonly="true"/>
            <EF:EFSelect ename="inqu_status-0-currencyCode" cname="币种" readonly="true">
                <EF:EFCodeOption codeName="wilp.mp.cont.currency"/>
            </EF:EFSelect>
            <EF:EFInput ename="inqu_status-0-manageDeptName" cname="合同所属部门" />
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-managerName" cname="管理员" />
            <EF:EFSelect ename="inqu_status-0-purchaseWay" cname="采购方式" readonly="true">
                <EF:EFCodeOption codeName="wilp.mp.cont.purchasWay"/>
            </EF:EFSelect>
            <EF:EFSelect ename="inqu_status-0-payWay" cname="付款方式" readonly="true" >
                <EF:EFCodeOption codeName="wilp.mp.cont.payWay"/>
            </EF:EFSelect>
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-validLimit" cname="合同期效(年)"/>
            <EF:EFSelect ename="inqu_status-0-fundingSourceNum" cname="资金来源" readonly="true" >
                <EF:EFCodeOption codeName="wilp.mp.source"/>
            </EF:EFSelect>
            <EF:EFInput ename="inqu_status-0-remark" cname="合同描述"  type="textarea"/>
        </div>
    </EF:EFRegion>

    <!-- 页面分页 -->
    <EF:EFTab id="tab-tab_grid" active="0">
        <div title="合同明细">
            <EF:EFGrid blockId="detail" autoDraw="no" autoBind="true" checkMode="row" readonly="true">
                <EF:EFColumn ename="id" cname="id" width="100" hidden="true"  align="center"/>
                <EF:EFColumn ename="matNum" cname="物资编码" width="100"  align="center"/>
                <EF:EFColumn ename="matName" cname="物资名称" width="100"  align="center" />
                <EF:EFColumn ename="matTypeName" cname="物资分类" width="100"  align="center"/>
                <EF:EFColumn ename="matSpec" cname="物资规格" width="100"  align="center"/>
                <EF:EFColumn ename="matModel" cname="物资型号" width="100"  align="center"/>
                <EF:EFColumn ename="unitName" cname="计量单位" width="100"  align="center"/>
                <EF:EFColumn ename="price" cname="含税单价(元)" width="100"  align="center"/>
                <EF:EFColumn ename="purchaseNo" cname="采购计划单号" width="100" align="center"/>
                <EF:EFColumn ename="num" cname="采购计划数量" width="100" align="center"/>
                <EF:EFColumn ename="purchaseId" cname="采购计划ID" width="100" align="center" hidden="true"/>
                <EF:EFColumn ename="contedNum" cname="合同数量" width="100" align="center" hidden="true"/>
                <EF:EFColumn ename="contractsNum" cname="合同数量" width="100" align="center" />
                <%--<EF:EFColumn ename="surplusNum" cname="剩余可采购数量" width="100" align="center"/>
                <EF:EFColumn ename="surplusCost" cname="剩余可采购金额" width="100" align="center"/>--%>
                <EF:EFColumn ename="orderNum" cname="已生成订单数量" width="100"  align="center"/>
                <EF:EFColumn ename="orderCost" cname="已生成订单金额" width="100" align="center"/>
                <EF:EFColumn ename="enterNum" cname="已入库数量" width="100" align="center"/>
                <EF:EFColumn ename="enterCost" cname="已入库金额" width="100" align="center"/>
                <EF:EFColumn ename="billedNum" cname="已开票数量" width="100" align="center"/>
                <EF:EFColumn ename="billedCost" cname="已开票金额" width="100" align="center"/>
                <EF:EFColumn ename="payNum" cname="已付款数量" width="100"  align="center"/>
                <EF:EFColumn ename="payCost" cname="已付款金额" width="100" align="center" />

            </EF:EFGrid>
        </div>
        <div title="合同附件">
            <EF:EFGrid blockId="file" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true">
                <EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
                <EF:EFColumn ename="contId" cname="contId" width="100" hidden="true"/>
                <EF:EFColumn ename="docId" cname="附件id" width="200" hidden="true"/>
                <EF:EFColumn ename="fileName" cname="附件名称" width="200"/>
                <EF:EFColumn ename="fileSize" cname="附件大小" width="100"/>
                <EF:EFColumn ename="remark" cname="附件说明" width="200"/>
                <EF:EFColumn ename="recCreatorName" cname="上传人" width="200"/>
                <EF:EFColumn ename="recCreateTime" cname="上传时间" width="200"/>
            </EF:EFGrid>
        </div>
    </EF:EFTab>
</EF:EFPage>