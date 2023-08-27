<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%--<script id="signImage-template" type="text/x-kendo-template">--%>
<%--    <div class='signImageDiv'>--%>
<%--        <img src='#=signImageStr#' class='signImage'></img>--%>
<%--    </div>--%>
<%--</script>--%>

<title>签名用户管理</title>

<EF:EFPage title="用户管理">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-userId" cname="工号"/>
            <EF:EFInput ename="inqu_status-0-userIdcard" cname="身份证号码"/>
            <EF:EFInput ename="inqu_status-0-userPhone" cname="电话号码"/>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="记录集" >
        <EF:EFGrid blockId="result" autoDraw="no">
<%--            <EF:EFColumn ename="userType" cname="用户类型"  align="center"/>--%>
            <EF:EFColumn ename="orgId" cname="机构编号" align="center"/>
            <EF:EFColumn ename="userId" cname="用户编号"  align="center"/>
            <EF:EFColumn ename="userName" cname="用户姓名"  align="center"/>
            <EF:EFColumn ename="userIdcard" cname="身份证号"  align="center"/>
            <EF:EFColumn ename="userPhone" cname="电话号码"  align="center"/>
            <EF:EFColumn ename="realNameStatus" cname="实名认证状态"  align="center"/>
            <EF:EFColumn ename="depName" cname="科室名称"  align="center"/>
            <EF:EFColumn ename="signatureImg" cname="手写签字图片"  align="center"/>
            <EF:EFColumn ename="status" cname="状态"  align="center"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>

<EF:EFWindow id="importUserInfoPopData" url="" modal="true" width="48%" height="40%" style="display:none;">
    <EF:EFRegion id="importUser" title="导入用户" fitHeight="false" >
        <div class="row" >
            <EF:EFSelect ename="userType" cname="用户类型" defaultValue="2" colWidth="6"  ratio="6:6">
                <EF:EFOption label="机构" value="1"></EF:EFOption>
                <EF:EFOption label="个人" value="2"></EF:EFOption>
            </EF:EFSelect>
        </div>
        <div class="row" >
            <EF:EFInput ename="userId" cname="用户编号" colWidth="6" required="true" ratio="6:6"/>
        </div>
        <div class="row" >
            <EF:EFInput ename="userName" cname="机构全称或个人真实姓名" required="true" colWidth="6" ratio="6:6" />
        </div>
        <div class="row" >
            <EF:EFInput ename="userIdcard" cname="社会信用代码号或身份证号" required="true" colWidth="6" ratio="6:6" />
        </div>
        <div class="row" >
            <EF:EFInput ename="depName" cname="科室（部门）全称" colWidth="6" ratio="6:6" required="true"/>
        </div>
        <div class="row" >
            <EF:EFInput ename="userPhone" cname="电话号码" colWidth="6" ratio="6:6" required="true"/>
        </div>
        <div class="row" >
            <EF:EFInput ename="userEmail" cname="个人邮箱" colWidth="6"  ratio="6:6"/>
        </div>

        <EF:EFButton ename="cancelImportUser" cname="取消"  class="k-button window-btn" style="float:right;" />
        <EF:EFButton ename="saveImportUser" cname="保存"  class="k-button window-btn" style="float:right;"/>
    </EF:EFRegion>
</EF:EFWindow>