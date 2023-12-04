<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!--需求计划审批管理-->
<EF:EFPage title="需求计划审批管理">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" readonly="true"/>
            <EF:EFInput ename="inqu_status-0-deptNum" cname="科室编码" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-planId" cname="需求计划ID" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-planNo" cname="需求计划单号"/>
            <EF:EFSelect ename="inqu_status-0-statusCode" cname="需求计划类型">
                <EF:EFOption label="全部" value=""/>
                <EF:EFCodeOption codeName="wilp.rm.require.planType"/>
            </EF:EFSelect>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="需求计划列表">
        <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row" readonly="true" height="250">
            <EF:EFColumn ename="id" cname="id" hidden="true"/>
            <EF:EFColumn ename="planNo" cname="需求计划单号" align="center" displayType="url"/>
            <EF:EFColumn ename="planTypeName" cname="计划类型" align="center"/>
            <EF:EFColumn ename="planTime" cname="计划时间" align="center"/>
            <EF:EFColumn ename="deptName" cname="科室名称" align="center"/>
            <EF:EFColumn ename="planNum" cname="需求总量" align="center"/>
            <EF:EFColumn ename="planCost" cname="需求总价(元)" align="center"/>
            <EF:EFColumn ename="statusName" cname="状态" align="center"/>
            <EF:EFColumn ename="remark" cname="备注" align="center"/>
        </EF:EFGrid>
    </EF:EFRegion>

    <EF:EFRegion id="detail" title="需求计划明细列表">
        <EF:EFGrid blockId="detail" autoDraw="no" autoBind="false" checkMode="row" height="200" readonly="true"
                   queryMethod="queryDetail">
            <EF:EFColumn ename="planNo" cname="需求计划单号" align="center"/>
            <EF:EFColumn ename="matNum" cname="物资编码" align="center"/>
            <EF:EFColumn ename="matName" cname="物资名称" align="center"/>
            <EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true"/>
            <EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center"/>
            <EF:EFColumn ename="matSpec" cname="物资规格" align="center"/>
            <EF:EFColumn ename="matModel" cname="物资型号" align="center"/>
            <EF:EFComboColumn ename="unit" cname="计量单位" align="center">
                <EF:EFCodeOption codeName="wilp.ac.gm.unit"/>
            </EF:EFComboColumn>
            <EF:EFColumn ename="price" cname="参考单价(元)" align="center"/>
            <EF:EFColumn ename="num" cname="需求数量" align="center"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="80%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-keydown.js"></script>