<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>固定资产闲置管理</title>
<EF:EFPage title="固定资产闲置">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="goodsNum" colWidth="3" ratio="3:9" cname="固资编码"/>
            <EF:EFInput ename="goodsName" colWidth="3" ratio="3:9" cname="固资名称"/>
			<EF:EFSelect ename="idleStatusCode" cname="申请状态" colWidth="3" ratio="3:9">
				<EF:EFOption label="请选择申请状态" value=""/>
				<EF:EFOption label="录入"  value="0"/>
				<EF:EFOption label="确认"  value="1"/>
			</EF:EFSelect>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
		</div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="固定资产闲置信息">
    	<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" readonly="true" checkMode="single,row" serviceName="FAXZ01">
			<EF:EFColumn ename="faIdleId" cname="faIdleId"  align="center" locked="true" hidden="true"/>
			<EF:EFColumn ename="goodsId" cname="goodsId"  align="center" locked="true" hidden="true"/>
			<EF:EFColumn ename="idleNo" cname="闲置单号"  align="center" locked="true" width="200"/>
    		<EF:EFColumn ename="goodsNum" cname="固资编码"  align="center" locked="true" width="200"/>
    		<EF:EFColumn ename="goodsName" cname="固资名称" align="center" locked="true"/>
    		<EF:EFColumn ename="model" cname="型号规格"  align="center" locked="true"/>
    		<EF:EFColumn ename="manufacturer" cname="制造厂商"  align="center" locked="true"/>
    		<EF:EFColumn ename="buyCost" cname="资产原值"  align="center" locked="true"/>
			<EF:EFColumn ename="useYears" cname="使用年限"   align="center" locked="true"/>
			<EF:EFColumn ename="statusCodeMean" cname="申请状态" align="center" locked="true"/>
    		<EF:EFColumn ename="buyDate" cname="购入日期"  align="center" />
    	    <EF:EFColumn ename="useDate" cname="使用日期"  align="center"/>
			<EF:EFColumn ename="deptName" cname="所属科室"   align="center" />
			<EF:EFColumn ename="installLocation" cname="地点"   align="center" />
			<EF:EFColumn ename="idleReason" cname="闲置原因"   align="center" />
			<EF:EFColumn ename="idleDirection" cname="闲置去向"   align="center" />
			<EF:EFColumn ename="idleDate" cname="闲置日期"   align="center" />
			<EF:EFColumn ename="remark" cname="备注"   align="center" />
    	</EF:EFGrid>
    </EF:EFRegion>
    <EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="固定资产闲置录入/编辑" modal="true" />
</EF:EFPage>