<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>资产上会</title>
<EF:EFPage title="资产上会">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <div id="discuss" style="display: none">
                <EF:EFInput ename="inqu_status-0-discussNo" cname="会议单号" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-discussName" cname="会议名称" colWidth="3"/>
                <EF:EFDatePicker ename="inqu_status-0-discussDate" cname="会议时间" role="date"
                                 readonly="true" colWidth="3" ratio="4:8" format="yyyy-MM-dd" />
            </div>
            <div id="one">
                <EF:EFInput ename="inqu_status-0-goodsNum" cname="资产编码" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-goodsName" cname="资产名称" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-surpName" cname="供应商" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-remark" cname="备注" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-buyCostS" colWidth="3" ratio="4:8" cname="原值范围起"/>
                <EF:EFInput ename="inqu_status-0-buyCostE" colWidth="3" ratio="4:8" cname="原值范围止"/>
                <EF:EFInput ename="inqu_status-0-netAssetValueS" colWidth="3" ratio="4:8" cname="净值范围起"/>
                <EF:EFInput ename="inqu_status-0-netAssetValueE" colWidth="3" ratio="4:8" cname="净值范围止"/>
                <EF:EFDateSpan startName="inqu_status-0-buyDateS" startCname="购入日期起"
                               endName="inqu_status-0-buyDateE" endCname="购入日期止"
                               ratio="3:3" startRatio="4:8" endRatio="4:8" readonly="true"/>
                <EF:EFDateSpan startName="inqu_status-0-useDateS" startCname="使用日期起"
                               endName="inqu_status-0-useDateE" endCname="使用日期止"
                               ratio="3:3" startRatio="4:8" endRatio="4:8" readonly="true"/>
                <EF:EFMultiSelect ename="inqu_status-0-deptName" cname="所属科室" colWidth="3" ratio="4:8" filter="contains">
                    <EF:EFOptions blockId="dept" textField="deptName" valueField="deptName"/>
                </EF:EFMultiSelect>
                <EF:EFInput ename="inqu_status-0-goodsClassifyCode" colWidth="3" ratio="4:8" cname="资产类别"/>
                <EF:EFInput ename="inqu_status-0-goodsTypeCode" colWidth="3" ratio="4:8" cname="类组"/>
                <EF:EFInput ename="inqu_status-0-spec" colWidth="3" ratio="4:8" cname="型号规格"/>
            </div>
        </div>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
            <EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
        </div>
    </EF:EFRegion>
    <EF:EFTab id="FaDaTab">
        <div title="报废提交资产">
            <EF:EFGrid blockId="resultA" autoDraw="no" autoBind="true" rowNo="true" readonly="true"
                       checkMode="multiple,row" queryMethod="confirmedQuery" height="418px" sort="setted">
                <EF:EFColumn ename="faInfoId" cname="faInfoId" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="120" sort="true"/>
                <EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="180" sort="true"/>
                <EF:EFColumn ename="spec" cname="型号规格" align="center" width="150" sort="true"/>
                <EF:EFColumn ename="deptName" cname="所属科室" align="center" width="150" sort="true"/>
                <EF:EFColumn ename="buyCost" cname="资产原值" align="center" width="130" sort="true"/>
                <EF:EFColumn ename="netAssetValue" cname="资产净值" align="center" width="130" sort="true"/>
                <EF:EFColumn ename="useDate" cname="使用日期" align="center" width="130" sort="true"/>
                <EF:EFColumn ename="room" cname="具体位置" align="center" width="150" sort="true"/>
                <EF:EFColumn ename="remark" cname="备注" align="center" width="150" sort="true"/>
                <EF:EFColumn ename="outRemark" cname="出库备注" align="center" width="150" sort="true"/>
                <EF:EFColumn ename="statusCodeMean" cname="资产状态" align="center" width="200" sort="true"/>
                <EF:EFColumn ename="goodsClassifyName" cname="资产类别" align="center" width="200"/>
                <EF:EFColumn ename="goodsTypeCode" cname="类组" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="goodsTypeName" cname="类组" align="center" width="200"/>
                <EF:EFColumn ename="manufacturer" cname="制造厂商" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="surpName" cname="供应商" align="center" width="200"/>
                <EF:EFColumn ename="buyDate" cname="购入日期" align="center" width="200"/>
                <EF:EFColumn ename="useYears" cname="使用年限" align="center" width="200"/>
                <%--			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" />--%>
                <EF:EFColumn ename="recCreateName" cname="创建人" align="center" width="200"/>
                <EF:EFColumn ename="installLocation" cname="地点" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="rfidCode" cname="RFID" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="cardStatus" cname="是否发卡" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="lockFlag" cname="变更状态" align="center" hidden="true"/>
            </EF:EFGrid>
        </div>
        <div title="上会讨论资产">
            <EF:EFGrid blockId="resultB" autoDraw="no" autoBind="true" rowNo="true" readonly="true"
                       checkMode="multiple,row" queryMethod="discussQuery" height="500px">
                <EF:EFColumn ename="discussId" cname="id" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="discussNo" cname="会议单号" align="center" width="200" displayType="url" enable="false"/>
                <EF:EFColumn ename="discussName" cname="会议名称" align="center" width="200" enable="false"/>
                <EF:EFColumn ename="discussDate" cname="会议时间" align="center" width="200" enable="false"/>
            </EF:EFGrid>
        </div>
        <div title="预报废资产">
            <EF:EFGrid blockId="resultC" autoDraw="no" autoBind="true" rowNo="true" readonly="true"
                       checkMode="multiple,row" queryMethod="wastingQuery" height="500px">
                <EF:EFColumn ename="discussId" cname="id" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="discussNo" cname="会议单号" align="center" width="200" displayType="url" enable="false"/>
                <EF:EFColumn ename="discussName" cname="会议名称" align="center" width="200" enable="false"/>
                <EF:EFColumn ename="discussDate" cname="会议时间" align="center" width="200" enable="false"/>
            </EF:EFGrid>
        </div>
        <div title="已销账资产">
            <EF:EFGrid blockId="resultD" autoDraw="no" autoBind="true" rowNo="true" readonly="true"
                       checkMode="multiple,row" queryMethod="wastedQuery" height="418px">
                <EF:EFColumn ename="faInfoId" cname="faInfoId" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="200"/>
                <EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="200"/>
                <EF:EFColumn ename="spec" cname="型号规格" align="center" width="200"/>
                <EF:EFColumn ename="discussNo" cname="会议单号" align="center" width="200" enable="false"/>
                <EF:EFColumn ename="discussName" cname="会议名称" align="center" width="200" enable="false"/>
                <EF:EFColumn ename="discussDate" cname="会议时间" align="center" width="200" enable="false"/>
                <EF:EFColumn ename="statusCodeMean" cname="资产状态" align="center" width="200"/>
                <EF:EFColumn ename="remark" cname="备注" align="center" width="200"/>
                <EF:EFColumn ename="deptName" cname="所属科室" align="center" width="200"/>
                <%--				<EF:EFColumn ename="build" cname="楼"   align="center" width="200"/>--%>
                <%--				<EF:EFColumn ename="floor" cname="层"   align="center" width="200"/>--%>
                <EF:EFColumn ename="room" cname="具体位置" align="center" width="200"/>
                <EF:EFColumn ename="goodsClassifyName" cname="资产类别" align="center" width="200"/>
                <EF:EFColumn ename="goodsTypeCode" cname="类组" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="goodsTypeName" cname="类组" align="center" width="200"/>
                <EF:EFColumn ename="manufacturer" cname="制造厂商" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="surpName" cname="供应商" align="center" width="200"/>
                <EF:EFColumn ename="buyDate" cname="购入日期" align="center" width="200"/>
                <EF:EFColumn ename="useDate" cname="使用日期" align="center" width="200"/>
                <EF:EFColumn ename="buyCost" cname="资产原值" align="center" width="200"/>
                <EF:EFColumn ename="netAssetValue" cname="资产净值" align="center" width="200"/>
                <EF:EFColumn ename="useYears" cname="使用年限" align="center" width="200"/>
                <%--			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" />--%>
                <EF:EFColumn ename="recCreateName" cname="创建人" align="center" width="200"/>
                <EF:EFColumn ename="rfidCode" cname="RFID" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="cardStatus" cname="是否发卡" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="lockFlag" cname="变更状态" align="center" hidden="true"/>
            </EF:EFGrid>
        </div>
<%--        <div title="完结上会单" style="display:none;">--%>
<%--            <EF:EFGrid blockId="resultE" autoDraw="no" autoBind="true" rowNo="true" readonly="true"--%>
<%--                       checkMode="multiple,row" queryMethod="FinsihQuery" height="500px">--%>
<%--                <EF:EFColumn ename="discussId" cname="id" align="center" locked="true" hidden="true"/>--%>
<%--                <EF:EFColumn ename="discussNo" cname="会议单号" align="center" width="200" enable="false"/>--%>
<%--                <EF:EFColumn ename="discussName" cname="会议名称" align="center" width="200" enable="false"/>--%>
<%--                <EF:EFColumn ename="discussDate" cname="会议时间" align="center" width="200" enable="false"/>--%>
<%--            </EF:EFGrid>--%>
<%--        </div>--%>
    </EF:EFTab>
    <!-- 固资资产选择弹出窗 -->
    <EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="资产上会管理" modal="true"/>
</EF:EFPage>