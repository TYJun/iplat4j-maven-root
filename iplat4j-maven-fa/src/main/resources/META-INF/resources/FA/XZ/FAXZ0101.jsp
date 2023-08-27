<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="固定资产闲置录入">
	<EF:EFRegion id="info" head="hidden">
		<div class="row">
			<EF:EFInput ename="type" cname="操作类型" colWidth="5" required="true" type="hidden"/>
			<EF:EFInput ename="info-0-faIdleId" cname="faIdleId" colWidth="5" required="true" type="hidden"/>
			<EF:EFInput ename="info-0-archiveFlag" cname="归档标记" colWidth="5" required="true" type="hidden"/>
			<EF:EFInput ename="info-0-idleNo" cname="闲置单号" colWidth="5" readOnly="true"/>
			<EF:EFInput ename="info-0-statusCode" cname="申请状态" colWidth="5" readOnly="true"/>
			<EF:EFInput ename="info-0-goodsId" cname="固资id" colWidth="5" required="true" readOnly="true" type="hidden"/>
		</div>
		<div class="row">
			<EF:EFPopupInput ename="info-0-goodsNum" cname="固资编码"  colWidth="5" required="true" readOnly="true"
							 containerId="ef_popup_grid" popupWidth="1050" popupHeight="500" popupTitle="固定资产选择"
							 resizable="true" center="true">
			</EF:EFPopupInput>
			<EF:EFInput ename="info-0-goodsName" cname="固资名称" colWidth="5" required="true" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-model" cname="型号规格" colWidth="5" readOnly="true"/>
			<EF:EFInput ename="info-0-manufacturer" cname="制造厂商" colWidth="5" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-buyCost" cname="资产原值" colWidth="5" readOnly="true"/>
			<EF:EFInput ename="info-0-useYears" cname="使用年限" colWidth="5" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-deptName" cname="所属科室" colWidth="5" readOnly="true"/>
			<EF:EFInput ename="info-0-installLocation" cname="地点" colWidth="5" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFDatePicker ename="info-0-idleDate" cname="闲置日期" role="date" readonly="true"
							 colWidth="5" format="yyyy-MM-dd" required="true"
							 parseFormats="['yyyy-mm-dd']" />
			<EF:EFInput ename="info-0-idleDirection" cname="闲置去向" colWidth="5" required="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-idleReason" cname="闲置原因" rows="3"
						colWidth="10" type="textarea" required="true" ratio="2:10"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-remark" cname="备注" colWidth="10" rows="3" type="textarea" ratio="2:10"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="提交" ename="SAVE" layout="3"></EF:EFButton>
			<EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
		</div>
	
		<!-- 固资资产选择弹出窗  -->
		<div id="ef_popup_grid" style="display: none;">
			<EF:EFPage>
				<EF:EFRegion id="inqu" title="查询条件" showClear="true">
					<div class="row">
						<EF:EFInput ename="goodsName" cname="固资资产名称"  colWidth="8"/>
						<EF:EFButton cname="查询" ename="queryFAInfoType" layout="1" class="k-button"></EF:EFButton>
					</div>
				</EF:EFRegion>
				<EF:EFRegion id="result" title="固资资产列表" >
					<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" readonly="true"
						serviceName="FADA01" queryMethod="queryAvailableFaInfo" height="305">
						<EF:EFColumn ename="faInfoId" cname="faInfoId" align="center" locked="true" hidden="true"/>
						<EF:EFColumn ename="goodsNum" cname="固定资产编码" align="center" locked="true" enable="false" width="200"/>
						<EF:EFColumn ename="goodsName" cname="固定资产名称" align="center" locked="true" enable="false"/>
						<EF:EFColumn ename="model" cname="型号规格" align="center" locked="true" enable="false"/>
						<EF:EFColumn ename="manufacturer" cname="制造厂商" align="center" locked="true" enable="false"/>
						<EF:EFColumn ename="buyCost" cname="资产原值" align="center" locked="true" enable="false"/>
						<EF:EFColumn ename="useYears" cname="使用年限" align="center" enable="false"/>
						<EF:EFColumn ename="deptName" cname="所属科室" align="center" enable="false"/>
						<EF:EFColumn ename="installLocation" cname="地点" align="center" enable="false"/>
					</EF:EFGrid>
				</EF:EFRegion>
			</EF:EFPage>
		</div>
	</EF:EFRegion>
</EF:EFPage>