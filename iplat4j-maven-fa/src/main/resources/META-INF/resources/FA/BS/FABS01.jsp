<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>固定资产报损管理</title>
<EF:EFPage title="固定资产报损">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="goodsNum" colWidth="3" ratio="4:8" cname="固定资产编码"/>
            <EF:EFInput ename="goodsName" colWidth="3" ratio="4:8" cname="固定资产名称"/>
			<EF:EFSelect ename="confirmFlag" cname="申请状态" colWidth="3" ratio="4:8">
				<EF:EFOption label="请选择申请状态" value=""/>
				<EF:EFOption label="录入"  value="0"/>
				<EF:EFOption label="确认"  value="1"/>
			</EF:EFSelect>
		</div>
		<div class="row">
			<EF:EFInput ename="reimburseNo" colWidth="3" ratio="4:8" cname="报损单号"/>
			<EF:EFDateSpan startName="reimburseDateS" startCname="报损日期起"
						   endName="reimburseDateE" endCname="报损日期止"
						   ratio="3:3" startRatio="4:8" endRatio="4:8" readonly="true"/>
        </div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
		</div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="固定资产报损信息">
    	<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" readonly="true" checkMode="single,row" serviceName="FABS01">
			<EF:EFColumn ename="faReimburseId" cname="faReimburseId"  align="center" locked="true" hidden="true" enable="false"/>
			<EF:EFColumn ename="goodsId" cname="goodsId"  align="center" locked="true" hidden="true" enable="false"/>
			<EF:EFColumn ename="reimburseNo" cname="报损单号"  align="center" locked="true" enable="false" width="200"/>
    		<EF:EFColumn ename="goodsNum" cname="固资编码"  align="center" locked="true" enable="false" width="200"/>
    		<EF:EFColumn ename="goodsName" cname="固资名称" align="center" locked="true" enable="false"/>
    		<EF:EFColumn ename="goodsClassifyName" cname="固资类别"  align="center" locked="true" enable="false"/>
    		<EF:EFColumn ename="goodsTypeName" cname="类别名称"  align="center" locked="true" enable="false"/>
    		<EF:EFColumn ename="cardStatusMean" cname="是否发卡"  align="center" locked="true" enable="false"/>
			<EF:EFColumn ename="statusCodeMean" cname="固资状态"   align="center" enable="false" locked="true"/>
			<EF:EFColumn ename="rfidCode" cname="RFID"  align="center" locked="true" enable="false" hidden="true"/>
			<EF:EFColumn ename="manufacturer" cname="制造厂商"  align="center" enable="false"/>
			<EF:EFColumn ename="surpName" cname="供应商"   align="center" enable="false"/>
			<EF:EFColumn ename="buyDate" cname="购入日期"   align="center" enable="false"/>
			<EF:EFColumn ename="useDate" cname="使用日期"   align="center" enable="false"/>
			<EF:EFColumn ename="deptName" cname="所属科室"   align="center" enable="false"/>
			<EF:EFColumn ename="installLocation" cname="地点"   align="center" enable="false"/>
			<EF:EFColumn ename="buyCost" cname="资产原值"   align="center" enable="false"/>
			<EF:EFColumn ename="useYears" cname="使用年限"   align="center" enable="false"/>
			<%--			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" enable="false"/>--%>
			<EF:EFColumn ename="reimburseReason" cname="报损原因"   align="center" enable="false"/>
			<EF:EFColumn ename="reimburseDate" cname="报损日期"   align="center" enable="false"/>
			<EF:EFColumn ename="finishTime" cname="完成时间"   align="center" enable="false"/>
			<EF:EFColumn ename="confirmFlagMean" cname="申请状态"   align="center"  enable="false"/>
		</EF:EFGrid>
    </EF:EFRegion>
    <EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="固定资产报损录入" modal="true" />
</EF:EFPage>