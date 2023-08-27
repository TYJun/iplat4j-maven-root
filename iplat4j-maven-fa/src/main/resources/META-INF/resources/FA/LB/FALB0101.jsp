<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>资产类别录入</title>
<EF:EFPage>
    <EF:EFRegion id="info" head="hidden">
        <EF:EFInput ename="type" cname="操作类型" ratio="4:6" type="hidden"/>
        <EF:EFInput ename="inqu_status-0-faTypeId" cname="faTypeId" ratio="4:6" type="hidden"/>
        <EF:EFTreeInput ename="inqu_status-0-parentId" cname="上级类别" serviceName="FALB01" methodName="queryFaTypeTree"
             valueField="id" textField="typeName" hasChildren="isLeaf" readonly="true" required="true"
             root="{id: 'root', typeName: '根节点'}" ratio="4:6">
        </EF:EFTreeInput>
        <EF:EFInput ename="inqu_status-0-typeName" cname="类别名称" ratio="4:6" required="true"/>
        <EF:EFInput ename="inqu_status-0-sortNo" cname="排序" type="text" rows="3" ratio="4:6" required="true"/>
        <EF:EFInput ename="inqu_status-0-useYears" cname="使用年限(年)" type="text" ratio="4:6" maxLength="3" required="true"/>
        <EF:EFInput ename="inqu_status-0-codeRule" cname="编码规则" type="text" ratio="4:6"/>
        <EF:EFInput ename="inqu_status-0-remark" cname="备注" type="textarea" rows="3" ratio="4:6"/>
        <EF:EFInput ename="inqu_status-0-archiveFlag" cname="归档标记" ratio="4:6" type="hidden"/>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="提交" ename="SAVE" layout="3"></EF:EFButton>
            <EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
        </div>
    </EF:EFRegion>
</EF:EFPage>