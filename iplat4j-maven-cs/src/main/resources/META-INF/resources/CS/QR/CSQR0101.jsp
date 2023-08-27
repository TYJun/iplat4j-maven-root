<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<style>
.picMaintain {
	margin: 2px;
}
</style>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage title="工单确认">
	<EF:EFRegion id="result">
		<div class="row">
			<EF:EFInput ename="taskId" cname="工单ID" colWidth="5" type="hidden"
				readonly="true" />
			<EF:EFInput ename="taskNo" cname="工单号" colWidth="5" type="hidden"
				readonly="true" />
		</div>
		<div class="row">
			<EF:EFInput ename="guaranteeNum" cname="来电人工号" colWidth="5"
				readonly="true" />
			<EF:EFInput ename="guaranteeName" cname="来电人" colWidth="5"
				readonly="true" />
		</div>
		<div class="row">
			<EF:EFInput ename="reqTelNum" cname="来电号码" colWidth="5"
				readonly="true" />
			<EF:EFInput ename="reqDeptNum" cname="来电科室编码" colWidth="5"
				type="hidden" />
			<EF:EFInput ename="reqDeptName" cname="来电科室" colWidth="5"
				readonly="true" />
		</div>

		<div class="row">
			<EF:EFInput ename="building" cname="保洁地点楼" colWidth="5"
				readonly="true" />
			<EF:EFInput ename="floor" cname="保洁地点层" colWidth="5" readonly="true" />
		</div>

		<div class="row">
			<EF:EFInput ename="reqSpotNum" cname="保洁地点编码" colWidth="5"
				type="hidden" readonly="true" />
			<EF:EFInput ename="reqSpotName" cname="保洁地点" colWidth="5"
				readonly="true" />
			<EF:EFInput ename="codeName" cname="工单状态"
						colWidth="5" readonly="true">
			</EF:EFInput>
		</div>

		<div class="row">
			<div class="col-md-10">
				<div class="form-group">
					<label for="reqPic" class="col-xs-2 control-label">图片</label>
					<div class="col-xs-10">
						<span id="reqPic"></span>
					</div>
				</div>
			</div>
		</div>

		<div id="outerdiv"
			 style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 2; width: 100%; height: 100%; display: none;">
			<div id="innerdiv" style="position: absolute;">
				<img id="bigimg" style="border: 5px solid #fff;" src="" />
			</div>
		</div>

		<div class="row">
			<EF:EFInput ename="comments" cname="备注" colWidth="5" type="textarea"
				placeholder="不能超过200字" readonly="true" />
		</div>

	</EF:EFRegion>

	<EF:EFRegion id="selectItem" title="保洁事项选择">
		<EF:EFGrid blockId="resultItem" autoDraw="no" autoBind="true"
			checkMode="multiple,row" serviceName="CSRE0201"
			queryMethod="queryItemsRE">
			<!-- 谁排前谁为id -->
			<EF:EFColumn ename="itemId" cname="保洁事项记录id" readonly="true"
				align="center" enable="false" hidden="true" />
			<EF:EFColumn ename="itemCode" cname="保洁事项编码" readonly="true"
				align="center" enable="false" hidden="true" />
			<EF:EFColumn ename="executeUserNo" cname="执行人工号" readonly="true" hidden="true"
                align="center" enable="false" />
            <EF:EFColumn ename="executeUserName" cname="执行人" readonly="true"
                align="center" enable="false" />
			<EF:EFColumn ename="itemName" cname="保洁事项" readonly="true"
				align="center" enable="false" />
			<EF:EFColumn ename="serviceDeptNum" cname="绑定科室编码" readonly="true"
				align="center" enable="false" hidden="true" />
			<EF:EFColumn ename="serviceDeptName" cname="绑定科室" readonly="true"
				align="center" enable="false" />
			<EF:EFColumn ename="typeCode" cname="保洁事项分类编码" readonly="true"
				align="center" enable="false" hidden="true" />
			<EF:EFColumn ename="typeName" cname="保洁事项分类" readonly="true"
				align="center" enable="false" />
			<EF:EFColumn ename="comments" cname="备注" readonly="true"
				align="center" enable="false" />

		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

<!-- 执行人选择弹出窗  -->
<div id="ef_popup_peoplegrid" style="display: none;">
	<EF:EFPage>
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<div class="row">
				<EF:EFInput ename="wgroupNum" cname="科室编码" colWidth="2"
					type="hidden" />
				<EF:EFInput ename="workNo" cname="工号" colWidth="4" />
				<EF:EFInput ename="userName" cname="姓名" colWidth="4" />
			</div>
			<div align="right">
				<EF:EFButton cname="查询" ename="serachData" layout="1"
					class="k-button"></EF:EFButton>
			</div>

		</EF:EFRegion>
		<EF:EFRegion id="result" title="执行人列表">
			<EF:EFGrid blockId="person" autoDraw="no" checkMode="multiple,row"
				readonly="true" serviceName="CSRE01" queryMethod="queryPersonnel"
				height="305">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="workNo" cname="工号" width="100" align="center" />
				<EF:EFColumn ename="name" cname="姓名" width="150" align="center" />
				<EF:EFColumn ename="deptNum" cname="所属科室编码" width="100"
					align="center" hidden="true" />
				<EF:EFColumn ename="deptName" cname="所属科室" width="150"
					align="center" />
			</EF:EFGrid>
			<div align="right">
				<EF:EFButton ename="ef_popup_peoplegrid_button" cname="确定" layout="1"
					class="k-button">
				</EF:EFButton>
			</div>
		</EF:EFRegion>
	</EF:EFPage>
</div>