<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!--出库 serviceName="RMJK03" methodName="dockWareHouse"-->
<EF:EFPage title="领用出库">
    <EF:EFRegion id="inqu" title="领用出库">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-deptName" cname="申领科室" readonly="true" colWidth="5"/>
            <EF:EFInput ename="inqu_status-0-deptNum" cname="领用科室" type="hidden"/>

            <EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库" optionLabel="请选择仓库" required="true"
                         filter="contains" resultId="wareHouse"
                         serviceName="RMJK03" methodName="dockWareHouse"
                         textField="wareHouseName" valueField="wareHouseNo">
            </EF:EFSelect>

            <EF:EFInput type="hidden" ename="inqu_status-0-wareHouseName" cname="仓库"/>
            <EF:EFInput ename="inqu_status-0-applyUserName" cname="领用人" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-applyUserNo" cname="领用人" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-costDeptNum" cname="成本科室" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-costDeptName" cname="成本科室" type="hidden"/>
            <EF:EFInput type="hidden" ename="type" cname="操作类型"/>
            <EF:EFInput type="hidden" ename="inqu_status-0-id" cname="主键"/>
            <EF:EFInput type="hidden" ename="inqu_status-0-statusCode" cname="领用状态"/>
            <EF:EFInput type="hidden" ename="inqu_status-0-claimNo" cname="领用单号"/>
            <EF:EFInput type="hidden" ename="inqu_status-0-remark" cname="备注"/>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="领用明细列表">
        <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row" height="365">
            <EF:EFColumn ename="claimId" cname="领用id" align="center" hidden="true"/>
            <EF:EFColumn ename="matNum" cname="物资编码" align="center" enable="false"/>
            <EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false"/>
            <EF:EFColumn ename="matSpec" cname="物资规格" align="center" enable="false"/>
            <%--<EF:EFColumn ename="matModel" cname="物资型号" align="center" enable="false"/>--%>
            <EF:EFColumn ename="unit" cname="计量单位" hidden="true"/>
            <EF:EFColumn ename="unitName" cname="计量单位" align="center" width="80"/>
            <EF:EFColumn ename="price" cname="单价(元)" align="center" enable="false" hidden="true"/>
            <EF:EFColumn ename="num" cname="领用数量" align="center" width="100" enable="false"/>
            <EF:EFColumn ename="stockNum" cname="库存量" align="center" width="100" enable="false"/>
            <EF:EFColumn ename="outNum" cname="已出库数量" align="center" width="100" enable="false"/>
            <EF:EFColumn ename="outAmount" cname="本次出库数量" align="center"/>
            <EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true"/>
            <EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="85%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript">


</script>