<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!--人员业务科室配置-->
<EF:EFPage title="人员业务科室配置">
	<div class="row">
		<div class="col-md-4">
			<EF:EFRegion id="save_data" title="保存人员业务科室配置">
				<div class="row">
					<EF:EFInput ename="type" cname="操作" type="hidden"/>
					<EF:EFInput ename="save_data-0-id" cname="id" type="hidden"/>
					<div class="col-md-12">
						<div class="form-group">
							<label for="save_data-0-name" class="col-xs-4 control-label">
								<span class="i-input-required">*</span>姓名
							</label>
							<div class="col-xs-8">
								<input id="save_data-0-workNo" name="save_data-0-workNo" type="hidden">
								<input id="save_data-0-name" name="save_data-0-name" ondblclick="showAll('save_data-0-name')" >
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label for="save_data-0-deptName" class="col-xs-4 control-label">
								<span class="i-input-required">*</span>姓名业务科室
							</label>
							<div class="col-xs-8">
								<input id="save_data-0-deptCode" name="save_data-0-deptCode" type="hidden">
								<input id="save_data-0-deptName" name="save_data-0-deptName" ondblclick="showAll('save_data-0-deptName')" >
							</div>
						</div>
					</div>
				</div>
				<div class="button-region" id="buttonDiv" style="float: right">
					<EF:EFButton cname="保存" ename="SAVE" layout="0" ></EF:EFButton>
				</div>
			</EF:EFRegion>
		</div>
		<div class="col-md-8">
			<EF:EFRegion id="inqu" title="" showClear="true">
				<div class="row">
					<EF:EFInput ename="inqu_status-0-workNo" cname="工号" />
					<EF:EFInput ename="inqu_status-0-name" cname="姓名" />
					<EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" />
				</div>
				<div class="button-region" id="buttonDiv">
					<EF:EFButton cname="查询" ename="QUERY" layout="0" ></EF:EFButton>
					<EF:EFButton cname="重置" ename="REQUERY" layout="0" ></EF:EFButton>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="人员业务科室配置信息">
				<EF:EFGrid blockId="result" autoDraw="no" checkMode="row" autoBind="true" readonly="true">
					<EF:EFColumn ename="id" cname="id" hidden="true" />
					<EF:EFColumn ename="workNo" cname="工号" align="center" />
					<EF:EFColumn ename="name" cname="姓名" width="100" align="center" />
					<EF:EFColumn ename="deptCode" cname="科室编码" align="center"/>
					<EF:EFColumn ename="deptName" cname="科室名称" align="center"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-autoComplete.js"></script>