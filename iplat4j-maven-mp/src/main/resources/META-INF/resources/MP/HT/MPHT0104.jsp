<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%-- 对接合同选择--%>
<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-deptNum" cname="当前科室编码" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-contNo" cname="合同号"/>
            <EF:EFInput ename="inqu_status-0-contName" cname="合同名称"/>
        </div>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
            <EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
        </div>
    </EF:EFRegion>

    <EF:EFRegion id="result" title="合同信息">
        <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="single,row"
                   serviceName="MPJK04" queryMethod="queryXrCont">
            <EF:EFColumn ename="id" cname="id" hidden="true" />
            <EF:EFColumn ename="contNo" cname="合同号" align="center" enable="false" />
            <EF:EFColumn ename="contName" cname="合同名称" align="center" enable="false"/>
            <EF:EFComboColumn ename="statusCode" cname="状态" align="center" enable="false">
                <EF:EFCodeOption codeName="wilp.mp.contract.Status"/>
            </EF:EFComboColumn>
            <EF:EFComboColumn ename="contClassify" cname="合同分类" align="center" enable="false">
                <EF:EFCodeOption codeName="wilp.mp.contractclassification"/>
            </EF:EFComboColumn>
            <EF:EFComboColumn ename="contType" cname="合同类型" align="center" enable="false">
                <EF:EFCodeOption codeName="wilp.mp.cont.contType"/>
            </EF:EFComboColumn>
            <EF:EFColumn ename="contCost" cname="合同金额" align="center" format="{0:0.00}" enable="false"/>
            <EF:EFColumn ename="signDate" cname="签订日期" align="center" enable="false"/>
            <EF:EFColumn ename="validDate" cname="生效日期"  align="center" enable="false"/>
            <EF:EFColumn ename="supplierName" cname="供应商" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>