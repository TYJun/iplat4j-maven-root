<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
    <EF:EFRegion id="info" head="hidden">
        <EF:EFInput ename="type" cname="操作类型" ratio="4:6" type="hidden"/>
        <EF:EFTreeInput ename="info-0-parentId" cname="归口科室" serviceName="FAZN01" methodName="queryFaDeptTree"
             valueField="id" textField="deptName" hasChildren="isLeaf" readonly="true" required="true"
             root="{id: 'root', deptName: '根节点'}" ratio="4:6">
        </EF:EFTreeInput>
        <EF:EFPopupInput ename="info-0-deptNum" cname="下属科室"
                         popupTitle="科室选择" readOnly="true" required="true"
                         popupType="ServiceGrid" serviceName="FADA01" methodName="queryDept" resultId="dept"
                         valueField="deptNum" textField="deptName"
                         columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="提交" ename="SAVE" layout="3"></EF:EFButton>
            <EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
        </div>
    </EF:EFRegion>
</EF:EFPage>