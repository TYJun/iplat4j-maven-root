<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<title>校外登记录入</title>
<EF:EFPage>
    <EF:EFRegion id="result" title="信息" fitHeight="true">
        <div class="row">
            <div class="col-md-5">
                <div class="form-group">
                    <label for="manNo" class="col-xs-4 control-label">
                        <span style="color: red">*</span>工号</label>
                    <div class="col-xs-8">
                        <input id="manNo" name="manNo"
                               ondblclick="showAll('manNo')"
                               onblur="echoOne('manNo')">
                    </div>
                </div>
            </div>
            <EF:EFPopupInput ename="manName" cname="姓名" colWidth="5"
                             popupTitle="人员选择" required="true" readOnly="true"
                             popupType="ServiceGrid" serviceName="DMRZ01"
                             methodName="queryPersonnelRZ" resultId="person" valueField="workNo"
                             textField="name" columnEnames="workNo,name" columnCnames="人员工号,人员姓名"
                             backFillColumnIds="workNo" backFillFieldIds="manNo"/>
        </div>
        <div class="row">
            <EF:EFSelect ename="sex" cname="性别" required="true" colWidth="5">
                <EF:EFOption label="请选择性别" value=""/>
                <EF:EFOption label="男" value="1"/>
                <EF:EFOption label="女" value="0"/>
            </EF:EFSelect>
            <EF:EFInput ename="age" cname="年龄" required="true"
                        colWidth="5" type="text"/>
        </div>
        <div class="row">
            <EF:EFInput ename="phone" cname="电话"
                        colWidth="5" type="text" required="true"/>
        </div>
        <div class="row">
            <EF:EFInput ename="note" cname="目前居住地"
                        colWidth="5" type="textarea"/>
        </div>
        <EF:EFPage>
        </EF:EFPage>
    </EF:EFRegion>
</EF:EFPage>
