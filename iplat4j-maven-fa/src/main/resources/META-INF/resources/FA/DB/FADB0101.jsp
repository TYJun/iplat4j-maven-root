<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="资产调拨">
	<EF:EFRegion id="info" head="hidden">
		<EF:EFInput ename="type" cname="操作类型" colWidth="5" readonly="true" type="hidden"/>
		<EF:EFInput ename="info-0-transferNo" cname="调拨单号" colWidth="5" readonly="true" type="hidden"/>
		<EF:EFInput ename="inventoryStatus" cname="供给/接收科室状态" rows="1" colWidth="10" type="textarea" ratio="2:10" maxLength="200" readonly="true"/>
		<div id="apply" style="display: none">
			<div id="confirmDeptNum" style="display: none">
				<EF:EFInput ename="info-0-applyFileCode" cname="申请科室电子签名fileCode" colWidth="5" readonly="true" type="hidden"/>
				<EF:EFInput ename="info-0-turnDeptNum" cname="科室编码" colWidth="5" readonly="true" type="hidden"/>
				<EF:EFInput ename="info-0-turnDeptName" cname="科室名称" colWidth="5" readonly="true" type="hidden"/>
				<EF:EFInput ename="info-0-deptNum" cname="接收科室编码" colWidth="5" readonly="true" type="hidden"/>
				<EF:EFAutoComplete ename="info-0-deptName" cname="接收科室" colWidth="5" ratio="4:8"
				noDataTemplate="没有数据" filter="contains" serviceName="FADA01" queryMethod="queryFaInfoDept"
				resultId="dept" dataField="deptName" required="true"/>
<%--				<EF:EFPopupInput ename="info-0-confirmDeptNum" cname="接收科室"  colWidth="5" required="true"--%>
<%--								 popupTitle="科室选择" readOnly="true"--%>
<%--								 popupType="ServiceGrid" serviceName="FADA01" methodName="queryDept" resultId="dept"--%>
<%--								 valueField="deptNum" textField="deptName"--%>
<%--								 columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />--%>
			</div>
			<EF:EFInput ename="info-0-applyReason" cname="调拨原因" rows="2" placeholder="不能超过200字"
						colWidth="10" type="textarea" required="true" ratio="2:10" maxLength="200"/>
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
		<div id="confirm" style="display: none">
			<div>
				<EF:EFInput ename="info-0-confirmFileCode" cname="接收科室电子签名fileCode" colWidth="5" readonly="true" type="hidden"/>
				<EF:EFInput ename="info-0-applyDeptNum" cname="供给编码" colWidth="5" readOnly="true" type="hidden"/>
				<EF:EFInput ename="info-0-applyDeptName" cname="供给科室" colWidth="5" readOnly="true"/>
				<EF:EFInput ename="info-0-confirmDeptNum" cname="接收编码" colWidth="5" readOnly="true" type="hidden"/>
				<EF:EFInput ename="info-0-confirmDeptName" cname="接收科室" colWidth="5" readOnly="true"/>
<%--				<EF:EFPopupInput ename="info-0-confirmLocationNum" cname="存放位置" colWidth="5" readonly="true" popupWidth = "550"--%>
<%--								 popupType="ServiceGrid" popupTitle="存放位置选择" required="true" serviceName="FADB0101" methodName="querySpot" resultId="spot" autofit="true"--%>
<%--								 valueField="spotNum" textField="spotName" backFillFieldIds="spotName" backFillColumnIds="spotName" columnEnames="building,floor,spotName" columnCnames="楼,层,地点"/>--%>
				<EF:EFPopupInput ename="info-0-confirmLocationNum" cname="存放位置" colWidth="5" readonly="true" popupWidth="850"
								 popupTitle="存放位置选择" required="true" containerId="confirmLocationNum"></EF:EFPopupInput>
				<EF:EFInput ename="info-0-confirmBuild" cname="楼" colWidth="5" readonly="true"/>
				<EF:EFInput ename="info-0-confirmFloor" cname="层" colWidth="5" readonly="true"/>
				<EF:EFInput ename="info-0-confirmRoom" cname="具体位置" colWidth="5"/>
				<EF:EFInput ename="info-0-confirmReason" cname="接收意见" rows="2" placeholder="不能超过200字"
							colWidth="10" type="textarea" required="true" ratio="2:10" maxLength="200"/>
			</div>
			<div class="row">
				<div class="col-md-1">
					<div class="form-group">
<%--						<label for="confirmPic" class="col-xs-2 control-label">电子签名</label>--%>
						<div class="col-xs-10">
							<span id="confirmPic"></span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="audit" style="display: none">
			<EF:EFInput ename="info-0-auditFileCode" cname="资产科电子签名fileCode" colWidth="5" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-auditReason" cname="审批意见" rows="2" placeholder="不能超过200字"
						colWidth="10" type="textarea" required="true" ratio="2:10" maxLength="200"/>
			<div class="row">
				<div class="col-md-1">
					<div class="form-group">
<%--						<label for="auditPic" class="col-xs-2 control-label">电子签名</label>--%>
						<div class="col-xs-10">
							<span id="auditPic"></span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="button-region" id="buttonDiv">
			<div id="enterButton" style="display: none">
				<EF:EFButton cname="保存并提交" ename="saveAndSubmit" layout="3"></EF:EFButton>
<%--				<EF:EFButton cname="保存" ename="save" layout="3"></EF:EFButton>--%>
				<EF:EFButton cname="关闭" ename="close1" layout="3"></EF:EFButton>
			</div>
			<div id="applyButton" style="display: none">
				<EF:EFButton cname="提交" ename="submit" layout="3"></EF:EFButton>
				<EF:EFButton cname="关闭" ename="close2" layout="3"></EF:EFButton>
			</div>
			<div id="confirmButton" style="display: none">
				<EF:EFButton cname="通过" ename="confirmPass" layout="3"></EF:EFButton>
				<EF:EFButton cname="整单驳回" ename="confirmReject" layout="3"></EF:EFButton>
				<EF:EFButton cname="关闭" ename="close3" layout="3"></EF:EFButton>
			</div>
			<div id="auditButton" style="display: none">
				<EF:EFButton cname="通过" ename="auditPass" layout="3"></EF:EFButton>
				<EF:EFButton cname="整单驳回" ename="auditReject" layout="3"></EF:EFButton>
				<EF:EFButton cname="关闭" ename="close4" layout="3"></EF:EFButton>
			</div>
			<div id="auditButton2" style="display: none">
				<EF:EFButton cname="关闭" ename="close6" layout="3"></EF:EFButton>
			</div>
			<div id="allButton" style="display: none">
				<EF:EFButton cname="关闭" ename="close5" layout="3"></EF:EFButton>
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" head="hidden">
		<EF:EFGrid blockId="resultFixedAssests" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
				   checkMode="multiple,row" readonly="true" rowNo="true" isFloat="true" height="380px">
			<EF:EFColumn ename="id" cname="id"  align="center" locked="true" hidden="true"/>
			<EF:EFColumn ename="faInfoId" cname="faInfoId"  align="center" hidden="true"/>
			<EF:EFColumn ename="goodsNum" cname="资产编码"  align="center" width="200" enable="false"/>
			<EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="200" enable="false"/>
			<EF:EFColumn ename="spec" cname="型号规格"  align="center" width="200" enable="false"/>
			<EF:EFColumn ename="deptName" cname="所属科室"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="room" cname="具体位置"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="statusCode" cname="资产状态"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="remark" cname="备注"  align="center" width="200" enable="false"/>
			<EF:EFColumn ename="goodsClassifyName" cname="资产类别"  align="center" width="200" enable="false"/>
			<EF:EFColumn ename="goodsTypeName" cname="资产类别名称"  align="center" width="200" enable="false"/>
			<EF:EFColumn ename="manufacturer" cname="制造厂商"  align="center" width="200" enable="false"/>
			<EF:EFColumn ename="surpName" cname="供应商"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="buyDate" cname="购入日期"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="useDate" cname="使用日期"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="build" cname="楼"   align="center" width="200" enable="false" hidden="true"/>
			<EF:EFColumn ename="floor" cname="层"   align="center" width="200" enable="false" hidden="true"/>
			<EF:EFColumn ename="installLocation" cname="地点"   align="center" width="200" enable="false" hidden="true"/>
			<EF:EFColumn ename="buyCost" cname="资产原值"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="useYears" cname="使用年限"   align="center" width="200" enable="false"/>
			<%--			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" />--%>
			<EF:EFColumn ename="recCreateName" cname="创建人"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="rfidCode" cname="RFID"  align="center" width="200" enable="false" hidden="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<div id="confirmLocationNum" style="display: none; height:550px; width: 850px">
		<EF:EFRegion id="result" title="楼层信息" head="hidden">
			<EF:EFGrid blockId="spot" checkMode="single,row" serviceName="FADB0101" queryMethod="querySpot"
					   readonly="true" rowNo="true" autoDraw="no" height="350px"> <!-- EFGrid 默认 autoDraw="yes" -->
				<EF:EFColumn ename="building" cname="楼" enable="false"/>
				<EF:EFColumn ename="floor" cname="层" enable="false"/>
				<EF:EFColumn ename="spotName" cname="地点" enable="false"/>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
	<EF:EFWindow id="popData" url="" lazyload="true" width="80%" height="80%" title="资产档案信息" modal="true" />
</EF:EFPage>
<style>
	label#info-0-deptNum:before{
		content: '*';
		color: red;
	}
</style>