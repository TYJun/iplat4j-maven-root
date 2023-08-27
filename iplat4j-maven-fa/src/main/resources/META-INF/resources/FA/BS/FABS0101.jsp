<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="固定资产报损录入">
	<EF:EFRegion id="info" head="hidden">
		<div class="row">
			<EF:EFInput ename="type" cname="操作类型" colWidth="5" required="true" type="hidden"/>
			<EF:EFInput ename="info-0-faReimburseId" cname="faReimburseId" colWidth="5" required="true" type="hidden"/>
			<EF:EFInput ename="info-0-archiveFlag" cname="归档标记" colWidth="5" required="true" type="hidden"/>
			<EF:EFInput ename="info-0-reimburseNo" cname="报损单号" colWidth="5" readOnly="true"/>
			<EF:EFInput ename="info-0-confirmFlag" cname="申请状态" colWidth="5" readOnly="true"/>
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
			<EF:EFInput ename="info-0-totalDepreciation" cname="资产累计折旧" colWidth="5" readOnly="true"/>
			<EF:EFInput ename="info-0-netAssetValue" cname="资产净值" colWidth="5" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFDateSpan startName="info-0-buyDate" startCname="购入日期"
						   endName="info-0-useDate" endCname="使用日期"
						   ratio="5:5" startRatio="4:8" endRatio="4:8" readonly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-deptName" cname="所属科室" colWidth="5" readOnly="true"/>
			<EF:EFInput ename="info-0-installLocation" cname="地点" colWidth="5" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFDateSpan startName="info-0-reimburseDate" startCname="报损日期"
						   endName="info-0-finishTime" endCname="完成时间" required="true"
						   ratio="5:5" startRatio="4:8" endRatio="4:8" readonly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-reimburseReason" cname="报损原因" rows="3"
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
						<EF:EFColumn ename="faInfoId" cname="faInfoId" width="100" locked="true" hidden="true"/>
						<EF:EFColumn ename="goodsNum" cname="固定资产编码" align="center" locked="true" width="200"/>
						<EF:EFColumn ename="goodsName" cname="固定资产名称" align="center" locked="true"/>
						<EF:EFColumn ename="model" cname="型号规格" align="center" locked="true"/>
						<EF:EFColumn ename="manufacturer" cname="制造厂商" align="center" locked="true"/>
						<EF:EFColumn ename="buyCost" cname="资产原值" align="center" locked="true"/>
						<EF:EFColumn ename="useYears" cname="使用年限" align="center" />
						<EF:EFColumn ename="buyDate" cname="购入日期" align="center" />
						<EF:EFColumn ename="useDate" cname="使用日期" align="center" />
						<EF:EFColumn ename="deptName" cname="所属科室" align="center" />
						<EF:EFColumn ename="installLocation" cname="地点" align="center" />
					</EF:EFGrid>
				</EF:EFRegion>
			</EF:EFPage>
		</div>


		<!-- 设备选择弹出窗  -->
<%--		<div id="ef_popup_grid_sb" style="display: none;">--%>
<%--			<EF:EFPage>--%>
<%--				<div class="col-md-4">--%>
<%--					<EF:EFRegion id="R2" title="设备选择" style="height:430px;" >--%>
<%--						<EF:EFTree id="tree02" textField="text" valueField="label"  style="height:320px;"--%>
<%--								   hasChildren="leaf" serviceName="FADA01" methodName="queryDeviceTypeTree">--%>
<%--						</EF:EFTree>--%>
<%--					</EF:EFRegion>--%>
<%--				</div>--%>
<%--				<div class="col-md-8">--%>
<%--					<EF:EFRegion id="inqu" title="查询条件" showClear="true">--%>
<%--						<div class="row">--%>
<%--							<EF:EFInput ename="machineName" cname="设备名称"  colWidth="8"/>--%>
<%--							<EF:EFInput ename="deviceClassifyCode" cname="" type="hidden" />--%>
<%--							<EF:EFButton cname="查询" ename="queryFADevice" layout="1" class="k-button"></EF:EFButton>--%>
<%--						</div>--%>
<%--					</EF:EFRegion>--%>
<%--					<EF:EFRegion id="result1" title="固资类别列表" >--%>
<%--						<EF:EFGrid blockId="result1" autoDraw="no" checkMode="single,row" readonly="true"--%>
<%--								   serviceName="FADA01" queryMethod="queryDeviceList" height="305">--%>
<%--							<EF:EFColumn ename="id" cname="设备id" width="100" hidden="true" />--%>
<%--							<EF:EFColumn ename="machineCode" cname="设备编码" width="100" />--%>
<%--							<EF:EFColumn ename="machineName" cname="设备名称" width="100" />--%>
<%--							<EF:EFColumn ename="machineTypeCode" cname="设备分类编码" width="100" hidden="true"/>--%>
<%--							<EF:EFColumn ename="machineTypeName" cname="设备分类名称" width="100" />--%>
<%--						</EF:EFGrid>--%>
<%--					</EF:EFRegion>--%>
<%--				</div>--%>
<%--			</EF:EFPage>--%>
<%--		</div>--%>
	</EF:EFRegion>
</EF:EFPage>