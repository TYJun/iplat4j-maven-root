<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage>
    <EF:EFRegion id="result" title="登记" fitHeight="false">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-status" cname="状态" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-type" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-projectId" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-applyNo" cname="申请单号" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-recCreateTime" cname="制单时间" colWidth="5" ratio="4:8" readonly="true"
                        type="hidden"/>
            <EF:EFInput ename="inqu_status-0-recCreator" cname="制单人" colWidth="5" ratio="4:8" readonly="true"
                        type="hidden"/>
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-iconName" cname="标识名称" colWidth="5" ratio="4:8" required="true"/>

            <EF:EFInput ename="inqu_status-0-iconZhContent" cname="标识中文内容" colWidth="5"
                        ratio="4:8"  required="true"/>

        </div>
        <div class="row">
    <EF:EFInput ename="inqu_status-0-iconEnContent" cname="标识英文内容" colWidth="5"
                ratio="4:8"  required="true"/>

            <EF:EFTreeInput ename="inqu_status-0-classifyId" cname="标识分类"   colWidth="5" ratio="4:8"  readonly="true" required="true"
                            serviceName="HIBZ01" methodName="queryTree" textField="text"
                            valueField="label" hasChildren="leaf"
                            root="{label: 'root',text: '分类'}" popupTitile="上级分类" clear ="true" >
            </EF:EFTreeInput>
        </div>
        <div class="row">

            <EF:EFPopupInput ename="inqu_status-0-fixedPlace" cname="安装地方" colWidth="5" ratio="4:8" readonly="true" popupWidth = "550"
                             popupType="ServiceGrid" popupTitle="安装地方" required="true" serviceName="HISQ0101" methodName="querySpot" resultId="spot" autofit="true"
                             valueField="spotNum" textField="spotName" backFillFieldIds="spotName" backFillColumnIds="spotName" columnEnames="building,floor,spotName" columnCnames="楼,层,地点"/>
            <EF:EFInput ename="building" colWidth="5" ratio="4:8" cname="楼"  center="true">

            </EF:EFInput>


            <EF:EFInput ename="inqu_status-0-spotId" type="hidden" cname="地点id" />
            <EF:EFInput ename="inqu_status-0-spotCode" type="hidden" cname="地点编码" />
            <EF:EFInput ename="inqu_status-0-spotName" type="hidden" cname="地点名称" />
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-floor" colWidth="5" ratio="4:8" cname="层" >

            </EF:EFInput>

            <EF:EFInput ename="inqu_status-0-iconAmount" cname="数量" colWidth="5"
                        ratio="4:8" type="text"  required="true"/>



        </div>

        <div class="row">
            <EF:EFInput ename="inqu_status-0-spotDesc" cname="安装地点说明" colWidth="5"
                        ratio="4:8" type="text" required="true"/>

<%--            <EF:EFDatePicker ename="inqu_status-0-installDate" cname="安装日期"--%>
<%--                             format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" readonly="true"--%>
<%--                             bindId="installDate" colWidth="5" ratio="4:8" type="hidden"/>--%>



        </div>

        <div class="row">
            <EF:EFInput ename="inqu_status-0-applyReason" cname="申请理由" colWidth="5"
                        ratio="4:8" type="text" required="true"/>

<%--            <EF:EFPopupInput ename="inqu_status-0-managerName" cname="管理员" readonly="true"--%>
<%--                             popupTitle="员工列表" popupType="ServiceGrid" serviceName="HISQ0101"--%>
<%--                             methodName="queryAdmin" resultId="iconAdmin" valueField="workNo"--%>
<%--                             textField="name" colWidth="5" ratio="4:8"--%>
<%--                             columnEnames="workNo,name" columnCnames="员工工号,员工姓名" type="hidden"/>--%>


        </div>
        <div class="row">
            <EF:EFPopupInput ename="inqu_status-0-applyDeptName" cname="申请科室"
                             popupTitle="合同所属部门" popupType="ServiceGrid" serviceName="HISQ0101"
                             methodName="queryIconCostNum" resultId="iconDept" readonly="true"
                             valueField="iconDeptNum" textField="iconDeptName" colWidth="5"
                             ratio="4:8" columnEnames="iconDeptNum,iconDeptName"
                             columnCnames="科室编码,科室名称"/>

            <EF:EFInput ename="inqu_status-0-emo1" cname="设计驳回理由" colWidth="5" ratio="4:8" type="text" />
        </div>
        <div class="row">

        <EF:EFInput ename="inqu_status-0-remark" cname="备注" colWidth="5"
                    ratio="4:8" type="text" required="true"/>


        </div>

        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="通过" ename="PASS" layout="0"></EF:EFButton>
            <EF:EFButton cname="驳回" ename="OVERRULE" layout="0"></EF:EFButton>
            <EF:EFButton cname="取消" ename="CANCEL" layout="0"></EF:EFButton>
        </div>
    </EF:EFRegion>

    <!-- 页面分页 -->
        <div title="合同记事内容">
            <EF:EFGrid blockId="resultD" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryFile">
                <EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
                <EF:EFColumn ename="relationId" cname="relationId" width="100" hidden="true"/>
                <EF:EFColumn ename="docId" cname="附件id" width="200" hidden="true"/>
                <EF:EFColumn ename="filePath" cname="附件路径" width="200" hidden="true"/>
                <EF:EFColumn ename="fileName" cname="附件名称" width="200"/>
                <EF:EFColumn ename="fileSize" cname="附件大小" width="100"/>
                <EF:EFColumn ename="remark" cname="附件说明" width="200"/>
                <EF:EFColumn ename="recCreator" cname="上传人" width="200"/>
                <EF:EFColumn ename="recCreateTime" cname="上传时间" width="200"/>
            </EF:EFGrid>
        </div>
    <!-- 附件上传窗口 -->
    <EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%" title="附件上传">
        <EF:EFRegion id="upload" title="文件上传">
            <EF:EFUpload ename="contentFile" docTag="co_file" path="Content"/>
        </EF:EFRegion>
    </EF:EFWindow>
</EF:EFPage>

<script type="text/javascript">
</script>