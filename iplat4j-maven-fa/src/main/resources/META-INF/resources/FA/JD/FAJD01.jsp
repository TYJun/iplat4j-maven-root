<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>资产鉴定</title>
<EF:EFPage title="资产鉴定">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="transNo" colWidth="3" ratio="3:9" cname="调拨单号"/>
			<EF:EFSelect ename="transferStatusCode" cname="申请状态" colWidth="3" ratio="3:9">
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
    <EF:EFRegion id="result" title="资产鉴定信息">
    	<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" readonly="true" checkMode="single,row" serviceName="FADB01">
			<EF:EFColumn ename="faTransferId" cname="faTransferId"  align="center" locked="true" hidden="true"/>
			<EF:EFColumn ename="goodsId" cname="goodsId"  align="center" locked="true" hidden="true"/>
			<EF:EFColumn ename="transNo" cname="调拨单号"  align="center" locked="true" enable="false" width="200"/>
    		<EF:EFColumn ename="transDate" cname="调拨日期"  align="center" enable="false"/>
    	    <EF:EFColumn ename="transReason" cname="调拨原因"  align="center" enable="false"/>
			<EF:EFColumn ename="newUseDeptName" cname="新所属科室"   align="center" enable="false"/>
			<EF:EFColumn ename="newGoodsLocation" cname="新地点"   align="center" enable="false"/>
			<EF:EFColumn ename="remark" cname="备注" align="center" enable="false"/>
			<EF:EFColumn ename="transferStatusCodeMean" cname="申请状态" align="center" enable="false"/>
			<EF:EFColumn ename="archiveFlag" cname="归档标记" align="center" enable="false" hidden="true"/>
    	</EF:EFGrid>
    </EF:EFRegion>
    <EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="固定资产调拨录入" modal="true" />
    <EF:EFWindow id="popDataEdit" url="" lazyload="true" width="90%" height="90%" title="固定资产调拨编辑" modal="true" />
</EF:EFPage>