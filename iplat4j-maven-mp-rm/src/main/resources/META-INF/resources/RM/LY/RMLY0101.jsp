<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!--领用申请信息-->
<EF:EFPage>
    <EF:EFRegion id="inqu" title="领用申请">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-deptName" cname="申领科室" readonly="true" colWidth="5"/>
            <EF:EFInput ename="inqu_status-0-deptNum" cname="领用科室" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-applyUserName" cname="申领人" readonly="true" colWidth="5"/>
            <EF:EFInput ename="inqu_status-0-applyUserNo" cname="申领人" type="hidden"/>
        </div>
        <div class="row">
            <c:choose>
                <c:when test="${type == 'see'}">
                    <EF:EFInput ename="inqu_status-0-costDeptName" cname="成本归集科室" readonly="true" colWidth="5"/>
                </c:when>
                <c:otherwise>
                    <EF:EFSelect ename="inqu_status-0-costDeptNum" cname="成本归集科室" resultId="userDept" colWidth="5"
                                 serviceName="RMTY01" methodName="queryUserBusinessDept"
                                 textField="deptName" valueField="deptNum" filter="contains" required="true">
                    </EF:EFSelect>
                    <EF:EFInput ename="inqu_status-0-costDeptName" cname="成本归集科室" type="hidden"/>
                </c:otherwise>
            </c:choose>
            <EF:EFInput ename="inqu_status-0-remark" cname="备注" type="textarea" colWidth="5" placeholder="不能超过200字"/>
        </div>
        <div class="row">
            <c:if test="${type == 'see'}">
                <EF:EFInput ename="inqu_status-0-rejectReason" cname="驳回原因" colWidth="5" type="textarea"
                            readonly="true"/>
            </c:if>

            <EF:EFInput type="hidden" ename="type" cname="操作类型"/>
            <EF:EFInput type="hidden" ename="inqu_status-0-id" cname="主键"/>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="领用申请明细列表">
        <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row" height="365">
            <EF:EFColumn ename="matNum" cname="物资编码" align="center" enable="false"/>
            <EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false"/>
            <EF:EFColumn ename="matSpec" cname="物资规格" align="center" enable="false"/>
            <%--<EF:EFColumn ename="matModel" cname="物资型号" align="center" enable="false"/>--%>
            <EF:EFColumn ename="unit" cname="计量单位" hidden="true"/>
            <EF:EFColumn ename="packfactor" cname="包装系数" align="center" enable="false"/>
            <EF:EFColumn ename="unitName" cname="计量单位" align="center" enable="false"/>
            <EF:EFColumn ename="price" cname="单价(元)" align="center" enable="false"/>
            <EF:EFColumn ename="num" cname="领用数量" align="center"/>
            <EF:EFColumn ename="claimAmount" cname="领用金额(元)" align="center" enable="false"/>
            <EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true"/>
            <EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false"/>
            <c:if test="${type=='see'}">
                <EF:EFColumn ename="outNum" cname="已领用数量" align="center" enable="false"/>
            </c:if>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="85%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-autoComplete.js"></script>
