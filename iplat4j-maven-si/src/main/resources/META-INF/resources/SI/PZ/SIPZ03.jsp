<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!--人员业务科室配置-->
<EF:EFPage title="人员业务科室配置">
	<div class="row">
		<div class="col-md-4">
			<EF:EFRegion id="save_data" title="仓库与物资大类关联配置">
				<div class="row">
					<EF:EFInput ename="type" cname="操作" type="hidden"/>
					<EF:EFInput ename="save_data-0-id" cname="id" type="hidden"/>
					<EF:EFSelect ename="save_data-0-wareHouseNo" cname="仓库名称" colWidth="12" require="true"
								 resultId="result" textField="wareHouseName" valueField="wareHouseNo"
								 serviceName="SIWH01" methodName="query" optionLabel="请选择">
					</EF:EFSelect>
				</div>
				<div class="row">
					<EF:EFMultiSelect ename="save_data-0-relateMatType" cname="关联物资大类" colWidth="12"
									  autoClose="false" filter="contains" require="true">
						<EF:EFOptions blockId="matRootType" textField="label" valueField="value"/>
					</EF:EFMultiSelect>
				</div>
				<div class="row">
					<EF:EFMultiSelect ename="save_data-0-applyMatType" cname="可申领物资大类" colWidth="12"
									  autoClose="false" filter="contains" require="true">
						<EF:EFOptions blockId="matRootType" textField="label" valueField="value"/>
					</EF:EFMultiSelect>
				</div>
				<div class="button-region" id="buttonDiv" style="float: right">
					<EF:EFButton cname="保存" ename="SAVE" layout="0" ></EF:EFButton>
				</div>
			</EF:EFRegion>
		</div>
		<div class="col-md-8">
			<EF:EFRegion id="inqu" title="" showClear="true">
				<div class="row">
					<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称"
								 resultId="result" textField="wareHouseName" valueField="wareHouseNo"
								 serviceName="SIWH01" methodName="query" optionLabel="请选择">
					</EF:EFSelect>
					<EF:EFSelect ename="inqu_status-0-relateMatType" cname="关联物资大类"
								 resultId="matRootType" textField="label" valueField="value"
								 serviceName="SIPZ03" methodName="queryMatRootType" optionLabel="请选择">
					</EF:EFSelect>
				</div>
				<div class="button-region" id="buttonDiv">
					<EF:EFButton cname="查询" ename="QUERY" layout="0" ></EF:EFButton>
					<EF:EFButton cname="重置" ename="REQUERY" layout="0" ></EF:EFButton>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="人员业务科室配置信息">
				<EF:EFGrid blockId="result" autoDraw="no" checkMode="row" autoBind="true" readonly="true">
					<EF:EFColumn ename="id" cname="id" hidden="true" />
					<EF:EFColumn ename="wareHouseNo" cname="仓库号" align="center" width="100"/>
					<EF:EFColumn ename="wareHouseName" cname="仓库名称" width="100" align="center" />
					<EF:EFColumn ename="relateMatType" cname="关联物资大类" hidden="true"/>
					<EF:EFColumn ename="relateMatTypeName" cname="关联物资大类" align="center"/>
					<EF:EFColumn ename="applyMatType" cname="可申领物资大类" hidden="true"/>
					<EF:EFColumn ename="applyMatTypeName" cname="可申领物资大类" align="center"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>