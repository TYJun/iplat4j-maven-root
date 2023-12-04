<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!--科室常用物资配置-->
<title>常用物资配置</title>
<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-deptNum" cname="科室编码" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" readonly="true"/>
            <EF:EFInput ename="inqu_status-0-matNum" cname="物资编码"/>
            <EF:EFInput ename="inqu_status-0-matName" cname="物资名称"/>
            <EF:EFInput ename="inqu_status-0-matTypeName" cname="物资分类名称"/>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="科室常用物资列表" fitHeight="true">
        <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="single,row" readonly="true">
            <EF:EFColumn ename="id" cname="id" hidden="true"/>
            <EF:EFColumn ename="pictureUri" cname="图片" align="center" width="50"/>
            <EF:EFColumn ename="matNum" cname="物资编码" align="center"/>
            <EF:EFColumn ename="matName" cname="物资名称" align="center"/>
            <EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center"/>
            <EF:EFColumn ename="matSpec" cname="物资规格" align="center"/>
            <EF:EFColumn ename="matModel" cname="物资型号" align="center"/>
            <EF:EFColumn ename="unit" cname="计量单位" hidden="true"/>
            <EF:EFColumn ename="unitName" cname="计量单位" align="center"/>
            <EF:EFColumn ename="price" cname="参考单价" align="center"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="80%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-keydown.js"></script>