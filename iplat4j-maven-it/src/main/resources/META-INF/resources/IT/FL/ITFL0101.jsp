<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
    <EF:EFRegion id="info" head="hidden">
        <EF:EFInput ename="type" cname="操作类型" ratio="4:6" type="hidden"/>
        <EF:EFInput ename="inqu_status-0-itClassifyId" cname="itClassifyId" ratio="4:6" type="hidden"/>
        <EF:EFInput ename="inqu_status-0-classifyNum" cname="分类编码" ratio="4:6" readonly="true" type="hidden"/>
        <EF:EFInput ename="inqu_status-0-classifyName" cname="分类名称" ratio="4:6" required="true"/>
        <EF:EFTreeInput ename="inqu_status-0-parentId" cname="上级类别" serviceName="ITFL01" methodName="queryItClassifyTree"
             valueField="id" textField="classifyName" hasChildren="isLeaf" readonly="true" required="true"
             root="{id: 'root', classifyName: '根节点'}" ratio="4:6">
        </EF:EFTreeInput>
        <EF:EFInput ename="inqu_status-0-remark" cname="备注" type="textarea" rows="3" ratio="4:6"/>
        <EF:EFInput ename="inqu_status-0-archiveFlag" cname="归档标记" ratio="4:6" type="hidden"/>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="确认" ename="SAVE" layout="3"></EF:EFButton>
            <EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
        </div>
    </EF:EFRegion>
</EF:EFPage>