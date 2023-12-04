<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!--领用申请科室审批管理-->
<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-claimId" cname="领用单ID" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-costDeptNum" cname="成本归集科室" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-costDeptName" cname="成本归集科室" readonly="true"/>
            <EF:EFInput ename="inqu_status-0-claimNo" cname="领用申请单号"/>
            <EF:EFDatePicker ename="inqu_status-0-beginTime" cname="申请日期起(>=)"></EF:EFDatePicker>
            <EF:EFDatePicker ename="inqu_status-0-endTime" cname="申请日期止(<=)"></EF:EFDatePicker>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="领用申请单列表">
        <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row" readonly="true" height="230">
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
    <EF:EFRegion id="detail" title="领用申请明细列表">
        <EF:EFGrid blockId="detail" autoDraw="no" autoBind="false" checkMode="row" height="200" readonly="true"
                   queryMethod="queryDetail">
            <EF:EFColumn ename="claimNo" cname="领用申请单号" align="center"/>
            <EF:EFColumn ename="matNum" cname="物资编码" align="center"/>
            <EF:EFColumn ename="matName" cname="物资名称" align="center"/>
            <EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true"/>
            <EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center"/>
            <EF:EFColumn ename="matSpec" cname="物资规格" align="center"/>
            <EF:EFColumn ename="matModel" cname="物资型号" align="center"/>
            <EF:EFComboColumn ename="unit" cname="计量单位" align="center" enable="false">
                <EF:EFCodeOption codeName="wilp.ac.gm.unit"/>
            </EF:EFComboColumn>
            <EF:EFColumn ename="price" cname="参考单价(元)" align="center" hidden="true"/>
            <EF:EFColumn ename="num" cname="领用数量" align="center"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="80%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-keydown.js"></script>