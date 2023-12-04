<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>资产报废</title>
<EF:EFPage title="资产报废">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <div id="only">
                <EF:EFInput ename="inqu_status-0-goodsNum" cname="资产编码" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-goodsName" cname="资产名称" colWidth="3"/>
            </div>
            <div id="one">
                <EF:EFInput ename="inqu_status-0-surpName" cname="供应商" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-remark" cname="备注" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-buyCostS" colWidth="3" ratio="4:8" cname="原值范围起"/>
                <EF:EFInput ename="inqu_status-0-buyCostE" colWidth="3" ratio="4:8" cname="原值范围止"/>
                <EF:EFInput ename="inqu_status-0-netAssetValueS" colWidth="3" ratio="4:8" cname="净值范围起"/>
                <EF:EFInput ename="inqu_status-0-netAssetValueE" colWidth="3" ratio="4:8" cname="净值范围止"/>
                <EF:EFDateSpan startName="inqu_status-0-buyDateS" startCname="购入日期起"
                               endName="inqu_status-0-buyDateE" endCname="购入日期止"
                               ratio="3:3" startRatio="4:8" endRatio="4:8" readonly="true"/>
                <EF:EFDateSpan startName="inqu_status-0-useDateS" startCname="使用日期起"
                               endName="inqu_status-0-useDateE" endCname="使用日期止"
                               ratio="3:3" startRatio="4:8" endRatio="4:8" readonly="true"/>
                    <%--			<EF:EFInput ename="inqu_status-0-useYear" colWidth="3" ratio="4:8" cname="使用年限"/>--%>
<%--                <EF:EFInput ename="inqu_status-0-deptName" cname="所属科室" colWidth="3"/>--%>
                <EF:EFMultiSelect ename="inqu_status-0-deptName" cname="所属科室" colWidth="3" ratio="4:8"
                                  serviceName="FADA01" queryMethod="queryDept" filter="contains">
                    <EF:EFOptions blockId="dept" textField="deptName" valueField="deptName"/>
                </EF:EFMultiSelect>
<%--                <EF:EFTreeInput ename="inqu_status-0-goodsClassifyCode" cname="资产类别" serviceName="FALB01"--%>
<%--                                methodName="queryFaTypeTree"--%>
<%--                                valueField="id" textField="typeName" hasChildren="isLeaf" readonly="true"--%>
<%--                                root="{id: 'root', typeName: '根节点'}" colWidth="3" ratio="4:8">--%>
<%--                </EF:EFTreeInput>--%>
                <EF:EFInput ename="inqu_status-0-goodsClassifyCode" colWidth="3" ratio="4:8" cname="资产类别"/>
                <EF:EFInput ename="inqu_status-0-goodsTypeCode" colWidth="3" ratio="4:8" cname="类组"/>
                <EF:EFSelect ename="inqu_status-0-fundingSourceNum" cname="资金来源" colWidth="3" ratio="4:8">
                    <EF:EFOption label="--请选择--" value=""/>
                    <EF:EFCodeOption codeName="wilp.mp.source"/>
                </EF:EFSelect>
<%--                <EF:EFSelect ename="inqu_status-0-goodsTypeCode" resultId="result" serviceName="FALB01"--%>
<%--                             methodName="faTypeEFSelect" filter="contains"--%>
<%--                             colWidth="3" ratio="4:8" optionLabel="--请选择--" cname="类别名称" textField="text"--%>
<%--                             valueField="value"/>--%>
            </div>
            <div id="other" style="display: none">
                <EF:EFInput ename="inqu_status-0-scrappedNo" cname="报废单号" colWidth="3" type="hidden"/>
                <EF:EFInput ename="inqu_status-0-applyDeptName" cname="申请科室" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-applyReason" cname="申请理由" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-identifyDeptName" cname="技术科" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-identifyReason" cname="技术指导" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-functionDeptName" cname="归口科室" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-functionReason" cname="归口原因" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-assetDeptName" cname="资产科" colWidth="3"/>
                <EF:EFInput ename="inqu_status-0-assetReason" cname="审批答复" colWidth="3"/>
            </div>
        </div>
        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
            <EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
        </div>
    </EF:EFRegion>
    <EF:EFTab id="FaDaTab">
        <div title="报废申请">
            <EF:EFGrid blockId="resultA" autoDraw="no" autoBind="true" rowNo="true" readonly="true"
                       checkMode="multiple,row" queryMethod="confirmedQuery" height="418px" sort="setted">
                <EF:EFColumn ename="faInfoId" cname="faInfoId" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="120" enable="false" displayType="url" sort="true"/>
                <EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="180" sort="true"/>
                <EF:EFColumn ename="spec" cname="型号规格" align="center" width="150" sort="true"/>
                <EF:EFColumn ename="deptName" cname="所属科室" align="center" width="150" sort="true"/>
                <EF:EFColumn ename="buyCost" cname="资产原值" align="center" width="130" sort="true"/>
                <EF:EFColumn ename="netAssetValue" cname="资产净值" align="center" width="130" sort="true"/>
                <EF:EFColumn ename="useDate" cname="使用日期" align="center" width="130" sort="true"/>
                <EF:EFColumn ename="room" cname="具体位置" align="center" width="150" sort="true"/>
                <EF:EFColumn ename="remark" cname="备注" align="center" width="150" sort="true"/>
                <EF:EFColumn ename="outRemark" cname="出库备注"  align="center" width="150" sort="true"/>
                <EF:EFColumn ename="statusCode" cname="资产状态" align="center" width="200" sort="true"/>
                <%--				<EF:EFColumn ename="build" cname="楼"   align="center" width="200"/>--%>
                <%--				<EF:EFColumn ename="floor" cname="层"   align="center" width="200"/>--%>
                <EF:EFColumn ename="installLocation" cname="地点" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="goodsClassifyName" cname="资产类别" align="center" width="200"/>
                <EF:EFColumn ename="goodsTypeCode" cname="资产类别名称" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="goodsTypeName" cname="类组" align="center" width="200"/>
                <EF:EFColumn ename="manufacturer" cname="制造厂商" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="surpName" cname="供应商" align="center" width="200"/>
                <EF:EFColumn ename="buyDate" cname="购入日期" align="center" width="200"/>
                <EF:EFColumn ename="useYears" cname="使用年限" align="center" width="200"/>
                <%--			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" />--%>
                <EF:EFColumn ename="recCreateName" cname="创建人" align="center" width="200"/>
                <EF:EFColumn ename="rfidCode" cname="RFID" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="cardStatus" cname="是否发卡" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="lockFlag" cname="变更状态" align="center" hidden="true"/>
            </EF:EFGrid>
        </div>
        <div title="科室负责人审批">
            <EF:EFGrid blockId="resultG" autoDraw="no" autoBind="true" rowNo="true" readonly="true"
                       checkMode="multiple,row" queryMethod="ApplyDeptQuery" height="460px" sort="setted">
                <EF:EFColumn ename="id" cname="id" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="detailId" cname="detailId" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="120" enable="false" displayType="url" sort="true" alias="fsd.goods_num"/>
                <EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="180" sort="true" alias="fsd.goods_name"/>
                <EF:EFColumn ename="spec" cname="型号规格" align="center" width="150" sort="true" alias="fi.spec"/>
                <EF:EFColumn ename="applyDeptName" cname="申请科室" align="center" width="150" sort="true" alias="fs.apply_dept_name"/>
                <EF:EFColumn ename="scrapStatus" cname="审批状态" align="center" width="150" sort="true" alias="fsd.scrap_detail_status"/>
                <EF:EFColumn ename="applyReason" cname="申请理由" align="center" width="150" sort="true" alias="fs.apply_reason"/>
                <EF:EFColumn ename="applyTime" cname="申请时间" align="center" width="150" sort="true" alias="fs.apply_time"/>
                <EF:EFColumn ename="assignmentReason" cname="驳回原因" align="center" width="150" sort="true" alias="fsd.assignment_reason"/>
                <EF:EFColumn ename="assignmentTime" cname="驳回时间" align="center" width="150" sort="true" alias="fsd.assignment_time"/>
                <EF:EFColumn ename="goodsClassifyCode" cname="资产类别编码" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="goodsClassifyName" cname="资产类别" align="center" width="150"/>
                <EF:EFColumn ename="applyPerson" cname="申请人" align="center" width="150"/>
                <EF:EFColumn ename="scrappedNo" cname="报废单号" align="center" width="200" displayType="url"
                             hidden="true"/>
            </EF:EFGrid>
        </div>
        <div title="鉴定科室选择">
            <EF:EFGrid blockId="resultB" autoDraw="no" autoBind="true" rowNo="true" readonly="true"
                       checkMode="multiple,row" queryMethod="ApplyQuery" height="460px" sort="setted">
                <EF:EFColumn ename="id" cname="id" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="detailId" cname="detailId" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="120" enable="false" displayType="url" sort="true" alias="fsd.goods_num"/>
                <EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="180" sort="true" alias="fsd.goods_name"/>
                <EF:EFColumn ename="spec" cname="型号规格" align="center" width="150" sort="true" alias="fi.spec"/>
                <EF:EFColumn ename="applyDeptName" cname="申请科室" align="center" width="150" sort="true" alias="fs.apply_dept_name"/>
                <EF:EFColumn ename="scrapStatus" cname="审批状态" align="center" width="150" sort="true" alias="fsd.scrap_detail_status"/>
                <EF:EFColumn ename="applyReason" cname="申请理由" align="center" width="150" sort="true" alias="fs.apply_reason"/>
                <EF:EFColumn ename="applyTime" cname="申请时间" align="center" width="150" sort="true" alias="fs.apply_time"/>
                <EF:EFColumn ename="goodsClassifyCode" cname="资产类别编码" align="center" width="200" hidden="true"/>
                <EF:EFColumn ename="goodsClassifyName" cname="资产类别" align="center" width="150"/>
                <EF:EFColumn ename="applyPerson" cname="申请人" align="center" width="150"/>
                <EF:EFColumn ename="scrappedNo" cname="报废单号" align="center" width="200" displayType="url"
                             hidden="true"/>
            </EF:EFGrid>
        </div>
        <div title="技术鉴定">
            <EF:EFGrid blockId="resultC" autoDraw="no" autoBind="true" rowNo="true" readonly="true"
                       checkMode="multiple,row" queryMethod="identifyQuery" height="460px" sort="setted">
                <EF:EFColumn ename="id" cname="id" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="detailId" cname="detailId" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="120" enable="false" displayType="url" sort="true" alias="fsd.goods_num"/>
                <EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="180" sort="true" alias="fsd.goods_name"/>
                <EF:EFColumn ename="spec" cname="型号规格" align="center" width="150" sort="true" alias="fi.spec"/>
                <EF:EFColumn ename="applyDeptName" cname="申请科室" align="center" width="150" sort="true" alias="fs.apply_dept_name"/>
                <EF:EFColumn ename="scrapStatus" cname="审批状态" align="center" width="150" sort="true" alias="fsd.scrap_detail_status"/>
                <EF:EFColumn ename="applyReason" cname="申请理由" align="center" width="150" sort="true" alias="fs.apply_reason"/>
                <EF:EFColumn ename="applyTime" cname="申请时间" align="center" width="150" sort="true" alias="fs.apply_time"/>
                <EF:EFColumn ename="applyPerson" cname="申请人" align="center" width="150" sort="true" alias="fs.apply_person"/>
                <EF:EFColumn ename="identifyDeptName" cname="技术科" align="center" width="150" sort="true" alias="fsd.identify_dept_name"/>
                <EF:EFColumn ename="functionDeptName" cname="归口科室" align="center" width="150" sort="true" alias="fsd.function_dept_name"/>
                <EF:EFColumn ename="scrappedNo" cname="报废单号" align="center" width="150" displayType="url"
                             hidden="true"/>
            </EF:EFGrid>
        </div>
        <div title="归口管理">
            <EF:EFGrid blockId="resultD" autoDraw="no" autoBind="true" rowNo="true" readonly="true"
                       checkMode="multiple,row" queryMethod="functionQuery" height="460px" sort="setted">
                <EF:EFColumn ename="id" cname="id" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="detailId" cname="detailId" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="120" enable="false" displayType="url" sort="true" alias="fsd.goods_num"/>
                <EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="180" sort="true" alias="fsd.goods_name"/>
                <EF:EFColumn ename="spec" cname="型号规格" align="center" width="150" sort="true" alias="fi.spec"/>
                <EF:EFColumn ename="applyDeptName" cname="申请科室" align="center" width="150" sort="true" alias="fs.apply_dept_name"/>
                <EF:EFColumn ename="scrapStatus" cname="审批状态" align="center" width="150" sort="true" alias="fsd.scrap_detail_status"/>
                <EF:EFColumn ename="applyReason" cname="申请理由" align="center" width="150" sort="true" alias="fs.apply_reason"/>
                <EF:EFColumn ename="applyPerson" cname="申请人" align="center" width="150" sort="true" alias="fs.apply_person"/>
                <EF:EFColumn ename="applyTime" cname="申请时间" align="center" width="150" sort="true" alias="fs.apply_time"/>
                <EF:EFColumn ename="identifyDeptName" cname="技术科" align="center" width="150" sort="true" alias="fsd.identify_dept_name"/>
                <EF:EFColumn ename="identifyPerson" cname="技术员" align="center" width="150" sort="true" alias="fsd.identify_person"/>
                <EF:EFColumn ename="identifyTime" cname="技术确认时间" align="center" width="150" sort="true" alias="fsd.identify_time"/>
                <EF:EFColumn ename="identifyReason" cname="技术指导" align="center" width="150" sort="true" alias="fsd.identify_reason"/>
                <EF:EFColumn ename="functionDeptName" cname="归口科室" align="center" width="150" sort="true" alias="fsd.function_dept_name"/>
            </EF:EFGrid>
        </div>
        <div title="资产管理">
            <EF:EFGrid blockId="resultE" autoDraw="no" autoBind="true" rowNo="true" readonly="true"
                       checkMode="multiple,row" queryMethod="assetQuery" height="460px" sort="setted">
                <EF:EFColumn ename="id" cname="id" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="detailId" cname="detailId" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="120" enable="false" displayType="url" sort="true" alias="fsd.goods_num"/>
                <EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="180" sort="true" alias="fsd.goods_name"/>
                <EF:EFColumn ename="spec" cname="型号规格" align="center" width="150" sort="true" alias="fi.spec"/>
                <EF:EFColumn ename="applyDeptName" cname="申请科室" align="center" width="150" sort="true" alias="fs.apply_dept_name"/>
                <EF:EFColumn ename="scrapStatus" cname="审批状态" align="center" width="150" sort="true" alias="fsd.scrap_detail_status"/>
                <EF:EFColumn ename="applyReason" cname="申请理由" align="center" width="150" sort="true" alias="fs.apply_reason"/>
                <EF:EFColumn ename="applyPerson" cname="申请人" align="center" width="150" sort="true" alias="fs.apply_person"/>
                <EF:EFColumn ename="applyTime" cname="申请时间" align="center" width="150" sort="true" alias="fs.apply_time"/>
                <EF:EFColumn ename="identifyDeptName" cname="技术科" align="center" width="150" sort="true" alias="fsd.identify_dept_name"/>
                <EF:EFColumn ename="identifyPerson" cname="技术员" align="center" width="150" sort="true" alias="fsd.identify_person"/>
                <EF:EFColumn ename="identifyTime" cname="技术确认时间" align="center" width="150" sort="true" alias="fsd.identify_time"/>
                <EF:EFColumn ename="identifyReason" cname="技术指导" align="center" width="150" sort="true" alias="fsd.identify_reason"/>
                <EF:EFColumn ename="functionDeptName" cname="归口科室" align="center" width="150" sort="true" alias="fsd.function_dept_name"/>
                <EF:EFColumn ename="functionPerson" cname="归口人" align="center" width="150" sort="true" alias="fsd.function_person"/>
                <EF:EFColumn ename="functionTime" cname="归口通过时间" align="center" width="150" sort="true" alias="fsd.function_time"/>
                <EF:EFColumn ename="functionReason" cname="归口原因" align="center" width="150" sort="true" alias="fsd.function_reason"/>
            </EF:EFGrid>
            <!-- 批量鉴定 -->
            <EF:EFWindow id="identify" title="批量鉴定" url="" lazyload="true" width="60%" height="40%">
                <EF:EFRegion id="identify" head="hidden">
                    <div class="row">
                        <EF:EFInput ename="info-0-identifyFileCode" cname="鉴定电子签名fileCode" colWidth="12" readonly="true" type="hidden"/>
                        <EF:EFInput ename="identifyReason" type="textarea" colWidth="12" ratio="2:10" cname="鉴定意见" rows="5"
                                    require="true" maxLength="200"/>
                    </div>
                    <div class="row">
                        <div class="col-md-10">
                            <div class="form-group">
                                <label for="identifyPic" class="col-xs-2 control-label"></label>
                                <div class="col-xs-10">
                                    <span id="identifyPic"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="button-region" id="buttonDiv">
                        <EF:EFButton cname="通过" ename="pass" layout="0"></EF:EFButton>
                        <EF:EFButton cname="驳回" ename="reject" layout="0"></EF:EFButton>
                        <EF:EFButton cname="取消" ename="cancel" layout="0"></EF:EFButton>
                    </div>
                </EF:EFRegion>
            </EF:EFWindow>
            <!-- 批量归口 -->
            <EF:EFWindow id="function" title="批量归口" url="" lazyload="true" width="60%" height="40%">
                <EF:EFRegion id="function" head="hidden">
                    <div class="row">
                        <EF:EFInput ename="info-0-functionFileCode" cname="归口电子签名fileCode" colWidth="12" readonly="true" type="hidden"/>
                        <EF:EFInput ename="functionReason" type="textarea" colWidth="12" ratio="2:10" cname="归口意见" rows="5"
                                    maxLength="200"/>
                    </div>
                    <div class="row">
                        <div class="col-md-10">
                            <div class="form-group">
                                <label for="functionPic" class="col-xs-2 control-label"></label>
                                <div class="col-xs-10">
                                    <span id="functionPic"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="button-region" id="buttonDiv">
                        <EF:EFButton cname="通过" ename="functionPass" layout="0"></EF:EFButton>
                        <EF:EFButton cname="驳回" ename="functionReject" layout="0"></EF:EFButton>
                        <EF:EFButton cname="取消" ename="functionCancel" layout="0"></EF:EFButton>
                    </div>
                </EF:EFRegion>
            </EF:EFWindow>
            <!-- 批量审批 -->
            <EF:EFWindow id="accept" title="批量审批" url="" lazyload="true" width="60%" height="40%">
                <EF:EFRegion id="accept" head="hidden">
                    <div class="row">
                        <EF:EFInput ename="info-0-assetFileCode" cname="审批电子签名fileCode" colWidth="12" readonly="true" type="hidden"/>
                        <EF:EFInput ename="assetReason" type="textarea" colWidth="12" ratio="2:10" cname="审批意见" rows="5"
                                    maxLength="200"/>
                    </div>
                    <div class="row">
                        <div class="col-md-10">
                            <div class="form-group">
                                <label for="assetPic" class="col-xs-2 control-label"></label>
                                <div class="col-xs-10">
                                    <span id="assetPic"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="button-region" id="buttonDiv">
                        <EF:EFButton cname="通过" ename="acceptPass" layout="0"></EF:EFButton>
                        <EF:EFButton cname="驳回" ename="acceptReject" layout="0"></EF:EFButton>
                        <EF:EFButton cname="取消" ename="acceptCancel" layout="0"></EF:EFButton>
                    </div>
                </EF:EFRegion>
            </EF:EFWindow>
        </div>
        <div title="审批记录">
            <EF:EFGrid blockId="resultF" autoDraw="no" autoBind="true" rowNo="true" readonly="true"
                       checkMode="multiple,row" queryMethod="allQuery" height="460px" sort="setted">
                <EF:EFColumn ename="id" cname="id" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="detailId" cname="detailId" align="center" locked="true" hidden="true"/>
                <EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="120" enable="false" displayType="url" sort="true" alias="fsd.goods_num"/>
                <EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="180" sort="true" alias="fsd.goods_name"/>
                <EF:EFColumn ename="spec" cname="型号规格" align="center" width="150" sort="true" alias="fi.spec"/>
                <EF:EFColumn ename="applyDeptName" cname="申请科室" align="center" width="150" sort="true" alias="fs.apply_dept_name"/>
                <EF:EFColumn ename="scrapStatus" cname="审批状态" align="center" width="150" sort="true" alias="fsd.scrap_detail_status"/>
                <EF:EFColumn ename="applyReason" cname="申请理由" align="center" width="150" sort="true" alias="fs.apply_reason"/>
                <EF:EFColumn ename="applyPerson" cname="申请人" align="center" width="150" sort="true" alias="fs.apply_person"/>
                <EF:EFColumn ename="applyTime" cname="申请时间" align="center" width="150" sort="true" alias="fs.apply_time"/>
                <EF:EFColumn ename="identifyDeptName" cname="技术科" align="center" width="150" sort="true" alias="fsd.identify_dept_name"/>
                <EF:EFColumn ename="identifyPerson" cname="技术员" align="center" width="150" sort="true" alias="fsd.identify_person"/>
                <EF:EFColumn ename="identifyTime" cname="技术确认时间" align="center" width="150" sort="true" alias="fsd.identify_time"/>
                <EF:EFColumn ename="identifyReason" cname="技术指导" align="center" width="150" sort="true" alias="fsd.identify_reason"/>
                <EF:EFColumn ename="functionDeptName" cname="归口科室" align="center" width="150" sort="true" alias="fsd.function_dept_name"/>
                <EF:EFColumn ename="functionPerson" cname="归口人" align="center" width="150" sort="true" alias="fsd.function_person"/>
                <EF:EFColumn ename="functionTime" cname="归口通过时间" align="center" width="150" sort="true" alias="fsd.function_time"/>
                <EF:EFColumn ename="functionReason" cname="归口原因" align="center" width="150" sort="true" alias="fsd.function_reason"/>
                <EF:EFColumn ename="assetDeptName" cname="资产科" align="center" width="150" sort="true" alias="fsd.asset_dept_name"/>
                <EF:EFColumn ename="assetPerson" cname="审批人" align="center" width="150" sort="true" alias="fsd.asset_person"/>
                <EF:EFColumn ename="assetTime" cname="审批时间" align="center" width="150" sort="true" alias="fsd.asset_time"/>
                <EF:EFColumn ename="assetReason" cname="审批答复" align="center" width="150" sort="true" alias="fsd.asset_reason"/>
            </EF:EFGrid>
        </div>
    </EF:EFTab>
    <!-- 固资资产选择弹出窗 -->
    <EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="资产报废录入" modal="true"/>
    <EF:EFWindow id="popDataEdit" url="" lazyload="true" width="90%" height="90%" title="资产报废编辑" modal="true"/>
    <script type="text/javascript" src="${ctx}/FA/common/js/si-yxSign.js"></script>
    <script type="text/javascript" src="${ctx}/FA/BF/FABF0101.js"></script>
</EF:EFPage>