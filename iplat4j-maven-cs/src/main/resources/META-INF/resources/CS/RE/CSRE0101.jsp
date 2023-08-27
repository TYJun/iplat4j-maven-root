<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="工单登记管理">
	<EF:EFRegion id="register">
		<div class="row">
			<div class="col-md-5">
				<div class="form-group">
					<label for="guaranteeNum" class="col-xs-4 control-label">来电人工号</label>
					<div class="col-xs-8">
						<input id="guaranteeNum" name="guaranteeNum"
							ondblclick="showAll('guaranteeNum')"
							onblur="echoOne('guaranteeNum')">
					</div>
				</div>
			</div>
			<EF:EFPopupInput ename="guaranteeName" cname="来电人" colWidth="5"
				popupTitle="来电人选择" required="false" readOnly="true"
				popupType="ServiceGrid" serviceName="CSRE01"
				methodName="queryPersonnelRE" resultId="person" valueField="workNo"
				textField="name" columnEnames="workNo,name" columnCnames="人员工号,人员姓名"
				backFillColumnIds="workNo" backFillFieldIds="guaranteeNum"/>

			<div class="col-md-5">
				<div class="form-group">
					<label for="reqTelNum" class="col-xs-4 control-label"><span
							style="color: red">*</span>来电号码</label>
					<div class="col-xs-8">
						<input id="reqTelNum" name="reqTelNum"
							ondblclick="showAll('reqTelNum')" onblur="echoOne('reqTelNum')">
					</div>
				</div>
			</div>

			<EF:EFPopupInput ename="reqDeptName" cname="来电科室" colWidth="5"
				popupTitle="科室选择" required="true" readOnly="true"
				popupType="ServiceGrid" serviceName="CSRE01" methodName="queryDept"
				resultId="dept" valueField="deptNum" textField="deptName"
				columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
		</div>

		<div class="row">
			<div class="col-md-10">
				<div class="form-group">
					<label class="col-md-2 control-label"> <span
						style="color: red">*</span>保洁地点
					</label>
					<div class="col-md-2">
						<input id="building" placeholder="楼"
							ondblclick="showAll('building')">
					</div>
					<div class="col-md-2" style="padding-left: 0px; margin-left: 0px;">
						<input id="floor" placeholder="层" ondblclick="showAll('floor')">
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-10">
				<div class="form-group">
					<label class="col-md-2 control-label"> </label>
					<div class="col-md-4">
						<input id="reqSpotNum" placeholder="地点编码" type="hidden"> <input
							id="reqSpotName" placeholder="保洁地点"
							ondblclick="showAll('reqSpotName')">
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<EF:EFInput ename="comments" cname="具体地址" colWidth="5" type="textarea"
				placeholder="不能超过200字" />
		</div>

		<div class="row">
			<div class="col-md-10">
				<div class="form-group">
					<label for="reqPic" class="col-xs-2 control-label">报修图片</label>
					<div class="col-xs-10">
						<span id="reqPic"></span>
						<span><EF:EFButton cname="上传图片" ename="uploadPic" layout="1" class="k-button"></EF:EFButton></span>
					</div>
				</div>
			</div>
		</div>

		<!-- 保洁事项选择弹出窗  -->
		<div id="ef_popup_grid" style="display: none;">
			<EF:EFPage>
				<div class="col-md-4">
					<EF:EFRegion id="R1" title="保洁事项分类" style="height:430px;">
						<EF:EFTree id="tree01" textField="classifyName" valueField="id"
							style="height:320px;" hasChildren="hasChildren"
							serviceName="CSSX01" methodName="queryTree">
						</EF:EFTree>
					</EF:EFRegion>
				</div>
				<div class="col-md-8">
					<EF:EFRegion id="inqu" title="查询条件" showClear="true">
						<div class="row">
							<EF:EFInput ename="itemName" cname="保洁事项名称：" colWidth="8" />
							<EF:EFInput ename="typeId" cname="" type="hidden" />
							<EF:EFButton cname="查询" ename="queryCsType" layout="1"
								class="k-button"></EF:EFButton>
						</div>
					</EF:EFRegion>
					<EF:EFRegion id="result" title="保洁事项列表">
						<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row"
							readonly="true" serviceName="CSSX01" queryMethod="query"
							height="305">
							<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
							<EF:EFColumn ename="itemCode" cname="保洁事项编码" width="100"
								align="center" hidden="true" />
							<EF:EFColumn ename="itemName" cname="保洁事项名称" width="150"
								align="center" />
							<EF:EFColumn ename="serviceDeptNum" cname="绑定科室编码" width="100"
								align="center" hidden="true" />
							<EF:EFColumn ename="serviceDeptName" cname="绑定科室" width="150"
								align="center" />
							<EF:EFColumn ename="typeCode" cname="保洁事项分类" width="100"
								align="center" hidden="true" />
							<EF:EFColumn ename="typeName" cname="保洁事项分类" width="100"
								align="center" />
						</EF:EFGrid>
					</EF:EFRegion>
				</div>
			</EF:EFPage>
		</div>
	</EF:EFRegion>

	<EF:EFRegion id="selectItem" title="保洁事项选择">
		<EF:EFGrid blockId="resultItem" autoDraw="no" autoBind="true"
			checkMode="multiple,row">
			<EF:EFColumn ename="itemCode" cname="保洁事项编码" readonly="true"
				align="center" enable="false" hidden="true" />
			<EF:EFColumn ename="itemId" cname="保洁事项ID" readonly="true"
						 align="center" enable="false" hidden="true" />
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
			<EF:EFColumn ename="comments" cname="具体保洁内容" readonly="false"
				align="center" enable="true" />
		</EF:EFGrid>
	</EF:EFRegion>
	<!-- 图片上传窗口 -->
	<EF:EFWindow id="picChoose" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="ReUpload" title="图片上传">
			<EF:EFUpload ename="mtrePic" docTag="cs_fs_file" path="cs/img"/>
		</EF:EFRegion>
	</EF:EFWindow>

	<EF:EFWindow id="popDataItem" url="" lazyload="true" width="95%"
		height="90%" title="新增保洁事项" modal="true" />

</EF:EFPage>
