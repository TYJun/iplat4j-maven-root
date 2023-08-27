<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%--生成合同--%>
<EF:EFPage>
    <EF:EFRegion id="result" title="登记" fitHeight="false">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-type" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-recCreateTime" cname="创建时间" colWidth="5" ratio="4:8" readonly="true"
                        type="hidden"/>
            <EF:EFInput ename="inqu_status-0-recCreator" cname="创建人" colWidth="5" ratio="4:8" readonly="true"
                        type="hidden"/>
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-contNo" cname="合同号" colWidth="4" ratio="4:8" required="true"/>
            <EF:EFInput ename="inqu_status-0-contName" cname="合同名称" colWidth="4"
                        ratio="4:8" type="text" required="true"/>
            <EF:EFInput ename="inqu_status-0-contClassify" cname="合同分类" colWidth="4"
                        ratio="4:8" type="text" required="true"/>
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-contType" cname="合同类型" colWidth="4"
                        ratio="4:8" type="text" required="true"/>

            <EF:EFPopupInput ename="inqu_status-0-supplierNum" cname="供应商"
                             popupTitle="供应商选择" popupType="ServiceGrid" serviceName="MPCG0104"
                             methodName="querySupplier" resultId="supplier" readonly="true"
                             valueField="surpNum" textField="surpName" colWidth="4"
                             ratio="4:8" columnEnames="surpNum,supplierName"
                             columnCnames="供应商编码,供应商名称" />

            <EF:EFInput ename="inqu_status-0-contCost" cname="金额(元)"
                        maxLength="10" data-regex="/^(([1-9]\d*)(\.\d{1,2})?)$|^(0\.0?([1-9]\d?))$/" data-errorprompt="请输入数字"
                        colWidth="4" ratio="4:8" format="{0:c2}" required="true"/>


        </div>
        <div class="row">
            <EF:EFDatePicker ename="inqu_status-0-signDate" cname="合同签订日期"
                             format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" readonly="true"
                             bindId="contSignTime" colWidth="4" ratio="4:8" required="true"/>

            <EF:EFDatePicker ename="inqu_status-0-validDate" cname="合同生效日期"
                             format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" readonly="true"
                             bindId="planTakeefTime" colWidth="4" ratio="4:8" required="true"/>

            <EF:EFDatePicker ename="inqu_status-0-overDate" cname="合同终止日期"
                             format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" readonly="true"
                             bindId="planFinishTime" colWidth="4" ratio="4:8" required="true"/>
        </div>
        <div class="row">
                <EF:EFSelect ename="inqu_status-0-currencyName" cname="币种" colWidth="4"
                                      ratio="4:8">
                             <EF:EFOption label="人民币" value="rmb"/>
                         </EF:EFSelect>

            <EF:EFPopupInput ename="inqu_status-0-manageDeptNum" cname="合同所属部门"
                             popupTitle="合同所属部门" popupType="ServiceGrid" serviceName="MPCG0104"
                             methodName="queryContCostNum" resultId="contDept" readonly="true"
                             valueField="manageDeptNum" textField="manageDeptName" colWidth="4"
                             ratio="4:8" columnEnames="manageDeptNum,manageDeptName"
                             columnCnames="科室编码,科室名称" required="true"/>

            <EF:EFPopupInput ename="inqu_status-0-managerNum" cname="管理员" readonly="true"
                             popupTitle="员工列表" popupType="ServiceGrid" serviceName="MPCG0104"
                             methodName="queryAdmin" resultId="contAdmin" valueField="workNo"
                             textField="name" colWidth="4" ratio="4:8"
                             columnEnames="workNo,name" columnCnames="员工工号,员工姓名" required="true"/>


        </div>
        <div class="row">

            <EF:EFSelect ename="inqu_status-0-purchaseWayName" cname="采购方式" colWidth="4"
                         ratio="4:8">
                <EF:EFOption label="方式一" value=""/>
            </EF:EFSelect>

            <EF:EFSelect ename="inqu_status-0-payWayName" cname="付款方式" colWidth="4"
                         ratio="4:8">
                <EF:EFOption label="方式一" value=""/>
            </EF:EFSelect>

            <EF:EFInput ename="inqu_status-0-validLimit" cname="合同期效(年)"
                        data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字"
                        colWidth="4" ratio="4:8" type="text"/>

        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-remark" cname="合同说明"  type="textarea" colWidth="4" ratio="4:8"/>

        </div>

        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="确定" ename="SAVEPR" layout="0"></EF:EFButton>
            <EF:EFButton cname="取消" ename="CANCEL" layout="0"></EF:EFButton>
        </div>
    </EF:EFRegion>

    <!-- 页面分页 -->
    <EF:EFTab id="tab-tab_grid" active="0">
        <div title="合同明细">
            <EF:EFGrid blockId="Details" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryMaterial">
                <EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false" align="center"/>
                <EF:EFColumn ename="matNum" cname="物资编码" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="matName" cname="物资名称" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="matTypeName" cname="物资分类" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="matSpec" cname="物资规格" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="matModel" cname="物资型号" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="unit" cname="计量单位" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="price" cname="单价(元)" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="num" cname="总量" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="contedNum" cname="已生成合同数量" width="100" enable="false" align="center" hidden="true"/>
                <EF:EFColumn ename="purchaseNo" cname="采购计划单号" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="purchaseId" cname="采购计划ID" width="100"  enable="false" align="center" hidden="true"/>
            </EF:EFGrid>
        </div>
        <div title="合同附件">
            <EF:EFGrid blockId="Fail" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true"  queryMethod="queryFile">
                <EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
                <EF:EFColumn ename="contId" cname="contId" width="100" hidden="true"/>
                <EF:EFColumn ename="docId" cname="附件id" width="200" hidden="true"/>
                <EF:EFColumn ename="filePath" cname="附件路径" width="200" hidden="true"/>
                <EF:EFColumn ename="fileName" cname="附件名称" width="200"/>
                <EF:EFColumn ename="fileSize" cname="附件大小" width="100"/>
                <EF:EFColumn ename="remark" cname="附件说明" width="200"/>
                <EF:EFColumn ename="recCreator" cname="上传人" width="200"/>
                <EF:EFColumn ename="recCreateTime" cname="上传时间" width="200"/>
            </EF:EFGrid>
        </div>

    </EF:EFTab>
    <!-- 附件上传窗口 -->
    <EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%" title="附件上传">
        <EF:EFRegion id="upload" title="文件上传">
            <EF:EFUpload ename="contentFile" docTag="co_file" path="Content"/>
        </EF:EFRegion>
    </EF:EFWindow>
</EF:EFPage>

<script type="text/javascript">

</script>