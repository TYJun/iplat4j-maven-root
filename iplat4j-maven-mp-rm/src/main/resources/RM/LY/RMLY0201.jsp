<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!--领用单录入-->
<EF:EFPage>
    <EF:EFRegion id="inqu" title="领用申请">
        <div class="row">
            <div class="col-md-5">
                <div class="form-group">
                    <label for="inqu_status-0-deptName" class="col-xs-4 control-label">
                        <span class="i-input-required">*</span>领用科室
                    </label>
                    <div class="col-xs-8">
                        <input id="inqu_status-0-deptNum" name="inqu_status-0-deptNum" type="hidden">
                        <input id="inqu_status-0-deptName" name="inqu_status-0-deptName"
                               ondblclick="showAll('inqu_status-0-deptName')">
                    </div>
                </div>
            </div>

            <div class="col-md-5">
                <div class="form-group">
                    <label for="inqu_status-0-costDeptName" class="col-xs-4 control-label">
                        <span class="i-input-required">*</span>成本归集科室
                    </label>
                    <div class="col-xs-8">
                        <input id="inqu_status-0-costDeptNum" name="inqu_status-0-costDeptNum" type="hidden">
                        <input id="inqu_status-0-costDeptName" name="inqu_status-0-costDeptName"
                               ondblclick="showAll('inqu_status-0-costDeptName')">
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-5">
                <div class="form-group">
                    <label for="inqu_status-0-recCreatorName" class="col-xs-4 control-label">
                        领用人
                    </label>
                    <div class="col-xs-8">
                        <input id="inqu_status-0-recCreator" name="inqu_status-0-recCreator" type="hidden">
                        <input id="inqu_status-0-recCreatorName" name="inqu_status-0-recCreatorName"
                               ondblclick="showAll('inqu_status-0-recCreatorName')">
                    </div>
                </div>
            </div>
            <EF:EFInput ename="inqu_status-0-remark" cname="备注" type="textarea" colWidth="5" placeholder="不能超过200字"/>
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
            <EF:EFColumn ename="unitName" cname="计量单位" align="center" enable="false"/>
            <EF:EFColumn ename="price" cname="单价(元)" align="center" enable="false"/>
            <EF:EFColumn ename="num" cname="领用数量" align="center"/>
            <EF:EFColumn ename="claimAmount" cname="领用金额(元)" align="center" enable="false"/>
            <EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true"/>
            <EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="85%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-autoComplete.js"></script>
