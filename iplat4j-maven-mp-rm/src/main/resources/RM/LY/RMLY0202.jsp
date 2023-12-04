<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!--仓库审批-->
<EF:EFPage title="领用审批">
    <EF:EFRegion id="inqu" title="领用审批">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-deptName" cname="领用科室" readonly="true" colWidth="5"/>
            <EF:EFInput ename="inqu_status-0-deptNum" cname="领用科室" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-applyUserName" cname="领用人" readonly="true" colWidth="5"/>
            <EF:EFInput ename="inqu_status-0-applyUserNo" cname="领用人" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-remark" cname="备注" type="textarea" colWidth="5" readonly="true"/>
            <EF:EFInput type="hidden" ename="type" cname="操作类型"/>
            <EF:EFInput type="hidden" ename="inqu_status-0-id" cname="主键"/>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="领用明细列表">
        <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row" height="365">
            <EF:EFColumn ename="matNum" cname="物资编码" align="center" enable="false"/>
            <EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false"/>
            <EF:EFColumn ename="matSpec" cname="物资规格" align="center" enable="false"/>
            <%--<EF:EFColumn ename="matModel" cname="物资型号" align="center" enable="false"/>--%>
            <EF:EFColumn ename="unit" cname="计量单位" hidden="true"/>
            <EF:EFColumn ename="unitName" cname="计量单位" align="center"/>
            <EF:EFColumn ename="price" cname="单价(元)" align="center" enable="false" hidden="true"/>
            <EF:EFColumn ename="num" cname="领用数量" align="center" enable="false"/>
            <EF:EFColumn ename="claimAmount" cname="领用金额(元)" align="center" enable="false"/>
            <EF:EFColumn ename="stockNum" cname="库存量" align="center" enable="false"/>
            <EF:EFColumn ename="reserveNum" cname="领用预约量" align="center" enable="false"/>
            <EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true"/>
            <EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="85%" height="92%"></EF:EFWindow>
<EF:EFWindow id="MatCKPopData" url=" " lazyload="true" width="80%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>