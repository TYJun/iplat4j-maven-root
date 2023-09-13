<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!--临时需求计划信息-->
<EF:EFPage title="临时需求计划信息">
	<EF:EFRegion id="inqu" title="需求计划">
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label for="recCreator" class="col-xs-4 control-label">申请人</label>
					<div class="col-xs-8">
						<input id="recCreator" name="recCreator" type="hidden">
						<input id="recCreatorName" name="recCreatorName" ondblclick="showAll('recCreatorName')" >
					</div>
				</div>
			</div>
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室" readonly="true" colWidth="4"/>
			<EF:EFInput ename="inqu_status-0-deptNum" cname="科室" type="hidden"/>
			<div class="col-md-4">
				<div class="form-group">
					<label for="deptPrincipal" class="col-xs-4 control-label">科室负责人</label>
					<div class="col-xs-8">
						<input id="deptPrincipal" name="deptPrincipal" type="hidden">
						<input id="deptPrincipalName" name="deptPrincipalName" ondblclick="showAll('deptPrincipalName')" >
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-planDesc" cname="需求计划描述" type="textarea"  placeholder="不能超过200字"/>
			<EF:EFInput ename="inqu_status-0-remark" cname="申请理由" type="textarea"  placeholder="不能超过200字"/>
			<c:if test="${type == 'see'}">
				<EF:EFInput ename="inqu_status-0-rejectReason" cname="驳回原因" type="textarea" readonly="true"/>
			</c:if>
			<EF:EFInput type="hidden" ename="type" cname="操作类型" />
			<EF:EFInput type="hidden" ename="inqu_status-0-id" cname="主键" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="临时需求计划明细列表">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row" height="365">
			<EF:EFColumn ename="matNum" cname="物资编码" align="center" enable="false"/>
			<EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" align="center" enable="false"/>
			<EF:EFColumn ename="matModel" cname="物资型号" align="center" enable="false"/>
			<EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true" />
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false"/>
			<EF:EFColumn ename="unit" cname="计量单位" hidden="true" />
			<EF:EFColumn ename="unitName" cname="计量单位" align="center" />
			<EF:EFColumn ename="price" cname="参考单价(元)" align="center" enable="false"/>
			<EF:EFColumn ename="num" cname="需求数量" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="85%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-autoComplete.js"></script>
