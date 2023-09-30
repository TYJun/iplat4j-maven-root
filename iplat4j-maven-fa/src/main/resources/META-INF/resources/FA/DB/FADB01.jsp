<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>资产调拨</title>
<EF:EFPage title="资产调拨">
    <EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
			<div id="one">
				<EF:EFInput ename="inqu_status-0-goodsNum" cname="资产编码" colWidth="3"/>
				<EF:EFInput ename="inqu_status-0-goodsName" cname="资产名称" colWidth="3"/>
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
<%--				<EF:EFTreeInput ename="inqu_status-0-goodsClassifyCode" cname="资产类别" serviceName="FALB01" methodName="queryFaTypeTree"--%>
<%--								valueField="id" textField="typeName" hasChildren="isLeaf" readonly="true"--%>
<%--								root="{id: 'root', typeName: '根节点'}" colWidth="3" ratio="4:8">--%>
<%--				</EF:EFTreeInput>--%>
				<EF:EFInput ename="inqu_status-0-goodsClassifyCode" colWidth="3" ratio="4:8" cname="资产类别"/>
				<EF:EFInput ename="inqu_status-0-goodsTypeCode" colWidth="3" ratio="4:8" cname="类组"/>
				<EF:EFSelect ename="inqu_status-0-fundingSourceNum" cname="资金来源" colWidth="3" ratio="4:8" >
					<EF:EFOption label="--请选择--" value=""/>
					<EF:EFCodeOption codeName="wilp.mp.source"/>
				</EF:EFSelect>
				<EF:EFSelect ename="inqu_status-0-statusCode" cname="资产状态" colWidth="3" ratio="4:8" >
					<EF:EFOption label="--请选择--" value=""/>
					<EF:EFOption label="在用" value="020"/>
					<EF:EFOption label="待用" value="010"/>
				</EF:EFSelect>
<%--				<EF:EFMultiSelect ename="inqu_status-0-deptName" cname="所属科室" colWidth="3" ratio="4:8"--%>
<%--								  serviceName="FADA01" queryMethod="queryDept" filter="contains">--%>
<%--					<EF:EFOptions blockId="dept" textField="deptName" valueField="deptName"/>--%>
<%--				</EF:EFMultiSelect>--%>
<%--				<EF:EFSelect ename="inqu_status-0-goodsTypeCode" resultId="result" serviceName="FALB01" methodName="faTypeEFSelect" filter="contains"--%>
<%--							 colWidth="3" ratio="4:8" optionLabel="--请选择--" cname="类别名称" textField="text" valueField="value" />--%>
			</div>
			<div id="role" style="display: none">
<%--				<EF:EFInput ename="inqu_status-0-deptName" cname="所属科室" colWidth="3"/>--%>
				<EF:EFMultiSelect ename="inqu_status-0-deptName" cname="所属科室" colWidth="3" ratio="4:8"
								  serviceName="FADA01" queryMethod="queryDept" filter="contains">
					<EF:EFOptions blockId="dept" textField="deptName" valueField="deptName"/>
				</EF:EFMultiSelect>
			</div>
			<div id="other" style="display: none">
				<EF:EFInput ename="inqu_status-0-applyDeptName" cname="供给科室" colWidth="3"/>
				<EF:EFInput ename="inqu_status-0-confirmDeptName" cname="接收科室" colWidth="3"/>
				<EF:EFDateSpan startName="inqu_status-0-applyTimeS" startCname="申请时间起"
							   endName="inqu_status-0-applyTimeE" endCname="申请时间止"
							   ratio="3:3" startRatio="4:8" endRatio="4:8" readonly="true"/>
				<EF:EFInput ename="inqu_status-0-transferNo" cname="调拨单号" colWidth="3"/>
				<EF:EFSelect ename="inqu_status-0-transferStatus" cname="调拨单状态" colWidth="3">
					<EF:EFOption label="请选择" value=""/>
					<EF:EFOption label="待接收" value="10"/>
					<EF:EFOption label="接收驳回" value="50"/>
					<EF:EFOption label="待审批" value="20"/>
					<EF:EFOption label="审批驳回" value="40"/>
					<EF:EFOption label="已审批" value="100"/>
				</EF:EFSelect>
				<EF:EFInput ename="inqu_status-0-applyReason" cname="调拨原因" colWidth="3"/>
				<EF:EFInput ename="inqu_status-0-confirmReason" cname="接收意见" colWidth="3"/>
				<EF:EFInput ename="inqu_status-0-auditReason" cname="审批意见" colWidth="3"/>
			</div>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="3"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="3"></EF:EFButton>
		</div>
    </EF:EFRegion>
	<EF:EFTab id="FaDaTab">
		<div title="调拨申请">
			<EF:EFGrid blockId="resultA" autoDraw="no" autoBind="true" rowNo="true" readonly="true" checkMode="multiple,row" queryMethod="confirmedQuery" height="440px">
				<EF:EFColumn ename="faInfoId" cname="faInfoId" align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="enterBillNo" cname="入库单号" align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="outBillNo" cname="出库单号" align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="matNum" cname="物资编码" align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="matName" cname="物资名称" align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="matTypeNum" cname="物资分类编码" align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="unitNum" cname="计量单位" align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="unitName" cname="计量单位" align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="price" cname="出库单价" align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="operationType" cname="出库类型" align="center" width="200"/>
				<EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="200" displayType="url" enable="false"/>
				<EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="200"/>
				<EF:EFColumn ename="spec" cname="型号规格"  align="center" width="200" />
				<EF:EFColumn ename="deptName" cname="所属科室" align="center" width="200"/>
<%--				<EF:EFColumn ename="build" cname="楼"   align="center" width="200"/>--%>
<%--				<EF:EFColumn ename="floor" cname="层"   align="center" width="200"/>--%>
				<EF:EFColumn ename="installLocation" cname="地点"   align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="room" cname="具体位置"   align="center" width="200"/>
				<EF:EFColumn ename="statusCode" cname="资产状态" align="center" width="200"/>
				<EF:EFColumn ename="remark" cname="备注"  align="center" width="200"/>
				<EF:EFColumn ename="goodsClassifyName" cname="资产类别"  align="center" width="200"/>
				<EF:EFColumn ename="goodsTypeName" cname="类组"  align="center" width="200"/>
				<EF:EFColumn ename="manufacturer" cname="制造厂商"  align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="surpName" cname="供应商"   align="center" width="200"/>
				<EF:EFColumn ename="buyDate" cname="购入日期"   align="center" width="200"/>
				<EF:EFColumn ename="useDate" cname="使用日期"   align="center" width="200"/>
				<EF:EFColumn ename="buyCost" cname="资产原值"   align="center" width="200"/>
				<EF:EFColumn ename="netAssetValue" cname="资产净值"   align="center" width="200"/>
				<EF:EFColumn ename="useYears" cname="使用年限"   align="center" width="200"/>
				<%--			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" />--%>
				<EF:EFColumn ename="recCreateName" cname="创建人"   align="center" width="200"/>
				<EF:EFColumn ename="rfidCode" cname="RFID"  align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="cardStatus" cname="是否发卡"  align="center" width="200" hidden="true"/>
				<EF:EFColumn ename="lockFlag" cname="变更状态"  align="center" hidden="true"/>
			</EF:EFGrid>
		</div>
<%--		<div title="科室调拨申请">--%>
<%--			<EF:EFGrid blockId="resultB" autoDraw="no" autoBind="true" readonly="true" checkMode="multiple,row" queryMethod="transferApplyQuery">--%>
<%--				<EF:EFColumn ename="id" cname="固定资产调拨表主键"  align="center" locked="true" hidden="true"/>--%>
<%--				<EF:EFColumn ename="transferNo" cname="调拨单号"  align="center" locked="true" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="applyDeptName" cname="调出科室"  align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="applyPerson" cname="申请人"  align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="applyTime" cname="申请时间"   align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="applyReason" cname="调拨原因"   align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="confirmDeptName" cname="调入科室" align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="confirmBuild" cname="楼" align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="confirmFloor" cname="层" align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="confirmLocationName" cname="地点" align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="confirmPerson" cname="确认人" align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="confirmTime" cname="确认时间" align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="confirmReason" cname="确认信息" align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="auditDeptName" cname="审批科室" align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="auditPerson" cname="审批人" align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="auditTime" cname="审批时间" align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="auditReason" cname="审批信息" align="center" enable="false" width="200"/>--%>
<%--				<EF:EFColumn ename="transferStatus" cname="调拨单状态" align="center" enable="false" width="200"/>--%>
<%--			</EF:EFGrid>--%>
<%--		</div>--%>
		<div title="接收科室确认">
			<EF:EFRegion id="C1" title="资产调拨单">
				<EF:EFGrid blockId="resultC" autoDraw="no" autoBind="true" rowNo="true" readonly="true" checkMode="single,row" queryMethod="transferConfirmQuery" height="300px">
					<EF:EFColumn ename="id" cname="资产调拨表主键"  align="center" locked="true" hidden="true"/>
					<EF:EFColumn ename="transferNo" cname="调拨单号"  align="center" width="200" displayType="url" enable="false"/>
					<EF:EFColumn ename="applyDeptName" cname="供给科室"  align="center" enable="false" width="200"/>
					<EF:EFColumn ename="confirmDeptName" cname="接收科室" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="applyPerson" cname="申请人"  align="center" enable="false" width="200"/>
					<EF:EFColumn ename="applyTime" cname="申请时间"   align="center" enable="false" width="200"/>
					<EF:EFColumn ename="applyReason" cname="调拨原因"   align="center" enable="false" width="200"/>
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFRegion id="C2" title="调拨单明细">
				<EF:EFGrid blockId="resultDetailsC2" autoDraw="no" autoBind="true" rowNo="true" readonly="true" checkMode="single,row" queryMethod="transferDetailResult" height="300px">
					<EF:EFColumn ename="goodsNum" cname="资产编码"  align="center" enable="false" width="200"/>
					<EF:EFColumn ename="goodsName" cname="资产名称" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="spec" cname="规格"  align="center" enable="false" width="200"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
		<div title="资产科审批">
			<EF:EFRegion id="D1" title="资产调拨单">
				<EF:EFGrid blockId="resultD" autoDraw="no" autoBind="true" rowNo="true" readonly="true" checkMode="multiple,row" queryMethod="transferAuditQuery" height="300px">
					<EF:EFColumn ename="id" cname="资产调拨表主键"  align="center" locked="true" hidden="true"/>
					<EF:EFColumn ename="transferNo" cname="调拨单号"  align="center" width="200" displayType="url" enable="false"/>
					<EF:EFColumn ename="applyDeptNum" cname="供给科室编码"  align="center" enable="false" width="200" hidden="true"/>
					<EF:EFColumn ename="applyDeptName" cname="供给科室"  align="center" enable="false" width="200"/>
					<EF:EFColumn ename="confirmDeptNum" cname="接收科室编码" align="center" enable="false" width="200" hidden="true"/>
					<EF:EFColumn ename="confirmDeptName" cname="接收科室" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="confirmLocationName" cname="存放地点" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="applyPerson" cname="申请人"  align="center" enable="false" width="200"/>
					<EF:EFColumn ename="applyTime" cname="申请时间" align="center" enable="false" width="200" dateFormat="yyyy-MM-dd hh:mm:ss"/>
					<EF:EFColumn ename="applyReason" cname="调拨原因"   align="center" enable="false" width="200"/>
					<EF:EFColumn ename="confirmPerson" cname="接收人" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="confirmTime" cname="接收时间" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="confirmReason" cname="接收意见" align="center" enable="false" width="200"/>
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFRegion id="D2" title="调拨单明细">
				<EF:EFGrid blockId="resultDetailsD2" autoDraw="no" autoBind="true" rowNo="true" readonly="true" checkMode="single,row" queryMethod="transferDetailResult" height="300px">
					<EF:EFColumn ename="goodsNum" cname="资产编码"  align="center" enable="false" width="200"/>
					<EF:EFColumn ename="goodsName" cname="资产名称" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="spec" cname="规格"  align="center" enable="false" width="200"/>
				</EF:EFGrid>
			</EF:EFRegion>
			<!-- 批量审批 -->
			<EF:EFWindow id="accept" title="资产科批量审批" url="" lazyload="true" width="60%" height="40%">
				<EF:EFRegion id="accept" head="hidden">
					<div class="row">
						<EF:EFInput ename="info-0-auditFileCode" cname="资产科电子签名fileCode" colWidth="12" readonly="true" type="hidden"/>
						<EF:EFInput ename="info-0-auditReason" type="textarea" colWidth="12" ratio="2:10" cname="审批意见" rows="5" maxLength="200"/>
					</div>
					<div class="row">
						<div class="col-md-10">
							<div class="form-group">
								<label for="auditPic" class="col-xs-2 control-label"></label>
								<div class="col-xs-10">
									<span id="auditPic"></span>
								</div>
							</div>
						</div>
					</div>
					<div class="button-region" id="buttonDiv">
						<EF:EFButton cname="通过" ename="pass" layout="0" ></EF:EFButton>
						<EF:EFButton cname="驳回" ename="reject" layout="0" ></EF:EFButton>
						<EF:EFButton cname="取消" ename="cancel" layout="0" ></EF:EFButton>
					</div>
				</EF:EFRegion>
			</EF:EFWindow>
		</div>
		<div title="调拨记录">
			<EF:EFRegion id="E1" title="资产调拨单">
				<EF:EFGrid blockId="resultE" autoDraw="no" autoBind="true" rowNo="true" readonly="true" checkMode="single,row" queryMethod="transferRecordQuery" height="300px">
					<EF:EFColumn ename="id" cname="资产调拨表主键"  align="center" locked="true" hidden="true"/>
					<EF:EFColumn ename="transferNo" cname="调拨单号"  align="center" width="200" displayType="url" enable="false"/>
					<EF:EFColumn ename="transferStatus" cname="调拨单状态" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="applyDeptName" cname="供给科室"  align="center" enable="false" width="200"/>
					<EF:EFColumn ename="confirmDeptName" cname="接收科室" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="confirmLocationName" cname="存放地点" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="applyPerson" cname="申请人"  align="center" enable="false" width="200"/>
					<EF:EFColumn ename="applyTime" cname="申请时间"   align="center" enable="false" width="200"/>
					<EF:EFColumn ename="applyReason" cname="调拨原因"   align="center" enable="false" width="200"/>
					<EF:EFColumn ename="confirmPerson" cname="接收人" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="confirmTime" cname="接收时间" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="confirmReason" cname="接收意见" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="auditPerson" cname="审批人" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="auditTime" cname="审批时间" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="auditReason" cname="审批意见" align="center" enable="false" width="200"/>
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFRegion id="E2" title="资产调拨单">
				<EF:EFGrid blockId="resultDetailsE2" autoDraw="no" autoBind="true" rowNo="true" readonly="true" checkMode="single,row" queryMethod="transferDetailResult" height="300px">
					<EF:EFColumn ename="goodsNum" cname="资产编码"  align="center" enable="false" width="200"/>
					<EF:EFColumn ename="goodsName" cname="资产名称" align="center" enable="false" width="200"/>
					<EF:EFColumn ename="spec" cname="规格"  align="center" enable="false" width="200"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
	</EF:EFTab>
<%--	<div class="row">--%>
<%--		<div class="col-md-10">--%>
<%--			<div class="form-group">--%>
<%--				<label for="reqPic" class="col-xs-2 control-label">参考图片</label>--%>
<%--				<div class="col-xs-10">--%>
<%--					<span id="reqPic"></span>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--	</div>--%>
    <EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="资产调拨录入" modal="true" />
    <EF:EFWindow id="popDataEdit" url="" lazyload="true" width="90%" height="90%" title="资产调拨编辑" modal="true" />
	<script type="text/javascript" src="${ctx}/FA/common/js/si-yxSign.js"></script>
	<script type="text/javascript" src="${ctx}/FA/DB/FADB0101.js"></script>
</EF:EFPage>