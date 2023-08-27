<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!--领用申请管理-->
<EF:EFPage>
    <div class="row">
        <div class="col-md-7">
            <EF:EFRegion id="detail_form" title="领用申请">
                <div class="row" style="margin-bottom: 10px">
                    <div class="button-region">
                        <EF:EFButton cname="保存" ename="SAVE" layout="0"></EF:EFButton>
                        <EF:EFButton cname="保存并提交" ename="SAVE_AND_SUBMIT" layout="0"></EF:EFButton>
                        <EF:EFButton cname="新增并保存" ename="ADD_ADN_SAVE" layout="0"></EF:EFButton>
                        <EF:EFButton cname="新增并提交" ename="ADD_AND_SUBMIT" layout="0"></EF:EFButton>
                        <EF:EFButton cname="清空" ename="REMOVE_ALL" layout="0"></EF:EFButton>
                    </div>
                </div>
                <div class="row">
                    <EF:EFInput ename="detail_form-0-deptName" cname="领用科室" readonly="true" colWidth="6"/>
                    <EF:EFInput ename="detail_form-0-deptNum" cname="领用科室" type="hidden"/>
                    <EF:EFInput ename="detail_form-0-recCreatorName" cname="领用人" readonly="true" colWidth="6"/>
                    <EF:EFInput ename="detail_form-0-recCreator" cname="领用人" type="hidden"/>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="costDeptName" class="col-xs-4 control-label">
                                <span class="i-input-required">*</span>成本归集科室
                            </label>
                            <div class="col-xs-8">
                                <input id="costDeptNum" name="costDeptNum" type="hidden">
                                <input id="costDeptName" name="costDeptName" ondblclick="showAll('costDeptName')">
                            </div>
                        </div>
                    </div>
                    <EF:EFInput ename="detail_form-0-remark" cname="备注" type="textarea" placeholder="不能超过200字"
                                colWidth="6"/>
                    <EF:EFInput type="hidden" ename="type" cname="操作类型"/>
                    <EF:EFInput type="hidden" ename="detail_form-0-id" cname="主键"/>
                </div>
            </EF:EFRegion>
            <EF:EFRegion id="result" title="领用申请明细列表" fitHeight="true">
                <EF:EFGrid blockId="detail" autoDraw="no" autoBind="false" checkMode="row" queryMethod="queryDetail">
                    <EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center" enable="false"/>
                    <EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false"/>
                    <EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true"/>
                    <EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false"/>
                    <EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" enable="false"/>
                    <%--<EF:EFColumn ename="matModel" cname="物资型号" align="center" enable="false"/>--%>
                    <EF:EFColumn ename="unit" cname="计量单位" hidden="true"/>
                    <EF:EFColumn ename="unitName" cname="单位" width="60" align="center" enable="false"/>
                    <EF:EFColumn ename="price" cname="参考单价(元)" width="95" align="center" enable="false"/>
                    <EF:EFColumn ename="num" cname="领用数量" align="center" width="90"/>
                    <EF:EFColumn ename="claimAmount" cname="领用金额(元)" width="100" align="center" enable="false"/>
                </EF:EFGrid>
            </EF:EFRegion>
        </div>

        <div class="col-md-5">
            <EF:EFRegion id="inqu" title="查询条件" showClear="true">
                <div class="row">
                    <EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" readonly="true" colWidth="6"/>
                    <EF:EFInput ename="inqu_status-0-deptNum" cname="科室编码" type="hidden"/>
                    <EF:EFInput ename="inqu_status-0-recCreatorName" cname="领用人" colWidth="6"/>
                    <EF:EFInput ename="inqu_status-0-planNo" cname="领用申请单号" colWidth="6"/>
                    <EF:EFSelect ename="inqu_status-0-statusCode" cname="状态" colWidth="6">
                        <EF:EFOption label="全部" value=""/>
                        <EF:EFCodeOption codeName="wilp.rm.claim.status"/>
                    </EF:EFSelect>
                    <EF:EFDatePicker ename="inqu_status-0-beginTime" cname="申请日期起" colWidth="6"></EF:EFDatePicker>
                    <EF:EFDatePicker ename="inqu_status-0-endTime" cname="申请日期止" colWidth="6"></EF:EFDatePicker>
                </div>
                <div class="button-region" id="buttonDiv">
                    <EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
                    <EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
                </div>
            </EF:EFRegion>
            <EF:EFRegion id="result" title="领用申请单列表" fitHeight="true">
                <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="single,row" readonly="true">
                    <EF:EFColumn ename="id" cname="id" hidden="true"/>
                    <EF:EFColumn ename="claimNo" cname="领用申请单号" align="center"/>
                    <EF:EFColumn ename="statusName" cname="状态" width="100" align="center"/>
                    <EF:EFColumn ename="claimNum" cname="领用总量" align="center" width="80"/>
                    <EF:EFColumn ename="deptName" cname="领用科室" align="center"/>
                    <EF:EFColumn ename="costDeptName" cname="成本归集科室" align="center"/>
                    <EF:EFColumn ename="remark" cname="备注" align="center"/>
                    <EF:EFColumn ename="recCreateTimeStr" cname="申请时间" align="center"/>
                    <EF:EFColumn ename="applyUserName" cname="领用人" align="center"/>
                </EF:EFGrid>
            </EF:EFRegion>
        </div>
    </div>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="90%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-autoComplete.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-keydown.js"></script>