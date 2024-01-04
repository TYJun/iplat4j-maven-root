<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>固定资产用户授权</title>
<EF:EFPage title="资产授权">
    <div class="row">
        <div class="col-md-7">
            <EF:EFRegion id="inqu" title="查询条件" showClear="true">
                <div class="row">
                    <EF:EFInput ename="inqu_status-0-workNo" cname="工号" colWidth="6"/>
                    <EF:EFInput ename="inqu_status-0-name" cname="姓名" colWidth="6"/>
                    <EF:EFInput ename="inqu_status-0-deptName" cname="默认科室" colWidth="6"/>
                </div>
                <div class="button-region" id="buttonDiv">
                    <EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
                    <EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
                </div>
            </EF:EFRegion>
            <EF:EFRegion id="resultPerson" title="平台人员信息" showClear="true">
                <EF:EFGrid blockId="resultPerson" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true" height="480px">
                    <EF:EFColumn ename="typeCode" cname="工号" enable="false" align="center"/>
                    <EF:EFColumn ename="typeName" cname="姓名" enable="false" align="center"/>
                    <EF:EFColumn ename="parentName" cname="默认科室" enable="false" align="center"/>
                </EF:EFGrid>
            </EF:EFRegion>
        </div>
        <div class="col-md-5">
            <EF:EFRegion id="info" title="授权信息" showClear="true">
                <div class="row">
                    <EF:EFInput ename="inqu_status-0-roleName" cname="角色权限" colWidth="12" ratio="3:8"/>
                    <EF:EFInput ename="inqu_status-0-deptName" cname="关联科室" colWidth="12" ratio="3:8"/>
                </div>
            </EF:EFRegion>

            <EF:EFRegion id="result" title="授权人员信息" showClear="true">
                <EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true" height="480px">
                    <EF:EFColumn ename="workNo" cname="工号" enable="false" align="center"/>
                    <EF:EFColumn ename="name" cname="姓名" enable="false" align="center"/>
                </EF:EFGrid>
            </EF:EFRegion>
        </div>
    </div>
</EF:EFPage>