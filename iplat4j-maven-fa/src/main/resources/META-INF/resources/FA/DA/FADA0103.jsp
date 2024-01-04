<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage>
    <EF:EFRegion title="转移信息">
        <EF:EFInput ename="goodsClassifyCode" cname="资产类别编码" type="hidden"/>
        <EF:EFInput ename="goodsClassifyName" cname="资产类别" type="hidden"/>
        <EF:EFPopupInput ename="info-0-deptNum" cname="所属科室"
                         popupTitle="科室选择" readOnly="true" required="true"
                         popupType="ServiceGrid" serviceName="FADA01" methodName="queryDept" resultId="dept"
                         valueField="deptNum" textField="deptName"
                         columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="转移" ename="save" layout="3"></EF:EFButton>
            <EF:EFButton cname="关闭" ename="close" layout="3"></EF:EFButton>
        </div>
    </EF:EFRegion>
    <div title="资产信息">
        <EF:EFGrid blockId="resultFixedAssests" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
                   checkMode="multiple,row" readonly="true" rowNo="true" isFloat="true">
            <EF:EFColumn ename="faInfoId" cname="faInfoId"  align="center" locked="true" width="200" enable="false"/>
            <EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="goodsName" cname="资产名称" locked="true" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="goodsClassifyName" cname="资产类别"  align="center" width="200" enable="false"/>
            <EF:EFColumn ename="goodsTypeName" cname="资产类别名称"  align="center" width="200" enable="false"/>
            <EF:EFColumn ename="model" cname="型号规格"  align="center" width="200" enable="false"/>
            <EF:EFColumn ename="statusCode" cname="资产状态"   align="center" width="200" enable="false"/>
            <EF:EFColumn ename="rfidCode" cname="RFID"  align="center" width="200" enable="false" hidden="true"/>
            <EF:EFColumn ename="manufacturer" cname="制造厂商"  align="center" width="200" enable="false"/>
            <EF:EFColumn ename="surpName" cname="供应商"   align="center" width="200" enable="false"/>
            <EF:EFColumn ename="buyDate" cname="购入日期"   align="center" width="200" enable="false"/>
            <EF:EFColumn ename="useDate" cname="使用日期"   align="center" width="200" enable="false"/>
            <EF:EFColumn ename="deptName" cname="所属科室"   align="center" width="200" enable="false"/>
            <EF:EFColumn ename="build" cname="楼"   align="center" width="200" enable="false"/>
            <EF:EFColumn ename="floor" cname="层"   align="center" width="200" enable="false"/>
            <EF:EFColumn ename="installLocation" cname="地点"   align="center" width="200" enable="false"/>
            <EF:EFColumn ename="buyCost" cname="资产原值"   align="center" width="200" enable="false"/>
            <EF:EFColumn ename="useYears" cname="使用年限"   align="center" width="200" enable="false"/>
            <%--			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" />--%>
            <EF:EFColumn ename="recCreateName" cname="创建人"   align="center" width="200" enable="false"/>
            <EF:EFColumn ename="remark" cname="备注"  align="center" width="200" enable="false"/>
            <EF:EFColumn ename="inAccountStatus" cname="是否审批启用"  align="center" width="200" enable="false"/>
            <EF:EFColumn ename="cardStatus" cname="是否发卡"  align="center" width="200" enable="false"/>
            <EF:EFColumn ename="operationType" cname="出库类型"  align="center" width="200" enable="false"/>
            <EF:EFColumn ename="lockFlag" cname="变更状态"  align="center" hidden="true" enable="false"/>
        </EF:EFGrid>
    </div>
</EF:EFPage>