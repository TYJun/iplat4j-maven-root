<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>资产类别管理</title>
<EF:EFPage>
    <span id="message"></span>
    <div class="row">
        <div class="col-md-2">
            <EF:EFRegion id="FaTypeMenu" title="资产类别名称">
                <EF:EFTree id="menu" valueField="id" textField="typeName" hasChildren="isLeaf"
                serviceName="FALB01" methodName="queryFaTypeTree" style="height:640px;"/>
            </EF:EFRegion>
        </div>
        <div class="col-md-10">
            <EF:EFRegion id="inqu" title="查询条件">
                <div class="row">
                    <EF:EFInput ename="typeId" cname="typeId" type="hidden"/>
                    <EF:EFInput ename="typeName" cname="typeName" type="hidden"/>
                    <EF:EFInput ename="inqu_status-0-faTypeId" cname="资产类别ID" type="hidden" value="root"/>
                    <EF:EFInput ename="inqu_status-0-typeCode" cname="资产类别编码" colWidth="4" ratio="5:7"/>
                    <EF:EFInput ename="inqu_status-0-typeName" cname="资产类别名称" colWidth="4" ratio="5:7"/>
                    <EF:EFInput ename="inqu_status-0-parentName" cname="上级资产类别名称" type="hidden" colWidth="4" ratio="5:7"/>
                    <EF:EFInput ename="inqu_status-0-codeRule" cname="资产类别编码规则" colWidth="4" ratio="5:7"/>
                    <EF:EFInput ename="inqu_status-0-useYearsS" cname="使用年限起" colWidth="4" ratio="5:7"/>
                    <EF:EFInput ename="inqu_status-0-useYearsE" cname="使用年限止" colWidth="4" ratio="5:7"/>
                    <EF:EFInput ename="inqu_status-0-remark" cname="备注" colWidth="4" ratio="5:7"/>
                </div>
                <div class="button-region" id="buttonDiv">
                    <EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
                    <EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
                </div>
            </EF:EFRegion>
            <EF:EFRegion id="result" title="资产类别信息">
                <EF:EFGrid blockId="result" autoDraw="no" readonly="true" checkMode="single,row" rowNo="true" height="500px">
                    <EF:EFColumn ename="faTypeId" cname="资产类别Id" width="100" enable="false" align="center" hidden="true"/>
                    <EF:EFColumn ename="typeCode" cname="资产类别编码" width="80" enable="false" align="center"/>
                    <EF:EFColumn ename="typeName" cname="资产类别名称" width="100" enable="false" align="center"/>
                    <EF:EFColumn ename="parentId" cname="上级资产类别ID" width="100" enable="false" align="center" hidden="true"/>
                    <EF:EFColumn ename="parentName" cname="上级资产类别名称" width="100" enable="false" align="center"/>
                    <EF:EFColumn ename="level" cname="资产类别层级" width="100" enable="false" align="center" hidden="true"/>
                    <EF:EFColumn ename="useYears" cname="使用年限(年)" width="80" enable="false" align="center"/>
                    <EF:EFColumn ename="codeRule" cname="资产类别编码规则" width="70" enable="false" align="center"/>
                    <EF:EFColumn ename="sortNo" cname="排序" width="30" enable="false" align="center" />
                    <EF:EFColumn ename="remark" cname="备注" width="150" enable="false" align="center"/>
                </EF:EFGrid>
            </EF:EFRegion>
            <EF:EFWindow id="popData" url="" lazyload="true" width="40%" height="60%" title="资产类别录入" modal="true" />
            <EF:EFWindow id="execlImport" url="" lazyload="true" refresh="true" width="40%" height="40%" title="资产类别模板导入">
                <EF:EFRegion id="FADAUpload" head="hidden">
                    <h4>资产类别导入规则：</h4>
                    <li>资产类别编码（必填; 字符长度小于等于20）</li>
                    <li>资产类别名称（必填; 字符长度小于等于100）</li>
                    <li>上级资产类别编码（必填; 取值为系统中已录入或文档中存在的资产类别编码）</li>
                    <br/>
                    <form id="excelForm"  enctype="multipart/form-data">
                        文件上传<input id="excelFile" type="file" name="file" ><br />
                    </form>
                    <button id="download">模板下载</button>
                </EF:EFRegion>
                <div class="button-region" id="buttonDiv" style="float: right">
                    <EF:EFButton cname="提交" ename="IMPORTSUBMIT" layout="0" ></EF:EFButton>
                    <EF:EFButton cname="关闭" ename="IMPORTCLOSE" layout="0" ></EF:EFButton>
                </div>
            </EF:EFWindow>
        </div>
    </div>
    <script type="text/javascript" src="${ctx}/FA/common/js/fa-keydown.js"></script>
</EF:EFPage>