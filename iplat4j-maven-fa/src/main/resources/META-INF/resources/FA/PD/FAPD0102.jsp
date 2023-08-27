<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="资产盘点详情">
	<EF:EFRegion id="info" head="hidden">
		<div class="row">
			<EF:EFInput ename="type" cname="操作类型" colWidth="10" type="hidden"/>
			<EF:EFInput ename="info-0-faInventoryId" cname="faInventoryId" colWidth="10" readOnly="true" type="hidden"/>
			<EF:EFInput ename="info-0-inventoryNo" cname="盘点单号" colWidth="10" readOnly="true"/>
			<EF:EFInput ename="info-0-archiveFlag" cname="归档记录" colWidth="10" type="hidden"/>
			<EF:EFInput ename="info-0-checkDeptNum" cname="科室编码" colWidth="10" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-checkDeptName" cname="科室名称" colWidth="10" readonly="true"/>
<%--			<EF:EFPopupInput ename="info-0-inventoryDeptNum" cname="盘点科室" required="true"--%>
<%--							 popupTitle="科室选择" readOnly="true" colWidth="10"--%>
<%--							 popupType="ServiceGrid" serviceName="FADA01" methodName="queryDept" resultId="dept"--%>
<%--							 valueField="deptNum" textField="deptName"--%>
<%--							 columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />--%>
			<EF:EFInput ename="info-0-newBuild" cname="盘点楼" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-newFloor" cname="盘点层" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-newGoodsLocationNum" cname="盘点地点" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-newGoodsLocation" cname="盘点地点" readonly="true" type="hidden"/>
			<div class="col-md-10">
				<div class="form-group">
					<label data-for="newBuild" class="col-xs-4 control-label">
						<span>楼</span>
					</label>
					<div class="col-xs-8">
						<input id="newBuild" name="newBuild" value=""
							   placeholder="楼" ondblclick="showAll('newBuild')">
					</div>
				</div>
			</div>
			<div class="col-md-10">
				<div class="form-group">
					<label data-for="newFloor" class="col-xs-4 control-label">
						<span>层</span>
					</label>
					<div class="col-xs-8">
						<input id="newFloor" name="newFloor" value=""
							   placeholder="层" ondblclick="showAll('newFloor')">
					</div>
				</div>
			</div>
			<div class="col-md-10">
				<div class="form-group">
					<label data-for="newGoodsLocation" class="col-xs-4 control-label">
						<span style="color:red">*</span>盘点位置
					</label>
					<div class="col-xs-8">
						<input id="newGoodsLocation" name="newGoodsLocation"
							   placeholder="地点"
							   ondblclick="showAll('newGoodsLocation')">
					</div>
				</div>
			</div>
			<EF:EFInput ename="newGoodsLocationNum" cname="地点编码" readOnly="true" type="hidden"/>
			<EF:EFInput ename="info-0-remark" cname="备注" colWidth="10" rows="3" type="textarea"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<div id="tab" style="display: none">
		<EF:EFTab id="tab-tab_grid">
			<div title="盘点履历">
				<EF:EFGrid blockId="resultInventoryDetail" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
						   checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryInventoryDetailTabInfo">
					<EF:EFColumn ename="faInventoryDetailId" cname="faInventoryDetailId" hidden="true" enable="false" width="100"/>
					<EF:EFColumn ename="goodsNum" cname="资产编码" align="center" enable="false" width="100"/>
					<EF:EFColumn ename="goodsName" cname="资产名称" align="center" enable="false" width="100"/>
					<EF:EFColumn ename="spotName" cname="盘点地点名称" align="center" enable="false" width="100"/>
					<EF:EFColumn ename="dealFlag" cname="盘点状态" align="center" enable="false" width="100"/>
					<EF:EFColumn ename="inventoryRemark" cname="盘点结果" align="center" enable="false" width="100"/>
				</EF:EFGrid>
			</div>
		</EF:EFTab>
	</div>
</EF:EFPage>