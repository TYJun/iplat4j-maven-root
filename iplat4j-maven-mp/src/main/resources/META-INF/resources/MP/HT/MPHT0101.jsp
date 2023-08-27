<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%--合同登记页面--%>
<EF:EFPage>
    <EF:EFRegion id="inqu" title="登记" fitHeight="false">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-dockContId" cname="对接合同ID" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-recCreateTime" cname="创建时间" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-recCreator" cname="创建人" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-recCreatorName" cname="创建人" type="hidden" />
            <EF:EFInput ename="type" cname="类别" type="hidden"/>
        </div>

        <div class="row">
            <div class="col-md-4">
                <div class="form-group">
                    <label for="inqu_status-0-contNo" class="col-xs-4 control-label">
                        <span class="i-input-required">*</span>合同号</label>
                    <div class="col-xs-8">
                        <input id="inqu_status-0-contNo" name="inqu_status-0-contNo" type="text"
                               required="required" validate="true" class="k-input" style="width:80%">
                        <EF:EFButton cname="选择" ename="selectCont" layout="1"></EF:EFButton>
                    </div>
                </div>
            </div>
            <EF:EFInput ename="inqu_status-0-contName" cname="合同名称" required="true"/>
            <EF:EFSelect ename="inqu_status-0-contClassify" cname="合同分类" required="true" readonly="true">
                <EF:EFCodeOption codeName="wilp.mp.contractclassification"/>
            </EF:EFSelect>
        </div>

        <div class="row">
            <EF:EFInput ename="inqu_status-0-itemNum" cname="项目号" required="true"/>
            <EF:EFSelect ename="inqu_status-0-contType" cname="合同类型" required="true">
                <EF:EFCodeOption codeName="wilp.mp.cont.contType"/>
            </EF:EFSelect>
            <EF:EFPopupInput ename="inqu_status-0-supplierNum" cname="供应商" popupTitle="供应商选择" readonly="true" required="true"
                             popupType="ServiceGrid" resultId="supplier" serviceName="MPCG0104" methodName="querySupplier"
                             valueField="surpNum" textField="surpName"  columnEnames="surpNum,supplierName"
                             columnCnames="供应商编码,供应商名称" />

        </div>

        <div class="row">
            <EF:EFInput ename="inqu_status-0-contCost" cname="金额(元)" required="true" maxLength="10"
                        data-regex="/^(([1-9]\d*)(\.\d{1,2})?)$|^(0\.0?([1-9]\d?))$/" data-errorprompt="请输入数字" />
            <EF:EFDatePicker ename="inqu_status-0-signDate" cname="合同签订日期" readonly="true" required="true"/>
            <EF:EFDatePicker ename="inqu_status-0-validDate" cname="合同生效日期" readonly="true" required="true"/>
            <EF:EFDatePicker ename="inqu_status-0-overDate" cname="合同终止日期" readonly="true" required="true"/>

            <EF:EFSelect ename="inqu_status-0-currencyCode" cname="币种" optionLabel="请选择">
                <EF:EFCodeOption codeName="wilp.mp.cont.currency"/>
            </EF:EFSelect>
            <EF:EFPopupInput ename="inqu_status-0-manageDeptNum" cname="合同所属部门" popupTitle="合同所属部门" readonly="true"
                             popupType="ServiceGrid" resultId="contDept" serviceName="MPCG0104"  methodName="queryContCostNum"
                             valueField="manageDeptNum" textField="manageDeptName"  columnEnames="manageDeptNum,manageDeptName"
                             columnCnames="科室编码,科室名称" required="true"/>
            <EF:EFPopupInput ename="inqu_status-0-managerNum" cname="管理员" readonly="true" required="true" popupTitle="员工列表"
                             resultId="contAdmin" popupType="ServiceGrid" serviceName="MPCG0104" methodName="queryAdmin"
                             valueField="workNo" textField="name" columnEnames="workNo,name" columnCnames="员工工号,员工姓名" />

            <EF:EFSelect ename="inqu_status-0-purchaseWay" cname="采购方式" optionLabel="请选择">
                <EF:EFCodeOption codeName="wilp.mp.cont.purchasWay"/>
            </EF:EFSelect>
            <EF:EFSelect ename="inqu_status-0-payWay" cname="付款方式" optionLabel="请选择" >
                <EF:EFCodeOption codeName="wilp.mp.cont.payWay"/>
            </EF:EFSelect>
            <EF:EFInput ename="inqu_status-0-validLimit" cname="合同期效(年)"
                        data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字" type="text"/>

            <EF:EFSelect ename="inqu_status-0-fundingSourceNum" cname="资金来源" optionLabel="请选择">
                <EF:EFCodeOption codeName="wilp.mp.source"/>
            </EF:EFSelect>
            <EF:EFInput ename="inqu_status-0-remark" cname="合同描述" type="textarea" placeholder="不能超过200字"/>
        </div>

        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="保存" ename="SAVE_CONT" layout="0"></EF:EFButton>
            <EF:EFButton cname="取消" ename="CANCEL" layout="0"></EF:EFButton>
        </div>
    </EF:EFRegion>

    <!-- 页面分页 -->
    <EF:EFTab id="tab-tab_grid" active="0">
        <div title="合同明细">
            <EF:EFGrid blockId="detail" autoDraw="no" autoBind="true" checkMode="row">
                <EF:EFColumn ename="matNum" cname="物资编码" enable="false" align="center"/>
                <EF:EFColumn ename="matName" cname="物资名称" enable="false" align="center"/>
                <EF:EFColumn ename="matTypeId" cname="物资分类编码" hidden="true"/>
                <EF:EFColumn ename="matTypeName" cname="物资分类" enable="false" align="center"/>
                <EF:EFColumn ename="matSpec" cname="物资规格" enable="false" align="center"/>
                <EF:EFColumn ename="matModel" cname="物资型号" enable="false" align="center"/>
                <EF:EFColumn ename="unit" cname="计量单位" hidden="true"/>
                <EF:EFColumn ename="unitName" cname="计量单位" width="70" enable="false" align="center"/>
                <EF:EFColumn ename="purchaseNum" cname="采购计划数量" width="100" align="center" enable="false"/>
                <EF:EFColumn ename="contedNum" cname="已生成合同数量" width="110" align="center" enable="false"/>
                <EF:EFColumn ename="num" cname="本次合同数量" align="center" />
                <EF:EFColumn ename="price" cname="含税单价(元)" width="90" align="center"/>
                <EF:EFColumn ename="purchaseNo" cname="采购计划单号" align="center" enable="false"/>
                <EF:EFColumn ename="purchasePlanId" cname="采购计划ID"  hidden="true"/>
                <EF:EFColumn ename="pictureUri" cname="图片" hidden="true"/>
            </EF:EFGrid>
        </div>
        <div title="合同附件">
            <EF:EFGrid blockId="file" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
                       checkMode="row" readonly="true" rowNo="true" queryMethod="queryFile">
                <EF:EFColumn ename="docId" cname="附件id" width="200" hidden="true"/>
                <EF:EFColumn ename="fileName" cname="附件名称" width="200" align="center"/>
                <EF:EFColumn ename="fileSize" cname="附件大小" width="100" align="center"/>
                <EF:EFColumn ename="remark" cname="附件说明" width="200" align="center"/>
                <EF:EFColumn ename="recCreator" cname="上传人" hidden="true"/>
                <EF:EFColumn ename="recCreatorName" cname="上传人" width="200" align="center"/>
                <EF:EFColumn ename="recCreateTimeStr" cname="上传时间" width="200" align="center"/>
            </EF:EFGrid>
        </div>
    </EF:EFTab>

    <!-- 附件上传窗口 -->
    <EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%" title="附件上传">
        <EF:EFRegion id="upload" title="文件上传">
            <EF:EFUpload ename="contentFile" docTag="mp_cont_file" path="Content"/>
        </EF:EFRegion>
    </EF:EFWindow>
    <!-- 采购计划选择弹窗 -->
    <EF:EFWindow id="popData" url="" lazyload="true" width="95%" height="90%" title="采购明细选择"/>
    <!-- 对接合同选择弹窗 -->
    <EF:EFWindow id="contChoose" url="" lazyload="true" width="90%" height="80%" title="合同选择"/>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>
<script type="text/javascript" src="${ctx}/MP/common/mp-upload.js"></script>
