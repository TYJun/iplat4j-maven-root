<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!--仓库出库确认管理-->
<title>物资出库</title>
<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-claimNo" cname="领用单号"/>
            <EF:EFInput ename="inqu_status-0-deptName" cname="申领科室"/>
                <%--<EF:Select ename="inqu_status-0-statusCodes" cname="状态" resultId="status"
                             serviceName="RMLY02" methodName="queryStatus" textField="label" valueField="value">
                </EF:Select>--%>
            <EF:EFMultiSelect ename="inqu_status-0-statusCodes" cname="状态" autoClose="false">
                <EF:EFOptions blockId="status" textField="label" valueField="value"/>
            </EF:EFMultiSelect>
            <EF:EFDatePicker ename="inqu_status-0-beginTime" cname="申请日期起(>=)"></EF:EFDatePicker>
            <EF:EFDatePicker ename="inqu_status-0-endTime" cname="申请日期止(<=)"></EF:EFDatePicker>
            <EF:EFInput ename="inqu_status-0-claimId" cname="领用ID" type="hidden"/>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="领用单列表">
        <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="single,row" readonly="true" height="250">
            <EF:EFColumn ename="id" cname="id" hidden="true"/>
            <EF:EFColumn ename="claimNo" cname="领用单号" align="center" displayType="url"/>
            <EF:EFColumn ename="statusName" cname="状态" align="center"/>
            <EF:EFColumn ename="statusCode" cname="状态" align="center" hidden="true"/>
            <EF:EFColumn ename="deptName" cname="申领科室" align="center"/>
            <EF:EFColumn ename="costDeptName" cname="成本归集科室" align="center"/>
            <EF:EFColumn ename="claimNum" cname="领用总量" align="center"/>
            <EF:EFColumn ename="claimTotalMoney" cname="领用总金额(元)" align="center"/>
            <EF:EFColumn ename="recCreateTimeStr" cname="申请时间" align="center"/>
            <EF:EFColumn ename="applyUserName" cname="申领人" align="center"/>
        </EF:EFGrid>
    </EF:EFRegion>
    <EF:EFRegion id="detail" title="领用明细列表">
        <EF:EFGrid blockId="detail" autoDraw="no" autoBind="true" checkMode="row" readonly="true"
                   queryMethod="queryDetail">
            <EF:EFColumn ename="matNum" cname="物资编码" align="center" enable="false"/>
            <EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false"/>
            <EF:EFColumn ename="matSpec" cname="物资规格" align="center" enable="false"/>
            <%--<EF:EFColumn ename="matModel" cname="物资型号" align="center" enable="false"/>--%>
            <EF:EFColumn ename="unit" cname="计量单位" hidden="true"/>
            <EF:EFColumn ename="unitName" cname="计量单位" align="center"/>
            <EF:EFColumn ename="price" cname="单价(元)" align="center" enable="false" hidden="true"/>
            <EF:EFColumn ename="num" cname="领用数量" align="center"/>
            <EF:EFColumn ename="claimAmount" cname="领用金额(元)" align="center" enable="false"/>
            <EF:EFColumn ename="outNum" cname="已出库数量" align="center" width="100" enable="false"/>
            <EF:EFColumn ename="stockNum" cname="库存量" align="center" enable="false"/>
            <EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true"/>
            <EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="100%" height="100%"></EF:EFWindow>
<EF:EFWindow id="detailPopData" url=" " lazyload="true" width="80%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-keydown.js"></script>