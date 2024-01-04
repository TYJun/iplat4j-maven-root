<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>固定资产用户授权</title>
<EF:EFPage title="资产授权">
    <div class="row">
        <div class="col-md-3">
            <EF:EFRegion id="FaTypeMenu" title="权限列表">
                <EF:EFTree id="menu" valueField="roleCode" textField="roleName" hasChildren="isLeaf"
                           serviceName="FASQ01" methodName="queryFaPermissionsTree" style="height:595px;"/>
            </EF:EFRegion>
        </div>
        <div class="col-md-9">
            <EF:EFRegion id="info" title="授权信息" showClear="true">
                <div class="row">
                    <EF:EFInput ename="info-0-deptNum" cname="科室编码" colWidth="3" ratio="4:6" readonly="true"/>
                    <EF:EFInput ename="info-0-deptName" cname="关联科室" colWidth="3" ratio="4:8" readonly="true"/>
                    <EF:EFInput ename="info-0-roleCode" cname="角色编码" colWidth="3" ratio="4:8" readonly="true" type="hidden"/>
                    <EF:EFInput ename="info-0-roleName" cname="角色权限" colWidth="6" ratio="3:6" readonly="true"/>
                </div>
            </EF:EFRegion>

            <EF:EFRegion id="inqu" title="查询条件" showClear="true">
                <div class="row">
                    <EF:EFInput ename="inqu_status-0-workNo" cname="工号" colWidth="3" ratio="4:6"/>
                    <EF:EFInput ename="inqu_status-0-name" cname="姓名" colWidth="3" ratio="4:8"/>
                    <EF:EFInput ename="inqu_status-0-deptName" cname="默认科室" colWidth="6" ratio="3:6"/>
                </div>
                <div class="button-region" id="buttonDiv">
                    <EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
                    <EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
                </div>
            </EF:EFRegion>

            <EF:EFRegion id="resultPerson" title="平台人员信息" showClear="true">
                <EF:EFGrid blockId="resultPerson" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true" height="400px">
                    <EF:EFColumn ename="MEMBER_ID" cname="MEMBER_ID" enable="false" align="center" hidden="true"/>
                    <EF:EFColumn ename="PARENT_ID" cname="PARENT_ID" enable="false" align="center" hidden="true"/>
                    <EF:EFColumn ename="workNo" cname="工号" enable="false" align="center"/>
                    <EF:EFColumn ename="name" cname="姓名" enable="false" align="center"/>
                    <EF:EFColumn ename="deptName" cname="默认科室" enable="false" align="center"/>
                </EF:EFGrid>
            </EF:EFRegion>
        </div>
    </div>
    <script type="text/javascript" src="${ctx}/FA/common/js/fa-keydown.js"></script>
</EF:EFPage>