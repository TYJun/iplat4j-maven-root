<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!--采购科室配置信息-->
<EF:EFPage>
	<EF:EFRegion id="inqu" title="采购科室配置信息">
		<div class="row">
			<EF:EFTreeInput ename="inqu_status-0-matTypeId" cname="物资分类" required="true" save="false"
							serviceName="MPTY01" methodName="selectMatTypeTree" textField="text"
							valueField="label" hasChildren="leaf" root="{label: 'root',text: '分类'}"
							popupTitile="上级分类" clear="true" placeholder="请选择">
			</EF:EFTreeInput>
			<EF:EFInput ename="inqu_status-0-matTypeNum" cname="物资分类编码" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-matTypeName" cname="物资分类名称" type="hidden"/>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label for="deptName" class="col-xs-4 control-label">科室名称</label>
					<div class="col-xs-8">
						<input id="deptNum" name="deptNum" type="hidden">
						<input id="deptName" name="deptName" ondblclick="showAll('deptName')" >
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" type="hidden" />
			<EF:EFInput ename="type" type="hidden" />
			<EF:EFInput ename="inqu_status-0-remark" cname="备注" type="textarea" placeholder="不能超过200字"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="确定" ename="SUBMIT" layout="0" ></EF:EFButton>
			<EF:EFButton cname="关闭" ename="CANCEL" layout="0" ></EF:EFButton>
		</div>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/MP/common/mp-autoComplete.js"></script>
<script type="text/javascript">
	$(function() {
		let op = $("#type").val();//获取操作
		//通过点击查看弹窗是隐藏提交按钮
		if (op == "see") {
			$("#SUBMIT").css("display", "none");
			$("#CANCEL").css("display", "none");
		}
	});
</script>