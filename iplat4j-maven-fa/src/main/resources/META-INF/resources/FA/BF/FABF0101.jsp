<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="资产报废录入">
    <EF:EFRegion id="info" head="hidden">
        <div class="row">
            <EF:EFInput ename="type" cname="类型" readonly="true" type="hidden"/>
            <EF:EFInput ename="scrappedNo" cname="报废单号" readonly="true" type="hidden"/>
        </div>
        <div class="row">
            <div id="identifyDeptNum" style="display: none">
                <EF:EFTreeInput ename="info-0-parentId" cname="鉴定科室" serviceName="FAZN01" methodName="queryFaDeptTree"
                                valueField="id" textField="deptName" hasChildren="isLeaf" readonly="true" required="true"
                                root="{id: 'root', deptName: '根节点'}" rows="3" colWidth="12" ratio="2:9">
                </EF:EFTreeInput>
            </div>
            <div id="applyReason" style="display: none">
                <EF:EFInput ename="info-0-applyFileCode" cname="applyFileCode" type="hidden"></EF:EFInput>
                <EF:EFInput ename="info-0-applyReason" cname="申请报废原因" rows="3" colWidth="9"
                            ratio="2:9" type="textarea" required="true" placeholder="报废申请不得过于简洁或复杂，请填写10~200字，谢谢" minLength="10" maxLength="200"/>
            </div>
            <div class="row">
                <div class="col-md-1">
                    <div class="form-group">
                            <%--						<label for="applyPic" class="col-xs-2 control-label">电子签名</label>--%>
                        <div class="col-xs-10">
                            <span id="applyPic"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div id="identifyReason" style="display: none">
                <EF:EFInput ename="info-0-identifyFileCode" cname="identifyFileCode" type="hidden"></EF:EFInput>
                <EF:EFInput ename="info-0-identifyReason" cname="技术鉴定意见" rows="3" colWidth="9"
                            ratio="2:9" type="textarea" required="true" placeholder="不能超过200字" maxLength="200"/>
            </div>
            <div class="row">
                <div class="col-md-1">
                    <div class="form-group">
                            <%--						<label for="confirmPic" class="col-xs-2 control-label">电子签名</label>--%>
                        <div class="col-xs-10">
                            <span id="identifyPic"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div id="functionReason" style="display: none">
                <EF:EFInput ename="info-0-functionFileCode" cname="functionFileCode" type="hidden"></EF:EFInput>
                <EF:EFInput ename="info-0-functionReason" cname="归口管理部门意见" rows="3" colWidth="9"
                            ratio="2:9" type="textarea" required="true" placeholder="不能超过200字" maxLength="200"/>
            </div>
            <div class="row">
                <div class="col-md-1">
                    <div class="form-group">
                            <%--						<label for="confirmPic" class="col-xs-2 control-label">电子签名</label>--%>
                        <div class="col-xs-10">
                            <span id="functionPic"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div id="assetReason" style="display: none">
                <EF:EFInput ename="info-0-assetFileCode" cname="assetFileCode" type="hidden"></EF:EFInput>
                <EF:EFInput ename="info-0-assetReason" cname="资产管理科意见" rows="3" colWidth="9"
                            ratio="2:9" type="textarea" required="true" placeholder="不能超过200字" maxLength="200"/>
            </div>
            <div class="row">
                <div class="col-md-1">
                    <div class="form-group">
                            <%--						<label for="confirmPic" class="col-xs-2 control-label">电子签名</label>--%>
                        <div class="col-xs-10">
                            <span id="assetPic"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="button-region" id="buttonDiv">
            <div id="enter" style="display: none">
                <EF:EFButton cname="保存" ename="SAVE" layout="3"></EF:EFButton>
                <EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
            </div>
            <div id="apply" style="display: none">
                <EF:EFButton cname="提交" ename="SUMBIT" layout="3"></EF:EFButton>
                <EF:EFButton cname="关闭" ename="applyClose" layout="3"></EF:EFButton>
            </div>
            <div id="identify" style="display: none">
                <EF:EFButton cname="通过" ename="identifyPass" layout="3"></EF:EFButton>
                <EF:EFButton cname="驳回" ename="identifyReject" layout="3"></EF:EFButton>
                <EF:EFButton cname="关闭" ename="identifyClose" layout="3"></EF:EFButton>
            </div>
            <div id="function" style="display: none">
                <EF:EFButton cname="通过" ename="functionPass" layout="3"></EF:EFButton>
                <EF:EFButton cname="驳回" ename="functionReject" layout="3"></EF:EFButton>
                <EF:EFButton cname="关闭" ename="functionClose" layout="3"></EF:EFButton>
            </div>
            <div id="asset" style="display: none">
                <EF:EFButton cname="通过" ename="assetPass" layout="3"></EF:EFButton>
                <EF:EFButton cname="驳回" ename="assetReject" layout="3"></EF:EFButton>
                <EF:EFButton cname="关闭" ename="assetClose" layout="3"></EF:EFButton>
            </div>
        </div>
    </EF:EFRegion>
    <EF:EFGrid blockId="result" autoDraw="no" autoBind="true" readonly="true" rowNo="true" checkMode="multiple,row">
        <EF:EFColumn ename="faInfoId" cname="faInfoId"  align="center" width="200" hidden="true"/>
        <EF:EFColumn ename="goodsNum" cname="资产编码"  align="center" width="200" enable="false"/>
        <EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="200" enable="false"/>
        <EF:EFColumn ename="goodsClassifyName" cname="资产类别"  align="center" width="200" enable="false"/>
        <EF:EFColumn ename="goodsTypeName" cname="类组"  align="center" width="200" enable="false"/>
        <EF:EFColumn ename="model" cname="型号规格"  align="center" width="200" enable="false"/>
        <EF:EFColumn ename="statusCode" cname="资产状态"   align="center" width="200" enable="false"/>
        <EF:EFColumn ename="rfidCode" cname="RFID"  align="center" width="200" enable="false"/>
        <EF:EFColumn ename="manufacturer" cname="制造厂商"  align="center" width="200" enable="false"/>
        <EF:EFColumn ename="surpName" cname="供应商"   align="center" width="200" enable="false"/>
        <EF:EFColumn ename="buyDate" cname="购入日期"   align="center" width="200" enable="false"/>
        <EF:EFColumn ename="useDate" cname="使用日期"   align="center" width="200" enable="false"/>
        <EF:EFColumn ename="deptName" cname="所属科室"   align="center" width="200" enable="false"/>
        <EF:EFColumn ename="build" cname="楼"   align="center" width="200" enable="false"/>
        <EF:EFColumn ename="floor" cname="层"   align="center" width="200" enable="false"/>
        <EF:EFColumn ename="installLocation" cname="地点"   align="center" width="200" enable="false"/>
        <EF:EFColumn ename="buyCost" cname="资产原值"   align="center" width="200" enable="false"/>
        <EF:EFColumn ename="useYears" cname="使用年限"   align="center" width="200" enable="false"/>
        <%--			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" />--%>
        <EF:EFColumn ename="recCreateName" cname="创建人"   align="center" width="200" enable="false"/>
        <EF:EFColumn ename="remark" cname="备注"  align="center" width="200" enable="false"/>
        <EF:EFColumn ename="inAccountStatus" cname="是否审批启用"  align="center" width="200" enable="false" hidden="true"/>
        <EF:EFColumn ename="operationType" cname="出库类型"  align="center" width="200" enable="false" hidden="true"/>
        <EF:EFColumn ename="lockFlag" cname="变更状态"  align="center" hidden="true"/>
    </EF:EFGrid>
</EF:EFPage>