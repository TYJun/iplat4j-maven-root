<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage>
    <EF:EFRegion title="大类信息">
        <EF:EFInput ename="matNum" cname="物资编码" readOnly="true"/>
        <EF:EFInput ename="enterBillNo" cname="入库单号" readOnly="true"/>
        <EF:EFInput ename="outBillNo" cname="出库单号" readOnly="true"/>
        <EF:EFInput ename="goodsTypeName" cname="类组" readOnly="true"/>
        <EF:EFInput ename="goodsClassifyCode" cname="资产类别编码" type="hidden"/>
        <EF:EFInput ename="goodsClassifyName" cname="资产类别" readOnly="true"/>
        <EF:EFInput ename="goodsCategoryName" cname="资产末级" readOnly="true"/>
    </EF:EFRegion>
    <EF:EFRegion title="基础信息">
        <EF:EFInput ename="faInfoId" cname="faInfoId" type="hidden"/>
        <EF:EFInput ename="archiveFlag" cname="归档标记" type="hidden"/>
        <EF:EFInput ename="contNo" cname="合同号" readOnly="true" type="hidden"/>
        <EF:EFInput ename="goodsNum" cname="资产编码" readOnly="true"/>
        <EF:EFInput ename="rfidCode" cname="RFID码" readOnly="true" type="hidden"/>
        <EF:EFInput ename="purchaseDept" cname="采购科室" readOnly="true"/>
        <EF:EFInput ename="goodsName" cname="资产名称" readOnly="true"/>
        <EF:EFInput ename="model" cname="型号" readOnly="true"/>
        <EF:EFInput ename="spec" cname="规格" readOnly="true"/>
        <EF:EFSelect ename="unitNum"  cname="计量单位" >
            <EF:EFOption label="--请选择--" value=""/>
            <EF:EFCodeOption codeName="wilp.ac.gm.unit" textField="label" valueField="value"/>
        </EF:EFSelect>
        <EF:EFSelect ename="assetGetWayCode" cname="获取方式">
            <EF:EFOption label="--请选择--" value=""/>
            <EF:EFCodeOption codeName="wilp.fa.assetGetWayCode"/>
        </EF:EFSelect>
        <EF:EFSelect ename="assetUseCode" cname="资产用途">
            <EF:EFOption label="--请选择--" value=""/>
            <EF:EFCodeOption codeName="wilp.fa.assetUseCode"/>
        </EF:EFSelect>
        <EF:EFSelect ename="manufacturerNatyCode" resultId="result" cname="国别"
                     textField="label" valueField="value" required="true" filter="contains"
                     serviceName="FAQR01" methodName="queryManufacturerNatyCode">

        </EF:EFSelect>
        <EF:EFInput ename="deptName" cname="所属科室" readOnly="true"/>
        <EF:EFInput ename="manufacturer" cname="制造厂商" readOnly="true"/>
        <EF:EFInput ename="surpName" cname="供应商" readOnly="true"/>
        <EF:EFInput ename="brandDesc" cname="出厂编号" readOnly="true"/>
        <EF:EFInput ename="buyDate" cname="购入日期" readOnly="true"/>
        <EF:EFInput ename="useDate" cname="使用日期" readOnly="true"/>
        <EF:EFInput ename="build" cname="楼" readOnly="true"/>
        <EF:EFInput ename="floor" cname="层" readOnly="true"/>
        <EF:EFInput ename="installLocation" cname="地点" readOnly="true"/>
        <EF:EFInput ename="room" cname="具体位置" readOnly="true"/>
        <EF:EFInput ename="recCreateName" cname="创建人" readonly="true"/>
        <EF:EFInput ename="recCreateTime" cname="创建时间" readonly="true"/>
        <EF:EFInput ename="statusCodeMean" cname="资产状态" readOnly="true"/>
        <EF:EFInput ename="remark" cname="备注" colWidth="8" type="textarea" ratio="2:10"
                    readOnly="true" rows="3"/>
    </EF:EFRegion>
    <EF:EFRegion title="价值信息">
        <EF:EFSelect ename="deprectCode" cname="折旧方式">
            <EF:EFCodeOption codeName="wilp.fa.deprectCode"/>
        </EF:EFSelect>
        <EF:EFInput ename="invoiceDate" cname="发票日期" readOnly="true" type="hidden"/>
        <EF:EFSelect ename="fundingSourceNum" cname="资金来源">
            <EF:EFCodeOption codeName="wilp.mp.source"/>
        </EF:EFSelect>
        <EF:EFInput ename="amount" cname="数量" readOnly="true"/>
        <EF:EFInput ename="price" cname="单价(元)" readOnly="true"/>
        <EF:EFInput ename="buyCost" cname="资产原值(元)" readOnly="true"/>
        <EF:EFInput ename="equityFund" cname="自有资金(元)" readOnly="true"/>
        <EF:EFInput ename="otherFund" cname="其它资金(元)" readOnly="true"/>
        <EF:EFInput ename="estimateCost" cname="暂估价值(元)" readOnly="true"/>
        <EF:EFInput ename="useYears" cname="使用年限" readOnly="true"/>
        <EF:EFInput ename="fundProjectCode" cname="资金项目编码" readOnly="true"/>
        <EF:EFInput ename="fundProject" cname="资金项目" readOnly="true"/>
<%--        <EF:EFInput ename="residualRate" cname="残值率(%)" readOnly="true"/>--%>
<%--        <EF:EFInput ename="estimatedResidualValue" cname="预计净残值(元)" readOnly="true"/>--%>
        <EF:EFInput ename="netAssetValue" cname="资产净值(元)" readOnly="true"/>
        <EF:EFInput ename="monthDepreciation" cname="月折旧值(元)" readOnly="true"/>
        <EF:EFInput ename="totalDepreciation" cname="累计折旧值(元)" readOnly="true"/>
    </EF:EFRegion>
    <EF:EFTab id="tab-tab_grid">
        <div title="入库履历">
            <EF:EFGrid blockId="resultPutIn" needAuth="false" autoDraw="no" autoBind="false" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryPutInTabInfo" height="480px">
                <EF:EFColumn ename="id" cname="id" hidden="true" enable="false" width="100"/>
                <EF:EFColumn ename="enterBillNo" cname="入库单号" align="center" enable="false" width="100"/>
                <EF:EFColumn ename="enterTypeName" cname="入库类型名称" align="center" enable="false" width="100"/>
                <EF:EFColumn ename="enterPerson" cname="制单人" align="center" enable="false" width="100"/>
                <EF:EFColumn ename="enterTime" cname="制单时间" align="center" enable="false" width="100"/>
                <EF:EFColumn ename="wareHouseName" cname="仓库名称" align="center" enable="false" width="100"/>
            </EF:EFGrid>
        </div>
        <div title="出库履历">
            <EF:EFGrid blockId="resultPutOut" needAuth="false" autoDraw="no" autoBind="false" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryPutOutTabInfo" height="480px">
                <EF:EFColumn ename="id" cname="id" hidden="true" enable="false" width="100"/>
                <EF:EFColumn ename="outBillNo" cname="出库单号" align="center" enable="false" width="100"/>
                <EF:EFColumn ename="outTypeName" cname="出库类型名称" align="center" enable="false" width="100"/>
                <EF:EFColumn ename="outPerson" cname="制单人" align="center" enable="false" width="100"/>
                <EF:EFColumn ename="outTime" cname="制单时间" align="center" enable="false" width="100"/>
                <EF:EFColumn ename="userDeptName" cname="领用科室" align="center" enable="false" width="100"/>
            </EF:EFGrid>
        </div>
        <div title="折旧履历">
            <EF:EFGrid blockId="resultDepreciation" needAuth="false" autoDraw="no" autoBind="false" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryDepreciationTabInfo" height="480px">
                <EF:EFColumn ename="faDepreciationId" cname="faDepreciationId" hidden="true" enable="false" width="100"/>
                <EF:EFColumn ename="faInfoId" cname="faInfoId" align="center" hidden="true" enable="false" width="100"/>
                <EF:EFColumn ename="depreciationMonth" cname="折旧月份" align="center" enable="false" width="100"/>
                <EF:EFColumn ename="usedMonth" cname="已使用月份(月)" align="center" enable="false" width="100"/>
                <EF:EFColumn ename="depreciationValue" cname="本月折旧(元)" align="center" enable="false" width="100"/>
                <EF:EFColumn ename="totalDepreciation" cname="累计折旧(元)" align="center" hidden="true" enable="false" width="100"/>
                <EF:EFColumn ename="netAssetValue" cname="资产净值(元)" align="center" enable="false" width="100"/>
            </EF:EFGrid>
        </div>
        <div title="变更履历">
            <EF:EFGrid blockId="resultModification" needAuth="false" autoDraw="no" autoBind="false" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryModificationTabInfo" height="480px">
                <EF:EFColumn ename="id" cname="id"  align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="faInfoId" cname="faInfoId"  align="center" hidden="true"/>
                <EF:EFColumn ename="key" cname="编码" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="label" cname="变更字段"   align="center" width="200"/>
                <EF:EFColumn ename="previous" cname="变更前"   align="center" width="200"/>
                <EF:EFColumn ename="later" cname="变更后"  align="center" width="200"/>
                <EF:EFColumn ename="time" cname="变更时间"  align="center" width="200"/>
                <EF:EFColumn ename="recCreateName" cname="变更人"  align="center" width="200"/>
                <EF:EFColumn ename="changeReason" cname="变更原因"  align="center" width="200"/>
            </EF:EFGrid>
        </div>
        <div title="拆分履历">
            <EF:EFGrid blockId="resultSplit" needAuth="false" autoDraw="no" autoBind="false" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="querySplitTabInfo" height="480px">
                <EF:EFColumn ename="goodsId" cname="goodsId" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="goodsNum" cname="资产编码" align="center" enable="false" width="200"/>
                <EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="200"/>
                <EF:EFColumn ename="amount" cname="数量" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="buyCost" cname="资产原值" align="center" width="200"/>
                <EF:EFColumn ename="totalDepreciation" cname="累计折旧" align="center" width="200"/>
                <EF:EFColumn ename="netAssetValue" cname="资产净值" align="center" width="200"/>
            </EF:EFGrid>
        </div>
        <div title="调拨履历">
            <EF:EFGrid blockId="resultTransfer" needAuth="false" autoDraw="no" autoBind="false" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryTransferTabInfo" height="480px">
                <EF:EFColumn ename="faTransferId" cname="faTransferId" hidden="true" enable="false" width="200"/>
                <EF:EFColumn ename="transferNo" cname="调拨单号" align="center" enable="false" width="200"/>
                <EF:EFColumn ename="applyDeptName" cname="申请科室" align="center" enable="false" width="200"/>
                <EF:EFColumn ename="confirmDeptName" cname="接收科室" align="center" enable="false" width="200"/>
                <EF:EFColumn ename="confirmLocationName" cname="存放地点" align="center" enable="false" width="200"/>
                <EF:EFColumn ename="applyTime" cname="申请时间" align="center" enable="false" width="200"/>
                <EF:EFColumn ename="auditTime" cname="审批时间" align="center" enable="false" width="200"/>
                <EF:EFColumn ename="applyPerson" cname="申请人" align="center" enable="false" width="200"/>
            </EF:EFGrid>
        </div>
<%--        <div title="闲置履历">--%>
<%--            <EF:EFGrid blockId="resultIdle" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"--%>
<%--                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryIdleTabInfo">--%>
<%--                <EF:EFColumn ename="faIdleId" cname="faIdleId" hidden="true" enable="false" width="100"/>--%>
<%--                <EF:EFColumn ename="idleNo" cname="闲置单号" align="center" enable="false" width="200"/>--%>
<%--                <EF:EFColumn ename="idleReason" cname="闲置原因" align="center" enable="false" width="100"/>--%>
<%--                <EF:EFColumn ename="idleDirection" cname="闲置去向" align="center" enable="false" width="100"/>--%>
<%--                <EF:EFColumn ename="remark" cname="备注" align="center" enable="false" width="100"/>--%>
<%--                <EF:EFColumn ename="idleDate" cname="闲置日期" align="center" enable="false" width="100"/>--%>
<%--                <EF:EFColumn ename="recCreateName" cname="创建人" align="center" enable="false" width="100"/>--%>
<%--            </EF:EFGrid>--%>
<%--        </div>--%>
<%--        <div title="报损履历">--%>
<%--            <EF:EFGrid blockId="resultReimburse" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"--%>
<%--                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryReimburseTabInfo">--%>
<%--                <EF:EFColumn ename="faReimburseId" cname="faReimburseId" hidden="true" enable="false" width="100"/>--%>
<%--                <EF:EFColumn ename="reimburseNo" cname="报损单号" align="center" enable="false" width="200"/>--%>
<%--                <EF:EFColumn ename="reimburseReason" cname="报损原因" align="center" enable="false" width="100"/>--%>
<%--                <EF:EFColumn ename="remark" cname="备注" align="center" enable="false" width="100"/>--%>
<%--                <EF:EFColumn ename="reimburseDate" cname="报损日期" align="center" enable="false" width="100"/>--%>
<%--                <EF:EFColumn ename="finishTime" cname="完成时间" align="center" enable="false" width="100"/>--%>
<%--                <EF:EFColumn ename="recCreateName" cname="创建人" align="center" enable="false" width="100"/>--%>
<%--            </EF:EFGrid>--%>
<%--        </div>--%>
        <div title="报废履历">
            <EF:EFGrid blockId="resultScrap" needAuth="false" autoDraw="no" autoBind="false" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryScrapTabInfo" height="480px">
                <EF:EFColumn ename="faScrapId" cname="faScrapId" hidden="true" enable="false" width="200"/>
                <EF:EFColumn ename="scrappedNo" cname="报废单号" align="center" enable="false" width="200"/>
                <EF:EFColumn ename="applyDeptName" cname="申请科室" align="center" enable="false" width="200"/>
                <EF:EFColumn ename="applyPerson" cname="申请人" align="center" enable="false" width="200"/>
                <EF:EFColumn ename="applyReason" cname="申请理由" align="center" enable="false" width="200"/>
                <EF:EFColumn ename="applyTime" cname="申请时间" align="center" enable="false" width="200"/>
            </EF:EFGrid>
        </div>
    </EF:EFTab>
    <!-- 变更单号弹窗 -->
    <EF:EFWindow id="popData" title="变更详情" url="" lazyload="true" width="100%" height="100%"></EF:EFWindow>
</EF:EFPage>