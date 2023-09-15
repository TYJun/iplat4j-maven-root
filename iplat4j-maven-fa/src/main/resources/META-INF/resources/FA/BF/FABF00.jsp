<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="资产报废鉴定科室选择">
    <div class="row">
        <div class="row">
            <EF:EFRegion id="info" head="hidden">
                <EF:EFInput ename="info-0-id" cname="鉴定表id" rows="3" colWidth="12"
                            ratio="2:9" readonly="true" type="hidden"/>
                <EF:EFInput ename="info-0-parentId" cname="归口科室id" rows="3" colWidth="12"
                            ratio="2:9" readonly="true" type="hidden"/>
                <EF:EFInput ename="info-0-identifyDeptNum" cname="鉴定科室编码" rows="3" colWidth="12"
                            ratio="2:9" readonly="true" type="hidden"/>
                <EF:EFInput ename="info-0-identifyDeptName" cname="鉴定科室" rows="3" colWidth="12"
                            ratio="2:9" readonly="true" required="true"/>
                <div class="button-region" id="buttonDiv">
                    <EF:EFButton cname="提交" ename="SUMBIT" layout="3"></EF:EFButton>
                    <EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
                </div>
            </EF:EFRegion>
        </div>
        <div class="col-md-10" style="height: 520px">
            <EF:EFRegion id="result" title="资产信息" head="hidden">
                <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" readonly="true" rowNo="true" checkMode="multiple,row">
                    <EF:EFColumn ename="faInfoId" cname="faInfoId"  align="center" width="200" hidden="true"/>
                    <EF:EFColumn ename="scrappedNo" cname="报废单号"  align="center" width="200" enable="false" hidden="true"/>
                    <EF:EFColumn ename="detailId" cname="detailId"  align="center" width="200" enable="false" hidden="true"/>
                    <EF:EFColumn ename="goodsNum" cname="资产编码"  align="center" width="200" enable="false" displayType="url"/>
                    <EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="200" enable="false"/>
                    <EF:EFColumn ename="spec" cname="型号规格" align="center" width="200" enable="false"/>
                    <EF:EFColumn ename="applyDeptName" cname="申请科室" align="center" width="200" enable="false"/>
                    <EF:EFColumn ename="scrapStatus" cname="审批状态" align="center" width="200" enable="false"/>
                    <EF:EFColumn ename="applyReason" cname="申请理由" align="center" width="200" enable="false"/>
                    <EF:EFColumn ename="applyTime" cname="申请时间" align="center" width="200" enable="false"/>
                    <EF:EFColumn ename="goodsClassifyCode" cname="类别编码" align="center" width="200" enable="false" hidden="true"/>
                    <EF:EFColumn ename="goodsClassifyName" cname="资产类别" align="center" width="200" enable="false"/>
                    <EF:EFColumn ename="applyPerson" cname="申请人"  align="center" width="200" enable="false"/>
                </EF:EFGrid>
            </EF:EFRegion>
        </div>
        <div class="col-md-2">
            <EF:EFRegion id="deptNameMenu" title="鉴定科室选择树">
                <EF:EFTree id="menu" valueField="id" textField="deptName" hasChildren="isLeaf"
                           serviceName="FAZN01" methodName="queryFaDeptTree" style="height:520px;"/>
            </EF:EFRegion>
        </div>
    </div>
</EF:EFPage>