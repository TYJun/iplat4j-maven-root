<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="日志信息展示页面">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFCascadeSelect ename="inqu_status-0-operationClass"
                                cname="操作类型" resultId="result_class"
                                textField="operationClassCname"
                                valueField="operationClassEname">
                <EF:EFOption label="请选择" value=""/>
            </EF:EFCascadeSelect>
            <EF:EFDateSpan blockId="inqu_status" row="0"
                           startName="operationTime_start"
                           endName="operationTime_end"
                           bindWidth="8" startCname="开始时间" endCname="结束时间"
                           bindRatio="2:4:4"
                           format="yyyyMMddHHmmss" role="datetime"/>
            <EF:EFCascadeSelect ename="inqu_status-0-operationTypeEname"
                                template="#=value#-#=label#"
                                methodName="queryOperationList"
                                cname="操作分类编号"
                                resultId="result_log"
                                cascadeFrom="inqu_status-0-operationClass"
                                textField="label" valueField="value">
                <EF:EFOption label="请选择" value=""/>
            </EF:EFCascadeSelect>
            <EF:EFPopupInput ename="inqu_status-0-operator" cname="操作人"
                             resizable="true"
                             containerId="ef_popup_grid" popupWidth="960"
                             popupTitle="用户信息页面"
                             data-bind="value: operator">
            </EF:EFPopupInput>
        </div>
    </EF:EFRegion>

    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" autoDraw="no">
            <EF:EFColumn ename="operationId" cname="操作编号" primaryKey="true"
                         hidden="true"/>
            <EF:EFColumn ename="operationTraceId" cname="操作行为编号" hidden="true"/>
            <EF:EFColumn ename="operationSpanId" cname="层级编号" hidden="true"/>
            <EF:EFColumn ename="operationTypeEname" cname="操作分类"/>
            <EF:EFColumn ename="operationTypeCname" cname="操作分类名称"/>
            <EF:EFColumn ename="operator" cname="操作人"/>
            <EF:EFColumn ename="clientIp" cname="客户端IP"/>
            <EF:EFColumn ename="operationDesc" cname="操作描述" width="350"/>
            <EF:EFColumn ename="operationTime" cname="操作时间" editType="datetime"
                         parseFormats="['yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss']"
                         dateFormat="yyyy-MM-dd HH:mm:ss"
                         displayType="datetime"/>
            <EF:EFColumn ename="operationInvokeKey" cname="关键字"/>
        </EF:EFGrid>
    </EF:EFRegion>


    <div id="ef_popup_grid" style="display: none">
        <EF:EFRegion id="inqus" title="查询条件">
            <div class="row">
                <EF:EFInput ename="popup_inqu_status-0-loginName" cname="登录账号"/>
                <EF:EFInput ename="popup_inqu_status-0-userName" cname="用户姓名"/>
            </div>
        </EF:EFRegion>
        <EF:EFRegion id="result2" title="记录集">
            <EF:EFGrid blockId="popup_result" autoDraw="no"
                       serviceName="EPLG01" queryMethod="queryUser"
                       checkMode="single"
                       autoBind="false"
                       toolbarConfig="{add: false, cancel: false, save:false, delete: true}">
                <EF:EFColumn ename="loginName" cname="登录账号"/>
                <EF:EFColumn ename="userName" cname="用户姓名"/>
            </EF:EFGrid>
            <div class="k-window-saves k-popup-saves">
                <EF:EFButton cname="确定" ename="ef_popup_grid_userName"
                             layout="1" class="k-button"></EF:EFButton>
            </div>
        </EF:EFRegion>
    </div>


</EF:EFPage>
