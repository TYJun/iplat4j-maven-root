<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>资产上会单</title>
<EF:EFPage title="资产上会单">
    <EF:EFRegion id="info" head="hidden">
        <div class="row">
            <EF:EFInput ename="info-0-discussId" cname="会议id" colWidth="3" required="true" type="hidden"/>
            <EF:EFInput ename="info-0-discussName" cname="会议名称" colWidth="3" required="true"/>
            <EF:EFDatePicker ename="info-0-discussDate" cname="会议时间" role="date"
                             readonly="true" colWidth="3" ratio="4:8" format="yyyy-MM-dd" />
        </div>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="确认" ename="SUBMIT" layout="3"></EF:EFButton>
            <EF:EFButton cname="取消" ename="CANCEL" layout="3"></EF:EFButton>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="上会资产列表">
        <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row" readonly="true" height="560px">
            <EF:EFColumn ename="faInfoId" cname="faInfoId" align="center" width="200" hidden="true"/>
            <EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="spec" cname="型号规格" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="deptName" cname="所属科室" align="center" width="200" enable="false"/>
            <%--				<EF:EFColumn ename="build" cname="楼"   align="center" width="200"/>--%>
            <%--				<EF:EFColumn ename="floor" cname="层"   align="center" width="200"/>--%>
            <EF:EFColumn ename="installLocation" cname="地点" align="center" width="200" enable="false" hidden="true"/>
            <EF:EFColumn ename="room" cname="具体位置" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="statusCodeMean" cname="资产状态" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="remark" cname="备注" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="goodsClassifyName" cname="资产类别" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="goodsTypeCode" cname="资产类别名称" align="center" width="200" hidden="true"/>
            <EF:EFColumn ename="goodsTypeName" cname="资产类别名称" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="manufacturer" cname="制造厂商" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="surpName" cname="供应商" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="buyDate" cname="购入日期" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="useDate" cname="使用日期" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="buyCost" cname="资产原值" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="netAssetValue" cname="资产净值" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="useYears" cname="使用年限" align="center" width="200" enable="false"/>
            <%--			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" />--%>
            <EF:EFColumn ename="recCreateName" cname="创建人" align="center" width="200" enable="false"/>
            <EF:EFColumn ename="rfidCode" cname="RFID" align="center" width="200" hidden="true"/>
            <EF:EFColumn ename="cardStatus" cname="是否发卡" align="center" width="200" hidden="true"/>
            <EF:EFColumn ename="lockFlag" cname="变更状态" align="center" hidden="true"/>
        </EF:EFGrid>
    </EF:EFRegion>
    <!-- 固资资产选择弹出窗 -->
    <EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="资产上会管理" modal="true"/>
</EF:EFPage>