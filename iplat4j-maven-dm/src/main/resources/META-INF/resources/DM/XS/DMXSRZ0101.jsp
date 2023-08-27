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
            <EF:EFInput ename="age" cname="年龄"
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

        </div>
        <div class="row">
            <EF:EFSelect ename="employmentNature" cname="人员属性" required="true" colWidth="5">
                <EF:EFOption label="请选择职工属性" value=""/>
                <EF:EFOption label="全日制学生" value="全日制学生"/>
                <EF:EFOption label="政策类研究生" value="政策类研究生"/>
                <EF:EFOption label="进修医生" value="进修医生"/>
                <EF:EFOption label="进修护士" value="进修护士"/>
                <EF:EFOption label="实习医生" value="实习医生"/>
                <EF:EFOption label="实习护士" value="实习护士"/>
                <EF:EFOption label="规范会培训生" value="规范会培训生"/>
            </EF:EFSelect>
        </div>
        <div class="row">
            <EF:EFInput ename="school" cname="所属学校" required="true"
                        colWidth="5"  type="text"/>
            <EF:EFInput ename="major" cname="专业" required="true"
                        colWidth="5"  type="text"/>
            <EF:EFInput ename="permanentResidence" cname="户口所在地" required="true"
                        colWidth="5"  type="text"/>
        </div>
        <div class="row">
    <EF:EFSelect ename="roomName" cname="房间地址" colWidth="5"
                     popupTitle="房间选择" required="true" readOnly="true"
                     popupType="ServiceGrid" serviceName="DMXSRZ0101"
                     methodName="queryRoomAddr" optionLabel="请选择" filter="contains" resultId="roomName"
                     valueField="id" textField="roomName"/>

            <EF:EFSelect ename="dormitoryDirector" cname="是否宿舍长" required="true" colWidth="5">
                        <EF:EFOption label="否" value="否"/>
                        <EF:EFOption label="是" value="是"/>
            </EF:EFSelect>

            <EF:EFInput ename="note" cname="备注"
                        colWidth="5"  type="textarea"/>
        </div>
        <EF:EFPage>
        </EF:EFPage>
    </EF:EFRegion>
</EF:EFPage>
