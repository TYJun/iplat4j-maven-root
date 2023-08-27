<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<title>宿舍入住申请</title>
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
            <EF:EFSelect ename="sex" cname="性别"  required="true" colWidth="5">
                <EF:EFOption label="请选择性别" value=""/>
                <EF:EFOption label="男" value="1"/>
                <EF:EFOption label="女" value="0"/>
            </EF:EFSelect>
            <EF:EFInput ename="age" cname="年龄" required="true"
                        colWidth="5"  type="text"/>
        </div>
        <div class="row">
            <EF:EFInput ename="phone" cname="电话" required="true"
                        colWidth="5"  type="text" />
            <EF:EFInput ename="identityCard" cname="身份证号" required="true"
                        colWidth="5"  type="text" />
        </div>
        <div class="row">
            <EF:EFPopupInput ename="deptName" cname="部门科室" colWidth="5"
                 popupTitle="科室选择" required="true" readOnly="true"
                 popupType="ServiceGrid" serviceName="DMRZ01" methodName="queryDept"
                 resultId="dept" valueField="deptNum" textField="deptName"
                 columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
            <EF:EFSelect ename="maritalStatus" cname="婚姻状态" required="true" colWidth="5">
                <EF:EFOption label="请选择是否已婚" value=""/>
                <EF:EFOption label="是" value="是"/>
                <EF:EFOption label="否" value="否"/>
            </EF:EFSelect>
            <EF:EFInput ename="spouseName" cname="配偶姓名" required="true"
                        colWidth="5"  type="text" />
        </div>
        <div class="row">
            <EF:EFSelect ename="educationBackground" cname="学历" required="true" colWidth="5">
                <EF:EFOption label="请选择学历" value=""/>
                <EF:EFOption label="小学" value="小学"/>
                <EF:EFOption label="初中" value="初中"/>
                <EF:EFOption label="高中" value="高中"/>
                <EF:EFOption label="大学" value="大学"/>
                <EF:EFOption label="政策类研究生" value="政策类研究生"/>
                <EF:EFOption label="硕士" value="硕士"/>
                <EF:EFOption label="博士" value="博士"/>
                <EF:EFOption label="博士后" value="博士后"/>
            </EF:EFSelect>
<%--            <EF:EFSelect ename="academicYear" cname="学年制" colWidth="5">--%>
<%--                <EF:EFOption label="请选择学年制" value=""/>--%>
<%--                <EF:EFOption label="一学年制" value="一学年制"/>--%>
<%--                <EF:EFOption label="二学年制" value="二学年制"/>--%>
<%--                <EF:EFOption label="三学年制" value="二学年制"/>--%>
<%--                <EF:EFOption label="四学年制" value="四学年制"/>--%>
<%--                <EF:EFOption label="五学年制" value="五学年制"/>--%>
<%--            </EF:EFSelect>--%>
        </div>
        <div class="row">
            <EF:EFSelect ename="employmentNature" cname="职工属性" required="true" colWidth="5">
                <EF:EFOption label="请选择职工属性" value=""/>
                <EF:EFOption label="本院职工" value="本院职工"/>
                <EF:EFOption label="政策类研究生" value="政策类研究生"/>
                <EF:EFOption label="外协单位员工" value="外协单位员工"/>
                <EF:EFOption label="医院返聘职工" value="医院返聘职工"/>
                <EF:EFOption label="科室返聘职工" value="科室返聘职工"/>
                <EF:EFOption label="临时医技人员" value="临时医技人员"/>
                <EF:EFOption label="院聘临时工" value="院聘临时工"/>
                <EF:EFOption label="科聘临时工" value="科聘临时工"/>
            </EF:EFSelect>
<%--            <EF:EFSelect ename="isNetwork" cname="是否联网" colWidth="5">--%>
<%--                <EF:EFOption label="请选择是否联网" value=""/>--%>
<%--                <EF:EFOption label="是" value="是"/>--%>
<%--                <EF:EFOption label="否" value="否"/>--%>
<%--            </EF:EFSelect>--%>
        </div>
        <div class="row">
<%--            <EF:EFSelect ename="isStay" cname="是否已办暂住证" colWidth="5">--%>
<%--                <EF:EFOption label="请选择是否已办暂住证" value=""/>--%>
<%--                <EF:EFOption label="是" value="是"/>--%>
<%--                <EF:EFOption label="否" value="否"/>--%>
<%--            </EF:EFSelect>--%>
            <EF:EFInput ename="permanentResidence" cname="户口所在地" required="true"
                        colWidth="5"  type="text"/>
        </div>
        <div class="row">
            <EF:EFDatePicker ename="hiredate" cname="入职时间" readonly="true"
                             colWidth="5"  type="text"
                             placeholder="请选择入职时间" required="true"/>
<%--            <EF:EFDatePicker ename="estimatedInDate" cname="预计入住时间" readonly="true"--%>
<%--                             colWidth="5"  type="text"--%>
<%--                             placeholder="请选择预计入住时间" />--%>
        </div>
        <div class="row">
<%--            <EF:EFDatePicker ename="estimatedOutDate" cname="预计退宿时间" readonly="true"--%>
<%--                             colWidth="5"  type="text"--%>
<%--                             placeholder="请选择预计退宿时间" />--%>
            <EF:EFInput ename="note" cname="备注"
                        colWidth="5"  type="textarea"/>
        </div>
        <EF:EFPage>
        </EF:EFPage>
    </EF:EFRegion>
</EF:EFPage>
