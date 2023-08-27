<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="巡检卡片编辑">
	<EF:EFRegion id="sumbit">
		<div class="row" style="text-align: left">
			  <EF:EFInput ename="inqu_status-0-cardid" cname="卡片id:" colWidth="5" type="hidden"/> 
			<EF:EFInput ename="inqu_status-0-cardname" cname="卡片名称:" colWidth="5" ratio="4:8" required = "true"/>
			<EF:EFInput ename="inqu_status-0-memo" cname="备注" colWidth="5" ratio="4:8" type="textarea" required = "true" placeholder="不能超过200字"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion title="巡检项目选择">
        <EF:EFGrid blockId="result" autoDraw="no" autoBind="false" queryMethod="queryInfo" >
        	<EF:EFColumn ename="itemID" cname="itemID"  align="center" readonly="true" enable="false" hidden="true"/>
        	<EF:EFColumn ename="idd" cname="id"  align="center" readonly="true" enable="false" hidden="true"/>
        	<EF:EFColumn ename="cardid" cname="cardid"  align="center" readonly="true" enable="false" hidden="true"/>
    		<EF:EFColumn ename="content" cname="巡检项目"  align="center" readonly="true" enable="false"/>
    		<EF:EFColumn ename="referencevalue" cname="巡检项目参考值"  align="center" readonly="true" enable="false"/>
    		<EF:EFColumn ename="projectDesc" cname="项目描述"  align="center" readonly="true" enable="false"/>
    		<EF:EFColumn ename="actualvalueunit" cname="实际值单位"  align="center" readonly="true" enable="false"/>
    	</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=""  width="80%" height="80%" title="增加项目" />

