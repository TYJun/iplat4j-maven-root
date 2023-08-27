<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<title>校外入住信息修改</title>
<EF:EFPage>
    <EF:EFRegion id="result" title="信息" fitHeight="true">
        <div class="row">
            <EF:EFInput ename="id" cname="主键" colWidth="5" type="hidden" readonly="true" />
            <EF:EFInput ename="manNo" cname="工号"  required="true" readOnly="true"
                        colWidth="5"  type="text"/>
            <EF:EFInput ename="manName" cname="姓名"  required="true" readOnly="true"
                        colWidth="5"  type="text"/>
        </div>
        <div class="row">
            <EF:EFSelect ename="sex" cname="性别"  required="true" colWidth="5">
                <EF:EFOption label="请选择性别" value=""/>
                <EF:EFOption label="男" value="1"/>
                <EF:EFOption label="女" value="0"/>
            </EF:EFSelect>
            <EF:EFInput ename="age" cname="年龄"  required="true"
                        colWidth="5"  type="text"/>
        </div>
        <div class="row">
            <EF:EFInput ename="phone" cname="电话"
                        colWidth="5"  type="text" required="true"/>
        </div>
        <div class="row">
            <EF:EFInput ename="note" cname="当前居住地"
                        colWidth="5"  type="textarea"/>
        </div>
        <EF:EFPage>
        </EF:EFPage>
    </EF:EFRegion>
</EF:EFPage>
