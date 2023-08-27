<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%--<title>出入库统计查询</title>--%>
<title>出入库报表</title>
<EF:EFPage>
    <div class="row">
        <div class="col-md-3">
            <EF:EFRegion id="inqu" title="查询条件" showClear="true">
                <div class="row">
                    <EF:EFSelect ename="wareHouseNo" cname="仓库名称" colWidth="12" ratio="3:9"
                         resultId="result" textField="wareHouseName" valueField="wareHouseNo"
                         serviceName="SIWH01" methodName="queryWareHouse">
                    </EF:EFSelect>
                </div>
                <div class="row">
                    <EF:EFTreeInput ename="matTypeId" cname="物资分类"
                        serviceName="SIKC01" methodName="getMateriaType" textField="text"
                        valueField="label" hasChildren="leaf" root="{label: 'root',text: '分类'}"
                        popupTitile="物资分类" clear="true" placeholder="请选择" colWidth="12" ratio="3:9">
                    </EF:EFTreeInput>
                    <EF:EFInput ename="matTypeNum" cname="物资分类编码" type="hidden"/>
                </div>
                <div class="row">
                    <EF:EFSelect ename="classGroup" cname="类组" colWidth="12" ratio="3:9">
                        <EF:EFOption label="物资用品" value="01"></EF:EFOption>
                        <EF:EFOption label="设备配件" value="02"></EF:EFOption>
                        <EF:EFOption label="捐赠物资" value="03"></EF:EFOption>
                        <EF:EFOption label="全部" value=""></EF:EFOption>
                    </EF:EFSelect>
                </div>
                <div class="row">
                    <EF:EFSelect ename="hasBack" cname="包含退货/退库" colWidth="12" ratio="3:9">
                        <EF:EFOption label="是" value="是"></EF:EFOption>
                        <EF:EFOption label="否" value="否"></EF:EFOption>
                    </EF:EFSelect>
                </div>
                <div class="row">
                    <EF:EFDatePicker ename="beginTime" cname="统计日期起" role="date" colWidth="12" ratio="3:9"/>
                </div>
                <div class="row">
                    <EF:EFDatePicker ename="endTime" cname="统计日期止" role="date" colWidth="12" ratio="3:9"/>
                </div>
                <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="inqu_status-0-supplierName" class="col-xs-3 control-label">供应商</label>
                        <div class="col-xs-9">
                            <input id="inqu_status-0-supplierNum" name="inqu_status-0-supplierNum" type="hidden">
                            <input id="inqu_status-0-supplierName" name="inqu_status-0-supplierName" ondblclick="showAll('inqu_status-0-supplierName')" >
                        </div>
                    </div>
                </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="inqu_status-0-deptName" class="col-xs-3 control-label">成本科室</label>
                            <div class="col-xs-9">
                                <input id="inqu_status-0-deptNum" name="inqu_status-0-deptNum" type="hidden">
                                <input id="inqu_status-0-deptName" name="inqu_status-0-deptName" ondblclick="showAll('inqu_status-0-deptName')" >
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <EF:EFInput ename="matName" cname="物资名称" colWidth="12" ratio="3:9"/>
                </div>
                <div class="button-region" id="buttonDiv">
                    <EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
                    <EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
                </div>
            </EF:EFRegion>
            <EF:EFRegion id="R1" title="报表列表" fitHeight="true">
                <EF:EFTree id="tree01" textField="text" valueField="label"
                           hasChildren="leaf" serviceName="SICX02" methodName="queryReportTree">
                </EF:EFTree>
            </EF:EFRegion>
        </div>
        <div class="col-md-9">
            <EF:EFRegion id="R2" title="报表" fitHeight="true">
                <iframe id="cxFrame" name="cxFrame" width="100%" height="100%" security="restricted"
                        sandbox="allow-same-origin allow-forms allow-downloads allow-popups allow-scripts"></iframe>
            </EF:EFRegion>
            <%--<form name="cxForm" method="post" target="cxFrame"></form>--%>

        </div>
    </div>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-initTime.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-autoComplete.js"></script>