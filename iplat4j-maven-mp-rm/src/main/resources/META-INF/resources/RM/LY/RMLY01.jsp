<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!--领用申请管理-->
<title>领用申请</title>
<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" readonly="true"/>
            <EF:EFInput ename="inqu_status-0-deptNum" cname="科室编码" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-claimNo" cname="领用申请单号"/>
            <EF:EFSelect ename="inqu_status-0-statusCode" cname="状态">
                <EF:EFOption label="全部" value=""/>
                <EF:EFCodeOption codeName="wilp.rm.claim.status"/>
            </EF:EFSelect>
            <EF:EFDatePicker ename="inqu_status-0-beginTime" cname="申请日期起(>=)"></EF:EFDatePicker>
            <EF:EFDatePicker ename="inqu_status-0-endTime" cname="申请日期止(<=)"></EF:EFDatePicker>
            <EF:EFInput ename="inqu_status-0-recCreatorName" cname="申领人"/>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="领用申请单列表">
        <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="single,row" readonly="true">
            <EF:EFColumn ename="id" cname="id" hidden="true"/>
            <EF:EFColumn ename="claimNo" cname="领用申请单号" align="center" displayType="url"/>
            <EF:EFColumn ename="statusName" cname="状态" align="center"/>
            <EF:EFColumn ename="claimNum" cname="领用总量" align="center"/>
            <EF:EFColumn ename="deptName" cname="申领科室" align="center"/>
            <EF:EFColumn ename="costDeptName" cname="成本归集科室" align="center"/>
            <EF:EFColumn ename="remark" cname="备注" align="center"/>
            <EF:EFColumn ename="recCreateTimeStr" cname="申请时间" align="center"/>
            <EF:EFColumn ename="applyUserName" cname="申领人" align="center"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="90%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-keydown.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-yxSign.js"></script>