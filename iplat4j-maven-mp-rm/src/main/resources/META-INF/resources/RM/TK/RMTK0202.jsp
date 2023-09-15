<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!--退库出库信息-->
<EF:EFPage title="退库信息">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-backOutNo" cname="退库申请单号" readonly="true"/>
            <EF:EFInput ename="inqu_status-0-deptName" cname="退库科室" readonly="true"/>
            <EF:EFInput ename="inqu_status-0-deptNum" cname="退库科室" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-costDeptName" cname="成本科室名称" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-costDeptNum" cname="成本科室编码" type="hidden"/>
            <EF:EFInput ename="type" cname="操作" type="hidden"/>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="退库明细列表">
        <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row">
            <EF:EFColumn ename="claimNo" cname="领用单号" hidden="true"/>
            <EF:EFColumn ename="matNum" cname="物资编码" align="center" enable="false"/>
            <EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false"/>
            <EF:EFColumn ename="matSpec" cname="物资规格" align="center" enable="false"/>
            <%--<EF:EFColumn ename="matModel" cname="物资型号" align="center" enable="false"/>--%>
            <EF:EFColumn ename="unit" cname="计量单位" hidden="true"/>
            <EF:EFColumn ename="unitName" cname="计量单位" align="center" enable="false" width="80"/>
            <EF:EFColumn ename="price" cname="参考单价(元)" align="center" enable="false" width="100"/>
            <EF:EFColumn ename="num" cname="退库数量" align="center" enable="false" width="100"/>
            <EF:EFColumn ename="outNum" cname="已退库数量" align="center" enable="false" width="100"/>
            <EF:EFColumn ename="curOutNum" cname="本次退库数量" align="center"/>
            <EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true"/>
            <EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>