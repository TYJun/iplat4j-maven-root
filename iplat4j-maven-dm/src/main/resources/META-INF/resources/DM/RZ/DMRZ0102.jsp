<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<title>宿舍入住申请</title>
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
            <EF:EFInput ename="age" cname="年龄"
                        colWidth="5"  type="text"/>
        </div>
        <div class="row">
            <EF:EFInput ename="phone" cname="电话"
                        colWidth="5"  type="text" />
            <EF:EFInput ename="identityCard" cname="身份证号"
                        colWidth="5"  type="text" />
        </div>
        <div class="row">
            <EF:EFPopupInput ename="deptName" cname="部门科室" colWidth="5"
                 popupTitle="科室选择" required="true" readOnly="true"
                 popupType="ServiceGrid" serviceName="DMRZ01" methodName="queryDept"
                 resultId="dept" valueField="deptNum" textField="deptName"
                 columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
            <EF:EFSelect ename="maritalStatus" cname="婚姻状态" colWidth="5">
                <EF:EFOption label="请选择是否已婚" value=""/>
                <EF:EFOption label="是" value="是"/>
                <EF:EFOption label="否" value="否"/>
            </EF:EFSelect>
            <EF:EFInput ename="spouseName" cname="配偶姓名" required="true"
                        colWidth="5"  type="text" />
        </div>
        <div class="row">
            <EF:EFSelect ename="educationBackground" cname="学历" colWidth="5">
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
                <EF:EFOption label="规范会培训生" value="规范会培训生"/>
                <EF:EFOption label="院聘临时工" value="院聘临时工"/>
                <EF:EFOption label="科聘临时工" value="科聘临时工"/>
            </EF:EFSelect>

        </div>
        <div class="row">
            <EF:EFInput ename="permanentResidence" cname="户口所在地"
                        colWidth="5"  type="text"/>
        </div>
        <div class="row">
            <EF:EFDatePicker ename="hiredate" cname="入职时间" readonly="true"
                             colWidth="5"  type="text"
                             placeholder="请选择入职时间" />
            <EF:EFDatePicker ename="estimatedInDate" cname="预计入住时间" readonly="true"
                             colWidth="5"  type="text"
                             placeholder="请选择预计入住时间" />
        </div>
        <div class="row">
            <EF:EFDatePicker ename="estimatedOutDate" cname="预计退宿时间" readonly="true"
                             colWidth="5"  type="text"
                             placeholder="请选择预计退宿时间" />
            <EF:EFInput ename="note" cname="备注"
                        colWidth="5"  type="textarea"/>
        </div>
        <EF:EFPage>
        </EF:EFPage>
    </EF:EFRegion>
</EF:EFPage>
