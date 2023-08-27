<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>退库信息</title>--%>
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
						   readonly="true" queryMethod="queryMat">
					<EF:EFColumn ename="matNum" cname="物资编码" width="80" align="center" enable="false"/>
					<EF:EFColumn ename="matName" cname="物资名称" width="100" align="center" enable="false"/>
					<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" enable="false"/>
					<%--<EF:EFColumn ename="matModel" cname="物资型号" hidden="true"/>--%>
					<EF:EFColumn ename="unit" cname="计量单位" hidden="true" enable="false"/>
					<EF:EFColumn ename="unitName" cname="计量单位" width="70" align="center" enable="false"/>
					<EF:EFColumn ename="price" cname="单价"  width="70" align="center" enable="false"/>
					<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true" />
					<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" align="center" enable="false"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
		<div class="col-md-6">
			<EF:EFRegion id="red_rush" title="红冲出库信息">
				<div class="row">
					<EF:EFSelect ename="red_rush-0-wareHouseNo" cname="仓库名称" colWidth="6"
								 resultId="result" textField="wareHouseName"
								 valueField="wareHouseNo" serviceName="SIWH01"
								 methodName="selectUseWareHouse" optionLabel="请选择" required="true" >
					</EF:EFSelect>
					<EF:EFPopupInput ename="red_rush-0-userDeptNum" cname="领用科室" colWidth="6"
									 popupTitle="科室选择" required="true" readOnly="true"
									 popupType="ServiceGrid" serviceName="SIRK01" methodName="queryDept"
									 resultId="dept" valueField="deptNum" textField="deptName"
									 columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
					<EF:EFInput ename="red_rush-0-remark" cname="红冲原因" type="textarea" colWidth="6"/>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="红冲明细信息">
				<EF:EFGrid blockId="result" autoDraw="no" checkMode="row" autoBind="false">
					<EF:EFColumn ename="matNum" cname="物资编码" width="80" enable="false" align="center"/>
					<EF:EFColumn ename="matName" cname="物资名称" width="100" enable="false" align="center"/>
					<EF:EFColumn ename="matSpec" cname="物资规格" width="80" enable="false" align="center"/>
					<EF:EFColumn ename="matModel" cname="物资型号" hidden="true"/>
					<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true"/>
					<EF:EFColumn ename="matTypeName" cname="物资分类" width="100" enable="false" align="center"/>
					<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
					<EF:EFColumn ename="unitName" cname="计量单位" width="70" enable="false" align="center"/>
					<EF:EFColumn ename="price" cname="单价"  width="70" align="center" enable="false"/>
					<EF:EFColumn ename="redRushNum" cname="红冲数量" width="80" />
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
	</div>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>