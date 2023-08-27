<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="保洁事项选择">
	<div class="row">
		<div class="col-md-2">
			<EF:EFRegion id="R1" title="保洁事项分类" fitHeight="true" >
				<EF:EFTree id="tree01" textField="classifyName" valueField="id"
					hasChildren="hasChildren" serviceName="CSSX01"
					methodName="queryTree">
				</EF:EFTree>
			</EF:EFRegion>
		</div>
		<div class="col-md-10">
			<EF:EFRegion id="inqu" title="查询条件" showClear="true">
				<div class="row">
					<EF:EFInput ename="itemName" cname="保洁事项名称：" colWidth="6" />
					<EF:EFInput ename="itemCodeList" cname="itemCodeList" type="hidden" colWidth="6" />
					<EF:EFInput ename="typeId" cname="" type="hidden" />
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="保洁事项列表">
				<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" autoFit="false" checkMode="multiple,row"
					readonly="true" rowNo="true"
					fitHeight="true">
					<EF:EFColumn ename="itemId" cname="id" width="100" hidden="true" />
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
	</div>
</EF:EFPage>

