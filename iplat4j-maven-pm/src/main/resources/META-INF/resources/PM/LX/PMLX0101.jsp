<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <EF:EFRegion id="resultPoject" title="项目属性">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-projectId" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-projectStatus" cname="状态" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-projectNo" cname="项目号" readOnly="true"/>
            <EF:EFInput ename="inqu_status-0-projectName" style="color:red;" cname="项目名称" required="true"/>
            <EF:EFSelect ename="inqu_status-0-projectProp" cname="项目性质" required="true">
                <EF:EFCodeOption codeName="pm.projectProp"/>
            </EF:EFSelect>
        </div>
        <div class="row">
            <EF:EFPopupInput ename="inqu_status-0-projectTypeNum" cname="项目类型" required="true"
                center="true" readOnly="true"
                containerId="ef_popup_projectTypeNum" popupWidth="1050" popupHeight="500" popupTitle="项目类型选择">
            </EF:EFPopupInput>
            <EF:EFSelect ename="inqu_status-0-projectPriNum" cname="项目优先级">
                <EF:EFCodeOption codeName="pm.projectPriority"/>
            </EF:EFSelect>
            <EF:EFPopupInput ename="inqu_status-0-contorId" cname="项目负责人"
                             popupTitle="人员选择" required="true" readOnly="true"
                             popupType="ServiceGrid" serviceName="PM0103" methodName="queryPerson" resultId="person"
                             valueField="workNo" textField="name"
                             columnEnames="workNo,name,deptName" columnCnames="工号,姓名,科室名称" />
        </div>
        <div class="row">
            <EF:EFPopupInput ename="inqu_status-0-undertakeDeptNum" cname="承办科室"
                             popupTitle="科室选择" required="false" readOnly="true"
                             popupType="ServiceGrid" serviceName="PM0101" methodName="queryDept" resultId="dept"
                             valueField="deptNum" textField="deptName"
                             columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
            <EF:EFInput ename="inqu_status-0-projectObjectDeptName" cname="项目科室" readOnly="true"/>
            <EF:EFInput ename="inqu_status-0-projectObjectDeptNum" cname="项目科室" type="hidden"/>
            <EF:EFPopupInput ename="inqu_status-0-projectObjectCons" cname="项目联络人"
                             popupTitle="人员选择" required="false" readOnly="true"
                             popupType="ServiceGrid" serviceName="PM0103" methodName="queryPerson" resultId="person"
                             valueField="workNo" textField="name"
                             columnEnames="workNo,name,deptName" columnCnames="工号,姓名,科室名称" />
        </div>
        <div class="row">
            <EF:EFSelect ename="inqu_status-0-projectProgress" cname="项目进度">
                <EF:EFCodeOption codeName="pm.projectProgress"/>
            </EF:EFSelect>
            <EF:EFDateSpan startName="inqu_status-0-startDate" endName="inqu_status-0-finishDate" startCname="开始日期" endCname="结束日期"
                startRatio="4:8" role="date" required="true" interval="15"/>
        </div>
        <div class="row">
            <EF:EFPopupInput ename="inqu_status-0-supplierNum" cname="供应商" popupTitle="供应商选择"
                             popupType="ServiceGrid" serviceName="PM0103" methodName="querySupplier" resultId="supplier"
                             valueField="supplierNum" textField="supplierName" readOnly="true"
                             columnEnames="supplierNum,supplierName" columnCnames="供应商编码,供应商名称" />
            <EF:EFInput ename="inqu_status-0-winbidExpense" cname="中标费用" data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字" />
            <EF:EFInput ename="inqu_status-0-finishExpense" cname="结算费用" data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字" />
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-projectContent" cname="项目内容" type="textarea"/>
            <EF:EFInput ename="inqu_status-0-projectRemark" cname="备注" type="textarea" />
        </div>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="确定" ename="SAVEPR" layout="0" ></EF:EFButton>
            <EF:EFButton cname="取消" ename="CANCEL" layout="0" ></EF:EFButton>
        </div>
    </EF:EFRegion>

    <%-- 项目类型选择弹框 --%>
    <div id="ef_popup_projectTypeNum" style="display: none">
        <EF:EFRegion id="inqu" title="查询条件">
            <div class="row">
                <EF:EFInput ename="inqu_status-0-typeCode" cname="类型编码" />
                <EF:EFInput ename="inqu_status-0-typeName" cname="类型名称" />
                <EF:EFButton ename="typeQuery" cname="查询"></EF:EFButton>
                <EF:EFButton ename="typeReset" cname="重置"></EF:EFButton>
                <EF:EFButton ename="typeSave" cname="确定" layout="20"></EF:EFButton>
            </div>
        </EF:EFRegion>
        <EF:EFRegion id="resultProjectType" title="结果集">
            <EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
                       autoFit="true" checkMode="single,row" readonly="true" rowNo="true"
                       isFloat="true" serviceName="PMPG02" queryMethod="queryPmTypeMsg">
                <EF:EFColumn ename="typeCode" cname="类型编码" width="12" align="center" enable="false"/>
                <EF:EFColumn ename="typeName" cname="类型名称" width="13" align="center" enable="false"/>
                <EF:EFColumn ename="typeRemark" cname="类型备注" width="13" align="center" enable="false"/>
                <EF:EFColumn ename="stageName" cname="绑定阶段名称" width="30" align="center" enable="false"/>
            </EF:EFGrid>
        </EF:EFRegion>
    </div>
    <%-- tab页 --%>
    <EF:EFTab id="tab-tab_grid">
        <div title="项目阶段">
            <EF:EFGrid blockId="resultA" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
                <EF:EFColumn ename="stageId" cname="项目阶段Id" align="center" width="100" hidden="true" enable="false"/>
                <EF:EFColumn ename="stageCode" cname="阶段编码" align="center" width="100" enable="false"/>
                <EF:EFColumn ename="stageName" cname="阶段名称" align="center" width="100" enable="false"/>
                <EF:EFColumn ename="stageRemark" cname="阶段备注" align="center" width="100" enable="false"/>
            </EF:EFGrid>
        </div>
        <div title="执行人员">
            <EF:EFGrid blockId="resultB" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryStaff">
                <EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false"/>
                <EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" enable="false"/>
                <EF:EFColumn ename="execStaffId" cname="执行人工号" width="100" enable="false"/>
                <EF:EFColumn ename="execStaffName" cname="执行人姓名" width="100" enable="false"/>
                <EF:EFColumn ename="number" cname="执行人联系电话" width="200" enable="false"/>
                <EF:EFColumn ename="deptNum" cname="执行人科室" width="200" hidden="true" enable="false"/>
                <EF:EFColumn ename="deptName" cname="执行人科室" width="200" enable="false"/>
            </EF:EFGrid>
        </div>
        <div title="知会人员">
            <EF:EFGrid blockId="resultC" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryKnow">
                <EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false"/>
                <EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" enable="false"/>
                <EF:EFColumn ename="notifyStaffId" cname="知会人工号" width="100" enable="false"/>
                <EF:EFColumn ename="notifyStaffName" cname="知会人姓名" width="100" enable="false"/>
                <EF:EFColumn ename="number" cname="知会人联系电话" width="200" enable="false"/>
                <EF:EFColumn ename="deptNum" cname="知会人科室" width="200" hidden="true" enable="false"/>
                <EF:EFColumn ename="deptName" cname="知会人科室" width="200" enable="false"/>
            </EF:EFGrid>
        </div>
        <div title="项目附件">
            <EF:EFGrid blockId="resultD" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryFile">
                <EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false"/>
                <EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" enable="false"/>
                <EF:EFColumn ename="attachId" cname="附件id" width="200" hidden="true" enable="false"/>
                <EF:EFColumn ename="attachPath" cname="附件路径" width="200" hidden="true" enable="false"/>
                <EF:EFColumn ename="attachName" cname="附件名称" width="200" enable="false"/>
                <EF:EFColumn ename="attachSize" cname="附件大小" width="100" enable="false"/>
                <EF:EFColumn ename="attachDesc" cname="附件说明" width="200" />
            </EF:EFGrid>
        </div>
    </EF:EFTab>

    <!-- 人员选择弹出窗口 -->
    <EF:EFWindow id="personChoose" url="" lazyload="true" width="50%" height="85%"></EF:EFWindow>

    <!-- 附件上传窗口 -->
    <EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%">
        <EF:EFRegion id="upload" title="文件上传">
            <EF:EFUpload ename="projectFile" docTag="pr_file" path="project"/>
        </EF:EFRegion>
    </EF:EFWindow>
</EF:EFPage>