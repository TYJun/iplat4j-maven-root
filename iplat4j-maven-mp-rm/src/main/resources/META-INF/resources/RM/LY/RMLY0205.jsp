<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!--领用单录入-->
<EF:EFPage>
	<div class="row">
		<div class="col-md-6">
			<EF:EFRegion id="inqu" title="查询条件" showClear="true">
				<div class="row">
					<EF:EFInput ename="inqu_status-0-matNum" cname="物资编码" />
					<EF:EFInput ename="inqu_status-0-matName" cname="物资名称"/>
					<EF:EFInput ename="inqu_status-0-matTypeName" cname="物资分类名称" />
				</div>
				<div class="button-region" id="buttonDiv">
					<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
					<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="mat" title="物资列表">
				<EF:EFGrid blockId="mat" autoDraw="no" autoBind="true" checkMode="row"
						   readonly="true" queryMethod="queryStockMat" sort="single">
					<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
					<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center" enable="false" sort="true"/>
					<EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false" sort="false"/>
					<EF:EFColumn ename="matSpec" cname="物资规格" align="center" enable="false" sort="false"/>
					<EF:EFColumn ename="matModel" cname="物资型号" hidden="true" sort="false"/>
					<EF:EFColumn ename="stockNum" cname="库存量" align="center" enable="false" sort="false"/>
					<EF:EFColumn ename="price" cname="单价" align="center" enable="false" sort="false"/>
					<EF:EFColumn ename="unit" cname="计量单位" hidden="true" enable="false" sort="false"/>
					<EF:EFColumn ename="unitName" cname="计量单位" width="80" align="center" enable="false" sort="false"/>
					<EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true" sort="false"/>
					<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false" sort="false"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
		<div class="col-md-6">
			<EF:EFRegion id="add_claim" title="领用申请">
				<div class="row">
					<EF:EFInput ename="add_claim-0-deptName" cname="申领科室" colWidth="6" readonly="true"/>
					<EF:EFInput ename="add_claim-0-deptNum" cname="领用科室" type="hidden"/>

					<div class="col-md-6">
						<div class="form-group">
							<label for="add_claim-0-costDeptName" class="col-xs-4 control-label">
								<span class="i-input-required">*</span>成本归集科室
							</label>
							<div class="col-xs-8">
								<input id="add_claim-0-costDeptNum" name="add_claim-0-costDeptNum" type="hidden">
								<input id="add_claim-0-costDeptName" name="add_claim-0-costDeptName" ondblclick="showAll('add_claim-0-costDeptName')" >
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label for="add_claim-0-applyUserName" class="col-xs-4 control-label">
								申领人
							</label>
							<div class="col-xs-8">
								<input id="add_claim-0-applyUserNo" name="add_claim-0-applyUserNo" type="hidden">
								<input id="add_claim-0-applyUserName" name="add_claim-0-applyUserName" ondblclick="showAll('add_claim-0-applyUserName')" >
							</div>
						</div>
					</div>
					<EF:EFSelect ename="add_claim-0-wareHouseNo" cname="仓库" colWidth="6" optionLabel="请选择仓库" filter="contains" resultId="wareHouse"
								 serviceName="RMJK03" methodName="dockWareHouse"
								 textField="wareHouseName" valueField="wareHouseNo">
					</EF:EFSelect>
					<EF:EFInput type="hidden" ename="add_claim-0-wareHouseName" cname="仓库" />
					<EF:EFInput ename="add_claim-0-remark" cname="备注" type="textarea" colWidth="6" placeholder="不能超过200字"/>
					<EF:EFInput type="hidden" ename="type" cname="操作类型" />
					<EF:EFInput type="hidden" ename="add_claim-0-id" cname="主键" />
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="领用申请明细列表">
				<EF:EFGrid blockId="result" autoDraw="no" autoBind="false" checkMode="row">
					<EF:EFColumn ename="matNum" cname="物资编码" align="center" enable="false" width="80"/>
					<EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false" width="100"/>
					<EF:EFColumn ename="matSpec" cname="物资规格" align="center" enable="false" width="80"/>
					<EF:EFColumn ename="matModel" cname="物资型号" hidden="true"/>
					<EF:EFColumn ename="unit" cname="计量单位" hidden="true" />
					<EF:EFColumn ename="unitName" cname="计量单位" align="center" enable="false" width="70"/>
					<EF:EFColumn ename="price" cname="单价(元)" align="center" enable="false" width="70"/>
					<EF:EFColumn ename="num" cname="领用数量" align="center" width="70"/>
					<EF:EFColumn ename="claimAmount" cname="领用金额(元)" width="90" align="center" enable="false"/>
					<EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true" />
					<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false" width="100"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
	</div>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-autoComplete.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-keydown.js"></script>
