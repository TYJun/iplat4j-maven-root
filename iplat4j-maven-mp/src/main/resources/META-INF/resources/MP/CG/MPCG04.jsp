<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>采购计划审批</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-purchaseId" cname="采购计划ID" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-purchaseNo" cname="采购计划单号"/>
			<c:choose>
				<c:when test="${roleAuth == 'deptAndLeader'}">
					<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态">
						<EF:EFOption label="请选择" value=""/>
						<EF:EFOption label="待审批" value="10"/>
						<EF:EFOption label="分管领导审批" value="60"/>
					</EF:EFSelect>
				</c:when>
				<c:otherwise>
					<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态" readonly="true">
						<EF:EFCodeOption codeName="wilp.mp.purchase.planStatus"/>
					</EF:EFSelect>
				</c:otherwise>
			</c:choose>
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" />
		</div>

		<div class="row">
			<EF:EFInput ename="inqu_status-0-recCreatorName" cname="创建人" />
			<EF:EFDatePicker ename="inqu_status-0-recCreateTimeS" cname="创建日期起" readonly="true"/>
			<EF:EFDatePicker ename="inqu_status-0-recCreateTimeE" cname="创建日期止" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-roleAuth" cname="角色权限" type="hidden" />
			<EF:EFInput ename="inqu_status-0-depts" cname="下级业务科室编码字符串" type="hidden" />
			<EF:EFInput ename="inqu_status-0-curDept" cname="当人登录人科室编码" type="hidden" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="RESET" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="采购计划审批">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="multiple,cell"
				   autoBind="true" readonly="true" rowNo="true" height="200">
			<EF:EFColumn ename="id" cname="主键" width="100" align="center" hidden="true" />
			<EF:EFColumn ename="purchaseNo" cname="采购计划单号" width="100" align="center" />
			<EF:EFColumn ename="purchaseNum" cname="计划数量" width="100" align="center" />
			<EF:EFColumn ename="purchaseCost" cname="计划总价" width="100" align="center"/>
			<EF:EFComboColumn ename="statusCode" cname="状态" width="100" align="center">
				<EF:EFCodeOption codeName="wilp.mp.purchase.planStatus"/>
			</EF:EFComboColumn>
			<EF:EFColumn ename="recCreator" cname="创建人" width="100" align="center"/>
			<EF:EFColumn ename="recCreateTime" cname="创建日期" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>


	<EF:EFRegion id="detail" title="采购计划明细列表">
		<EF:EFGrid blockId="detail" autoDraw="no" autoBind="false" checkMode="row" readonly="true" queryMethod="queryDetail">
			<EF:EFColumn ename="matNum" cname="物资编码" align="center" />
			<EF:EFColumn ename="matName" cname="物资名称" align="center" />
			<EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true" />
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" />
			<EF:EFColumn ename="matSpec" cname="物资规格" align="center" />
			<EF:EFColumn ename="matModel" cname="物资型号" align="center" />
			<EF:EFComboColumn ename="unit" cname="计量单位" align="center" enable="false">
				<EF:EFCodeOption codeName="wilp.ac.gm.unit"/>
			</EF:EFComboColumn>
			<EF:EFColumn ename="price" cname="单价" align="center" hidden="true"/>
			<EF:EFColumn ename="num" cname="采购计划数量" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="" url=" " lazyload="true" width="90%" height="90%"></EF:EFWindow>

<EF:EFWindow id="popData2" title="采购计划" url=" " lazyload="true" width="90%" height="90%"></EF:EFWindow>