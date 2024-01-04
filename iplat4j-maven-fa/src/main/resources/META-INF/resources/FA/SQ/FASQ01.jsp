<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>固定资产用户授权</title>
<EF:EFPage title="资产授权">
    <div class="row">
        <div class="col-md-2">
            <EF:EFRegion id="DeptMenu" title="科室列表">
                <div class="row">
                    <EF:EFInput ename="info-0-deptName" cname="所属科室" colWidth="12" ratio="4:8" />
                </div>
                <EF:EFTree id="deptMenu" valueField="deptCode" textField="deptName" hasChildren="isLeaf"
                           serviceName="FASQ01" methodName="queryDeptTree" style="height:616px;" expandLevel="0"/>
            </EF:EFRegion>
        </div>
        <div class="col-md-2">
            <EF:EFRegion id="FaTypeMenu" title="权限列表">
                <EF:EFTree id="menu" valueField="roleCode" textField="roleName" hasChildren="isLeaf"
                           serviceName="FASQ01" methodName="queryFaPermissionsTree" style="height:643px;"/>
            </EF:EFRegion>
        </div>
        <div class="col-md-8">
            <EF:EFRegion id="inqu" title="查询条件" showClear="true">
                <div class="row">
                    <EF:EFInput ename="inqu_status-0-workNo" cname="工号" colWidth="5"/>
                    <EF:EFInput ename="inqu_status-0-name" cname="姓名" colWidth="5"/>
                    <EF:EFInput ename="inqu_status-0-deptNum" cname="关联科室" colWidth="5" type="hidden"/>
                    <EF:EFInput ename="inqu_status-0-deptName" cname="关联科室" colWidth="5"/>
                    <EF:EFInput ename="inqu_status-0-roleCode" cname="用户权限" colWidth="5" type="hidden"/>
                    <EF:EFInput ename="inqu_status-0-roleName" cname="用户权限" colWidth="5"/>
                </div>
                <div class="button-region" id="buttonDiv">
                    <EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
                    <EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
                </div>
            </EF:EFRegion>
            <EF:EFRegion id="result" title="人员授权信息" showClear="true">
                <EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true" height="473px">
                    <EF:EFColumn ename="workNo" cname="工号" enable="false" align="center"/>
                    <EF:EFColumn ename="name" cname="姓名" enable="false" align="center"/>
                    <EF:EFColumn ename="deptNum" cname="关联科室" enable="false" align="center" hidden="true"/>
                    <EF:EFColumn ename="deptName" cname="关联科室" enable="false" align="center"/>
                    <EF:EFColumn ename="roleCode" cname="用户权限" enable="false" align="center" hidden="true"/>
                    <EF:EFColumn ename="roleName" cname="用户权限" enable="false" align="center"/>
                </EF:EFGrid>
            </EF:EFRegion>
        </div>
    </div>
    <EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="" modal="true"/>
    <EF:EFWindow id="popData2" url="" lazyload="true" width="75%" height="90%" title="" modal="true"/>
    <script type="text/javascript" src="${ctx}/FA/common/js/fa-keydown.js"></script>
    <script>
        var ctx = "${ctx}";
    </script>
</EF:EFPage>