<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage>
    <EF:EFRegion id="result" title="入住申请详情信息" fitHeight="true">
        <div class="row">
            <EF:EFInput ename="id" cname="主键" colWidth="5" type="hidden" readonly="true" />
            <EF:EFInput ename="manNo" cname="工号" readOnly="true"
                        colWidth="5"  type="text"/>
            <EF:EFInput ename="manName" cname="姓名" readOnly="true"
                        colWidth="5"  type="text"/>
        </div>
        <div class="row">
            <EF:EFInput ename="sexMeaning" cname="性别"  readOnly="true"
                        colWidth="5"  type="text"/>
            <EF:EFInput ename="age" cname="年龄"  readOnly="true"
                        colWidth="5"  type="text"/>
        </div>
        <div class="row">
            <EF:EFInput ename="phone" cname="电话"
                        colWidth="5"  type="text" readOnly="true"/>
            <EF:EFInput ename="identityCard" cname="身份证号"
                        colWidth="5"  type="text" readOnly="true"/>
        </div>
        <div class="row">
            <EF:EFInput ename="deptName" cname="部门科室"
                        colWidth="5"  type="text" readOnly="true"/>
            <EF:EFInput ename="maritalStatus" cname="婚姻状态"
                        colWidth="5"  type="text" readOnly="true"/>
        </div>
        <div class="row">
            <EF:EFInput ename="spouseName" cname="配偶姓名"
                        colWidth="5"  type="text" readOnly="true"/>
            <EF:EFInput ename="educationBackground" cname="学历"
                        colWidth="5"  type="text" readOnly="true"/>
<%--            <EF:EFInput ename="academicYear" cname="学年制"--%>
<%--                        colWidth="5"  type="text" readOnly="true"/>--%>
        </div>
        <div class="row">
            <EF:EFInput ename="employmentNature" cname="职工属性"
                        colWidth="5"  type="text" readOnly="true"/>
<%--            <EF:EFInput ename="isNetwork" cname="是否联网"--%>
<%--                        colWidth="5"  type="text" readOnly="true"/>--%>
        </div>
        <div class="row">
<%--            <EF:EFInput ename="isStay" cname="是否已办暂住证"--%>
<%--                        colWidth="5"  type="text" readOnly="true"/>--%>
            <EF:EFInput ename="permanentResidence" cname="户口所在地"
                        colWidth="5"  type="text" readOnly="true"/>
        </div>
        <div class="row">
            <EF:EFInput ename="hiredate" cname="入职时间"
                        colWidth="5"  type="text" readOnly="true"/>
<%--            <EF:EFInput ename="estimatedInDate" cname="预计入住时间"--%>
<%--                        colWidth="5"  type="text" readOnly="true"/>--%>
        </div>
        <div class="row">
<%--            <EF:EFInput ename="estimatedOutDate" cname="预计退宿时间"--%>
<%--                        colWidth="5"  type="text" readOnly="true"/>--%>
            <EF:EFInput ename="note" cname="备注"
                        colWidth="5"  type="textarea" readOnly="true"/>
        </div>
    </EF:EFRegion>
</EF:EFPage>
