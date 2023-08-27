<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>入库编辑</title>--%>
<EF:EFPage>
	<div class="row">
		<div class="col-md-6">
			<EF:EFRegion id="inqu" title="物资选择" showClear="true">
				<div class="row" style="height: 40px; line-height: 40px">
					<EF:EFInput ename="matNum" cname="物资编码" />
					<EF:EFInput ename="matName" cname="物资名称" />
					<EF:EFSelect resultId="matType" ename="matTypeNum" cname="物资分类" optionLabel="请选择"
								 serviceName="SITY02" methodName="selectMatType" filter="contains"
								 textField="matClassName" valueField="matClassCode">
					</EF:EFSelect>
				</div>
				<div class="button-region" id="buttonDiv">
					<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
					<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="mat" title="物质列表">
				<EF:EFGrid blockId="mat" autoDraw="no" checkMode="row"
						   readonly="true" sort="single" queryMethod="queryMatInformation" serviceName="SIRK0106">
					<EF:EFColumn ename="matNum" cname="物资编码" width="90" align="center" sort="true"/>
					<EF:EFColumn ename="matName" cname="物资名称" width="120" align="center" sort="false"/>
					<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" sort="false"/>
					<%--<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center"/>--%>
					<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" align="center" sort="false"/>
					<EF:EFColumn ename="unitName" cname="计量单位" width="60" align="center" sort="false"/>
					<EF:EFColumn ename="price" cname="单价" width="60" align="center" sort="false"/>
					<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true" sort="false"/>
					<EF:EFColumn ename="matTypeName" cname="物资分类" width="100" align="center" sort="false"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>

		<div class = "col-md-6">
			<EF:EFRegion id="inqu" title="入库信息">
				<div class="row">
					<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称" colWidth="6"
								 resultId="result" textField="wareHouseName" required="true"
								 valueField="wareHouseNo" serviceName="SIWH01"
								 methodName="selectUseWareHouse" optionLabel="请选择">
					</EF:EFSelect>
					<div class="col-md-6">
						<div class="form-group">
							<label for="inqu_status-0-supplierName" class="col-xs-4 control-label">供应商</label>
							<div class="col-xs-8">
								<input id="inqu_status-0-supplierNum" name="inqu_status-0-supplierNum" type="hidden">
								<input id="inqu_status-0-supplierName" name="inqu_status-0-supplierName" ondblclick="showAll('inqu_status-0-supplierName')" >
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="inqu_status-0-deptName" class="col-xs-4 control-label">领用科室</label>
							<div class="col-xs-8">
								<input id="inqu_status-0-deptNum" name="inqu_status-0-deptNum" type="hidden">
								<input id="inqu_status-0-deptName" name="inqu_status-0-deptName" ondblclick="showAll('inqu_status-0-deptName')" >
							</div>
						</div>
					</div>
					<EF:EFInput ename="inqu_status-0-enterBillNo" cname="入库单号" type="hidden"/>
					<EF:EFInput ename="inqu_status-0-remark" cname="备注" type="textarea" placeholder="不能超过200字" colWidth="6"/>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="入库信息">
				<EF:EFGrid blockId="result" autoDraw="no" autoBind="false"  checkMode="row" >
					<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
					<EF:EFColumn ename="matNum" cname="物资编码" width="90" enable="false" align="center" />
					<EF:EFColumn ename="matName" cname="物资名称" width="120" enable="false" align="center"/>
					<EF:EFColumn ename="matSpec" cname="物资规格" width="100" enable="false" align="center"/>
					<%--<EF:EFColumn ename="matModel" cname="物资型号" width="100" enable="false" align="center"/>--%>
					<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
					<EF:EFColumn ename="unitName" cname="单位" width="60" enable="false" align="center"/>
					<EF:EFColumn ename="enterNum" cname="入库数量" width="80" align="center"/>
					<EF:EFColumn ename="enterPrice" cname="入库单价(元)" width="100" align="center"/>
					<EF:EFColumn ename="enterAmount" cname="入库总价(元)" width="100" enable="false" align="center"/>
					<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true"/>
					<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" enable="false" align="center"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
	</div>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-autoComplete.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>