<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<title>保洁登记管理</title>
<EF:EFPage title="工单登记">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="taskNo" colWidth="3" ratio="4:8" cname="工单号"/>
            <EF:EFInput ename="reqDeptName" colWidth="3" ratio="4:8" cname="来电科室"/>
            <EF:EFDatePicker ename="recCreateTimeS" cname="登记日期起" role="date" colWidth="3" ratio="4:8" format="yyyy-MM-dd"
			parseFormats="['yyyy-mm-dd']"/>
            <EF:EFDatePicker ename="recCreateTimeE" cname="登记日期止" role="date" colWidth="3" ratio="4:8" format="yyyy-MM-dd"
			parseFormats="['yyyy-mm-dd']"/>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="工单列表">
    	<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" readonly="true" checkMode="multiple,row" serviceName="CSRE01">
    	    <EF:EFColumn ename="taskId" cname="主键"  align="center" hidden="true"/>
    		<EF:EFColumn ename="taskNo" cname="工单号"  align="center" width="100" displayType="url"/>
    		<EF:EFColumn ename="statusCode" cname="工单状态" align="center" width="70" hidden="true" />
    		<EF:EFColumn ename="codeName" cname="工单状态" align="center" width="70"/>
    		<EF:EFColumn ename="reqStaffName" cname="来电人"  align="center" width="100"/>
    		<EF:EFColumn ename="reqTelNum" cname="来电号码"  align="center" width="100"/>
    		<EF:EFColumn ename="reqDeptName" cname="来电科室"  align="center" width="100"/>
    	    <EF:EFColumn ename="reqSpotName" cname="保洁地点"  align="center" width="100"/>
    	    <EF:EFColumn ename="itemName" cname="保洁事项"  align="center" width="100"/>
    	    <EF:EFColumn ename="serDeptName" cname="服务科室"  align="center" width="100"/>
    	    <EF:EFColumn ename="comments" cname="具体地址"  align="center" width="100"/>
    	    <EF:EFColumn ename="recCreateName" cname="登记人"   align="center" width="100"/>
			<EF:EFColumn ename="recCreateTime" cname="登记时间"   align="center" width="100"/>
			<EF:EFColumn ename="recReviseName" cname="修改人"   align="center" width="100"/>
			<EF:EFColumn ename="recReviseTime" cname="修改时间"   align="center" width="100"/>
    	</EF:EFGrid>
    </EF:EFRegion>
    <EF:EFWindow id="popData" url="" lazyload="true" width="80%" height="80%" title="工单登记管理" modal="true" />
    <EF:EFWindow id="popDataEdit" url="" lazyload="true" width="80%" height="80%" title="工单编辑" modal="true" />
    <EF:EFWindow id="popDatashow" url="" lazyload="true" width="80%" height="80%" title="查看工单" modal="true" />
</EF:EFPage>