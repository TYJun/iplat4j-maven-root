<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>独立任务评价详情</title>
<EF:EFPage>
    <EF:EFRegion>
        <div class="row">
            <div class="col-md-5">
                <div class="form-group">
                    <label data-for="impFlag" class="col-xs-4 control-label">
                        <span>是否紧急</span>
                    </label>
                    <div class="col-md-8">
                        <EF:EFInput ename="impFlag" cname="是" value="Y" type="radio" inline="true"/>
                        <EF:EFInput ename="impFlag" cname="否" value="N" type="radio" inline="true"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <EF:EFInput ename="type" cname="操作类型" colWidth="4" ratio="4:6" readonly="true" type="hidden"/>
            <EF:EFInput ename="itTaskId" cname="itTaskId" colWidth="4" ratio="4:6" readonly="true" type="hidden"/>
        </div>
        <div class="row">
            <EF:EFInput ename="reqStaffName" cname="需求人" colWidth="4" ratio="4:6" readonly="true"/>
            <EF:EFInput ename="reqTelNum" cname="需求电话" colWidth="4" ratio="4:6" readonly="true"/>
            <EF:EFInput ename="reqDeptName" cname="需求科室" colWidth="4" ratio="4:6" readonly="true"/>
        </div>
        <div class="row">
            <EF:EFInput ename="classifyName" cname="任务分类" colWidth="4" ratio="4:6" readonly="true"/>
            <EF:EFInput ename="serveDeptName" cname="服务科室" colWidth="4" ratio="4:6" readonly="true"/>
            <EF:EFInput ename="spotName" cname="服务地点" colWidth="4" ratio="4:6" readonly="true"/>
        </div>
        <div class="row">
            <EF:EFInput ename="recCreateTime" cname="创建时间" colWidth="4" ratio="4:6" readonly="true"/>
            <EF:EFInput ename="finishTime" cname="完工时间" colWidth="4" ratio="4:6" readonly="true"/>
            <EF:EFInput ename="finishManName" cname="完工人" colWidth="4" ratio="4:6" readonly="true"/>
        </div>
        <div class="row">
            <EF:EFInput ename="serveContent" cname="服务内容" type="textarea" colWidth="4" ratio="4:6" readonly="true"/>
            <EF:EFInput ename="remark" cname="备注" type="textarea" colWidth="4" ratio="4:6" readonly="true"/>
        </div>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
        </div>
    </EF:EFRegion>
</EF:EFPage>

